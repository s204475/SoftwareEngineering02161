package dtu.projectmanagement;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

//The ReportWriter handles creating the report as an actual .txt to a specified path on the user's computer

// Anders Gad s204496
public class ReportWriter {

	private String path;
	
	public ReportWriter(String path)
	{
		this.path = path;
	}
	
	public void writeReportToFile(String fileName, String report) throws IOException
	{
		FileWriter write = new FileWriter(path + "\\"+fileName+".txt");
		PrintWriter print = new PrintWriter(write);
		
		print.printf("%s" + "%n", report);
		
		print.close();
	}
	
}
