package data;

import data.interfaces.CrudMarcaInterface;
import database.Conexion;
import entities.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDAO implements CrudMarcaInterface {

    private final Connection conexion;

    public MarcaDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Marca> listar(String nombre) throws SQLException {
        List<Marca> lista = new ArrayList<>();
        String sql = nombre.isEmpty()
                ? "SELECT * FROM Marca;"
                : "SELECT * FROM Marca WHERE nombre LIKE ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!nombre.isEmpty()) {
                st.setString(1, "%" + nombre + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Marca marca = new Marca(
                            rs.getInt("id_marca"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getBoolean("activo")
                    );
                    lista.add(marca);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(Marca marc) throws SQLException {
        String sql = "INSERT INTO Marca (nombre, descripcion, activo) VALUES (?, ?, ?);";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, marc.getNombre());
            st.setString(2, marc.getDescripcion());
            st.setBoolean(3, marc.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Marca marc) throws SQLException {
        String sql = "UPDATE Marca SET nombre = ?, descripcion = ?, activo = ? WHERE id_marca = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, marc.getNombre());
            st.setString(2, marc.getDescripcion());
            st.setBoolean(3, marc.isActivo());
            st.setInt(4, marc.getIdMarca());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Marca WHERE id_marca = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) throws SQLException {
        String sql = "SELECT nombre FROM Marca WHERE id_marca = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("nombre");
                }
            }
        }
        return "Desconocido";
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
        String sql = "UPDATE Marca SET activo = ? WHERE id_marca = ?;";
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
        String sql = "SELECT COUNT(*) FROM Marca;";
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
        String sql = "SELECT 1 FROM Marca WHERE nombre = ?;";
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
    public Marca getMarcaById(int id) throws SQLException {
        String sql = "SELECT * FROM Marca WHERE id_marca = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return new Marca(
                            rs.getInt("id_marca"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return null;
    }
}
