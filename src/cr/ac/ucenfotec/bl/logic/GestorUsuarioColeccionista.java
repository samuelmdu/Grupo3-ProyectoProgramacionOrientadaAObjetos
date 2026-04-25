package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOUsuarioColeccionista;
import cr.ac.ucenfotec.bl.dao.DAOUsuarioVendedor;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorUsuarioColeccionista {
    public static String registrarUsuarioColeccionista(String nombreCompleto, LocalDate fechaNacimiento, String contrasenna, String correoElectronico, int puntuacion, String direccion) throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioColeccionista.insertarUsuarioColeccionista(new UsuarioColeccionista(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion));
    }

    public static UsuarioColeccionista ingresarUsuarioColeccionista(String correoElectronico, String contrasenna) throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioVendedor.seleccionarUsuarioColeccionista(correoElectronico, contrasenna);
    }
}
