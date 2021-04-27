package dtu.projectmanagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

//Does general calculation and conversions unrelated to the individual classes

public class Helper {

	
	public GregorianCalendar dateToCalendar(Date date) {

		GregorianCalendar calendar = (GregorianCalendar) Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }
	
	public String calendarToString(Calendar calendar)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-dd/MM/yyyy");

		return sdf.format(calendar.getTime());
	}

	public boolean isDate(String dateString) {
		try {
			convertDate(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	public Date convertDate(String dateString) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("HH-mm-dd-MM-yyyy", Locale.ENGLISH);

		Date date = sdf.parse(dateString);
		return date;
	}
	
}
