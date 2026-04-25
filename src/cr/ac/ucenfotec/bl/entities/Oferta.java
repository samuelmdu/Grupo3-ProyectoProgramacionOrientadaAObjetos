package cr.ac.ucenfotec.bl.entities;

public class Oferta {

    private int idOferta;
    private String nombreOfertante;
    private int puntuacionOfertante;
    private double precioOfertado;
    private int idSubasta;
    private String correoCreador;

    public Oferta(UsuarioColeccionista usuario, double precioOfertado, int idSubasta, String correoCreador) {
        this.idOferta = (int) (Math.random() * 9000) + 1000;
        this.nombreOfertante = usuario.getNombreCompleto();
        this.puntuacionOfertante = usuario.getPuntuacion();
        this.precioOfertado = precioOfertado;
        this.idSubasta = idSubasta;
        this.correoCreador = correoCreador;
    }

    public Oferta() {
    }

    public int getIdOferta() {
        return idOferta;
    }

    public void setIdOferta(int idOferta) {
        this.idOferta = idOferta;
    }

    public String getNombreOfertante() {
        return nombreOfertante;
    }

    public void setNombreOfertante(String nombreOfertante) {
        this.nombreOfertante = nombreOfertante;
    }

    public int getPuntuacionOfertante() {
        return puntuacionOfertante;
    }

    public void setPuntuacionOfertante(int puntuacionOfertante) {
        this.puntuacionOfertante = puntuacionOfertante;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    public int getIdSubasta() {
        return idSubasta;
    }

    public void setIdSubasta(int idSubasta) {
        this.idSubasta = idSubasta;
    }

    public String getCorreoCreador() {
        return correoCreador;
    }

    public void setCorreoCreador(String correoCreador) {
        this.correoCreador = correoCreador;
    }

    @Override
    public String toString() {
        return "\nOferta:" +
                "\n  ID                 : " + idOferta +
                "\n  Nombre ofertante   : " + nombreOfertante +
                "\n  Puntuación ofertor : " + puntuacionOfertante +
                "\n  Precio ofertado    : " + precioOfertado +
                "\n  ID subasta         : " + idSubasta +
                "\n  Correo creador     : " + correoCreador;
    }
}