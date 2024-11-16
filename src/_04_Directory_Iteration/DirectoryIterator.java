package _04_Directory_Iteration;

import java.io.File;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		/* 
		 * The following is an example of how to list all of the files in a directory.
		 * Once the program is running, the directory is chosen using the JFileChooser.
		 */
		
		/*
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			File[] files = directory.listFiles();
			if(files != null) {
				for(File f : files) {
				  System.out.println(f.getAbsolutePath());
				}
			}
		}
		*/
		
		/*
		 * Your task is to write a program that iterates through the src folder of this current Java Project. 
		 * For every .java file it finds, the program will add a (non-legally binding) copyright statement at the bottom.
		 * Be aware of possible directories inside of directories.
		 * (e.g //Copyright © 2019 FirstName LastName)
		 */
		new DirectoryIterator().run();
	}
	
	void run()
	{
		File[] files = new File("src").listFiles();
		loopFiles(files);
	}
	
	public void loopFiles(File[] files) {
		for (int i = 0; i < files.length; i++)
		{
			File current = files[i];
			String[] fileName = current.getName().split(".");
			
			System.out.println(current.getName());
			if (false)
			{
				//check if .java and add //Copyright © 2019 FirstName LastName
			}
			else {
				File[] recursiveFiles = current.listFiles();
				for (File k : recursiveFiles)
				{
					System.out.println(" - " + k.getName());
				}
				if (recursiveFiles != null)
				{
					loopFiles(recursiveFiles);
				}
			}
		}
		
	}

	File[] getFiles(File directory)
	{
		File[] files = directory.listFiles();
		if(files != null) {
			for(File f : files) {
			  System.out.println(f.getAbsolutePath());
			}
		}
		
		return files;
	}
}
