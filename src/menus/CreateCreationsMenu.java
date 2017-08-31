package menus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.MasterController;

public class CreateCreationsMenu {
	private JFrame frame;
	private MasterController menuController;
	private JButton returnButton;
	private JButton recordButton;
	private JTextField creationName;

	public CreateCreationsMenu(MasterController controller) {

		this.menuController = controller;
		setupCreateCreationsMenu();

	}

	private void setupCreateCreationsMenu() {

		// create the frame
		frame = new JFrame("Create Creations");

		// set up frame to close upon exit of eclipse
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// define the type of layout for the frame
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel TextPanel = new JPanel();
		// add intro text panel to frame
		frame.getContentPane().add(TextPanel);
		// set layout for intro text
		TextPanel.setLayout(new BorderLayout(0, 0));

		// create a label used for intro text
		JLabel viewLabel = new JLabel("Please use the options on this screen to create your creations ");
		// specify position for intro text to be
		viewLabel.setVerticalAlignment(SwingConstants.TOP);
		TextPanel.add(viewLabel, BorderLayout.CENTER);
		viewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel creationNamingPanel = new JPanel();
		frame.getContentPane().add(creationNamingPanel);
		creationNamingPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel creationNameInstructions = new JLabel("What would you like to name your creation");
		creationNameInstructions.setHorizontalAlignment(SwingConstants.CENTER);
		creationNameInstructions.setVerticalAlignment(SwingConstants.CENTER);
		creationNamingPanel.add(creationNameInstructions);
		
		creationName = new JTextField();
		creationNamingPanel.add(creationName);
		creationName.setColumns(10);

		JPanel dirBtnPanel = new JPanel();
		frame.getContentPane().add(dirBtnPanel);
		dirBtnPanel.setLayout(new FlowLayout());

		recordButton = new JButton("Record Audio");
		recordButton.setVerticalAlignment(SwingConstants.TOP);

		recordButton.addActionListener(menuController);
		dirBtnPanel.add(recordButton);

		JPanel returnPanel = new JPanel();
		frame.getContentPane().add(returnPanel);
		returnPanel.setLayout(new FlowLayout());

		returnButton = new JButton("Return to view menu");
		returnButton.setVerticalAlignment(SwingConstants.CENTER);

		returnPanel.add(returnButton);
		returnButton.addActionListener(menuController);

		// packing GUI to fit the panels created
		frame.pack();

		// allowing the frame to be viable
		frame.setVisible(false);

	}

	public JButton getReturnButton() {
		return this.returnButton;
	}
	
	public JButton getRecordButton() {
		return this.recordButton;
	}
	
	public String getCreationName() {
		return creationName.getText();
	}

	public void setVisable(Boolean bool) {
		frame.setVisible(bool);
	}
	
	public void setRecordButtonEnable(boolean bool) {
		recordButton.setEnabled(bool);
	}
	
	public void clearCreationNameField() {
		creationName.setText("");
	}
	
	public void destory() {
		frame.dispose();
	}
}
