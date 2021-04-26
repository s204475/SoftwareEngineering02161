package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.picocontainer.lifecycle.NullLifecycleStrategy;

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
	
	@Given("these employees are contained in the app")
	public void these_employees_are_contained_in_the_app(List<List<String>> employees) {
		for (List<String> employee : employees) {
			managementApp.createEmployee(employee.get(0), employee.get(1));
		}
	}
	
	@Given("there is an employee with the initials {string}")
	public void there_is_an_employee_with_the_intials(String initials) {
		employee = new Employee("John", initials);
		managementApp.addEmployee(employee);
		assertTrue(managementApp.getEmployees().contains(employee));
	}
	
	@Given("the employee is active user")
	public void the_employee_is_active_user() {
	    managementApp.setActiveUser(employee);
	    assertTrue(managementApp.getActiveUser().equals(employee));
	}
	
	@Given("there is a project with the name {string}")
	public void there_is_a_project_with_the_name(String title) throws OperationNotAllowed {
		project = new Project(title);
		managementApp.addProject(project);
		managementApp.setActiveProject(project);
	    assertTrue(managementApp.getProjects().contains(project));
	}

	@Given("the employee is project manager of the project")
	public void the_employee_is_project_manager_of_the_project() {
	    project.assignProjectManager(employee);
	    assertTrue(project.isProjectManager(employee));
	}

	@Then("the task is created")
	public void the_task_is_created() {
		assertTrue(project.getTasks().contains(task));
	}
	
	@Given("the employee is not project manager of the project")
	public void the_employee_is_not_project_manager_of_the_project() {
	    assertFalse(project.isProjectManager(employee));
	}

	@When("the employee creates a task with the name {string} and a estimated time of {int} hours")
	public void the_employee_creates_a_task_with_the_name_and_a_estimated_time_of_hours(String title, Integer time) {
			try {
				task = new Task(title, Duration.ofHours(time));
				managementApp.addTask(task);	
			} catch (OperationNotAllowed e) {
				errorMessageHolder.setErrorMessage(e.getMessage());
			}
	}
	
	@When("the employee creates a task with no name")
	public void the_employee_creates_a_task_with_no_name() {
		try {
			managementApp.createTask("",5);	
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the employee creates a task with no estimated time")
	public void the_employee_creates_a_task_with_no_estimated_time() {
		try {
			managementApp.createTask("project",0);	
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there exist a task with the name {string} and an estimated time of {int} hours, which is the active task")
	public void there_exist_a_task_with_the_name_and_an_estimated_time_of_hours_which_is_the_active_task(String title, Integer time) throws OperationNotAllowed {
		try {
			project.assignProjectManager(employee);
			task = new Task(title, Duration.ofHours(time));
			managementApp.addTask(task);
			managementApp.setActiveTask(task);
			project.removeProjectManager();
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		} // kig på om dette ikke kan refactores sammen med linjen @When("the employee creates a task with the name {string} and a estimated time of {int} hours")
		
	}	
	
	@When("the employee tries to change the estimated time to {int} hours")
	public void the_employee_tries_to_change_the_estimated_time_to_hours(Integer time) {
	    try {
	    	managementApp.setEstimatedTimeOfTask(Duration.ofHours(time));
	    } catch(OperationNotAllowed e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}
	@Then("the estimated time of the project is changed to {int} hours")
	public void the_estimated_time_of_the_project_is_changed_to_hours(Integer time) {
		Duration estimatedTime = task.getEstimatedTime();
		assertEquals(estimatedTime,Duration.ofHours(time));
	    }
	
	@When("the employee edits a task to have no name")
	public void the_employee_edits_a_task_to_have_no_name() {
		try {
			managementApp.setTaskName("");	
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the employee edits a task to have no estimated time")
	public void the_employee_edits_a_task_to_have_no_estimated_time() {
	   try {
		   managementApp.setEstimatedTimeOfTask(Duration.ofHours(0));
	   } catch(OperationNotAllowed e) {
		   errorMessageHolder.setErrorMessage(e.getMessage());
	   }
	   
	}
	
	@When("the employee edits a non existing task")
	public void the_employee_edits_a_non_existing_task() {
	    try {
	    	managementApp.setEstimatedTimeOfTask(Duration.ofHours(5));
	    } catch(OperationNotAllowed e) { // kan sandsynligvis refactors sammen på en smarter måde med @When("the employee tries to change the estimated time to {int} hours")
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }   
	}
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

