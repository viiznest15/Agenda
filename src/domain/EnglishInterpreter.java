package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnglishInterpreter extends AbsInterpreter implements Interpreter {

	public EnglishInterpreter(Map<String, String> codelang) {
		super(codelang);
	}

	@Override
	public Scheduler initialize(Scheduler table) {
		String[][] tmp = table.getTable();
		String[] weekDays = getWeekDays();
		String[] measureOfTime = getTimeMask();
		
		StringBuilder sb = new StringBuilder();
		sb.append(measureOfTime[2]);
		sb.append(table.getWeekCounter());
		
		
		tmp[0][0] = sb.toString();
		for(int i = 1; i<tmp[0].length; i++){
			tmp[0][i] = weekDays[i-1];
		}
		
		tmp[1][0] = measureOfTime[3];
		for(int i = 1; i < tmp.length-1; i++){
			tmp[i+1][0] = String.valueOf(i-1) + "-" + String.valueOf(i);
		}
		
		table.setTable(tmp);
		table.setDaysInTable();
		 
		 return table;
	}

	@Override
	public void write(List<Reservation> petitions, List<Scheduler> tables) {
		
		List<Reservation> closeReserv = (ArrayList<Reservation>)splitReserv(petitions,"Tancat", true);
		List<Reservation> meetingList = (ArrayList<Reservation>)splitReserv(petitions,"Tancat", false);
		
		
		
		
		
	}
	
	@Override
	public List<Reservation> splitReserv(List<Reservation> petition, String identifier, Boolean marker){
		List<Reservation> listReserv = new ArrayList<>();
		if(marker == true){
			for(Reservation r : petition){
				if(r.getMeetingName().equals(identifier)){
					listReserv.add(r);
				}
			}
		}
		else{
			for(Reservation r : petition){
				if(!r.getMeetingName().equals(identifier)){
					listReserv.add(r);
				}
			}
		}
		return listReserv;
	}
	
	@Override
	public int[] maskConversion(String days){
		char[] mask = days.toCharArray();
		int[] result = new int[6];
		for(int i = 0; i < mask.length; i++){
			if(mask[i] == 'L' | mask[i] == 'L'){
				result[i] = 1;
			}
			else if (mask[i] == 'M' | mask[i] == 'M'){
				result[i] = 2;
			}
			else if(mask[i] == 'C' | mask[i] == 'X'){
				result[i] = 3;
			}
			else if (mask[i] == 'J' | mask[i] == 'J'){
				result[i] = 4;
			}
			else if (mask[i] == 'V' | mask[i] == 'V'){
				result[i] = 5;
			}
			else if (mask[i] == 'S' | mask[i] == 'S'){
				result[i] = 6;
			}
			else if (mask[i] == 'G' | mask[i] == 'G'){
				result[i] = 7;
			}
		}
		
		return result;
	}
}
