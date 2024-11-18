package data.interfaces;

import entities.Proveedor;
import java.util.List;

public interface CrudProveedorInterface {
    public List<Proveedor> listar(String nombre) throws Exception;
    public void registrar(Proveedor categ) throws Exception;
    public void modificar(Proveedor categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Proveedor getProveedorById(int idProducto) throws Exception;
}
