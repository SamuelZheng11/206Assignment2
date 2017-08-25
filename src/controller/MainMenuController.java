package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import createCreationsUtility.finders.CreationsFinder;
import createCreationsUtility.folderCreators.CreationMainFolderCreator;
import deleteCreations.CreationDestoryer;
import menus.CreateCreationsMenu;
import menus.CreationModelListener;
import menus.MainMenu;
import menus.ViewCreationsMenu;
import playCreations.CreationPlayer;

public class MainMenuController implements ActionListener, CreationModelListener {

	private static final MainMenuController controller = new MainMenuController();
	private MainMenu mainMenu = new MainMenu(this);
	private CreateCreationsMenu createMenu = new CreateCreationsMenu(this);
	private ViewCreationsMenu viewMenu = new ViewCreationsMenu(this);
	private CreationsFinder finder;

	private MainMenuController() {
		new CreationMainFolderCreator();
		updateCreationsModel();
	}

	public static MainMenuController getController() {

		return controller;
	}

	public static void main(String[] args) {

		controller.start();
	}

	private void start() {

		mainMenu.setVisable(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mainMenu.getProceedButton()) {
			if (mainMenu.getComboBoxItem() == "List/Play/Delete Creations") {

				mainMenu.setVisable(false);
				viewMenu.setVisable(true);

			} else if (mainMenu.getComboBoxItem() == "Make a Creation") {

				mainMenu.setVisable(false);
				createMenu.setVisable(true);
			}
		} else if (e.getSource() == viewMenu.getReturnButton()) {

			viewMenu.setVisable(false);
			mainMenu.setVisable(true);

		} else if (e.getSource() == createMenu.getReturnButton()) {

			createMenu.setVisable(false);
			mainMenu.setVisable(true);

		} else if (e.getSource() == viewMenu.getDeleteButton()) {

			int confirmation = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete Creation \"" + viewMenu.getSelectedItem() + "\"", null,
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (confirmation == JOptionPane.YES_OPTION) {

				CreationDestoryer destoryer = new CreationDestoryer();
				destoryer.destoryCreation(viewMenu.getSelectedItem());
				modelHasChanged();

			}

		} else if (e.getSource() == viewMenu.getPlayButton()) {

			CreationPlayer player = new CreationPlayer(viewMenu.getMediaPlayer(), viewMenu.getSelectedItem());
			player.execute();

		} else if (e.getSource() == createMenu.getRecordButton()) {

			NameValidator validator = new NameValidator(createMenu.getCreationName());

			if (validator.isValidName()) {
				CreateCreationController creationController = new CreateCreationController(
						createMenu.getCreationName());
				changeRecordButtonPressable(false);
				creationController.makeCreation();
			} else {
				JOptionPane.showConfirmDialog(null,
						"Sorry, that is not a valid creaton name, please choose another", null,
						JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void updateCreationsModel() {
		finder = new CreationsFinder(viewMenu);
		finder.execute();
	}

	private void changeRecordButtonPressable(boolean bool) {
		createMenu.setRecordButtonEnable(bool);
	}

	@Override
	public void modelHasChanged() {

		updateCreationsModel();
		changeRecordButtonPressable(true);

	}

}
