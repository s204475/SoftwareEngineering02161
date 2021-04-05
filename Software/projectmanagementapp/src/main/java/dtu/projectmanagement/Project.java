package dtu.projectmanagement;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.time.Duration;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.function.BooleanSupplier;


public class Project {
	private String title;
	private String id;
	private Calendar startTime; 
	private int budgetTime;

	private Employee projectManager;
	
	private DateServer dateServer = new DateServer();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	public Project(String title) throws OperationNotAllowed {
		if (title == "") {
			throw new OperationNotAllowed("A project needs a name");
		} else {
			this.title = title;
		}
		this.id = setId();
		this.startTime = dateServer.getDate();
		
	}
	
	public void setBudgetTime(int budgetTime) {
		this.budgetTime=budgetTime;
	}
	
	public int getBudgetTime() {
		return budgetTime;
	}
	
	public void assignProjectManager(Employee employee) {
		projectManager = employee;
	}
	public String setId() { 
		id = Year.now().format(DateTimeFormatter.ofPattern("yy")) + "0001"; // IMPLEMENTER LØBENUMMER
		return id;
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

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Employee getProjectManager() {
		return projectManager;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public void createTask(String title, Duration estimatedTime) throws OperationNotAllowed {
		Task task = new Task(title, estimatedTime);
		addTask(task);
	}
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	
	
	
	
	
	
	
	

}
