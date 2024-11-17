
package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import data.interfaces.CrudSimpleInterface;
import database.Conexion;
import entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.sql.SQLException;


public class UsuarioDAO implements CrudSimpleInterface<Usuario>{
    
    private final Conexion con;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;
    
    public UsuarioDAO(){
        con = Conexion.getInstancia();
        
    }

    @Override
    public List<Usuario> listar(String Texto) {  
        List<Usuario> registros = new ArrayList<>();

        try {
            ps = con.conectar().prepareStatement("SELECT * FROM usuario WHERE nombre LIKE ? OR email LIKE ?");
            ps.setString(1, "%" + Texto + "%");
            ps.setString(2, "%" + Texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Usuario(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getBoolean(7)
                        
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
    public boolean insertar(Usuario object) {
      resp = false;
        try {
            ps = con.conectar().prepareStatement("INSERT INTO usuario (nombre, email, user, contraseña, fecha_creacion) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getUser());
            ps.setString(4, object.getContraseña());
            ps.setString(5, object.getFechacreacion());

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
    public boolean actualizar(Usuario object) {
        try {
            ps = con.conectar().prepareStatement("UPDATE usuario SET nombre = ?, email = ?, user = ?, contraseña = ?, fecha_creacion = ? WHERE id_usuario = ?");
            ps.setString(1, object.getNombre());
            ps.setString(2, object.getEmail());
            ps.setString(3, object.getUser());
            ps.setString(4, object.getContraseña());
            ps.setString(5, object.getFechacreacion());
            ps.setInt(6, object.getId());

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
            ps = con.conectar().prepareStatement("UPDATE usuario SET activo = 0 WHERE id_usuario = ?");
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
            ps = con.conectar().prepareStatement("UPDATE usuario SET activo = 1 WHERE id_usuario = ?");
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
            ps = con.conectar().prepareStatement("SELECT COUNT(id_usuario) FROM usuario");
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
    public boolean existencia(String existe) {
      resp = false;
        try {
            ps = con.conectar().prepareStatement("SELECT COUNT(*) FROM usuario WHERE user = ?");
            ps.setString(1, existe);
            rs = ps.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
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
    
    

