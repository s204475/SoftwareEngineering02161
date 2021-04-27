package dtu.projectmanagement;

import java.awt.ActiveEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import org.junit.runner.manipulation.Sortable;



public class Employee {

	private String name; 
	private String initials; 
	private ArrayList<Activity> activities = new ArrayList<Activity>();
	private ArrayList<Activity> oldActivities = new ArrayList<Activity>();
	private DateServer dateServer = new DateServer();
	
	
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
	
	public void addActivity(Activity activity) throws OperationNotAllowed {
		for (Activity a : activities) {
			/* MAN KAN EVT. OPTIMERE SØGNINGEN - ELLER LAV ARRAY MED GAMLE ACTIVITIES*/ 
			if ((activity.getStartTime().after(a.getStartTime()) && activity.getStartTime().before(a.getEndTime()) || activity.getStartTime().equals(a.getStartTime()) || activity.getStartTime().equals(a.getEndTime()))) {
				throw new OperationNotAllowed("Timeframe not available");
			}
		}
		activities.add(activity);
		sortActivities();
	}
	
	public void sortActivities() {
		activities = (ArrayList<Activity>) activities.stream()
				.sorted(Comparator.comparing(Activity::getStartTime))
				.collect(Collectors.toList());
	}

	public void assignTask(TaskActivity taskActivity) throws OperationNotAllowed {
		for (Activity a : activities) {
			/* MAN KAN EVT. OPTIMERE SØGNINGEN - ELLER LAV ARRAY MED GAMLE ACTIVITIES*/ 
			if ((taskActivity.getStartTime().after(a.getStartTime()) && taskActivity.getStartTime().before(a.getEndTime()) || taskActivity.getStartTime().equals(a.getStartTime()) || taskActivity.getStartTime().equals(a.getEndTime()))) {
				throw new OperationNotAllowed("Timeframe not available");
			}
		
		}
		activities.add(taskActivity);
		sortActivities();
	}
	
	public void removeActivities() {
		for(int i = 0; i < activities.size(); i++) {
			if(activities.get(i).getEndTime().before(dateServer.getDateAndTime())) {
				oldActivities.add(activities.get(i));
				activities.remove(i);
				sortOldActivities();
			}
		}
		
	}
	
	public void sortOldActivities() {
		oldActivities = (ArrayList<Activity>) oldActivities.stream()
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
	
	public ArrayList<Activity> getOldActivities(){
		return oldActivities;
	}

	
	

	
//	public void finishTask() {
//		//will be implementet later
//	}
//	
	public boolean isAvailable(GregorianCalendar startTime, GregorianCalendar endTime) {
		if(activities.isEmpty()) {
			return true;
		}
		for(int i = 0; i < activities.size(); i++) {
			if(activities.get(i).getEndTime().before(startTime) && i == activities.size() - 1) {
				return true;
			}
			if(activities.get(i).getEndTime().before(startTime) && activities.get(i + 1).getStartTime().after(endTime)) {
				return true;
			}
		}
		return false;
	}
 }
