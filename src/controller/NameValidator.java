package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commandLineExecutor.CommandLineFeedbackPrinter;

public class NameValidator {
	
/**
 * this class is concerned with the validity of the string passed into it with respect to the 
 * assisngment specifications
 * 
 * the reason why this class is its own class is because it may be the case that other class might need 
 * a way to check validity later on in the development cycle, thus a class that checks for validity itself
 * was created
 */
	
	//a private string that contain the name in question (whether it is valid or not)
	private String creationName;

	
	//construction for creating an instance of the checker and accepts a string to set the name in question
	public NameValidator(String creationName) {
		this.creationName = creationName;
	}

	//public method that other class can call to check the validity of the name entered 
	public boolean isValidName() {
		//calls private methods to check if the name is valid
		if (nameExists() || emptyName() || containsUnexpectedCharacters()) {
			//if any of the private checking methods return true then the name is invalid
			//and returns that the name is invalid
			return false;
		}
		//otherwise the name is valid and returns true
		return true;
	}

	
	//private method for checking if the name contains and unexpected character
	private boolean containsUnexpectedCharacters() {

		//creates a matcher and compiles with respect to non alphaNumeric characters
		Pattern pattern = Pattern.compile("\\W+");

		//tries to match the pattern of non alphaNumeric
		Matcher matcher = pattern.matcher(creationName);

		//a counter for the number of invalid characters picked up by the matcher
		int invalidCharacterCount = 0;

		//Initializes two variables for to indicate the start and finsih of the pattern found by
		//matcher
		int charStart;
		int charEnd;

		// while the matcher can still find a non alphanumeric pattern 
		while (matcher.find()) {
			//define/redefine the start and end position of the pattern
			charStart = matcher.start();
			charEnd = matcher.end();
			
			//iterate through the start and end position
			for (int index = charStart; index < charEnd; index++) {
				//identify the character at each position between the start and finish of the pattern
				char characterAtIndex = creationName.charAt(index);
				//check if the character is a "-" or "_"
				if (!((characterAtIndex == '-') || (characterAtIndex == '_'))) {
					//if it is not either one then increase the number of invalid characters by one
					invalidCharacterCount++;
				}
			}

		}

		//return whether or not the name contains any invalid characters
		if (invalidCharacterCount == 0) {
			return false;
		} else {
			return true;
		}
	}

	
	//checks if the name entered in an empty string
	//this should return as invalid
	private boolean emptyName() {
		if (creationName.equals("")) {
			return true;
		}
		return false;
	}

	//checks if the name entered already exists
	private boolean nameExists() {

		//gets the list of creations present in the model
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("ls");

		//stores the list of creations as a local variable
		CommandLineFeedbackPrinter printer = new CommandLineFeedbackPrinter(commands);
		ArrayList<String> feedback = printer.executeCommands();

		//iterates through the list and checks if there are any that are the same with the name
		//in question
		for (String line : feedback) {
			if (line.equals(creationName)) {
				return true;
			}
		}
		return false;
	}

}
