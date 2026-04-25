package cr.ac.ucenfotec.bl.logic;


import cr.ac.ucenfotec.bl.dao.DAOOferta;
import cr.ac.ucenfotec.bl.entities.Oferta;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;

import java.io.IOException;
import java.sql.SQLException;

public class GestorOferta {

    public static String registrarOferta(UsuarioColeccionista usuarioColeccionista, double precioOfertado, Subasta subastaEncontrada) throws SQLException, IOException, ClassNotFoundException {
        return DAOOferta.insertarOferta(new Oferta(usuarioColeccionista, precioOfertado, subastaEncontrada.getIdSubasta(), subastaEncontrada.getCorreoUsuarioCreador()), subastaEncontrada);
    }

    public static Oferta buscarOfertaMayor(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        return DAOOferta.buscarOfertaMayor(idSubasta);
    }

    public static void eliminarOfertas(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        DAOOferta.eliminarOfertas(idSubasta);
    }
}
