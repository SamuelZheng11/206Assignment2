package creators.creationCreators;

import java.util.ArrayList;

import commandLineExecutor.CommandLineRunner;
import commandLineExecutor.CommandLineRunnerListener;

public class VideoMaker implements CommandLineRunnerListener{

	private String creationName;
	private VideoMakerListener listener;

	public VideoMaker(String creationName) {
		this.creationName = creationName;
	}
	
	public void addListener(VideoMakerListener listener) {
		this.listener = listener;
	}
	

	public void makeVideo() {
		
		
		ArrayList<String> commands = new ArrayList<String>();
		commands.add("cd " + creationName);
		commands.add("ffmpeg -f lavfi -i color=c=orange:s=320x240:d=3.0 -vf \\\n"
				+ "	\"drawtext=fontfile= \"$localHome\"/font/font.ttf:fontsize=30: \\\n"
				+ "	fontcolor=blue:x=(w-text_w)/2:y=(h-text_h)/2:text='" + creationName + "'\" videoOnly.mp4");

		CommandLineRunner executor = new CommandLineRunner(commands);
		executor.addListener(this);
		executor.execute();
	}


	@Override
	public void commandsHaveBeenExecuted() {
		listener.videoHasBeenMade();
		
	}

}
