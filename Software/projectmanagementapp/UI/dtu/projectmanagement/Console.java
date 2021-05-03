package dtu.projectmanagement;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/* The UI done as console commands 
 * By Victor Rasmussen s204475
*/

public class Console {
	
	public static Scanner scanner = new Scanner(System.in);
	private ProjectManagementApp app;
	private Helper helper = new Helper();
	
	public Console(ProjectManagementApp app)
	{
		this.app = app;
	}
	
	public void start()
	{
		System.out.println("Project Management App 2021");
		chooseActiveUser();
	}
	
	public void chooseActiveUser()
	{	
		/*Prints all employees in the app and the active user can then be chosen.
		 * If there are no user in the app, a guest user is automatically added. 
		 */
		if(app.getEmployees() == null || app.getEmployees().size() == 0)
		{
			System.out.println("There are currently no user. Please create one.");
			createEmployee();
		}
		else
		{
			//clearConsole();
			System.out.println("Choose active user:");
			printEmployees();
			
			while (!scanner.hasNextInt()) scanner.next();
			int userChoice = scanner.nextInt();
			if(userChoice>app.getEmployees().size() || userChoice<0)
			{
				System.out.println("Incorrect input. Returning to main menu...");
			} else {
				app.setActiveUser(app.getEmployees().get(userChoice));
			}
		}
		mainMenu();
	}

	private void printEmployees() {
		for(int i = 0; i<app.getEmployees().size();i++)
		{
			System.out.println(i+": "+app.getEmployees().get(i).getName());
		}
	}
	
	private void createEmployee()
	{
		System.out.println("Creating new user... \nType in your full name.");
		String userName = userInput();
		String initials = app.createInitials(userName);
		System.out.println("Your initials will be: "+initials);
		app.createEmployee(userName, initials);
		if(app.getActiveUser() == null)
		{
			app.setActiveUser(app.getEmployees().get(0));
		}
	}

	//Checks if a string has any digits.
	//Currently not used
	public boolean hasDigits(String s) {
	    boolean digits = false;

	    if (s != null && !s.isEmpty())
	    {
	        for (char c : s.toCharArray())
	        {
	            if (Character.isDigit(c))
	            {
	                return true;
	            }
	            else
	            {
	            	return false;
	            }
	        }
	    }

	    return digits;
	}

	private void mainMenu()
	{
		//The main menu of the app
		System.out.println("Main menu("+app.getActiveUser().getName()+")"
		+ "\n1: Change active user"
		+ "\n2: Add user"
		+ "\n3: Remove user"
		+ "\n4: Create project"
		+ "\n5: See projects"
		+ "\n6: Personal activities"
		+ "\n7: Quit");
		
		while (!scanner.hasNextInt()) scanner.next();
		
		switch(scanner.nextInt())
		{
			case 1:
				chooseActiveUser();
				break;
			case 2:
				createEmployee();
				mainMenu();
				break;
			case 3:
				removeUser();
				mainMenu();
				break;
			case 4: 
				createProject();
				pressEnterToContinue();
				mainMenu();
				break;
			case 5: 
				seeProjects();
				break;
			case 6: 
				seePersonalActivities();
				break;
			case 7: 
				helper.ExitApp();
				break;
		}
	}

	private void removeUser() {
		if(app.getEmployees().size() == 1)
		{
			System.out.println("You cannot remove the only user.");
		} else {
			System.out.println("Choose user to remove.");
			printEmployees();
			while (!scanner.hasNextInt()) scanner.next();
			Employee employee = app.getEmployees().get(scanner.nextInt());
			app.deleteEmployee(employee);
			if(employee == app.getActiveUser())
			{
				chooseActiveUser();
			}
		}
	}

	private void createProject()  {
		System.out.println("Choose a name for the project");
		String projectTitle = userInput();
		try {
			app.createProject(projectTitle);
			System.out.println("The project can now be found under \"See projects\"");
		} catch (OperationNotAllowed e) {
			helper.printError(e);
		}
		
	}

