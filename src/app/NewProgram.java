package app;

import java.util.List;
import java.util.Map;

import domain.Config;
import domain.Reservation;
import services.ServiceManagementLonge;
import services.subservices.ServiceCodeLanguage;
import services.subservices.ServiceConfigCreation;

public class NewProgram {

	public static void main(String[] args) {
		
		ServiceManagementLonge servSalas = new ServiceManagementLonge();
		ServiceConfigCreation servConf = new ServiceConfigCreation();
		ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();
		
		// IMPRESION DE LA CONFIGURACION----------------------------------------------
		Config config = servConf.getConf("input/config.txt");
		System.out.println(config);
		// ---------------------------------------------------------------------------

		// IMPRESION DE RESERVAS POR SALA---------------------------------------------
		Map<String, List<Reservation>> loungesList = servSalas.getLoungesWithReserv("input/peticions.txt", config);
		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------
		
		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE ENTRADA----------------------------
		Map<String, String> codeValueInputLanguage = servCodeLan.getLanguageCodes(config.getInputLanguage());
		codeValueInputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE SALIDA-----------------------------
		System.out.println("\n");
		Map<String, String> codeValueOutputLanguage = servCodeLan.getLanguageCodes(config.getOutputLanguage());
		codeValueOutputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------
		
		OldController cont = new OldController();
		cont.setConfigFile();
		cont.setPetitions();
		cont.setSchedulerList(cont.getFactory());
		cont.configInterpreter(cont.getConfigFile());
		cont.initializeTables();
		cont.schedulersToString();
		
		

	}

}
