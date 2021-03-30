package dtu.projectManagement;

public class Activity {
	private String name;
	private Calendar startTime;
	private Calendar endTime;
	private DateServer dateServer = new DateServer();
	
	
	public Activity(String name, Calendar endTime) {
		this.name = name;
		this.startTime = dateServer.getDate();
		this.endTime = endTime;
	}
	
	public Calendar getEndTime() {
		return endTime;
	}

	public String getName()
	{
		return name;
	}
	
	
}
