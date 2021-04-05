package dtu.projectmanagement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import org.junit.runner.manipulation.Sortable;

public class Employee {

	private String name; 
	private String initials; 
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	
	public Employee(String name, String initials) {
		this.name = name;
		this.initials = initials; // MANGLER CHECK PÅ INITIALS
		
	}
	
	public Project createProject(String title) throws OperationNotAllowed {
		return new Project(title);
	}
	public void endProject() {
		//will be implementet later.
	}
	
	public void getSchedule() {
		//will be implementet later.
	}
	
	public void addActivity(Activity activity) {
		activities.add(activity);
		sortActivities();
	}
	
	public void sortActivities() {
		activities = (ArrayList<Activity>) activities.stream()
				.sorted(Comparator.comparing(Activity::getStartTime))
				.collect(Collectors.toList());
	}
	
//	public Activity editActivity() {
//		//will be implementet later.
//	}
	
	public void setHoursWorked() {
		//will be implementet later
	}
	
	public void editHoursWorked() {
		//will be implementet later
	}

	public String getInitials() {
		return initials; 
	}

	public String getName() {
		return name;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}
	

	
//	public void finishTask() {
//		//will be implementet later
//	}
//	
//	public boolean isAvailable(timeSlot) {
//		return //activity in timeSlot == null ? true : false;
//	}
 }
