package createCreationsUtility.finders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;

import menus.ViewCreationsMenu;

public class CreationsFinder extends SwingWorker<DefaultListModel<String>, Void> {
	
	private DefaultListModel<String> listOfCreations = new DefaultListModel<String>();
	private ViewCreationsMenu viewMenu;
	
	public CreationsFinder(ViewCreationsMenu viewMenu) {
		this.viewMenu = viewMenu;
	}

	@Override
	protected DefaultListModel<String> doInBackground() throws Exception {
		
		WorkingDirectoryFinder directoryFinder = new WorkingDirectoryFinder();
		
		try {
			String cmd = "ls "+ directoryFinder.getWorkingDirectory() +"/Creations";
			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);
			
			Process process = builder.start();

			InputStream out = process.getInputStream();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(out));

			String line = null;
			while ((line = stdout.readLine()) != null) {
				listOfCreations.addElement(line);
			}
			return listOfCreations;
			
		} catch (Exception ex) {
			ex.getStackTrace();
			System.out.println("Unexpected error occured when trying to find creations");
		}
		return listOfCreations;
		
	}
	
	@Override
	protected void done() {
		try {
			viewMenu.setJListModel(get());;
		} catch (InterruptedException e) {
			System.out.println("Unexpected error occured when trying to load creations to view");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("Unexpected error occured when trying to load creations to view");
			e.printStackTrace();
		}
	}
}
