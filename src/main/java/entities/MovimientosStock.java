
package entities;


public class MovimientosStock {
    private int idMovStock;
    private int idProducto;
    private String FechaMov;
    private int cantidad;
    private String tipoMov;
    private String comentario;
    private boolean activo;

    public MovimientosStock() {
    }
    
    

    public MovimientosStock(int idMovStock, int idProducto, String FechaMov, int cantidad, String tipoMov, String comentario, boolean activo) {
        this.idMovStock = idMovStock;
        this.idProducto = idProducto;
        this.FechaMov = FechaMov;
        this.cantidad = cantidad;
        this.tipoMov = tipoMov;
        this.comentario = comentario;
        this.activo = activo;
    }

    public int getIdMovStock() {
        return idMovStock;
    }

    public void setIdMovStock(int idMovStock) {
        this.idMovStock = idMovStock;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getFechaMov() {
        return FechaMov;
    }

    public void setFechaMov(String FechaMov) {
        this.FechaMov = FechaMov;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getTipoMov() {
        return tipoMov;
    }

    public void setTipoMov(String tipoMov) {
        this.tipoMov = tipoMov;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return "MovimientosStock{" + "idMovStock=" + idMovStock + ", idProducto=" + idProducto + ", FechaMov=" + FechaMov + ", cantidad=" + cantidad + ", tipoMov=" + tipoMov + ", comentario=" + comentario + ", activo=" + activo +'}';
    }
    
    
            
            
    
}
