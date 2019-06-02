package domain;

import java.util.List;
import java.util.Map;

public interface Interpreter {
	
	public Scheduler initialize(Scheduler table);
	
	public void write(List<Reservation> petitions, List<Scheduler> tables);
	
//	public void exchange();

}
