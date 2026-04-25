package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.SQLException;

public class DAOUsuarioColeccionista {

    private static String statement;
    private static String query;

    public static String insertarUsuarioColeccionista(UsuarioColeccionista usuarioColeccionista) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_usuarios_coleccionista VALUES ('"
                + usuarioColeccionista.getIdUsuario() + "', '"
                + usuarioColeccionista.getNombreCompleto() + "', '"
                + usuarioColeccionista.getFechaNacimiento() + "', '"
                + usuarioColeccionista.getEdad() + "', '"
                + usuarioColeccionista.getContrasenna() + "', '"
                + usuarioColeccionista.getCorreoElectronico() + "', '"
                + usuarioColeccionista.getPuntuacion() + "', '"
                + usuarioColeccionista.getDireccion() + "');";

        try {
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
            return new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "El usuario coleccionista fue registrado correctamente";
    }
}
