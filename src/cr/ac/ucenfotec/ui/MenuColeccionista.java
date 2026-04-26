package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.dl.Controlador;

import java.io.IOException;
import java.sql.SQLException;

import static cr.ac.ucenfotec.ui.MenuPrincipal.reader;

public class MenuColeccionista {

    public static void mostrarMenu(UsuarioColeccionista usuarioColeccionista) throws IOException, SQLException, ClassNotFoundException {

        byte opcionMenuPrincipal = 0;

        do {

            System.out.println("-- MENÚ COLECCIONISTA --");
            System.out.println("Bienvenido " + usuarioColeccionista.getNombreCompleto());
            System.out.println("1. Ver subastas activas");
            System.out.println("2. Crear oferta");
            System.out.println("3. Añadir intereses");
            System.out.println("4. Ver intereses");
            System.out.println("5. Ver coleccion de objetos");
            System.out.println("0. Cerrar sesión");

            opcionMenuPrincipal = Byte.parseByte(reader.readLine());

            switch(opcionMenuPrincipal){

                case 1:
                    Controlador.imprimirSubastasActivas();
                    break;

                case 2:
                    Controlador.registrarOferta(usuarioColeccionista);
                    break;

                case 3:
                    Controlador.agregarInteres(usuarioColeccionista);
                    break;

                case 4:
                    Controlador.verIntereses(usuarioColeccionista);
                    break;

                case 5:
                    Controlador.verColeccion(usuarioColeccionista);
                    break;

                case 0:
                    System.out.println("Cerrando sesión.");
                    break;

                default:
                    System.out.println("Error: Opción inválida.");
            }

        } while(opcionMenuPrincipal != 0);
    }
}