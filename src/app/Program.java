package app;

import java.util.List;
import java.util.Map;

import domain.Config;
import domain.Reservation;
import domain.Validator;
import services.ServiceReservCreation;
import services.ServiceConfigCreation;
import services.ServiceManagementLonge;
import services.ServiceCodeLanguage;;

public class Program {

	public static void main(String[] args) {
		ServiceManagementLonge servSalas = new ServiceManagementLonge();
		ServiceConfigCreation servConf = new ServiceConfigCreation();
		ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();
		Validator validator = new Validator();

		// IMPRESION DE LA CONFIGURACION----------------------------------------------
		Config config = servConf.getConf("input/config.txt");
		System.out.println(config);
		// ---------------------------------------------------------------------------

		// IMPRESION DE RESERVAS POR SALA---------------------------------------------
		Map<String, List<Reservation>> reservas = servSalas.getSalasConReservas("input/peticions.txt", config);
		reservas.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
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
		
		//COMPROBANDO Q iNTERPRETER INICIALIZA LAS TABLAS-----------------------------
//				System.out.println("**********************************************");
//				OldController cntr = new OldController();
//				cntr.setConfigFile();
//				cntr.configInterpreter(cntr.getConfigFile());
//				cntr.initializeTable();
//				
//				cntr.tableToString();
		
		OldController cntr = new OldController();
		cntr.setConfigFile();
		cntr.setSchedulerList(cntr.getFactory());
		cntr.schedulersToSring();
		
	}
}
