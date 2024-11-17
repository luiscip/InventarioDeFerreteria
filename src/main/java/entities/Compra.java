
package entities;


public class Compra {
    private int idCompra;
    private int idProveedor;
    private String fechaCompra;
    private double totalCompra;
    private boolean activo;

    public Compra() {
    }
    
    

    public Compra(int idCompra, int idProveedor, String fechaCompra, double totalCompra, boolean activo) {
        this.idCompra = idCompra;
        this.idProveedor = idProveedor;
        this.fechaCompra = fechaCompra;
        this.totalCompra = totalCompra;
        this.activo = activo;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", idProveedor=" + idProveedor + ", fechaCompra=" + fechaCompra + ", totalCompra=" + totalCompra + ", activo=" + activo + '}';
    }
    
    
    
}
