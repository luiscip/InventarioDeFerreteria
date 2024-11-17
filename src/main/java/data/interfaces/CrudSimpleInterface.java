
package data.interfaces;

import java.util.List;


public interface CrudSimpleInterface <T>{
    
    public List<T> listar(String Texto);
    public boolean insertar(T object);
    public boolean actualizar(T object);
    public boolean desactivar(int id);
    public boolean activar(int id);
    public int total();
    public boolean existencia(String existe);
}
