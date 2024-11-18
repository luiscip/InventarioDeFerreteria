//package biz;
//
//import data.ProductoDAO;
//import entities.Producto;
//import java.util.ArrayList;
//import java.util.List;
//import javax.swing.table.DefaultTableModel;
//
//public class ProductoControl {
//
//    private final ProductoDAO DATOS;
//    private Producto obj;
//    private DefaultTableModel modeloTabla;
//    public int registrosMostrados;
//
//    public ProductoControl() {
//        this.DATOS = new ProductoDAO();
//        this.obj = null;
//        this.registrosMostrados = 0;
//    }
//
//    // Método para listar productos con paginación
//    public DefaultTableModel listar(String texto, int totalPorPagina, int numPagina) {
//        List<Producto> lista = new ArrayList<>();
//        lista.addAll(DATOS.listar(texto, totalPorPagina, numPagina));
//
//        String[] titulos = {"ID", "Categoría", "Marca", "Ubicación", "Nombre", "Descripción", "Precio Compra", "Precio Venta", "Stock", "Stock Mínimo", "Estado"};
//        this.modeloTabla = new DefaultTableModel(null, titulos);
//
//        String estado;
//        String[] registro = new String[11];
//        this.registrosMostrados = 0;
//
//        for (Producto item : lista) {
//            if (item.isActivo()) {
//                estado = "Activo";
//            } else {
//                estado = "Inactivo";
//            }
//
//            registro[0] = Integer.toString(item.getIdProducto());
//            registro[1] = Integer.toString(item.getIdCategoria());
//            registro[2] = Integer.toString(item.getIdMarca());
//            registro[3] = Integer.toString(item.getIdUbicacion());
//            registro[4] = item.getNombre();
//            registro[5] = item.getDescripcion();
//            registro[6] = Double.toString(item.getPrecioCompra());
//            registro[7] = Double.toString(item.getPrecioVenta());
//            registro[8] = Integer.toString(item.getStock());
//            registro[9] = Integer.toString(item.getStockMinimo());
//            registro[10] = estado;
//
//            this.modeloTabla.addRow(registro);
//            this.registrosMostrados++;
//        }
//
//        return this.modeloTabla;
//    }
//
//    public String insertar(int idCategoria, int idMarca, int idUbicacion, String nombre, String descripcion, double precioCompra, double precioVenta, int stock, int stockMinimo, String fecha) {
//        if (DATOS.existencia(nombre)) {
//            return "El producto ya existe";
//        } else {
//            obj = new Producto(0, idCategoria, idMarca, idUbicacion, nombre, descripcion, precioCompra, precioVenta, stock, stockMinimo, fecha, true);
//            if (DATOS.insertar(obj)) {
//                return "OK";
//            } else {
//                return "Error en el registro del producto";
//            }
//        }
//    }
//
//    public String actualizar(int id, int idCategoria, int idMarca, int idUbicacion, String nombre, String nombreAnt, String descripcion, double precioCompra, double precioVenta, int stock, int stockMinimo, String fecha) {
//        if (nombre.equals(nombreAnt) || !DATOS.existencia(nombre)) {
//            obj = new Producto(id, idCategoria, idMarca, idUbicacion, nombre, descripcion, precioCompra, precioVenta, stock, stockMinimo, fecha, true);
//            if (DATOS.actualizar(obj)) {
//                return "OK";
//            } else {
//                return "Error en la actualización del producto";
//            }
//        } else {
//            return "El producto ya existe con ese nombre";
//        }
//    }
//
//    public String desactivar(int id) {
//        if (DATOS.desactivar(id)) {
//            return "OK";
//        } else {
//            return "No se puede desactivar el producto";
//        }
//    }
//
//    public String activar(int id) {
//        if (DATOS.activar(id)) {
//            return "OK";
//        } else {
//            return "No se puede activar el producto";
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
//}
