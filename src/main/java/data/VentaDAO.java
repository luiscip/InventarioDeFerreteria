
package data;

import database.Conexion;
import data.interfaces.CrudPaginadoInterface;
import entities.Venta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class VentaDAO implements CrudPaginadoInterface<Venta>{
    private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public VentaDAO(){
        con = Conexion.getInstancia();
}

    
    @Override
    public List<Venta> listar(String texto, int totalPorPagina, int numPagina) {
    List<Venta> registros = new ArrayList<>();
        try {
            int offset = (numPagina - 1) * totalPorPagina;
            ps = con.conectar().prepareStatement("SELECT * FROM Venta WHERE fecha_venta LIKE ? LIMIT ?, ?");
            ps.setString(1, "%" + texto + "%");
            ps.setInt(2, offset);
            ps.setInt(3, totalPorPagina);
            rs = ps.executeQuery();
            while (rs.next()) {
                registros.add(new Venta(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getBoolean(4)));
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
    public boolean insertar(Venta obj) {
         resp = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO Venta (fecha_venta, total, activo) VALUES (?, ?, 1)");
            ps.setString(1, obj.getFechaventa());
            ps.setDouble(2, obj.getTotal());
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
    public boolean actualizar(Venta obj) {
       resp = false;
        try {
            ps = con.conectar().prepareStatement("UPDATE Venta SET fecha_venta=?, total=? WHERE id_venta=?");
            ps.setString(1, obj.getFechaventa());
            ps.setDouble(2, obj.getTotal());
            ps.setInt(3, obj.getIdventa());
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
            ps = con.conectar().prepareStatement("UPDATE Venta SET activo=0 WHERE id_venta=?");
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
            ps = con.conectar().prepareStatement("UPDATE Venta SET activo=1 WHERE id_venta=?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(id_venta) FROM Venta");
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
            ps = con.conectar().prepareStatement("SELECT fecha_venta FROM Venta WHERE fecha_venta=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
    
    

