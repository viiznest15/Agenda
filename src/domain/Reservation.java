package domain;

import java.util.Date;

public class Reservation {
	private String meetingName, lounge, days, hours;
	private Date iniDat, finalDat;

	public Reservation(String meetingName, String lounge, Date iniDat, Date finalDat, String days, String hours) {

		this.lounge = lounge;
		this.days = days;
		this.hours = hours;
		this.iniDat = iniDat;
		this.finalDat = finalDat;
	}

	public String getMeetingName() {
		return meetingName;
	}

	public String getLounge() {
		return lounge;
	}

	public String getDays() {
		return days;
	}

	public String getHours() {
		return hours;
	}

	public Date getIniDat() {
		return iniDat;
	}

	public Date getFinalDat() {
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

	public void setHours(String hours) {
		this.hours = hours;
	}

	public void setIniDat(Date iniDat) {
		this.iniDat = iniDat;
	}

	public void setFinalDat(Date finalDat) {
		this.finalDat = finalDat;
	}

	@Override
	public String toString() {
		return "Reservation [meetingName=" + meetingName + ", lounge=" + lounge + ", days=" + days + ", hours=" + hours
				+ ", iniDat=" + iniDat + ", finalDat=" + finalDat + "]\n";
	}

}
