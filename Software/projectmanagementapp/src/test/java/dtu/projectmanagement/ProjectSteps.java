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
	
	
	@Given("there is an employee who is active user")
	public void there_is_an_employee_who_is_active_user() {
		try {
		String name = "John Hans Petersen";
	    employee = new Employee(name, managementApp.createInitials(name));
	    managementApp.addEmployee(employee);
	    managementApp.setActiveUser(employee);
	    assertTrue(managementApp.getEmployees().contains(employee));
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	}
	
	@Then("the project is created and added to the list of projects")
	public void the_project_is_created_and_added_to_the_list_of_projects() {
		assertTrue(managementApp.getProjects().contains(project));
	}

	@Then("the error message {string} is given")
	public void the_error_message_is_given(String errorMessage) throws OperationNotAllowed {
		assertEquals(errorMessage, this.errorMessageHolder.getErrorMessage());
	}
	
	@Then("the project is not added to the list of projects")
	public void the_project_is_not_added_to_the_list_of_projects() {
	    assertFalse(managementApp.getProjects().contains(project));
	}
	
	@When("the active user tries to create a project with the name {string}")
	public void the_active_user_tries_to_create_a_project_with_the_name(String title) {
	    try {
			project = new Project(title, 0);
			managementApp.addProject(project);
		} catch (OperationNotAllowed e) {
			errorMessageHolder.setErrorMessage(e.getMessage());
		}
	    
	}
	@When("the employee searches for the project with the Id {string}")
	public void the_employee_searches_for_the_project_with_the_id(String Id) throws OperationNotAllowed {
	    	try {
	    	project = managementApp.searchProjectsId(Id);
	    	} catch (OperationNotAllowed e) {
	    		errorMessageHolder.setErrorMessage(e.getMessage());
	    	}
	}

    @Then("the project {string} is given to the user.")
    public void the_project_is_given_to_the_user(String ProjectName) {
    	assertTrue(project.getTitle().equals(ProjectName));
    }

    @When("the employee searches for the project with the Title for {string}")
    public void the_employee_searches_for_the_project_with_the_title_for(String projectName) throws OperationNotAllowed {
	    	try {
	    	project = managementApp.searchProjectsTitle(projectName);
	    	} catch (OperationNotAllowed e) {
	    		errorMessageHolder.setErrorMessage(e.getMessage());
	    	}
	    }
}
