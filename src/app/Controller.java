package app;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Config;
import domain.Interpreter;
import domain.Reservation;
import domain.Scheduler;
import services.ServiceCodeLanguage;
import services.ServiceConfigCreation;
import services.ServiceManagementLonge;
import services.ServiceReservCreation;
import tools.DateTools;

public class Controller {
	
	//variables locales
	private Map<String, List<Reservation>> peticiones = new HashMap<>();
	private List<String[][]> allTables = new ArrayList<>();
	private String[][] table;
	private Scheduler tablas = new Scheduler();
	private Config configuration;
	private Interpreter inter;
	
	
	//adquisicion de los servicios
	ServiceReservCreation servReserv = new ServiceReservCreation();//not used
	ServiceManagementLonge servSalas = new ServiceManagementLonge();
	ServiceConfigCreation servConf = new ServiceConfigCreation();
	ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();
	
	public void setPeticiones(){
		peticiones = servSalas.getSalasConReservas("input/peticiones.txt");
	}
	
	public void setConfigFile(){
		configuration = servConf.getConf("input/config.txt");
	}
	
	public void setTablesProperties(){
		tablas.setMonth(Month.of(configuration.getMonth()));
		tablas.setYear(Year.of(configuration.getYear()));
	}
	
	public void generateTables(){
		tablas.generateScheduler(DateTools.
				getTotalWeekMonth(configuration.getMonth(),
						configuration.getYear()));
	}
	
	 
	

}
