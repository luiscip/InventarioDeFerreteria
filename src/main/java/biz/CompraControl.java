package biz;

import data.CompraDAO;
import entities.Compra;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CompraControl {

    private final CompraDAO DATOS;
    private Compra obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public CompraControl() {
        this.DATOS = new CompraDAO();
        this.obj = new Compra();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Compra> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        String[] titulos = {"ID", "Proveedor", "Fecha de Compra", "Total", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String[] registro = new String[5];

        this.registrosMostrados = 0;

        for (Compra item : lista) {
            estado = item.isActivo() ? "Activo" : "Inactivo";

            registro[0] = Integer.toString(item.getIdCompra());
            registro[1] = Integer.toString(item.getIdProveedor());
            registro[2] = item.getFechaCompra();
            registro[3] = Double.toString(item.getTotalCompra());
            registro[4] = estado;

            this.modeloTabla.addRow(registro);
            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(int idProveedor, String fechaCompra, double total) {
        obj.setIdProveedor(idProveedor);
        obj.setFechaCompra(fechaCompra);
        obj.setTotalCompra(total);
        obj.setActivo(true);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int id, int idProveedor, String fechaCompra, double total, boolean activo) {
        obj.setIdCompra(id);
        obj.setIdProveedor(idProveedor);
        obj.setFechaCompra(fechaCompra);
        obj.setTotalCompra(total);
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
