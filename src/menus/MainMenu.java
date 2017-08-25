package menus;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.MainMenuController;

public class MainMenu implements ActionListener {

	private JFrame frame;
	private JComboBox<String> userSelection = new JComboBox<String>();
	private JButton confirmSelection = new JButton();
	private MainMenuController controller;

	public MainMenu(MainMenuController controller) {

		this.controller = controller;
		setupMenu();

	}

	private void setupMenu() {

		// create the frame
		frame = new JFrame("Maths_Aid");

		// set up frame to close upon exit of eclipse
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// define the type of layout for the frame
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		// add a JPanel for the intro text
		JPanel introTextPanel = new JPanel();
		// add intro text panel to frame
		frame.getContentPane().add(introTextPanel);
		// set layout for intro text
		introTextPanel.setLayout(new BorderLayout(0, 0));

		// create a label used for intro text
		JLabel introLabel = new JLabel("Hello and welcome to maths audio/visual aid");
		// specify position for intro text to be
		introLabel.setVerticalAlignment(SwingConstants.TOP);
		introTextPanel.add(introLabel, BorderLayout.NORTH);
		introLabel.setHorizontalAlignment(SwingConstants.CENTER);

		// create a label used for instruction text
		JLabel instruction = new JLabel("Please select one of the options from the drop down box then press proceed");
		introTextPanel.add(instruction);
		instruction.setHorizontalAlignment(SwingConstants.CENTER);

		// create panel for drop down bar panel
		JPanel dropDownBoxPanel = new JPanel();
		frame.getContentPane().add(dropDownBoxPanel);

		dropDownBoxPanel.add(userSelection);
		// add selection options for combo box
		userSelection
				.setModel(new DefaultComboBoxModel<String>(new String[] { "List/Play/Delete Creations", "Make a Creation" }));
		userSelection.setToolTipText("Please choose one of the option avalible");

		// creating panel to put the proceed button on
		JPanel confirmPanel = new JPanel();
		frame.getContentPane().add(confirmPanel);

		// add button for confirming selection to the frame
		confirmSelection = new JButton("Proceed");
		confirmSelection.setVerticalAlignment(SwingConstants.TOP);
		confirmPanel.add(confirmSelection);

		// add the controller as a listener to the proceed button
		confirmSelection.addActionListener(controller);

		// adding the frame as a listener
		confirmSelection.addActionListener(this);

		// packing GUI to fit the panels created
		frame.pack();

		// allowing the frame to be viable
		frame.setVisible(false);
	}

	public String getComboBoxItem() {
		return (String) userSelection.getSelectedItem().toString();
	}

	public JButton getProceedButton() {
		return this.confirmSelection;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.confirmSelection) {
			frame.setVisible(false);
		}

	}

	public void setVisable(Boolean bool) {
		frame.setVisible(bool);
	}

}
