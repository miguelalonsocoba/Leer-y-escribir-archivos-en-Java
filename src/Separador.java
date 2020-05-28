import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class LeerArchivos.
 *
 */
public class Separador {

	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Separador");

	/**
	 * Representa del archivo original la parte de la cabecera donde se encuentra el
	 * 01.
	 */
	private static final String CABECERAPARTE1 = "01";

	/**
	 * Representa del archivo original la parte del cuerpo donde se encuetnra el 02.
	 */
	private static final String CUERPOPARTE02 = "02";

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
	 * Representa el monto Total Nacional.
	 */
	private String montoTotalNacional;
	
	/**
	 * Representa el monto Total Extranjero.
	 */
	private String montoTotalExtranjero;

	/**
	 * Contrucor por defecto. Se inizializa la variable.
	 */
	public Separador() {
		lectureFile = new File("C:\\pruebaJava\\prueba2.txt");
	}

	/**
	 * Constructor que recive la ruta del archivo que se va a leer.
	 *
	 * @param ruta
	 */
	public Separador(String ruta) {
		lectureFile = new File(ruta);
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = "C:/pruebaJava/ICE.txt";
		nombreArchivoNuevoBKL = "C:/pruebaJava/BKL.txt";
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		totalRegistrosICE = 0;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
	}

	/**
	 * Constructor que recive la ruta del archivo que se va a leer, se va guarda en
	 * ICE y en BKL
	 *
	 * @param ruta, guardaArchivoICE, guardaArchivoBKL
	 */
	public Separador(String ruta, String guardaArchivoICE, String guardaArchivoBKL) {
		lectureFile = new File(ruta);
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = guardaArchivoICE;
		nombreArchivoNuevoBKL = guardaArchivoBKL;
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		totalRegistrosICE = 0;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
	}

