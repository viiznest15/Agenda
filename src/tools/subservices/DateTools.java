package tools.subservices;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTools {

	public static Date incFecha(Date date, int days) {
		long mseg = date.getTime();// ms transcurridos desde 1/1/70
		long futureDate = mseg + (days * 1000L * 60 * 60 * 24);
		return new Date(futureDate);
	}

	public static Date ints2Date(int d, int m, int a) {
		String date = d + "/" + m + "/" + a;
		return string2Date(date);
	}

	public static Date ints2Date(int m, int a) {
		String date = 1 + "/" + m + "/" + a;
		return string2Date(date);
	}

	public static String prettyPrint(Date date) {
		return prettyPrint(date, "dd/MM/yyyy");
	}

	public static String prettyPrint(Date date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		return df.format(date);
	}

	/**
	 * Este metodo asume que el String proporcionado sigue el patron dd/MM/yyyy
	 * 
	 * @param date
	 * @return Un objeto Date o lanza RuntimeException si no se cumple el formato
	 *         dd/MM/yyyy
	 */
	public static Date string2Date(String date) {
		return string2Date(date, "dd/MM/yyyy");
	}

	/**
	 * Este metodo no asume ningun patron de formato
	 * 
	 * @param date formatoFecha
	 * @return Un objeto Date o lanza RuntimeException si no se cumple el formato
	 *         dd/MM/yyyy
	 */
	public static Date string2Date(String date, String pattern) {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException("El string proporcionado, no se ajusta al patron indicado!");
		}
	}

	public static String getDayName(Date date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("u"); // dia de la semana
		String day = (String) simpleDateformat.format(date);
		return day;
	}

	public static int getWeekOfTheYear(Date date) {
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("w"); // w --> numero semana year
		int day = Integer.parseInt(simpleDateformat.format(date));
		return day;
	}
//Este metodo nos ayuda a calcular la posicion de los dias en la matriz
	public static int getDayInWeek(Date date) {
//		Date date = ints2Date(1, month, year);
		Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);        
		return calendar.get(Calendar.DAY_OF_WEEK-1);
	}

	// necesario para tamaño matriz
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

	// Se utiliza para la clase Validator, para las fechas que no son del mes de
	// config
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
