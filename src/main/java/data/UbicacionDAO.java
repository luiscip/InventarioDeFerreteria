package data;

import entities.Ubicacion;
import data.interfaces.CrudUbicacion;
import database.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UbicacionDAO implements CrudUbicacion {
    private final Connection conexion;

    public UbicacionDAO() {
        this.conexion = Conexion.getInstancia().conectar();
    }

    @Override
    public List<Ubicacion> listar(String nombre) throws SQLException {
        List<Ubicacion> lista = new ArrayList<>();
        String sql = nombre.isEmpty()
                ? "SELECT * FROM Ubicacion;"
                : "SELECT * FROM Ubicacion WHERE nombre LIKE ?;";
        
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            if (!nombre.isEmpty()) {
                st.setString(1, "%" + nombre + "%");
            }
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Ubicacion ubicacion = new Ubicacion(
                        rs.getInt("id_ubicacion"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("seccion"),
                        rs.getBoolean("activo")
                    );
                    lista.add(ubicacion);
                }
            }
        }
        return lista;
    }

    @Override
    public void registrar(Ubicacion ubicacion) throws SQLException {
        String sql = "INSERT INTO Ubicacion (nombre, descripcion, seccion, activo) VALUES (?, ?, ?, ?);";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, ubicacion.getNombre());
            st.setString(2, ubicacion.getDescripcion());
            st.setString(3, ubicacion.getSeccion());
            st.setBoolean(4, ubicacion.isActivo());
            st.executeUpdate();
        }
    }

    @Override
    public void modificar(Ubicacion ubicacion) throws SQLException {
        String sql = "UPDATE Ubicacion SET nombre = ?, descripcion = ?, seccion = ?, activo = ? WHERE id_ubicacion = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, ubicacion.getNombre());
            st.setString(2, ubicacion.getDescripcion());
            st.setString(3, ubicacion.getSeccion());
            st.setBoolean(4, ubicacion.isActivo());
            st.setInt(5, ubicacion.getIdUbicacion());
            st.executeUpdate();
        }
    }

    @Override
    public void eliminar(int id) throws SQLException {
        String sql = "DELETE FROM Ubicacion WHERE id_ubicacion = ?;";

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            st.executeUpdate();
        }
    }

    @Override
    public String getNombrePorID(int id) throws SQLException {
        String nombre = "Desconocido";
        String sql = "SELECT nombre FROM Ubicacion WHERE id_ubicacion = ?;";
        
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    nombre = rs.getString("nombre");
                }
            }
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
        String sql = "UPDATE Ubicacion SET activo = ? WHERE id_ubicacion = ?;";
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
        String sql = "SELECT COUNT(*) FROM Ubicacion;";
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
    public boolean existencia(String nombre) {
        String sql = "SELECT 1 FROM Ubicacion WHERE nombre = ?;";
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, nombre);
            try (ResultSet rs = st.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Ubicacion getCategoriaById(int id) throws SQLException {
        Ubicacion ubicacion = null;
        String sql = "SELECT * FROM Ubicacion WHERE id_ubicacion = ? LIMIT 1;";
        
        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    ubicacion = new Ubicacion(
                        rs.getInt("id_ubicacion"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getString("seccion"),
                        rs.getBoolean("activo")
                    );
                }
            }
        }
        return ubicacion;
    }


    public int obtenerORegistrarPorNombre(String nombre) throws SQLException {
    String sqlBuscar = "SELECT id_ubicacion FROM Ubicacion WHERE nombre = ?;";
    try (PreparedStatement ps = conexion.prepareStatement(sqlBuscar)) {
        ps.setString(1, nombre);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getInt("id_ubicacion"); // Devuelve el ID si la ubicación ya existe
            }
        }
    }

    // Si no existe, registrar la ubicación
    String sqlInsertar = "INSERT INTO Ubicacion (nombre, descripcion, seccion, activo) VALUES (?, '', 'General', 1);";
    try (PreparedStatement ps = conexion.prepareStatement(sqlInsertar, Statement.RETURN_GENERATED_KEYS)) {
        ps.setString(1, nombre);
        ps.executeUpdate();
        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getInt(1); // ID generado automáticamente
            }
        }
    }
    throw new SQLException("No se pudo registrar la ubicación.");
}

}
