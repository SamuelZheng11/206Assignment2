package createCreationsUtility.folderCreators;

import java.io.IOException;

public class CreationMainFolderCreator {

	public CreationMainFolderCreator() {

		createMainFolder();
	}

	private void createMainFolder() {

		ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", "mkdir Creations");

		try {
			builder.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
