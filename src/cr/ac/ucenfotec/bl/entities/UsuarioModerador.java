package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;

public class UsuarioModerador extends Usuario{

    public UsuarioModerador() {
        super("admin", LocalDate.of(01,01,01), "123", "admin");
    }

    @Override
    public String toString() {
        return "\nUsuario Moderador:" +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  Usuario         : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() +
                "\n  Correo          : " + getCorreoElectronico();
    }
}
