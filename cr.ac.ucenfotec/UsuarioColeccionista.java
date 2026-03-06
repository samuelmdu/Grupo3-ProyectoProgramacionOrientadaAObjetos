import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioColeccionista extends Usuario {
    private int puntuacion;
    private String direccion;
    private ArrayList<Objeto> listaIntereses;
    private ArrayList<Objeto> listaObjetos;


    // Constructores
    public UsuarioColeccionista(String nombreCompleto, int idUsuario, LocalDate fechaNacimiento, int edad, String contrasenna, String correoElectronico, int puntuacion, String direccion) {
        super(nombreCompleto, idUsuario, fechaNacimiento, contrasenna, correoElectronico);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
    }

    public UsuarioColeccionista(){
    }

    // Getter & Setter
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

    public ArrayList<Objeto> getListaIntereses() {
        return listaIntereses;
    }

    public void setListaIntereses(Objeto objeto) {
        listaIntereses.add(objeto);
    }

    public ArrayList<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(Objeto objeto) {
        listaIntereses.add(objeto);
    }


    @Override
    public String toString() {
        return "\nUsuario Coleccionista:" +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  ID Usuario      : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() +
                "\n  Correo          : " + getCorreoElectronico() +
                "\n  Intereses       : " + listaIntereses +
                "\n  Objetos         : " + listaObjetos ;

     }
}
