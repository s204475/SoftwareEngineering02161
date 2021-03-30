package dtu.projectManagement;

import java.time.Duration;
import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
	private Duration estimatedTime;
	private Duration timeSpent; //Should it not be an int=?
	
	public Task (String name, Duration estimatedTime) {
		this.name = name;
		this.startTime = dateServer.getDate();
		this.estimatedTime = estimatedTime;
		this.timeSpent = Duration.ofMinutes(0);		
	}
	
	public Duration getEstimatedTime() {
		return estimatedTime;
	}
	
	public void setTimeSpent(Duration timeSpent) {
		this.timeSpent = this.timeSpent.plus(timeSpent);
	}
	
	public Duration getRemainingTime() {
		return estimatedTime.minus(timeSpent);
	}

	public String getName()
	{
		return name;
	}

	public Calendar getStartTime()
	{
		return startTime;
	}

	public int getTimeSpent() //Not implemented - should it be an int?
	{
		return 1;
	}
	
	
	
}
