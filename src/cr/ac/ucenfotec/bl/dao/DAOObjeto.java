package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOObjeto {

    private static String statement;
    private static String query;

    public static String insertarObjeto(Objeto objeto ) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_objetos VALUES ('"
                + objeto.getIdObjeto() + "', '"
                + objeto.getNombre() + "', '"
                + objeto.getDescripcion() + "', '"
                + objeto.getFechaCompra() + "', '"
                + objeto.getEstado() + "', '"
                + objeto.getCorreoCreador() + "');";

        try {
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
            return new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "El objeto fue registrado correctamente";
    }

    public static void imprimirObjetos(String correoCreador) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_objetos WHERE correoCreador = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, correoCreador);

        if (!resultado.isBeforeFirst()) {
            System.out.println("Error: no tiene objetos registrados.");
            return;
        }

        while (resultado.next()) {
            Objeto objeto = new Objeto(
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getDate("fechaCompra").toLocalDate(),
                    resultado.getString("estado"),
                    resultado.getString("correoCreador")
            );
            objeto.setIdObjeto(resultado.getInt("id"));
            System.out.println(objeto);
        }
    }

    public static void imprimirObjetosColeccionista(String correoColeccionista) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_objetos WHERE correoCreador = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, correoColeccionista);

        if (!resultado.isBeforeFirst()) {
            System.out.println("Error: no tiene objetos en su colección.");
            return;
        }

        while (resultado.next()) {
            Objeto objeto = new Objeto(
                    resultado.getString("nombre"),
                    resultado.getString("descripcion"),
                    resultado.getDate("fechaCompra").toLocalDate(),
                    resultado.getString("estado"),
                    resultado.getString("correoCreador")
            );
            objeto.setIdObjeto(resultado.getInt("id"));
            System.out.println(objeto);
        }
    }

    public static Objeto buscarObjeto(int id) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_objetos WHERE id = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        if (!resultado.next()) return null;
        Objeto objeto = new Objeto(
                resultado.getString("nombre"),
                resultado.getString("descripcion"),
                resultado.getDate("fechaCompra").toLocalDate(),
                resultado.getString("estado"),
                resultado.getString("correoCreador")
        );
        objeto.setIdObjeto(resultado.getInt("id"));

        return objeto;
    }

    public static void actualizarDuennoObjeto(int idObjeto, String correoGanador) throws SQLException, IOException, ClassNotFoundException {
        statement = "UPDATE t_objetos SET correoCreador = ? WHERE id = ?";
        Conector.getConexion().ejecutarStatement(statement, correoGanador, idObjeto);
    }
}
