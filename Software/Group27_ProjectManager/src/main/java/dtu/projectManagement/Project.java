package dtu.projectManagement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
	private String title;
	private int id;
	private Calendar startTime; 
	
	private Employee projectManager;
	
	private DateServer dateServer = new DateServer();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	public Project(String title, int id) {
		this.title = title;
		this.id = id;
		this.startTime = dateServer.getDate();
	}
	
	public void createTask(String title, Duration estimatedTime) {
		Task task = new Task(title, estimatedTime);
		tasks.add(task);
	}
	
	public void assignProjectManager(Employee employee) {
		projectManager = employee;
	}
	
	
	
	
	
	
	
	
	

}
