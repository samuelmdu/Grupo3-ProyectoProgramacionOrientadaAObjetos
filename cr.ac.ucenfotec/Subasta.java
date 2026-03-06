import java.util.ArrayList;
import java.time.Duration;
import java.time.LocalDateTime;


public class Subasta {

    private LocalDateTime fechaVencimiento;
    private String tiempoRestante;
    private Usuario usuarioCreador;
    private int puntuacionCreador;
    private double precioMinimoAceptable;
    private String estadoSubasta;
    private ArrayList<Objeto> listaObjetos;

    public Subasta(LocalDateTime fechaVencimiento, Usuario usuarioCreador, double precioMinimoAceptable) {
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

        this.listaObjetos = new ArrayList<Objeto>();
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

    public ArrayList<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(Objeto objeto) {
        listaObjetos.add(objeto);
    }

    @Override
    public String toString() {
        return "\nSubasta:" +
                "\n  Fecha vencimiento : " + fechaVencimiento +
                "\n  Tiempo restante   : " + tiempoRestante +
                "\n  Usuario creador   : " + usuarioCreador.getNombreCompleto() +
                "\n  Puntuación creador: " + puntuacionCreador +
                "\n  Precio mínimo     : " + precioMinimoAceptable +
                "\n  Lista objetos     : " + listaObjetos +
                "\n  Estado subasta    : " + estadoSubasta;
    }
}
