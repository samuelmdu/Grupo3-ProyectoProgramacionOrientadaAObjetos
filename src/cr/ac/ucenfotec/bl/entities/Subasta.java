package cr.ac.ucenfotec.bl.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Subasta {

    private int idSubasta;
    private int idObjetoSubastar;
    private String nombreObjeto;
    private LocalDateTime fechaVencimiento;
    private String tiempoRestante;
    private double precioMinimoAceptable;
    private String estadoSubasta;
    private String correoUsuarioCreador;
    private int puntuacionCreador;

    public Subasta(int idObjetoSubastar, String nombreObjeto, LocalDateTime fechaVencimiento, double precioMinimoAceptable, String correoUsuarioCreador, int puntuacionCreador) {
        this.idSubasta = (int) (Math.random() * 9000) + 1000;
        this.idObjetoSubastar = idObjetoSubastar;
        this.nombreObjeto = nombreObjeto;
        this.fechaVencimiento = fechaVencimiento;
        this.tiempoRestante = getTiempoRestante();
        this.precioMinimoAceptable = precioMinimoAceptable;
        this.correoUsuarioCreador = correoUsuarioCreador;
        this.puntuacionCreador = puntuacionCreador;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public int getIdObjetoSubastar() {
        return idObjetoSubastar;
    }

    public void setIdObjetoSubastar(int idObjetoSubastar) {
        this.idObjetoSubastar = idObjetoSubastar;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public LocalDateTime getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDateTime fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }

    public double getPrecioMinimoAceptable() {
        return precioMinimoAceptable;
    }

    public void setPrecioMinimoAceptable(double precioMinimoAceptable) {
        this.precioMinimoAceptable = precioMinimoAceptable;
    }

    public String getEstadoSubasta() {
        return estadoSubasta;
    }

    public void setEstadoSubasta(String estadoSubasta) {
        this.estadoSubasta = estadoSubasta;
    }

    public String getCorreoUsuarioCreador() {
        return correoUsuarioCreador;
    }

    public void setCorreoUsuarioCreador(String correoUsuarioCreador) {
        this.correoUsuarioCreador = correoUsuarioCreador;
    }

    public int getPuntuacionCreador() {
        return puntuacionCreador;
    }

    public void setPuntuacionCreador(int puntuacionCreador) {
        this.puntuacionCreador = puntuacionCreador;
    }

    public String getTiempoRestante() {

        LocalDateTime now = LocalDateTime.now();

        if(now.isAfter(fechaVencimiento)){
            estadoSubasta = "Expirada";
        } else {
            estadoSubasta = "Activa";
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

    @Override
    public String toString() {
        return "\nSubasta:" +
                "\n  ID                       : " + getIdSubasta() +
                "\n  ID objeto a subastar     : " + idObjetoSubastar +
                "\n  nombre objeto a subastar : " + nombreObjeto +
                "\n  Usuario creador          : " + correoUsuarioCreador +
                "\n  Fecha vencimiento        : " + fechaVencimiento +
                "\n  Tiempo restante          : " + tiempoRestante +
                "\n  Precio mínimo            : " + precioMinimoAceptable +
                "\n  Estado subasta           : " + estadoSubasta +
                "\n  Puntuación creador       : " + puntuacionCreador +
                "\n";
    }
}
