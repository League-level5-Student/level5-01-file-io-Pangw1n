package _04_Directory_Iteration;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class DirectoryIterator {
	
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

	public static void main(String[] args) {
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
			
			System.out.println(current.getName());
			if (getFileExtension(current).equalsIgnoreCase("java"))
			{
				//check if .java and add //Copyright © 2019 FirstName LastName
				System.out.println("java file: " + current.getName());
				try {
					FileWriter fw = new FileWriter(current.getPath(), true);
					fw.write("\n");
					fw.write("//Copyright © 2024 FirstName LastName");
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else
			{
				if (current.isDirectory())
				{
					File[] recursiveFiles = current.listFiles();
					for (File k : recursiveFiles)
					{
						System.out.println(" - " + k.getName());
					}
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
	
	 
	
	public static String getFileExtension(File file) {
	    String fileName = file.getName();
	    int dotIndex = fileName.lastIndexOf('.');
	    return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
	}
}

//Copyright © 2024 FirstName LastName