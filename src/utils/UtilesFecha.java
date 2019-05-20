package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class UtilesFecha {

	public static Date incFecha(Date fecha, int dias) {
		long mseg = fecha.getTime();// ms transcurridos desde 1/1/70
		long fechaFutura = mseg + (dias * 1000L * 60 * 60 * 24);
		return new Date(fechaFutura);
	}

	public static Date ints2Date(int d, int m, int a) {
		String presuntaFecha = d + "/" + m + "/" + a;
		return string2Date(presuntaFecha);
	}
	public static Date ints2Date(int m, int a) {
		String presuntaFecha = m + "/" + a;
		return string2Date(presuntaFecha);
	}

	public static String prettyPrint(Date fecha) {
		return prettyPrint(fecha, "dd/MM/yyyy");
	}

	public static String prettyPrint(Date fecha, String patron) {
		DateFormat df = new SimpleDateFormat(patron);
		return df.format(fecha);
	}

	/**
	 * Este metodo asume que el String proporcionado sigue el patron dd/MM/yyyy
	 * 
	 * @param presuntaFecha
	 * @return Un objeto Date o lanza RuntimeException si no se cumple el formato
	 *         dd/MM/yyyy
	 */
	public static Date string2Date(String presuntaFecha) {
		return string2Date(presuntaFecha, "dd/MM/yyyy");
	}

	/**
	 * Este metodo no asume ningun patron de formato
	 * 
	 * @param presuntaFecha formatoFecha
	 * @return Un objeto Date o lanza RuntimeException si no se cumple el formato
	 *         dd/MM/yyyy
	 */
	public static Date string2Date(String presuntaFecha, String patron) {
		DateFormat df = new SimpleDateFormat(patron);
		try {
			return df.parse(presuntaFecha);
		} catch (ParseException e) {
			throw new RuntimeException("El string proporcionado, no se ajusta al patron indicado!");
		}
	}

	public static String getDayDate(Date fecha) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("u"); // dia de la semana
		String dia = (String) simpleDateformat.format(fecha);
		return dia;
	}

	public static String getDayDate(int a�o, int mes) { // Sobrecarga teniendo en cuenta "config.txt"
		Date fecha = ints2Date(mes, a�o);
		return getDayDate(fecha);
	}

	public static int getWeekDate(Date fecha) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("w"); // w --> numero semana a�o
		int dia = Integer.parseInt(simpleDateformat.format(fecha));
		return dia;
	}
	
	public static int getTotalWeekMonth(int a�o, int mes) {
		int ultimoDiaMes = getLastDayMonth(a�o, mes);
		Date fecha = ints2Date(ultimoDiaMes, mes, a�o);
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("W"); // w --> numero semana MES
		System.out.println(simpleDateformat.format(fecha));
		int semanasMes = Integer.parseInt(simpleDateformat.format(fecha));
		return semanasMes;
	}
	public static int getLastDayMonth(int a�o, int mes) {
		Date fecha = ints2Date(mes, a�o);		
		LocalDate fechaLocal = convertToLocalDateViaInstant(fecha);
		int ultimoDiaMes = fechaLocal.lengthOfMonth();
		return ultimoDiaMes;
	}
	
	public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDate();
	}
}
