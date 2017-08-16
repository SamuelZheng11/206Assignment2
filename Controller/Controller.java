package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import menus.CreateCreationsMenu;
import menus.MainMenu;
import menus.ViewCreationsMenu;

public class Controller implements ActionListener {

	private static final Controller controller = new Controller();
	private MainMenu mainMenu;
	private ViewCreationsMenu viewMenu;
	private CreateCreationsMenu createCreationMenu;

	private Controller() {
		this.mainMenu = new MainMenu(this);
		this.viewMenu = new ViewCreationsMenu(this);
		this.createCreationMenu = new CreateCreationsMenu(this);
	}
	
	public static Controller getController() {
		return controller;
	}

	public static void main(String[] args) {	
		controller.setup();
	}
	
	
	public void setup() {
		this.mainMenu.setupMenu();
		this.viewMenu.setupView();
		this.createCreationMenu.setupCreateCreationsMenu();
		this.mainMenu.setVisable(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == mainMenu.getProceedButton()) {
			if (mainMenu.getComboBoxItem() == "List/Play/Delete Creations") {
				mainMenu.setVisable(false);
				viewMenu.setVisable(true);
			} else if (mainMenu.getComboBoxItem() == "Make a Creation") {
				mainMenu.setVisable(false);
				createCreationMenu.setVisable(true);
			}
		}
		
		if (e.getSource() == viewMenu.getReturnButton()) {
			viewMenu.setVisable(false);
			mainMenu.setVisable(true);

		}
		
		if (e.getSource() == createCreationMenu.getReturnButton()) {
			createCreationMenu.setVisable(false);
			mainMenu.setVisable(true);

		}
	}
}
