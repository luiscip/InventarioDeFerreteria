
package data;

import database.Conexion;
import data.interfaces.CrudPaginadoInterface;
import entities.DetalleVenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class DetalleVentaDAO implements CrudPaginadoInterface<DetalleVenta>{
     private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public DetalleVentaDAO() {
        con = Conexion.getInstancia();
    }

    @Override
    public List<DetalleVenta> listar(String texto, int totalPorPagina, int numPagina) {
         List<DetalleVenta> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                "SELECT * FROM detalle_venta WHERE id_producto LIKE ? LIMIT ?, ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new DetalleVenta(
                    rs.getInt("id_detalle_venta"),
                    rs.getInt("id_venta"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getDouble("precio_venta_uni"),
                    rs.getBoolean("activo")
                ));
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            con.desconectar();
        }
        return registros;
    }


    @Override
    public boolean insertar(DetalleVenta obj) {
         resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "INSERT INTO detalle_venta (id_venta, id_producto, cantidad, precio_venta_uni, activo) VALUES (?, ?, ?, ?, 1)");
            ps.setInt(1, obj.getIdVenta());
            ps.setInt(2, obj.getIdProducto());
            ps.setInt(3, obj.getCantidad());
            ps.setDouble(4, obj.getPrecioVentaUni());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return resp;
    }


    @Override
    public boolean actualizar(DetalleVenta obj) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE detalle_venta SET id_venta = ?, id_producto = ?, cantidad = ?, precio_venta_uni = ? WHERE id_detalle_venta = ?");
            ps.setInt(1, obj.getIdVenta());
            ps.setInt(2, obj.getIdProducto());
            ps.setInt(3, obj.getCantidad());
            ps.setDouble(4, obj.getPrecioVentaUni());
            ps.setInt(5, obj.getIdDetalleVenta());
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return resp;
    }


    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE detalle_venta SET activo = 0 WHERE id_detalle_venta = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return resp;
    }


    @Override
    public boolean activar(int id) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE detalle_venta SET activo = 1 WHERE id_detalle_venta = ?");
            ps.setInt(1, id);
            if (ps.executeUpdate() > 0) {
                resp = true;
            }
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            con.desconectar();
        }
        return resp;
    }

    @Override
    public int total() {
       int totalRegistros = 0;
        try {
            ps = con.conectar().prepareStatement(
                "SELECT COUNT(id_detalle_venta) FROM detalle_venta");
            rs = ps.executeQuery();
            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id_detalle_venta)");
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            con.desconectar();
        }
        return totalRegistros;
    }

    @Override
    public boolean existencia(String texto) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "SELECT id_producto FROM detalle_venta WHERE id_producto = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, texto);
            rs = ps.executeQuery();
            rs.last();
            if (rs.getRow() > 0) {
                resp = true;
            }
            ps.close();
            rs.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            ps = null;
            rs = null;
            con.desconectar();
        }
        return resp;
    }
}

