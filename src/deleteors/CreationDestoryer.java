package deleteors;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class CreationDestoryer implements CommandLineRunnerListener {

	private ArrayList<CreationDestoryerListener> listeners = new ArrayList<CreationDestoryerListener>();

	public void addListener(CreationDestoryerListener listener) {
		this.listeners.add(listener);
	}

	public void destoryCreation(String creationName) {

		ArrayList<String> commands = new ArrayList<String>();
		commands.add("cd " + creationName);
		commands.add("rm -f *");
		commands.add("cd ..");
		commands.add("rmdir " + creationName);

		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.addListener(this);
		executor.execute();

	}

	
	@Override
	public void commandsHaveBeenExecuted() {
		
		fireModelUpdate();
		
	}
	
	private void fireModelUpdate() {
		for(CreationDestoryerListener listener : listeners) {
			listener.creationHasBeenDestoryed();
		}
	}
	
	
}
