package app;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.CatalanInterpreter;
import domain.Config;
import domain.EnglishInterpreter;
import domain.Interpreter;
import domain.Reservation;
import domain.Scheduler;
import domain.SpanishInterpreter;
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
	public Interpreter itr;
	
	
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
	//Setting Interpreter
	public void setInterpreter(Interpreter inter){
		itr = inter;
	}
	
	public void configInterpreter(Config config){
		if(config.getOutputLanguage().equals("ENG")){
			setInterpreter(new EnglishInterpreter(servCodeLan.getLanguageCodes("ENG")));
		}
		else if(config.getOutputLanguage().equals("ES")){
			setInterpreter(new SpanishInterpreter(servCodeLan.getLanguageCodes("ES")));
		}
		else{
			setInterpreter(new CatalanInterpreter(servCodeLan.getLanguageCodes("CAT")));
		}
	}
	
	public Config getConfigFile(){
		return configuration;
	}
	
	public void initializeTable(){
		table =  itr.initialize(table);
	}
	
	public String[][] getTable(){
		return table;
	} 
	

}
