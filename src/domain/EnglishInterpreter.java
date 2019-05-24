package domain;

import java.util.Map;

public class EnglishInterpreter extends AbsInterpreter implements Interpreter {

	public EnglishInterpreter(Map<String, String> codelang) {
		super(codelang);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[][] initialize(String[][] table) {
		String[][] tmp = table;
		String[] weekDays = getWeekDays();
		String[] measureOfTime = getTimeMask();
		
		table[0][0] = measureOfTime[2];
		 for (int i = 0; i < weekDays.length; i++) 
		 { System.arraycopy(weekDays[i], 0, tmp[i], 1, weekDays[0].length()-1); }
		 
		 return tmp;
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub

	}

}
