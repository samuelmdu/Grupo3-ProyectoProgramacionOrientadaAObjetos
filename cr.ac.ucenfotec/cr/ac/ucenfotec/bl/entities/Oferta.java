package cr.ac.ucenfotec.bl.entities;

public class Oferta {

    private static int contador = 0;

    private int idOferta;
    private String nombreOfertante;
    private int puntuacionOfertante;
    private double precioOfertado;
    private UsuarioColeccionista usuarioColeccionista;

    public Oferta(UsuarioColeccionista usuario, double precioOfertado) {
        this.idOferta = ++contador;
        this.nombreOfertante = usuario.getNombreCompleto();
        this.puntuacionOfertante = usuario.getPuntuacion();
        this.precioOfertado = precioOfertado;
        this.usuarioColeccionista = usuario;

    }

    public Oferta() {
    }

    public int getIdOferta() {
        return idOferta;
    }

    public String getNombreOfertante() {
        return nombreOfertante;
    }

    public int getPuntuacionOfertante() {
        return puntuacionOfertante;
    }

    public double getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(double precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    public UsuarioColeccionista getUsuarioColeccionista() {
        return usuarioColeccionista;
    }


    @Override
    public String toString() {
        return "\nOferta:" +
                "\n  ID                 : " + getIdOferta() +
                "\n  Nombre ofertante   : " + nombreOfertante +
                "\n  Puntuación ofertor : " + puntuacionOfertante +
                "\n  Precio ofertado    : " + precioOfertado;
    }
}
