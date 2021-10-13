/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mundos;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author Daniel Parra
 */
public class PropiedadesMundos {

    public static Properties propiedades;

    public static void cargarPropiedades() {
        propiedades = new Properties();
        InputStream entrada = null;
        try {
            //Entrada de flujo de datos
            entrada = new FileInputStream("src/sources/mundos.properties");
            //Cargar propiedades
            propiedades.load(entrada);
            
            //System.out.println(propiedades.getProperty("miNave"));

        } catch (IOException ex) {
            System.out.println(ex);
        } finally {
            if (entrada != null) {
                try {
                    entrada.close();
                } catch (IOException e) {

                    System.out.println(e);

                }

            }
        }

    }

}
