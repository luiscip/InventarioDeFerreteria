package data;

import database.Conexion;
import data.interfaces.CrudPaginadoInterface;
import entities.Producto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoDAO implements CrudPaginadoInterface<Producto> {

    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ProductoDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Producto> listar(String texto, int totalPorPagina, int numPagina) {
        List<Producto> registros = new ArrayList<>();
        try {
            ps = CON.conectar().prepareStatement(
                    "SELECT * FROM producto WHERE nombre LIKE ? ORDER BY id_producto ASC LIMIT ?, ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Producto(
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
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
            CON.desconectar();
        }
        return registros;
    }

    @Override
    public boolean insertar(Producto obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "INSERT INTO Producto (id_categoria, id_marca, id_ubicacion, nombre, descripcion, precio_compra, precio_venta, stock, stock_minimo, fecha_ultima_actualizacion, activo) VALUES (?,?,?,?,?,?,?,?,?,?,1)"
            );
            ps.setInt(1, obj.getIdCategoria());
            ps.setInt(2, obj.getIdMarca());
            ps.setInt(3, obj.getIdUbicacion());
            ps.setString(4, obj.getNombre());
            ps.setString(5, obj.getDescripcion());
            ps.setDouble(6, obj.getPrecioCompra());
            ps.setDouble(7, obj.getPrecioVenta());
            ps.setInt(8, obj.getStock());
            ps.setInt(9, obj.getStockMinimo());
            ps.setString(10, obj.getFechaUltimaActualizacion());

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Producto obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "UPDATE Producto SET id_categoria=?, id_marca=?, id_ubicacion=?, nombre=?, descripcion=?, precio_compra=?, precio_venta=?, stock=?, stock_minimo=?, fecha_ultima_actualizacion=? WHERE id_producto=?"
            );
            ps.setInt(1, obj.getIdCategoria());
            ps.setInt(2, obj.getIdMarca());
            ps.setInt(3, obj.getIdUbicacion());
            ps.setString(4, obj.getNombre());
            ps.setString(5, obj.getDescripcion());
            ps.setDouble(6, obj.getPrecioCompra());
            ps.setDouble(7, obj.getPrecioVenta());
            ps.setInt(8, obj.getStock());
            ps.setInt(9, obj.getStockMinimo());
            ps.setString(10, obj.getFechaUltimaActualizacion());
            ps.setInt(11, obj.getIdProducto());

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "UPDATE Producto SET activo=0 WHERE id_producto=?"
            );
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean activar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "UPDATE Producto SET activo=1 WHERE id_producto=?"
            );
            ps.setInt(1, id);

            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
        int totalRegistros = 0;
        try {
            ps = CON.conectar().prepareStatement(
                    "SELECT COUNT(id_producto) FROM Producto"
            );
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt(1);
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existencia(String existe) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "SELECT nombre FROM Producto WHERE nombre=?"
            );
            ps.setString(1, existe);
            rs = ps.executeQuery();

            if (rs.next()) {
                resp = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            CON.desconectar();
        }
        return resp;
    }
}
