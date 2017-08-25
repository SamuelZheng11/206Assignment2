package controller;

import createCreationsUtility.folderCreators.CreationSubFolderCreator;
import createCreationsUtility.folderCreators.SubFolderCreatorListener;
import creationCreators.AudioMaker;
import creationCreators.AudioMakerListener;
import creationCreators.AudioVideoMerger;
import creationCreators.VideoMaker;
import creationCreators.VideoMakerListener;

public class CreateCreationController implements SubFolderCreatorListener, AudioMakerListener, VideoMakerListener{
	private String creationName;
	
	public CreateCreationController(String creationName) {
		this.creationName = creationName;
	}
	
	public void makeCreation() {
		
		CreationSubFolderCreator subFolderCreator = new CreationSubFolderCreator(creationName);
		subFolderCreator.addListener(this);
		subFolderCreator.makeCreationSubFolder();
		
		
	}
	
	public void creationFolderHasBeenMade() {
		VideoMaker videoMaker = new VideoMaker(creationName);
		videoMaker.addListener(this);
		videoMaker.makeVideo();
	}

	@Override
	public void videoHasBeenMade() {
		AudioMaker audioMaker = new AudioMaker(creationName);
		audioMaker.addListene(this);
		audioMaker.makeAudio();
		
	}
	
	@Override
	public void AudioHadBeenMade() {
		AudioVideoMerger merger = new AudioVideoMerger(creationName);
		merger.addListener(MainMenuController.getController());
		merger.mergeVideoAudio();
		
	}
}
