package createCreationsUtility.finders;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WorkingDirectoryFinder {

	
	public String getWorkingDirectory() {
		try {
			String cmd = "pwd";
			ProcessBuilder builder = new ProcessBuilder("/bin/bash", "-c", cmd);

			Process process = builder.start();

			InputStream out = process.getInputStream();
			BufferedReader stdout = new BufferedReader(new InputStreamReader(out));

			String line = null;
			while ((line = stdout.readLine()) != null) {
				return line;
			}
		} catch (Exception ex) {
			System.out.println("Unexpected error occured when trying to locate working directory");
		}
		return null;
	}
	
}
