package javaUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class GetdatafromJavaFile {
	public int random(int boundary) {
		Random ran = new Random();
		int rand = ran.nextInt(boundary);
		return rand;
	}
	
	public String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("MM-dd-YYYY");
		return sim.format(date);
	}
	
	public String getRequiredDate(int days) {
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("MM-dd-YYYY");
		sim.format(date);
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, days);
		return sim.format(cal.getTime());
	}

}
