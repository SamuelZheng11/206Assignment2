package commandLineExecutor;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import createCreationsUtility.finders.WorkingDirectoryFinder;

public class CommandLineRunner extends SwingWorker<Void, Void> {
	
	/**
	 * this class is concerned with the executing of costly or blocking code, by running them on a separate thread
	 * 
	 * this class is very general and should be its own class because a lot of other classes may need to execute 
	 * something that is costly on the command line.
	 * 
	 * this class main deals with commands on the command line that do not require a return from the command
	 * line, only that the commands are executed
	 */
	
	//Initializing a field that can be accessed by all child classes to find the current working directory
	private WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
	//defines a starting position before commands are executed, during the very first execution of Creations
	//it would not exist so that last part will not execute ("cd Creations;")
	private String commands = "cd " + directoryFinder.getWorkingDirectory()  + ";" + "cd Creations;";
	//Initializing a listener that will listen for when commands have finished running on different threads
	private CommandLineRunnerListener listener;
	//defines a wait time that the thread should wait for while other commands are being run
	private int waitFor = 0;
	
	//constructor for defining what commands need to be run
	public CommandLineRunner( ArrayList<String> executionCommands ) {
		for(String command : executionCommands) {
			commands += command + ";";
		}
	}
	
	//method for adding a listener for when the commands on a separate thread have finished running
	public void addListener(CommandLineRunnerListener listener) {
		this.listener = listener;
	}
	
	//a way for the caller to set the amount of time the execution on this thread should wait for while
	//other commands on other threads are executing (ie to sync up with the audio recording)
	public void setWaitTime(int waitFor) {
		this.waitFor = waitFor;
	}
	
	//Defining a swing doInBackground method that should be run on a separate thread to the ED thread
	@Override
	protected Void doInBackground() {
		
		try {
			//build a builder with respect to the defined commands in bash
			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", commands);
			//start the builder 
			builder.start();
			//wait for the amount of time defined
			Thread.sleep(waitFor);

		} catch (Exception ex) {
			//catch any exceptions and print an error message
			System.out.println("Unexpected error occured when running commands in bash");
		}
		return null;
	}
	
	//once the doInBackground finishes execute the process has finished method
	@Override
	protected void done() {
		fireProcessHasFinishedUpdate();
	}
	
	//fire to all listeners that the process has finished
	private void fireProcessHasFinishedUpdate() {
		listener.commandsHaveBeenExecuted();
	}
	
}
