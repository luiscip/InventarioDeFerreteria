package biz;

import data.UbicacionDAO;
import entities.Ubicacion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UbicacionControl {

    private final UbicacionDAO DATOS;
    private Ubicacion obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public UbicacionControl() {
        this.DATOS = new UbicacionDAO();
        this.obj = new Ubicacion();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Ubicacion> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String titulos[] = {"ID", "Nombre", "Descripción", "Sección", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[5];

        this.registrosMostrados = 0;

        for (Ubicacion item : lista) {
            estado = item.isActivo() ? "Activo" : "Inactivo";

            registro[0] = Integer.toString(item.getIdUbicacion());
            registro[1] = item.getNombre();
            registro[2] = item.getDescripcion();
            registro[3] = item.getSeccion();
            registro[4] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String descripcion, String seccion) {
        if (DATOS.existencia(nombre)) {
            return "El registro ya existe";
        } else {
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            obj.setSeccion(seccion);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error en el registro";
            }
        }
    }

    public String actualizar(int id, String nombre, String nombreAnt, String descripcion, String seccion) {

        if (nombre.equals(nombreAnt)) {
            obj.setIdUbicacion(id);
            obj.setNombre(nombre);
            obj.setDescripcion(descripcion);
            obj.setSeccion(seccion);

            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualización";
            }
        } else {
            if (DATOS.existencia(nombre)) {
                return "El registro ya existe";
            } else {
                obj.setIdUbicacion(id);
                obj.setNombre(nombre);
                obj.setDescripcion(descripcion);
                obj.setSeccion(seccion);

                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualización";
                }
            }
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
