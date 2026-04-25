package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;
import cr.ac.ucenfotec.dl.Controlador;

import java.io.IOException;
import java.sql.SQLException;

import static cr.ac.ucenfotec.ui.MenuPrincipal.reader;

public class MenuVendedor {

    public static void mostrarMenu(UsuarioVendedor usuarioVendedor) throws IOException, SQLException, ClassNotFoundException {

        byte opcionMenuPrincipal = 0;

        do {

            System.out.println("-- MENÚ VENDEDOR --");
            System.out.println("Bienvenido " + usuarioVendedor.getNombreCompleto());
            System.out.println("1. Registrar objeto");
            System.out.println("2. Ver mis objetos registrados");
            System.out.println("3. Crear subasta");
            System.out.println("4. Ver mis subastas creadas");
            System.out.println("5. Concluir subasta");
            System.out.println("0. Cerrar sesión");

            try {
                opcionMenuPrincipal = Byte.parseByte(reader.readLine());
            } catch (NumberFormatException e) {
                System.out.println(new ExceptionNumeroInvalido().getMessage());
                continue;
            }

            switch (opcionMenuPrincipal) {

                case 1:
                    Controlador.registrarObjeto(usuarioVendedor);
                    break;

                case 2:
                    Controlador.imprimirObjetos(usuarioVendedor);
                    break;

                case 3:
                    Controlador.registrarSubasta(usuarioVendedor);
                    break;

                case 4:
                    Controlador.imprimirSubastasUsuario(usuarioVendedor);
                    break;

                case 5:
                    Controlador.concluirSubasta(usuarioVendedor);
                    break;

                case 0:
                    System.out.println("Cerrando sesión.");
                    break;

                default:
                    System.out.println("Error: Opción ingresada inválida.");
            }

        } while (opcionMenuPrincipal != 0);
    }
}


