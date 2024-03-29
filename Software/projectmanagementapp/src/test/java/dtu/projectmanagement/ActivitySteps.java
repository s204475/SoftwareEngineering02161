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
			assertTrue(activity.getName().equals(name));
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
	public void the_task_is_added_to_activities(String initials) throws OperationNotAllowed {
	    assertTrue(managementApp.searchEmployees(initials).getActivities().contains(taskActivity));
	}
	
	@Given("{string} is not project manager of the project")
	public void is_not_project_manager_of_the_project(String initials) throws OperationNotAllowed {
		assertFalse(managementApp.getActiveProject().isProjectManager(managementApp.searchEmployees(initials)));
	}
	
	@When("{string} assigns the task to {string}")
	public void assigns_the_task_to(String initials1, String initials2) throws OperationNotAllowed {
	    managementApp.setActiveUser(managementApp.searchEmployees(initials1));
	    try {
			managementApp.assignTask(initials2, taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@Then("the task is not added to {string} activities")
	public void the_task_is_not_added_to_activities(String initials) throws OperationNotAllowed {
	    assertFalse(managementApp.searchEmployees(initials).getActivities().contains(taskActivity));
	}

	
	@Given("{string} has an activity starting at {int} {int} {int} {int} {int} and ending at {int} {int} {int} {int} {int}")
	public void has_an_activity_starting_at_and_ending_at(String initials, Integer yearStart, Integer monthStart, Integer dayStart, Integer hourStart, Integer minuteStart, Integer yearEnd, Integer monthEnd, Integer dayEnd, Integer hourEnd, Integer minuteEnd) throws OperationNotAllowed {
	    managementApp.setActiveUser(managementApp.searchEmployees(initials));
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

	@When("the active user assigns the task to {string} with the start time {int} {int} {int} {int} {int}")
	public void the_active_user_assigns_the_task_to_with_the_start_time(String initials, Integer yearStart, Integer monthStart, Integer dayStart, Integer hourStart, Integer minuteStart) {
	    try {
			taskActivity = new TaskActivity(
					"Working on task", 
					new GregorianCalendar(yearStart, monthStart, dayStart, hourStart, minuteStart),
					new GregorianCalendar(yearStart, monthStart, dayStart, hourStart + 1, minuteStart), task);
			managementApp.assignTask(initials, taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	@When("the active user assigns the task to {string} with the end time {int} {int} {int} {int} {int}")
	public void the_active_user_assigns_the_task_to_with_the_end_time(String initials, Integer yearEnd, Integer monthEnd, Integer dayEnd, Integer hourEnd, Integer minuteEnd) {
	    try {
			taskActivity = new TaskActivity(
					"Working on task", 
					new GregorianCalendar(yearEnd, monthEnd, dayEnd - 1, hourEnd, minuteEnd),
					new GregorianCalendar(yearEnd, monthEnd, dayEnd, hourEnd, minuteEnd), task);
			managementApp.assignTask(initials, taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@When("the employee wants to change the name of the activity to {string} and the startime to {int} {int} {int} {int} {int}")
	public void the_employee_wants_to_change_the_name_of_the_activity_to_and_the_startime_to(String newName, Integer newYear, Integer newMonth, Integer newDay, Integer newHour, Integer newMinute) {
	    assertFalse(activity.getName().equals(newName));
	    if(managementApp.getActiveActivity()!=activity) {
	    managementApp.setActiveActivity(activity);
	    }
	    managementApp.setNewActivityName(newName);
	    
	    assertFalse(activity.getStartTime().equals(new GregorianCalendar(newYear,newMonth,newDay,newHour,newMinute)));
	    activity.setStartTime(new GregorianCalendar(newYear,newMonth,newDay,newHour,newMinute));
	}

	@Then("the name of the activity is {string} and the startime is {int} {int} {int} {int} {int}")
	public void the_name_of_the_activity_is_and_the_startime_is(String newName, Integer newYear, Integer newMonth, Integer newDay, Integer newHour, Integer newMinute) {
		assertTrue(activity.getName().equals(newName));
		assertTrue(activity.getStartTime().equals(new GregorianCalendar(newYear,newMonth,newDay,newHour,newMinute)));
	}
	
	@Given("the active user is assigned to the task")
	public void the_active_user_is_assigned_to_the_task() {
		try {
			task = managementApp.getActiveTask();
			taskActivity = new TaskActivity(
					"Working on task", 
					new GregorianCalendar(2000, 11, 15, 10, 0),
					new GregorianCalendar(2000, 12, 15, 10, 0), task);
			activity = taskActivity;
			managementApp.assignTask(managementApp.getActiveUser().getInitials(), taskActivity);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}

	@When("the active user deletes their task-activty")
	public void the_active_user_deletes_their_task_activty() {
	    managementApp.deleteActivity(activity);
	}

	@Then("the user is no longer working on the task")
	public void the_user_is_no_longer_working_on_the_task() {
		if(task.getEmployeesOnTask().isEmpty()) {
			assertTrue(task.getEmployeesOnTask().isEmpty());
		} else {
	    assertFalse(task.getEmployeesOnTask().contains(managementApp.getActiveUser()));
		}
	}
	
}
