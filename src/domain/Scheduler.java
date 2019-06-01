package domain;

import java.time.Month;
import java.time.Year;
import java.util.Arrays;

public class Scheduler {
	private String[][] table = new String[26][8];
	private Year year;
	private Month month;
	private int weekCounter;
	
	public Scheduler(Year year, Month month, int weekCounter){
		this.year = year;
		this.month = month;
		
	}
	
	public String[][] getTable(){
		return table;
	}
	
	public void setTable(String[][] table){
		this.table = table;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month2) {
		this.month = month2;
	}
	
	public void setWeek(int week){
		weekCounter = week;
	}
	
	public int getWeekCounter(){
		return weekCounter;
	}
	
	public void tableToString(){
		String lineSeparator = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		
		for(String[] row: this.table){
			sb.append(Arrays.toString(row)).append(lineSeparator);
			
		}
		System.out.println(sb.toString());
		
	}
}
