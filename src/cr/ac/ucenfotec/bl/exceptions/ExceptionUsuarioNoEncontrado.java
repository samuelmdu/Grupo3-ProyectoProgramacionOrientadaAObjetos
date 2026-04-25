package cr.ac.ucenfotec.bl.exceptions;

public class ExceptionUsuarioNoEncontrado extends RuntimeException {
    public ExceptionUsuarioNoEncontrado() {
        super("Error: no se encontró al usuario buscado.");
    }
}
