import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main (String[]args) {

        String Directorio = "src/Directorio";
        String Archivo = "archivo.txt";
        String Texto = "texto";

        ArrayList<String> Lista = new ArrayList<String>();
        Lista.add("Perro");
        Lista.add("Gato");
        Lista.add("Juan");
        Lista.add("Daniel");
        Lista.add("Juan");
        Lista.add("Gato");
        Lista.add("Perro");
        Lista.add("Camila");
        Lista.add("Daniel");
        Lista.add("Camila");


        crearArchivo(Directorio, Archivo, Lista);
        buscarTexto(Directorio, Archivo, Texto);
    }

    public static void crearArchivo (String nombreDirectorio, String nombreArchivo, ArrayList < String > lista){
        File directorio = new File(nombreDirectorio);
        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                    System.out.printf("Directorio creado como %s\n", directorio.getName());
            } else {
                    System.out.println("Error al crear directorio");
                    return;
            }
        } else {
            System.out.println("El directorio ya existe");
        }

        File f = new File(directorio.getAbsolutePath() + "/" + nombreArchivo);

        try (FileWriter fw = new FileWriter(f);
             BufferedWriter bw = new BufferedWriter(fw);) {
            Iterator<String> it = lista.iterator();
            while (it.hasNext()) {
                bw.write(it.next());
                bw.newLine();
            }
            bw.close();
            System.out.println("Se han cargado archivo y textos!");
        } catch (IOException e) {
            System.out.println("Error al crear el archivo");
            e.printStackTrace();
        }
    }

    public static void buscarTexto (String directorio, String archivo, String texto){
        Scanner sc = new Scanner(System.in);
        File f = new File(directorio + "/" + archivo);

        if (!f.exists()) {
            System.out.println("El archivo ingresado no existe");
            return;
        }


        int count = 0;

        System.out.println("====================");
        System.out.println("Ingrese una palabra: ");
        texto = sc.nextLine();

        try (FileReader fr = new FileReader(f);
             BufferedReader br = new BufferedReader(fr);) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equals(texto)) {
                    count++;
                }
            }
            System.out.println("=======================================================");
            System.out.printf("Cantidad de repeticiones del texto: %d%n\n", count);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo");
            e.printStackTrace();
        }
    }
}
