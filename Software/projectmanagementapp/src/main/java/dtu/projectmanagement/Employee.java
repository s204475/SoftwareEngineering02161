package dtu.projectmanagement;

public class Employee {

	private String name; 
	
	public Employee(String name) {
		this.name = name;
		
	}
	
	public Project createProject(String title, int id) {
		return new Project(title, id);
	}
	
	public void endProject() {
		//will be implementet later.
	}
	
	public void getSchedule() {
		//will be implementet later.
	}
	
//	public Activity createActivity(String title, int duration) { //tilbagemelding fra Anders omkring, hvilken type, at duration har
//		return new Activity(title, duration);
//	}
	
//	public Activity editActivity() {
//		//will be implementet later.
//	}
	
	public void setHoursWorked() {
		//will be implementet later
	}
	
	public void editHoursWorked() {
		//will be implementet later
	}
	
//	public void finishTask() {
//		//will be implementet later
//	}
//	
//	public boolean isAvailable(timeSlot) {
//		return //activity in timeSlot == null ? true : false;
//	}
 }
