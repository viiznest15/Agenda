package services;


public class TableFactory {
	
	private String[][] table;
	
	public String[][] createTable(){
		setTable(new String[26][8]);
		return getTable();
	}

	private String[][] getTable() {
		return table;
	}

	private void setTable(String[][] table) {
		this.table = table;
	}
	
	
	
	

}
