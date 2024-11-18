package data.interfaces;

import entities.Producto;
import java.util.List;

public interface CrudGeneralInterface {
    public List<Producto> listar(String nombre) throws Exception;
    public void registrar(Producto produc) throws Exception;
    public void modificar(Producto produc) throws Exception;
    public void eliminar(int IdProducto) throws Exception;
    public Producto getProductoPorNombre(String nombre) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
}
