package dtu.projectmanagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;

/* The main application commands 
*/

public class ProjectManagementApp {
	Employee activeUser;
	Project activeProject;
	Task activeTask;
	private Activity activeActivity;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	int lastProjectId = 0;
	
	/* Create and add */
	// Magnus Siegumfeldt s204472
	public void createEmployee(String name, String initials) {
		Employee employee = new Employee(name, initials);
		addEmployee(employee);
		sortEmployees();
	}
	// Magnus Siegumfeldt s204472
	public void addProject(Project project) throws OperationNotAllowed {
		for (Project p : projects) {
			if (p.getTitle().equals(project.getTitle())) {
				throw new OperationNotAllowed("The project name is already taken");
			}
		}
		projects.add(project);
		incrementLastProjectId();
	}
	// Magnus Siegumfeldt s204472
	public void incrementLastProjectId() {
		lastProjectId=getLastProjectId()+1;
		if(lastProjectId == 10000) {
			lastProjectId = 0;
		}
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	// Anders Reher s194587
	public void addTask(Task task) throws OperationNotAllowed {
		assert activeProject != null && activeUser != null; // Precondition
		if(task.getName().equals("") || task.getEstimatedTime() <= 0) {
			assert !activeProject.getTasks().contains(task); // Postcondition
			throw new OperationNotAllowed("A task has to have a name and estimated time");
		}
		else if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeProject.addTask(task);
			assert activeProject.getTasks().contains(task); // Postcondition
		} else {
			assert !activeProject.getTasks().contains(task); // Postcondition
			throw new OperationNotAllowed("You have to be a project manager to change or create a task");
		}
	}
	
	public void addActivity(Activity activity) throws OperationNotAllowed { 
		activeUser.addActivity(activity);
	}
	
