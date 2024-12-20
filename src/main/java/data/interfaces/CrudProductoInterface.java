package data.interfaces;

import entities.Producto;
import java.util.List;

public interface CrudProductoInterface {
    public List<Producto> listar(String nombre) throws Exception;
    public void registrar(Producto produc) throws Exception;
    public void modificar(Producto produc) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Producto getProductoById(int idProducto) throws Exception;
}
