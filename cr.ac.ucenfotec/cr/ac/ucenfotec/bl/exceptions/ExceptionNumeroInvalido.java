package cr.ac.ucenfotec.bl.exceptions;

public class ExceptionNumeroInvalido extends RuntimeException {
    public ExceptionNumeroInvalido() {
        super("Error: Debe ingresar únicamente numeros en este campo");
    }
}
