package app;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.Interval;

import domain.Config;
import domain.Reservation;
import services.ServiceManagementLonge;
import services.subservices.ServiceCodeLanguage;
import services.subservices.ServiceConfigCreation;
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
		Map<String, List<Reservation>> loungesList = servSalas.getSalasConReservas("input/peticions.txt", config);
//		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------

		// CONFLICTOS DE HORAS Y FECHAS
		// preferencia a los cerrados, primera comprobacion entre closeds
		// sumar las reservas que se puedan juntar y ampliar
		List<Reservation> incidencies = new ArrayList<>();

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
		Map<String, String> codeValueInputLanguage = servCodeLan.getLanguageCodes(config.getInputLanguage());
//		codeValueInputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE SALIDA-----------------------------
		System.out.println("\n");
		Map<String, String> codeValueOutputLanguage = servCodeLan.getLanguageCodes(config.getOutputLanguage());
		codeValueOutputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------
		String[][] semana1 = {
				{ "Weak 44", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
				{ "Day", "3", "4", "5", "6", "7", "8", "9" },
				{ "0-1", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "1-2", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "2-3", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "3-4", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "4-5", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "5-6", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "6-7", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "7-8", "free", "free", "free", "free", "free", "free", "free" },
				{ "8-9", "free", "free", "free", "free", "free", "free", "free" },
				{ "9-10", "free", "free", "free", "free", "free", "free", "free" },
				{ "10-11", "free", "free", "free", "free", "free", "free", "free" },
				{ "11-12", "free", "free", "free", "free", "free", "free", "free" },
				{ "12-13", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "13-14", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "14-15", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "15-16", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "16-17", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "17-18", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "18-19", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "19-20", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "20-21", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
				{ "21-22", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "22-23", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
				{ "23-24", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" }, };
		String[][] semana2 = {
					{ "Weak 45", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" },
					{ "Day", "3", "4", "5", "6", "7", "8", "9" },
					{ "0-1", "free", "free", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "1-2", "Closed", "Closed", "Closed", "free", "Closed", "Closed", "Closed" },
					{ "2-3", "Closed", "Closed", "Closed", "free", "free", "Closed", "Closed" },
					{ "3-4", "Closed", "Closed", "Closed", "free", "free", "Closed", "Closed" },
					{ "4-5", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "5-6", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "6-7", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "7-8", "free", "free", "free", "free", "free", "free", "free" },
					{ "8-9", "free", "free", "free", "free", "free", "free", "free" },
					{ "9-10", "free", "free", "free", "free", "free", "free", "free" },
					{ "10-11", "free", "free", "free", "free", "free", "free", "free" },
					{ "11-12", "free", "free", "free", "free", "free", "free", "free" },
					{ "12-13", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "13-14", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "14-15", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "15-16", "ReunioJava", "ReunioJava", "ReunioJava", "free", "ReunioJava", "free", "free" },
					{ "16-17", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "17-18", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "18-19", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "19-20", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "20-21", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "ReunioJava", "free", "free" },
					{ "21-22", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "22-23", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" },
					{ "23-24", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed", "Closed" }, };
		List<String[][]> semanas = new ArrayList<>();
		semanas.add(semana1);
		semanas.add(semana2);
		HtmlGenerator htmlGen = new HtmlGenerator(codeValueOutputLanguage.get("007"));
		htmlGen.escribirFichero(semanas);

	}	
}
