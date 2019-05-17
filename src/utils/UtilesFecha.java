package utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
}
