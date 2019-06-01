package domain;

import java.util.List;

public interface Interpreter {
	
	public String[][] initialize(String[][] table);
	
	public void write(List<Reservation> petitions, Scheduler table);

}
