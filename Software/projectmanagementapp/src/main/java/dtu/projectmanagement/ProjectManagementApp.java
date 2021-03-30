package dtu.projectmanagement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
	public void addProject(Project project) {
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
//	
	public void setActiveUser(Employee employee) {
		if (!(employees.contains(employee))) {
			//implementer error message. Med den employee ikke eksistere 
		} else {
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
	
	public List<Project> searchProjectsTitle(String title) {
		List<Project> projectsWithTitle = new ArrayList<>();
		for (Project project : projects) {
			if (project.getTitle().equals(title)) {
				projectsWithTitle.add(project);
			}
		}
		return projectsWithTitle;
	}
	public Project getActiveProject() {
		return activeProject;
	}
	public void createTask(String taskName, double estimatedDuration) {
		//current date as default starttime
		
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
	
}
	
	//getTaskStarttime
	//getTaskTimespent
	//getTaskEstimatedTime
	//getTaskRemaningTime
	
	//setTaskName
	//setTaskEstimatedTime
	//setTaskTimeWorked
	//setStartTime

	
	
