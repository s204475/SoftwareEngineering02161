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
	
	@Given("there is an employee with the intials {string}")
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
	    assertTrue(managementApp.getProjects().contains(project));
	}

	@Given("the employee is project manager of the project")
	public void the_employee_is_project_manager_of_the_project() {
	    project.assignProjectManager(employee);
	    assertTrue(project.getProjectManager().equals(employee));
	}

	@When("the project manager creates a task with the name {string}")
	public void the_project_manager_creates_a_task_with_the_name(String title) {
		throw new io.cucumber.java.PendingException();
	}

	@Then("the task {string} is created")
	public void the_task_is_created(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
}