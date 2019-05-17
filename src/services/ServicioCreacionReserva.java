package services;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import domain.Reserva;
import static utils.UtilesFecha.*;

public class ServicioCreacionReserva {
	public Map<String,Reserva> getReservaDesdeFichero(String nomFichero) {

		Map<String, Reserva> reservas = Collections.emptyMap();

		File fichero = new File(nomFichero);

		try (Scanner sc = new Scanner(fichero)) {
			reservas = new HashMap<>();
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				Reserva reserva = crearReserva(lin);
				reservas.put(reserva.getSala() ,reserva);
			}
		}
	catch(FileNotFoundException e){
		e.printStackTrace();
	}
	return reservas;
}

	private Reserva crearReserva(String lin) {

		final byte ESTADO = 0, SALA = 1, FECHAINI = 2, FECHAFIN = 3, DIAS = 4, HORAS = 5;

		String[] parts = lin.split(" ");

		String estado = parts[ESTADO];
		String sala = parts[SALA];
		
		Date fechaIni = string2Date(parts[FECHAINI]);
		Date fechaFin = string2Date(parts[FECHAFIN]);
		
		String dias = parts[DIAS];
		String horas = parts[HORAS];

		return new Reserva(estado, sala, fechaIni, fechaFin, dias, horas);
	}
}
