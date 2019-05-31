package tools;

import domain.Config;
import domain.Reservation;

public class Validator {

	public boolean validateMonth(Reservation reserv, Config config) {
		return between(config.getMonth(), reserv.getIniDat().getMonthValue(), reserv.getFinalDat().getMonthValue());
	}

	public boolean validateYear(Reservation reserv, Config config) {
		return between(config.getYear(), reserv.getIniDat().getYear(), reserv.getFinalDat().getYear());
	}

	private boolean between(int i, int minValueInclusive, int maxValueInclusive) {
		return i >= minValueInclusive && i <= maxValueInclusive;
	}
}
