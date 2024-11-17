
package biz;

import data.DetalleVentaDAO;
import entities.DetalleVenta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DetalleVentaControl {

    private final DetalleVentaDAO DATOS;
    private DetalleVenta obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public DetalleVentaControl() {
        this.DATOS = new DetalleVentaDAO();
        this.obj = new DetalleVenta();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<DetalleVenta> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        String titulos[] = {"ID", "ID Venta", "ID Producto", "Cantidad", "Precio Unitario", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[6];

        this.registrosMostrados = 0;

        for (DetalleVenta item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdDetalleVenta());
            registro[1] = Integer.toString(item.getIdVenta());
            registro[2] = Integer.toString(item.getIdProducto());
            registro[3] = Integer.toString(item.getCantidad());
            registro[4] = Double.toString(item.getPrecioVentaUni());
            registro[5] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(int idVenta, int idProducto, int cantidad, double precioVentaUni) {
        obj.setIdVenta(idVenta);
        obj.setIdProducto(idProducto);
        obj.setCantidad(cantidad);
        obj.setPrecioVentaUni(precioVentaUni);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int id, int idVenta, int idProducto, int cantidad, double precioVentaUni) {
        obj.setIdDetalleVenta(id);
        obj.setIdVenta(idVenta);
        obj.setIdProducto(idProducto);
        obj.setCantidad(cantidad);
        obj.setPrecioVentaUni(precioVentaUni);

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
