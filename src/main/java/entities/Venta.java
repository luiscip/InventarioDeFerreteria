
package entities;

public class Venta {
    private int idVenta;
    private String fechaVenta;
    private double total;
    private boolean activo;

    public Venta() {
    }
    
    public Venta(int idventa, String fechaVenta, double total, boolean activo) {
        this.idVenta = idventa;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.activo = activo;
    }

    public int getIdventa() {
        return idVenta;
    }

    public void setIdventa(int idventa) {
        this.idVenta = idventa;
    }

    public String getFechaventa() {
        return fechaVenta;
    }

    public void setFechaventa(String fechaventa) {
        this.fechaVenta = fechaventa;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public boolean isActivoVenta() {
        return activo;
    }

    public void setActivoVenta(boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return "Venta{" + "idventa=" + idVenta + ", fechaventa=" + fechaVenta + ", total=" + total + ", activo=" + activo +'}';
    }
    
    
    
}
