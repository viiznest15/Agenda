package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Config;
import domain.Reservation;
import services.subservices.ServiceReservCreation;
import tools.Validator;

public class ServiceManagementLonge {

	public Map<String, List<Reservation>> getSalasConReservas(String nomFichero, Config config) {

		ServiceReservCreation serv = new ServiceReservCreation();

		// El map nos guarda los distintos valores de sala del fichero
		Map<String, String> diffLounges = serv.getReservFromFile(nomFichero);

		// La lista guarda esos valores de forma mas simple, teniendo asi un campo por
		// sala
		List<String> loungesList = new ArrayList<String>(diffLounges.keySet());

		// Cargamos en reservasList todas las reservas del fichero
		List<Reservation> reservList = serv.getReservListFromFile(nomFichero);

		// lista temporal para gestionar las listas dentro del map
		List<Reservation> temp = new ArrayList<Reservation>();
		
		// Se crea el objeto validator par vereficar las fechas de las peticiones que entran al programa.
		Validator validator = new Validator();
		
		Map<String, List<Reservation>> reservations = new HashMap<>();
		for (String lounge : loungesList) {
			for (Reservation reserv : reservList) {
				if (reserv.getLounge().equals(lounge) && validator.validateMonth(reserv, config)) {
					temp.add(reserv);
				}
			}
			reservations.put(lounge, new ArrayList<Reservation>(temp));
			temp.clear();
		}
		return reservations;
	}
}
