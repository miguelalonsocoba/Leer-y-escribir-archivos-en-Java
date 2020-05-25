import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class LeerArchivos.
 *
 */
public class LeerArchivo2 {
	
	/**
	 * Variable de cabecera Importe Sumatoria Moneda USD del archivo que se lee.
	 */
	private String cabeceraImporteSumatoriaMonedaUSD;
	
	/**
	 * Variable de cabecera Importe Sumatoria Moneda MXP del archivo que se lee.
	 */
	private String cabeceraImporteSumatoriaMonedaMXP;
	
	/**
	 * Variable de cabecera de Total de Registro del archivo que se lee.
	 */
	private String cabeceraTotalRegistros;
	
	/**
	 * Variable de cabecera Fecha Pago del archivo que se lee.
	 */
	private String cabeceraFechaPago;
	
	/**
	 * Variable que sirve de auxiliar, cuando la variable cabeceraFechaPago tenga valor esta variable cambiara el valor a true.
	 */
	private Boolean auxCabeceraFechaPago = false;

	/**
	 * Variable que almacenara la ruta del nuevo archivo ICE creado
	 */
	private String newFileICE;

	/**
	 * Variable que almacenara la ruta del nuevo archivo creado del segundo sistema.
	 */
	private String newOtherSistem;

	/**
	 * Variable que almacenara la ruta del archivo que se va a leer.
	 */
	private File lectureFile;

	/**
	 * Contrucor por defecto. Se inizializa la variable.
	 */
	public LeerArchivo2() {
		lectureFile = new File("c:\\priebaJava\\prueba2.txt");
	}

	/**
	 * Constructor que recive la ruta del archivo que se va a leer.
	 * 
	 * @param ruta
	 */
	public LeerArchivo2(String ruta) {
		lectureFile = new File(ruta);
	}
	
	/**
	 * Metodo que lee un archivo.
	 */
	public void readFile() {
		try {
			FileReader fr = new FileReader(lectureFile);
			BufferedReader br = new BufferedReader(fr);
			
			String linea;
			
			//Lee linea por linea del archivo...
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
				
				//Comprueba si la variable es false, si cumple asigna valores a las variables de cabecera siguientes, e imprime los valores de las variables.
				if (auxCabeceraFechaPago == false) {
					//Asigna el valor a cabeceraFechaPago.
					cabeceraFechaPago = linea.substring(4, 12);
					System.out.println("Cabecera Fecha Pago: " + cabeceraFechaPago);
					
					//Aseigna valor a cabeceraTotalRegistros.
					cabeceraTotalRegistros = linea.substring(12, 17);
					System.out.println("Cabecera Total Registros: " + cabeceraTotalRegistros);
					
					//Aseigna valor a cabeceraImporteSumatoriaMonedaMXP.
					cabeceraImporteSumatoriaMonedaMXP = linea.substring(17, 33);
					System.out.println("Cabecera Importe Sumatoria Moneda MXP: " + cabeceraImporteSumatoriaMonedaMXP);
					
					//Asigna valor a cabeceraImporteSumatoriaMonedaUSD.
					cabeceraImporteSumatoriaMonedaUSD = linea.substring(37, 53);
					System.out.println("Cabecera Importe Sumatoria Moneda USD: " + cabeceraImporteSumatoriaMonedaUSD);
					
					auxCabeceraFechaPago = true;
				}
				
			}	
			
			//Se cierran los recursos que se utilizan.
			br.close();
			fr.close();
			
		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser leido... " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Ocurrió un error leyendo el archivo: " + e);
		}
	}
	
	/**
	 * Metodo principal de la aplicación.
	 * @param mac
	 */
	public static void main(String... mac) {
		//Se utiliza el constructor con parametro.
		LeerArchivo2 object = new LeerArchivo2("c:\\pruebaJava\\prueba2.txt");
		
		object.readFile();
	}

}
