package cr.ac.ucenfotec.bl.exceptions;

public class ExceptionFechaInvalida extends RuntimeException {
  public ExceptionFechaInvalida() {
    super("Error: Formato de fecha inválido. Use el formato solicitado");
  }
}