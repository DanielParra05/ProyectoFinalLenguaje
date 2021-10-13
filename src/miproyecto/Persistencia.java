package miproyecto;

import java.io.*;

public class Persistencia {

    public static File archivo;

    public Persistencia() {

        archivo = new File("src/sources/jugadores.bin");
 
    }

    public static File getArchivo() {
        return archivo;
    }

    public static void escribirObjeto(Object obj) throws Exception {
        
        ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(archivo));

        escritor.writeObject(obj);
        escritor.flush();
        escritor.close();

    }

    public static Object leerObjeto() throws IOException, FileNotFoundException, ClassNotFoundException{
       
        ObjectInputStream lector = new ObjectInputStream(new FileInputStream(archivo));

        Object obj = lector.readObject();
        lector.close();

        return obj;
    }

}
