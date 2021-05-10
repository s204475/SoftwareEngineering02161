package dtu.projectmanagement;


import static org.junit.Assert.assertFalse;

import java.util.GregorianCalendar;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemovalSteps {

	private Employee employee;
	private Task task;
	private Activity activity;
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	
	public RemovalSteps(ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@When("the employee tries to delete the task")
	public void the_employee_tries_to_delete_the_task() {
		managementApp.deleteTask(task);
	}

	@Then("the task is deleted")
	public void the_task_is_deleted() {
	    assertFalse(managementApp.getActiveProject().getTasks().contains(task));
	}

	@When("the employee tries to delete the activity")
	public void the_employee_tries_to_delete_the_activity() {
		managementApp.deleteActivity(activity);
	}

	@Then("the activity is deleted")
	public void the_activity_is_deleted() {
		assertFalse(managementApp.getActiveUser().getActivities().contains(activity));
	}

	@When("the employee tries to delete the project")
	public void the_employee_tries_to_delete_the_project() throws OperationNotAllowed {
		task = new Task("Task", 5);
		project = new Project("projectForDeletion", 0);
		project.addTask(task);
		managementApp.deleteProject(project);
	}

	@Then("the project is deleted")
	public void the_project_is_deleted() {
		assertFalse(managementApp.projects.contains(project));
	}

	@When("the employee tries to delete a user")
	public void the_employee_tries_to_delete_a_user() {
	    managementApp.deleteEmployee(employee);
	}

	@Then("the user is deleted")
	public void the_user_is_deleted() {
	    assertFalse(managementApp.employees.contains(employee));
	}
	
}
