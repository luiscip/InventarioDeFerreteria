package data.interfaces;

import entities.Compra;
import java.util.List;

public interface CrudComprasInterface {
    public List<Compra> listar(String nombre) throws Exception;
    public void registrar(Compra categ) throws Exception;
    public void modificar(Compra categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Compra getCategoriaById(int idProducto) throws Exception;
}
