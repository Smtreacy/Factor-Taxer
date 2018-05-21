//Steven Treacy
//NumberRunner.java
//5/9/18
//Compile and display the game

import java.util.Scanner;

import javax.swing.*;

public class NumberRunner
{

	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
	
		System.out.println("Please enter 1st username");
		String name = scan.next();
		
		
		System.out.println("Please enter 2nd username");
		String secondName = scan.next();
		

		NumFrame NFrame = new NumFrame(name,secondName);
		NFrame.createNumButtons();
		NFrame.createControlPanel();
		JPanel Button_test = NFrame.buttonPanel;
		
		NFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		NFrame.setTitle("Factor Taxer");
		NFrame.setVisible(true);
		NFrame.getContentPane().add(Button_test);
	}

}
