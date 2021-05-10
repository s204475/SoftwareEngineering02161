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
	
	
	public Employee(String name, String initials) {
		this.name = name;
		this.initials = initials;
	}
	// Magnus Siegumfeldt s204472
	public void addActivity(Activity activity) throws OperationNotAllowed {
		assert activity != null && activities != null; // Precondition
		if (!checkTimeframe(activity)) {
			assert !activities.contains(activity); // Postcondition
			throw new OperationNotAllowed("Timeframe not available"); 
		}
		activities.add(activity);
		sortActivities();
		assert activities.contains(activity); // Postcondition
	}
	
	public void addTaskActivity(TaskActivity activity) throws OperationNotAllowed {	
		if (!checkTimeframe(activity)) {
			throw new OperationNotAllowed("Timeframe not available"); 
		}
		activities.add(activity);
		sortActivities();
	}
	
	// Victor Rasmussen s204475
	public boolean isAvailable(GregorianCalendar startTime, GregorianCalendar endTime) throws OperationNotAllowed {
		//Checks whether the employee already is available for an activity in the given timeframe
		
		assert startTime != null && endTime != null && activities != null; // Precondition
		boolean result = false;
		if (!checkInput(startTime, endTime)){
			assert result == checkInput(startTime, endTime);
			throw new OperationNotAllowed("Invalid timeframe");
		}
		if (hasActivityAtTime(startTime, endTime)) {
			assert !result;		// Postcondition
			return false;
		} else {
			result = true;
		}
		assert result;    // Postcondition
		return result;
	}
	// Magnus Siegumfeldt s204472
	public boolean checkTimeframe(Activity activity) {
		//Checks whether the employee already has an activity in a given timeframe
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
		//Sorts activities by their start time
		activities = (ArrayList<Activity>) activities.stream()
				.sorted(Comparator.comparing(Activity::getStartTime))
				.collect(Collectors.toList());
	}

	public void assignTask(TaskActivity taskActivity) throws OperationNotAllowed {
		addTaskActivity(taskActivity);
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
	// Victor Rasmussen s204475
	public boolean checkInput(GregorianCalendar startTime, GregorianCalendar endTime) {
		//The system only accepts half-hour intervals
		if (startTime.equals(endTime) || endTime.before(startTime) || 
				startTime.get(Calendar.MINUTE) % 30 != 0 || endTime.get(Calendar.MINUTE) % 30 != 0) {     
			return false;
		}
		return true;
	}
	// Victor Rasmussen s204475
	public boolean hasActivityAtTime(GregorianCalendar startTime, GregorianCalendar endTime) {
		if(activities.isEmpty() || endTime.before(activities.get(0).getStartTime()) ||
				startTime.after(activities.get(activities.size() - 1).getEndTime())) {         
			return false;
		}
		for(int i = 0; i < activities.size() - 1; i++) {       
			if(activities.get(i).getEndTime().before(startTime) && activities.get(i + 1).getStartTime().after(endTime)) {    
				return false;
			}
		}
		return true;
	}
 }

	