package cr.ac.ucenfotec.bl.exceptions;

public class ExceptionUsuarioNoRegistrado extends RuntimeException {
    public ExceptionUsuarioNoRegistrado() {
        super("Error: Hubo un error al crear al usuario.");
    }
}
