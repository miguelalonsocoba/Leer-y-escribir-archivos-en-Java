import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class LeerArchivos.
 *
 */
public class Separador {

	private final static Logger LOGGER = Logger.getLogger("bitacora.subnivel.Separador");
	
	/**
	 * Almacena la ruta del archivio que sera leido.
	 */
	private String rutaLeerArchivo;

	/**
	 * Almacena los montos de moneda Nacional del archivo original.
	 */
	private List<Long> montosMXPArchivoOriginal;

	/**
	 * Almacena los montos de moneda Extranjera del archivo original.
	 */
	private List<Long> montosUSDArchivoOriginal;

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
	 * Representa el tipo de moneda Extranjera.
	 */
	private static final String TIPOMONEDAUSD = "USD";

	/**
	 * Representa el tipo de moneda Nacional.
	 */
	private static final String TIPOMONEDAMXP = "MXP";

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
	private static final String ICE = "ICE";

	/**
	 * Costante.
	 */
	private static final String BKL = "BKL";

	/**
	 * Variable de cabecera Importe Sumatoria Moneda USD del archivo que se lee.
	 */
	private String cabeceraImporteSumatoriaMonedaUSD;

	/**
	 * Variable de cabecera Importe Sumatoria Moneda MXP del archivo que se lee.
	 */
	private String cabeceraImporteSumatoriaMonedaMXP;

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
	 * Variable que al crearse el nuevo archivo newFileIce cambiara el valor a true.
	 */
	private Boolean auxNewFileICE;

	/**
	 * Variable que al crearse el nuevo archivo newFileBKL cambiara el valor a true.
	 */
	private Boolean auxNewFileBKL;

	/**
	 * Representa el monto Total Nacional.
	 */
	private String montoTotalNacional;

	/**
	 * Representa el monto Total Extranjero.
	 */
	private String montoTotalExtranjero;

	/**
	 * Lleva el registro de cuantas lineas hay en el archivo original
	 */
	private Integer contadorLineas;

	/**
	 * Lleva control de los registros totales de moneda MXP
	 */
	private Integer contadorRegistrosMXP;

	/**
	 * Lleva control de los registros totales de moneda USD
	 */
	private Integer contadorRegistrosUSD;

	/**
	 * Contrucor por defecto. Se inizializa la variable.
	 */
	public Separador() {
		rutaLeerArchivo = null;
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = null;
		nombreArchivoNuevoBKL = null;
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
		contadorLineas = 0;
		montosMXPArchivoOriginal = new ArrayList<>();
		montosUSDArchivoOriginal = new ArrayList<>();
		contadorRegistrosMXP = 0;
		contadorRegistrosUSD = 0;
	}

	/**
	 * Constructor que recive la ruta del archivo que se va a leer.
	 *
	 * @param ruta
	 */
	public Separador(String ruta) {
		rutaLeerArchivo = null;
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = "C:/pruebaJava/ICE.txt";
		nombreArchivoNuevoBKL = "C:/pruebaJava/BKL.txt";
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
		contadorLineas = 0;
		montosMXPArchivoOriginal = new ArrayList<>();
		montosUSDArchivoOriginal = new ArrayList<>();
		contadorRegistrosMXP = 0;
		contadorRegistrosUSD = 0;
	}

	/**
	 * Constructor que recive la ruta del archivo que se va a leer, se va guarda en
	 * ICE y en BKL
	 *
	 * @param ruta, guardaArchivoICE, guardaArchivoBKL
	 */
	public Separador(String ruta, String guardaArchivoICE, String guardaArchivoBKL) {
		registrosICE = new ArrayList<>();
		registrosBKL = new ArrayList<>();
		nombreArchivoNuevoICE = "C:/pruebaJava/ICE.txt";
		nombreArchivoNuevoBKL = "C:/pruebaJava/BKL.txt";
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		montoTotalMXP = 0L;
		montoTotalUSD = 0L;
		auxCabeceraFechaPago = false;
		auxNewFileICE = false;
		auxNewFileBKL = false;
		montoTotalNacional = null;
		montoTotalExtranjero = null;
		contadorLineas = 0;
		montosMXPArchivoOriginal = new ArrayList<>();
		montosUSDArchivoOriginal = new ArrayList<>();
		contadorRegistrosMXP = 0;
		contadorRegistrosUSD = 0;
	}

