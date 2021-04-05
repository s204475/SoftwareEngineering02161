package dtu.projectmanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Activity activity;
	
	
	public ActivitySteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	@When("when the active user creates an activity with the name {string}")
	public void when_the_active_user_creates_an_activity_with_the_name(String name) {
	    try {
	    	activity = new Activity(
	    			name, 
					new GregorianCalendar(2021, 04, 02, 0, 0),
					new GregorianCalendar(2021, 04, 05, 0, 0));
			managementApp.addActivity(activity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the activity is created and is added to the employees schedule")
	public void the_activity_is_created_and_is_added_to_the_employees_schedule() {
	    assertTrue(managementApp.getActiveUser().getActivities().contains(activity));
	}

	@Then("the activity is not added to the employees schedule")
	public void the_activity_is_not_added_to_the_employees_schedule() {
		assertFalse(managementApp.getActiveUser().getActivities().contains(activity));
	}
}
