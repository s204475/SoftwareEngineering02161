package dtu.projectManagement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
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
		chooseActiveUser();
	}
	
	//Prints all employees in the app and the active user can then be chosen.
	public void chooseActiveUser()
	{	
		for(int i = 0; i<app.getEmployees().size();i++)
		{
			System.out.println(i+": "+app.getEmployees().get(i).getName());
		}
		
		while (!scanner.hasNextInt()) scanner.next();
		
		app.setActiveUser(app.getEmployees().get(scanner.nextInt()));
		
		mainMenu();
	}
	
	private void mainMenu()
	{
		System.out.println("Main menu: " 
		+ "\n1: Change active user"
		+ "\n2: See projects"
		+ "\n3: See personal activities"
		+ "\n4: Quit");
		
		while (!scanner.hasNextInt()) scanner.next();
		
		switch(scanner.nextInt())
		{
			case 1:
				chooseActiveUser();
				break;
			case 2: 
				seeProjects();
				break;
			case 3: 
				seePersonalActivities();
				break;
			case 4: 
				System.exit(0);
				break;
		}

	}

	private void seePersonalActivities()
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
				break;
			case 2: 
				addActivity();
				break;
			case 3: 
				printAllActivitesInfo();
				break;
			case 4: 
				printSchedule();
				break;
			case 5: 
				mainMenu();
				break;
		}
	}



	//Prints all active projects and chooses one to be the active project
	public void seeProjects()
	{
		for(int i = 0; i<app.getProjects().size();i++)
		{
			System.out.println(i+": "+app.getProjects().get(i).getTitle());
		}
		
		System.out.println("Choose a project");
		
		while (!scanner.hasNextInt() || scanner.nextInt()<0 || scanner.nextInt()>app.getProjects().size()) scanner.next();
		
		app.setActiveProject(app.getProjects().get(scanner.nextInt()));
		
		activeProjectChoices();
	}
	
	
	//Prints all eligible employees and assigns one as project manager on currently active project
	public void setProjectManager()
	{
		for(int i = 0; i<app.getEmployees().size();i++)
		{
			System.out.println(i+": "+app.getEmployees().get(i).getName());
		}
		
		System.out.println("Choose a Project Manager");
		
		while (!scanner.hasNextInt()) scanner.next();
		app.activeProject.assignProjectManager(app.employees.get(scanner.nextInt()));
	}
	
	//Choices after choosing a project
	public void activeProjectChoices()
	{
		System.out.println("Current project: " + app.getActiveProject().getTitle() + " Serial number: " app.getActiveProject().getId()
				+ "\n1: See tasks"
				+ "\n2: Edit tasks"
				+ "\n3: Create task"
				+ "\n4: Assign project manager"
				+ "\n5: Go back");
		
		if(app.activeProject.getProjectManager() == app.getActiveUser())
		{
			System.out.println("Project Manager choices: "
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
				printAllTaskInformation();
				break;
			case 2: 
				editTasks();
				break;
			case 3: 
				createTask();
				break;
			case 4: 
				setProjectManager();
				break;
			case 5: 
				seeProjects();
				break;
			default: 
				if(app.getActiveProject().getProjectManager != app.activeUser)
				{
					System.out.println("Incorrect input.");
				}
		}
		
		if(app.getActiveProject().getProjectManager() == app.activeUser)
		{
			switch(scanner.nextInt())
			{
				case 6: 
					createReport();
					break;
				case 7: 
					printEstimatedTime();
					break;
				case 8: 
					printRemainingTime();
					break;
				case 9:
					printBudgetedTime();
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
		// TODO iMPLEMENT THIS
		System.out.println("A report is printed");
	}

	private void printBudgetedTime()
	{
		System.out.println(app.getProjectBudgetedTime());
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
		while (!scanner.hasNext()) scanner.next();
		String taskName = scanner.next();
		System.out.println("Input estimated duration of task in hours");
		while (!scanner.hasNextDouble()) scanner.next();
		app.createTask(taskName,scanner.nextDouble()); //duration!
	}
	
	private void editTasks() {
		System.out.println("Choose task to edit");
		
		printTasks();
		
		while (!scanner.hasNextInt()) scanner.next();

		app.setActiveTask(app.getTasks().scanner.nextInt));
		
		System.out.println("Choose what to edit");
		
		System.out.println(""
				+ "\n1: Edit name"
				+ "\n2: Edit start time"
				+ "\n3: Estimated completion time"
				+ "\n4: Time worked on task"
				+ "\n5: Go back");
		
		
		while (!scanner.hasNextInt()) scanner.next();
		int choice = scanner.nextInt();
		switch(choice)
		{
			case 1:
				System.out.println("Input new name");
				app.setTaskName(scanner.next());
				break;
			case 2: 
				//input startTIme
				System.out.println("Change start time to");
				while (!scanner.hasNextInt()) scanner.next();
				app.setTaskStartTime(scanner.nextInt());
				break;
			case 3: 
				System.out.println("Input new estimated time");
				while (!scanner.hasNextInt()) scanner.next();
				app.setTaskEstimatedTime(scanner.nextInt());
				break;
			case 4: 
				System.out.println("Input new time worked on task");
				while (!scanner.hasNextInt()) scanner.next();
				app.setTaskTimeWorked(scanner.nextInt());
				break;
			default:
				break;
		}
		
		activeProjectChoices();
		
	}

	//Prints all tasks (in name only) 
	private void printTasks()
	{
		for(int i = 0; i<app.getActiveProject().getTasks().size();i++)
		{
			System.out.println(i+": "+app.getActiveProject().getTasks().get(i).getName());
		}
	}
	
	//Prints all tasks and their information
	private void printAllTaskInformation()
	{
		for(int i = 0; i<app.getActiveProject().getTasks().size();i++)
		{
			System.out.println(i+": "+app.getActiveProject().getTasks()[i].getName());
			System.out.println("Start time: "+app.getActiveProject().getTasks()[i].getStartTime());
			System.out.println("Estimated time: "+app.getActiveProject().getTasks()[i].getEstimatedTime());
			System.out.println("Time spent: "+app.getActiveProject().getTasks()[i].getTimeSpent());
			System.out.println("Remaining time: "+app.getActiveProject().getTasks()[i].getRemainingTime());
		}
	}
	
	private void printSchedule()
	{
		//To-do: Implement schedule
		System.out.println("This should be a schedule.");
	}

	//Adds a new activity to the active user
	//Test this method thoroughly
	private void addActivity()
	{
		System.out.println("Input name for activity");
		while (!scanner.hasNext()) scanner.nextLine();
		String activityName = scanner.next();
		
		System.out.println("Input start time (e.g: dd-MM-yyyy)");
		
		while (!scanner.hasNext()) scanner.nextLine();
		Date start = convertDate(scanner.nextLine());
		
		System.out.println("Input end time (e.g: dd-MM-yyyy)");
		
		while (!scanner.hasNext()) scanner.nextLine();
		Date end = convertDate(scanner.nextLine());
		        
		Duration activityDuration = Duration.between(start.toInstant(), end.toInstant());
		
		app.createTask(activityName, activityDuration);
	}
	
	private Date convertDate(String dateString) throws ParseException
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		return sdf.parse(sdf.format(dateString));
	}

	private void editActivity()
	{
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
				app.setActivityName(scanner.next());
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

	private void printActivities()
	{
		for(int i = 0; i<app.projects.size();i++)
		{
			System.out.println(i+": "+app.getActiveUser().getActivities().get(i));
		}	
	}
	
	private void printAllActivitesInfo()
	{
		for(int i = 0; i<app.activeUser.getActivities().size();i++)
		{
			System.out.println(i+": "+app.getActivities().get(i).getName());
			System.out.println("Start time: "+app.getActivities().get(i).getStartTime());
			System.out.println("End time: "app.getActivities().get(i).getEndTime());
		}	
	}
}
