package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import domain.Reserva;
import services.ServicioCreacionReserva;
import static utils.UtilesFecha.*;

public class Program {

	public static void main(String[] args) {
		ServicioCreacionReserva serv = new ServicioCreacionReserva();

		Map<String, Reserva> reservas = serv.getReservaDesdeFichero("input/peticions.txt");
		System.out.println("Reservas leidos:");

//		for (Reserva reserva : reservas.values()) {
//			Date fecha = reserva.getDatIni();
//			String diaSemana = getDayDate(fecha);
//			System.out.println(diaSemana);
//			int semana = getWeekDate(fecha);
//			System.out.println(semana);
//		}

//		for(int i=1;i<13;i++) {
//			System.out.println(getTotalWeekMonth(2019, i));
//			System.out.println("-------------------------------------------");
//		}

		Calendar cal = Calendar.getInstance();
		cal.set(2019, 0, 31);
//		System.out.println(cal.getActualMaximum(Calendar.WEEK_OF_MONTH));



		for (int i = 1; i < 13; i++) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
			Date date;
			try {
				date = format.parse("2019-" + i);
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				int start = c.get(Calendar.WEEK_OF_MONTH);

				c.add(Calendar.MONTH, 1);
				c.add(Calendar.DATE, -1);
				int end = c.get(Calendar.WEEK_OF_MONTH);
				System.out.println(" # of weeks in " + format.format(c.getTime()) + ": " + (end - start + 1));
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}
		System.out.println();
	}
}
