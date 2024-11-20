package entities;

import data.CategoriaDAO;
import data.MarcaDAO;
import data.UbicacionDAO;
import java.sql.SQLException;

public class Producto {

    private int idProducto;
    private int idCategoria;
    private int idMarca;
    private int idUbicacion;
    private String nombre;
    private String descripcion;
    private double precioCompra;
    private double precioVenta;
    private int stock;
    private int stockMinimo;
    private String fechaUltimaActualizacion;
    private boolean activo;

    public Producto(int idProducto, int idCategoria, int idMarca, int idUbicacion, String nombre, String descripcion, double precioCompra, double precioVenta, int stock, int stockMinimo, String fechaUltimaActualizacion, boolean activo) {
        this.idProducto = idProducto;
        this.idCategoria = idCategoria;
        this.idMarca = idMarca;
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
        this.stockMinimo = stockMinimo;
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
        this.activo = activo;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdUbicacion() {
        return idUbicacion;
    }

    public void setIdUbicacion(int idUbicacion) {
        this.idUbicacion = idUbicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public String getFechaUltimaActualizacion() {
        return fechaUltimaActualizacion;
    }

    public void setFechaUltimaActualizacion(String fechaUltimaActualizacion) {
        this.fechaUltimaActualizacion = fechaUltimaActualizacion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCategoriaNombre() {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        try {
            return categoriaDAO.getNombrePorID(this.idCategoria);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    public String getUbicacionNombre() {
        UbicacionDAO ubicacionDAO = new UbicacionDAO();
        try {
            return ubicacionDAO.getNombrePorID(this.idUbicacion);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    public String getMarcaNombre() {
        MarcaDAO marcaDAO = new MarcaDAO();
        try {
            return marcaDAO.getNombrePorID(this.idMarca);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Desconocido";
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", idCategoria=" + idCategoria + ", idMarca=" + idMarca + ", idUbicacion=" + idUbicacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", stock=" + stock + ", stockMinimo=" + stockMinimo + ", fechaUltimaActualizacion=" + fechaUltimaActualizacion + ", activo=" + activo + '}';
    }

}
