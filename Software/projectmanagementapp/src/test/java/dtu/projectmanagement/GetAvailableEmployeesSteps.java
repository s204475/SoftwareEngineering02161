package dtu.projectmanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class GetAvailableEmployeesSteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private ArrayList<Employee> availableEmployees = new ArrayList<Employee>();
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
		
	public GetAvailableEmployeesSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
		this.employees = managementApp.getEmployees();
	}
	
	@Given("they have an empty schedules")
	public void they_have_an_empty_schedules() {
		for (Employee employee : employees) {
			assertTrue(employee.getActivities().isEmpty());
		}  
	}

	@When("the active user provides a duration")
	public void the_active_user_provides_a_duration() {
	    this.startTime = new GregorianCalendar(2021, 10, 10, 12, 0);
	    this.endTime = new GregorianCalendar(2021, 10, 10, 14, 0);
	    
	}

	@When("there is an employee who is available in the duration")
	public void there_is_an_employee_who_is_available_in_the_duration() throws OperationNotAllowed {
	    availableEmployees = managementApp.getAvailableEmployees(startTime, endTime);
	    assertFalse(availableEmployees.isEmpty());
	}	
	
	@Then("the available employees {string} and {string} are given")
	public void the_available_employees_and_are_given(String string, String string2) {
		for (Employee employee : employees) {
			assertTrue(availableEmployees.contains(employee));
		}
	}
	
	@Given("they have activities from year {int} month {int} day {int} hour {int} to year {int} month {int} day {int} hour {int}")
	public void they_have_activities_from_year_month_day_hour_to_year_month_day_hour(Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
		GregorianCalendar startTime = new GregorianCalendar(startYear, startMonth, startDay, startHour, 0);
	    GregorianCalendar endTime = new GregorianCalendar(endYear, endMonth, endDay, endHour, 0);
	    for (Employee employee : employees) {
	    	employee.addActivity(new Activity("Aktivitet", startTime, endTime));
	    	assertFalse(employee.getActivities().isEmpty());
	    }
	}

	@When("the active user provides a duration from year {int} month {int} day {int} hour {int} to year {int} month {int} day {int} hour {int}")
	public void the_active_user_provides_a_duration_from_year_month_day_hour_to_year_month_day_hour(Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) {
	   this.startTime = new GregorianCalendar(startYear, startMonth, startDay, startHour, 0);
	   this.endTime = new GregorianCalendar(endYear, endMonth, endDay, endHour, 0);
	}
	
	
	@Then("there are no employees who are available")
	public void there_are_no_employees_who_are_available() throws OperationNotAllowed {
		try {
			availableEmployees = managementApp.getAvailableEmployees(startTime, endTime);
		    assertTrue(availableEmployees.isEmpty());
		} catch (IllegalArgumentException e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}

	@When("the active user provides the same start time and end time")
	public void the_active_user_provides_the_same_start_time_and_end_time() {
	    try {
	    	GregorianCalendar startTime = new GregorianCalendar(0000, 00, 00, 0, 0);
	    	GregorianCalendar endTime = new GregorianCalendar(0000, 00, 00, 0, 0);
			availableEmployees = managementApp.getAvailableEmployees(startTime, endTime);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	
}
