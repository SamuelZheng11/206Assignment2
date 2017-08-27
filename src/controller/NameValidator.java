package controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import commandLineExecutor.CommandLineFeedbackPrinter;

public class NameValidator {

	private String creationName;

	public NameValidator(String creationName) {
		this.creationName = creationName;
	}

	public boolean isValidName() {
		if (nameExists() || emptyName() || containsUnexpectedCharacters()) {
			return false;
		}
		return true;
	}

	private boolean containsUnexpectedCharacters() {

		Pattern pattern = Pattern.compile("\\W+");

		Matcher matcher = pattern.matcher(creationName);

		int invalidCharacter = 0;

		int charStart;
		int charEnd;

		while (matcher.find()) {
			charStart = matcher.start();
			charEnd = matcher.end();
			for (int index = charStart; index < charEnd; index++) {
				char characterAtIndex = creationName.charAt(index);
				if (!((characterAtIndex == '-') || (characterAtIndex == '_'))) {
					invalidCharacter++;
				}
			}

		}

		if (invalidCharacter == 0) {
			return false;
		} else {
			return true;
		}
	}

	private boolean emptyName() {
		if (creationName.equals("")) {
			return true;
		}
		return false;
	}

	private boolean nameExists() {

		ArrayList<String> commands = new ArrayList<String>();
		commands.add("ls");

		CommandLineFeedbackPrinter printer = new CommandLineFeedbackPrinter(commands);
		ArrayList<String> feedback = printer.executeCommands();

		for (String line : feedback) {
			if (line.equals(creationName)) {
				return true;
			}
		}
		return false;
	}

}
