package tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

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

	public static String getDayDate(int anyo, int mes) { // Sobrecarga teniendo en cuenta "config.txt"
		Date fecha = ints2Date(mes, anyo);
		return getDayDate(fecha);
	}

	public static int getWeekDate(Date fecha) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("w"); // w --> numero semana anyo
		int dia = Integer.parseInt(simpleDateformat.format(fecha));
		return dia;
	}

	public static int getTotalWeekMonth(int mes, int anyo) {

		Date date = ints2Date(1, mes, anyo);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int start = c.get(Calendar.WEEK_OF_MONTH);

		c.add(Calendar.MONTH, 1);
		c.add(Calendar.DATE, -1);
		int end = c.get(Calendar.WEEK_OF_MONTH);

		return end - start + 1;
	}

	public static int getLastDayMonth(int anyo, int mes) {
		Date fecha = ints2Date(1, mes, anyo);
		Calendar cal = Calendar.getInstance();
		cal.setTime(fecha);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int mes = cal.get(Calendar.MONTH) + 1;
		return mes;
	}
}
