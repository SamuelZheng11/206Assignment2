package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import creators.creationCreators.CreationModelCreator;
import creators.folderCreators.CreationMainFolderCreator;
import deleteors.CreationDestoryer;
import deleteors.CreationDestoryerListener;
import menus.CreateCreationsMenu;
import menus.CreationModelListener;
import menus.MainMenu;
import menus.ViewCreationsMenu;
import players.CreationPlayer;

/**
 * this class is design to be the main controller that controls all the decision
 * making that happens between the three menus that are implemented
 */
public class MasterController implements ActionListener, CreationModelListener, CreationDestoryerListener {

	// initializing a singleton controller as there should only be one 'master'
	// controller
	private static final MasterController controller = new MasterController();

	// creating a main menu view passing in this controller as the controller to the
	// menu
	private MainMenu mainMenu = new MainMenu(this);

	// creating a create creations menu view passing in this controller as the
	// controller to the menu
	private CreateCreationsMenu creationMenu;

	// creating a view/play/delete menu view passing in this controller as the
	// controller to the menu
	private ViewCreationsMenu viewMenu = new ViewCreationsMenu(this);

	// private constructor as a singleton pattern
	private MasterController() {
		new CreationMainFolderCreator(this);
	}

	// providing a way to obtain the main menu singleton
	public static MasterController getController() {

		return controller;
	}

	// method that allows the main menu to become viable allowing user interaction
	// with the program
	public void startProgram() {
		updateCreationsModel();
		mainMenu.makeVisable();

	}

	// all buttons that are clicked are relayed to the MainController that decided
	// what to do
	// the following are actions that must be performed when a button has been
	// pressed
	// the use of concatenated else if's is done for efficiency as if one evaluates
	// to true the
	// other else if's are not checked
	@Override
	public void actionPerformed(ActionEvent e) {

		// if the button pressed was the proceed on the main menu screen, after reading
		// the introduction
		// statement it will go into the core of the program
		if (e.getSource() == mainMenu.getProceedButton()) {

			viewMenu.setVisable(true);
			mainMenu.destoryFrame();

		}

		// if the button pressed on the view menu then is called "create new creation"
		// then set then a new window will be made for the task of creating a new creation
		else if (e.getSource() == viewMenu.getCreateNewCreationButton()) {

			creationMenu = new CreateCreationsMenu(this);
			creationMenu.setVisable(true);
			
		}
		
		//if the button pressed was the return to view menu on the creation screen, then destory the
		//creation
		else if (e.getSource() == creationMenu.getReturnButton()) {
			creationMenu.destory();
		}

		// if the button pressed on the view menu was the "delete button" then perform
		// the delete protocol
		else if (e.getSource() == viewMenu.getDeleteButton()) {

			// first create a JPane with the options yes, no, cancel, warning the user of
			// permernate deletion
			int confirmation = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete Creation \"" + viewMenu.getSelectedItem() + "\"", null,
					JOptionPane.YES_NO_OPTION);

			// once the user has read the options and has selected yes then
			if (confirmation == JOptionPane.YES_OPTION) {

				// create an instance of the destroyer class and
				CreationDestoryer destoryer = new CreationDestoryer();
				// add the master controller (this class) to listen to when the creation has
				// been destroyed
				destoryer.addListener(this);
				// tell the destroyer to destroy the creation that was selected in the JList
				// details as to why I made the destroyer its own class will be explained in the
				// "CreationDestoryer" class
				destoryer.destoryCreation(viewMenu.getSelectedItem());

			}
			// if the user selects anything but yes then do nothing
			else {

			}
		}

		// if the button pressed was the play button on the view menu then perform the
		// play protocol
		else if (e.getSource() == viewMenu.getPlayButton()) {

			// create an instance of the creation player, and notify it where we want a
			// video the be loaded
			// onto
			CreationPlayer player = new CreationPlayer(viewMenu.getMediaPlayer(), viewMenu.getSelectedItem());
			// the creation loader has been made to extend swing worker becuase loading the
			// player may block
			// the ED thread
			// details as to why I made the player its own class will be explained in the
			// "CreationPlayer" class
			player.execute();
		}

		// if the button pressed on the create creations menu was the record button then
		// perform
		// the create creation protocol
		else if (e.getSource() == creationMenu.getRecordButton()) {

			// first create a validator and check weather the name entered in the text box
			// on the create
			// creations menu was valid according to the assignment specifications
			// the reason why i made the validator its own class instead of an internal
			// method will be
			// explained in the class description for "NameValidator"
			NameValidator validator = new NameValidator(creationMenu.getCreationName());

			// if the validator return that the name entered is valid then
			if (validator.isValidName()) {

				// create an instance of the creation creator controller, this will make the
				// creating of the creation
				// controlled as some parts may take longer to make
				CreateFlowController creationController = new CreateFlowController(creationMenu.getCreationName());

				// adding the main menu controller as a listener to the creation flow controller
				// to be notifed when the creation has been made
				creationController.addListener(this);

				// the master controller will make the record button un-usable as we should not
				// be able to
				// create anything while something is being created
				changeRecordButtonPressable(false);

				// tell the make creation controller to start creating the creation
				creationController.makeCreation();

			}

			// in any case where the validator says that the name is not valid then
			// report a message saying that the creation does not have a valid name and
			// cannot be created
			else {
				JOptionPane.showConfirmDialog(null, "Sorry, that is not a valid creaton name, please choose another",
						null, JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	// a method used for loading a model onto the view via the modelUpdater
	private void updateCreationsModel() {

		// creating an instance of a class that deals with model creating new models and
		// loading it as
		// the new model
		CreationModelCreator modelMaker = new CreationModelCreator();

		// creates a model and loads it into the JList in the view menu
		viewMenu.setJListModel(modelMaker.makeModel());

	}

	// change the usability of the record button on the create creation menu
	private void changeRecordButtonPressable(boolean bool) {
		creationMenu.setRecordButtonEnable(bool);
	}

	// An interface method for updating the view due to a change in the model
	// through deletion of a creation
	public void creationHasBeenDestoryed() {
		updateCreationsModel();
	}

	// method used to remove the name typped into the creation name Jtext field when
	// creating creation
	public void clearCreationnameField() {
		// calls for the creation view menu to clear the text field to an empty string
		creationMenu.clearCreationNameField();
	}

	// An interface method for updating the view due to a change in the model
	// through adding of a creation
	@Override
	public void modelHasChanged() {

		// update the model that is being viewed
		updateCreationsModel();
		// clear the field that the user entered the name for the creation in
		clearCreationnameField();
		// change the usability of the record button as the only time that this
		// interface method should be called
		// is through the adding of a creation which must go through the create creation
		// protocol
		changeRecordButtonPressable(true);

	}

}
