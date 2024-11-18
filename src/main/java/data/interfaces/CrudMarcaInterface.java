package data.interfaces;

import entities.Marca;
import java.util.List;

public interface CrudMarcaInterface {
    public List<Marca> listar(String nombre) throws Exception;
    public void registrar(Marca marc) throws Exception;
    public void modificar(Marca marc) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Marca getMarcaById(int idProducto) throws Exception;
}
