
package biz;

import data.VentaDAO;
import entities.Venta;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class VentaControl {

    private final VentaDAO DATOS;
    private Venta obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public VentaControl() {
        this.DATOS = new VentaDAO();
        this.obj = new Venta();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
        List<Venta> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));

        String titulos[] = {"ID", "Fecha de Venta", "Total", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[4];

        this.registrosMostrados = 0;

        for (Venta item : lista) {
            if (item.isActivoVenta()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdventa());
            registro[1] = item.getFechaventa();
            registro[2] = String.format("%.2f", item.getTotal());
            registro[3] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String fechaVenta, double total) {
        obj.setFechaventa(fechaVenta);
        obj.setTotal(total);

        if (DATOS.insertar(obj)) {
            return "OK";
        } else {
            return "Error en el registro";
        }
    }

    public String actualizar(int id, String fechaVenta, double total) {
        obj.setIdventa(id);
        obj.setFechaventa(fechaVenta);
        obj.setTotal(total);

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

