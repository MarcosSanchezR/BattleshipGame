package es.upm.etsisi.fis.persistencia;

import java.util.Objects;

public class JugadorHumano extends Jugador {

    private String nombreUsuario;
    private String correo;
    private String password;
    private boolean isAdmin;

    public JugadorHumano(String nombreUsuario, String correo, String password, boolean isAdmin) {
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JugadorHumano that = (JugadorHumano) o;
        return Objects.equals(correo, that.correo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(correo);
    }

    //@TODO: Implementar RF#18604
    public boolean iniciarSesion() {
        return false;
    }

    //@TODO: Implementar RF#18605
    public boolean cerrarSesion(){
        return false;
    }

}
