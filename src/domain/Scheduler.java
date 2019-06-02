package domain;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.Arrays;
import java.util.Date;

import static tools.subservices.DateTools.*;

public class Scheduler {
	private String[][] table = new String[26][8];
	private Year year;
	private Month month;
	private int weekCounter;
	
	public Scheduler(Year year, Month month, int weekCounter){
		this.year = year;
		this.month = month;
		this.weekCounter = weekCounter;
		
	}
	
	//final initializers for table ****************************************************
	
	public void setDaysInTable(){
		LocalDate date = ints2LocalDate(month.getValue(),year.getValue());
		Date sameDate = convertToDateViaInstant(date);
		
		while(getWeekOfTheYear(sameDate) != weekCounter){
			date = getFirstDayOfNextWeek(date);
			sameDate = convertToDateViaInstant(date);
		}
		
		int days = date.getDayOfMonth();
		if(getWeekOfTheYear(sameDate) == weekCounter){
			int x = getDayInWeek(date);
			for(int i = x; i < table[1].length; i++ ){
				if(days <= getLastDayMonth(year.getValue(),month.getValue())){
					table[1][i] = String.valueOf(days);
					days++;
				}
				else{
					break;
				}
			}
		}
	}
	
	//operators for table ********************************************************************
	
	public void populateTableWithClosed(Reservation reserve, int[]days){
		LocalDate initDate = reserve.getIniDat();
		LocalDate finalDate = reserve.getFinalDat();
		
		Date initDate2 = convertToDateViaInstant(initDate);
		Date finalDate2 = convertToDateViaInstant(finalDate);
		
		while(getWeekOfTheYear(initDate2) != weekCounter){
			initDate = getFirstDayOfNextWeek(initDate);
			initDate2 = convertToDateViaInstant(initDate);
		}
		
		int[] hoursRange = evaluateReserv(reserve);
		
		if(getWeekOfTheYear(initDate2) == weekCounter){
			for(int i = 0; i < hoursRange.length; i++){
				
			}
		}
	}
	
	public void populateTableWithMeeting(Reservation reserve, int[]days){
		LocalDate initDate = reserve.getIniDat();
		LocalDate finalDate = reserve.getFinalDat();
		
		Date initDate2 = convertToDateViaInstant(initDate);
		Date finalDate2 = convertToDateViaInstant(finalDate);
		
		while(getWeekOfTheYear(initDate2) != weekCounter){
			initDate = getFirstDayOfNextWeek(initDate);
			initDate2 = convertToDateViaInstant(initDate);
		}
		
		
		
		if(getWeekOfTheYear(initDate2) == weekCounter){
//			for(int i = 0; i < table[])
		}
		
		
		
	}
	
	private int[] evaluateReserv(Reservation reserve){
		
		if(reserve.getMeetingName().equals("Tancat") | 
				reserve.getMeetingName().equals("Cerrado") | 
				reserve.getMeetingName().equals("Closed")){
			
						return getClosedRange(reserve);
		}else{
						return getHoursRange(reserve);
		}
		
	}
	
	private int[] getClosedRange(Reservation reserve){
		StringBuilder sb = new StringBuilder();
		sb.append(reserve.getHours());
		sb.replace(sb.indexOf("_"), sb.indexOf("_"), "-");
		String str = sb.toString();
		String[] rangeStr = str.split("-");
		
		int[] range = new int[4];
		
		for(int i = 0; i < rangeStr.length; i++){
			range[i]=(Integer.parseInt(rangeStr[i]) + 2);
		}
		
		return range;
	}
	
	private int[] getHoursRange(Reservation reserve){
		String[] str = reserve.getHours().split("-");
		int[] range = new int[2];
		
		for(int i = 0; i < str.length; i++){
			range[i]=(Integer.parseInt(str[i]) + 2);
		}
		
		return range;
	}
	
	//*************************************************************************
	
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
//		System.out.println(getWeekCounter());
		
	}
}
