package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOObjeto;

import cr.ac.ucenfotec.bl.entities.Objeto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorObjeto {
    public static String registrarObjeto(String nombre, String descripcion, LocalDate fechaCompra, String estado, String correoCreador) throws SQLException, IOException, ClassNotFoundException {
        return DAOObjeto.insertarObjeto(new Objeto(nombre, descripcion, fechaCompra, estado, correoCreador));
    }

    public static void imprimirObjetos(String correoCreador) throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.imprimirObjetos(correoCreador);
    }

    public static Objeto buscarObjeto(int id) throws SQLException, IOException, ClassNotFoundException {
        return  DAOObjeto.buscarObjeto(id);
    }

    public static void actualizarDuennoObjeto(int idObjeto, String correoGanador) throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.actualizarDuennoObjeto(idObjeto, correoGanador);
    }

    public static void imprimirObjetosColeccionista(String correoColeccionista) throws SQLException, IOException, ClassNotFoundException {
        DAOObjeto.imprimirObjetosColeccionista(correoColeccionista);
    }

}
