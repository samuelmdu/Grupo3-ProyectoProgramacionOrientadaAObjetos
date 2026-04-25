package cr.ac.ucenfotec.bl.exceptions;

public class ExceptionMayorEdad extends RuntimeException {
  public ExceptionMayorEdad() {
    super("Error: Para utilizar el sistema se requiere ser mayor de edad");
  }
}
