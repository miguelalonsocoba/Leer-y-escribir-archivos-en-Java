
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Escritura {
	
	public static void main(String[] args) {
		
		String texto = "Mi texto en el archivo";
		
		List<String> valores = new ArrayList<>();
		valores.add("02          ICESARB0000012345           000000000897650MXP");
		valores.add("02          ICESAPO0000012345           000000000897650MXP");
		valores.add("02          ICESARB0000012345           000000000897650USD");
		valores.add("02          BKLSARB0000012340           000000000705880MXP");
		
		try (PrintWriter pw = new PrintWriter("mi archivo.txt")) {//Se crea el archivo.
//			pw.println(texto);//Se escribe el contenido de la linea.
			for (String string : valores) {
				pw.write(string + "\n");
			}
//			System.out.println("Archivo creado correctamente.");
			pw.write("Hola");
			pw.close();//Se cierra el recurso. Si no se cierra el recurso al crear el archivo se creara vacio.
		} catch (Exception e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		}
		
	}

}
