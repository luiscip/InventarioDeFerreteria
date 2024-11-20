package data;

import entities.Proveedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.CrudProveedorInterface;
import database.Conexion;

public class ProveedorDAO implements CrudProveedorInterface {

    private Connection conexion;

    public ProveedorDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Proveedor> listar(String nombre) throws SQLException {
        List<Proveedor> lista = new ArrayList<>();
        String sql = nombre.isEmpty() 
                ? "SELECT * FROM Proveedor;" 
                : "SELECT * FROM Proveedor WHERE nombre LIKE ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!nombre.isEmpty()) {
                st.setString(1, "%" + nombre + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Proveedor proveedor = new Proveedor(
                        rs.getInt("id_proveedores"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getBoolean("activo")
                    );
                    lista.add(proveedor);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO Proveedor (nombre, telefono, email, direccion, activo) VALUES (?, ?, ?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, proveedor.getNombreProveedor());
            st.setString(2, proveedor.getTelefonoProveedor());
            st.setString(3, proveedor.getEmailProveedor());
            st.setString(4, proveedor.getDireccionProveedor());
            st.setBoolean(5, proveedor.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE Proveedor SET nombre = ?, telefono = ?, email = ?, direccion = ?, activo = ? WHERE id_proveedores = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, proveedor.getNombreProveedor());
            st.setString(2, proveedor.getTelefonoProveedor());
            st.setString(3, proveedor.getEmailProveedor());
            st.setString(4, proveedor.getDireccionProveedor());
            st.setBoolean(5, proveedor.isActivo());
            st.setInt(6, proveedor.getIdProveedor());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idProveedor) throws SQLException {
        String sql = "DELETE FROM Proveedor WHERE id_proveedores = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idProveedor);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) {
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM Proveedor WHERE id_proveedores = ?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        String sql = "UPDATE Proveedor SET activo = ? WHERE id_proveedores = ?;";
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
        String sql = "SELECT COUNT(*) FROM Proveedor;";
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
    public boolean existencia(String nombre) {
        String sql = "SELECT 1 FROM Proveedor WHERE nombre = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, nombre);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Proveedor getProveedorById(int idProveedor) throws Exception {
        Proveedor proveedor = null;
        String sql = "SELECT * FROM Proveedor WHERE id_proveedores = ? LIMIT 1;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idProveedor);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    proveedor = new Proveedor(
                        rs.getInt("id_proveedores"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getBoolean("activo")
                    );
                }
            }
        } catch (SQLException e) {
            throw e;
        }
        return proveedor;
    }
}
