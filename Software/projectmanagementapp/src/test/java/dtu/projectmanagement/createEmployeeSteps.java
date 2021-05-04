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
	private Activity activity;
	private Task task;
	private Employee employee;


	public createEmployeeSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@When("An employee with the name {string} is created")
	public void an_employee_with_the_name_is_created(String name) {
		employee = new Employee(name, managementApp.createInitials(name));
		managementApp.addEmployee(employee);
	}

	@Then("Then the employee exist")
	public void then_the_employee_exist() {
		assertTrue(managementApp.getEmployees().contains(employee));
		}
}
