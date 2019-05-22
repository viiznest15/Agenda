package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Reservation;

public class ServiceManagementLonge {

	public Map<String, List<Reservation>> getSalasConReservas(String nomFichero) {

		ServiceReservCreation serv = new ServiceReservCreation();

		// El map nos guarda los distintos valores de sala del fichero
		Map<String, String> salasDist = serv.getReservFromFile(nomFichero);

		// La lista guarda esos valores de forma mas simple, teniendo asi un campo por
		// sala
		List<String> salasList = new ArrayList(salasDist.keySet());

		// Cargamos en reservasList todas las reservas del fichero
		List<Reservation> reservasList = serv.getReservListFromFile(nomFichero);

		// lista temporal para gestionar las listas dentro del map
		List<Reservation> temp = new ArrayList<Reservation>();
		Map<String, List<Reservation>> reservas = new HashMap<>();
		for (String sala : salasList) {
			for (Reservation reserva : reservasList) {
				if (reserva.getLounge().equals(sala)) {
					temp.add(reserva);
				}
			}
			reservas.put(sala, new ArrayList<Reservation>(temp));
			temp.clear();
		}
		return reservas;
	}
}