	public String userInput()
	{
		String input = "";
		while (input.equals(""))
		{
			input = scanner.nextLine();
		}
		return input;
	}
	
	private void seePersonalActivities()
	{
		System.out.println(""
				+ "\n1: Add activity"
				+ "\n2: Edit activity"
				+ "\n3: See activities"
				+ "\n4: Go back");
		
		while (!scanner.hasNextInt()) scanner.next();
		
		switch(scanner.nextInt())
		{
			case 1:
				try {
					addActivity();
				} catch (ParseException e) {
					helper.printError(e);
				}
				seePersonalActivities();
				break;
			case 2: 
				editActivity();
				seePersonalActivities();
				break;
			case 3: 
				printAllActivitesInfo();
				seePersonalActivities();
				break;
			case 4: 
				mainMenu();
				break;
			default:
				InvalidInput();
				break;
		}
	}

	/*private void setTimeWorkedOnTaskActivity() {
		
		if(app.getActiveUser().getActivities() == null || app.getActiveUser().getActivities().size() == 0)
		{
			System.out.println("You currently have no activities.");
			seePersonalActivities();
		} else {
			System.out.println("Choose task to edit work time: ");

			Activity activity = selectTaskActivity();
			
			app.setActiveActivity(activity);
		
			System.out.println("Input hours worked on task (e.g. 4.5 or 4.0 )");
			while (!scanner.hasNextDouble()) scanner.next();
			
			TaskActivity taskActivity = (TaskActivity)activity;
		
		}
			
		
	}*/

	private void InvalidInput() {
		System.out.println("Invalid input");
	}
	
	public void seeProjects()
	{
		//Prints all active projects and chooses one to be the active project
		if (app.getProjects().size() == 0)
		{
			System.out.println("There are currently no projects. Please create one first. Returning to main menu...");
			mainMenu();
		}
		
		System.out.println("Choose a project");
		
		for(int i = 0; i<app.getProjects().size();i++)
		{
			System.out.println(i+": "+app.getProjects().get(i).getTitle());
		}
		
		while (!scanner.hasNextInt()) scanner.next();
		
		int userChoice = scanner.nextInt();
		
		if(userChoice>app.getProjects().size() || userChoice<0)
		{
			System.out.println("Incorrect input. Returning to main menu...");
			mainMenu();
		} else {
			app.setActiveProject(app.getProjects().get(userChoice));;
			activeProjectChoices();
		}
		
	}
	
	
	//Prints all eligible employees and assigns one as project manager on currently active project
	public void setProjectManager()
	{
		System.out.println("Choose a Project Manager.");
		if(app.getActiveProject().getProjectManager() != null)
		{
			System.out.println("Current project manager: "+app.getActiveProject().getProjectManager().getName());
		} else {
			System.out.println("There is currently no project manager.");
		}
		
		printEmployees();
		
		while (!scanner.hasNextInt()) scanner.next();
		
		app.assignProjectManager(app.employees.get(scanner.nextInt()));
		
		//clearConsole();
		
		activeProjectChoices();
	}
	
	public String displayProjectManager()
	{
		String output = "Project manager: ";
		if(app.getActiveProject().getProjectManager() == null)
		{
			output += "No project manager assigned.";
		} else {
			output += app.getActiveProject().getProjectManager().getName();
		}
		
		return output;
		
	}
	
