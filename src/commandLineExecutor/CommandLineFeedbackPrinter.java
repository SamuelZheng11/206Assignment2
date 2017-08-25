package commandLineExecutor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import createCreationsUtility.finders.WorkingDirectoryFinder;

public class CommandLineFeedbackPrinter {
	
	//Initializing a field that can be accessed by all child classes to find the current working directory
	private WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
	private String commands = "cd " + directoryFinder.getWorkingDirectory()  + ";" + "cd Creations;";
	private ArrayList<String> feedback = new ArrayList<String>();
	
	public CommandLineFeedbackPrinter( ArrayList<String> executionCommands) {
		
		for(String command : executionCommands) {
			commands += command + ";";
		}
	}
	
	public ArrayList<String> executeCommands() {
		
		try {

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", commands);

			Process process = builder.start();

			InputStream out = process.getInputStream();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(out));
			
			String line = null;
			while ((line = stdout.readLine()) != null) {
				feedback.add(line);
			}
			
			return feedback;

		} catch (Exception ex) {
			System.out.println("Unexpected error occured when running commands in bash");
		}
		

		return feedback;
	}
}
