package dtu.projectmanagement;

import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;


public class Project {
	private String title;
	private String id;
	private Calendar startTime; 

	private Employee projectManager;
	
	private DateServer dateServer = new DateServer();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	public Project(String title, int lastProjectId) throws OperationNotAllowed {
		if (title.equals("")) {
			throw new OperationNotAllowed("A project needs a name");
		} else {
			this.title = title;
		}
		this.id = setId(lastProjectId);
		this.startTime = dateServer.getDate();
	}
	
	public double getBudgetTime() {
		//Returns the budget time which is the sum of the project manager's estimation of tasks' time in hours
		double budgetTime = 0;
		for(Task task : tasks)
		{
			budgetTime += task.getEstimatedTime();
		}
		return budgetTime;
	}
	
	public void assignProjectManager(Employee employee) {
		projectManager = employee;
	}
	
	public String setId(int lastProjectId) { 
		String lastProjectIdString = String.valueOf(lastProjectId);
		while(lastProjectIdString.length() < 4) {
			lastProjectIdString = "0" + lastProjectIdString;
		}
		id = Year.now().format(DateTimeFormatter.ofPattern("yy")) + lastProjectIdString;
		return id;
	}
	
	public double getEstimatedTime() {
		double estimatedTimeInHours = 0.0;
		for(Task task : tasks) {
			estimatedTimeInHours += (double) task.getEstimatedTime();
		}
		return estimatedTimeInHours;
	}
	
	public double getRemainingTime() {
		double remainingTime = 0.0;
		for(Task task : tasks) {
			remainingTime += (double) task.getRemainingTime();
		}
		return remainingTime;
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
	
	public boolean isProjectManager(Employee employee) {
		if (projectManager != null && projectManager.equals(employee)) {
			return true;
		}
		return false;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}

	public void removeProjectManager() {
		projectManager = null;
	}

}