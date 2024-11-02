package _03_To_Do_List;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		});
		viewButton.addActionListener((arg) -> {
			String tasksList = "";
			for (int i = 0; i < tasks.size(); i++)
			{
				tasksList += "\n" + tasks.get(i);
			}
			JOptionPane.showMessageDialog(null, "Tasks:" + tasksList);
		});
		removeButton.addActionListener((arg) -> {
			String task = JOptionPane.showInputDialog("Remove a task: Enter a name or number");
			if (task.charAt(0) >= 48 && task.charAt(0) <= 57)
			{
				int id = Integer.parseInt(task);
				if (id < tasks.size())
				{
					return;
				}
				tasks.remove(Integer.parseInt(task));
				return;
			}
			else
			{
				if (!tasks.remove(task))
				{
					
				}
			}
		});
		saveButton.addActionListener((arg) -> {});
		loadButton.addActionListener((arg) -> {});
		
		frame.add(panel);
		frame.setVisible(true);
		frame.pack();
	}
}
