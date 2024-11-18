package data;

import entities.Compra;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.CrudComprasInterface;
import database.Conexion;

public class CompraDAO implements CrudComprasInterface {

    private Connection conexion;

    public CompraDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Compra> listar(String nombre) throws SQLException {
        List<Compra> lista = new ArrayList<>();
        // Para esta implementación, suponemos que 'nombre' no es aplicable directamente,
        // pero puedes personalizarlo si hay un campo relevante para filtrar.
        String sql = "SELECT * FROM Compra;";

        try (PreparedStatement st = conexion.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("id_compra"),
                        rs.getInt("id_proveedor"),
                        rs.getString("fecha_compra"),
                        rs.getDouble("total"),
                        rs.getBoolean("activo")
                );
                lista.add(compra);
            }
        }
        return lista;
    }

    @Override
    public void registrar(Compra compra) throws SQLException {
        String sql = "INSERT INTO Compra (id_proveedor, fecha_compra, total, activo) VALUES (?, ?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, compra.getIdProveedor());
            st.setString(2, compra.getFechaCompra());
            st.setDouble(3, compra.getTotalCompra());
            st.setBoolean(4, compra.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Compra compra) throws SQLException {
        String sql = "UPDATE Compra SET id_proveedor = ?, fecha_compra = ?, total = ?, activo = ? WHERE id_compra = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, compra.getIdProveedor());
            st.setString(2, compra.getFechaCompra());
            st.setDouble(3, compra.getTotalCompra());
            st.setBoolean(4, compra.isActivo());
            st.setInt(5, compra.getIdCompra());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idCompra) throws SQLException {
        String sql = "DELETE FROM Compra WHERE id_compra = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idCompra);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) throws SQLException {
        // Este método es un ejemplo y puede no tener sentido en el contexto de 'Compra'.
        // Aquí lo implementamos solo como referencia.
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM Proveedor WHERE id_proveedores = ?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
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
        String sql = "UPDATE Compra SET activo = ? WHERE id_compra = ?;";
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
        String sql = "SELECT COUNT(*) FROM Compra;";
        try (PreparedStatement st = conexion.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
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
        // Puedes personalizar la búsqueda según sea necesario
        String sql = "SELECT 1 FROM Compra WHERE id_proveedor = ?;";
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
    public Compra getCategoriaById(int idCompra) throws SQLException {
        Compra compra = null;
        String sql = "SELECT * FROM Compra WHERE id_compra = ? LIMIT 1;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idCompra);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    compra = new Compra(
                            rs.getInt("id_compra"),
                            rs.getInt("id_proveedor"),
                            rs.getString("fecha_compra"),
                            rs.getDouble("total"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return compra;
    }
}
