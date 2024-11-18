package data.interfaces;

import entities.Categoria;
import java.util.List;

public interface CrudCategoriaInterface {
    public List<Categoria> listar(String nombre) throws Exception;
    public void registrar(Categoria categ) throws Exception;
    public void modificar(Categoria categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public Categoria getCategoriaById(int idProducto) throws Exception;
}
