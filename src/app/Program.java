package app;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import domain.Config;
import domain.Incidence;
import domain.Reservation;
import domain.Table;
import services.IncidenceManager;
import services.ServiceManagementLonge;
import services.TableGenerator;
import services.subservices.ServiceCodeLanguage;
import services.subservices.ServiceConfigCreation;
import tools.subtools.DateTools;
import view.HtmlGenerator;

public class Program {

	public static void main(String[] args) throws ParseException {
		ServiceManagementLonge servSalas = new ServiceManagementLonge();
		ServiceConfigCreation servConf = new ServiceConfigCreation();
		ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();

		// IMPRESION DE LA CONFIGURACION----------------------------------------------
		Config config = servConf.getConf("input/config.txt");
//		System.out.println(config);
		// ---------------------------------------------------------------------------

		// IMPRESION DE RESERVAS POR SALA---------------------------------------------
		Map<String, List<Reservation>> loungesList = servSalas.getLoungesWithReserv("input/peticions.txt", config);
//		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------

		// CONFLICTOS DE HORAS Y FECHAS
		// preferencia a los cerrados, primera comprobacion entre closeds
		// sumar las reservas que se puedan juntar y ampliar
//		List<Reservation> incidencies = new ArrayList<>();

//		for (List<Reservation> listReserv : loungesList.values()) {
//			Iterator<Reservation> it = listReserv.iterator();
//			while (it.hasNext()) {
//				Reservation reserv = it.next();
//				if (reserv.getMeetingName().equalsIgnoreCase("tancat")) {
//					Iterator<Reservation> it1 = listReserv.iterator();
//					while (it1.hasNext()) {
//						Reservation reserv1 = it1.next();
//						if (!reserv1.getMeetingName().equalsIgnoreCase("tancat")) {
//							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//							DateTime fechaInicio1 = (DateTime.parse(format.format(reserv.getIniDat())));
//							DateTime fechaFin1 = (DateTime.parse(format.format(reserv.getFinalDat())));
//							DateTime fechaInicio2 = (DateTime.parse(format.format(reserv1.getIniDat())));
//							DateTime fechaFin2 = (DateTime.parse(format.format(reserv1.getFinalDat())));
//
//							Interval intervalo1 = new Interval(fechaInicio1, fechaFin1);
//							Interval intervalo2 = new Interval(fechaInicio2, fechaFin2);
//
//							if (intervalo1.overlaps(intervalo2)) {
//								if (reserv.getDays().equals(reserv1.getDays())) {
//									incidencies.add(new Reservation(reserv1));
//									reserv1.setMeetingName("remove");
//									listReserv.set(listReserv.indexOf(reserv1), reserv1);
//								}
//							}
//						}
//					}
//				}
//			}
//		}
//		for (List<Reservation> listReserv : loungesList.values()) {
//			Iterator<Reservation> it = listReserv.iterator();
//			while (it.hasNext()) {
//				Reservation reserv = it.next();
//				if (reserv.getMeetingName().equalsIgnoreCase("remove")) {
//					it.remove();
//				}
//			}
//		}
//		incidencies.forEach(System.out::println);

//		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE ENTRADA----------------------------
		Map<String, String[]> codeValueInputLanguage = servCodeLan.getLanguageCodes(config.getInputLanguage());

//		for (Entry<String, String[]> entry : codeValueInputLanguage.entrySet()) {
//			for (String x : entry.getValue())
//				System.out.println(entry.getKey() + "=" + x);
//		}

//		codeValueInputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v.toString()));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE SALIDA-----------------------------
		System.out.println("\n");
		Map<String, String[]> codeValueOutputLanguage = servCodeLan.getLanguageCodes(config.getOutputLanguage());
//		for (Entry<String, String[]> entry : codeValueOutputLanguage.entrySet()) {
//			for (String x : entry.getValue())
//				System.out.println(entry.getKey() + "=" + x);
//		}
//		codeValueOutputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------
		TableGenerator tg = new TableGenerator();
		List<Table> tables = tg.initTables();
//		for (Table x : tables) {
//			System.out.println(x.toString());
//		}
		HtmlGenerator htmlGen = new HtmlGenerator(codeValueOutputLanguage.get("007"), config.getYear());
		htmlGen.writeFile(tables);
//		System.out.println(DateTools.getDayInWeek(new Date()));
	}
}
