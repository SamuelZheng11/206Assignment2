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
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import controller.MainMenuController;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import java.awt.Component;
import javax.swing.Box;


public class ViewCreationsMenu {

	private MainMenuController viewController;
	private JFrame frame;
	private JButton returnBtn;
	private JButton delete;
	private JButton play;
	public EmbeddedMediaPlayer video;
	private EmbeddedMediaPlayerComponent mediaPlayerComponent;
	private JList<String> creationList;
	private Component verticalStrut;

	public ViewCreationsMenu(MainMenuController mainMenuController) {

		this.viewController = mainMenuController;
		setupView();
		
	}

	private void setupView() {

		// create the frame
		frame = new JFrame("View Creations");

		// set up frame to close upon exit of eclipse
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));

		JPanel TextPanel = new JPanel();
		// add intro text panel to frame
		frame.getContentPane().add(TextPanel);
		TextPanel.setLayout(new BorderLayout(0, 0));

		// create a label used for intro text
		JLabel viewLabel = new JLabel("Please select a creation to preform actions on ");
		TextPanel.add(viewLabel);
		viewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel showCreationsPanel = new JPanel();
		showCreationsPanel.setPreferredSize(new Dimension(500,210));
		frame.getContentPane().add(showCreationsPanel);
		showCreationsPanel.setLayout(new BorderLayout(0, 0));

		creationList = new JList<String>();
		creationList.setVisibleRowCount(-1);
		
		JScrollPane scrollPane = new JScrollPane(creationList);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		showCreationsPanel.add(scrollPane, BorderLayout.WEST);
		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
		showCreationsPanel.add(mediaPlayerComponent, BorderLayout.CENTER);
		
		verticalStrut = Box.createVerticalStrut(20);
		showCreationsPanel.add(verticalStrut, BorderLayout.NORTH);

		video = mediaPlayerComponent.getMediaPlayer();
		
		JPanel buttonPanel = new JPanel();
		frame.getContentPane().add(buttonPanel);
		buttonPanel.setLayout(new FlowLayout());
		
		play = new JButton("Play Creation");
		play.setVerticalAlignment(SwingConstants.CENTER);
		play.addActionListener(viewController);
		
		delete = new JButton("Delete Creation");
		delete.setVerticalAlignment(SwingConstants.CENTER);
		delete.addActionListener(viewController);
		
		buttonPanel.add(play);
		
		buttonPanel.add(delete);
		
		returnBtn = new JButton("Return to main menu");
		buttonPanel.add(returnBtn);
		returnBtn.setVerticalAlignment(SwingConstants.CENTER);
		returnBtn.addActionListener(viewController);
		
		// packing GUI to fit the panels created
		frame.pack();

		// allowing the frame to be viable
		frame.setVisible(false);

	}
	
	public JButton getReturnButton() {
		return this.returnBtn;
	}
	
	public JButton getPlayButton() {
		return this.play;
	}
	
	public JButton getDeleteButton() {
		return this.delete;
	}
	
	public String getSelectedItem() {
		return creationList.getSelectedValue().toString();
	}
	
	public EmbeddedMediaPlayer getMediaPlayer() {
		return video;
	}
	
	public void setVisable(Boolean bool) {
		frame.setVisible(bool);
	}
	
	public void setJListModel(DefaultListModel<String> creationsFound) {
		this.creationList.setModel(creationsFound);
	}

}