	public void activeProjectChoices()
	{
		//Choices after choosing a project
		System.out.println("Current project: "
												+ app.getActiveProject().getTitle()
												+ " (Serial number: "
												+ app.getActiveProject().getId()+") \n"
												+ displayProjectManager()
				+ "\n1: Go back"
				+ "\n2: See tasks"
				+ "\n3: Assign project manager");
		
		if(app.activeProject.getProjectManager() == app.getActiveUser())
		{
			System.out.println("Project Manager choices: "
				+ "\n4: Create task"
				+ "\n5: Edit task"
				+ "\n6: Assign employee to task"
				+ "\n7: Create a report"
				+ "\n8: Show estimated time until project completion"
				+ "\n9: Show time spent on project"
				+ "\n10: Show remaining budgeted time"
				+ "\n11: Delete project");
		}
		
		while (!scanner.hasNextInt()) scanner.next();
		int choice = scanner.nextInt();
		switch(choice)
		{
			case 1:
				mainMenu();
				break;
			case 2: 
				printAllTaskInformation();
				pressEnterToContinue();
				break;
			case 3: 
				setProjectManager();
				break;
			case 4: 
				createTask();
				break;
			case 5: 
			try {
				editTasks();
			} catch (ParseException e) {
				helper.printError(e);
			}
				break;
			case 6: 
			try {
				assignEmployeeToTask();
			} catch (ParseException e) {
				helper.printError(e);
			}
				break;
			default: 
				if(app.getActiveProject().getProjectManager() != app.activeUser)
				{
					System.out.println("Incorrect input.");
				}
				break;
		}
		
		if(app.getActiveProject().getProjectManager() == app.activeUser)
		{
			switch(choice)
			{
				case 7: 
					createReport();
					pressEnterToContinue();
					break;
				case 8: 
					System.out.println("Collectively, the current tasks should take the following hours to complete: ");
					printEstimatedTime();
					System.out.println();
					pressEnterToContinue();
					break;
				case 9: 
					System.out.println("Remaining time on project:");
					printRemainingTime();
					System.out.println();
					pressEnterToContinue();
					break;
				case 10:
					System.out.println("The following total hours are paid (budgeted):");
					printBudgetedTime();
					System.out.println();
					pressEnterToContinue();
					break;
				case 11:
					System.out.println("Are you sure you wish to delete this project and all of its information?"
							+ "\n1: Yes"
							+ "\n2: No");
					while(!scanner.hasNextInt()) scanner.next();
					if(scanner.nextInt() == 1)
					{
						app.deleteProject(app.getActiveProject());
					}
					mainMenu();
					break;
				default: 
					if(choice <1 || choice > 11)
					{
						System.out.println("Incorrect input.");
					}
					break;
			}
		}
		
		if (choice != 5 && choice != 11) //unless you go back, you are presented with the project choices again.
		{
			activeProjectChoices();
		}
		
	}
	
	

	private void assignEmployeeToTask() throws ParseException {
		if(app.getActiveProject().getTasks() != null && app.getActiveProject().getTasks().size() > 0)
		{
			System.out.println("Choose employee to add");
			printEmployees();
			
			while (!scanner.hasNextInt()) scanner.next();
			int userChoice = scanner.nextInt();
			
			if(userChoice>app.getEmployees().size() || userChoice<0)
			{
				System.out.println("Incorrect input.");
			} else {
				System.out.println("Choose task to add employee to");
				printTasks();
				while (!scanner.hasNextInt()) scanner.next();
				
				app.setActiveTask(app.getActiveProject().getTasks().get(scanner.nextInt()));
				
				GregorianCalendar end;
				GregorianCalendar start;
				
				System.out.println("Input start time as HH-mm-dd-MM-yyyy (e.g. 12-30-10-02-2020 = 12:30 10/02/2020)"
						+ "The system only accepts half hour intervals.");
				
				String dateString = userInput();
				
				if(helper.isDate(dateString))
				{
					start = helper.dateToCalendar(helper.convertDate(dateString));
				} else {
					System.out.println("Incorrect input.");
					return;
				}
				
				System.out.println("Input end time as HH-mm-dd-MM-yyyy (e.g. 12-30-10-02-2020 = 12:30 10/02/2020)"
						+ "The system only accepts half hour intervals.");
				
				dateString = userInput();
				if(helper.isDate(dateString))
				{
					end = helper.dateToCalendar(helper.convertDate(dateString));
				} else {
					System.out.println("Incorrect input.");
					return;
				}
				
				try {
					app.createTaskActivity("Assigned to work on the task \""+app.getActiveTask().getName()
											+ "\" by "+app.getActiveUser().getName() +"("+ app.getActiveUser().getInitials()+")",
											start, end, app.getActiveTask(),app.getEmployees().get(userChoice));
					app.setTaskTimeWorked();
				
				} catch (OperationNotAllowed e) {
					helper.printError(e);
				}
				
			}
		} else {
			System.out.println("There are currently no tasks to add employees to.");
		}
		
	}

