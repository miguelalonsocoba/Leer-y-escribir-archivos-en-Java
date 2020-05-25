import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class LeerArchivos.
 *
 */
public class LeerArchivo2 {

	/**
	 * Almacenara el total de registros de ICE.
	 */
	private Integer totalRegistrosICE;

	/**
	 * Representa el monto total de moneda Extranjera.
	 */
	private Long montoTotalUSD;

	/**
	 * Representa el monto total de moneda nacional.
	 */
	private Long montoTotalMXP;

	/**
	 * Lista que almacenara los montos de moneda nacional.
	 */
	private List<Long> sumatoriaMontoMXP;

	/**
	 * Lista que almacenara los montos de moneda extranjera.
	 */
	private List<Long> sumatoriaMontoExt;

	/**
	 * Nombre del archivo nuevo de ICE
	 */
	private String nombreArchivoNuevoICE;

	/**
	 * Nombre del archivo nuevo de BKL.
	 */
	private String nombreArchivoNuevoBKL;

	/**
	 * Almacena todos los registros existentes en el archivo que sera leido.
	 */
	private List<String> registrosICE;

	/**
	 * Almacena todos los registros existentes en el archivo que sera leido.
	 */
	private List<String> registrosBKL;

	/**
	 * Variable para crear archivo.
	 */
	private PrintWriter pw;

	/**
	 * Constante.
	 */
	private final String ICE = "ICE";

	/**
	 * Costante.
	 */
	private final String BKL = "BKL";

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
	 * Variable que sirve de auxiliar, cuando la variable cabeceraFechaPago tenga
	 * valor esta variable cambiara el valor a true.
	 */
	private Boolean auxCabeceraFechaPago;

	/**
	 * Variable que almacenara la ruta del nuevo archivo ICE creado
	 */
	private String newFileICE;

	/**
	 * Variable que al crearse el nuevo archivo newFileIce cambiara el valor a true.
	 */
	private Boolean auxNewFileICE;

	/**
	 * Variable que almacenara la ruta del nuevo archivo creado del segundo sistema.
	 */
	private String newFileBKL;

	/**
	 * Variable que al crearse el nuevo archivo newFileBKL cambiara el valor a true.
	 */
	private Boolean auxNewFileBKL;

	/**
	 * Variable que almacenara la ruta del archivo que se va a leer.
	 */
	private File lectureFile;

