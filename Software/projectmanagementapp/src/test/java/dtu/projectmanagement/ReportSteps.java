package dtu.projectmanagement;

import java.io.File;
import java.io.IOException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
		managementApp.createEmployee("John", managementApp.createInitials("John"));
		employee = managementApp.searchEmployees("J");
	    managementApp.addEmployee(employee);
	    managementApp.setActiveUser(employee);
	    //managementApp.createProject("project1");
	    project = managementApp.searchProjectsTitle("project1");
	    managementApp.setActiveProject(project);
	    managementApp.assignProjectManager(employee);
	    
	    //The report will be printed to the main folder of the java project
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

	@When("the employee tries creates a report to the bad path {string}")
	public void the_employee_tries_creates_a_report_to_the_bad_path(String badPath) throws IOException {
		managementApp.printReport(badPath);
	}

	@Then("the report is not created in {string} and an error is given")
	public void the_report_is_not_created_in_and_an_error_is_given(String badPath) {
	    assertFalse(new File(badPath).isFile());
	}
	
}