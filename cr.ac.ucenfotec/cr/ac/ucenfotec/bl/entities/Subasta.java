package cr.ac.ucenfotec.bl.entities;

import java.time.Duration;
import java.time.LocalDateTime;


public class Subasta {

    private static int contador;

    private int idSubasta;
    private LocalDateTime fechaVencimiento;
    private String tiempoRestante;
    private Usuario usuarioCreador;
    private int puntuacionCreador;
    private double precioMinimoAceptable;
    private String estadoSubasta;
    private Objeto objetoSubastar;


    public Subasta(LocalDateTime fechaVencimiento, Usuario usuarioCreador, double precioMinimoAceptable, Objeto objetoSubastar) {
        this.idSubasta = ++contador;
        this.fechaVencimiento = fechaVencimiento;
        this.tiempoRestante = getTiempoRestante();
        this.usuarioCreador = usuarioCreador;
        // Revisa si el usuario es vendedor o coleccionista y asigna a puntuacionCreador, utilizando el .getPuntuacion.
        if (usuarioCreador instanceof UsuarioVendedor) {
          this.puntuacionCreador = ((UsuarioVendedor) usuarioCreador).getPuntuacion();
        }
        else if (usuarioCreador instanceof UsuarioColeccionista) {
            this.puntuacionCreador = ((UsuarioColeccionista) usuarioCreador).getPuntuacion();
        }
        this.precioMinimoAceptable = precioMinimoAceptable;
        this.objetoSubastar = objetoSubastar;

    }

    public Subasta() {
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public String getTiempoRestante() {

        LocalDateTime now = LocalDateTime.now();

        if(now.isAfter(fechaVencimiento)){
           estadoSubasta = "Subasta expirada";
        } else {
            estadoSubasta = "Subasta activa";
        }

        Duration duration = Duration.between(now, fechaVencimiento);

        long seconds = duration.getSeconds();

        long days = seconds / (24 * 3600);
        seconds %= (24 * 3600);

        long hours = seconds / 3600;
        seconds %= 3600;

        long minutes = seconds / 60;
        seconds %= 60;

        return days + " días, " + hours + " hours, " +
                minutes + " minutos, " + seconds + " segundos";
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public Usuario getUsuarioCreador() {
        return usuarioCreador;
    }

    public void setUsuarioCreador(Usuario usuarioCreador) {
        this.usuarioCreador = usuarioCreador;
    }

    public int getPuntuacionCreador() {
        return puntuacionCreador;
    }

    public double getprecioMinimoAceptable() {
        return precioMinimoAceptable;
    }

    public void setprecioMinimoAceptable(double precioMinimoAceptable) {
        this.precioMinimoAceptable = precioMinimoAceptable;
    }

    public String getEstadoSubasta() {
        return estadoSubasta;
    }

    public Objeto getObjetoSubastar() {
        return objetoSubastar;
    }

    public void setObjetoSubastar(Objeto objetoSubastar) {
        this.objetoSubastar = objetoSubastar;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    @Override
    public String toString() {
        return "\nSubasta:" +
                "\n  ID                : " + getIdSubasta() +
                "\n  Fecha vencimiento : " + fechaVencimiento +
                "\n  Tiempo restante   : " + tiempoRestante +
                "\n  Usuario creador   : " + usuarioCreador.getNombreCompleto() +
                "\n  Puntuación creador: " + puntuacionCreador +
                "\n  Precio mínimo     : " + precioMinimoAceptable +
                "\n  Objeto a subastar : " + objetoSubastar.getNombre() +
                "\n  Estado subasta    : " + estadoSubasta +
                "\n";
    }
}
