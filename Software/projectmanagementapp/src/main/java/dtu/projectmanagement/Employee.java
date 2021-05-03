package dtu.projectmanagement;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.stream.Collectors;

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
	
	public void addActivity(Activity activity) throws OperationNotAllowed {
		
		if (!checkTimeframe(activity)) {
			throw new OperationNotAllowed("Timeframe not available"); 
		}
		activities.add(activity);
		sortActivities();
	}
	
	public void addTaskActivity(TaskActivity activity) throws OperationNotAllowed {	
		if (!checkTimeframe(activity)) {
			throw new OperationNotAllowed("Timeframe not available"); 
		}
		activities.add(activity);
		sortActivities();
	}
	
	
	public boolean checkTimeframe(Activity activity) {
		if (activity.getStartTime().equals(activity.getEndTime()) || activity.getEndTime().before(activity.getStartTime()) ||
				activity.getStartTime().get(Calendar.MINUTE) % 30 != 0 || activity.getEndTime().get(Calendar.MINUTE) % 30 != 0) {  
			return false;
		}
		for (Activity a : activities) {    
			if (
					(activity.getStartTime().after(a.getStartTime())   &&  activity.getStartTime().before(a.getEndTime()))  ||
					(activity.getEndTime().after(a.getStartTime())     &&  activity.getEndTime().before(a.getEndTime()))    ||
					(activity.getStartTime().before(a.getStartTime())  &&  activity.getEndTime().after(a.getEndTime()))     ||
					 activity.getStartTime().equals(a.getStartTime())  ||  activity.getEndTime().equals(a.getEndTime())
			) {           
				return false;
			}
			
		}
		return true;
	}
	
	public void sortActivities() {
		activities = (ArrayList<Activity>) activities.stream()
				.sorted(Comparator.comparing(Activity::getStartTime))
				.collect(Collectors.toList());
	}

	public void assignTask(TaskActivity taskActivity) throws OperationNotAllowed {
		addActivity(taskActivity);
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

	
	public boolean isAvailable(GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowed {
		if (startTime.equals(endTime) || endTime.before(startTime) || 
				startTime.get(Calendar.MINUTE) % 30 != 0 || endTime.get(Calendar.MINUTE) % 30 != 0) {     // 1
			throw new OperationNotAllowed("Invalid timeframe");
		}
		
		if(activities.isEmpty() || endTime.before(activities.get(0).getStartTime()) ||
				startTime.after(activities.get(activities.size() - 1).getEndTime())) {         // 2
			return true;
		}
		
		for(int i = 0; i < activities.size() - 1; i++) {        // 3
			if(activities.get(i).getEndTime().before(startTime) && activities.get(i + 1).getStartTime().after(endTime)) {    // 4
				return true;
			}
		}
		return false;
	}
 }
