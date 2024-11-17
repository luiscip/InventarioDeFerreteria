package biz;

import data.DetalleCompraDAO;
import entities.DetalleCompra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class DetalleCompraControl {

    private final DetalleCompraDAO DATOS;
    private DetalleCompra obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public DetalleCompraControl() {
        this.DATOS = new DetalleCompraDAO();
        this.obj = new DetalleCompra();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<DetalleCompra> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        String titulos[] = {"ID", "ID Compra", "ID Producto", "Cantidad", "Precio Unitario", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[6];

        this.registrosMostrados = 0;

        for (DetalleCompra item : lista) {
            estado = item.isActivo() ? "Activo" : "Inactivo";

            registro[0] = Integer.toString(item.getIdDetalleCompra());
            registro[1] = Integer.toString(item.getIdCompra());
            registro[2] = Integer.toString(item.getIdProducto());
            registro[3] = Integer.toString(item.getCantidad());
            registro[4] = String.format("%.2f", item.getPrecioUnitario());
            registro[5] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(int idCompra, int idProducto, int cantidad, int precioUnitario, boolean activo) {
        obj.setIdCompra(idCompra);
        obj.setIdProducto(idProducto);
        obj.setCantidad(cantidad);
        obj.setPrecioUnitario(precioUnitario);
        obj.setActivoCompra(activo);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int idDetalleCompra, int idCompra, int idProducto, int cantidad, int precioUnitario, boolean activo) {
        obj.setIdDetalleCompra(idDetalleCompra);
        obj.setIdCompra(idCompra);
        obj.setIdProducto(idProducto);
        obj.setCantidad(cantidad);
        obj.setPrecioUnitario(precioUnitario);
        obj.setActivoCompra(activo);

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
