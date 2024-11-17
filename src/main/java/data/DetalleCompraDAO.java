
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import data.interfaces.CrudPaginadoInterface;
import entities.DetalleCompra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class DetalleCompraDAO implements CrudPaginadoInterface<DetalleCompra>{
    private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public DetalleCompraDAO() {
        con = Conexion.getInstancia();
    }

    @Override
    public List<DetalleCompra> listar(String texto, int totalPorPagina, int numPagina) {
        List<DetalleCompra> registros = new ArrayList<>();
        int offset = (numPagina - 1) * totalPorPagina;

        try {
            ps = con.conectar().prepareStatement(
                "SELECT * FROM Detalle_compra WHERE id_compra LIKE ? ORDER BY id_detalle_compra ASC LIMIT ? OFFSET ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, totalPorPagina);
            ps.setInt(3, offset);
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new DetalleCompra(
                    rs.getInt("id_detalle_compra"),
                    rs.getInt("id_compra"),
                    rs.getInt("id_producto"),
                    rs.getInt("cantidad"),
                    rs.getInt("precio_unitario"),
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
    public boolean insertar(DetalleCompra obj) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "INSERT INTO Detalle_compra (id_compra, id_producto, cantidad, precio_unitario, activo) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, obj.getIdCompra());
            ps.setInt(2, obj.getIdProducto());
            ps.setInt(3, obj.getCantidad());
            ps.setInt(4, obj.getPrecioUnitario());
            ps.setBoolean(5, obj.isActivo());

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
    public boolean actualizar(DetalleCompra obj) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE Detalle_compra SET id_compra=?, id_producto=?, cantidad=?, precio_unitario=?, activo=? WHERE id_detalle_compra=?");
            ps.setInt(1, obj.getIdCompra());
            ps.setInt(2, obj.getIdProducto());
            ps.setInt(3, obj.getCantidad());
            ps.setInt(4, obj.getPrecioUnitario());
            ps.setBoolean(5, obj.isActivo());
            ps.setInt(6, obj.getIdDetalleCompra());

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
            ps = con.conectar().prepareStatement("UPDATE Detalle_compra SET activo=0 WHERE id_detalle_compra=?");
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
            ps = con.conectar().prepareStatement("UPDATE Detalle_compra SET activo=1 WHERE id_detalle_compra=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(id_detalle_compra) FROM Detalle_compra");
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
            con.desconectar();
        }

        return totalRegistros;
    }

    @Override
    public boolean existencia(String texto) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "SELECT id_detalle_compra FROM Detalle_compra WHERE id_compra=?");
            ps.setString(1, texto);
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
            con.desconectar();
        }

        return resp;
    }
}




