//Steven Treacy
//NumFrame.java
//5/9/18
//Control the buttons and frame of the game as well as give out points and check if numbers have been taken

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.TitledBorder;

@SuppressWarnings("serial")
public class NumFrame extends JFrame
{
	//frame dimensions 
	private static final int FRAME_WIDTH = 900;
	private static final int FRAME_HEIGHT = 700;
	
	//Instance Variables
	JPanel buttonPanel = new JPanel();
	private JLabel pOne;
	private JLabel pTwo;
	private JLabel winnerLabel;
	private JButton [] pointButtons = new JButton [100];
	
	//new player objects
	Player p1 = new Player();
	Player p2 = new Player();
	
	public int [] choosingNums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
			  					 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
			  					 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
			  					 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
			  					 41, 42, 43, 44, 45, 46, 47, 48, 49, 50,
			  					 51, 52, 53, 54, 55, 56, 57, 58, 59, 60,
			  					 61, 62, 63, 64, 65, 66, 67, 68, 69, 70,
			  					 71, 72, 73, 74, 75, 76, 77, 78, 79, 80,
			  					 81, 82, 83, 84, 85, 86, 87, 88, 89, 90,
			  					 91, 92, 93, 94, 95, 96, 97, 98, 99, 100};
	public int [] usedNums;
	public Boolean isUsed;
	public int buttonAmnt;
	public int turnNum = 0;
	public ActionListener buttonCheck;
	
	
	
	//Place frame elements, except buttons, as to keep score
	public NumFrame(String name, String secondName)
	{
		p1.setName(name);
		p2.setName(secondName);
		//convert to string otherwise the label doesnt update
		pOne = new JLabel(p1.getName()+ " " + String.valueOf(p1.points));
		add(pOne, BorderLayout.WEST);
		
		pTwo = new JLabel(p2.getName()+ " " + String.valueOf(p2.points));
		add(pTwo, BorderLayout.EAST);
		
		winnerLabel = new JLabel("");
		add(winnerLabel, BorderLayout.SOUTH);

		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	
	//Create and organize buttons
	public JPanel createNumButtons()
	{
		
		ButtonGroup nums = new ButtonGroup();
		JPanel panel = new JPanel(new GridLayout (10, 10));
		
		//Create an array of JButtons to make disabling "factor" numbers easier
		JButton[] pointButtons = new JButton[choosingNums.length + 1]; 

		for (int i = 1 ; i < choosingNums.length + 1; i++)
		{
			String val = Integer.toString(i);
			pointButtons[i] = new JButton (val);
			pointButtons[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent event) {
				   turnNum ++ ;
				   addPoint(Integer.parseInt(val), pointButtons);
				   int k = Integer.parseInt(val);
				   int sum = 0;
				   for (int i = 1; i <= k; i++)
					{
						if (k%i == 0) {
							  sum = sum + i;
							deactivateFactor(pointButtons, i);
							CheckForwinner(pointButtons);
						}
					}	
				  }
			});		
			nums.add(pointButtons[i]);
			panel.add(pointButtons[i]);
		}	
		panel.setBorder(new TitledBorder("Choose a number"));
		return panel;
	}
	
	
	//Method to group the JButtons together
	public void createControlPanel()
	{
		JPanel numGroupPanel = createNumButtons();
		
		JPanel controlPanel = new JPanel();
	    controlPanel.add(numGroupPanel);
	    add(controlPanel, BorderLayout.NORTH);
	}
	
	//Function to find the factor of the number button pressed.
	//this number is eventually sent to the deactivate factor method
	int findFactor(int val) {
		int sum = 0;
		for (int i = 1; i <= val; i++)
		{
			if (val%i == 0) {
				 sum = sum + i;				
			}
		}		
		return sum;
	}
	
	//Take the Jbutton and value needed to disable it.
	void deactivateFactor(JButton button[], int val){
		button[val].setEnabled(false);
	}
	
	void CheckForwinner(JButton button[]) {
		int check = 0;
		for(int i = 1; i < 101; i++) {
			if(button[i].isEnabled()) {
				check++;
				
			}
		}
			if(check == 0) {
				if(p1.points > p2.points) {
					winnerLabel.setText(p1.getName() + " wins");
				}
				else if (p2.points > p1.points) {
					winnerLabel.setText(p2.getName() + " wins");
				}
				else
					winnerLabel.setText("Draw");
			}
			
		}
	//Checks if button was used as well as gives out points to the player, and the factors to the other player
	public void addPoint(int clickNum, JButton button[])
	{
		int val = clickNum;
		button[val].setEnabled(false);
		int sumOfFactor = findFactor(clickNum);
			//if P2's turn
			if (turnNum % 2 == 0)
			{
				p1.points =  sumOfFactor + p1.points;
				pOne.setText(p1.getName() + " " + Integer.toString(p1.points));
					
					
				p2.points = clickNum + p2.points;
				pTwo.setText(p2.getName()+ " " + Integer.toString(p2.points));
							
			}
			//If P1's turn
			else
			{
				p1.points = clickNum + p1.points;
				pOne.setText(p1.getName() + " " + Integer.toString(p1.points));
				
				p2.points = sumOfFactor + p2.points;
				pTwo.setText(p2.getName()+ " " + Integer.toString(p2.points));
					
			}
				
		}
	
	}
	
