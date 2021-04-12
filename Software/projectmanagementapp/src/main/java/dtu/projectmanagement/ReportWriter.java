package dtu.projectmanagement;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

public class ReportWriter {

	private String path;
	private boolean append = false; //doesn't append, but erases everything in the file
	
	public ReportWriter(String path)
	{
		this.path = path;
	}
	
	public void writeReportToFile(String fileName, String report) throws IOException
	{
		FileWriter write = new FileWriter(path + fileName+".txt");
		PrintWriter print = new PrintWriter(write);
		
		print.printf("%s" + "%n", report);
		
		print.close();
	}
	
}
