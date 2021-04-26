package dtu.projectmanagement;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/* The UI done as console commands 
 * By Victor Rasmussen s204475
*/

public class Console {
	
	public static Scanner scanner = new Scanner(System.in);
	private ProjectManagementApp app;
	
	public Console(ProjectManagementApp app)
	{
		this.app = app;
	}
	
	public void start() throws ParseException, OperationNotAllowed
	{
		System.out.println("Project Management App 2021");
		chooseActiveUser();
	}
	
	public void chooseActiveUser() throws ParseException, OperationNotAllowed
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
			for(int i = 0; i<app.getEmployees().size();i++)
			{
				System.out.println(i+": "+app.getEmployees().get(i).getName());
			}
			
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

	private void mainMenu() throws ParseException, OperationNotAllowed
	{
		//The main menu of the app
		System.out.println("Main menu("+app.getActiveUser().getName()+")"
		+ "\n1: Change active user"
		+ "\n2: Add user"
		+ "\n3: Create project"
		+ "\n4: See projects"
		+ "\n5: Personal activities"
		+ "\n6: Quit");
		
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
				createProject();
				mainMenu();
				break;
			case 4: 
				seeProjects();
				break;
			case 5: 
				seePersonalActivities();
				break;
			case 6: 
				app.ExitApp();
				break;
		}
	}

	private void createProject() throws OperationNotAllowed {
		System.out.println("Choose a name for the project");
		String projectTitle = userInput();
		app.createProject(projectTitle);
		System.out.println("The project can now be found under \"See projects\"");
		pressEnterToContinue();
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
	
	private void seePersonalActivities() throws ParseException, OperationNotAllowed
	{
		System.out.println(""
				+ "\n1: Edit activity"
				+ "\n2: Add activity"
				+ "\n3: See activities"
				+ "\n4: Print schedule"
				+ "\n5: Go back");
		
		while (!scanner.hasNextInt()) scanner.next();
		
		switch(scanner.nextInt())
		{
			case 1:
				editActivity();
				seePersonalActivities();
				break;
			case 2: 
				addActivity();
				seePersonalActivities();
				break;
			case 3: 
				printAllActivitesInfo();
				pressEnterToContinue();
				seePersonalActivities();
				break;
			case 4: 
				printSchedule();
				pressEnterToContinue();
				seePersonalActivities();
				break;
			case 5: 
				mainMenu();
				break;
			default:
				InvalidInput();
				break;
		}
	}

	private void InvalidInput() {
		System.out.println("Invalid input");
	}
	
	public void seeProjects() throws ParseException, OperationNotAllowed
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
	public void setProjectManager() throws ParseException, OperationNotAllowed
	{
		System.out.println("Choose a Project Manager.");
		if(app.getActiveProject().getProjectManager() != null)
		{
			System.out.println("Current project manager: "+app.getActiveProject().getProjectManager().getName());
		} else {
			System.out.println("There is currently no project manager.");
		}
		
		for(int i = 0; i<app.getEmployees().size();i++)
		{
			System.out.println(i+": "+app.getEmployees().get(i).getName());
		}
		
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
	
	public void activeProjectChoices() throws ParseException, OperationNotAllowed
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
				+ "\n6: Create a report"
				+ "\n7: Show estimated time until project completion"
				+ "\n8: Show time spent on project"
				+ "\n9: Show remaining budgeted time");
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
				editTasks();
				break;
			default: 
				if(app.getActiveProject().getProjectManager() != app.activeUser)
				{
					System.out.println("Incorrect input.");
				}
		}
		
		if(app.getActiveProject().getProjectManager() == app.activeUser)
		{
			switch(choice)
			{
				case 6: 
					createReport();
					pressEnterToContinue();
					break;
				case 7: 
					System.out.println("Collectively, the current tasks should take the following hours to complete: ");
					printEstimatedTime();
					System.out.println();
					pressEnterToContinue();
					break;
				case 8: 
					System.out.println("Remaining time on project:");
					printRemainingTime();
					System.out.println();
					pressEnterToContinue();
					break;
				case 9:
					System.out.println("The following total hours are paid (budgeted):");
					printBudgetedTime();
					System.out.println();
					pressEnterToContinue();
					break;
				default: 
					if(choice <1 || choice > 9)
					{
						System.out.println("Incorrect input.");
					}
					break;
			}
		}
		
		if (choice != 5) //unless you go back, you are presented with the project choices again.
		{
			activeProjectChoices();
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

	public void createTask() throws OperationNotAllowed
	{
		System.out.println("Input name for task");
		String taskName = userInput();
		System.out.println("Input estimated duration of task in hours (e.g. 4.5 or 4.0 )");
		while (!scanner.hasNextDouble()) scanner.next();
		app.createTask(taskName,scanner.nextDouble());
	}
	
	private void editTasks() throws ParseException, OperationNotAllowed {
		
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
					+ "\n4: Time worked on task"
					+ "\n5: Add employee to task"
					+ "\n6: Go back to main menu");
			
			
			while (!scanner.hasNextInt()) scanner.next();
			int choice = scanner.nextInt();
			switch(choice)
			{
				case 1:
					System.out.println("Input new name");
					app.setTaskName(userInput());
					break;
				case 2: 
					System.out.println("Input start time (e.g: dd-MM-yyyy)");
					Date start = convertDate(userInput());
					app.setTaskStartTime(start);
					break;
				case 3: 
					System.out.println("Input new estimated time");
					while (!scanner.hasNextDouble()) scanner.next();
					app.setTaskEstimatedTime(scanner.nextDouble());
					break;
				case 4: 
					System.out.println("Input new time worked on task");
					while (!scanner.hasNextDouble()) scanner.next();
					app.setTaskTimeWorked(scanner.nextDouble());
					break;
				case 5: 
					addEmployeeToTask();
					break;
				case 6: 
					mainMenu();
					break;
				default:
					break;
			}
		}
		
		activeProjectChoices();
	}

	
	private void addEmployeeToTask() {
		for(int i = 0; i<app.getEmployees().size();i++)
		{
			System.out.println(i+": "+app.getEmployees().get(i).getName());
		}
		
		while (!scanner.hasNextInt()) scanner.next();
		app.addEmployeeToTask(app.getEmployees().get(scanner.nextInt()));
	}

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
				System.out.println("Start time: "+app.getActiveProject().getTasks().get(i).getStartTime());
				System.out.println("Estimated time: "+app.getActiveProject().getTasks().get(i).getEstimatedTime());
				System.out.println("Time spent: "+app.getActiveProject().getTasks().get(i).getTimeSpent());
				System.out.println("Remaining time: "+app.getActiveProject().getTasks().get(i).getRemainingTime());
			}
		}
	}
	
	private void printSchedule()
	{
		//TODO Implement schedule
		System.out.println("This should be a schedule.");
		pressEnterToContinue();
	}

	
	private void addActivity() throws ParseException
	{
		//Adds a new activity to the active user
			//Test this method thoroughly!
		System.out.println("Input name for activity");
		String activityName = userInput();
		
		System.out.println("Input start time (e.g: dd-MM-yyyy)");
		
		Date start = convertDate(userInput());
		
		System.out.println("Input end time (e.g: dd-MM-yyyy)");
		
		Date end = convertDate(userInput());
		        
		Duration activityDuration = Duration.between(start.toInstant(), end.toInstant());
		
		//app.createActivity(activityName, activityDuration);
	}
	
	private Date convertDate(String dateString) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);

		Date date = sdf.parse(dateString);
		return date;
	}

	private void editActivity() throws ParseException, OperationNotAllowed
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
					+ "\n4: Go back");
			
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
					break;
				case 3: 
					System.out.println("This edits the end time"); //Needs implementation
					break;
				case 4: 
					mainMenu();
					break;
				default:
					System.out.println("Invalid input");
					break;	
			}
			
			if (choice != 4)
			{
				seePersonalActivities();
			}
		}	
	}

	private void printActivities()
	{
		for(int i = 0; i<app.projects.size();i++)
		{
			System.out.println(i+": "+app.getActiveUser().getActivities().get(i));
		}	
	}
	
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
				System.out.println("Start time: "+app.getActiveUser().getActivities().get(i).getStartTime());
				System.out.println("End time: "+app.getActiveUser().getActivities().get(i).getEndTime());
			}	
		}
	}
	
	public void clearConsole()
	{
		//Clears the console. 
		System.out.println(new String(new char[70]).replace("\0", "\r\n"));
	}
	
}
