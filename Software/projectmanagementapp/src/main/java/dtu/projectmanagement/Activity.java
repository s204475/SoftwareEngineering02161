package dtu.projectmanagement;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;

public class Activity {
	private String name;
	private Calendar startTime;
	private Calendar endTime;
	
	public Activity(String name, Calendar startTime, Calendar endTime) throws OperationNotAllowed {
		if (name.equals("")) {
			throw new OperationNotAllowed("An activity needs a name");
		} else {
			this.name = name;
		}
		this.startTime = startTime;
		this.endTime = endTime;
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

	public double getTimePassed() {
		if(endTime.before(Calendar.getInstance()))
		{
			return (double)ChronoUnit.MINUTES.between(startTime.toInstant(), endTime.toInstant())/60;
		} else
		{
			return (double)ChronoUnit.MINUTES.between(startTime.toInstant(), Calendar.getInstance().toInstant())/60;
		}
	}
}

