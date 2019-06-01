package services.subservices;

import static tools.subservices.DateTools.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import domain.Reservation;

public class ServiceReservCreation {

	public Map<String, String> getReservFromFile(String fileName) {

		Map<String, String> reservations = Collections.emptyMap();

		File file = new File(fileName);

		try (Scanner sc = new Scanner(file)) {
			reservations = new HashMap<>();
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				Reservation reserv = getReserv(lin);
				reservations.put(reserv.getLounge(), "");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	public List<Reservation> getReservListFromFile(String fileName) {

		List<Reservation> reservations = Collections.emptyList();

		File file = new File(fileName);

		try (Scanner sc = new Scanner(file)) {
			reservations = new ArrayList<>();
			while (sc.hasNextLine()) {
				String lin = sc.nextLine();
				Reservation reserv = getReserv(lin);
				reservations.add(reserv);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return reservations;
	}

	private Reservation getReserv(String lin) {

		final byte MEETINGNAME = 0, LOUNGE = 1, INIDAT = 2, FINALDAT = 3, DAYS = 4, HOURS = 5;

		String[] parts = lin.split(" ");

		String meetingName = parts[MEETINGNAME];
		String lounge = parts[LOUNGE];

		LocalDate iniDat = getLocalDateFromInts(parts[INIDAT]);
		LocalDate finalDat = getLocalDateFromInts(parts[FINALDAT]);

		String days = parts[DAYS];
		String hours = parts[HOURS];

		return new Reservation(meetingName, lounge, iniDat, finalDat, days, hours);
	}

}
