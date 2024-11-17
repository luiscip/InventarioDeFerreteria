package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import data.interfaces.CrudSimpleInterface;
import entities.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UbicacionDAO implements CrudSimpleInterface<Ubicacion> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public UbicacionDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Ubicacion> listar(String texto) {
        List<Ubicacion> registros = new ArrayList<>();

        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM Ubicacion WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Ubicacion(
                        rs.getInt("id_ubicacion"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("seccion"),
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
            CON.desconectar();
        }

        return registros;
    }

    @Override
    public boolean insertar(Ubicacion obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "INSERT INTO Ubicacion (nombre, descripcion, seccion, activo) VALUES (?, ?, ?, ?)"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getSeccion());
            ps.setBoolean(4, obj.isActivo());

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
    public boolean actualizar(Ubicacion obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement(
                    "UPDATE Ubicacion SET nombre = ?, descripcion = ?, seccion = ?, activo = ? WHERE id_ubicacion = ?"
            );
            ps.setString(1, obj.getNombre());
            ps.setString(2, obj.getDescripcion());
            ps.setString(3, obj.getSeccion());
            ps.setBoolean(4, obj.isActivo());
            ps.setInt(5, obj.getIdUbicacion());

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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_ubicacion) AS total FROM Ubicacion");
            rs = ps.executeQuery();

            if (rs.next()) {
                totalRegistros = rs.getInt("total");
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
            ps = CON.conectar().prepareStatement("SELECT nombre FROM Ubicacion WHERE nombre = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            ps = CON.conectar().prepareStatement("UPDATE Ubicacion SET activo = 0 WHERE id_ubicacion = ?");
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
            ps = CON.conectar().prepareStatement("UPDATE Ubicacion SET activo = 1 WHERE id_ubicacion = ?");
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
