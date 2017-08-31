package creators.creationCreators;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class AudioMaker implements CommandLineRunnerListener{

	private String creationName;
	private AudioMakerListener listener;
	
	public AudioMaker(String creationName) {
		this.creationName = creationName;
	}
	
	public void addListene(AudioMakerListener listener) {
		this.listener = listener;
	}

	public void makeAudio() {
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("cd " + creationName);
		commands.add("rm -f audioOnly.wav");
		commands.add("ffmpeg -nostats -loglevel panic -f pulse -i default -t 3 audioOnly.wav");
		
		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.addListener(this);
		executor.setWaitTime(3200);
		executor.execute();
	}

	@Override
	public void commandsHaveBeenExecuted() {
		
		listener.AudioHadBeenMade();
	}
	
}
