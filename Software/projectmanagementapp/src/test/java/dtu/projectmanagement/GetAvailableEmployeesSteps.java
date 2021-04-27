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
	private GregorianCalendar[] timeSpan;
	private Employee employee;
		
	public GetAvailableEmployeesSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
		this.employees = managementApp.getEmployees();
	}

	@Given("{string} has an activty from year {int} month {int} day {int} hour {int} to year {int} month {int} day {int} hour {int}")
	public void has_an_activty_from_year_month_day_hour_to_year_month_day_hour(String initials, Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
	    try {
	    	employee = managementApp.searchEmployees(initials);
	    	Activity activity = createActivity(startYear, startMonth, startDay, startHour, endYear, endMonth, endDay, endHour);
		    employee.addActivity(activity);
	    } catch (OperationNotAllowed e) {
	    	errorMessageHolder.setErrorMessage(e.getMessage());
	    }
	}
	
	@Given("{string} has no activty from year {int} month {int} day {int} hour {int} to year {int} month {int} day {int} hour {int}")
	public void has_no_activty_from_year_month_day_hour_to_year_month_day_hour(String initials, Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
		try {
			timeSpan = createTimeSpan(startYear, startMonth, startDay, startHour, endYear, endMonth, endDay, endHour);
			availableEmployees = managementApp.getAvailableEmployees(timeSpan[0], timeSpan[1]);
			assertTrue(availableEmployees.contains(managementApp.searchEmployees(initials))); 
		} catch(OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the employees {string} is given")
	public void the_employees_is_given(String string) throws OperationNotAllowed {
		try {
			assertTrue(availableEmployees.contains(managementApp.searchEmployees(string)));	
		}
		catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@When("the active user searches for availble employees in the duration from year {int} month {int} day {int} hour {int} to year {int} month {int} day {int} hour {int}")
	public void the_active_user_searches_for_availble_employees_in_the_duration_from_year_month_day_hour_to_year_month_day_hour(Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
	   try {
		   timeSpan = createTimeSpan(startYear, startMonth, startDay, startHour, endYear, endMonth, endDay, endHour);
		   availableEmployees = managementApp.getAvailableEmployees(timeSpan[0], timeSpan[1]);
	   } catch(OperationNotAllowed e) {
		   errorMessageHolder.setErrorMessage(e.getMessage());
	   }
	}

	public GregorianCalendar[] createTimeSpan(Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
		GregorianCalendar[] timeSpan= new GregorianCalendar[2];
		timeSpan[0]= new GregorianCalendar(startYear, startMonth, startDay, startHour, 0);
		timeSpan[1]= new GregorianCalendar(endYear, endMonth, endDay, endHour, 0);
		return timeSpan;
	}
	
	public Activity createActivity(Integer startYear, Integer startMonth, Integer startDay, Integer startHour, Integer endYear, Integer endMonth, Integer endDay, Integer endHour) throws OperationNotAllowed {
		GregorianCalendar[] timeSpan = createTimeSpan(startYear, startMonth, startDay, startHour, endYear, endMonth, endDay, endHour);
	    return (new Activity("Activity", timeSpan[0], timeSpan[1]));
	}
	
}