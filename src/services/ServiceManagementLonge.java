package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Config;
import domain.Reservation;
import services.subservices.ServiceReservCreation;
import tools.Validator;

public class ServiceManagementLonge {

	public Map<String, List<Reservation>> getLoungesWithReserv(String fileName, Config config) {

		ServiceReservCreation serv = new ServiceReservCreation();
		
		// Cargamos en reservasList todas las reservas del fichero
		List<Reservation> reservList = serv.getReservListFromFile(fileName);
		
		// Uso de streams para ver las diferentes salas que hay en las peticiones
        List<String> loungesList = reservList.stream().map(reserv -> reserv.getLounge()).distinct().collect(Collectors.toList());

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
