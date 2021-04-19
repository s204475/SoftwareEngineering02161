package dtu.projectmanagement;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import io.cucumber.messages.Messages.TestCaseStarted;


public class ProjectManagementApp {
	Employee activeUser;
	Project activeProject;
	Task activeTask;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	
	/* --------- UI CONNECTION --------- */ 
	public void createProject(String title) throws OperationNotAllowed {
		Project project = activeUser.createProject(title);
		addProject(project);
	}
	public void createEmployee(String name, String initials) {
		Employee employee = new Employee(name, initials);
		addEmployee(employee);
		sortEmployees();
	}
	
	private void sortEmployees() {
		employees.sort(new NameSort());        
	}
	
	public void createTask(String taskName, double estimatedDuration) throws OperationNotAllowed {
		Task task = new Task(taskName, estimatedDuration);
		addTask(task);
	}
	public void createActivity(String activityName, GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowed {
		Activity activity = new Activity(activityName, startTime, endTime);
		addActivity(activity);
	}
	
	
	/* ---------------------------------- */
	/* --------- BUSINESS LOGIC --------- */
	/* ---------------------------------- */
	/* METHODS */
	public void addProject(Project project) throws OperationNotAllowed {
		for (Project p : projects) {
			if (p.getTitle().equals(project.getTitle())) {
				throw new OperationNotAllowed("The project name is already taken");
			}
		}
		projects.add(project);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	public void addTask(Task task) throws OperationNotAllowed { 
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeProject.addTask(task);
		} else {
			throw new OperationNotAllowed("You have to be a project manager to create a task");
		}
	}
	public void addActivity(Activity activity) throws OperationNotAllowed { 
		activeUser.addActivity(activity);
	}
	public void assignTask(String initials, TaskActivity taskActivity) throws OperationNotAllowed {
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			searchEmployees(initials).assignTask(taskActivity);
			
		} else {
			throw new OperationNotAllowed("Only project managers can assign tasks");
		}
	}

	
	
	
	
	/* GETTERS AND SETTERS */
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public Employee getActiveUser() {
		return activeUser;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	
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
	
	
	
//	public ArrayList<Employee> getAvailableEmployees() {
//		ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
//		for (Employee employee : employees) {
//			if (employee.isAvailable()) {
//				availableEmployees.add(employee);
//			}
//		}
//		return availableEmployees;
//	}
	
	
	
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
	
	
	
	private String getActiveProjectInformation() {
		
		String taskInformation = "";
		
		//PROJECT INFORMATION
		taskInformation += "Project name: "+activeProject.getTitle()+"\n";
		taskInformation += "Project ID: "+activeProject.getId()+"\n";
		taskInformation += "Collective budget time spent for all tasks: "+activeProject.getBudgetTime()+"\n";
		taskInformation += "Estimated time remaining on tasks: "+activeProject.getEstimatedTime()+"\n";
		taskInformation += "Remaining budget time on tasks: "+activeProject.getRemaningTime()+"\n";
		taskInformation += "Current project manager: "+activeProject.getProjectManager().getName()
														+ "("+activeProject.getProjectManager().getInitials()+")"+"\n";
		
		taskInformation += "The following task are associated with the project:\n";
		
		taskInformation += "\n";
		
		//TASK INFORMATION
		if(activeProject.tasks.size() > 0)
		{
			for(Task task : activeProject.tasks)
			{
				taskInformation += "----------------------------------------------------------------\n";
				taskInformation += "Task name: "+ task.getName()+"\n";
				taskInformation += "Estimated time left on task: "+task.getEstimatedTime()+"\n";
				taskInformation += "Total budget time on task: "+task.getRemainingTime()+"\n";
				taskInformation += "Task started on: "+task.getStartTime().getTime().toString()+"\n";
				taskInformation += "Total work hours used on project: "+task.getTimeSpent()+"\n";
				taskInformation += "Employees currently assigned to task: "+"\n";
				if(task.getEmployeesOnTask().size() > 0)
				{
					for(Employee employee : task.getEmployeesOnTask())
					{
						taskInformation += employee.getName() + "("+employee.getInitials()+")"+"\n";
					}
				} else {
					taskInformation += "There are no employees on this task yet."+"\n";
				}
				
			}
		} else {
			taskInformation += "There are no task associated with this project yet."+"\n";
		}
		
		
		return taskInformation;
	}
	public void addEmployeeToTask(Employee employee) {
		if(!activeTask.getEmployeesOnTask().contains(employee))
		{
			activeTask.addEmployeeToTask(employee);
		}
	}
	
	public class NameSort implements Comparator<Employee> 
	{
		//Sorts employees alphabetically
	    @Override
	    public int compare(Employee employee1, Employee employee2)
	    {
	        return employee1.getName().compareToIgnoreCase(employee2.getName());
	    }
	}

	public void printReport(String path_to_file) throws IOException {
		if(pathExists(path_to_file))
		{
			ReportWriter writer = new ReportWriter(path_to_file);
			
			String reportFileName = "Report on " + activeProject.getTitle() + " ("+activeProject.getId()+")";
			
			String reportContent = getActiveProjectInformation();
			
			writer.writeReportToFile(reportFileName,reportContent);
		}
		
	}
	
	public boolean pathExists(String path_to_file) {
		
		File file = new File(path_to_file);
		 
        if (file.isDirectory()) {
            return true;
        }
        else {
            return false;
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

	
	
