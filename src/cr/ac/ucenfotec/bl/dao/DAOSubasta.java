package cr.ac.ucenfotec.bl.dao;

import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.exceptions.ExceptionUsuarioNoRegistrado;
import cr.ac.ucenfotec.tl.Conector;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DAOSubasta {

    private static String statement;
    private static String query;

    public static String insertarSubasta(Subasta subasta) throws SQLException, IOException, ClassNotFoundException {

        statement = "INSERT INTO t_subasta VALUES ('"
                + subasta.getIdSubasta() + "', '"
                + subasta.getFechaVencimiento() + "', '"
                + subasta.getPuntuacionCreador() + "', '"
                + subasta.getPrecioMinimoAceptable() + "', '"
                + subasta.getEstadoSubasta() + "', '"
                + subasta.getIdObjetoSubastar() + "', '"
                + subasta.getNombreObjeto() + "', '"
                + subasta.getCorreoUsuarioCreador() + "');";

        try {
            Conector.getConexion().ejecutarStatement(statement);
        } catch (ExceptionUsuarioNoRegistrado e) {
             new ExceptionUsuarioNoRegistrado().getMessage();
        }

        return "La subasta fue registrado correctamente";
    }

    public static void imprimirSubastasUsuario(String correoCreador) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_subasta WHERE correoCreador = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, correoCreador);

        if (!resultado.isBeforeFirst()) {
            System.out.println("Error: no tiene subastas registradas.");
            return;
        }

        while (resultado.next()) {
            Subasta subasta = new Subasta(
                    resultado.getInt("idObjeto"),
                    resultado.getString("nombreObjeto"),
                    resultado.getTimestamp("fechaVencimiento").toLocalDateTime(),
                    resultado.getDouble("precioMinimoAceptable"),
                    resultado.getString("estadoSubasta"),
                    resultado.getInt("puntuacionCreador")
            );
            subasta.setIdSubasta(resultado.getInt("id"));
            subasta.setCorreoUsuarioCreador(resultado.getString("correoCreador"));
            System.out.println(subasta);
        }
    }

    public static void imprimirSubastasActivas() throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_subasta WHERE estadoSubasta = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, "Activa");

        if (!resultado.isBeforeFirst()) {
            System.out.println("Error: no hay subastas activas en este momento.");
            return;
        }

        while (resultado.next()) {
            Subasta subasta = new Subasta(
                    resultado.getInt("idObjeto"),
                    resultado.getString("nombreObjeto"),
                    resultado.getTimestamp("fechaVencimiento").toLocalDateTime(),
                    resultado.getDouble("precioMinimoAceptable"),
                    resultado.getString("estadoSubasta"),
                    resultado.getInt("puntuacionCreador")
            );
            subasta.setIdSubasta(resultado.getInt("id"));
            subasta.setCorreoUsuarioCreador(resultado.getString("correoCreador"));
            System.out.println(subasta);
        }
    }

    public static Subasta buscarSubasta(int id) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_subasta WHERE id = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id);
        if (!resultado.next()) return null;
        Subasta subasta = new Subasta(
                resultado.getInt("idObjeto"),
                resultado.getString("nombreObjeto"),
                resultado.getTimestamp("fechaVencimiento").toLocalDateTime(),
                resultado.getDouble("precioMinimoAceptable"),
                resultado.getString("estadoSubasta"),
                resultado.getInt("puntuacionCreador")
        );
        subasta.setIdSubasta(resultado.getInt("id"));
        subasta.setCorreoUsuarioCreador(resultado.getString("correoCreador"));

        return subasta;
    }

    public static Subasta buscarSubastaUsuario(int id, String correoElectronico) throws SQLException, IOException, ClassNotFoundException {

        query = "SELECT * FROM t_subasta WHERE id = ? AND correoCreador = ?";
        ResultSet resultado = Conector.getConexion().ejecutarQuery(query, id, correoElectronico);
        if (!resultado.next()) return null;
        Subasta subasta = new Subasta(
                resultado.getInt("idObjeto"),
                resultado.getString("nombreObjeto"),
                resultado.getTimestamp("fechaVencimiento").toLocalDateTime(),
                resultado.getDouble("precioMinimoAceptable"),
                resultado.getString("estadoSubasta"),
                resultado.getInt("puntuacionCreador")
        );
        subasta.setIdSubasta(resultado.getInt("id"));
        subasta.setCorreoUsuarioCreador(resultado.getString("correoCreador"));

        return subasta;
    }

    public static void eliminarSubasta(int idSubasta) throws SQLException, IOException, ClassNotFoundException {
        statement = "DELETE FROM t_subasta WHERE id = ?";
        Conector.getConexion().ejecutarStatement(statement, idSubasta);
    }

}
