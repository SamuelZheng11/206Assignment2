package controller;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import commandLineExecutor.CommandLineFeedbackPrinter;
import menus.ViewCreationsMenu;

public class CreationModelUpdater {
	private DefaultListModel<String> listOfCreations = new DefaultListModel<String>();
	private ViewCreationsMenu viewMenu;

	public CreationModelUpdater(ViewCreationsMenu viewMenu) {
		this.viewMenu = viewMenu;
	}

	public void loadCreationsIntoModel() {

		ArrayList<String> commands = new ArrayList<String>();
		commands.add("ls");
		
		CommandLineFeedbackPrinter printer = new CommandLineFeedbackPrinter(commands);
		ArrayList<String> feedback = printer.executeCommands();
		
		for(String module : feedback) {
			listOfCreations.addElement(module);
		}
		
		viewMenu.setJListModel(listOfCreations);

	}
}
