package dtu.projectManagement;

import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
//	private Duration estimatedTime;
//	private Duration timeSpent;
	
	public Task (String name, Duration estimatedTime) {
		this.name = name;
		this.startTime = dateServer.getDate();
		this.estimatedTime = estimatedTime;
		
		
	}
	
//	public Duration getRemainingTime() {
//		return estimatedTime - timeSpent;
//	}
	
	
	
}
