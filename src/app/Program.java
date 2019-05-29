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
import services.nivelbajo.ServiceCodeLanguage;
import services.nivelbajo.ServiceConfigCreation;;

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
		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------

		// CONFLICTOS DE HORAS Y FECHAS
		// preferencia a los cerrados, primera comprobacion entre closeds
		// sumar las reservas que se puedan juntar y ampliar
		List<Reservation> incidencies = new ArrayList<>();

		for (List<Reservation> listReserv : loungesList.values()) {
			Iterator<Reservation> it = listReserv.iterator();
			while (it.hasNext()) {
				Reservation reserv = it.next();
				if (reserv.getMeetingName().equalsIgnoreCase("tancat")) {
					Iterator<Reservation> it1 = listReserv.iterator();
					while (it1.hasNext()) {
						Reservation reserv1 = it1.next();
						if (!reserv1.getMeetingName().equalsIgnoreCase("tancat")) {
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							DateTime fechaInicio1 = (DateTime.parse(format.format(reserv.getIniDat())));
							DateTime fechaFin1 = (DateTime.parse(format.format(reserv.getFinalDat())));
							DateTime fechaInicio2 = (DateTime.parse(format.format(reserv1.getIniDat())));
							DateTime fechaFin2 = (DateTime.parse(format.format(reserv1.getFinalDat())));

							Interval intervalo1 = new Interval(fechaInicio1, fechaFin1);
							Interval intervalo2 = new Interval(fechaInicio2, fechaFin2);

							if (intervalo1.overlaps(intervalo2)) {
								if (reserv.getDays().equals(reserv1.getDays())) {
									incidencies.add(new Reservation(reserv1));
									reserv1.setMeetingName("remove");
									listReserv.set(listReserv.indexOf(reserv1), reserv1);
								}
							}
						}
					}
				}
			}
		}
		for (List<Reservation> listReserv : loungesList.values()) {
			Iterator<Reservation> it = listReserv.iterator();
			while (it.hasNext()) {
				Reservation reserv = it.next();
				if (reserv.getMeetingName().equalsIgnoreCase("remove")) {
					it.remove();
				}
			}
		}
		incidencies.forEach(System.out::println);

//		loungesList.forEach((k, v) -> System.out.println("Key: " + k + ": \nValue: \n" + v));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE ENTRADA----------------------------
		Map<String, String> codeValueInputLanguage = servCodeLan.getLanguageCodes(config.getInputLanguage());
//		codeValueInputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------

		// IMPRESION DE LOS CODIGOS DE LENGUAJE DE SALIDA-----------------------------
		System.out.println("\n");
		Map<String, String> codeValueOutputLanguage = servCodeLan.getLanguageCodes(config.getOutputLanguage());
//		codeValueOutputLanguage.forEach((k, v) -> System.out.println("Key: " + k + ": Value: " + v));
		// ---------------------------------------------------------------------------
		String[][] peticiones = {
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
		escribirFichero(peticiones);
	}



	public static void escribirFichero(String[][] peticiones) {

//		ArrayList<String[][]> peticiones = new ArrayList<String[][]>();

		File top = new File("resources/top.txt");

		try (Scanner sc = new Scanner(top);
				DataOutputStream dos = new DataOutputStream(new FileOutputStream("output/sala1.html"))) {
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				dos.writeBytes(lin);
			}
			insertTable(dos, peticiones);
			closeHtml(dos);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void closeHtml(DataOutputStream dos) throws IOException {
		dos.writeBytes("</body>\r\n" + "</html>");
	}

	private static void insertTable(DataOutputStream dos, String[][] peticiones) throws IOException {
		String firstRow = "#D6E7FA";
		String firstCol = "#E8F1FE";
		String colsDefault = "#DFFAC4";// verde
		String titulo1 = "Sala1";
		String titulo2 = "January 2008";

		dos.writeBytes("<h1 align=center>\n" + titulo1 + "</h1>\n" + "<h2 align=center>" + titulo2 + "</h2>\n");
		dos.writeBytes("<table align=center width=90% border=1 cellpadding=2 cellspacing=0>\n");

		dos.writeBytes("<tr style=\"text-align: center; background-color:" + firstRow + ";\">\n");

		for (int row = 0; row < peticiones.length; row++) {
			if (row != 0)
				dos.writeBytes("<tr style=\"text-align: center;\">\n");
			for (int col = 0; col < peticiones[0].length; col++) {
				if (row == 0) {
					dos.writeBytes("<td style=\"border: 1px solid black; background-color:" + firstRow + ";\">");
				} else {
					if (row == 1 || (col == 0 && row != 0))
						dos.writeBytes("<td style=\"border: 1px solid black; background-color:" + firstCol + ";\">");
					else {
						if (closed(peticiones[row][col])) {
							dos.writeBytes("<td style=\"border: 1px solid black;background-color: #b2aaaa;\">");
						} else if (free(peticiones[row][col])) {
							dos.writeBytes("<td style=\"border: 1px solid black;background-color: #DFFAC4;\">");
						} else if (reunion(peticiones[row][col])) {
							dos.writeBytes("<td style=\"border: 1px solid black;background-color: #fbfccf;\">");
						}
					}
				}
				dos.writeBytes(peticiones[row][col]);
				dos.writeBytes("</td>\n");
			}
			dos.writeBytes("</tr>\n");
		}
		dos.writeBytes("</table>\n");

	}

	private static boolean reunion(String field) {
		return field.toLowerCase().substring(0, 1).equals("r");
	}

	private static boolean free(String field) {
		return field.toLowerCase().equals("free");
	}

	private static boolean closed(String field) {
		return field.toLowerCase().equals("closed");
	}
}
