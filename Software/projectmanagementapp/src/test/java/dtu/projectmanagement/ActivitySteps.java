package dtu.projectmanagement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.GregorianCalendar;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ActivitySteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Activity activity;
	private Task task;
	private TaskActivity taskActivity;
	
	
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
	
	@Given("the employee has an activity starting at {int} {int} {int} {int} {int} and ending at {int} {int} {int} {int} {int}")
	public void the_employee_has_an_activity_starting_at_and_ending_at(Integer yearStart, Integer monthStart, Integer dayStart, Integer hourStart, Integer minuteStart, Integer yearEnd, Integer monthEnd, Integer dayEnd, Integer hourEnd, Integer minuteEnd) {
		try {
	    	activity = new Activity(
	    			"Activity", 
					new GregorianCalendar(yearStart, monthStart, dayStart, hourStart, minuteStart),
					new GregorianCalendar(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd));
			managementApp.addActivity(activity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(managementApp.getActiveUser().getActivities().contains(activity));
	}

	@When("the employee tries to create an activity starting at {int} {int} {int} {int} {int}")
	public void the_employee_tries_to_create_an_activity_starting_at(Integer yearStart, Integer monthStart, Integer dayStart, Integer hourStart, Integer minuteStart) {
		try {
	    	activity = new Activity(
	    			"Activity", 
					new GregorianCalendar(yearStart, monthStart, dayStart, hourStart, minuteStart),
					new GregorianCalendar(0, 0, 0, 0, 0));
			managementApp.addActivity(activity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Given("there is a task in the project")
	public void there_is_a_task_in_the_project() {
		try {
			task = new Task("Task", Duration.ofHours(10));
			managementApp.addTask(task);	
			managementApp.setActiveTask(task);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		assertTrue(managementApp.getActiveProject().getTasks().contains(task));
	}
	@When("the active user assigns the task to {string}")
	public void the_active_user_assigns_the_task_to(String initials) {
		try {
			taskActivity = new TaskActivity(
					"Working on task", 
					new GregorianCalendar(0, 0, 0, 0, 0),
					new GregorianCalendar(0, 0, 1, 0, 0), task);
			managementApp.assignTask(initials, taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
		
	}
	@Then("the task is added to {string} activities")
	public void the_task_is_added_to_activities(String initials) {
	    assertTrue(managementApp.searchEmployees(initials).getActivities().contains(taskActivity));
	}
	
	@Given("{string} is not project manager of the project")
	public void is_not_project_manager_of_the_project(String initials) {
		assertFalse(managementApp.getActiveProject().isProjectManager(managementApp.searchEmployees(initials)));
	}
	
	@When("{string} assigns the task to {string}")
	public void assigns_the_task_to(String initials1, String initials2) {
	    managementApp.setActiveUser(managementApp.searchEmployees(initials1));
	    try {
			managementApp.assignTask(initials2, taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the task is not added to {string} activities")
	public void the_task_is_not_added_to_activities(String initials) {
	    assertFalse(managementApp.searchEmployees(initials).getActivities().contains(taskActivity));
	}
}
