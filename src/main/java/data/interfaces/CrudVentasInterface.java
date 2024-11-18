package data.interfaces;

import entities.Venta;
import java.util.List;

public interface CrudVentasInterface {
    public List<Venta> listar(String nombre) throws Exception;
    public void registrar(Venta categ) throws Exception;
    public void modificar(Venta categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Venta getCategoriaById(int idProducto) throws Exception;
}
