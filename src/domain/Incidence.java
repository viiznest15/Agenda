package domain;

import java.time.LocalDate;

public class Incidence {
	private String activity, lounge, hours, conflict;
	private LocalDate date;

	public Incidence(String activity, String lounge, String hours, String conflict, LocalDate date) {
		this.activity = activity;
		this.lounge = lounge;
		this.hours = hours;
		this.conflict = conflict;
		this.date = date;
	}

	public String getActivity() {
		return activity;
	}

	public String getLounge() {
		return lounge;
	}

	public String getHours() {
		return hours;
	}

	public String getConflict() {
		return conflict;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public void setLounge(String lounge) {
		this.lounge = lounge;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public void setConflict(String conflict) {
		this.conflict = conflict;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Espacio: " +lounge+ " Dia: "+ date +" Hora: "+ hours + " Conflicto con: " + conflict+"\n";
	}

}
