package cr.ac.ucenfotec.bl.entities;

import java.time.LocalDate;
import java.time.Period;

public class Objeto {

    private static int contador;

    private int idObjeto;
    private String nombre;
    private String descripcion;
    private LocalDate fechaCompra;
    private String estado;
    private String antiguedad;

    public Objeto(String nombre, String descripcion, LocalDate fechaCompra, String estado) {
        this.idObjeto = ++contador;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaCompra = fechaCompra;
        this.estado = estado;
    }

    public Objeto() {
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


    @Override
    public String toString() {
        return "\nObjeto: " +
                "\nID           : " + getIdObjeto() +
                "\nNombre       : " + nombre +
                "\nDescripcion  : " + descripcion +
                "\nFechaCompra  : " + fechaCompra +
                "\nAntiguedad   : " + getAntiguedad() +
                "\nEstado       : " + estado +
                "\n";

    }
}
