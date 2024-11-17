
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import data.interfaces.CrudSimpleInterface;
import entities.Categoria;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CategoriaDAO implements CrudSimpleInterface<Categoria> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public CategoriaDAO(){
        CON = Conexion.getInstancia();
    }
    
    @Override
    public List<Categoria> listar(String Texto) {

        List<Categoria> registros = new ArrayList();

        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM Categoria WHERE nombre LIKE ?");
            ps.setString(1, "%" + Texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Categoria(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getBoolean(4)));
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

        return registros;
    }

    @Override
    public boolean insertar(Categoria obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO Categoria (nombre, descripcion, activo) VALUES (?,?,1)");
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            if(ps.executeUpdate()>0){
                resp = true;
            }
            
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }finally{
            ps = null;
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean actualizar(Categoria object) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE Categoria SET nombre=?, descripcion=? WHERE id_categoria = ?");
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getDescripcion());
            ps.setInt(3, object.getIdCategoria());
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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id) FROM Categoria");
            rs = ps.executeQuery();

            while (rs.next()) {
                totalRegistros = rs.getInt("COUNT(id)");
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
            ps = CON.conectar().prepareStatement("SELECT nombre FROM Categoria WHERE nombre = ?", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
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
            CON.desconectar();
        }
        return resp;
    }

    @Override
    public boolean desactivar(int id) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE Categoria SET activo = 0 WHERE id_categoria = ?");
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
            ps = CON.conectar().prepareStatement("UPDATE Categoria SET activo = 1 WHERE id_categoria = ?");
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
}
