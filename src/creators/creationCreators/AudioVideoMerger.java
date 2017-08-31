package creators.creationCreators;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class AudioVideoMerger implements CommandLineRunnerListener {
	private String creationName;
	private AudioVideoMergerListener modelListeners;

	public AudioVideoMerger(String creationName) {
		this.creationName = creationName;
	}

	public void addListener(AudioVideoMergerListener listener) {
		this.modelListeners = listener;
	}

	public void mergeVideoAudio() {

		ArrayList<String> commands = new ArrayList<String>();

		commands.add("cd " + creationName);
		commands.add("ffmpeg -i videoOnly.mp4 -i audioOnly.wav -c:v copy"
				+ " -c:a aac -strict experimental -map 0:v:0 -map 1:a:0 \\"+ creationName + "\\.mp4");

		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.addListener(this);
		executor.execute();

	}

	@Override
	public void commandsHaveBeenExecuted() {
		fireModelUpdate();

	}

	private void fireModelUpdate() {
		modelListeners.creationHasBeenMerged();
	}
}
