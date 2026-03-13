package cr.ac.ucenfotec.bl;

import java.time.LocalDate;

public class UsuarioVendedor extends Usuario{
    private int puntuacion;
    private String direccion;


    //Constructores
    public UsuarioVendedor(String nombreCompleto, LocalDate fechaNacimiento, String contrasenna, String correoElectronico, int puntuacion, String direccion) {
        super(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico);
        this.puntuacion = puntuacion;
        this.direccion = direccion;

    }

    public UsuarioVendedor() {
    }

    //Getters & Setters
    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    @Override
    public String toString() {
        return "\nUsuario Vendedor:" +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  Usuario         : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() +
                "\n  Correo          : " + getCorreoElectronico();
    }
}
