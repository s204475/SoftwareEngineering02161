package dtu.projectmanagement;

import java.awt.ActiveEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	
	public static void main(String[] args) throws ParseException, OperationNotAllowed
	{
		ProjectManagementApp app = new ProjectManagementApp();
		Console console = new Console(app);	
		console.start();
	}

}
