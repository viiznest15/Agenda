package app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import domain.Scheduler;

public class Program {

	public static void main(String[] args) throws ParseException {
		
//		for (String m : new String[] {"2019-01", "2019-02", "2019-03",
//	              "2019-04", "2019-05", "2019-06", "2019-07", "2019-08",
//	              "2019-09", "2019-10", "2019-11", "2019-12"}) {
//	      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
//	      Date date = format.parse(m);
//	      Calendar c = Calendar.getInstance();
//	      c.setTime(date);
//
//	      int start = c.get(Calendar.WEEK_OF_MONTH);
//
//	      c.add(Calendar.MONTH, 1);
//	      c.add(Calendar.DATE, -1);
//	      int end = c.get(Calendar.WEEK_OF_MONTH);
//	      System.out.println(" # of weeks in " + format.format(c.getTime())
//	              + ": " + (end - start + 1));
//	  }
		
		Scheduler matrix = new Scheduler();
		matrix.generateScheduler(5);
		
		for(String[][] table : matrix.getTables()){
			System.out.println(table.hashCode());
		}

	}
}
