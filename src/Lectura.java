
import java.io.*;

public class Lectura {
	
	public static void main(String[] args) {
		
		String texto;
		
		try {
			FileReader fr = new FileReader("archivo.txt");//Se asigna la ruta donde se encuentra el archivo y lee el archivo desde una ruta externa.
			BufferedReader br = new BufferedReader(fr);//Clase que permite leer información desde un Buffer. Buffer es memoria temporal que se graba para acceder a la información mas rapido.
			texto = br.readLine();
			System.out.println(texto);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error al leer archivo: " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error al leer una linea del archivo: " + e.getMessage());
		}
	}

}
