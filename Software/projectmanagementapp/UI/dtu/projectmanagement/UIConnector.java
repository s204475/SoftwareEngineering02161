package dtu.projectmanagement;

import java.util.Calendar;
import java.util.GregorianCalendar;

// Victor Rasmussen s204475
public class UIConnector {
	
	private ProjectManagementApp app;
	
	public UIConnector(ProjectManagementApp app)
	{
		this.app = app;
	}

	/* --------- UI CONNECTION --------- */ 
	public void assignProjectManager(Employee employee) {
		app.activeProject.assignProjectManager(employee);
	}
	
	public void createProject(String title) throws OperationNotAllowed {
		Project project = new Project(title, app.getLastProjectId());
		app.addProject(project);
	}
	
	
	public void createTask(String taskName, double estimatedtime) throws OperationNotAllowed {
		Task task = new Task(taskName, estimatedtime);
		app.addTask(task);
	}
	
	public void createActivity(String activityName, Calendar startTime, Calendar endTime) throws OperationNotAllowed {
		Activity activity = new Activity(activityName, startTime, endTime);
		app.addActivity(activity);
	}
	
	public void createTaskActivity(String activityName, Calendar startTime, Calendar endTime, Task task,Employee employee) throws OperationNotAllowed {
		TaskActivity taskActivity = new TaskActivity(activityName, (GregorianCalendar) startTime, (GregorianCalendar) endTime, task);
		taskActivity.getTask().addEmployeeToTask(employee);
		app.assignTask(employee.getInitials(), taskActivity);
	}
}
