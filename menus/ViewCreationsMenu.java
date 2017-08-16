package menus;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Controller.Controller;

public class ViewCreationsMenu {

	private JFrame frame;
	private Controller controller;
	private JButton returnBtn;

	public ViewCreationsMenu(Controller controller) {

		this.controller = controller;

	}

	public void setupView() {

		// create the frame
		frame = new JFrame("View Creations");

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
		JLabel viewLabel = new JLabel("Please select a creation to preform actions on ");
		// specify position for intro text to be
		viewLabel.setVerticalAlignment(SwingConstants.TOP);
		TextPanel.add(viewLabel, BorderLayout.CENTER);
		viewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel showCreationsPanel = new JPanel();
		// add intro text panel to frame
		frame.add(showCreationsPanel);
		showCreationsPanel.setLayout(new BorderLayout(0, 0));

		JList list = new JList();
		list.setVisibleRowCount(-1);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setPreferredSize(new Dimension(0, 0));
		
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Jane Doe");
		listModel.addElement("John Smith");
		listModel.addElement("Kathy Green");


		list = new JList(listModel);

		showCreationsPanel.add(list);
		
		JPanel buttonPanel = new JPanel();
		// add intro text panel to frame
		frame.add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout());
		
		JButton play = new JButton("Play Creation");
		play.setVerticalAlignment(SwingConstants.CENTER);
		
		JButton delete = new JButton("Delete Creation");
		delete.setVerticalAlignment(SwingConstants.CENTER);
		
		buttonPanel.add(play);
		buttonPanel.add(delete);
		
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
