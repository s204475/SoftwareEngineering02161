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
			task = new Task(title, time);
			managementApp.addTask(task);	
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	
}
