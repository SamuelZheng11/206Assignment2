package creators.creationCreators;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import commandLineExecutor.CommandLineFeedbackPrinter;

public class CreationModelCreator {
	/**
	 * this method is concerned with creation of the creation model
	 * this method has its own class as it may be the case that different view in the future may need 
	 * a general way to create a model
	 */


	//creates a model and returns the model created
	public DefaultListModel<String> makeModel() {

		//looks for all creations in a specific place
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("ls");
		
		//Obtains the return from the command line printer
		CommandLineFeedbackPrinter printer = new CommandLineFeedbackPrinter(commands);
		ArrayList<String> feedback = printer.executeCommands();
		
		DefaultListModel<String> listOfCreations = new DefaultListModel<String>();
		
		//looks at all the list of creations and loads them into the local variable of a model
		for(String module : feedback) {
			//builds up the model
			listOfCreations.addElement(module);
		}
		//returns it as a result
		return listOfCreations;

	}
}
