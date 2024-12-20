package _03_To_Do_List;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ToDoList {
	/*
	 * Create a program with five buttons, add task, view tasks, remove task, save list, and load list. 
	 *
	 * When add task is clicked:
	 * 		Create a JOptionPane to ask the user for a task and add it to an ArrayList
	 * 
	 * When the view tasks button is clicked:
	 *		show all the tasks in the list
	 * 
	 * When the remove task button is clicked:
	 * 		Create a JOptionPane to prompt the user for which task to remove and take it off of the list.
	 * 
	 * When the save list button is clicked:
	 * 		Save the list to a file
	 * 
	 * When the load list button is clicked:
	 * 		Create a JOptionPane to Prompt the user for the location of the file and load the list from that file
	 * 
	 * When the program starts, it should automatically load the last saved file into the list. 
	 */
	
	JFrame frame;
	JPanel panel;
	JButton addButton;
	JButton viewButton;
	JButton removeButton;
	JButton saveButton;
	JButton loadButton;
	
	ArrayList<String> tasks;
	
	public static void main(String[] args) {
		new ToDoList().run();
	}
	
	void run()
	{
		tasks = new ArrayList<String>();
		
		frame = new JFrame();
		panel = new JPanel();
		addButton = new JButton();
		viewButton = new JButton();
		removeButton = new JButton();
		saveButton = new JButton();
		loadButton = new JButton();
		
		panel.add(addButton);
		panel.add(viewButton);
		panel.add(removeButton);
		panel.add(saveButton);
		panel.add(loadButton);
		
		addButton.setText("Add Item");
		viewButton.setText("View List");
		removeButton.setText("Remove Item");
		saveButton.setText("Save List");
		loadButton.setText("Load List");
		
		addButton.addActionListener((arg) -> {
			String task = JOptionPane.showInputDialog("Add a new task:");
			if (task.charAt(0) >= 48 && task.charAt(0) <= 57)
			{
				JOptionPane.showMessageDialog(null, "Name cannot begin with a number");
				return;
			}
			for (String s : tasks)
			{
				if (task.equals(s))
				{
					JOptionPane.showMessageDialog(null, "A task already exists with the same name");
					return;
				}
			}
			
			tasks.add(task);
			JOptionPane.showMessageDialog(null, "Added: " + task);
		});
		
		viewButton.addActionListener((arg) -> {
			String tasksList = "";
			for (int i = 0; i < tasks.size(); i++)
			{
				tasksList += "\n" + i + " - " + tasks.get(i);
			}
			JOptionPane.showMessageDialog(null, "Tasks:" + tasksList);
		});
		
		removeButton.addActionListener((arg) -> {
			String task = JOptionPane.showInputDialog("Remove a task: Enter a name or number");
			if (task.charAt(0) >= 48 && task.charAt(0) <= 57)
			{
				int id = Integer.parseInt(task);
				if (id >= tasks.size() || id < 0)
				{
					JOptionPane.showMessageDialog(null, "No task exists with that id");
					return;
				}
				JOptionPane.showMessageDialog(null, "Removed: " + tasks.get(id));
				tasks.remove(id);
				return;
			}
			else
			{
				if (!tasks.remove(task))
				{
					JOptionPane.showMessageDialog(null, "No task exists with that name");
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Removed: " + task);
				}
			}
		});
		
		saveButton.addActionListener((arg) -> {
			save();
		});
		
		loadButton.addActionListener((arg) -> {
			JFileChooser jfc = new JFileChooser();
			File currentDir = getDirectoryOfCurrentClass();
	        if (currentDir != null) {
	            jfc.setCurrentDirectory(currentDir);
	        }
	        //jfc.setFileFilter(new FileNameExtensionFilter("txt"));
			
			int returnVal = jfc.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				load(jfc.getSelectedFile().getPath());
			}
		});
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
		
		openLastSave();
	}

	
	
	private void openLastSave() {
		// TODO Auto-generated method stub
		try {
			FileReader fr = new FileReader("src/_03_To_Do_List/memory.txt");
			int i = fr.read();
			String fileName = "";
			while (i != -1)
			{
				fileName += (char)i;
				i = fr.read();
			}
			load(fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
	
	private void saveLastOpened(String fileName)
	{
		try {
			FileWriter fw = new FileWriter("src/_03_To_Do_List/memory.txt");
			fw.write(fileName);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File getDirectoryOfCurrentClass() {
		 try {
	            // Get the location of the current class file (compiled class or JAR)
	            URL location = ToDoList.class.getProtectionDomain().getCodeSource().getLocation();
	            
	            // Convert the URL to a File object and return its directory
	            File binFile = new File(location.toURI());
	            File file = new File(binFile, "_03_To_Do_List");
	            if (file.isDirectory()) {
	                return file;
	            } else {
	                return file.getParentFile(); // For JAR files, return the directory containing the JAR
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null; // Return null if something goes wrong
	        }
	}
	
	private void save()
	{
		try {
			String name = JOptionPane.showInputDialog("Enter a name for this list");
			FileWriter fw = new FileWriter("src/_03_To_Do_List/" + name + ".txt");
			for (int i = 0; i < tasks.size(); i++)
			{
				fw.write(tasks.get(i) + "/");
			}
			fw.close();
			saveLastOpened("src/_03_To_Do_List/" + name + ".txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void load(String fileName)
	{
		
		System.out.println(fileName);
		
		try {
			FileReader fr = new FileReader(fileName);
			
			tasks.clear();
			int i = fr.read();
			String next = "";
			while (i != -1)
			{
				if (i == 47)
				{
					tasks.add(next);
					next = "";
				}
				else
				{
					next += (char)i;
				}
				i = fr.read();
			}
			
			saveLastOpened(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

//Copyright © 2024 FirstName LastName