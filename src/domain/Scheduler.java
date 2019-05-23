package domain;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import services.TableFactory;

public class Scheduler {
	
	private List<String[][]> Tables = new ArrayList<>();
	private TableFactory factory = new TableFactory();
	private Year year;
	private Month month;
	
	public void generateScheduler(int weeks){
		for(int i = 0; i<= weeks; i++){
			Tables.add(factory.createTable());
		}
	}
	
	public List<String[][]> getTables() {
		return Tables;
	}
	
	public void setTables(List<String[][]> tables) {
		Tables = tables;
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
