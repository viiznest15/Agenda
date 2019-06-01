package app;

import java.util.ArrayList;
import java.util.Arrays;
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
import services.TableFactory;
import tools.DateTools;

public class OldController {
	
	//variables de clase
	private Config configuration;
	public Interpreter itr;
	private Map<String, List<Reservation>> petitions = new HashMap<>();
	private List<Scheduler> schedulerList = new ArrayList<>();
	private String[][] table = new String[26][8];
	private TableFactory factory = new TableFactory();

	
	
	//adquisicion de los servicios
	ServiceReservCreation servReserv = new ServiceReservCreation();//not used
	ServiceManagementLonge servSalas = new ServiceManagementLonge();
	ServiceConfigCreation servConf = new ServiceConfigCreation();
	ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();
	
	
	//INITIALAZIERS
	public void setConfigFile(){
		configuration = servConf.getConf("input/config.txt");
	}
	
	public void setPetitions(){
		petitions = servSalas.getSalasConReservas("input/peticiones.txt");
	}
	
	public void setSchedulerList(TableFactory factory){
		factory.setSchedulers(DateTools.getTotalWeekMonth(configuration.getMonth(), 
				configuration.getYear()),getConfigFile());
		
		schedulerList = factory.getSchedulerList();
	}
	
//	public void setSchedulerProperties(){
//		tablas.setMonth(Month.of(configuration.getMonth()));
//		tablas.setYear(Year.of(configuration.getYear()));
//	}
	

	//Setting Interpreter
	private void setInterpreter(Interpreter inter){
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
	
	public TableFactory getFactory(){
		return factory;
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
	
	public void tableToString(){
		String lineSeparator = System.lineSeparator();
		StringBuilder sb = new StringBuilder();
		
		for(String[] row: this.table){
			sb.append(Arrays.toString(row)).append(lineSeparator);
			
		}
		System.out.println(sb.toString());
		
	}
	
	public void schedulersToSring(){
		schedulerList.forEach(scheduler -> scheduler.tableToString());
	}
	

}
