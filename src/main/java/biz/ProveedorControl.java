package biz;

import data.ProveedorDAO;
import entities.Proveedor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ProveedorControl {

    private final ProveedorDAO DATOS;
    private Proveedor obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public ProveedorControl() {
        this.DATOS = new ProveedorDAO();
        this.obj = new Proveedor();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Proveedor> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String titulos[] = {"ID", "Nombre", "Teléfono", "Email", "Dirección", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[6];

        this.registrosMostrados = 0;

        for (Proveedor item : lista) {
            if (item.isActivo()) {
                estado = "Activo";
            } else {
                estado = "Inactivo";
            }

            registro[0] = Integer.toString(item.getIdProveedor());
            registro[1] = item.getNombreProveedor();
            registro[2] = item.getTelefonoProveedor();
            registro[3] = item.getEmailProveedor();
            registro[4] = item.getDireccionProveedor();
            registro[5] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String telefono, String email, String direccion) {
        if (DATOS.existencia(nombre)) {
            return "El proveedor ya existe";
        } else {
            obj.setNombreProveedor(nombre);
            obj.setTelefonoProveedor(telefono);
            obj.setEmailProveedor(email);
            obj.setDireccionProveedor(direccion);
            obj.setActivo(true);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error al registrar el proveedor";
            }
        }
    }

    public String actualizar(int id, String nombre, String nombreAnt, String telefono, String email, String direccion) {
        if (nombre.equals(nombreAnt)) {
            obj.setIdProveedor(id);
            obj.setNombreProveedor(nombre);
            obj.setTelefonoProveedor(telefono);
            obj.setEmailProveedor(email);
            obj.setDireccionProveedor(direccion);

            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error al actualizar el proveedor";
            }
        } else {
            if (DATOS.existencia(nombre)) {
                return "El proveedor ya existe";
            } else {
                obj.setIdProveedor(id);
                obj.setNombreProveedor(nombre);
                obj.setTelefonoProveedor(telefono);
                obj.setEmailProveedor(email);
                obj.setDireccionProveedor(direccion);

                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error al actualizar el proveedor";
                }
            }
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se pudo desactivar el proveedor";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se pudo activar el proveedor";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}
