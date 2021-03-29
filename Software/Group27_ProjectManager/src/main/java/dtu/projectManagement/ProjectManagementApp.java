package dtu.projectManagement;

import java.util.ArrayList;

public class ProjectManagementApp {
	Employee activeUser;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public void createProject(String title, int id) {
		Project project = activeUser.createProject(title, id);
		projects.add(project);
	}
	
	
	
	
}
