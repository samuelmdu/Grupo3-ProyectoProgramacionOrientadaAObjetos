package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.entities.UsuarioModerador;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;
import cr.ac.ucenfotec.bl.exceptions.ExceptionFechaInvalida;
import cr.ac.ucenfotec.bl.exceptions.ExceptionMayorEdad;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class MenuAdministrador {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static boolean moderadorCreado = false;
    private static UsuarioVendedor usuarioActualVendedor;
    private static UsuarioColeccionista usuarioActualColeccionista;
    private static ArrayList<UsuarioVendedor> listaVendedores = new ArrayList<>();
    private static ArrayList<UsuarioColeccionista> listaColeccionistas = new ArrayList<>();
    public static ArrayList<Subasta> listaSubastas = new ArrayList<>();

    public static void mostrarMenu() throws IOException {

        // USUARIOS DE PRUEBA
//        UsuarioModerador usuarioModerador = new UsuarioModerador("samuel", LocalDate.of(2002, 10, 17), "123", "samuel@gmail.com");
//        moderadorCreado = true;
//
//        UsuarioVendedor samuel = new UsuarioVendedor("Samuel", LocalDate.of(2002, 10, 17), "123", "samuel", 200, "San joaquin");
//        listaVendedores.add(samuel);
//
//        UsuarioColeccionista samuelCole = new UsuarioColeccionista("Samuel", LocalDate.of(2002, 10, 17), "123", "samuel", 200, "San joaquin");
//
//        listaColeccionistas.add(samuelCole);
//


        if (!moderadorCreado) {

            System.out.println("Error: Lo sentimos, no hay un moderador creado en el sistema.");
            System.out.println("Por favor ingrese la información a continuación para crear un moderador -> ");

            System.out.print("\nIngrese su nombre completo: ");
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

            System.out.print("Ingrese su correo electrónico: ");
            String correoElectronico = reader.readLine();

            if (revisarEdad(fechaNacimiento) < 18) {
                System.out.println(new ExceptionMayorEdad().getMessage());

            } else {
                UsuarioModerador usuarioModeradorInsertar = new UsuarioModerador(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico);
                System.out.println("El moderador fue creado exitosamente");
                moderadorCreado = true;
            }
        }

        if (moderadorCreado) {

            byte opcionMenuPrincipal = 0;
            do {

                System.out.println("\n--- MENÚ ADMIN ---");
                System.out.println("1. Registrar vendedor");
                System.out.println("2. Listar usuarios vendedores");
                System.out.println("3. Registrar coleccionista");
                System.out.println("4. Listar usuarios coleccionista");
                System.out.println("5. Iniciar sesión");
                System.out.println("0. Salir");

                try {
                    opcionMenuPrincipal = Byte.parseByte(reader.readLine());
                } catch (NumberFormatException e) {
                    System.out.println(new ExceptionNumeroInvalido().getMessage());
                    return;
                }

                switch (opcionMenuPrincipal) {

                    case 1:
                        registrarVendedor();
                        break;

                    case 2:
                        listarUsariosVendedores();
                        break;

                    case 3:
                        registrarColeccionista();
                        break;

                    case 4:
                        listarUsuariosColeccionistas();
                        break;

                    case 5:
                        iniciarSesion();
                        break;

                    case 0:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Error: Opción ingresada inválida.");
                }

            } while (opcionMenuPrincipal != 0);
        }
    }

    private static void registrarVendedor() throws IOException {

        System.out.print("\nIngrese su correo electrónico: ");
        String correoElectronico = reader.readLine();

        if (correoExiste(correoElectronico, (byte) 1)) {
            System.out.println("Error: Ya existe un vendedor registrado con ese correo electrónico.");
            return;
        }

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
        } else {
            UsuarioVendedor insertarVendedor = new UsuarioVendedor(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion);
            listaVendedores.add(insertarVendedor);
            System.out.println("El vendedor fue creado exitosamente");
        }
    }

    private static void listarUsariosVendedores() throws IOException {
        if (listaVendedores.isEmpty()) {
            System.out.println("Error: Lo sentimos no hay usuarios vendedores registrados.");
        } else {
            System.out.println(listaVendedores);
        }
    }

    private static void registrarColeccionista() throws IOException {

        System.out.print("\nIngrese su correo electrónico: ");
        String correoElectronico = reader.readLine();

        if (correoExiste(correoElectronico, (byte) 2)) {
            System.out.println("Error: Ya existe un coleccionista registrado con ese correo electrónico.");
            return;
        }

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
        } else {
            UsuarioColeccionista insertarColeccionista = new UsuarioColeccionista(nombreCompleto, fechaNacimiento, contrasenna, correoElectronico, puntuacion, direccion);
            listaColeccionistas.add(insertarColeccionista);
            System.out.println("El coleccionista fue creado exitosamente");
        }
    }

    private static void listarUsuariosColeccionistas() throws IOException {
        if (listaColeccionistas.isEmpty()) {
            System.out.println("Error: Lo sentimos no hay usuarios coleccionistas registrados.");
        } else {
            System.out.println(listaColeccionistas);
        }
    }

    private static void iniciarSesion() throws IOException {

        System.out.print("Ingrese su correo electronico: ");
        String correoElectronico = reader.readLine();

        System.out.print("Ingrese su contraseña: ");
        String contrasennia = reader.readLine();

        byte opcionCuenta = 0;
        try {
            System.out.println("Ingrese el tipo de cuenta con la que desea ingresar (1. Vendedor 2. Coleccionista)");
            opcionCuenta = Byte.parseByte(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        if (opcionCuenta == 1) {
            for (UsuarioVendedor temp : listaVendedores) {
                if (temp.getCorreoElectronico().equalsIgnoreCase(correoElectronico) && temp.getContrasenna().equalsIgnoreCase(contrasennia)) {
                    usuarioActualVendedor = temp;
                    MenuVendedor.mostrarMenu(usuarioActualVendedor);
                }
            }
        }

        if (opcionCuenta == 2) {
            for (UsuarioColeccionista temp : listaColeccionistas) {
                if (temp.getCorreoElectronico().equalsIgnoreCase(correoElectronico) && temp.getContrasenna().equalsIgnoreCase(contrasennia)) {
                    usuarioActualColeccionista = temp;
                    MenuColeccionista.mostrarMenu(usuarioActualColeccionista);
                }
            }
        }
    }

    public static int revisarEdad(LocalDate fechaNacimiento) {
        LocalDate today = LocalDate.now();
        return Period.between(fechaNacimiento, today).getYears();
    }

    private static boolean correoExiste(String correo, byte tipoUsuario) {
        if (tipoUsuario == 1) {
            for (UsuarioVendedor v : listaVendedores) {
                if (v.getCorreoElectronico().equals(correo)) return true;
            }
        } else if (tipoUsuario == 2) {
            for (UsuarioColeccionista c : listaColeccionistas) {
                if (c.getCorreoElectronico().equals(correo)) return true;
            }
        }
        return false;
    }
}