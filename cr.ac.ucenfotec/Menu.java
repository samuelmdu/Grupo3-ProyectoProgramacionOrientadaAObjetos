import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Menu {

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Subasta> listaSubastas = new ArrayList<>();
    private ArrayList<Oferta> listaOfertas = new ArrayList<>();

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

        System.out.println("ID usuario:");
        int id = sc.nextInt();

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

            UsuarioVendedor vendedor = new UsuarioVendedor(
                    nombre,id,fecha,pass,correo,puntuacion,direccion
            );

            listaUsuarios.add(vendedor);

        }else{

            UsuarioColeccionista coleccionista = new UsuarioColeccionista(
                    nombre,id,fecha,0,pass,correo,puntuacion,direccion
            );

            listaUsuarios.add(coleccionista);

        }

        System.out.println("Usuario registrado!");

    }

    public void listarUsuarios(){

        for(Usuario u : listaUsuarios){

            System.out.println(u);

        }

    }

    public void crearSubasta(){

        if(listaUsuarios.isEmpty()){

            System.out.println("Primero debe registrar usuarios.");
            return;

        }

        System.out.println("Indice del usuario creador:");
        listarUsuarios();

        int indice = sc.nextInt();

        Usuario usuario = listaUsuarios.get(indice);

        System.out.println("Precio minimo:");
        double precio = sc.nextDouble();

        LocalDateTime fecha = LocalDateTime.now().plusDays(7);

        Subasta subasta = new Subasta(fecha, usuario, precio);

        listaSubastas.add(subasta);

        System.out.println("Subasta creada!");

    }

    public void listarSubastas(){

        for(Subasta s : listaSubastas){

            System.out.println(s);

        }

    }
    public void crearOferta(){

        if(listaUsuarios.isEmpty()){

            System.out.println("Debe haber usuarios.");
            return;

        }

        System.out.println("Seleccione usuario:");

        listarUsuarios();

        int indice = sc.nextInt();

        Usuario usuario = listaUsuarios.get(indice);

        System.out.println("Precio ofertado:");
        double precio = sc.nextDouble();

        Oferta oferta = new Oferta(usuario,precio);

        listaOfertas.add(oferta);

        System.out.println("Oferta creada!");

    }
    public void listarOfertas(){

        for(Oferta o : listaOfertas){

            System.out.println(o);

        }

    }

}