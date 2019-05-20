package domain;

import java.util.ArrayList;
import java.util.List;

import services.TableFactory;

public class Scheduler {
	
	List<String[][]> Tables = new ArrayList<>();
	TableFactory factory = new TableFactory();
	
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
	
	

}
