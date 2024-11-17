
package entities;

public class Ubicacion {
    private int idUbicacion;
    private String nombre;
    private String descripcion;
    private String seccion;
    private boolean activo;
    
    public Ubicacion() {
    }
    
    public Ubicacion(int idUbicacion, String nombre, String descripcion, String seccion, boolean activo) {
        this.idUbicacion = idUbicacion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.seccion = seccion;
        this.activo = activo;
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
    

    @Override
    public String toString() {
        return "Ubicacion{" + "idUbicacion=" + idUbicacion + ", nombre=" + nombre + ", descripcion=" + descripcion + ", seccion=" + seccion + ", activo=" + activo + '}';
    }
    
}
