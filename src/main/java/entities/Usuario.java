
package entities;


public class Usuario {
    private int id;
    private String nombre;
    private String email;
    private String user;
    private String contraseña;
    private String fechacreacion;
    private boolean activo;

    public Usuario() {
        
    }
    
    

    public Usuario(int id, String nombre, String email, String user, String contraseña, String fechacreacion, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.user = user;
        this.contraseña = contraseña;
        this.fechacreacion = fechacreacion;
        this.activo = activo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public boolean isActivoUsuario() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", user=" + user + ", contrase\u00f1a=" + contraseña + ", fechacreacion=" + fechacreacion + ", activo=" + activo + '}';
    }
    
    
    
    
}
