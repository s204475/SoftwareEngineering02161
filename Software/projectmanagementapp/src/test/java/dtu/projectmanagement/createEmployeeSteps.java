package dtu.projectmanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class createEmployeeSteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	private Project project;


	public createEmployeeSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("An employee with the name {string} is created")
	public void an_employee_with_the_name_is_created(String name) {
		employee = new Employee(name, managementApp.createInitials(name));
		managementApp.addEmployee(employee);
	}

	@Then("the employee exist")
	public void the_employee_exist() {
		assertTrue(managementApp.getEmployees().contains(employee));
	}

	@Given("There is exist an employee with the initials {string} who is not in the system")
	public void there_is_exist_an_employee_with_the_initials_who_is_not_in_the_system(String initials) {
		employee = new Employee("The real name",initials);
		assertFalse(managementApp.getEmployees().contains(employee));
	}

	@When("the employee is assigned to active user")
	public void the_employee_is_assigned_to_active_user() {
		try {
			managementApp.setActiveUser(employee);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("There is exist an project with the title {string} which is not in the system")
	public void there_is_exist_an_project_with_the_title_which_is_not_in_the_system(String name) {
		try {
		project = new Project(name, managementApp.getLastProjectId());
		assertFalse(managementApp.getProjects().contains(project));
		}
		catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the project is assigned to active project")
	public void the_project_is_assigned_to_active_project() {
		try {
			managementApp.setActiveProject(project);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}


}
