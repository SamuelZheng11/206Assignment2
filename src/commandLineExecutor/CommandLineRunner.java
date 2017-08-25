package commandLineExecutor;

import java.util.ArrayList;

import javax.swing.SwingWorker;

import createCreationsUtility.finders.WorkingDirectoryFinder;

public class CommandLineRunner extends SwingWorker<Void, Void> {
	
	//Initializing a field that can be accessed by all child classes to find the current working directory
	private WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
	private String commands = "cd " + directoryFinder.getWorkingDirectory()  + ";" + "cd Creations;";
	private CommandLineRunnerListener listener;
	private int waitFor = 0;
	
	public CommandLineRunner( ArrayList<String> executionCommands ) {
		for(String command : executionCommands) {
			commands += command + ";";
		}
	}
	
	public void addListener(CommandLineRunnerListener listener) {
		this.listener = listener;
	}
	
	public void setWaitTime(int waitFor) {
		this.waitFor = waitFor;
	}
	
	@Override
	protected Void doInBackground() {
		
		try {

			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", commands);

			builder.start();
			
			Thread.sleep(waitFor);


		} catch (Exception ex) {
			System.out.println("Unexpected error occured when running commands in bash");
		}
		return null;
	}
	
	@Override
	protected void done() {
		fireProcessHasFinishedUpdate();
	}
	
	private void fireProcessHasFinishedUpdate() {
		listener.commandsHaveBeenExecuted();
	}
	
}
