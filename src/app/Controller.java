package app;

import java.util.*;

import domain.CatalanInterpreter;
import domain.Config;
import domain.EnglishInterpreter;
import domain.Incidence;
import domain.Interpreter;
import domain.Reservation;
import domain.Scheduler;
import domain.SpanishInterpreter;
import services.subservices.ServiceCodeLanguage;
import services.subservices.ServiceConfigCreation;
import services.ServiceManagementLonge;
import services.TableFactory;
import tools.subservices.DateTools;

public class Controller {
	
	//variables de clase
		private Config configuration;
		public Interpreter itr;
		private Map<String, List<Reservation>> petitions = new HashMap<>();
		private List<Scheduler> schedulerList = new ArrayList<>();
		private List<Incidence> incidences = new ArrayList<>();
		private TableFactory factory = new TableFactory();
		
		//adquisicion de los servicios
		ServiceManagementLonge servSalas = new ServiceManagementLonge();
		ServiceConfigCreation servConf = new ServiceConfigCreation();
		ServiceCodeLanguage servCodeLan = new ServiceCodeLanguage();
		
		//INITIALAZIERS
		public void setConfigFile(){
			configuration = servConf.getConf("input/config.txt");
		}
		
		public void setPetitions(){
			petitions = servSalas.getLoungesWithReserv("input/peticions.txt", configuration);
		}
		
		public void setSchedulerList(TableFactory factory){
			factory.setSchedulers(DateTools.getTotalWeekMonth(configuration.getMonth(), 
					configuration.getYear()),getConfigFile());
			
			schedulerList = factory.getSchedulerList();
		}
		
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
		
		public void initializeTables(){
			for(Scheduler s : schedulerList){
				itr.initialize(s);
			}
		}
		
		//Getters & Setters**********************************
		
		public TableFactory getFactory(){
			return factory;
		}
		
		public Config getConfigFile(){
			return configuration;
		}
		
		public List<Incidence> getIncidence(){
			return incidences;
		}
		
		public void setIncidences(List<Incidence> incidences){
			this.incidences = incidences;
		}
		
		public List<Scheduler> getSchedulerList(){
			return schedulerList;
		}
		
		public void schedulersToString(){
			schedulerList.forEach(scheduler -> scheduler.tableToString());
			System.out.println("******************************************");
		}

}
