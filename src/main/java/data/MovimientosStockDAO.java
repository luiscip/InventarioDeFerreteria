package data;

import entities.MovimientosStock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.CrudMovimientosInterface;
import database.Conexion;

public class MovimientosStockDAO implements CrudMovimientosInterface {

    private Connection conexion;

    public MovimientosStockDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<MovimientosStock> listar(String filtro) throws SQLException {
        List<MovimientosStock> lista = new ArrayList<>();
        String sql = filtro.isEmpty()
                ? "SELECT * FROM movimientos_stock;"
                : "SELECT * FROM movimientos_stock WHERE comentario LIKE ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!filtro.isEmpty()) {
                st.setString(1, "%" + filtro + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    MovimientosStock mov = new MovimientosStock(
                            rs.getInt("id_mov_stock"),
                            rs.getInt("id_productos"),
                            rs.getString("fecha_mov"),
                            rs.getInt("cantidad"),
                            rs.getString("tipo_mov"),
                            rs.getString("comentario"),
                            rs.getBoolean("activo")
                    );
                    lista.add(mov);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(MovimientosStock mov) throws SQLException {
        String sql = "INSERT INTO movimientos_stock (id_productos, fecha_mov, cantidad, tipo_mov, comentario, activo) VALUES (?, ?, ?, ?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, mov.getIdProducto());
            st.setString(2, mov.getFechaMov());
            st.setInt(3, mov.getCantidad());
            st.setString(4, mov.getTipoMov());
            st.setString(5, mov.getComentario());
            st.setBoolean(6, mov.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(MovimientosStock mov) throws SQLException {
        String sql = "UPDATE movimientos_stock SET id_productos = ?, fecha_mov = ?, cantidad = ?, tipo_mov = ?, comentario = ?, activo = ? WHERE id_mov_stock = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, mov.getIdProducto());
            st.setString(2, mov.getFechaMov());
            st.setInt(3, mov.getCantidad());
            st.setString(4, mov.getTipoMov());
            st.setString(5, mov.getComentario());
            st.setBoolean(6, mov.isActivo());
            st.setInt(7, mov.getIdMovStock());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int idMovStock) throws SQLException {
        String sql = "DELETE FROM movimientos_stock WHERE id_mov_stock = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idMovStock);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) {
        // Este método puede no ser relevante para MovimientosStock, implementado solo como referencia
        String nombre = "Desconocido";
        String sql = "SELECT comentario FROM movimientos_stock WHERE id_mov_stock = ?;";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("comentario");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nombre;
    }

    @Override
    public boolean desactivar(int id) {
        return cambiarEstado(id, false);
    }

    @Override
    public boolean activar(int id) {
        return cambiarEstado(id, true);
    }

    private boolean cambiarEstado(int id, boolean activo) {
        String sql = "UPDATE movimientos_stock SET activo = ? WHERE id_mov_stock = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setBoolean(1, activo);
            st.setInt(2, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int total() {
        String sql = "SELECT COUNT(*) FROM movimientos_stock;";
        try (PreparedStatement st = conexion.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean existencia(String texto) {
        String sql = "SELECT 1 FROM movimientos_stock WHERE comentario = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, texto);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public MovimientosStock getCategoriaById(int idMovStock) throws Exception {
        MovimientosStock mov = null;
        String sql = "SELECT * FROM movimientos_stock WHERE id_mov_stock = ? LIMIT 1;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, idMovStock);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    mov = new MovimientosStock(
                            rs.getInt("id_mov_stock"),
                            rs.getInt("id_productos"),
                            rs.getString("fecha_mov"),
                            rs.getInt("cantidad"),
                            rs.getString("tipo_mov"),
                            rs.getString("comentario"),
                            rs.getBoolean("activo")
                    );
                }
            }
        }
        return mov;
    }
}
