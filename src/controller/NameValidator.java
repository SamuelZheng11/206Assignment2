package controller;

import java.util.ArrayList;

import commandLineExecutor.CommandLineFeedbackPrinter;

public class NameValidator{

	private String creationName;
	
	public NameValidator(String creationName) {
		this.creationName = creationName;
	}
	
	public boolean isValidName() {
		if(nameExists()) {
			return false;
		}
		return true;
	}
	
	private boolean nameExists() {
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("ls");
		
		CommandLineFeedbackPrinter printer = new CommandLineFeedbackPrinter(commands);
		ArrayList<String> feedback = printer.executeCommands();
		
		for(String line : feedback){
			if(line.equals(creationName)) {
				return true;
			}
		}
		return false;
	}
	
}
