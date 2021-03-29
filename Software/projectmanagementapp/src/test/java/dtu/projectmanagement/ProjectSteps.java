package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProjectSteps {
	private Employee employee;
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Project project;
	
	public ProjectSteps(ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	
	@Given("there is an employee")
	public void there_is_an_employee() {
	    employee = new Employee("John", "joh");
	    managementApp.setActiveUser(employee);
	    managementApp.addEmployee(employee);
	    assertTrue(managementApp.getEmployees().contains(employee));
	}

	@When("the active user creates a project with a name")
	public void the_active_user_creates_a_project_with_a_name() throws OperationNotAllowed {
	    assertTrue(managementApp.getActiveUser().equals(employee));
	    project = employee.createProject("Project1");
	    managementApp.addProject(project);
	}

	@Then("the project is created and added to the list of projects")
	public void the_project_is_created_and_added_to_the_list_of_projects() {
		assertTrue(managementApp.getProjects().contains(project));
	}
	
	@When("the active user tries to create a project which does not have a name")
	public void the_active_user_tries_to_create_a_project_which_does_not_have_a_name() {
		assertTrue(managementApp.getActiveUser().equals(employee));
	    try {
			project = employee.createProject("");
			managementApp.addProject(project);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    
	}

	@Then("the error message {string} is given")
	public void the_error_message_is_given(String errorMessage) throws OperationNotAllowed {
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}
	
	@Then("the project is not added to the list of projects")
	public void the_project_is_not_added_to_the_list_of_projects() {
	    assertFalse(managementApp.getProjects().contains(project));
	}
	
	
	
	
}
