package domain;

import java.time.LocalDate;

public class Reservation {
	private String meetingName, lounge, days;
	private String[] hours;
	private LocalDate iniDat, finalDat;

	public Reservation(String meetingName, String lounge, LocalDate iniDat, LocalDate finalDat, String days, String[] hours) {
		this.meetingName = meetingName;
		this.lounge = lounge;
		this.days = days;
		this.hours = hours;
		this.iniDat = iniDat;
		this.finalDat = finalDat;
	}

	public Reservation(Reservation reserv1) {
		this.meetingName = reserv1.getMeetingName();
		this.lounge = reserv1.getLounge();
		this.days = reserv1.getDays();
		this.hours = reserv1.getHours();
		this.iniDat = reserv1.getIniDat();
		this.finalDat = reserv1.getFinalDat();	}

	public String getMeetingName() {
		return meetingName;
	}

	public String getLounge() {
		return lounge;
	}

	public String getDays() {
		return days;
	}

	public String[] getHours() {
		return hours;
	}

	public LocalDate getIniDat() {
		return iniDat;
	}

	public LocalDate getFinalDat() {
		return finalDat;
	}

	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

	public void setLounge(String lounge) {
		this.lounge = lounge;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public void setHours(String[] hours) {
		this.hours = hours;
	}

	public void setIniDat(LocalDate iniDat) {
		this.iniDat = iniDat;
	}

	public void setFinalDat(LocalDate finalDat) {
		this.finalDat = finalDat;
	}

	@Override
	public String toString() {
		return "Reservation [meetingName=" + meetingName + ", lounge=" + lounge + ", days=" + days + ", hours=" + hours
				+ ", iniDat=" + iniDat + ", finalDat=" + finalDat + "]\n";
	}

}
