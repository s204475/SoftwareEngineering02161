package dtu.projectmanagement;

import java.util.ArrayList;

public class ProjectManagementApp {
	Employee activeUser;
	
	ArrayList<Project> projects = new ArrayList<Project>();
	ArrayList<Employee> employees = new ArrayList<Employee>();
	
	public void createProject(String title) throws OperationNotAllowed {
		Project project = activeUser.createProject(title);
		addProject(project);
	}
	public void addProject(Project project) {
		projects.add(project);
	}
	
	
	public void createEmployee(String name, String initials) {
		Employee employee = new Employee(name, initials);
		addEmployee(employee);
	}
	
	public void addEmployee(Employee employee) {
		employees.add(employee);
	}
	
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setActiveUser(Employee employee) {
		activeUser = employee;
	}
	
	public Employee getActiveUser() {
		return activeUser;
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}
	
//	public ArrayList<Employee> getAvailableEmployees() {
//	ArrayList<Employee> availableEmployees= new ArrayList<Employee>();
//	for(Employee employee : employees) {
//		if(employee.isAvailable()) {
//			availableEmployees.add(employee);
//		}
//	}
//	return availableEmployees;
//}
	
	
}
