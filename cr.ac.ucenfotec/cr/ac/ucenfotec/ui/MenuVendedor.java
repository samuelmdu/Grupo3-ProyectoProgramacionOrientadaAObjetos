package cr.ac.ucenfotec.ui;

import cr.ac.ucenfotec.bl.entities.Objeto;
import cr.ac.ucenfotec.bl.entities.Subasta;
import cr.ac.ucenfotec.bl.entities.UsuarioVendedor;
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

    public static void mostrarMenu(UsuarioVendedor usuarioVendedor, ArrayList<Subasta> listaSubastas) throws IOException {

        byte opcionMenuPrincipal = 0;

        do {

            System.out.println("-- MENÚ VENDEDOR --");
            System.out.println("Bienvenido " + usuarioVendedor.getNombreCompleto());
            System.out.println("1. Registrar objeto");
            System.out.println("2. Ver mis objetos registrados");
            System.out.println("3. Crear subasta");
            System.out.println("4. Ver mis subastas creadas");
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
                    System.out.println(usuarioVendedor.getListaObjetos());
                    break;

                case 3:
                    registrarSubasta(usuarioVendedor);
                    break;

                case 4:
                    System.out.println(usuarioVendedor.getListaSubastas());
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

            Subasta insertarSubasta = new Subasta(fechaVencimiento, usuarioVendedor, precioMinimoAceptable, objetoSubastar);
            usuarioVendedor.setListaSubastas(insertarSubasta);
            listaSubastas.add(insertarSubasta);
        }
        }
    }
}