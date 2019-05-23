package domain;

import java.util.Map;

public class SpanishInterpreter extends AbsInterpreter implements Interpreter {
	
	StringBuilder sb;
	
	public SpanishInterpreter(Map<String, String> codelang, StringBuilder sb) {
		super(codelang);
		this.sb = sb;
	}

	@Override
	public void initialize(String[][] table) {
		String[] weekDays = getWeekDays();
		String[] measureOfTime = getTimeMask();
		for(int i = 0; i<2; i++){
			for(int j = 0; j<8; j++){
				if(table[i][j] == table[0][0]){
					table[0][0] = measureOfTime[2];
				}
				else{
					table[i][j] = weekDays[j];
				}
			}
		}
			
		
	}

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}
}
