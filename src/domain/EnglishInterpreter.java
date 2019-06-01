package domain;

import java.util.List;
import java.util.Map;

public class EnglishInterpreter extends AbsInterpreter implements Interpreter {

	public EnglishInterpreter(Map<String, String> codelang) {
		super(codelang);
	}

	@Override
	public String[][] initialize(String[][] table) {
		String[][] tmp = table;
		String[] weekDays = getWeekDays();
		String[] measureOfTime = getTimeMask();
		
		tmp[0][0] = measureOfTime[2];
		for(int i = 1; i<tmp[0].length; i++){
			tmp[0][i] = weekDays[i-1];
		}
		
		tmp[1][0] = measureOfTime[3];
		for(int i = 1; i < tmp.length-1; i++){
			tmp[i+1][0] = String.valueOf(i-1) + "-" + String.valueOf(i);
		}
		 
		 return tmp;
	}

	@Override
	public void write(List<Reservation> petitions, Scheduler table) {
		// TODO Auto-generated method stub
		
	}
}
