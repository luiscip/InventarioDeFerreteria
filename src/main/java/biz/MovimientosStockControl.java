
package biz;

import data.MovimientosStockDAO;
import entities.MovimientosStock;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class MovimientosStockControl {

    private final MovimientosStockDAO DATOS;
    private MovimientosStock obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public MovimientosStockControl() {
        this.DATOS = new MovimientosStockDAO();
        this.obj = new MovimientosStock();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<MovimientosStock> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String titulos[] = {"ID", "Producto", "Fecha", "Cantidad", "Tipo", "Comentario", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[7];

        this.registrosMostrados = 0;

        for (MovimientosStock item : lista) {
            estado = item.isActivo() ? "Activo" : "Inactivo";

            registro[0] = Integer.toString(item.getIdMovStock());
            registro[1] = Integer.toString(item.getIdProducto());
            registro[2] = item.getFechaMov();
            registro[3] = Integer.toString(item.getCantidad());
            registro[4] = item.getTipoMov();
            registro[5] = item.getComentario();
            registro[6] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(int idProducto, String fechaMov, int cantidad, String tipoMov, String comentario) {
        obj.setIdProducto(idProducto);
        obj.setFechaMov(fechaMov);
        obj.setCantidad(cantidad);
        obj.setTipoMov(tipoMov);
        obj.setComentario(comentario);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int id, int idProducto, String fechaMov, int cantidad, String tipoMov, String comentario, boolean activo) {
        obj.setIdMovStock(id);
        obj.setIdProducto(idProducto);
        obj.setFechaMov(fechaMov);
        obj.setCantidad(cantidad);
        obj.setTipoMov(tipoMov);
        obj.setComentario(comentario);
        obj.setActivo(activo);

        if (DATOS.actualizar(obj)) {
            return "OK";
        } else {
            return "Error en la actualización";
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el registro";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el registro";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}

