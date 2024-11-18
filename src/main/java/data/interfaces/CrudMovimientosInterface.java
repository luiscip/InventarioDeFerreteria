package data.interfaces;

import entities.MovimientosStock;
import java.util.List;

public interface CrudMovimientosInterface {
    public List<MovimientosStock> listar(String nombre) throws Exception;
    public void registrar(MovimientosStock categ) throws Exception;
    public void modificar(MovimientosStock categ) throws Exception;
    public void eliminar(int id) throws Exception;
    public String getNombrePorID(int id) throws Exception;
    public boolean desactivar (int id);
    public boolean activar (int id);
    public int total();
    public boolean existencia(String texto);
    public MovimientosStock getCategoriaById(int idProducto) throws Exception;
}
