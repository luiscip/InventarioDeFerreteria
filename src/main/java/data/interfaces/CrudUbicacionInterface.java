package data.interfaces;

import entities.Ubicacion;
import java.util.List;

public interface CrudUbicacionInterface {
    public List<Ubicacion> listar(String nombre) throws Exception;
    public void registrar(Ubicacion categ) throws Exception;
    public void modificar(Ubicacion categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Ubicacion getCategoriaById(int idProducto) throws Exception;
}
