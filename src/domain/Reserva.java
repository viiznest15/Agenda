package domain;

public class Reserva {
	private String nomReunion, sala, dias, horas;
	private String datIni, datFin;

	public Reserva(String nomReunion, String sala, String dias, String horas, String datIni, String datFin) {

		this.sala = sala;
		this.dias = dias;
		this.horas = horas;
		this.datIni = datIni;
		this.datFin = datFin;
	}

	public String getNomReunion() {
		return nomReunion;
	}

	public String getSala() {
		return sala;
	}

	public String getDias() {
		return dias;
	}

	public String getHoras() {
		return horas;
	}

	public String getDatIni() {
		return datIni;
	}

	public String getDatFin() {
		return datFin;
	}

	public void setNomReunion(String nomReunion) {
		this.nomReunion = nomReunion;
	}

	public void setSala(String sala) {
		this.sala = sala;
	}

	public void setDias(String dias) {
		this.dias = dias;
	}

	public void setHoras(String horas) {
		this.horas = horas;
	}

	public void setDatIni(String datIni) {
		this.datIni = datIni;
	}

	public void setDatFin(String datFin) {
		this.datFin = datFin;
	}

	@Override
	public String toString() {
		return "Reserva [sala=" + sala + ", dias=" + dias + ", horas=" + horas + ", datIni=" + datIni + ", datFin="
				+ datFin + "]";
	}

}
