package data;

import entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.CrudProductoInterface;
import database.Conexion;  // Asegúrate de importar la clase Conexion

public class ProductoDAO implements CrudProductoInterface {

    private Connection conexion;  // Ya no es final

    public ProductoDAO() {
        // Establecer la conexión usando la clase Conexion, pero no la asignamos a una variable final
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Producto> listar(String nombre) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        String sql = nombre.isEmpty()
                ? "SELECT * FROM Producto;"
                : "SELECT * FROM Producto WHERE nombre LIKE ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!nombre.isEmpty()) {
                st.setString(1, "%" + nombre + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto(
                            rs.getInt("id_producto"),
                            rs.getInt("id_categoria"),
                            rs.getInt("id_marca"),
                            rs.getInt("id_ubicacion"),
                            rs.getString("nombre"),
                            rs.getString("descripcion"),
                            rs.getDouble("precio_compra"),
                            rs.getDouble("precio_venta"),
                            rs.getInt("stock"),
                            rs.getInt("stock_minimo"),
                            rs.getString("fecha_ultima_actualizacion"),
                            rs.getBoolean("activo")
                    );
                    lista.add(producto);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(Producto produc) throws SQLException {
        String sql = "INSERT INTO Producto (id_categoria, id_marca, id_ubicacion, nombre, descripcion, precio_compra, precio_venta, stock, stock_minimo, fecha_ultima_actualizacion, activo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, produc.getIdCategoria());
            st.setInt(2, produc.getIdMarca());
            st.setInt(3, produc.getIdUbicacion());
            st.setString(4, produc.getNombre());
            st.setString(5, produc.getDescripcion());
            st.setDouble(6, produc.getPrecioCompra());
            st.setDouble(7, produc.getPrecioVenta());
            st.setInt(8, produc.getStock());
            st.setInt(9, produc.getStockMinimo());
            st.setString(10, produc.getFechaUltimaActualizacion());
            st.setBoolean(11, produc.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Producto produc) throws SQLException {
        String sql = "UPDATE Producto SET id_categoria = ?, id_marca = ?, id_ubicacion = ?, nombre = ?, descripcion = ?, precio_compra = ?, precio_venta = ?, stock = ?, stock_minimo = ?, fecha_ultima_actualizacion = ?, activo = ? WHERE id_producto = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, produc.getIdCategoria());
            st.setInt(2, produc.getIdMarca());
            st.setInt(3, produc.getIdUbicacion());
            st.setString(4, produc.getNombre());
            st.setString(5, produc.getDescripcion());
            st.setDouble(6, produc.getPrecioCompra());
            st.setDouble(7, produc.getPrecioVenta());
            st.setInt(8, produc.getStock());
            st.setInt(9, produc.getStockMinimo());
            st.setString(10, produc.getFechaUltimaActualizacion());
            st.setBoolean(11, produc.isActivo());
            st.setInt(12, produc.getIdProducto());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idProducto) throws SQLException {
        String sql = "DELETE FROM Producto WHERE id_producto = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idProducto);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) {
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM Categoria WHERE id_categoria = ?;";
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
        String sql = "UPDATE Producto SET activo = ? WHERE id_producto = ?;";
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
        String sql = "SELECT COUNT(*) FROM Producto;";
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
        String sql = "SELECT 1 FROM Producto WHERE nombre = ?;";
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
    public Producto getProductoById(int idProducto) throws Exception {
        Producto producto = null;

        try {
            // Obtener la conexión dentro del método
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM Producto WHERE id_producto = ? LIMIT 1;");
            st.setInt(1, idProducto);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getInt("id_categoria"),
                        rs.getInt("id_marca"),
                        rs.getInt("id_ubicacion"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_compra"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("stock"),
                        rs.getInt("stock_minimo"),
                        rs.getString("fecha_ultima_actualizacion"),
                        rs.getBoolean("activo")
                );
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            // Usar el método desconectar de la clase Conexion
            Conexion.getInstancia().desconectar();
        }
        return producto;
    }


}
