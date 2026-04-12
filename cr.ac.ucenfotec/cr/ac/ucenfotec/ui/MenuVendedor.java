package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.*;
import cr.ac.ucenfotec.bl.exceptions.ExceptionFechaInvalida;
import cr.ac.ucenfotec.bl.exceptions.ExceptionNumeroInvalido;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static cr.ac.ucenfotec.ui.MenuAdministrador.listaSubastas;
import static cr.ac.ucenfotec.ui.MenuAdministrador.reader;

public class MenuVendedor {

    public static void mostrarMenu(UsuarioVendedor usuarioVendedor) throws IOException {

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
                    registrarObjeto(usuarioVendedor);
                    break;

                case 2:

                    if (usuarioVendedor.getListaObjetos().isEmpty()){
                        System.out.println("Error: usted no cuenta con subastas creadas.");
                    }
                    System.out.println(usuarioVendedor.getListaObjetos());
                    break;


                case 3:
                    registrarSubasta(usuarioVendedor);
                    break;

                case 4:
                    if (usuarioVendedor.getListaSubastas().isEmpty()){
                        System.out.println("Error: usted no cuenta con subastas creadas.");
                    }
                    System.out.println(usuarioVendedor.getListaSubastas());
                    break;
                case 5:
                    concluirSubasta(usuarioVendedor);
                    break;


                case 0:
                    System.out.println("Cerrando sesión.");
                    break;

                default:
                    System.out.println("Error: Opción ingresada inválida.");
            }

        } while (opcionMenuPrincipal != 0);
    }

    private static void registrarObjeto(UsuarioVendedor usuarioVendedor) throws IOException {

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

        Objeto insertarObjeto = new Objeto(nombre, descripcion, fechaCompra, estado);
        usuarioVendedor.setListaObjetos(insertarObjeto);
    }

    private static void registrarSubasta(UsuarioVendedor usuarioVendedor) throws IOException {

        if (usuarioVendedor.getListaObjetos().isEmpty()){
            System.out.println("Error: lo sentimos usted no cuenta con objetos registrados.");
        } else{

        ArrayList<Objeto> listaObjetos = usuarioVendedor.getListaObjetos();
        Objeto objetoSubastar = new Objeto();
        boolean objetoEncontrado = false;

        System.out.println(listaObjetos);

        int id = 0;
        try {
            System.out.println("\nIngrese el id del objeto a añadir a la subasta:");
            id = Integer.parseInt(reader.readLine());
        } catch (NumberFormatException e) {
            System.out.println(new ExceptionNumeroInvalido().getMessage());
            return;
        }

        for (int i = 0; i < listaObjetos.size(); i++) {
            Objeto temp = listaObjetos.get(i);
            if (temp.getIdObjeto() == id) {
                objetoSubastar = temp;
                objetoEncontrado = true;
            }
        }

        if (!objetoEncontrado) {
            System.out.println("Error: No se encontró el objeto para la subasta.");
        } else {
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

            // ACA
            Subasta insertarSubasta = new Subasta(fechaVencimiento, usuarioVendedor, precioMinimoAceptable, objetoSubastar);
            usuarioVendedor.setListaSubastas(insertarSubasta);
            listaSubastas.add(insertarSubasta);
        }
        }
    }

    private static void concluirSubasta(UsuarioVendedor usuarioVendedor) throws IOException {

        if (usuarioVendedor.getListaSubastas().isEmpty()) {
            System.out.println("Error: usted no cuenta con subastas creadas.");
            return;
        }

        System.out.println(usuarioVendedor.getListaSubastas());
        System.out.println("\n");

        byte opcSubasta = 0;

        try {
            System.out.println("Ingrese el id de la subasta a concluir.");
            opcSubasta = Byte.parseByte(reader.readLine());
        } catch (IOException e) {
            throw new ExceptionNumeroInvalido();
        }

        for (int i = 0; i < usuarioVendedor.getListaSubastas().size(); i++) {

            Subasta tempSubasta = usuarioVendedor.getListaSubastas().get(i);

            if (tempSubasta.getIdSubasta() == opcSubasta) {

                if (tempSubasta.getListaOfertas().isEmpty()) {
                    System.out.println("Error: no hay ofertas asociadas a esta subasta.");
                    return;
                }

                double mayor = 0;
                Oferta mayorOferta = null;

                for (int j = 0; j < tempSubasta.getListaOfertas().size(); j++) {
                    Oferta ofertaActual = tempSubasta.getListaOfertas().get(j);

                    if (ofertaActual.getPrecioOfertado() > mayor) {
                        mayor = ofertaActual.getPrecioOfertado();
                        mayorOferta = ofertaActual;
                    }
                }

                System.out.println("Ganador: " + mayorOferta.getNombreOfertante());
                mayorOferta.getUsuarioColeccionista().setListaObjetos(tempSubasta.getObjetoSubastar());

                listaSubastas.remove(i);
                usuarioVendedor.getListaSubastas().remove(i);
                usuarioVendedor.getListaObjetos().remove(i);
                break;
            }
        }
    }
}


