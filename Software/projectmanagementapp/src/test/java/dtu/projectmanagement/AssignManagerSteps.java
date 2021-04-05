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
	private Employee employee;
	protected Project project;
	
	public AssignManagerSteps (ProjectManagementApp managementApp, ErrorMessageHolder errorMessageHolder) {
		this.managementApp = managementApp;
		this.errorMessageHolder = errorMessageHolder;
	}
	
	@Given("the {string} doesn't have a project manager")
	public void the_doesn_t_have_a_project_manager(String string) {
		project = managementApp.searchProjectsTitle(string); //sprøge hjælpe lærer her, om der findes en bedre løsning. 
	    assertTrue(project.getProjectManager()== null);
	}
}