	/**
	 * Metodo que lee un archivo.
	 */
	public void readFile() {

		try {
			FileReader fr = new FileReader(rutaLeerArchivo);
			BufferedReader br = new BufferedReader(fr);
			
			/**
			 * Variable que almacenara linea por linea del archivo leido.
			 */
			String linea;

			LOGGER.log(Level.INFO, "Inicia proceso de lectura archivo Proceso Separador ");

			// Lee linea por linea del archivo...
			while ((linea = br.readLine()) != null) {

				contadorLineas++;

// Comprueba si la variable es false, si cumple asigna valores a las variables
// de cabecera siguientes, e imprime los valores de las variables.
				if (auxCabeceraFechaPago == false) {

// Valida que venga siempre 01 en la cabecera del archivo origina.
					validar01And02(linea, contadorLineas);

// Asigna el valor a cabeceraFechaPago.
					cabeceraFechaPago = linea.substring(4, 12);
					LOGGER.log(Level.INFO, "Cabecera Fecha Pago: " + cabeceraFechaPago);

// Asigna valor a cabeceraImporteSumatoriaMonedaMXP.
					cabeceraImporteSumatoriaMonedaMXP = linea.substring(17, 32);
					LOGGER.log(Level.INFO,
							"Cabecera Importe Sumatoria Moneda MXP: " + cabeceraImporteSumatoriaMonedaMXP);

// Asigna valor a cabeceraImporteSumatoriaMonedaUSD.
					cabeceraImporteSumatoriaMonedaUSD = linea.substring(40, 55);
					LOGGER.log(Level.INFO,
							"Cabecera Importe Sumatoria Moneda USD: " + cabeceraImporteSumatoriaMonedaUSD + "\n");
					auxCabeceraFechaPago = true;
				}

// Valida que siempre sea cabecera01 y registro 02 en cabecera y cuerpo.
				validar01And02(linea, contadorLineas);

				if (linea.contains(ICE)) {
					identificaTipoRegstroPorMOneda(linea);
					LOGGER.log(Level.INFO, "Si contiene la linea la palabra ICE");
					registrosICE.add(linea);

				} else if (linea.contains(BKL)) {
					LOGGER.log(Level.INFO, "Si contiene la linea la palabra ICE");
					registrosBKL.add(linea);
				}

				imprimirArchivoLeido();

// Hacer suma de registros MXP
				sumaMontosMXPAndUSDDetalle(linea, contadorLineas);

			}

// Se cierran los recursos que se utilizan.
			fr.close();
			br.close();

// Valida la comparacion monto de cabecera del archivo original con la suma resultante de los
// montos tanto de USD y MXP.
			compararMontoCabeceraYMontosDetalle();

			LOGGER.log(Level.INFO, "Inicia proceso de creación de archivos ICE/BKL ");
			LOGGER.log(Level.INFO, "Tamaño de registros de ICE: " + registrosICE.size());
			LOGGER.log(Level.INFO, "Tamaño de registros de BKL: " + registrosBKL.size());
			crearArchivoICE(cabeceraFechaPago, registrosICE, nombreArchivoNuevoICE);
			inicializarVariables();
			crearArchivoBKL(cabeceraFechaPago, registrosBKL, nombreArchivoNuevoBKL);

		} catch (FileNotFoundException e) {
//Valida que el archivo siempre sea PEB.txt
			LOGGER.log(Level.INFO, "El archivo PEB.txt no pudo ser leido... " + e.getMessage());
		} catch (IOException e) {
			LOGGER.log(Level.INFO, "Ocurrió un error leyendo el archivo PEB.txt: " + e);
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
	}

	/**
	 * Compara que el montos USD y MXP de la cabecera del archivo original sea igual
	 * a la suma de los montos de su detalle.
	 */
	private void compararMontoCabeceraYMontosDetalle() throws Excepciones {
		Long montoTotalMXPDetalle = 0L;
		Long montoTotalUSDDetalle = 0L;
		try {
			for (Long long1 : montosMXPArchivoOriginal) {
				montoTotalMXPDetalle = montoTotalMXPDetalle + long1;
			}
			if (!montoTotalMXPDetalle.equals(Long.parseLong(cabeceraImporteSumatoriaMonedaMXP))) {
				throw new Excepciones(
						"Error... El monto MXP de la cabecera del archivo original no es igual a la suma de los montos de su detalle");
			}

			for (Long long1 : montosUSDArchivoOriginal) {
				montoTotalUSDDetalle = montoTotalUSDDetalle + long1;
			}
			if (!montoTotalUSDDetalle.equals(Long.parseLong(cabeceraImporteSumatoriaMonedaUSD))) {
				throw new Excepciones(
						"Error... El monto USD de la cabecera del archivo original no es igual a la suma de los montos de su detalle");
			}
		} catch (NumberFormatException e) {
			throw new Excepciones(
					"Error... Los montos de la cabecera del archivo original no cumplen con el formato especifico");
		}

	}

	/**
	 * Suma los montos de MXP y USD, de igual forma revisa si el formato de los
	 * montos es valido.
	 *
	 * @param valorLinea
	 * @param numeroLinea
	 * @throws Excepciones
	 */
	private void sumaMontosMXPAndUSDDetalle(String valorLinea, Integer numeroLinea) throws Excepciones {
		try {
			if (numeroLinea > 1) {
				if (valorLinea.contains(TIPOMONEDAMXP)) {
					Long valorMXP;
					valorMXP = Long.parseLong(valorLinea.substring(40, 55));
					montosMXPArchivoOriginal.add(valorMXP);
				} else if (valorLinea.contains(TIPOMONEDAUSD)) {
					Long valorUSD;
					valorUSD = Long.parseLong(valorLinea.substring(40, 55));
					montosUSDArchivoOriginal.add(valorUSD);
				}
			}
		} catch (NumberFormatException e) {
//Valida el formato de la moneda
			throw new Excepciones(String.format("Error... El monto contiene un formato invalido en la linea numero %s",
					contadorLineas));
		}
	}

	/**
	 * Metodo que valida que la cabecera y el cuerpo del archivo original contenga
	 * los digitos de "01" y "02" respectivamente.
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
			throw new Excepciones("Error... En el cuerpo del archivo no se encuentra los digitos " + CUERPOPARTE02
					+ " en la linea número " + contadorLineas);
		}

	}

	/**
	 * Imprime el archivo.
	 */
	public void imprimirArchivoLeido() {
		LOGGER.log(Level.INFO, "linea");
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
				String registroTotalesMXP = establecerTotalRegistrosMXP(contadorRegistrosMXP);
				String registroTotalesUSD = establecerTotalRegistrosUSD(contadorRegistrosUSD);
				montoTotalNacional = establecerSumatoriaMonedaNacional(montoTotalMXP);
				montoTotalExtranjero = establecerSumatoriaMonedaExtranjera(montoTotalUSD);

				LOGGER.log(Level.INFO, "Registros totales de ICE MXP ......" + registroTotalesMXP);
				LOGGER.log(Level.INFO, "Registros totales de ICE USD ......" + registroTotalesUSD);
				LOGGER.log(Level.INFO, "Monto Total Nacional: " + montoTotalNacional);
				LOGGER.log(Level.INFO, "Monto Total Extranjero: " + montoTotalExtranjero);

				pw.write("01".concat("  ").concat(cabeceraFechaPago).concat(registroTotalesMXP)
						.concat(montoTotalNacional).concat("MXP").concat(registroTotalesUSD)
						.concat(montoTotalExtranjero).concat("USD\n"));
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
				String registroTotalesMXP = establecerTotalRegistrosMXP(registrosBKL.size());
				String registroTotalesUSD = establecerTotalRegistrosUSD(registrosBKL.size());
				montoTotalNacional = establecerSumatoriaMonedaNacional(montoTotalMXP);
				montoTotalExtranjero = establecerSumatoriaMonedaExtranjera(montoTotalUSD);

				LOGGER.log(Level.INFO, "Registros totales de ICE MXP ......" + registroTotalesMXP);
				LOGGER.log(Level.INFO, "Registros totales de ICE USD ......" + registroTotalesUSD);
				LOGGER.log(Level.INFO, "Monto Total Nacional: " + montoTotalNacional);
				LOGGER.log(Level.INFO, "Monto Total Extranjero: " + montoTotalExtranjero);

				pw.write("01".concat("  ").concat(cabeceraFechaPago).concat(registroTotalesMXP)
						.concat(montoTotalNacional).concat("MXP").concat(registroTotalesUSD)
						.concat(montoTotalExtranjero).concat("USD\n"));
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
	 * Metodo para settear las variables al crear el nuevo archivo BKL
	 */
	private void inicializarVariables() {
		montoTotalNacional = null;
		montoTotalExtranjero = null;
		montoTotalMXP = null;
		montoTotalUSD = null;
		sumatoriaMontoMXP = new ArrayList<>();
		sumatoriaMontoExt = new ArrayList<>();
		contadorRegistrosMXP = 0;
		contadorRegistrosUSD = 0;
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
		Integer numeroCaracteresMonto = 15 - montoTotal.length();

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
	private String establecerTotalRegistrosMXP(Integer totalRegistros) {

		String registrosCompletos = "";
		String registros = totalRegistros.toString();
		Integer numeroCaracteres = 5 - registros.length();

		for (int i = 0; i < numeroCaracteres; i++) {
			registrosCompletos = registrosCompletos.concat("0");
		}

		return registrosCompletos.concat(registros);
	}

	/**
	 * Establece el numero de registros totales de ICE
	 *
	 * @param totalRegistros
	 */
	private String establecerTotalRegistrosUSD(Integer totalRegistros) {

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

	private void identificaTipoRegstroPorMOneda(String registro) {
		if (registro.contains("MXP")) {
			contadorRegistrosMXP++;
			System.out.println("El registro es en MXP");
		} else if (registro.contains("USD")) {
			contadorRegistrosUSD++;
			System.out.println("El registro es USD");
		}
	}
	
	/**
	 * Metodo que lee las variables de configuracion del archivo properties.
	 * @throws Excepciones 
	 */
	private void leerArchivoProperties() throws Excepciones {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("c:/pruebaJava/variables.properties"));
			rutaLeerArchivo = properties.getProperty("rutaLeerArchivo");
			nombreArchivoNuevoICE = properties.getProperty("nombreArchivoNuevoICE");
			nombreArchivoNuevoBKL = properties.getProperty("nombreArchivoNuevoBKL");
			if (nombreArchivoNuevoBKL == null || nombreArchivoNuevoICE == null) {
				throw new Excepciones("Error... El parametro no existe en el archivo de configuraciones... ");
			}
		} catch (IOException e) {
			throw new Excepciones("Error al leer el archvivo de configuraciones... " + e.getMessage());
		}
	}

	/**
	 * Metodo principal de la aplicación.
	 *
	 * @param mac
	 * @throws Excepciones 
	 */
	public static void main(String... separador) {
		
		try {
			Separador object = new Separador();
			object.leerArchivoProperties();
			//Lectura del archivo txt
			object.readFile();
		} catch (Exception e) {
			LOGGER.warning("Error al leer el archivo de properties... " + e.getMessage());
		}
		
	}
}