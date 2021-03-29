package dtu.projectManagement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
	private String title;
	private int id;
	private Calendar startTime; 
	private int budgetTime;
	private Employee projectManager;
	
	private DateServer dateServer = new DateServer();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Project(String title, int id) {
		this.title = title;
		this.id = id;
		this.startTime = dateServer.getDate();
	}
	
	public void setBudgetTime(int budgetTime) {
		this.budgetTime=budgetTime;
	}
	
	public int getBudgetTime() {
		return budgetTime;
	}
	
	public void createTask(String title, Duration estimatedTime) {
		Task task = new Task(title, estimatedTime);
		tasks.add(task);
	}
	
	public void assignProjectManager(Employee employee) {
		projectManager = employee;
	}
	
	public double getEstimatedTime() {
		double estimatedTimeInHours=0.0;
		for(Task task : tasks) {
			estimatedTimeInHours+=(double)task.getEstimatedTime().toMinutes()/60;
		}
		return estimatedTimeInHours;
	}
	
	public double getRemaningTime() {
		double remaningTimeInHours=0.0;
		for(Task task : tasks) {
			remaningTimeInHours+=(double)task.getRemainingTime().toMinutes()/60;
		}
		return remaningTimeInHours;
	}
	
}
