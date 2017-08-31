package commandLineExecutor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import createCreationsUtility.finders.WorkingDirectoryFinder;

public class CommandLineFeedbackPrinter {
	
	/**
	 * this class is concerned with the execution and returning the results to a caller that needs to execute
	 * commands on the command line
	 * 
	 * this is its own class because this is a very general class that can be used by many other classes and
	 * would highly likely need to be used in future iterations which can reduce cost.
	 * 
	 * this class is performed on the ED thread because this class should never encounter blocking 
	 * as it should only be used for returning items from the command line to the caller
	 */
	//Initializing a field that can be accessed by all child classes to find the current working directory
	private WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
	//defines a load path that is the default starting position, cd Creations should not load during initial
	//start up because it has not been made yet 
	//in future this could be re-factored to have different staring position it can start at,
	//and not just the Creations folder
	private String commands = "cd " + directoryFinder.getWorkingDirectory()  + ";" + "cd Creations;";
	//Initializing a local string to store the feedback from the command line
	private ArrayList<String> feedback = new ArrayList<String>();
	
	//constructor that takes in the input commands and stores it to be executed onto the command line
	//later
	public CommandLineFeedbackPrinter( ArrayList<String> executionCommands) {
		//adds the commands passed into the constructor to the list of commands that need to be executed
		for(String command : executionCommands) {
			//Concatenate with semi-colon to signify end of line
			commands += command + ";";
		}
	}
	
	//method for the caller to execute the commands
	public ArrayList<String> executeCommands() {
		
		//try to execute commands as it is done in a proccess builder
		try {
			//create the builder and execute the commands in bash
			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", commands);
			
			//start executing the commands
			Process process = builder.start();

			//set up for reading the command line with inputStreamReader and BufferedReader
			InputStream out = process.getInputStream();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(out));
			
			//Initialize a variable to store the print from the command line
			String line = null;
			//while the line is not null (EOF)
			while ((line = stdout.readLine()) != null) {
				//add the line to the feedback that goes to the caller
				feedback.add(line);
			}
			//return the caller the feedback from the command line
			return feedback;

		} catch (Exception ex) {
			//catch any exceptions and print an error message
			System.out.println("Unexpected error occured when running commands in bash and trying to feed them back to the caller");
		}
		
		return feedback;
	}
}
