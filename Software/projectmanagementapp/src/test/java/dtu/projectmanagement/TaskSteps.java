package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskSteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	private Project project;
	private Task task;
	
	public TaskSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("there is an employee with the initials {string}")
	public void there_is_an_employee_with_the_intials(String initials) {
		employee = new Employee("John", initials);
		managementApp.addEmployee(employee);
		assertTrue(managementApp.getEmployees().contains(employee));
	}
	
	@Given("the employee is active user")
	public void the_employee_is_active_user() {
		try {
	    managementApp.setActiveUser(employee);
	    assertTrue(managementApp.getActiveUser().equals(employee));
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Given("{string} is active user")
	public void is_active_user(String initials) throws OperationNotAllowed {
	    managementApp.setActiveUser(managementApp.searchEmployees(initials));
	    assertTrue(managementApp.getActiveUser().equals(managementApp.searchEmployees(initials)));
	}
	
	@Given("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String title) throws OperationNotAllowed {
		project = new Project(title, 0);
		managementApp.addProject(project);
		managementApp.setActiveProject(project);
	    assertTrue(managementApp.getProjects().contains(project));
	}

	@Given("the employee is project manager of the project")
	public void the_employee_is_project_manager_of_the_project() {
		managementApp.getActiveProject().assignProjectManager(employee);
	    assertTrue(project.isProjectManager(employee));
	}
	@Given("{string} is project manager of the project")
	public void is_project_manager_of_the_project(String initials) throws OperationNotAllowed {
	    managementApp.getActiveProject().assignProjectManager(managementApp.searchEmployees(initials));
	    assertTrue(managementApp.getActiveProject().isProjectManager(managementApp.searchEmployees(initials)));
	}

	@Then("the task is created")
	public void the_task_is_created() {
		assertTrue(project.getTasks().contains(task));
	}
	
	@Given("there is a task in the project")
	public void there_is_a_task_in_the_project() {
		try {
			if(managementApp.getActiveProject().getProjectManager() == null) {
			managementApp.getActiveProject().assignProjectManager(employee);
			task = new Task("Task", 10);
			managementApp.addTask(task);	
			managementApp.setActiveTask(task);
			managementApp.getActiveProject().removeProjectManager();
			}
			else {
				task = new Task("Task", 10);
				managementApp.addTask(task);	
				managementApp.setActiveTask(task);
			}
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(managementApp.getActiveProject().getTasks().contains(task));
	}
	
	@Given("the employee is not project manager of the project")
	public void the_employee_is_not_project_manager_of_the_project() {
	    assertFalse(project.isProjectManager(employee));
	}

	@When("the employee tries to creates a task with the name {string} and a estimated time of {int} hours")
	public void the_employee_tries_to_creates_a_task_with_the_name_and_a_estimated_time_of_hours(String title, Integer time) throws OperationNotAllowed {
		createTask(title,time);
	}
	
	@Given("there exist a task with the name {string} and an estimated time of {int} hours, which is the active task")
	public void there_exist_a_task_with_the_name_and_an_estimated_time_of_hours_which_is_the_active_task(String title, double time) throws OperationNotAllowed {
		project.assignProjectManager(employee);
		createTask(title,time);
		project.removeProjectManager();
		} 

	
	@When("the employee tries to change the estimated time to {int} hours")
	public void the_employee_tries_to_change_the_estimated_time_to_hours(double time) {
	    try {
	    	managementApp.setEstimatedTimeOfTask(time);
	    } catch(OperationNotAllowed e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}
	@Then("the estimated time of the task is {int} hours")
	public void the_estimated_time_of_the_task_is_hours(double time) {
		double estimatedTime = task.getEstimatedTime();
		assertTrue(estimatedTime == time);
	    }
	
	@When("the employee tries to change the name to {string}")
	public void the_employee_tries_to_change_the_name_to(String name) {
		try {
			managementApp.setTaskName(name);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the name of the task is {string}")
	public void the_name_of_the_task_is(String TaskName) {
	    assertTrue(task.getName().equals(TaskName));
	}
	
	public void createTask(String title, double time) throws OperationNotAllowed {
		try {
			task = new Task(title, time);
			managementApp.addTask(task);
			managementApp.setActiveTask(task);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} 
	}
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

