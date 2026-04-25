package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOOferta {

    private static String statement;
    private static String query;

    public static String insertarOferta(Oferta oferta, Subasta subastaEncontrada) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_oferta VALUES ('"
                + oferta.getIdOferta() + "', '"
                + oferta.getNombreOfertante() + "', '"
                + oferta.getPuntuacionOfertante() + "', '"
                + oferta.getPrecioOfertado() + "', '"
                + subastaEncontrada.getIdSubasta() + "', '"
                + subastaEncontrada.getCorreoUsuarioCreador() + "');";

        try{
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
            return new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "La oferta fue registrada correctamente";
    }

    public static Oferta buscarOfertaMayor(int idSubasta) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_oferta WHERE idSubasta = ? ORDER BY precioOfertado DESC LIMIT 1";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, idSubasta);

        if (!resultado.next()) return null;

        Oferta oferta = new Oferta();

        oferta.setIdOferta(resultado.getInt("id"));
        oferta.setNombreOfertante(resultado.getString("nombreOfertante"));
        oferta.setPuntuacionOfertante(resultado.getInt("puntuacionOfertante"));
        oferta.setPrecioOfertado(resultado.getDouble("precioOfertado"));
        oferta.setIdSubasta(resultado.getInt("idSubasta"));
        oferta.setCorreoCreador(resultado.getString("correoCreador"));


        return oferta;
    }

    public static void eliminarOfertas(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "DELETE FROM t_oferta WHERE idSubasta = ?";
        Conector.getConexion().ejecutarStatement(statement, idSubasta);
    }

}
