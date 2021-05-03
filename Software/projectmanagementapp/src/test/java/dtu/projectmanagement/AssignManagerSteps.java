package dtu.projectmanagement;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.picocontainer.lifecycle.NullLifecycleStrategy;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AssignManagerSteps {
	
	protected ProjectManagementApp managementApp;
	private ErrorMessageHolder errorMessageHolder;
	private Employee firstEmployee;
	private Employee secondEmployee;
	protected Project project;
	
	public AssignManagerSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("there is a project named {string}")
	public void there_is_a_project_named(String title) throws OperationNotAllowed {
		project = new Project(title, 0);
		managementApp.addProject(project);
	    assertTrue(managementApp.getProjects().contains(project));
	}
	
	@Given("the project doesn't have a project manager")
	public void the_project_doesn_t_have_a_project_manager() {
//		project = managementApp.searchProjectsTitle(projectName); //sprøge hjælpe-lærer her, om der findes en bedre løsning. 
	    assertTrue(project.getProjectManager()== null);
	}
	
	@When("the active user assigns {string} as project manager of the project")
	public void the_active_user_assigns_as_project_manager_of_the_project(String initials) throws OperationNotAllowed {
		firstEmployee = managementApp.searchEmployees(initials);
	    project.assignProjectManager(firstEmployee);
	}
	
	@Then("the employee is assigned as project manager of the project")
	public void the_employee_is_assigned_as_project_manager_of_the_project() {
		assertEquals(firstEmployee, project.getProjectManager());
	}
	
	@Given("and the first empolyee is the project manager of the project")
	public void and_the_first_empolyee_is_the_project_manager_of_the_project() {
	    project.assignProjectManager(firstEmployee);
	    assertEquals(firstEmployee, project.getProjectManager());
	}

	@When("the active user assigns the second employee as project manager for the project")
	public void the_active_user_assigns_the_second_employee_as_project_manager_for_the_project() {
		project.assignProjectManager(secondEmployee);
	}

	@Then("the project manager of the project is changed to the second employee")
	public void then_the_project_manager_of_the_project_is_changed_to_the_second_employee() {
		assertEquals(secondEmployee, project.getProjectManager());
	}

	
}
