package dtu.projectmanagement;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;


public class ProjectManagementApp {
	Employee activeUser;
	Project activeProject;
	Task activeTask;
	Activity activeActivity;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	/* ---------------------------------- */
	/* --------- BUSINESS LOGIC --------- */
	/* ---------------------------------- */
	/* METHODS */
	public void createEmployee(String name, String initials) {
		Employee employee = new Employee(name, initials);
		addEmployee(employee);
		sortEmployees();
	}
	
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
		if(task.getName().equals("") || task.getEstimatedTime() <= 0) { //2
			throw new OperationNotAllowed("A task has to have a name and estimated time");
		}
		else if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) { //3
			activeProject.addTask(task);
		} else {
			throw new OperationNotAllowed("You have to be a project manager to change or create a task");
		}
	}
	
	public void addActivity(Activity activity) throws OperationNotAllowed { 
		activeUser.addActivity(activity);
	}
	
	public void assignTask(String initials, TaskActivity taskActivity) throws OperationNotAllowed {
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			searchEmployees(initials).assignTask(taskActivity);
			setTaskTimeWorked();
		} else if(activeUser.getInitials().equals(initials)){
			activeUser.assignTask(taskActivity);
			setTaskTimeWorked();
		} else {
			throw new OperationNotAllowed("Only project managers can assign tasks");
		}
	}
	
	public void setProjectManager(Employee employee) {
		activeProject.assignProjectManager(employee);
	}

	private void sortEmployees() {
		employees.sort(new NameSort());        
	}
	
	public Employee searchEmployees(String initials) throws OperationNotAllowed {
		//assert ((initials != null && !(initials.equals(""))) && (!(employees.isEmpty()) && employees!=null)); // precondition:
		//boolean found =false;
		for (Employee employee : employees) { //1
			if (employee.getInitials().equals(initials)) { //2
		//		found= true;
		//		assert employees.contains(employee); // postcondition:
				return employee;
			}
		}
		// assert !found; //postcondition:
		throw new OperationNotAllowed("Employee doesn't exist");
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
			String shortInitials = ""+initials.charAt(0)+initials.charAt(1)+initials.charAt(2)+initials.charAt(3);
			return shortInitials;
		} else{
			return initials;
		}
	}
	
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
	

	/* GETTERS AND SETTERS */
	
	public void assignProjectManager(Employee employee) {
		activeProject.assignProjectManager(employee);
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
	
	public ArrayList<Employee> getAvailableEmployees(GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowed {
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
		double remamningTimeInHours = activeProject.getRemainingTime(); 
		return remamningTimeInHours;
		}
	}
	
	public Project getActiveProject() {
		return activeProject;
	}
	
	public void setTaskName(String newName) throws OperationNotAllowed {
		if(activeTask == null) {
			throw new OperationNotAllowed("the task does not exist");
		}
		else if(newName.equals("")) {
			throw new OperationNotAllowed("A task has to have a name and estimed time");
		} else {
		activeTask.setName(newName);
		}
	}
	

	public void setTaskStartTime(Calendar newStartTime) {
		activeTask.setStartTime(newStartTime);
	}

	public void changeTaskName(String newName) throws OperationNotAllowed {
		if (activeProject.getProjectManager() != null && activeUser.equals(activeProject.getProjectManager())) {
			activeProject.changeTaskName(activeTask, newName);
		} else {
			System.out.println("fejl");
			throw new OperationNotAllowed("Active user is not project manager for this project");
		}
	}
	

	
	public void setTaskEstimatedTime(double estimatedTime) throws OperationNotAllowed {
		activeTask.setEstimatedTime(estimatedTime);
	}
	
	public void setTaskTimeWorked() {
		activeTask.setTimeSpent();
	}
	
	public void setActiveActivity(Activity activity) {
		activeActivity = activity;
	}
	
	public void setNewActivityName(String newName) {
		activeActivity.setName(newName);
	}

	
	
	
	
	private String getActiveProjectInformation() {
		
		String taskInformation = "";
		
		//PROJECT INFORMATION
		taskInformation += "Project name: "+activeProject.getTitle()+"\n";
		taskInformation += "Project ID: "+activeProject.getId()+"\n";
		taskInformation += "Collective budget time spent for all tasks: "+activeProject.getBudgetTime()+"\n";
		taskInformation += "Estimated time remaining on tasks: "+activeProject.getEstimatedTime()+"\n";
		taskInformation += "Remaining budget time on tasks: "+activeProject.getRemainingTime()+"\n";
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
				taskInformation += "Task started on: "+task.getStartTime().getTime().toString()
						+" (Week:"+task.getStartTime().get(Calendar.WEEK_OF_YEAR)+")"+"\n";
				Calendar finishDate = task.getStartTime();
				finishDate.add(Calendar.HOUR_OF_DAY, (int)task.getEstimatedTime());
				taskInformation += "Task should be finished on week "+finishDate.get(Calendar.WEEK_OF_YEAR)+"\n";
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

	public void deleteEmployee(Employee employee) {
		employees.remove(employee);
	}

	public void deleteProject(Project project) {
		
		projects.remove(project);
		activeProject = null;
	}

	public void deleteTask(Task task) {
		activeProject.getTasks().remove(task);
		activeTask = null;
	}

	public void deleteActivity(Activity activity) {
		activeUser.getActivities().remove(activity);
		if(activity instanceof TaskActivity)
		{
			((TaskActivity)(activity)).getTask().getEmployeesOnTask().remove(activeUser);
		}
	}
	
	
	
}

	
	
