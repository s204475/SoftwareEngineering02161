package dtu.projectmanagement;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Activity {
	private String name;
	private Calendar startTime;
	private Calendar endTime;
	
	public Activity(String name, Calendar startTime2, Calendar endTime2) throws OperationNotAllowed {
		if (name.equals("")) {
			throw new OperationNotAllowed("An activity needs a name");
		} else {
			this.name = name;
		}
		this.startTime = startTime2;
		this.endTime = endTime2;
	}
	
	public Calendar getEndTime() {
		return endTime;
	}
	
	public Calendar getStartTime() {
		return startTime;
	}
	
	public String getStartTimeString()
	{
		return startTime.toString();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String newName) {
		name = newName;
	}
}