	private void createReport()
	{
		System.out.println("Please input complete path to save report in.");
		
		String path_to_file = userInput();
		
		if(app.pathExists(path_to_file))
		{
			try {
				app.printReport(path_to_file);
				System.out.println("A report is printed");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else
		{
			System.out.println("That path does not exist.");
		}
	}
	
	public void pressEnterToContinue()
	{
		System.out.println("Press enter to continue...");
		scanner.nextLine();
		//scanner.nextLine();
		//clearConsole();
	}

	private void printBudgetedTime()
	{
		System.out.println(app.getProjectBudgetTime());
	}

	private void printRemainingTime()
	{
		System.out.println(app.getProjectRemainingTime());
	}

	private void printEstimatedTime()
	{
		System.out.println(app.getProjectEstimatedTime());
	}

	public void createTask()
	{
		System.out.println("Input name for task");
		String taskName = userInput();
		System.out.println("Input estimated duration of task in hours (e.g. 4.5 or 4.0 )");
		while (!scanner.hasNextDouble()) scanner.next();
		try {
			app.createTask(taskName,scanner.nextDouble());
		} catch (OperationNotAllowed e) {
			helper.printError(e);
		}
	}
	
	private void editTasks() throws ParseException {
		
		if(app.getActiveProject().getTasks().size() == 0)
		{
			System.out.println("There are currently no tasks.");
		} else
		{
			System.out.println("Choose task to edit");
			
			printTasks();
			
			while (!scanner.hasNextInt()) scanner.next();

			app.setActiveTask(app.getActiveProject().getTasks().get(scanner.nextInt()));
			
			System.out.println("Choose what to edit");
			
			System.out.println(""
					+ "\n1: Edit name"
					+ "\n2: Edit start time"
					+ "\n3: Estimated completion time"
					+ "\n4: Delete task"
					+ "\n5: Go back to main menu");
			
			
			while (!scanner.hasNextInt()) scanner.next();
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("Input new name");
				try {
					app.setTaskName(userInput());
				} catch (OperationNotAllowed e) {
					helper.printError(e);
				}
					break;
				case 2: 
					System.out.println("\"Input start time as HH-mm-dd-MM-yyyy (e.g. 12-30-10-02-2020 = 12:30 10/02/2020)"
							+ "The system only accepts half hour intervals.");
					Date start;
					String dateString = userInput();
					
					if(helper.isDate(dateString))
					{
						start = helper.convertDate(dateString);
					} else {
						System.out.println("Incorrect input.");
						break;
					}
					
					GregorianCalendar startCal = helper.dateToCalendar(start);
					
					app.setTaskStartTime(startCal);
					break;
				case 3: 
					System.out.println("Input new estimated time");
					while (!scanner.hasNextDouble()) scanner.next();
				try {
					app.setTaskEstimatedTime(scanner.nextDouble());
				} catch (OperationNotAllowed e) {
					helper.printError(e);
				}
					break;
				case 4:
					System.out.println("Are you sure you wish to delete this task and all of its information?"
							+ "\n1: Yes"
							+ "\n2: No");
					while(!scanner.hasNextInt()) scanner.next();
					if(scanner.nextInt() == 1)
					{
						app.deleteTask(app.getActiveTask());
					}
					break;
				case 5: 
					mainMenu();
					break;
				default:
					break;
			}
		}
		
		activeProjectChoices();
	}

	
	/*private void addEmployeeToTask() {
		printEmployees();
		
		while (!scanner.hasNextInt()) scanner.next();
		app.addEmployeeToTask(app.getEmployees().get(scanner.nextInt()));
	}*/

	private void printTasks()
	{
		//Prints all tasks (in name only) 
		if(app.getActiveProject().getTasks().size() == 0)
		{
			System.out.println("There are currently no tasks.");
		} else
		{
			for(int i = 0; i<app.getActiveProject().getTasks().size();i++)
			{
				System.out.println(i+": "+app.getActiveProject().getTasks().get(i).getName());
			}
		}
	}
	
	
	private void printAllTaskInformation()
	{
		//Prints all tasks and their information
		if(app.getActiveProject().getTasks().size() == 0)
		{
			System.out.println("There are currently no tasks.");
		} else
		{
			for(int i = 0; i<app.getActiveProject().getTasks().size();i++)
			{
				System.out.println(i+": "+app.getActiveProject().getTasks().get(i).getName());
				System.out.println("Start time: "+helper.calendarToString(app.getActiveProject().getTasks().get(i).getStartTime()));
				System.out.println("Estimated time: "+app.getActiveProject().getTasks().get(i).getEstimatedTime());
				System.out.println("Time spent: "+app.getActiveProject().getTasks().get(i).getTimeSpent());
				System.out.println("Remaining time: "+app.getActiveProject().getTasks().get(i).getRemainingTime());
			}
		}
	}

	
	private void addActivity() throws ParseException
	{
		//Adds a new activity to the active user
		
		GregorianCalendar end;
		GregorianCalendar start;
		
		System.out.println("Input name for activity");
		String activityName = userInput();
		
		System.out.println("Input start time as HH-mm-dd-MM-yyyy (e.g. 12-30-10-02-2020 = 12:30 10/02/2020)"
				+ "The system only accepts half hour intervals.");
		
		String dateString = userInput();
		
		if(helper.isDate(dateString))
		{
			start = helper.dateToCalendar(helper.convertDate(dateString));
		} else {
			System.out.println("Incorrect input.");
			return;
		}
		
		System.out.println("Input end time as HH-mm-dd-MM-yyyy (e.g. 12-30-10-02-2020 = 12:30 10/02/2020)"
				+ "The system only accepts half hour intervals.");
		
		dateString = userInput();
		if(helper.isDate(dateString))
		{
			end = helper.dateToCalendar(helper.convertDate(dateString));
		} else {
			System.out.println("Incorrect input.");
			return;
		}
		
		System.out.println("Was this time spent on a project task?"
				+ "\n1: Yes"
				+ "\n2: No");
		while(!scanner.hasNextInt()) scanner.next();
		switch(scanner.nextInt())
		{
			case 1:
				if((app.projects != null && app.projects.size() > 0))
				{
					System.out.println("Which project?");
					printProjects();
					while (!scanner.hasNextInt()) scanner.next();
					app.setActiveProject(app.projects.get(scanner.nextInt()));
					
					if(app.getActiveProject() != null && app.getActiveProject().getTasks().size() > 0)
					{
						System.out.println("Which task?");
						printTasks();
						while (!scanner.hasNextInt()) scanner.next();
						app.setActiveTask(app.getActiveProject().getTasks().get(scanner.nextInt()));
						
						try {
							app.createTaskActivity(activityName, start, end, app.getActiveTask(),app.getActiveUser());
						} catch (OperationNotAllowed e) {
							helper.printError(e);
						}
					}
				} else{
					System.out.println("There are currently no project and therefore no task can have been worked on.");
				}
				
				break;
			
			case 2:
			try {
				app.createActivity(activityName, start, end);
			} catch (OperationNotAllowed e) {
				helper.printError(e);
			}
				break;	
			
			default:
					System.out.println("Incorrect input.");
				break;
		}
	}
	
	

	private void editActivity()
	{
		if(app.getActiveUser().getActivities() == null || app.getActiveUser().getActivities().size() == 0)
		{
			System.out.println("You currently have no activities.");
			seePersonalActivities();
		} else {
			System.out.println("Choose activity to edit: ");
			printActivities();
			
			while (!scanner.hasNextInt()) scanner.next();
			app.setActiveActivity(app.activeUser.getActivities().get(scanner.nextInt()));
			
			System.out.println(""
					+ "\n1: Edit name"
					+ "\n2: Edit start time"
					+ "\n3: Edit end time"
					+ "\n4: Delete activity"
					+ "\n5: Go back");
			
			while (!scanner.hasNextInt()) scanner.next();
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1: 
					System.out.println("Input new activity name");
					app.setNewActivityName(scanner.next());
					break;
				case 2: 
					System.out.println("This edits the start time"); //Needs implementation
					app.setTaskTimeWorked();
					break;
				case 3: 
					System.out.println("This edits the end time"); //Needs implementation
					app.setTaskTimeWorked();
					break;
				case 4: 
					app.deleteActivity(app.getActiveActivity());
					break;
				case 5: 
					mainMenu();
					break;
				default:
					System.out.println("Invalid input");
					break;	
			}
			
			if (choice != 5)
			{
				seePersonalActivities();
			}
		}	
	}

