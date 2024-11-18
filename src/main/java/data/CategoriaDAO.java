package data;

import entities.Categoria;
import data.interfaces.CrudCategoriaInterface;
import database.Conexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements CrudCategoriaInterface {

    private final Connection conexion;

    public CategoriaDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Categoria> listar(String nombre) throws SQLException {
        List<Categoria> lista = new ArrayList<>();
        String sql = nombre.isEmpty()
                ? "SELECT * FROM Categoria;"
                : "SELECT * FROM Categoria WHERE nombre LIKE ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!nombre.isEmpty()) {
                st.setString(1, "%" + nombre + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Categoria categoria = new Categoria(
                            rs.getInt("id_categoria"),
                            rs.getString("nombre"),
                            rs.getString("descripción"),
                            rs.getBoolean("activo")
                    );
                    lista.add(categoria);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categoria (nombre, descripción, activo) VALUES (?, ?, ?);";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, categoria.getNombre());
            st.setString(2, categoria.getDescripcion());
            st.setBoolean(3, categoria.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categoria SET nombre = ?, descripción = ?, activo = ? WHERE id_categoria = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, categoria.getNombre());
            st.setString(2, categoria.getDescripcion());
            st.setBoolean(3, categoria.isActivo());
            st.setInt(4, categoria.getIdCategoria());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idCategoria) throws SQLException {
        String sql = "DELETE FROM Categoria WHERE id_categoria = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idCategoria);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) throws SQLException {
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM Categoria WHERE id_categoria = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
        }
        return nombre;
    }

    @Override
    public boolean desactivar(int id) {
        return cambiarEstado(id, false);
    }

    @Override
    public boolean activar(int id) {
        return cambiarEstado(id, true);
    }

    private boolean cambiarEstado(int id, boolean activo) {
        String sql = "UPDATE Categoria SET activo = ? WHERE id_categoria = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setBoolean(1, activo);
            st.setInt(2, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int total() {
        String sql = "SELECT COUNT(*) FROM Categoria;";
        try (PreparedStatement st = conexion.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean existencia(String texto) {
        String sql = "SELECT 1 FROM Categoria WHERE nombre = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, texto);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Categoria getCategoriaById(int idCategoria) throws SQLException {
        Categoria categoria = null;
        String sql = "SELECT * FROM Categoria WHERE id_categoria = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idCategoria);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria(
                            rs.getInt("id_categoria"),
                            rs.getString("nombre"),
                            rs.getString("descripción"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return categoria;
    }
}
