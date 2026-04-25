package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;
import cr.ac.ucenfotec.bl.dao.DAOSubasta;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class GestorSubasta {
    public static String registrarSubasta(int idObjetoSubastar, String nombreObjeto, LocalDateTime fechaVencimiento, double precioMinimoAceptable, String correoUsuarioCreador, int puntuacionCreador) throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.insertarSubasta(new Subasta(idObjetoSubastar, nombreObjeto, fechaVencimiento, precioMinimoAceptable, correoUsuarioCreador, puntuacionCreador));
    }

    public static void imprimirSubastasUsuario(String correoCreador) throws SQLException, IOException, ClassNotFoundException {
        DAOSubasta.imprimirSubastasUsuario(correoCreador);
    }

    public static void imprimirSubastasActivas() throws SQLException, IOException, ClassNotFoundException {
        DAOSubasta.imprimirSubastasActivas();
    }

    public static Subasta buscarSubasta(int id) throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.buscarSubasta(id);
    }

    public static Subasta buscarSubastaUsuario(int id, UsuarioVendedor usuarioVendedor) throws SQLException, IOException, ClassNotFoundException {
        return DAOSubasta.buscarSubastaUsuario(id, usuarioVendedor.getCorreoElectronico());
    }

    public static void eliminarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        DAOSubasta.eliminarSubasta(idSubasta);
    }

}
