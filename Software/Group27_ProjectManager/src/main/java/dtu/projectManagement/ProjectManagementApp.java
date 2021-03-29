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
	
	public void createEmployee(String name) {
		Employee employee = new Employee(name);
		employees.add(employee);
	}
	
	public ArrayList<Employee> getAvailableEmployees() {
	ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
	for(Employee employee : employees) {
		if(employee.isAvabilble()) {
			availableEmployees.add(employee);
		}
	}
	return availableEmployees;
}
	
	
}
