package cr.ac.ucenfotec.dl;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exceptions.ExceptionFechaInvalida;
import cr.ac.ucenfotec.bl.exceptions.ExceptionMayorEdad;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;
import cr.ac.ucenfotec.bl.logic.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Controlador {

    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));


    // --- REGISTRAR USUARIOS --- //

    public static void registrarUsuarioModerador() throws IOException, SQLException, ClassNotFoundException {

        System.out.println("Error: Lo sentimos, no hay un moderador creado en el sistema.");

        System.out.println(GestorUsuarioModerador.registrarUsuarioModerador());

    }

    public static void registrarUsuarioVendedor() throws IOException, SQLException, ClassNotFoundException {

        System.out.print("\nIngrese su correo electrónico: ");
        String correoElectronico = reader.readLine();

        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = reader.readLine();

        LocalDate fechaNacimiento = null;
        try {
            System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
            fechaNacimiento = LocalDate.parse(reader.readLine());
        } catch (Exception e) {
            System.out.println(new ExceptionFechaInvalida().getMessage());
            return;

        }

        System.out.print("Ingrese su contraseña: ");
        String contrasenna = reader.readLine();

        int puntuacion = 0;
        try {
            System.out.print("Ingrese su puntuación: ");
            puntuacion = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        System.out.print("Ingrese su dirección: ");
        String direccion = reader.readLine();

        if (revisarEdad(fechaNacimiento) < 18) {
            System.out.println(new ExceptionMayorEdad().getMessage());
            return;
        }

        System.out.println(GestorUsuarioVendedor.registrarUsuarioVendedor(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion));

    }

    public static void registrarColeccionista() throws IOException, SQLException, ClassNotFoundException {

        System.out.print("\nIngrese su correo electrónico: ");
        String correoElectronico = reader.readLine();


        System.out.print("Ingrese su nombre completo: ");
        String nombreCompleto = reader.readLine();

        LocalDate fechaNacimiento = null;
        try {
            System.out.print("Ingrese su fecha de nacimiento (YYYY-MM-DD): ");
            fechaNacimiento = LocalDate.parse(reader.readLine());
        } catch (Exception e) {
            System.out.println(new ExceptionFechaInvalida().getMessage());
            return;
        }

        System.out.print("Ingrese su contraseña: ");
        String contrasenna = reader.readLine();

        int puntuacion = 0;
        try {
            System.out.print("Ingrese su puntuación: ");
            puntuacion = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        System.out.print("Ingrese su dirección: ");
        String direccion = reader.readLine();

        if (revisarEdad(fechaNacimiento) < 18) {
            System.out.println(new ExceptionMayorEdad().getMessage());
            return;
        }

        System.out.println(GestorUsuarioColeccionista.registrarUsuarioColeccionista(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion));

    }

    public static void registrarObjeto(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        System.out.print("\nIngrese el nombre del objeto: ");
        String nombre = reader.readLine();

        System.out.print("Ingrese la descripción del objeto: ");
        String descripcion = reader.readLine();

        LocalDate fechaCompra = null;
        try {
            System.out.print("Ingrese la fecha de compra (YYYY-MM-DD): ");
            fechaCompra = LocalDate.parse(reader.readLine());
        } catch (Exception e) {
            System.out.println(new ExceptionFechaInvalida().getMessage());
            return;
        }

        System.out.println("Ingrese el estado del objeto:");
        String estado = reader.readLine();

        System.out.println(GestorObjeto.registrarObjeto(nombre, descripcion, fechaCompra, estado, usuarioVendedor.getCorreoElectronico()));

    }

    public static void registrarSubasta(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        imprimirObjetos(usuarioVendedor);

        Objeto objetoEncontrado = buscarObjeto();

        if (objetoEncontrado == null) {
            System.out.println("Error: el objeto no fue encontrado.");
            return;
        }

        LocalDateTime fechaVencimiento = null;
        try {
            System.out.print("Ingrese la fecha y hora de vencimiento (YYYY-MM-DD HH:MM): ");
            fechaVencimiento = LocalDateTime.parse(reader.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (Exception e) {
            System.out.println(new ExceptionFechaInvalida().getMessage());
            return;
        }

        double precioMinimoAceptable = 0;
        try {
            System.out.print("Ingrese el precio mínimo aceptable: ");
            precioMinimoAceptable = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        System.out.println(GestorSubasta.registrarSubasta(objetoEncontrado.getIdObjeto(), objetoEncontrado.getNombre(), fechaVencimiento, precioMinimoAceptable, objetoEncontrado.getCorreoCreador(), usuarioVendedor.getPuntuacion()));

    }

    public static void registrarOferta(UsuarioColeccionista usuarioColeccionista) throws IOException, SQLException, ClassNotFoundException {

         imprimirSubastasActivas();

         Subasta subastaEncontrada =  buscarSubasta();

        if (subastaEncontrada == null) {
            System.out.println("Error: el objeto no fue encontrado.");
            return;
        }

        double precioOfertar = 0;
        try {
            System.out.println("Precio minimo: " + subastaEncontrada.getPrecioMinimoAceptable());
            System.out.print("Ingrese el precio a ofertar: ");
            precioOfertar = Double.parseDouble(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        if (precioOfertar < subastaEncontrada.getPrecioMinimoAceptable()){
            System.out.println("Error: lo sentimos el precio a ofertar es menor al precio minimo aceptable");
            return;
        }

        System.out.println(GestorOferta.registrarOferta(usuarioColeccionista, precioOfertar, subastaEncontrada));


    }


    public static int revisarEdad(LocalDate fechaNacimiento) {
        LocalDate today = LocalDate.now();
        return Period.between(fechaNacimiento, today).getYears();
    }

    // --- INGRESAR USUARIOS --- //

    public static UsuarioVendedor ingresarUsuarioVendedor() throws IOException, SQLException, ClassNotFoundException {

        System.out.print("Ingrese su correo electronico: ");
        String correoElectronico = reader.readLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasennia = reader.readLine();

        return GestorUsuarioVendedor.ingresarUsuarioVendedor(correoElectronico, contrasennia);
    }

    public static UsuarioColeccionista ingresarUsuarioColeccionista() throws IOException, SQLException, ClassNotFoundException {

        System.out.print("Ingrese su correo electronico: ");
        String correoElectronico = reader.readLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasennia = reader.readLine();

        return GestorUsuarioColeccionista.ingresarUsuarioColeccionista(correoElectronico, contrasennia);
    }


    // --- BUSCAR USUARIO --- //

    public static boolean buscarUsuarioModerador() throws IOException, SQLException, ClassNotFoundException {

        return GestorUsuarioModerador.buscarUsuarioModerador();
    }

    public static Objeto buscarObjeto() throws IOException, SQLException, ClassNotFoundException {

        int idObjetoSubastar = 0;
        try {
            System.out.println("\nIngrese el id del objeto a añadir a la subasta:");
            idObjetoSubastar = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
        }

        return GestorObjeto.buscarObjeto(idObjetoSubastar);
    }

    public static Subasta buscarSubasta() throws IOException, SQLException, ClassNotFoundException {

        int idSubasta = 0;
        try {
            System.out.println("\nIngrese el id de la subasta a la que se va a ofertar:");
            idSubasta = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
        }

        return GestorSubasta.buscarSubasta(idSubasta);
    }

    public static Subasta buscarSubastaUsuario(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        int idSubasta = 0;
        try {
            System.out.println("\nIngrese el id de la subasta a concluir:");
            idSubasta = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
        }

        return GestorSubasta.buscarSubastaUsuario(idSubasta, usuarioVendedor);
    }

    public static Oferta buscarOfertaMayor(int idSubasta) throws SQLException, IOException, ClassNotFoundException {

        return GestorOferta.buscarOfertaMayor(idSubasta);
    }


    // --- IMPRIMIR  --- //

    public static void imprimirObjetos(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        GestorObjeto.imprimirObjetos(usuarioVendedor.getCorreoElectronico());

    }

    public static void imprimirSubastasUsuario(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        GestorSubasta.imprimirSubastasUsuario(usuarioVendedor.getCorreoElectronico());

    }

    public static void imprimirSubastasActivas() throws IOException, SQLException, ClassNotFoundException {

        GestorSubasta.imprimirSubastasActivas();

    }

    public static void imprimirObjetosColeccionista(UsuarioColeccionista usuarioColeccionista) throws IOException, SQLException, ClassNotFoundException {
        GestorObjeto.imprimirObjetosColeccionista(usuarioColeccionista.getCorreoElectronico());
    }

    // -- CONCLUIR SUBASTA -- //

    public static void concluirSubasta(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        imprimirSubastasUsuario(usuarioVendedor);

        Subasta subastaEncontrada = buscarSubastaUsuario(usuarioVendedor);

        if (subastaEncontrada == null) {
            System.out.println("Error: usted no cuenta con una subasta registrada con ese id");
            return;
        }

        Oferta ofertaMayor = buscarOfertaMayor(subastaEncontrada.getIdSubasta());

        if (ofertaMayor == null) {
            System.out.println("Error: no hay ofertas registradas para esta subasta");
            return;
        }

        // 1. Transferir objeto al ganador
        GestorObjeto.actualizarDuennoObjeto(subastaEncontrada.getIdObjetoSubastar(), ofertaMayor.getNombreOfertante());

        // 2. Eliminar ofertas de la subasta
        GestorOferta.eliminarOfertas(subastaEncontrada.getIdSubasta());

        // 3. Eliminar la subasta
        GestorSubasta.eliminarSubasta(subastaEncontrada.getIdSubasta());

        System.out.println("Subasta concluida exitosamente.");
        System.out.println("El objeto '" + subastaEncontrada.getNombreObjeto() + "' fue transferido a: " + ofertaMayor.getNombreOfertante());
    }

    public static void agregarInteres(UsuarioColeccionista usuarioColeccionista) throws IOException, SQLException, ClassNotFoundException {

        imprimirSubastasActivas();

        Subasta subastaEncontrada = buscarSubasta();

        if (subastaEncontrada == null) {
            System.out.println("Error: la subasta no fue encontrada.");
            return;
        }

        usuarioColeccionista.agregarInteres(subastaEncontrada);

        System.out.println("Interés agregado correctamente: " + subastaEncontrada.getNombreObjeto());
    }

    public static void verIntereses(UsuarioColeccionista usuarioColeccionista) {

        if (usuarioColeccionista.getIntereses().isEmpty()) {
            System.out.println("Error: usted no tiene intereses registrados.");
            return;
        }

        System.out.println("\n--- INTERESES ---");
        for (Subasta subasta : usuarioColeccionista.getIntereses()) {
            System.out.println(subasta);
        }
    }

    public static void verColeccion(UsuarioColeccionista usuarioColeccionista) throws SQLException, IOException, ClassNotFoundException {

        GestorObjeto.imprimirObjetosColeccionista(usuarioColeccionista.getCorreoElectronico());

    }
}
