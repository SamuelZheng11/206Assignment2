package deleteCreations;

import createCreationsUtility.finders.WorkingDirectoryFinder;

public class CreationDestoryer {

	public void destoryCreation(String creationName) {
		try {
			
			WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
			
			String cmd = "cd " + directoryFinder.getWorkingDirectory() + "/Creations/\"" + creationName + "\";"
					+ "rm -f *;" 
					+ "cd ..;"
					+ "rmdir " + creationName;
			
			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			builder.start();
			
		} catch (Exception ex) {
			System.out.println("Unexpected error occured when trying to delete creation");
		}
	}
}
