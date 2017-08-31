package creators.folderCreators;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class CreationSubFolderCreator implements CommandLineRunnerListener {

	private String creationName;
	private SubFolderCreatorListener listener;

	public CreationSubFolderCreator(String creationName) {
		this.creationName = creationName;
	}

	public void addListener(SubFolderCreatorListener listener) {
		this.listener = listener;
	}

	public void makeCreationSubFolder() {

		ArrayList<String> commands = new ArrayList<String>();
		commands.add("mkdir " + creationName);

		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.addListener(this);
		executor.execute();
		return;

	}

	@Override
	public void commandsHaveBeenExecuted() {
		listener.creationFolderHasBeenMade();

	}

}
