import java.time.LocalDate;
import java.time.Period;


abstract class Usuario {
    private String nombreCompleto;
    private int idUsuario;
    private LocalDate fechaNacimiento;
    private int edad; // Edad se calcula basandose en fechaNacimiento
    private String contrasenna;
    private String correoElectronico;


    public Usuario(String nombreCompleto, int idUsuario, LocalDate fechaNacimiento, String contrasenna, String correoElectronico) {
        this.nombreCompleto = nombreCompleto;
        this.idUsuario = idUsuario;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasenna = contrasenna;
        this.correoElectronico = correoElectronico;
    }

    public Usuario() {
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        LocalDate today = LocalDate.now();
        return Period.between(fechaNacimiento, today).getYears();
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }



    @Override
    public String toString() {
        return "\nUsuario:" +
                "\n  Nombre completo : " + nombreCompleto +
                "\n  ID Usuario      : " + idUsuario +
                "\n  Fecha nacimiento: " + fechaNacimiento +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + contrasenna +
                "\n  Correo          : " + correoElectronico;
}

}
