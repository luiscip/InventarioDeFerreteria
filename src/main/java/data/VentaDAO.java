package data;

import entities.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.Conexion;

public class VentaDAO {

    private Connection conexion;

    public VentaDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    public List<Venta> listar() throws SQLException {
        List<Venta> lista = new ArrayList<>();
        String sql = "SELECT * FROM Venta;";

        try (PreparedStatement st = conexion.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Venta venta = new Venta(
                        rs.getInt("id_venta"),
                        rs.getString("fecha_venta"),
                        rs.getDouble("total"),
                        rs.getBoolean("activo")
                );
                lista.add(venta);
            }
        }
        return lista;
    }

    public void registrar(Venta venta) throws SQLException {
        String sql = "INSERT INTO Venta (fecha_venta, total, activo) VALUES (?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, venta.getFechaventa());
            st.setDouble(2, venta.getTotal());
            st.setBoolean(3, venta.isActivoVenta());
            st.executeUpdate();
        }
    }

    public void modificar(Venta venta) throws SQLException {
        String sql = "UPDATE Venta SET fecha_venta = ?, total = ?, activo = ? WHERE id_venta = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, venta.getFechaventa());
            st.setDouble(2, venta.getTotal());
            st.setBoolean(3, venta.isActivoVenta());
            st.setInt(4, venta.getIdventa());
            st.executeUpdate();
        }
    }

    public void eliminar(int idVenta) throws SQLException {
        String sql = "DELETE FROM Venta WHERE id_venta = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idVenta);
            st.executeUpdate();
        }
    }

    public boolean cambiarEstado(int idVenta, boolean activo) {
        String sql = "UPDATE Venta SET activo = ? WHERE id_venta = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setBoolean(1, activo);
            st.setInt(2, idVenta);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int total() {
        String sql = "SELECT COUNT(*) FROM Venta;";
        try (PreparedStatement st = conexion.prepareStatement(sql);
             ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Venta getVentaById(int idVenta) throws Exception {
        Venta venta = null;
        String sql = "SELECT * FROM Venta WHERE id_venta = ? LIMIT 1;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idVenta);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    venta = new Venta(
                            rs.getInt("id_venta"),
                            rs.getString("fecha_venta"),
                            rs.getDouble("total"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return venta;
    }
}
