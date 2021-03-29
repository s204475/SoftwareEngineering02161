package dtu.projectmanagement;

import java.time.Duration;
import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
	private Duration estimatedTime;
	private Duration timeSpent;
	
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
	
	
}
