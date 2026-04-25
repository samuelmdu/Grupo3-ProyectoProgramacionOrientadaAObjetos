package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOUsuarioModerador;
import cr.ac.ucenfotec.bl.entities.UsuarioModerador;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorUsuarioModerador {

    public static String registrarUsuarioModerador() throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioModerador.insertarUsuarioModerador(new UsuarioModerador());
    }

    public static boolean buscarUsuarioModerador() throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioModerador.buscarUsuarioModerador();
    }
}
