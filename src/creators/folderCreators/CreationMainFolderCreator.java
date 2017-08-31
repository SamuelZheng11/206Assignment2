package creators.folderCreators;

import java.io.IOException;

import menus.CreationModelListener;

public class CreationMainFolderCreator implements CreationModelListener {
	
	private CreationModelListener listener;

	public CreationMainFolderCreator(CreationModelListener caller) {
		this.listener = caller;
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

	@Override
	public void modelHasChanged() {
		
		listener.modelHasChanged();
		
	}
}
