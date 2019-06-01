package domain;

import static tools.DateTools.*;

public class Validator {

	public boolean validateMonth(Reservation reserv, Config config) {

		return getMonth(reserv.getIniDat()) == (config.getMonth())
				|| getMonth(reserv.getFinalDat()) == (config.getMonth());

	}
}
