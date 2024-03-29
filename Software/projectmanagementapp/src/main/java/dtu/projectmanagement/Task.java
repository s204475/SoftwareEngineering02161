package dtu.projectmanagement;

import java.util.ArrayList;
import java.util.Calendar;

public class Task {
	private String name;
	private DateServer dateServer = new DateServer();
	private Calendar startTime;
	private double estimatedTime;
	private double timeSpent;
	private ArrayList<Employee> employeesOnTask = new ArrayList<Employee>();

	// Anders Reher s194587
	public Task (String name, double estimatedTime) throws OperationNotAllowed {
		assert name != null && dateServer != null; // Precondition 
		boolean created = false;
		this.name = name;
		this.startTime = dateServer.getDate();
		if (estimatedTime % 0.5 == 0 && estimatedTime > 0) {   
			this.estimatedTime = estimatedTime;
		} else {
			assert !created; // Postcondition
			throw new OperationNotAllowed("Estimated time has to be given 0.5 hours");
		}
		this.estimatedTime = estimatedTime;
		this.timeSpent = 0;
		created = true; 
		assert created;  // Postcondition
	}

	// Victor Rasmussen s204475
	public void setTimeSpent(Task task) {
		//Calculates how much time employees assigned to the task cumulatively have spent on the task
		
		double hoursSpent = 0;

		for(Employee employee : employeesOnTask)
		{
			for(Activity activity : employee.getActivities())
			{
				if(activity instanceof TaskActivity && activity.getStartTime().before(Calendar.getInstance())) {
					if (((TaskActivity)activity).getTask() == null) {
						break;
					} else if (((TaskActivity)activity).getTask().equals(task)) {
						hoursSpent += activity.getTimePassed();	
					}

				}
			}
		}
		this.timeSpent = hoursSpent;
	}

	public double getRemainingTime() {
		return estimatedTime - timeSpent;
	}

	public double getEstimatedTime() {
		return estimatedTime;
	}

	// Victor Rasmussen s204475
	public void setEstimatedTime(double newEstimatedTime) throws OperationNotAllowed {
		//Effectively sets the budget time for a task. Assigned by the project manager. 
		if(newEstimatedTime <= 0) {
			throw new OperationNotAllowed ("A task has to have a name and estimed time");
		} else {
			estimatedTime = newEstimatedTime;
		}
	}

	public void setName(String newName) {
		name = newName;
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

	public void addEmployeeToTask(Employee employee) {
		if(!employeesOnTask.contains(employee))
		{
			employeesOnTask.add(employee);
		}
	}

	public ArrayList<Employee> getEmployeesOnTask() {
		return employeesOnTask;
	}


	public void setStartTime(Calendar newStartTime) {
		startTime = newStartTime;
	}

}
