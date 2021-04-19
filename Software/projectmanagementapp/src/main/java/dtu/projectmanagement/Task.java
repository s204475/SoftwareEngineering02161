package dtu.projectmanagement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
	private double estimatedTime;
	private double timeSpent;
	private ArrayList<Employee> employeesOnTask = new ArrayList<Employee>();
	
	public Task (String name, double estimatedTime) {
		this.name = name;
		this.startTime = dateServer.getDate();
		this.estimatedTime = estimatedTime;
		this.timeSpent = 0;
	}
	
	public void setTimeSpent(double timeSpent) {
		this.timeSpent = timeSpent;
	}
	
	public double getRemainingTime() {
		return estimatedTime-timeSpent;
	}
	
	public double getEstimatedTime() {
		return estimatedTime;
	}

	public String getName() {
		return name;
	}

	public Calendar getStartTime() {
		return startTime;
	}

	public double getTimeSpent() {
		return timeSpent;
	}

	public void addEmployeeToTask(Employee employee)
	{
		employeesOnTask.add(employee);
	}
	
	public ArrayList<Employee> getEmployeesOnTask() {
		return employeesOnTask;
	}
	
	
}
