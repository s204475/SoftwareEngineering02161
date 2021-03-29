package dtu.projectmanagement;

import static org.junit.Assert.assertNotNull;

import java.text.DateFormat;
import java.time.Duration;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Project {
	private String title;
	private String id;
	private Calendar startTime; 
	
	private Employee projectManager;
	
	private DateServer dateServer = new DateServer();
	ArrayList<Task> tasks = new ArrayList<Task>();
	
	
	
	public Project(String title) throws OperationNotAllowed {
		if (title == "") {
			throw new OperationNotAllowed("A project needs a name");
		} else {
			this.title = title;
		}
		this.id = setId();
		this.startTime = dateServer.getDate();
	}
	
	public void createTask(String title, Duration estimatedTime) {
		Task task = new Task(title, estimatedTime);
		tasks.add(task);
	}
	
	public void assignProjectManager(Employee employee) {
		projectManager = employee;
	}
	public String setId() { 
		id = Year.now().format(DateTimeFormatter.ofPattern("yy")) + "0001"; // IMPLEMENTER LØBENUMMER
		return id;
	}
	
	
	
	
	
	
	
	
	

}
