package app;

public class NewProgram {

	public static void main(String[] args) {
		OldController cont = new OldController();
		cont.setConfigFile();
		cont.setPetitions();
		cont.setSchedulerList(cont.getFactory());
		cont.schedulersToString();
		
		

	}

}
