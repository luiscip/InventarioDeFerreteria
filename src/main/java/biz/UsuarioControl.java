package biz;

import data.UsuarioDAO;
import entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class UsuarioControl {

    private final UsuarioDAO DATOS;
    private Usuario obj;
    private DefaultTableModel modeloTabla;
    public int registrosMostrados;

    public UsuarioControl() {
        this.DATOS = new UsuarioDAO();
        this.obj = new Usuario();
        this.registrosMostrados = 0;
    }

    public DefaultTableModel listar(String texto) {
        List<Usuario> lista = new ArrayList<>();
        lista.addAll(DATOS.listar(texto));

        String titulos[] = {"ID", "Nombre", "Email", "Usuario", "Fecha Creación", "Estado"};
        this.modeloTabla = new DefaultTableModel(null, titulos);

        String estado;
        String registro[] = new String[6];

        this.registrosMostrados = 0;

        for (Usuario item : lista) {
            estado = item.isActivoUsuario() ? "Activo" : "Inactivo";

            registro[0] = Integer.toString(item.getId());
            registro[1] = item.getNombre();
            registro[2] = item.getEmail();
            registro[3] = item.getUser();
            registro[4] = item.getFechacreacion();
            registro[5] = estado;

            this.modeloTabla.addRow(registro);

            this.registrosMostrados++;
        }

        return this.modeloTabla;
    }

    public String insertar(String nombre, String email, String user, String contraseña, String fechaCreacion) {
        if (DATOS.existencia(user)) {
            return "El usuario ya existe";
        } else {
            obj.setNombre(nombre);
            obj.setEmail(email);
            obj.setUser(user);
            obj.setContraseña(contraseña);
            obj.setFechacreacion(fechaCreacion);

            if (DATOS.insertar(obj)) {
                return "OK";
            } else {
                return "Error al registrar el usuario";
            }
        }
    }

    public String actualizar(int id, String nombre, String email, String user, String contraseña, String fechaCreacion, String userAnt) {
        if (user.equals(userAnt)) {
            obj.setId(id);
            obj.setNombre(nombre);
            obj.setEmail(email);
            obj.setUser(user);
            obj.setContraseña(contraseña);
            obj.setFechacreacion(fechaCreacion);

            if (DATOS.actualizar(obj)) {
                return "OK";
            } else {
                return "Error en la actualización del usuario";
            }
        } else {
            if (DATOS.existencia(user)) {
                return "El usuario ya existe";
            } else {
                obj.setId(id);
                obj.setNombre(nombre);
                obj.setEmail(email);
                obj.setUser(user);
                obj.setContraseña(contraseña);
                obj.setFechacreacion(fechaCreacion);

                if (DATOS.actualizar(obj)) {
                    return "OK";
                } else {
                    return "Error en la actualización del usuario";
                }
            }
        }
    }

    public String desactivar(int id) {
        if (DATOS.desactivar(id)) {
            return "OK";
        } else {
            return "No se puede desactivar el usuario";
        }
    }

    public String activar(int id) {
        if (DATOS.activar(id)) {
            return "OK";
        } else {
            return "No se puede activar el usuario";
        }
    }

    public int total() {
        return DATOS.total();
    }

    public int totalMostrados() {
        return this.registrosMostrados;
    }
}
