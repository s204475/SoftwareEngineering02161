package dtu.projectmanagement;

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
	
	@When("the employee tries creates a report to the desktop")
	public void the_employee_tries_creates_a_report_to_the_desktop() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("the report is created on the desktop")
	public void the_report_is_created_on_the_desktop() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@When("the employee tries creates a report to {string}")
	public void the_employee_tries_creates_a_report_to(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Then("the report is not created and an error is given")
	public void the_report_is_not_created_and_an_error_is_given() {
	    // Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
	
}
