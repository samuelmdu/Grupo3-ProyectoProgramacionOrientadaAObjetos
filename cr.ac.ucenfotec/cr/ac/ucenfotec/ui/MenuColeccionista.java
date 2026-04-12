package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.*;

import java.io.IOException;
import java.util.ArrayList;

import static cr.ac.ucenfotec.ui.MenuAdministrador.listaSubastas;
import static cr.ac.ucenfotec.ui.MenuAdministrador.reader;

public class MenuColeccionista {

    public static void mostrarMenu(UsuarioColeccionista usuarioColeccionista) throws IOException {

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
                    listarSubastasActivas();
                    break;

                case 2:
                    crearOferta(usuarioColeccionista);
                    break;

                case 3:
                    agregarInteres(usuarioColeccionista);
                    break;

                case 4:
                    verIntereses(usuarioColeccionista);
                    break;
                case 5:
                    verListaObjetos(usuarioColeccionista);
                    break;
                case 0:
                    System.out.println("Cerrando sesión.");
                    break;

                default:
                    System.out.println("Error: Opción inválida.");
            }

        } while(opcionMenuPrincipal != 0);
    }

    private static void listarSubastasActivas() {
        if (listaSubastas.isEmpty()) {
            System.out.println("No hay subastas registradas.");
        } else {
            for (Subasta s : listaSubastas) {
                System.out.println(s);
            }
        }
    }

    private static void crearOferta(UsuarioColeccionista usuario) throws IOException {

        listarSubastasActivas();

        if (listaSubastas.isEmpty()) return;

        System.out.print("Ingrese ID de la subasta: ");
        int id = Integer.parseInt(reader.readLine());

        Subasta seleccionada = null;

        for (Subasta s : listaSubastas) {
            if (s.getIdSubasta() == id) {
                seleccionada = s;
            }
        }

        if (seleccionada == null) {
            System.out.println("Error: Subasta no encontrada.");
            return;
        }

        System.out.print("Ingrese monto a ofertar: ");
        double monto = Double.parseDouble(reader.readLine());

        if (monto < seleccionada.getprecioMinimoAceptable()) {
            System.out.println("Error: La oferta debe ser mayor al precio mínimo.");
        } else {
            Oferta nueva = new Oferta(usuario, monto);
            seleccionada.agregarOferta(nueva);

            System.out.println("Oferta realizada correctamente");
        }
    }

    private static void agregarInteres(UsuarioColeccionista usuario) throws IOException {

        listarSubastasActivas();

        if (listaSubastas.isEmpty()) return;

        System.out.print("Ingrese ID de la subasta: ");
        int id = Integer.parseInt(reader.readLine());

        for (Subasta s : listaSubastas) {
            if (s.getIdSubasta() == id) {
                usuario.agregarInteres(s);
                System.out.println("Agregado a intereses");
                return;
            }
        }

        System.out.println("Subasta no encontrada.");
    }

    private static void verIntereses(UsuarioColeccionista usuario) {

        if (usuario.getListaIntereses().isEmpty()) {
            System.out.println("Error: usted no cuenta con intereses guardados.");
        } else {
            for (Subasta s : usuario.getListaIntereses()) {
                System.out.println(s);
            }
        }
    }

    private static void verListaObjetos(UsuarioColeccionista usuario) {

        if (usuario.getListaObjetos().isEmpty()) {
            System.out.println("Error: usted no cuenta con objetos guardas.");
        } else {
            System.out.println(usuario.getListaObjetos());
        }
    }

}