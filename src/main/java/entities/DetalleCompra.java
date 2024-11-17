
package entities;


public class DetalleCompra {
    private int idDetalleCompra;
    private int idCompra;
    private int idProducto;
    private int cantidad;
    private int precioUnitario;
    private boolean activo;
    

    public DetalleCompra() {
    }
    
    

    public DetalleCompra(int idDetalleCompra, int idCompra, int idProducto, int cantidad, int precioUnitario, boolean activo) {
        this.idDetalleCompra = idDetalleCompra;
        this.idCompra = idCompra;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.activo = activo;
    }

    public int getIdDetalleCompra() {
        return idDetalleCompra;
    }

    public void setIdDetalleCompra(int idDetalleCompra) {
        this.idDetalleCompra = idDetalleCompra;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
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

    public int getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(int precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivoCompra(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "idDetalleCompra=" + idDetalleCompra + ", idCompra=" + idCompra + ", idProducto=" + idProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", activo=" + activo + '}';
    }
    
    
   
    
}
