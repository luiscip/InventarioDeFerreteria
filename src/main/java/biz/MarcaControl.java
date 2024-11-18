//package biz;
//
//import data.MarcaDAO;
//import entities.Marca;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.table.DefaultTableModel;
//
//public class MarcaControl {
//
//    private final MarcaDAO DATOS;
//    private Marca obj;
//    private DefaultTableModel modeloTabla;
//    public int registrosMostrados;
//
//    public MarcaControl() {
//        this.DATOS = new MarcaDAO();
//        this.obj = new Marca();
//        this.registrosMostrados = 0;
//    }
//
//    public DefaultTableModel listar(String texto) {
//        List<Marca> lista = new ArrayList<>();
//        lista.addAll(DATOS.listar(texto));
//
//        String titulos[] = {"ID", "Nombre", "Descripcion", "Estado"};
//        this.modeloTabla = new DefaultTableModel(null, titulos);
//
//        String estado;
//        String registro[] = new String[4];
//
//        this.registrosMostrados = 0;
//
//        for (Marca item : lista) {
//            if (item.isActivo()) {
//                estado = "Activo";
//            } else {
//                estado = "Inactivo";
//            }
//
//            registro[0] = Integer.toString(item.getIdMarca());
//            registro[1] = item.getNombre();
//            registro[2] = item.getDescripcion();
//            registro[3] = estado;
//
//            this.modeloTabla.addRow(registro);
//
//            this.registrosMostrados++;
//        }
//
//        return this.modeloTabla;
//    }
//
//    public String insertar(String nombre, String descripcion) {
//        if (DATOS.existencia(nombre)) {
//            return "El registro ya existe";
//        } else {
//            obj.setNombre(nombre);
//            obj.setDescripcion(descripcion);
//
//            if (DATOS.insertar(obj)) {
//                return "OK";
//            } else {
//                return "Error en el registro";
//            }
//        }
//    }
//
//    public String actualizar(int id, String nombre, String nombreAnt, String descripcion) {
//
//        if (nombre.equals(nombreAnt)) {
//
//            obj.setIdMarca(id);
//            obj.setNombre(nombre);
//            obj.setDescripcion(descripcion);
//
//            if (DATOS.actualizar(obj)) {
//                return "OK";
//            } else {
//                return "Error en la actualización";
//            }
//        } else {
//            if (DATOS.existencia(nombre)) {
//                return "El registro ya existe";
//            } else {
//                obj.setIdMarca(id);
//                obj.setNombre(nombre);
//                obj.setDescripcion(descripcion);
//
//                if (DATOS.actualizar(obj)) {
//                    return "OK";
//                } else {
//                    return "Error en la actualización";
//                }
//            }
//        }
//    }
//
//    public String desactivar(int id) {
//        if (DATOS.desactivar(id)) {
//            return "OK";
//        } else {
//            return "No se puede desactivar el registro";
//        }
//    }
//
//    public String activar(int id) {
//        if (DATOS.activar(id)) {
//            return "OK";
//        } else {
//            return "No se puede activar el registro";
//        }
//    }
//
//    public int total() {
//        return DATOS.total();
//    }
//
//    public int totalMostrados() {
//        return this.registrosMostrados;
//    }
//
//}
