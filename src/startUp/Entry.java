package startUp;

import controller.MasterController;

public class Entry {
	// Entry point of the program
		public static void main(String[] args) {
		//gets the instance of the singleton master controller and tells it to start the program
			MasterController.getController().startProgram();
		}
}
