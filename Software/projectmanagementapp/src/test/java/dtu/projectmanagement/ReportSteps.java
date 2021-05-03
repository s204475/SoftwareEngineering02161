package dtu.projectmanagement;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReportSteps {

	private Employee employee;
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	
	public ReportSteps(ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the employee tries to create a report to default path")
	public void the_employee_tries_to_create_a_report_to_default_path() throws OperationNotAllowed, IOException {
		project = managementApp.getActiveProject();
	    managementApp.printReport(System.getProperty("user.dir"));
	    
	}

	@Then("the report is created")
	public void the_report_is_created() {
		assertTrue(new File(System.getProperty("user.dir")
				+"\\"
				+"Report on "
				+project.getTitle()
				+" ("
				+project.getId()
				+")"
				+".txt").isFile());
	}

	@When("the employee tries creates a report to the path {string}")
	public void the_employee_tries_creates_a_report_to_the_path(String badPath) throws IOException {
		try {
			managementApp.printReport(badPath);
		} catch (IOException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("the employee with the initials {string} is assigned to the task")
	public void the_employee_with_the_initials_is_assigned_to_the_task(String string) throws OperationNotAllowed {
		try {
		managementApp.getActiveTask().addEmployeeToTask(managementApp.searchEmployees(string));
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the report contains information")
	public void the_report_contains_information() {
		assertTrue(new File(System.getProperty("user.dir")
				+"\\"
				+"Report on "
				+project.getTitle()
				+" ("
				+project.getId()
				+")"
				+".txt").length() > 0);
	}
	
	
//    managementApp.assignProjectManager(employee);
//    
//    Task task = new Task("task1",10);
//    managementApp.setActiveTask(task);
//    task.addEmployeeToTask(employee);
//    managementApp.addTask(task);
    
    //The report will be printed to the main folder of the java project
	
	//employee = new Employee("John med et langt navn",managementApp.createInitials("John med et langt navn"));
	//employee = managementApp.searchEmployees("J");
   // managementApp.addEmployee(employee);
   // managementApp.setActiveUser(employee);
	
}
