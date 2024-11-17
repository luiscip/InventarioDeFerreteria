
package entities;

public class DetalleVenta {
    private int idDetalleVenta;
    private int idVenta;
    private int idProducto;
    private int cantidad;
    private double PrecioVentaUni;
    private boolean activo;

    public DetalleVenta() {
    }
    
    

    public DetalleVenta(int idDetalleVenta, int idVenta, int idProducto, int cantidad, double PrecioVentaUni, boolean activo) {
        this.idDetalleVenta = idDetalleVenta;
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.PrecioVentaUni = PrecioVentaUni;
        this.activo = activo;
    }

    public int getIdDetalleVenta() {
        return idDetalleVenta;
    }

    public void setIdDetalleVenta(int idDetalleVenta) {
        this.idDetalleVenta = idDetalleVenta;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioVentaUni() {
        return PrecioVentaUni;
    }

    public void setPrecioVentaUni(double PrecioVentaUni) {
        this.PrecioVentaUni = PrecioVentaUni;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return "DetalleVenta{" + "idDetalleVenta=" + idDetalleVenta + ", idVenta=" + idVenta + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", PrecioVentaUni=" + PrecioVentaUni + ", activo=" + activo +'}';
    }
    
    
    
    
}
