package services.nivelbajo;


import static tools.nivelbajo.DateTools.*;

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

		final byte MEETINGNAME = 0, LOUNGE = 1, INIDAT = 2, FINALDAT = 3, DAYS = 4, HOURS = 5;

		String[] parts = lin.split(" ");

		String meetingName = parts[MEETINGNAME];
		String lounge = parts[LOUNGE];
		
		Date iniDat = string2Date(parts[INIDAT]);
		Date finalDat = string2Date(parts[FINALDAT]);
		
		String days = parts[DAYS];
		String hours = parts[HOURS];

		return new Reservation(meetingName, lounge, iniDat, finalDat, days, hours);
	}
}
