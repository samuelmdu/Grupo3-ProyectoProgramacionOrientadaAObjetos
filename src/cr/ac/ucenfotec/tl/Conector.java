package cr.ac.ucenfotec.tl;

import cr.ac.ucenfotec.dl.AccesoBD;
import cr.ac.ucenfotec.utils.Utilidades;

import java.io.IOException;
import java.sql.SQLException;

public class Conector {

    private static AccesoBD conexionBD = null;

    public static AccesoBD getConexion() throws SQLException, ClassNotFoundException, IOException {
        if (conexionBD != null) return conexionBD;
        String[] propiedades = Utilidades.getProperties();
        String direccion = propiedades[0] + "//" + propiedades[1] + "/" + propiedades[2];
        String usuario = propiedades[3];
        String contrasenia = propiedades[4];

        return conexionBD = new AccesoBD(direccion, usuario, contrasenia);
    }
}
