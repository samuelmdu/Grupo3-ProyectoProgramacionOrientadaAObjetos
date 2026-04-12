package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioColeccionista extends Usuario {
    private int puntuacion;
    private String direccion;
    private ArrayList<Subasta> listaIntereses = new ArrayList<>();
    private ArrayList<Objeto> listaObjetos = new ArrayList<>();

    public UsuarioColeccionista(String nombreCompleto, LocalDate fechaNacimiento, String contrasenna, String correoElectronico, int puntuacion, String direccion) {
        super(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico);
        this.puntuacion = puntuacion;
        this.direccion = direccion;
    }

    public UsuarioColeccionista(){
    }

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

    public ArrayList<Subasta> getListaIntereses() {
        return listaIntereses;
    }

    public void agregarInteres(Subasta subasta) {
        listaIntereses.add(subasta);
    }

    public ArrayList<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(Objeto objeto) {
        listaObjetos.add(objeto);
    }

    @Override
    public String toString() {
        return "\nUsuario Coleccionista:" +
                "\n  Correo          : " + getCorreoElectronico() +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  Usuario         : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() +
                "\n  Intereses       : " + listaIntereses +
                "\n  Objetos         : " + listaObjetos;
    }
}