	/**
	 * Metodo que lee un archivo.
	 */
	public void readFile() {

		Integer auxiliar = 0;// Lleva el control de las vueltas del while.
		try {
			FileReader fr = new FileReader(lectureFile);
			BufferedReader br = new BufferedReader(fr);

			LOGGER.log(Level.INFO, "Inicia proceso de lectura archivo Proceso Separador ");

			// Lee linea por linea del archivo...
			while ((linea = br.readLine()) != null) {

				auxiliar++;

				// Comprueba si la variable es false, si cumple asigna valores a las variables
				// de cabecera siguientes, e imprime los valores de las variables.
				if (auxCabeceraFechaPago == false) {

					// validar que venga siempre 01 en la cabecera del archivo origina.
					validar01And02(linea, auxiliar);

					// Asigna el valor a cabeceraFechaPago.
					cabeceraFechaPago = linea.substring(4, 12);
					LOGGER.log(Level.INFO, "Cabecera Fecha Pago: " + cabeceraFechaPago);
					System.out.println("Cabecera Fecha Pago: " + cabeceraFechaPago);

					// Asigna valor a cabeceraTotalRegistros.
					cabeceraTotalRegistros = linea.substring(12, 17);// MXP&USD
					LOGGER.log(Level.INFO, "Cabecera Total Registros: " + cabeceraTotalRegistros);
					System.out.println("Cabecera Total Registros: " + cabeceraTotalRegistros);

					// Aseigna valor a cabeceraImporteSumatoriaMonedaMXP.
					cabeceraImporteSumatoriaMonedaMXP = linea.substring(17, 33);
					LOGGER.log(Level.INFO,
							"Cabecera Importe Sumatoria Moneda MXP: " + cabeceraImporteSumatoriaMonedaMXP);
					System.out.println("Cabecera Importe Sumatoria Moneda MXP: " + cabeceraImporteSumatoriaMonedaMXP);

					// Asigna valor a cabeceraImporteSumatoriaMonedaUSD.
					cabeceraImporteSumatoriaMonedaUSD = linea.substring(37, 53);
					LOGGER.log(Level.INFO,
							"Cabecera Importe Sumatoria Moneda USD: " + cabeceraImporteSumatoriaMonedaUSD + "\n");
					System.out.println(
							"Cabecera Importe Sumatoria Moneda USD: " + cabeceraImporteSumatoriaMonedaUSD + "\n");

//comparar moneda extranjera y moneda nacional

//Validar que sea numerico los montos. 000000897650MXP

//Hacer suma de registros MXP

//Hacer suma de registros USD

//Comparar la suma contra el monto de la lectura de la sumatoria inicial

					auxCabeceraFechaPago = true;
				}

				// validar que venga siempre 01 y 02 en cabecera y cuerpo.
				validar01And02(linea, auxiliar);

//Validar que la longitud de moneda sea la correcta.

				if (linea.contains(ICE)) {
					LOGGER.log(Level.INFO, "Si contiene la linea la palabra ICE");
					registrosICE.add(linea);

				} else if (linea.contains(BKL)) {
					LOGGER.log(Level.INFO, "Si contiene la linea la palabra ICE");
					registrosBKL.add(linea);
				}

				imprimirArchivoLeido();

			}
			// Se cierran los recursos que se utilizan.
			fr.close();
			br.close();

			LOGGER.log(Level.INFO, "Tamaño de registros de ICE: " + registrosICE.size());
			LOGGER.log(Level.INFO, "Tamaño de registros de BKL: " + registrosBKL.size());
			crearArchivoICE(cabeceraFechaPago, registrosICE, nombreArchivoNuevoICE);
			inicializarVariables();
			crearArchivoBKL(cabeceraFechaPago, registrosBKL, nombreArchivoNuevoBKL);

		} catch (FileNotFoundException e) {
			LOGGER.log(Level.INFO, "El archivo no pudo ser leido... " + e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Ocurrió un error leyendo el archivo: " + e);
		} catch (Exception e) {
			LOGGER.warning(e.getMessage());
		}
	}

	/**
	 * Metodo que valida que la cabecera y el cuerpo del archivo original contenga los digitos
	 * de "01" y "02" respectivamente.
	 * 
	 * @param cabecera
	 * @return boolean
	 * @throws Exception
	 */
	private void validar01And02(String valor, Integer numLinea) throws Excepciones {

		String subValor = valor.substring(0, 2);
		LOGGER.info("Valor del sub string: " + subValor);
		if (numLinea.equals(1) && !subValor.contains(CABECERAPARTE1)) {
			throw new Excepciones("Error... En la cabecera del archivo no se encuentra los digitos " + CABECERAPARTE1);
		} else if (numLinea > 1 && !subValor.contains(CUERPOPARTE02)) {
			throw new Excepciones("Error... En el cuerpo del archivo no se encuentra los digitos " + CUERPOPARTE02);
		}

	}

	/**
	 * Imprime el archivo.
	 */
	public void imprimirArchivoLeido() {
		LOGGER.log(Level.INFO, "linea");
	}

	/**
	 * Metodo vacio crearArchivoICE.
	 *
	 */
	public void crearArchivoICE() {
		LOGGER.log(Level.INFO, "crearArchivoICE");
	}

	/**
	 * Metodo que crea un nuevo archivo de ICE.
	 *
	 * @param valor
	 * @return true o false si creo el archivo
	 */
	public boolean crearArchivoICE(String cabeceraFechaPago, List<String> registrosICE, String nombreArchivo) {

		// Creara un nuevo archivo siempre y cuando el valor sea false.
		if (Boolean.FALSE.equals(auxNewFileICE)) {
			try {
				pw = new PrintWriter(nombreArchivo);// Se crea el archivo con el nombre que lleva como parametro.
				identificarTipoMonto(registrosICE);
				montoTotalMXP = sumaMonto(sumatoriaMontoMXP);
				montoTotalUSD = sumaMonto(sumatoriaMontoExt);
				String registroTotalesICE = establecerTotalRegistrosICE(registrosICE.size());
				montoTotalNacional = establecerSumatoriaMonedaNacional(montoTotalMXP);
				montoTotalExtranjero = establecerSumatoriaMonedaExtranjera(montoTotalUSD);

				LOGGER.log(Level.INFO, "Registros totales de ICE ......" + registroTotalesICE);

				LOGGER.log(Level.INFO, "Monto Total Nacional: " + montoTotalNacional);

				LOGGER.log(Level.INFO, "Monto Total Extranjero: " + montoTotalExtranjero);

				pw.write("01".concat("  ").concat(cabeceraFechaPago).concat(registroTotalesICE)
						.concat(montoTotalNacional).concat("MXP").concat(montoTotalExtranjero).concat("USD\n"));
				for (String registro : registrosICE) {
					pw.write(registro + "\n");
				}
				LOGGER.log(Level.INFO, "Archivo ICE creado correctamente...");
			} catch (Exception e) {
				LOGGER.log(Level.INFO, "Error al crear el archivo ICE: " + e.getMessage());
			}
			auxNewFileICE = true;
		}
		pw.close();
		return true;
	}

	/**
	 * Metodo que crea un nuevo archivo de BKL.
	 *
	 * @param valor
	 * @return true o false si creo el archivo
	 */

	public boolean crearArchivoBKL(String cabeceraFechaPago, List<String> registrosBKL, String nombreArchivo) {
		// Creara un nuevo archivo siempre y cuando el valor sea false.
		if (Boolean.FALSE.equals(auxNewFileBKL)) {
			try {
				pw = new PrintWriter(nombreArchivo);// Se crea el archivo con el nombre que lleva como parametro.
				identificarTipoMonto(registrosBKL);
				montoTotalMXP = sumaMonto(sumatoriaMontoMXP);
				montoTotalUSD = sumaMonto(sumatoriaMontoExt);
				String registroTotalesBKL = establecerTotalRegistrosICE(registrosBKL.size());
				montoTotalNacional = establecerSumatoriaMonedaNacional(montoTotalMXP);
				montoTotalExtranjero = establecerSumatoriaMonedaExtranjera(montoTotalUSD);

				LOGGER.log(Level.INFO, "Registros totales de BKL ......" + registroTotalesBKL);

				LOGGER.log(Level.INFO, "Monto Total Nacional: " + montoTotalNacional);

				LOGGER.log(Level.INFO, "Monto Total Extranjero: " + montoTotalExtranjero);

				pw.write("01".concat("  ").concat(cabeceraFechaPago).concat(registroTotalesBKL)
						.concat(montoTotalNacional).concat("MXP").concat(montoTotalExtranjero).concat("USD\n"));
				for (String registro : registrosBKL) {
					pw.write(registro + "\n");
				}
				LOGGER.log(Level.INFO, "Archivo BKL creado correctamente...");
			} catch (Exception e) {
				LOGGER.log(Level.INFO, "Error al crear el archivo BKL: " + e.getMessage());
			}
			auxNewFileBKL = true;
		}
		pw.close();
		return true;
	}

	/**
	 * Metodo para setear las variables al crear el nuevo archivo BKL
	 */
	private void inicializarVariables() {
		totalRegistrosICE = 0;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
		montoTotalMXP = null;
		montoTotalUSD = null;
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
	}

	/**
	 * Identifica el tipo de monto entre moneda Nacional(MXP) y moneda
	 * extranjera(USD).
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
	private String establecerSumatoriaMonedaExtranjera(Long monto) {
		String montoCompleto = "";
		String montoTotal = monto.toString();
		Integer numeroCaracteresMonto = 20 - montoTotal.length();

		for (int i = 0; i < numeroCaracteresMonto; i++) {
			montoCompleto = montoCompleto.concat("0");
		}
		return montoCompleto.concat(montoTotal);
	}

	/**
	 * Establece el montoTotal de moneda Nacional o Extranjera.
	 *
	 * @param monto
	 * @return
	 */
	private String establecerSumatoriaMonedaNacional(Long monto) {
		String montoCompleto = "";
		String montoTotal = monto.toString();

		Integer numeroCaracteresMonto = 15 - montoTotal.length();

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
	 * Metodo principal de la aplicación.
	 *
	 * @param mac
	 */
	public static void main(String... separador) {
		// Se utiliza el constructor con parametro.
		Separador object = new Separador("c:\\pruebaJava\\prueba2.txt");

//Lectura del archivo txt
		object.readFile();
//
//		Bitacora bitacora = new Bitacora();
//		bitacora.main(separador);
	}

}
