package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.UsuarioModerador;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoEncontrado;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuarioModerador {

    private static String statement;
    private static String query;

    public static String insertarUsuarioModerador(UsuarioModerador usuarioModerador) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_usuarios_moderador VALUES ('"
                + usuarioModerador.getIdUsuario() + "', '"
                + usuarioModerador.getNombreCompleto() + "', '"
                + usuarioModerador.getFechaNacimiento() + "', '"
                + usuarioModerador.getEdad() + "', '"
                + usuarioModerador.getContrasenna() + "', '"
                + usuarioModerador.getCorreoElectronico() + "');";

        try{
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
            return new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "El usuario moderador fue registrado correctamente";
    }

    public static boolean buscarUsuarioModerador() throws SQLException, IOException, ClassNotFoundException {

        boolean usuarioModCreado = false;

        query = "SELECT * FROM t_usuarios_moderador WHERE correoElectronico = 'admin';";

        ResultSet resultado = Conector.getConexion().ejecutarQuery(query);

        if (!resultado.next()) return false;

        if(resultado.getString("correoElectronico").equals("admin"))  usuarioModCreado = true;

        return usuarioModCreado;

    }
}
