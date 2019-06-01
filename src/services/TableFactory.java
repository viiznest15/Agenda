package services;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import domain.Config;
import domain.Scheduler;
import tools.subservices.DateTools;

import static tools.subservices.DateTools.*;

public class TableFactory {

	private List<Scheduler> tables = new ArrayList<>();

	public void setSchedulers(int totalWeeksMonth, Config configurationInput) {
		
		Date date = DateTools.ints2Date(configurationInput.getMonth(), 
				configurationInput.getYear());

		for (int i = 0; i < totalWeeksMonth; i++) {

			tables.add(new Scheduler(Year.of(configurationInput.getYear()), 
					Month.of(configurationInput.getMonth()),
						getDateCalc(date)));
			LocalDate ld = convertToLocalDateViaInstant(date);
			date = convertToDateViaInstant(getFirstDayOfNextWeek(ld));
		}

	}
	
	private int getDateCalc(Date input) {
		
		int weeks = getWeekOfTheYear(input);

		return weeks;
	}

	public List<Scheduler> getSchedulerList() {
		return tables;
	}

}
