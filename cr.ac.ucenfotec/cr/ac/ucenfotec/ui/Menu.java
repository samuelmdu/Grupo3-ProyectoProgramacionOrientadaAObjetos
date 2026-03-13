package cr.ac.ucenfotec.ui;
import cr.ac.ucenfotec.bl.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Menu {

    ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    ArrayList<Subasta> listaSubastas = new ArrayList<>();
    ArrayList<Oferta> listaOfertas = new ArrayList<>();
    boolean usuarioEncontrado = false;

    Scanner sc = new Scanner(System.in);

    public void mostrarMenu(){

        int opcion = 0;

        do {

            System.out.println("\nMENU SUBASTAS");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Listar usuarios");
            System.out.println("3. Crear subasta");
            System.out.println("4. Listar subastas");
            System.out.println("5. Crear oferta");
            System.out.println("6. Listar ofertas");
            System.out.println("7. Salir");

            opcion = sc.nextInt();

            switch(opcion){

                case 1:
                    registrarUsuario();
                    break;

                case 2:
                    listarUsuarios();
                    break;

                case 3:
                    crearSubasta();
                    break;

                case 4:
                    listarSubastas();
                    break;

                case 5:
                    crearOferta();
                    break;

                case 6:
                    listarOfertas();
                    break;

            }

        }while(opcion != 7);

    }

    public void registrarUsuario(){

        sc.nextLine();

        System.out.println("Nombre completo:");
        String nombre = sc.nextLine();

        System.out.println("Año nacimiento:");
        int anio = sc.nextInt();

        System.out.println("Mes nacimiento:");
        int mes = sc.nextInt();

        System.out.println("Dia nacimiento:");
        int dia = sc.nextInt();

        sc.nextLine();

        System.out.println("Contraseña:");
        String pass = sc.nextLine();

        System.out.println("Correo:");
        String correo = sc.nextLine();

        System.out.println("Tipo usuario:");
        System.out.println("1. Vendedor");
        System.out.println("2. Coleccionista");

        int tipo = sc.nextInt();

        System.out.println("Puntuacion:");
        int puntuacion = sc.nextInt();

        sc.nextLine();

        System.out.println("Direccion:");
        String direccion = sc.nextLine();

        LocalDate fecha = LocalDate.of(anio, mes, dia);

        if(tipo == 1){

            UsuarioVendedor vendedor = new UsuarioVendedor(nombre,fecha,pass,correo,puntuacion,direccion);

            listaUsuarios.add(vendedor);

        }else{

            UsuarioColeccionista coleccionista = new UsuarioColeccionista(nombre,fecha,0,pass,correo,puntuacion,direccion);

            listaUsuarios.add(coleccionista);

        }

        System.out.println("El usuario fue registrado correctamente.");

    }

    public void listarUsuarios(){

        if (listaUsuarios.isEmpty()){
            System.out.println("Error: Vuelvalo a intentar, no se encontró ningun usuario registrado en el sistema.");
            return;
        }else{
            for(Usuario u : listaUsuarios){

                System.out.println(u);

            }
        }

    }

    public void crearSubasta(){

        Usuario usuario = new Usuario(){};

        if(listaUsuarios.isEmpty()){

            System.out.println("Error: Vuelvalo a intentar, primero debe registrar usuarios en el sistema.");
            return;

        }

        listarUsuarios();
        System.out.println("ID del usuario creador:");

        int id = sc.nextInt();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuarioTemp = listaUsuarios.get(i);

            if (usuarioTemp.getIdUsuario() == id){
                usuarioEncontrado = true;
                usuario = usuarioTemp;
            }
        }

        System.out.println("Precio minimo:");
        double precio = sc.nextDouble();

        LocalDateTime fecha = LocalDateTime.now().plusDays(7);

        Subasta subasta = new Subasta(fecha, usuario, precio);

        listaSubastas.add(subasta);

        System.out.println("La subasta fue creatada correctamente.");

    }

    public void listarSubastas(){

        if (listaSubastas.isEmpty()){
            System.out.println("Error: Vuelvalo a intentar, no se encontró ninguna subasta registrada en el sistema.");
            return;
        } else{
            for(Subasta s : listaSubastas){

                System.out.println(s);

            }
        }


    }
    public void crearOferta(){

        Usuario usuario = new Usuario(){};

        if(listaUsuarios.isEmpty()){

            System.out.println("Error: Vuelvalo a intentar, primero debe registrar usuarios en el sistema.");
            return;

        }

        listarUsuarios();

        System.out.println("Seleccione el ID del usuario para crear la oferta:");
        int id = sc.nextInt();

        for (int i = 0; i < listaUsuarios.size(); i++) {
            Usuario usuarioTemp = listaUsuarios.get(i);

            if (usuarioTemp.getIdUsuario() == id){
                usuarioEncontrado = true;
                usuario = usuarioTemp;
            }
        }

        System.out.println("Precio ofertado:");
        double precio = sc.nextDouble();

        Oferta oferta = new Oferta(usuario,precio);

        listaOfertas.add(oferta);

        System.out.println("La oferta fue creada correctamente.");

    }
    public void listarOfertas(){

        if (listaOfertas.isEmpty()){
            System.out.println("Error: Vuelvalo a intentar, no se encontró ninguna oferta creada en el sistema.");
        }
        for(Oferta o : listaOfertas){

            System.out.println(o);

        }

    }

}