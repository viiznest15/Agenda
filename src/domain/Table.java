package domain;

import java.util.Arrays;

public class Table {

	private String[][] table = new String[26][8];
//	private int year;
//	private int month;
//	private static int monthCont;
//	private String[] daysName;
//	private String[] daysNumber;
	private String week;

	public Table(String week, String[] daysName, String[] daysNumber) {
		this.week = week;
		table[0][0] = week;
		for (int i = 2; i < table.length; i++)
			table[i][0] = (i - 2) + "-" + (i - 1);
		
		for (int j = 1; j < table[0].length; j++) 			
			table[0][j] = daysName[j-1];
		
		for (int j = 1; j < table[0].length; j++) 			
			table[1][j] = daysNumber[j-1];
		
	}

	public String[][] getTable() {
		return table;
	}

	public String getWeek() {
		return week;
	}

	public void setTable(String[][] table) {
		this.table = table;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	@Override
	public String toString() {
		return "Table [table=" + Arrays.toString(table) + ", week=" + week + "]";
	}

}
