package _02_File_Encrypt_Decrypt;

import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JOptionPane;

public class FileEncryptor {
	/*
	 * Encryption is the process of encoding a message or information
	 * in such a way that only authorized parties can access it and
	 * those who are not authorized cannot.
	 *
	 * A simple shift cipher works by shifting the letters of a message
	 * down based on a key. If our key is 4 then:
	 * 
	 * a b c d e f g h i j k l m n o p q r s t u v w x y z
	 * 
	 * becomes:
	 *
	 * e f g h i j k l m n o p q r s t u v w x y z a b c d
	 * 
	 * "Hello World" changes to "Lipps Asvph"
	 *
	 * Create a program that takes a message and a key from the user.
	 * Use the key to shift each letter in the users input and save the final result to a file.
	 */
	
	public static void main(String[] args) {
		new FileEncryptor().inputText();
	}
	
	void inputText()
	{
		String input = JOptionPane.showInputDialog("Input text to encode");
		int key = Integer.parseInt(JOptionPane.showInputDialog("Input a key"));
		char index = input.charAt(0);
		
		String encodedString = encode(input, key);
		
		try {
			FileWriter fw = new FileWriter("src/_02_File_Encrypt_Decrypt/storage.txt");
			fw.write(encodedString);
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	String encode(String input, int key)
	{
		String encodedText = "";
		for (int i = 0; i < input.length(); i++)
		{
			char index = input.charAt(i);
			char newLetter = index;
			if (index >= 65 && index <= 90)
			{
				newLetter += key;
				newLetter = (char) (((newLetter - 65) % 26) + 65);
			}
			else if (index >= 97 && index <= 122)
			{
				newLetter += key;
				newLetter = (char) (((newLetter - 97) % 26) + 97);
			}
			encodedText += newLetter;
		}
		System.out.println(encodedText);
		
		return encodedText;
	}
}