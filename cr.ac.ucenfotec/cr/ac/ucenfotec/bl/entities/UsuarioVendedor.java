package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.util.ArrayList;

public class UsuarioVendedor extends Usuario{
    private int puntuacion;
    private String direccion;
    private ArrayList<Objeto> listaObjetos = new ArrayList<>();
    private ArrayList<Subasta> listaSubastas= new ArrayList<>();
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

    public ArrayList<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(Objeto objeto) {
        listaObjetos.add(objeto);
    }

    public ArrayList<Subasta> getListaSubastas() {
        return listaSubastas;
    }

    public void setListaSubastas(Subasta subasta) {
        listaSubastas.add(subasta);
    }

    @Override
    public String toString() {
        return "\nUsuario Vendedor:" +
                "\n  Correo          : " + getCorreoElectronico() +
                "\n  Nombre completo : " + getNombreCompleto() +
                "\n  Usuario         : " + getIdUsuario() +
                "\n  Fecha nacimiento: " + getFechaNacimiento() +
                "\n  Edad            : " + getEdad() +
                "\n  Contraseña      : " + getContrasenna() ;
    }
}
