package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUsuarioVendedor {

    private static String statement;
    private static String query;

    public static String insertarUsuarioVendedor(UsuarioVendedor usuarioVendedor) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_usuarios_vendedor VALUES ('"
                + usuarioVendedor.getIdUsuario() + "', '"
                + usuarioVendedor.getNombreCompleto() + "', '"
                + usuarioVendedor.getFechaNacimiento() + "', '"
                + usuarioVendedor.getEdad() + "', '"
                + usuarioVendedor.getContrasenna() + "', '"
                + usuarioVendedor.getCorreoElectronico() + "', '"
                + usuarioVendedor.getPuntuacion() + "', '"
                + usuarioVendedor.getDireccion() + "');";

        try {
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
            return new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "El usuario vendedor fue registrado correctamente";
    }

    public static UsuarioVendedor seleccionarUsuarioVendedor(String correo_electronico, String contrasenia) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_usuarios_vendedor WHERE correoElectronico = ? AND contrasenna = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, correo_electronico, contrasenia);
        if (!resultado.next()) return null;
        UsuarioVendedor insertarUsuarioVendedor = new UsuarioVendedor(
                resultado.getString("nombreCompleto"),
                resultado.getDate("fechaNacimiento").toLocalDate(),
                resultado.getString("contrasenna"),
                resultado.getString("correoElectronico"),
                resultado.getInt("puntuacion"),
                resultado.getString("direccion")
        );

        insertarUsuarioVendedor.setIdUsuario(resultado.getInt("id"));

        return insertarUsuarioVendedor;
    }

    public static UsuarioColeccionista seleccionarUsuarioColeccionista(String correo_electronico, String contrasenia) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_usuarios_coleccionista WHERE correoElectronico = ? AND contrasenna = ?;";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, correo_electronico, contrasenia);
        if (!resultado.next()) return null;
        UsuarioColeccionista insertarUsuarioColeccionista = new UsuarioColeccionista(
                resultado.getString("nombreCompleto"),
                resultado.getDate("fechaNacimiento").toLocalDate(),
                resultado.getString("contrasenna"),
                resultado.getString("correoElectronico"),
                resultado.getInt("puntuacion"),
                resultado.getString("direccion")
        );

        insertarUsuarioColeccionista.setIdUsuario(resultado.getInt("id"));

        return insertarUsuarioColeccionista;
    }


}
