package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioColeccionista;

import java.io.IOException;
import java.util.ArrayList;

import static cr.ac.ucenfotec.ui.MenuAdministrador.listaSubastas;
import static cr.ac.ucenfotec.ui.MenuAdministrador.reader;

public class MenuColeccionista {

    public static void mostrarMenu(UsuarioColeccionista usuarioColeccionista, ArrayList<Subasta> listaSubastas) throws IOException {

        byte opcionMenuPrincipal = 0;

        do {

            System.out.println("-- MENÚ COLECCIONISTA --");
            System.out.println("Bienvenido " + usuarioColeccionista.getNombreCompleto());
            System.out.println("1. Ver subastas activas");
            System.out.println("2. Crear oferta"); // La oferta más alta al final se lleva el objeto. (Si la gana restar de los objetos del usuario vendedor).
            System.out.println("3. Añadir intereses"); // Añade a intereses los objetos de subastas activas. (No restar de los objetos del usuario vendedor).
            System.out.println("4. Ver intereses"); // Ver intereses en coleccionista.listaIntereses
            System.out.println("5. Crear subasta"); // Repetir la lógica en MenuVendedor de Crear subasta. (Restar de la lista de objetos del coleccionista).
            System.out.println("0. Cerrar sesión");

            opcionMenuPrincipal = Byte.parseByte(reader.readLine());

            switch(opcionMenuPrincipal){

                case 1:
                    listarSubastasActivas();
                    break;

                case 2:

                    break;
                case 3:

                    break;
                case 4:

                    break;

                case 0:
                    System.out.println("Cerrando sesión.");
                    break;

                default:
                    System.out.println("Error: Opción ingresa inválida.");
            }

        }while(opcionMenuPrincipal != 0);
    }
    private static void listarSubastasActivas() throws IOException {
        if (listaSubastas.isEmpty()) {
            System.out.println("Error: Lo sentimos no hay subastas registradas.");
        } else {
            System.out.println(listaSubastas);
        }
    }
}
