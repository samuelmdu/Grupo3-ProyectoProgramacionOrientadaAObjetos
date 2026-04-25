package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.time.Period;

public class Objeto {

    private int idObjeto;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCompra;
    private String estado;
    private String correoCreador;

    public Objeto(String nombre, String descripcion, LocalDate fechaCompra, String estado, String correoCreador) {
        this.idObjeto = (int) (Math.random() * 9000) + 1000;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
        this.correoCreador = correoCreador;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getIdObjeto() {
        return idObjeto;
    }

    public String getAntiguedad() {
        LocalDate hoy = LocalDate.now();
        Period periodo = Period.between(fechaCompra, hoy);

        return periodo.getYears()  + " Años, " +
                periodo.getMonths() + " Meses, " +
                periodo.getDays()   + " Días";
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCorreoCreador() {
        return correoCreador;
    }

    public void setCorreoCreador(String correoCreador) {
        this.correoCreador = correoCreador;
    }

    @Override
    public String toString() {
        return "\nObjeto: " +
                "\nID             : " + idObjeto +
                "\nNombre         : " + nombre +
                "\nDescripcion    : " + descripcion +
                "\nFechaCompra    : " + fechaCompra +
                "\nAntiguedad     : " + getAntiguedad() +
                "\nEstado         : " + estado +
                "\nCorreo dueño   : " + correoCreador +
                "\n";

    }
}
