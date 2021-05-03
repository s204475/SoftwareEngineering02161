package dtu.projectmanagement;

import java.text.ParseException;

public class Main {
	
	public static void main(String[] args) throws ParseException, OperationNotAllowed
	{
		ProjectManagementApp app = new ProjectManagementApp();
		Console console = new Console(app);	
		console.start();
	}

}
