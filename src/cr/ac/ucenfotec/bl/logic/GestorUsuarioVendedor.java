package cr.ac.ucenfotec.bl.logic;

import cr.ac.ucenfotec.bl.dao.DAOUsuarioVendedor;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class GestorUsuarioVendedor {

    public static String registrarUsuarioVendedor(String nombreCompleto, LocalDate fechaNacimiento, String contrasenna, String correoElectronico, int puntuacion, String direccion) throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioVendedor.insertarUsuarioVendedor(new UsuarioVendedor(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion));
    }

    public static UsuarioVendedor ingresarUsuarioVendedor(String correoElectronico, String contrasenna) throws SQLException, IOException, ClassNotFoundException {
        return DAOUsuarioVendedor.seleccionarUsuarioVendedor(correoElectronico, contrasenna);
    }

}