	/**
	 * Variable que almacenara linea por linea del archivo leido.
	 */
	private String linea;

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
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = "Nuevo Archivo ICE.txt";
		nombreArchivoNuevoBKL = "Nuevo Archivo BKL.txt";
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		totalRegistrosICE = 0;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
	}

	/**
	 * Metodo que lee un archivo.
	 */
	public void readFile() {
		try {
			FileReader fr = new FileReader(lectureFile);
			BufferedReader br = new BufferedReader(fr);

			// Lee linea por linea del archivo...
			while ((linea = br.readLine()) != null) {

				// Comprueba si la variable es false, si cumple asigna valores a las variables
				// de cabecera siguientes, e imprime los valores de las variables.
				if (auxCabeceraFechaPago == false) {
					// Asigna el valor a cabeceraFechaPago.
					cabeceraFechaPago = linea.substring(4, 12);
					System.out.println("Cabecera Fecha Pago: " + cabeceraFechaPago);

					// Aseigna valor a cabeceraTotalRegistros.
					cabeceraTotalRegistros = linea.substring(12, 17);
					System.out.println("Cabecera Total Registros: " + cabeceraTotalRegistros);

					// Aseigna valor a cabeceraImporteSumatoriaMonedaMXP.
					cabeceraImporteSumatoriaMonedaMXP = linea.substring(17, 33);
					System.out.println("Cabecera Importe Sumatoria Moneda MXP: " + cabeceraImporteSumatoriaMonedaMXP);

					// Asigna valor a cabeceraImporteSumatoriaMonedaUSD.
					cabeceraImporteSumatoriaMonedaUSD = linea.substring(37, 53);
					System.out.println(
							"Cabecera Importe Sumatoria Moneda USD: " + cabeceraImporteSumatoriaMonedaUSD + "\n");

					auxCabeceraFechaPago = true;
				}

				if (linea.contains(ICE)) {
					System.out.println("Si contiene la linea la palabra ICE");
					registrosICE.add(linea);

				} else if (linea.contains(BKL)) {
					System.out.println("Si contienen la linea la palabra BKL");
					registrosBKL.add(linea);
				}

				imprimirArchivoLeido();

			}
			// Se cierran los recursos que se utilizan.
			br.close();
			fr.close();

			System.out.println("Tamaño de registros de ICE: " + registrosICE.size());
			System.out.println("Tamaño de registros de BKL: " + registrosBKL.size());

			crearArchivoICE(cabeceraFechaPago, registrosICE, nombreArchivoNuevoICE);
//			crearArchivoBKL(cabeceraFechaPago, registrosBKL.size(), registrosBKL, nombreArchivoNuevoBKL);

		} catch (FileNotFoundException e) {
			System.out.println("El archivo no pudo ser leido... " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Ocurrió un error leyendo el archivo: " + e);
		}
	}

	/**
	 * Imprime el archivo.
	 */
	private void imprimirArchivoLeido() {
		System.out.println(linea);
	}

	/**
	 * Metodo que crea un nuevo archivo de ICE.
	 * 
	 * @param valor
	 * @return true o false si creo el archivo
	 */
	private boolean crearArchivoICE(String cabeceraFechaPago, List<String> registrosICE, String nombreArchivo) {

		// Creara un nuevo archivo siempre y cuando el valor sea false.
		if (auxNewFileICE == false) {
			try {
				pw = new PrintWriter(nombreArchivo);// Se crea el archivo con el nombre que lleva como parametro.
				identificarTipoMonto(registrosICE);
				montoTotalMXP = sumaMonto(sumatoriaMontoMXP);
				montoTotalUSD = sumaMonto(sumatoriaMontoExt);
				String registroTotalesICE = establecerTotalRegistrosICE(registrosICE.size());
				String montoTotalNacional = establecerSumatoriaMonedaNacionalOExtranjera(montoTotalMXP);
				String montoTotalExtranjero = establecerSumatoriaMonedaNacionalOExtranjera(montoTotalUSD);

				System.out.println("Registros totales de ICE ......" + registroTotalesICE);
				System.out.println("Monto Total Nacional: " + montoTotalNacional);
				System.out.println("Monto Total Extranjero: " + montoTotalExtranjero);

				pw.write("01".concat("   ").concat(cabeceraFechaPago).concat(registroTotalesICE)
						.concat(montoTotalNacional).concat("MXP").concat(montoTotalExtranjero).concat("USD\n"));
				for (String registro : registrosICE) {
					pw.write(registro + "\n");
				}
				System.out.println("Archivo ICE creado correctamente...");
			} catch (Exception e) {
				System.out.println("Error al crear el archivo: " + e.getMessage());
			}
			auxNewFileICE = true;
		}
		pw.close();
		return true;
	}
	
	/**
	 * Identifica el tipo de monto entre moneda Nacional(MXP) y moneda extranjera(USD).
	 * 
	 * @param registros
	 */
	private void identificarTipoMonto(List<String> registros) {
		for (String registro : registros) {
			if (registro.contains("MXP")) {
				String aux = registro.substring(40, 55);
				Long par = Long.parseLong(aux);
				sumatoriaMontoMXP.add(par);

			} else if (registro.contains("USD")) {
				String aux = registro.substring(40, 55);
				Long par = Long.parseLong(aux);
				sumatoriaMontoExt.add(par);
			}
		}
	}

	/**
	 * Establece el montoTotal de moneda Nacional o Extranjera.
	 * 
	 * @param monto
	 * @return
	 */
	private String establecerSumatoriaMonedaNacionalOExtranjera(Long monto) {

		String montoCompleto = "";
		String montoTotal = monto.toString();
		Integer numeroCaracteresMonto = 16 - montoTotal.length();

		for (int i = 0; i < numeroCaracteresMonto; i++) {
			montoCompleto = montoCompleto.concat("0");
		}
		return montoCompleto.concat(montoTotal);
	}

	/**
	 * Establece el numero de registros totales de ICE
	 * 
	 * @param totalRegistros
	 */
	private String establecerTotalRegistrosICE(Integer totalRegistros) {

		String registrosCompletos = "";
		String registros = totalRegistros.toString();
		Integer numeroCaracteres = 5 - registros.length();

		for (int i = 0; i < numeroCaracteres; i++) {
			registrosCompletos = registrosCompletos.concat("0");
		}

		return registrosCompletos.concat(registros);
	}

	/**
	 * Suma el monto de la lista que reciva por parametro.
	 * 
	 * @param montos
	 * @return
	 */
	private Long sumaMonto(List<Long> montos) {

		Long montoTotal = 0L;

		for (Long monto : montos) {
			montoTotal = montoTotal + monto;
		}

		return montoTotal;
	}

	/**
	 * Metodo que crea un nuevo archivo de BKL.
	 * 
	 * @param valor
	 * @return true o false si creo el archivo
	 */
//	private boolean crearArchivoBKL(String cabeceraFechaPago, Integer cabeceraTotalRegistrosBKL,
//			List<String> registrosBKL, String nombreArchivo) {
//		// Creara un nuevo archivo siempre y cuando el valor sea false.
//		if (auxNewFileBKL == false) {
//			try {
//				pw = new PrintWriter(nombreArchivo);// Se crea el archivo con el nombre que lleva como parametro.
//				System.out.println("Archivo BKL creado correctamente...");
//			} catch (Exception e) {
//				System.out.println("Error al crear el archivo: " + e.getMessage());
//			}
//			auxNewFileICE = true;
//		}
//		pw.close();
//		return true;
//	}

	/**
	 * Metodo principal de la aplicación.
	 * 
	 * @param mac
	 */
	public static void main(String... mac) {
		// Se utiliza el constructor con parametro.
		LeerArchivo2 object = new LeerArchivo2("c:\\pruebaJava\\prueba2.txt");

		object.readFile();
	}

}
