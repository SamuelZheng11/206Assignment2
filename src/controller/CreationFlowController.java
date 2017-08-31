package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import creators.creationCreators.AudioMaker;
import creators.creationCreators.AudioMakerListener;
import creators.creationCreators.AudioVideoMerger;
import creators.creationCreators.AudioVideoMergerListener;
import creators.creationCreators.VideoMaker;
import creators.creationCreators.VideoMakerListener;
import creators.folderCreators.CreationSubFolderCreator;
import creators.folderCreators.SubFolderCreatorListener;
import menus.CreationModelListener;
import players.AudioPlayer;
import players.AudioPlayerListener;

/**
 * this class is concerned with the creation of a creation
 * 
 * creating a creation, and preventing blocking of the ED thread is complex,
 * thus its own controller class is needed to prevent access of files that have
 * not been created yet for example if the audio file (3 second recording) has
 * not yet finished recording yet, then the merging of audio and video will
 * fail.
 * 
 * the entire class is LIKE that of a template method but in class form
 *
 */
public class CreationFlowController implements SubFolderCreatorListener, AudioMakerListener, VideoMakerListener, AudioVideoMergerListener, AudioPlayerListener {

	// private variable used for storing the name of the creation that we want to
	// create
	private String creationName;
	// private list used to store all listeners for model Listener as when a creation is created all listeners
	// to the model must react
	private ArrayList<CreationModelListener> listeners = new ArrayList<CreationModelListener>();

	// constructor that will assign the name that is wanted to the field
	public CreationFlowController(String creationName) {
		this.creationName = creationName;
	}
	
	//method to add something as a listener as when a creation is created all listeners of the Creations model
	//must be notified
	public void addListener(CreationModelListener listener) {
		listeners.add(listener);
	}

	// the method that is called by a controller to create the creation
	public void makeCreation() {

		// creates the sub folder to store all files related to the creation
		CreationSubFolderCreator subFolderCreator = new CreationSubFolderCreator(creationName);
		subFolderCreator.addListener(this);
		subFolderCreator.makeCreationSubFolder();

	}

	// interface method that will execute at the end of the creation of the
	// "creationName" folder
	public void creationFolderHasBeenMade() {
		// makes the creation video
		VideoMaker videoMaker = new VideoMaker(creationName);
		videoMaker.addListener(this);
		videoMaker.makeVideo();
	}

	// interface method that will execute at the end of the creation of the
	// "creationName" video
	@Override
	public void videoHasBeenMade() {
		AudioMaker audioMaker = new AudioMaker(creationName);
		audioMaker.addListene(this);
		audioMaker.makeAudio();

	}

	// interface method that will execute at the end of the creation of the
	// "creationName" audio
	@Override
	public void AudioHadBeenMade() {
		
		AudioPlayer audioPlayer = new AudioPlayer(creationName);
		audioPlayer.addListener(this);
		audioPlayer.playAudio();

	}

	//method used to notify all listeners that a creation has been made and needs to be added to the model
	//all corresponding listeners are notified to change
	@Override
	public void creationHasBeenMerged() {
		for(CreationModelListener listener : listeners) {
			listener.modelHasChanged();
		}
	}

	//interface method used to notify the controller that the audio has been played
	@Override
	public void audioHasBeenPlayed() {
		int confirmation = JOptionPane.showConfirmDialog(null,
				"would you like to redo the recording [yes] or finish the creation [no]", null,
				JOptionPane.YES_NO_OPTION);

		// once the user has read the options and has selected chooses to redo the recording then
		if (confirmation == JOptionPane.YES_OPTION) {
			//re-record the recording by executing then interface method that triggers the audio to be created
			videoHasBeenMade();
		}
		//otherwise the user is happy with the creation and want to finish the creation
		else {
			
			//in that case merge the audio and video files
			AudioVideoMerger merger = new AudioVideoMerger(creationName);
			merger.addListener(this);
			merger.mergeVideoAudio();
		}
		
	}
}
