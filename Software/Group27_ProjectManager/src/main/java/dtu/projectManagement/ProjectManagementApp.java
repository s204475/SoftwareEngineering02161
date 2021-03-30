package dtu.projectManagement;

import java.time.Duration;
import java.util.ArrayList;

public class ProjectManagementApp {
	Employee activeUser;
	Project activeProject;
	Task activeTask;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public void createProject(String title, int id) {
		Project project = activeUser.createProject(title, id);
		projects.add(project);
	}
	
	public void createEmployee(String name) {
		Employee employee = new Employee(name);
		employees.add(employee);
	}
	
	public ArrayList<Employee> getAvailableEmployees() {
	ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
	for(Employee employee : employees) {
		if(employee.isAvailable()) {
			availableEmployees.add(employee);
		}
	}
	return availableEmployees;
}
	
	public void setActiveUser(Employee employee) {
		if (!(employees.contains(employee))) {
			//implementer error message. Med den employee ikke eksistere 
		} else {
			activeUser=employee;	
		}
	}
	
	public void setActiveProject(Project project) {
		if(!(projects.contains(project))) {
			//implementer error message. Projectet eksistrer ikke.
		}
		else {
			activeProject=project;
		}
	}
	
	public void setActiveTask(Task task) {
		if(activeProject == null) {
			//Error message. no activeProject.
		}
		else if(!(activeProject.tasks.contains(task))) {
			//Error message. activeProject, does not contaion the task. 
		}
		else {
			activeTask=task; 
		}
	}
	
	public int getProjectBudgetTime() {
		if(activeProject == null) {
			//Error message. no activeProject.
			return 0;
		}
		else {
			return activeProject.getBudgetTime();
		}
	}
	
	public double getProjectEstimatedTime() {
		if(activeProject == null) {
			//Error message. no activeProject.
			return 0.0;
		} else {
		double estimatedTimeInHours = activeProject.getEstimatedTime(); 
		return estimatedTimeInHours;
		}
	}
	
	public double getProjectRemainingTime() {
		if(activeProject == null) {
			//Error message. no activeProject.
			return 0.0;
		} else {
		double remamningTimeInHours = activeProject.getRemaningTime(); 
		return remamningTimeInHours;
		}
	}

	public ArrayList<Employee> getEmployees()
	{
		return employees;
	}

	public ArrayList<Project> getProjects()
	{
		return projects;
	}

	public Project getActiveProject()
	{
		return activeProject;
	}

	public Employee getActiveUser()
	{
		return activeUser;
	}

	public void setTaskName(String newName) {
		// TODO Auto-generated method stub
	}

	public void setTaskEstimatedTime(int newEstimatedTime) {
		// TODO Auto-generated method stub
	}

	public void setTaskTimeWorked(int newTimeWorked) {
		// TODO Auto-generated method stub
	}

	public void createTask(String taskName, double taskEstimatedTime) {
		// TODO Auto-generated method stub
	}

	public void setActivityName(String newName) {
		// TODO Auto-generated method stub
	}

	public Object getTasks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createActivity(String activityName, Duration activityDuration) {
		// TODO Auto-generated method stub
		
	}

	public void setActiveActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}
		
}
	
	//getTaskStarttime
	//getTaskTimespent
	//getTaskEstimatedTime
	//getTaskRemaningTime
	
	//setTaskName
	//setTaskEstimatedTime
	//setTaskTimeWorked
	//setStartTime
