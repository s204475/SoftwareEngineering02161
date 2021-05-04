package dtu.projectmanagement;

import java.util.GregorianCalendar;


public class TaskActivity extends Activity{
	
	/*A TaskActivity is a work-related activity which has an associated task and thus project
	  	Usually used for employees not directly assigned to a task by a project manager.
	 */
	
	private Task task;

	
	public TaskActivity(String name, GregorianCalendar startTime, GregorianCalendar endTime, Task task) throws OperationNotAllowed {
		super(name, startTime, endTime);
		this.task = task;
	}
	
	public Task getTask() {
		return task;
	}
}
