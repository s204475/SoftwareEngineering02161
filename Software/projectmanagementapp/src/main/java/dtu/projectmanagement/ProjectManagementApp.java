package dtu.projectmanagement;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.cucumber.messages.Messages.TestCaseStarted;


public class ProjectManagementApp {
	Employee activeUser;
	Project activeProject;
	Task activeTask;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public void createProject(String title) throws OperationNotAllowed {
		Project project = activeUser.createProject(title);
		addProject(project);
	}
	public void addProject(Project project) throws OperationNotAllowed {
		for (Project p : projects) {
			if (p.getTitle().equals(project.getTitle())) {
				throw new OperationNotAllowed("The project name is already taken");
			}
		}
		projects.add(project);
	}
	
	public void createEmployee(String name, String initials) {
		Employee employee = new Employee(name, initials);
		addEmployee(employee);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public Employee getActiveUser() {
		return activeUser;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	
//	public ArrayList<Employee> getAvailableEmployees() {
//		ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
//		for (Employee employee : employees) {
//			if (employee.isAvailable()) {
//				availableEmployees.add(employee);
//			}
//		}
//		return availableEmployees;
//	}
	
	public void setActiveUser(Employee employee) {
		if (!(employees.contains(employee))) {
			//implementer error message. Med den employee ikke eksistere 
		} else {
			if (activeUser != null) {
				activeUser.removeActiveUser();
			}
			employee.setActiveUser();
			activeUser = employee;	
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
			//Error message. activeProject, does not contain the task. 
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
	public Employee searchEmployees(String initials) {
		for (Employee employee : employees) {
			if (employee.getInitials().equals(initials)) {
				return employee;
			}
		}
		return null;
	}
	
	public Project searchProjectsId(String id) {
		for (Project project : projects) {
			if (project.getId().equals(id)) {
				return project;
			}
		}
		return null;
	}
	
	public Project searchProjectsTitle(String title) {
		for (Project project : projects) {
			if (project.getTitle().equals(title)) {
				return project;
			}
		}
		return null;
	}
	public Project getActiveProject() {
		return activeProject;
	}
	public void createTask(String taskName, long estimatedDuration) throws OperationNotAllowed {
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeProject.createTask(taskName, Duration.ofHours(estimatedDuration));
		} else {
			throw new OperationNotAllowed("You have to be a project manager to create a task");
		}
		
	}
	
	public void addTask(Task task) throws OperationNotAllowed { 
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeProject.addTask(task);
		} else {
			throw new OperationNotAllowed("You have to be a project manager to create a task");
		}
	}
	public void setTaskName(String newName) {
		// TODO Auto-generated method stub
		
	}
	public void setTaskStartTime(Date newStartTime) {
		// TODO Auto-generated method stub
		
	}
	public void setTaskEstimatedTime(double estimatedTime) {
		// TODO Auto-generated method stub
		
	}
	public void setTaskTimeWorked(double timeWorked) {
		// TODO Auto-generated method stub
		
	}
	public void setActiveActivity(Activity activity) {
		// TODO Auto-generated method stub
		
	}
	public void createActivity(String activityName, Duration activityDuration) {
		// TODO Auto-generated method stub
		
	}
	public void setNewActivityName(String newName) {
		// TODO Auto-generated method stub
		
	}

	public String createInitials(String userName) {
		String initials = "";
		initials += userName.charAt(0);
		for (int i = 1; i < userName.length() - 1; i++)
		{
			 if (userName.charAt(i) == ' ')
			 {
				 initials += userName.charAt(i + 1);
			 }
		}
		if(initials.length()>4)
		{
			String shortInitials = "";
			shortInitials += initials.charAt(0)+initials.charAt(1)+initials.charAt(2)+initials.charAt(3);
			return shortInitials;
		}
		else
		{
			return initials;
		}
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

	
	
