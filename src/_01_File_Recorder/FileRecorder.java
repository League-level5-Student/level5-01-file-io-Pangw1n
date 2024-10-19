package _01_File_Recorder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileRecorder {
	// Create a program that takes a message from the user and saves it to a file.
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		try {
			FileWriter fw = new FileWriter("src/_01_File_Recorder/storage.txt");
			new FileRecorder().inputText(fw, scanner);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void inputText(FileWriter fw, Scanner scanner)
	{
		System.out.println("Input text: (type 'QUIT' to exit)");
		String input = scanner.nextLine();
		
		if (input.trim().equalsIgnoreCase("QUIT"))
		{
			try {
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		else
		{
			try {
				fw.write(input + "\n");
				inputText(fw, scanner);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
