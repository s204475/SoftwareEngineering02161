package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TaskSteps {
	private ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	
	public TaskSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}

	@Given("these employees are contained in the app")
	public void these_employees_are_contained_in_the_app(List<List<String>> employees) {
		for (List<String> employee : employees) {
			managementApp.createEmployee(employee.get(0), employee.get(1));
		}
	}

	@Given("there is a project {string}")
	public void there_is_a_project(String title) throws OperationNotAllowed {
	    managementApp.createProject(title);
	}

	@Given("{string} is project manager of {string}")
	public void is_project_manager_of(String initaials, String project) {
	    
	}
}
