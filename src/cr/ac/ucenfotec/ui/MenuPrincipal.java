package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;
import cr.ac.ucenfotec.bl.entities.UsuarioModerador;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;
import cr.ac.ucenfotec.dl.Controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;


public class MenuPrincipal {

    public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static UsuarioVendedor usuarioActualVendedor;
    private static UsuarioColeccionista usuarioActualColeccionista;

    public static void mostrarMenu() throws IOException, SQLException, ClassNotFoundException {

        boolean usuarioModerador = Controlador.buscarUsuarioModerador();

        if (!usuarioModerador){
            Controlador.registrarUsuarioModerador();
        }

        byte opcionMenuPrincipal = 0;

        do {

            System.out.println("\n--- MENÚ PRINCIPAL ---");
            System.out.println("1. Registrar vendedor");
            System.out.println("2. Registrar coleccionista");
            System.out.println("3. Iniciar sesión");
            System.out.println("0. Salir");

            try {
                opcionMenuPrincipal = Byte.parseByte(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(new ExceptionNumeroInvalido().getMessage());
                return;
            }

            switch (opcionMenuPrincipal) {

                case 1:
                    Controlador.registrarUsuarioVendedor();
                    break;

                case 2:
                    Controlador.registrarColeccionista();
                    break;

                case 3:
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

    private static void iniciarSesion() throws IOException, SQLException, ClassNotFoundException {

        byte opcionCuenta = 0;
        try {
            System.out.println("Ingrese el tipo de cuenta con la que desea ingresar (1. Vendedor 2. Coleccionista)");
            opcionCuenta = Byte.parseByte(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        switch (opcionCuenta){

            case 1:
                UsuarioVendedor usuarioVendedor = Controlador.ingresarUsuarioVendedor();

                if (usuarioVendedor == null){
                    System.out.println("Error: Autenticación invalida");
                    break;
                }
                usuarioActualVendedor = usuarioVendedor;
                MenuVendedor.mostrarMenu(usuarioActualVendedor);
                break;

            case 2:
                UsuarioColeccionista usuarioColeccionista = Controlador.ingresarUsuarioColeccionista();

                if (usuarioColeccionista == null){
                    System.out.println("Error: Autenticación invalida");
                    break;
                }
                usuarioActualColeccionista = usuarioColeccionista;
                MenuColeccionista.mostrarMenu(usuarioActualColeccionista);

                break;

            default:
                System.out.println("Opción no válida.");
                break;
        }

    }




}