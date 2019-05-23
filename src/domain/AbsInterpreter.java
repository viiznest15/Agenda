package domain;

import java.util.Arrays;
import java.util.Map;

public abstract class AbsInterpreter {
	
//	private StringBuilder sb = new StringBuilder();
	private String schedule;
	private String[] weekDays;
	private String[] monthsYear;
	private String[] weekMask;
	private String[] measuresOfTime;
	private String generated;
	private String closeTag;
	private String error;
	
	public AbsInterpreter(Map<String, String> codelang){
		schedule = codelang.get(1);
		weekDays = codelang.get(2).split(",");
		weekMask = codelang.get(3).split(",");
		monthsYear = codelang.get(4).split(",");
		measuresOfTime = codelang.get(5).split(",");
		generated = codelang.get(6);
		closeTag = codelang.get(7);
		error = codelang.get(8);
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
	
//	public String getWeekDays(String args){
//		if(Arrays.asList(weekDays).contains(args)){
//			return weekDays[args];
//		}	
//	}

	public void setWeekDays(String[] weekDays) {
		this.weekDays = weekDays;
	}

	public String[] getMonthsYear() {
		return monthsYear;
	}

	public void setMonthsYear(String[] monthsYear) {
		this.monthsYear = monthsYear;
	}

	public String[] getWeekMask() {
		return weekMask;
	}

	public void setWeekMask(String[] weekMask) {
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
