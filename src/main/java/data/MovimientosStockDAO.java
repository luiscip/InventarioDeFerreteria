
package data;

import database.Conexion;
import data.interfaces.CrudSimpleInterface;
import entities.MovimientosStock;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MovimientosStockDAO implements CrudSimpleInterface<MovimientosStock>{
    private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public MovimientosStockDAO() {
        con = Conexion.getInstancia();
    }  

    @Override
    public List<MovimientosStock> listar(String Texto) {
        List<MovimientosStock> registros = new ArrayList<>();
        try {
            ps = con.conectar().prepareStatement(
                "SELECT * FROM movimientos_stock WHERE comentario LIKE ?");
            ps.setString(1, "%" + Texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new MovimientosStock(
                    rs.getInt("id_mov_stock"),
                    rs.getInt("id_productos"),
                    rs.getString("fecha_mov"),
                    rs.getInt("cantidad"),
                    rs.getString("tipo_mov"),
                    rs.getString("comentario"),
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
    public boolean insertar(MovimientosStock object) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "INSERT INTO movimientos_stock (id_productos, fecha_mov, cantidad, tipo_mov, comentario, activo) VALUES (?, ?, ?, ?, ?, 1)");
            ps.setInt(1, object.getIdProducto());
            ps.setString(2, object.getFechaMov());
            ps.setInt(3, object.getCantidad());
            ps.setString(4, object.getTipoMov());
            ps.setString(5, object.getComentario());

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
    public boolean actualizar(MovimientosStock object) {
        resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "UPDATE movimientos_stock SET id_productos=?, fecha_mov=?, cantidad=?, tipo_mov=?, comentario=?, activo=? WHERE id_mov_stock=?");
            ps.setInt(1, object.getIdProducto());
            ps.setString(2, object.getFechaMov());
            ps.setInt(3, object.getCantidad());
            ps.setString(4, object.getTipoMov());
            ps.setString(5, object.getComentario());
            ps.setBoolean(6, object.isActivo());
            ps.setInt(7, object.getIdMovStock());

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
                "UPDATE movimientos_stock SET activo=0 WHERE id_mov_stock=?");
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
                "UPDATE movimientos_stock SET activo=1 WHERE id_mov_stock=?");
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
                "SELECT COUNT(id_mov_stock) FROM movimientos_stock");
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
    public boolean existencia(String existe) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement(
                "SELECT id_mov_stock FROM movimientos_stock WHERE comentario=?", 
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, existe);
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

