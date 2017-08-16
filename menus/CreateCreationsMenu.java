package menus;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Controller.Controller;

public class CreateCreationsMenu {
	private JFrame frame;
	private Controller controller;
	private JButton returnBtn;

	public CreateCreationsMenu(Controller controller) {

		this.controller = controller;

	}

	public void setupCreateCreationsMenu() {

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

		JPanel returnPanel = new JPanel();
		// add intro text panel to frame
		frame.add(returnPanel);
		returnPanel.setLayout(new FlowLayout());
		
		returnBtn = new JButton("Return to main menu");
		returnBtn.setVerticalAlignment(SwingConstants.CENTER);
		
		returnPanel.add(returnBtn);
		returnBtn.addActionListener(controller);
		
		// packing GUI to fit the panels created
		frame.pack();
		
		// allowing the frame to be viable
		frame.setVisible(false);

	}
	
	public JButton getReturnButton() {
		return this.returnBtn;
	}
	
	public void setVisable(Boolean bool) {
		frame.setVisible(bool);
	}
}
