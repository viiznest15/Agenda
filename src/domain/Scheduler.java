package domain;

import java.time.Month;
import java.time.Year;

public class Scheduler {
	private String[][] table = new String[26][8];
	private Year year;
	private Month month;
	
	
	
	
	
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
}
