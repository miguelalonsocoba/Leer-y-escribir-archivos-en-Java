
import java.io.*;

public class Escritura {
	
	public static void main(String[] args) {
		
		String texto = "Mi texto en el archivo";
		
		try {
			PrintWriter pw = new PrintWriter("mi archivo.txt");//Se crea el archivo.
			pw.println(texto);//Se escribe el contenido de la linea.
			System.out.println("Archivo creado correctamente.");
			pw.close();//Se cierra el recurso. Si no se cierra el recurso al crear el archivo se creara vacio.
		} catch (Exception e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		}
		
	}

}
