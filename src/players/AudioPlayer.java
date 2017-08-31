package players;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class AudioPlayer implements CommandLineRunnerListener {
	
	private String creationName;
	private AudioPlayerListener listener;
	
	public AudioPlayer(String creationName) {
		this.creationName = creationName;
	}
	
	public void addListener(AudioPlayerListener listener) {
		this.listener = listener;
	}
	
	public void playAudio() {
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("cd " + creationName);
		commands.add("ffplay -t 3 -autoexit audioOnly.wav");
		
		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.setWaitTime(3000);
		executor.addListener(this);
		executor.execute();
	}

	@Override
	public void commandsHaveBeenExecuted() {
		
		listener.audioHasBeenPlayed();
		
	}
}