	/* Methods for removal and clean-up */
	// Anders Gad s204496
	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
	}
	// Anders Gad s204496
	public void deleteProject(Project project) {
		
		projects.remove(project);
		activeProject = null;
	}
	// Anders Gad s204496
	public void deleteTask(Task task) {
		activeProject.getTasks().remove(task);
		activeTask = null;
	}
	// Anders Gad s204496
	public void deleteActivity(Activity activityToBeDeleted) {
		//If the user is active on several activities associated with a task, they are not removed from the task
		int activitiesForTask = 0;
		for(Activity activity : activeUser.getActivities())
		{
			if(activity instanceof TaskActivity)
			{
				if (((TaskActivity)(activity)).getTask() == getActiveTask())
				{
					activitiesForTask++;
				}
			}
		}
		
		if(activityToBeDeleted instanceof TaskActivity && activitiesForTask < 2)
		{
			((TaskActivity)(activityToBeDeleted)).getTask().getEmployeesOnTask().remove(activeUser);
		}
		activeUser.getActivities().remove(activityToBeDeleted);
	}
	// Magnus Siegumfeldt s204472
	public void assignTask(String initials, TaskActivity taskActivity) throws OperationNotAllowed {
		//Assign an employee to a task
		
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			searchEmployees(initials).assignTask(taskActivity);
			activeTask.addEmployeeToTask(searchEmployees(initials));
			setTaskTimeWorked();
		} else if(activeUser.getInitials().equals(initials)){
			activeUser.assignTask(taskActivity);
			activeTask.addEmployeeToTask(searchEmployees(initials));
			setTaskTimeWorked();
		} else {
			throw new OperationNotAllowed("Only project managers can assign tasks");
		}
	}

	/* Sort and search */
	
	private void sortEmployees() {
		employees.sort(new NameSort());        
	}
	// Anders Gad s204496
	public Employee searchEmployees(String initials) throws OperationNotAllowed {
		assert ((initials != null && employees!=null)); // Precondition
		boolean found = false;
		for (Employee employee : employees) { 
			if (employee.getInitials().equals(initials)) { 
				found = true;
				assert found == employees.stream().anyMatch(e -> e.getInitials().equals(initials));  // Postcondition
				return employee;
			}
		}
		assert found == employees.stream().anyMatch(e -> e.getInitials().equals(initials));  // Postcondition
		throw new OperationNotAllowed("Employee doesn't exist");
	}
	
	// Victor Rasmussen s204475
	public Project searchProjectsId(String id) throws OperationNotAllowed {
		for (Project project : projects) {
			if (project.getId().equals(id)) {
				return project;
			}
		}
		throw new OperationNotAllowed("project does not exist");
	}
	// Victor Rasmussen s204475
	public Project searchProjectsTitle(String title) throws OperationNotAllowed {
		for (Project project : projects) {
			if (project.getTitle().equals(title)) {
				return project;
			}
		}
		throw new OperationNotAllowed("project does not exist");
	}
	// Anders Reher s194587
	public String createInitials(String userName) {
		//Creates initials for an employee based on the full name of the employee. John Smithson would be name JS. 
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
			initials = "" + initials.charAt(0)+initials.charAt(1)+initials.charAt(2);
		}
		boolean found = false;
		int index = 0;
		while (true) {
			for (Employee e : employees) {
				if (e.getInitials().equals(initials)) {
					found = true;
				}
			}
			if(!found) {
				return initials;
			}
			initials = initials.substring(0, initials.length() - 1) + index;
			found = false;
			index++;
			
		}
		
	}
	// Anders Reher s194587
	public class NameSort implements Comparator<Employee> 
	{
		//Sorts employees alphabetically
	    @Override
	    public int compare(Employee employee1, Employee employee2)
	    {
	        return employee1.getName().compareToIgnoreCase(employee2.getName());
	    }
	}

	/* Report writing */
	// Anders Gad s204496
	public void printReport(String path_to_file) throws IOException {
		//Print a report of the current active project
		
		if(pathExists(path_to_file)) {
			ReportWriter writer = new ReportWriter(path_to_file);
			
			String reportFileName = "Report on " + activeProject.getTitle() + " ("+activeProject.getId()+")";
			
			String reportContent = getActiveProjectInformation();
			
			writer.writeReportToFile(reportFileName,reportContent);
		}
		else {
			throw new IOException("the path was not found");
		}
	}
	// Anders Gad s204496
	public boolean pathExists(String path_to_file) {
		//Checks if the directory path exist
		
		File file = new File(path_to_file);
		 
        if (file.isDirectory()) {
            return true;
        }
        else {
            return false;
        }
	}

	/* GETTERS AND SETTERS */
	
	public int getLastProjectId() {
		return lastProjectId;
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	
	public Employee getActiveUser() {
		return activeUser;
	}
	
	public Activity getActiveActivity() {
		return activeActivity;
	}
	public Project getActiveProject() {
		return activeProject;
	}
	

	public ArrayList<Project> getProjects() {
		return projects;
	}
	// Magnus Siegumfeldt s204472
	public void setEstimatedTimeOfTask(double Time) throws OperationNotAllowed {
		if(activeTask == null) {
			throw new OperationNotAllowed("the task does not exist");
		}
		else {
			if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
				activeTask.setEstimatedTime(Time);
			} else {
				throw new OperationNotAllowed("You have to be a project manager to change or create a task");
			}
		}
	}
	// Anders Reher s194587
	public void setActiveUser(Employee employee) throws OperationNotAllowed {
		if (!(employees.contains(employee))) {
			throw new OperationNotAllowed("the employee doesn't exist");
		} else {
			activeUser = employee;	
		}
	}
	// Anders Reher s194587
	public void setActiveProject(Project project) throws OperationNotAllowed {
		if(!(projects.contains(project))) {
			throw new OperationNotAllowed("the project does not exist");
		}
		else {
			activeProject = project;
		}
	}
	// Anders Reher s194587
	public void setActiveTask(Task task) throws OperationNotAllowed {
		if(activeProject == null) {
			throw new OperationNotAllowed("no project choosen");
		}
		else if(!(activeProject.tasks.contains(task))) {
			throw new OperationNotAllowed("the task does not exist"); 
		}
		else {
			activeTask=task; 
		}
	}
	
	// Magnus Siegumfeldt s204472
	public ArrayList<Employee> getAvailableEmployees(GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowed {
		//Returns a list of employees who are not occupied in the provided timeframe
		ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
		if (startTime.equals(endTime)) {
			throw new OperationNotAllowed("You have not selected a duration to find available employees");
		} else {
			for (Employee employee : employees) {
				if (employee.isAvailable(startTime, endTime)) {
					availableEmployees.add(employee);
				}
			}
		if (availableEmployees.isEmpty()) {
			throw new OperationNotAllowed("No available employees at the given time");
		} else {
			return availableEmployees;
		}
		}
	}
	
	// Anders Gad s204475
	public void setTaskName(String newName) throws OperationNotAllowed {
		if(activeTask == null) {
			throw new OperationNotAllowed("the task does not exist");
		}
		else if(newName.equals("")) {
			throw new OperationNotAllowed("A task has to have a name and estimed time");
		} else {
			changeTaskName(newName);
		}
	}
	
	public void setTaskStartTime(Calendar newStartTime) {
		activeTask.setStartTime(newStartTime);
	}

	public void changeTaskName(String newName) throws OperationNotAllowed {
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeTask.setName(newName);
		} else {
			throw new OperationNotAllowed("Active user is not project manager for this project");
		}
	}
	
	public void setTaskEstimatedTime(double estimatedTime) throws OperationNotAllowed {
		activeTask.setEstimatedTime(estimatedTime);
	}
	
	public void setTaskTimeWorked() {
		activeTask.setTimeSpent(activeTask);
	}
	
	public void setActiveActivity(Activity activity) {
		activeActivity = activity;
	}
	
	public void setNewActivityName(String newName) {
		activeActivity.setName(newName);
	}
	// Victor Rasmussen s204475 
	private String getActiveProjectInformation() {
		// All the information in a project. Used for printing a report.
		
		String taskInformation = "";
		
		// PROJECT INFORMATION
		taskInformation += "Project name: "+activeProject.getTitle()+"\n";
		taskInformation += "Project ID: "+activeProject.getId()+"\n";
		taskInformation += "Collective budget time spent for all tasks: "+activeProject.getBudgetTime()+"\n";
		taskInformation += "Estimated time remaining on tasks: "+activeProject.getEstimatedTime()+"\n";
		taskInformation += "Remaining budget time on tasks: "+activeProject.getRemainingTime()+"\n";
		taskInformation += "Current project manager: "+activeProject.getProjectManager().getName()
														+ "("+activeProject.getProjectManager().getInitials()+")"+"\n";
		taskInformation += "The following task are associated with the project:\n";
		
		taskInformation += "\n";
		
		// TASK INFORMATION
		if(activeProject.tasks.size() > 0)
		{
			for(Task task : activeProject.tasks)
			{
				taskInformation += "----------------------------------------------------------------\n";
				taskInformation += "Task name: "+ task.getName()+"\n";
				taskInformation += "Task started on: "+task.getStartTime().getTime().toString()
						+" (Week:"+task.getStartTime().get(Calendar.WEEK_OF_YEAR)+")"+"\n";
				taskInformation += "Estimated time left on task in hours: "+task.getEstimatedTime()+"\n";
				taskInformation += "Total budget time on task in hours: "+task.getRemainingTime()+"\n";
				taskInformation += "Total work hours used on task: "+task.getTimeSpent()+"\n";
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

	public Task getActiveTask() {
		return activeTask;
	}

}