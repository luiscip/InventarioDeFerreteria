package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import database.Conexion;
import data.interfaces.CrudSimpleInterface;
import entities.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ProveedorDAO implements CrudSimpleInterface<Proveedor> {
    private final Conexion CON;
    private PreparedStatement ps;
    private ResultSet rs;
    private boolean resp;

    public ProveedorDAO() {
        CON = Conexion.getInstancia();
    }

    @Override
    public List<Proveedor> listar(String texto) {
        List<Proveedor> registros = new ArrayList<>();

        try {
            ps = CON.conectar().prepareStatement("SELECT * FROM Proveedor WHERE nombre LIKE ?");
            ps.setString(1, "%" + texto + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                registros.add(new Proveedor(
                        rs.getInt("id_proveedores"),
                        rs.getString("nombre"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
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
    public boolean insertar(Proveedor obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("INSERT INTO Proveedor (nombre, telefono, email, direccion, activo) VALUES (?, ?, ?, ?, 1)");
            ps.setString(1, obj.getNombreProveedor());
            ps.setString(2, obj.getTelefonoProveedor());
            ps.setString(3, obj.getEmailProveedor());
            ps.setString(4, obj.getDireccionProveedor());

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
    public boolean actualizar(Proveedor obj) {
        resp = false;
        try {
            ps = CON.conectar().prepareStatement("UPDATE Proveedor SET nombre = ?, telefono = ?, email = ?, direccion = ? WHERE id_proveedores = ?");
            ps.setString(1, obj.getNombreProveedor());
            ps.setString(2, obj.getTelefonoProveedor());
            ps.setString(3, obj.getEmailProveedor());
            ps.setString(4, obj.getDireccionProveedor());
            ps.setInt(5, obj.getIdProveedor());

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
            ps = CON.conectar().prepareStatement("SELECT COUNT(id_proveedores) AS total FROM Proveedor");
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
            ps = CON.conectar().prepareStatement("SELECT nombre FROM Proveedor WHERE nombre = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
            ps = CON.conectar().prepareStatement("UPDATE Proveedor SET activo = 0 WHERE id_proveedores = ?");
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
            ps = CON.conectar().prepareStatement("UPDATE Proveedor SET activo = 1 WHERE id_proveedores = ?");
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
