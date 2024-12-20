
package data.interfaces;

import java.util.List;


public interface CrudPaginadoInterface <T> {
    
    public List<T>listar(String texto, int totalPorPagina, int numPagina);
    public boolean insertar (T obj);
    public boolean actualizar (T obj);
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    
}
