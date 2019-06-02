package domain;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class AbsInterpreter {
	
//	private StringBuilder sb = new StringBuilder();
	private String schedule;
	private String[] weekDays = new String[6];
	private String[] monthsYear = new String[11];
	private char[] weekMask = new char[6];
	private String[] measuresOfTime = new String[3];
	private String generated;
	private String closeTag;
	private String error;
	
	public AbsInterpreter(Map<String, String> codelang){
		
		schedule = codelang.get("001");
		generated = codelang.get("006");
		closeTag = codelang.get("007");
		error = codelang.get("008");
		
		String str = codelang.get("002");
		weekDays = str.split(",");
		
		str = codelang.get("003");
		weekMask = str.toCharArray();
		
		str = codelang.get("004");
		monthsYear = str.split(",");
		
		str = codelang.get("005");
		measuresOfTime = str.split(",");
		

	}


	public String getTitle() {
		return schedule;
	}

	public void setTitle(String title) {
		this.schedule = title;
	}

	public String[] getWeekDays() {
		return weekDays;
	}
	
	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	public String[] getMonthsYear() {
		return monthsYear;
	}

	public void setMonthsYear(String[] monthsYear) {
		this.monthsYear = monthsYear;
	}

	public char[] getWeekMask() {
		return weekMask;
	}

	public void setWeekMask(char[] weekMask) {
		this.weekMask = weekMask;
	}

	public String[] getTimeMask() {
		return measuresOfTime;
	}

	public void setTimeMask(String[] timeMask) {
		this.measuresOfTime = timeMask;
	}

	public String getOwnerInf() {
		return generated;
	}

	public void setOwnerInf(String ownerInf) {
		this.generated = ownerInf;
	}

	public String getCloseTag() {
		return closeTag;
	}

	public void setCloseTag(String closeTag) {
		this.closeTag = closeTag;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
	
}
