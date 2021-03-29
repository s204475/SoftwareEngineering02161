package dtu.projectManagement;

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
	
	public Activity createActivity(String title, int duration) { //tilbagemelding fra Anders omkring, hvilken type, at duration har
		return new Activity(title, duration);
	}
	
	public Activity editActivity() {
		//will be implementet later.
	}
	
	public void setHoursWorked() {
		//will be implementet later
	}
	
	public void editHoursWorked() {
		//will be implementet later
	}
	
	public void finishTask() {
		//will be implementet later
	}
	
	public boolean isAvabilble(timeSlot) {
		return //activity in timeSlot == null ? true : false;
	}
	
//	public Employee[] getAvalibleEmployees() { forkert sted, men rigtig struktur.
//		List<Employee> avalibleEmplyess= new ArrayList<Employee>();
//		for()
//	}
 }
