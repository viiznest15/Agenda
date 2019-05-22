package services;


import static tools.DateTools.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import domain.Reservation;

public class ServiceReservCreation {

	public Map<String, String> getReservFromFile(String nomFichero) {

		Map<String, String> reservas = Collections.emptyMap();

		File fichero = new File(nomFichero);

		try (Scanner sc = new Scanner(fichero)) {
			reservas = new HashMap<>();
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				Reservation reserva = getReserv(lin);
				reservas.put(reserva.getLounge(), "");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	public List<Reservation> getReservListFromFile(String nomFichero) {

		List<Reservation> reservas = Collections.emptyList();

		File fichero = new File(nomFichero);

		try (Scanner sc = new Scanner(fichero)) {
			reservas = new ArrayList<>();
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				Reservation reserva = getReserv(lin);
				reservas.add(reserva);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return reservas;
	}

	private Reservation getReserv(String lin) {

		final byte ESTADO = 0, SALA = 1, FECHAINI = 2, FECHAFIN = 3, DIAS = 4, HORAS = 5;

		String[] parts = lin.split(" ");

		String estado = parts[ESTADO];
		String sala = parts[SALA];
		
		Date fechaIni = string2Date(parts[FECHAINI]);
		Date fechaFin = string2Date(parts[FECHAFIN]);
		
		String dias = parts[DIAS];
		String horas = parts[HORAS];

		return new Reservation(estado, sala, fechaIni, fechaFin, dias, horas);
	}
}
