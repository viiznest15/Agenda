package services;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import domain.Config;
import domain.Incidence;

public class IncidenceManager {

	public void WriteLog(List<Incidence> incidences, Config config) {

		Stream<Incidence> stream = incidences.stream();
		List<Incidence> activities = stream.filter(distinctByKey(Incidence::getActivity)).collect(Collectors.toList());

		try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("output/incidencies.log"))) {
			dos.writeBytes("#Resumen actividades " + config.getMonth() + "/" + config.getYear() + "\n");
			for (Incidence activity : activities) {
				dos.writeBytes("#Actividad " + activity.getActivity() + "\n");
				for (Incidence inci : incidences) {
					if (activity.getActivity().equals(inci.getActivity()))
						dos.writeBytes(inci.toString());
				}
				dos.writeBytes("\n");
			}
			dos.writeBytes("-------> Total: 10 / 12 h assignades. (No Assignades: 4 h)");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new ConcurrentHashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

}
