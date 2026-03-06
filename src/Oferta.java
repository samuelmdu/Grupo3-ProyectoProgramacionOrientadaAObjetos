public class Oferta {

    private String nombreOfertante;
    private int puntuacionOfertante;
    private double precioOfertado;

    public Oferta(Usuario usuario, double precioOfertado) {
        this.nombreOfertante = usuario.getNombreCompleto();
        // Revisa si el usuario es vendedor o coleccionista y asigna a puntuacionCreador, utilizando el .getPuntuacion.
        if (usuario instanceof UsuarioVendedor) {
            this.puntuacionOfertante = ((UsuarioVendedor) usuario).getPuntuacion();
        }
        else if (usuario instanceof UsuarioColeccionista) {
            this.puntuacionOfertante = ((UsuarioColeccionista) usuario).getPuntuacion();
        }
        this.precioOfertado = precioOfertado;
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

    @Override
    public String toString() {
        return "\nOferta:" +
                "\n  Nombre ofertante   : " + nombreOfertante +
                "\n  Puntuación ofertor : " + puntuacionOfertante +
                "\n  Precio ofertado    : " + precioOfertado;
    }
}
