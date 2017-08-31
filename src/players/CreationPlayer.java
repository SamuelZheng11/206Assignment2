package players;

import javax.swing.SwingWorker;

import createCreationsUtility.finders.WorkingDirectoryFinder;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class CreationPlayer extends SwingWorker<Void, Void> {
	
	/**
	 * this class is concerned with the loading of a video onto an embedded media player
	 */
	private EmbeddedMediaPlayer video;
	private String creationName;
	
	//constructor that loads stores all the video name and the place it has to be loaded to
	public CreationPlayer(EmbeddedMediaPlayer video ,String creationName) {
		
		this.video = video;
		this.creationName = creationName;
		
	}

	//Performs the loading of the video onto the place specified
	@Override
	protected Void doInBackground() throws Exception {
		
		//find the video
		WorkingDirectoryFinder finder = new WorkingDirectoryFinder();
		//loads the video onto the embedded video player
		video.playMedia( finder.getWorkingDirectory() + "/Creations/" + creationName +"/" + creationName + ".mp4");
		//tell the thread to wait while the video is playing
		Thread.sleep(3000);
		//stop the video so that the black screen comes back
		video.stop();
		return null;
	}
}
