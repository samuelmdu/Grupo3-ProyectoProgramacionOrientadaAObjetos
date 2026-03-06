import java.time.LocalDate;

public class UsuarioModerador extends Usuario{

    public UsuarioModerador(String nombreCompleto, int idUsuario, LocalDate fechaNacimiento, String contrasenna, String correoElectronico) {
        super(nombreCompleto, idUsuario, fechaNacimiento, contrasenna, correoElectronico);
    }

    public UsuarioModerador(){
    }

    @Override
    public String toString() {
        return "\nUsuario Moderador:" +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  ID Usuario      : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() +
                "\n  Correo          : " + getCorreoElectronico();
    }
}
