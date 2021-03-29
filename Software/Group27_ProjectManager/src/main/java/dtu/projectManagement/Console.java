package dtu.projectManagement;

import java.util.Scanner;

/* The UI done as console commands 
 * By Victor Rasmussen s204475
*/

public class Console {
	
	public static Scanner scanner = new Scanner(System.in);
	private ProjectMangementApp app;
	
	public Console(ProjectMangementApp app)
	{
		this.app = app;
		chooseActiveUser();
	}
	
	//Prints all employees in the app and the active user can then be chosen.
	public void chooseActiveUser()
	{	
		for(int i = 0; i<app.employees.length;i++)
		{
			System.out.println(i+": "+app.employees[i].name);
		}
		
		while (!scanner.hasNextInt()) scanner.next();
		
		app.setActiveUser(app.employees[scanner.nextInt()]);
		
		seeProjects();
	}
	
	//Prints all active projects and chooses one to be the active project
	public void seeProjects()
	{
		for(int i = 0; i<app.projects.length;i++)
		{
			System.out.println(i+": "+app.projects[i].title);
		}
		
		System.out.println("Choose a project");
		
		while (!scanner.hasNextInt() || scanner.nextInt()<0 || scanner.nextInt()>app.projects.length) scanner.next();
		
		app.setActiveProject(app.projects[scanner.NextInt()]);
		
		activeProjectChoices();
	}
	
	
	//Prints all eligible employees and assigns one as project manager on currently active project
	public void setProjectManager()
	{
		for(int i = 0; i<app.employees.length;i++)
		{
			System.out.println(i+": "+app.employees[i].name);
		}
		
		System.out.println("Choose a Project Manager");
		
		while (!scanner.hasNextInt()) scanner.next();
		app.activeProject.assignProjectManager(app.employees[scanner.hasNextInt()]);
	}
	
	//Choices after choosing a project
	public void activeProjectChoices()
	{
		System.out.println("Current project: " + app.activeProject.title
				+ "\n1: See tasks"
				+ "\n2: Edit tasks"
				+ "\n3: Create task"
				+ "\n4: Assign project manager"
				+ "\n5: Go back");
		
		if(app.activeProject.projectManager == app.activeUser)
		{
			System.out.println(""
				+ "6: Create a report"
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
				if(app.activeProject.projectManager != app.activeUser)
				{
					System.out.println("Incorrect input.");
				}
		}
		
		if(app.activeProject.projectManager == app.activeUser)
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
					if(choice <1 || choice > 8)
					{
						System.out.println("Incorrect input.");
					}
					break;
			}
		}
		
		activeProjectChoices();
	}
	
	private void createReport()
	{
		// TODO Auto-generated method stub
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
		app.createTask(taskName,scanner.nextDouble());
	}
	
	private void editTasks() {
		System.out.println("Choose task to edit");
		
		printTasks();
		
		while (!scanner.hasNextInt()) scanner.next();

		app.setActiveTask(scanner.nextInt());
		
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
				app.setTaskStartTime(scanner.nextInt());
				break;
			case 3: 
				System.out.println("Input new estimated time");
				app.setTaskEstimatedTime(scanner.nextInt());
				break;
			case 4: 
				System.out.println("Input new time worked on task");
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
		for(int i = 0; i<app.activeProject.tasks.length;i++)
		{
			System.out.println(i+": "+app.activeProject.tasks[i].getName());
		}
	}
	
	//Prints all tasks and their information
	private void printAllTaskInformation()
	{
		for(int i = 0; i<app.activeProject.tasks.length;i++)
		{
			System.out.println(i+": "+app.activeProject.tasks[i].getName());
			System.out.println(app.activeProject.tasks[i].getStartTime());
			System.out.println(app.activeProject.tasks[i].getEstimatedTime());
			System.out.println(app.activeProject.tasks[i].getTimeSpent());
			System.out.println(app.activeProject.tasks[i].getRemainingTime());
		}
	}
}
