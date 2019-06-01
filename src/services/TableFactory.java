package services;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import domain.Config;
import domain.Scheduler;
import static tools.subservices.DateTools.*;

public class TableFactory {

	private List<Scheduler> tables = new ArrayList<>();

	public void setSchedulers(int totalWeeksMonth, Config configurationInput) {

		for (int i = 0; i <= totalWeeksMonth; i++) {

			tables.add(new Scheduler(Year.of(configurationInput.getYear()), Month.of(configurationInput.getMonth()),
					getDateCalc(configurationInput)));
		}

	}

	private int getDateCalc(Config config) {

		int weeks = getWeekOfTheYear(ints2LocalDate(config.getMonth(), config.getYear()));

		return weeks;
	}

	public List<Scheduler> getSchedulerList() {
		return tables;
	}

}
