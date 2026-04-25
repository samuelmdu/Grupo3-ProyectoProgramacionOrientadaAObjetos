package cr.ac.ucenfotec.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Utilidades {
    public static String[] getProperties() throws IOException {
        String[] propiedas = new String[5];
        Properties lectura = new Properties();
        String ruta = "src/cr/ac/ucenfotec/utils/bd.properties";

        try{
            lectura.load(new FileInputStream(ruta));
            propiedas[0] = lectura.getProperty("driver");
            propiedas[1] = lectura.getProperty("server");
            propiedas[2] = lectura.getProperty("database");
            propiedas[3] = lectura.getProperty("user");
            propiedas[4] = lectura.getProperty("password");
            return propiedas;

        } catch (Exception e) {
            System.out.println("Sucedio un error al cargar las credenciales de acceso a la BD");
            throw e;
        }
    }
}
