package tools.subservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

	public static Date incFecha(Date date, int dias) {
		long mseg = date.getTime();// ms transcurridos desde 1/1/70
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

	public static String prettyPrint(Date date) {
		return prettyPrint(date, "dd/MM/yyyy");
	}

	public static String prettyPrint(Date date, String patron) {
		DateFormat df = new SimpleDateFormat(patron);
		return df.format(date);
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

	public static String getDayDate(Date date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("u"); // dia de la semana
		String dia = (String) simpleDateformat.format(date);
		return dia;
	}

	public static String getDayDate(int year, int month) { // Sobrecarga teniendo en cuenta "config.txt"
		Date date = ints2Date(month, year);
		return getDayDate(date);
	}

	public static int getWeekDate(Date date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("w"); // w --> numero semana year
		int dia = Integer.parseInt(simpleDateformat.format(date));
		return dia;
	}

	public static int getTotalWeekMonth(int month, int year) {

		Date date = ints2Date(1, month, year);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int start = c.get(Calendar.WEEK_OF_MONTH);

		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		int end = c.get(Calendar.WEEK_OF_MONTH);

		return end - start + 1;
	}

	public static int getLastDayMonth(int year, int month) {
		Date date = ints2Date(1, month, year);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH) + 1;
		return month;
	}
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR) + 1;
		return year;
	}
}
