package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Reserva;

public class ServicioGestionSalas {

	public Map<String, List<Reserva>> getSalasConReservas(String nomFichero) {

		ServicioCreacionReserva serv = new ServicioCreacionReserva();

		// El map nos guarda los distintos valores de sala del fichero
		Map<String, String> salasDist = serv.getReservaDesdeFichero(nomFichero);

		// La lista guarda esos valores de forma mas simple, teniendo asi un campo por
		// sala
		List<String> salasList = new ArrayList(salasDist.keySet());

		// Cargamos en reservasList todas las reservas del fichero
		List<Reserva> reservasList = serv.getListReservaDesdeFichero(nomFichero);

		// lista temporal para gestionar las listas dentro del map
		List<Reserva> temp = new ArrayList<Reserva>();
		Map<String, List<Reserva>> reservas = new HashMap<>();
		for (String sala : salasList) {
			for (Reserva reserva : reservasList) {
				if (reserva.getSala().equals(sala)) {
					temp.add(reserva);
				}
			}
			reservas.put(sala, new ArrayList<Reserva>(temp));
			temp.clear();
		}
		return reservas;
	}
}
