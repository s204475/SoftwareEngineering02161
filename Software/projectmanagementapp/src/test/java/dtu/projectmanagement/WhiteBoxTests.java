package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.time.Duration;
import java.util.GregorianCalendar;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WhiteBoxTests {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Employee employee;
	private Activity activity;

	
	public WhiteBoxTests(ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("these employees are contained in the app")
	public void these_employees_are_contained_in_the_app(List<List<String>> employees) throws OperationNotAllowed {
		for (List<String> employee : employees) {
			managementApp.createEmployee(employee.get(0), employee.get(1));
			assertTrue(managementApp.getEmployees().contains(managementApp.searchEmployees(employee.get(1))));
		} 
	}
	@When("there is searched for an employee with the initials {string}")
	public void there_is_searched_for_an_employee_with_the_initials(String initials) {
	    try {
			employee = managementApp.searchEmployees(initials);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the employee with the initials {string} is found")
	public void the_employee_with_the_initials_is_found(String initials) {
	    assertEquals(initials, employee.getInitials());
	}
	
	@Given("that no employees is contained in the app")
	public void that_no_employees_is_contained_in_the_app() {
	    assertTrue(managementApp.getEmployees().isEmpty());
	}
	
	@When("when the active user creates an activity with the name {string}, the start time {int} {int} {int} {int} {int} and end time {int} {int} {int} {int} {int}")
	public void when_the_active_user_creates_an_activity_with_the_name_the_start_time_and_end_time(String name, Integer yearStart, Integer monthStart, Integer dayStart, Integer hourStart, Integer minuteStart, Integer yearEnd, Integer monthEnd, Integer dayEnd, Integer hourEnd, Integer minuteEnd) {
		try {
	    	activity = new Activity(
	    			name, 
					new GregorianCalendar(yearStart, monthStart, dayStart, hourStart, minuteStart),
					new GregorianCalendar(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd));
			managementApp.addActivity(activity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the activity is contained in the employees activities")
	public void the_activity_is_contained_in_the_employees_activities() {
	    assertTrue(managementApp.getActiveUser().getActivities().contains(activity));
	}
	@Then("the activity is not contained in the employees activities")
	public void the_activity_is_not_contained_in_the_employees_activities() {
		assertFalse(managementApp.getActiveUser().getActivities().contains(activity));
	}
	
	
//	@Given("the these activities is contained in the employees activities")
//	public void the_these_activities_is_contained_in_the_employees_activities(List<List<String>> activities) {
//		for (List<String> activity : activities) {
//			managementApp.createEmployee(employee.get(0), employee.get(1));
//			assertTrue(managementApp.getEmployees().contains(managementApp.searchEmployees(employee.get(1))));
//		} 
//	}

	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

