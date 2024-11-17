
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import data.interfaces.CrudPaginadoInterface;
import entities.Compra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;



public class CompraDAO implements CrudPaginadoInterface<Compra>{
    private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public CompraDAO() {
        con = Conexion.getInstancia();
    }

    @Override
    public List<Compra> listar(String texto, int totalPorPagina, int numPagina) {
        List<Compra> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                "SELECT * FROM compra WHERE id_proveedor LIKE ? LIMIT ?, ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, (numPagina - 1) * totalPorPagina);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Compra(
                    rs.getInt("id_compra"),
                    rs.getInt("id_proveedor"),
                    rs.getString("fecha_compra"),
                    rs.getDouble("total"),
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
    public boolean insertar(Compra obj) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "INSERT INTO compra (id_proveedor, fecha_compra, total, activo) VALUES (?, ?, ?, ?)");
            ps.setInt(1, obj.getIdProveedor());
            ps.setString(2, obj.getFechaCompra());
            ps.setDouble(3, obj.getTotalCompra());
            ps.setBoolean(4, obj.isActivo());
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
    public boolean actualizar(Compra obj) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE compra SET id_proveedor=?, fecha_compra=?, total=?, activo=? WHERE id_compra=?");
            ps.setInt(1, obj.getIdProveedor());
            ps.setString(2, obj.getFechaCompra());
            ps.setDouble(3, obj.getTotalCompra());
            ps.setBoolean(4, obj.isActivo());
            ps.setInt(5, obj.getIdCompra());
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
            ps = con.conectar().prepareStatement("UPDATE compra SET activo=1 WHERE id_compra=?");
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
            ps = con.conectar().prepareStatement("UPDATE compra SET activo=1 WHERE id_compra=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(id_compra) FROM compra");
            rs = ps.executeQuery();
            while (rs.next()) {
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
            ps = con.conectar().prepareStatement("SELECT id_compra FROM compra WHERE id_proveedor=?", 
                                                 ResultSet.TYPE_SCROLL_INSENSITIVE, 
                                                 ResultSet.CONCUR_READ_ONLY);
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
    

