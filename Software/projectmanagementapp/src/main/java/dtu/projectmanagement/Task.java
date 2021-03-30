package dtu.projectmanagement;

import java.time.Duration;
import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
	private Duration estimatedTime;
	private Duration timeSpent; //should be an integer, right? Class diagram is wrong, tænker jeg (Victor)
	
	public Task (String name, Duration estimatedTime) {
		this.name = name;
		this.startTime = dateServer.getDate();
		this.estimatedTime = estimatedTime;
		this.timeSpent = Duration.ofHours(0);
		
	}
	
	public void setTimeSpent(Duration timeSpent) {
		this.timeSpent = this.timeSpent.plus(timeSpent);
	}
	
	public Duration getRemainingTime() {
		return estimatedTime.minus(timeSpent);
	}
	
	public Duration getEstimatedTime() {
		return estimatedTime;
	}

	public String getName() {
		return name;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public int getTimeSpent() { //should be an integer, right? should not return 1. Only for testing
		return 1;
	}
	
	
}
