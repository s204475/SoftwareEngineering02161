package dtu.projectmanagement;

import java.util.GregorianCalendar;

public class TaskActivity extends Activity{
	private Task task;
	
	public TaskActivity(String name, GregorianCalendar startTime, GregorianCalendar endTime, Task task) throws OperationNotAllowed {
		super(name, startTime, endTime);
		this.task = task;
	}
	public Task getTask() {
		return task;
	}
}
