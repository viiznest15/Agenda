package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import domain.Config;
import domain.Reservation;
import domain.Table;
import services.subservices.ServiceCodeLanguage;
import services.subservices.ServiceConfigCreation;

import static tools.subtools.DateTools.*;

public class TableGenerator {

	public List<Table> initTables() {
		List<Table> tables = new ArrayList<>();
		ServiceConfigCreation servConf = new ServiceConfigCreation();
		ServiceManagementLonge servSalas = new ServiceManagementLonge();
		ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();

		Config config = servConf.getConf("input/config.txt");
		Map<String, String[]> entreda = servCodeLan.getLanguageCodes(config.getInputLanguage());
		Map<String, String[]> salida = servCodeLan.getLanguageCodes(config.getOutputLanguage());
		Map<String, List<Reservation>> loungesList = servSalas.getLoungesWithReserv("input/peticions.txt", config);

		int year = config.getYear();
		int month = config.getMonth();

//		int firstWeek = getWeekOfTheYear(ints2Date(month, year));
//		String[][] matrix = table.getTable();
		int weeks = getTotalWeekMonth(month, year);

//		int semana0 = getWeekOfTheYear(convertToDateViaInstant(getFirstDayOfNextWeek(ints2LocalDate(month, year))))-1;
		int semana = getWeekOfTheYear(ints2Date(month, year));

		int posicionDiaSemana = getDayInWeek(ints2LocalDate(month, year));
		String[] ndays = new String[7];
		Arrays.fill(ndays, "");
		LocalDate week = getFirstDayOfNextWeek(ints2LocalDate(month, year));
		int dia = 1;
		List<String[]> dias= new ArrayList<>();
		
		
		for (int x = 0; x < weeks; x++) {
			for (int i = posicionDiaSemana-1; i < 7; i++) {
				ndays[i] = Integer.toString(dia);
				dia++;
			}
//			for (int x = 0; x < weeks; x++) {
				tables.add(new Table("Week " + Integer.toString(semana), salida.get("002"), ndays));
				semana++;
//			}
				
			posicionDiaSemana = getDayInWeek(week);
			week.plusDays(7);
//			week = getFirstDayOfNextWeek(week);
			dia = week.getDayOfMonth();
//			dias.add(String);
		}

//		for (int x = 0; x < weeks; x++) {
//			tables.add(new Table("Week " + Integer.toString(semana), salida.get("002"), ndays));
//			semana++;
//		}
		// -------------------------------------------------------------------------------
//			matrix = new String[][]
//			matrix[0][0] == "Weak "+ (getWeekOfTheYear(ints2Date(getFirstDayOfNextWeek(ints2LocalDate()),month, year)));

//		for (int i = 0; i < matrix.length; i++) {
//			for (int j = 0; j < matrix[0].length; j++) {
//
//			}
//		}

		return tables;
	}
}
