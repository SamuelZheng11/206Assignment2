package playCreations;

import javax.swing.SwingWorker;

import createCreationsUtility.finders.WorkingDirectoryFinder;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;

public class CreationPlayer extends SwingWorker<Void, Void> {
	
	private EmbeddedMediaPlayer video;
	private String creationName;
	
	public CreationPlayer(EmbeddedMediaPlayer video ,String creationName) {
		
		this.video = video;
		this.creationName = creationName;
		
	}


	@Override
	protected Void doInBackground() throws Exception {
		
		WorkingDirectoryFinder finder = new WorkingDirectoryFinder();
		video.playMedia( finder.getWorkingDirectory() + "/Creations/" + creationName +"/" + creationName + ".mp4");

		return null;
	}
}