	private void printActivities()
	{
		for(int i = 0; i < app.activeUser.getActivities().size();i++)
		{
			System.out.println(i+": "+app.getActiveUser().getActivities().get(i).getName());
			if(app.activeUser.getActivities().get(i) instanceof TaskActivity)
			{
				System.out.println("Associated task: "+((TaskActivity) app.getActiveUser().getActivities().get(i)).getTask().getName());
			}
		}	
	}
	
	private void printProjects()
	{
		for(int i = 0; i < app.projects.size();i++)
		{
			System.out.println(i+": "+app.projects.get(i).getTitle()+" ("+app.projects.get(i).getId()+")");
		}
	}
	
	/*private void printTaskActivities()
	{
		for(int i = 0; i < app.activeUser.getActivities().size();i++)
		{
			if(app.activeUser.getActivities().get(i) instanceof TaskActivity)
			{
				System.out.println(i+": "+app.getActiveUser().getActivities().get(i).getName());
			}
		}	
	}*/
	
	/*private Activity selectTaskActivity()
	{
		ArrayList<Activity> tempList = new ArrayList<Activity>();
		for(int i = 0; i < app.activeUser.getActivities().size();i++)
		{
			if(app.activeUser.getActivities().get(i) instanceof TaskActivity)
			{
				System.out.println(i+": "+app.getActiveUser().getActivities().get(i).getName());
				tempList.add(app.getActiveUser().getActivities().get(i));
			}
		}
		while(!scanner.hasNextInt()) scanner.next();
		
		return tempList.get(scanner.nextInt());
		
	}*/
	
	private void printAllActivitesInfo()
	{
		if(app.getActiveUser().getActivities() == null || app.getActiveUser().getActivities().size() == 0)
		{
			System.out.println("You currently have no activities.");
		} else
		{
			for(int i = 0; i<app.getActiveUser().getActivities().size();i++)
			{
				System.out.println(i+": "+app.getActiveUser().getActivities().get(i).getName());
				System.out.println("Start time: "+helper.calendarToString(app.getActiveUser().getActivities().get(i).getStartTime()));
				System.out.println("End time: "+helper.calendarToString(app.getActiveUser().getActivities().get(i).getEndTime()));
			}	
		}
	}
	
	public void clearConsole()
	{
		//Clears the console. 
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
	}
	
}
