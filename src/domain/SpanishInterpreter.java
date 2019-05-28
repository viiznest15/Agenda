package domain;

import java.util.Map;

public class SpanishInterpreter extends AbsInterpreter implements Interpreter {
	
	public SpanishInterpreter(Map<String, String> codelang) {
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
	public void write() {
		// TODO Auto-generated method stub
		
	}
}
