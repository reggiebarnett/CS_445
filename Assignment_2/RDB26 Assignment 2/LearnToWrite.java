// CS 0445 Spring 2013
// Partial implementation of LearnToWrite program
// You must complete this implementation for Assignment 2.
// Specifically, you must add the function buttons and logic to allow:
//	1) Insertion of a blank space at the current edit position
//	2) Move the current edit position to the left
//	3) Move the current edit position to the right
//	4) Delete the character at the current edit position
// Be sure to handle special cases for all of these.  I recommend putting
// all of these buttons into a second JPanel and then adding that JPanel
// appropriately into the JFrame.

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LearnToWrite
{
	private static String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	JFrame theWindow;       // This is the main window
	JButton [] theButtons;  // Array of buttons for letters
	JButton [] nextButtons; // Array for next row of buttons

	JPanel panelOne;	    // Panel to store letter buttons
	JPanel panelTwo;		//Panel to store Space, <, >, and Del
	
	JLabel theLabel;	    // Label to show output string
	JLabel posLabel;		// Label to show caret position within string
	int pos;				// Indicates the position of the caret (showing
							// where we are in the string)
	MyListener theListener; // ActionListener for letters
	
	MyStringBuilder theBuffer; // Note the StringBuilder class (so you can run this as
							 // is).  With your program the class should instead be
							 // MyStringBuilder
	MyStringBuilder theCaret;	 // This should also be a MyStringBuilder in your final
							 // program.  This StringBuilder will contain all blanks
							 // except for one caret (^) to indicate the current
							 // edit position within theBuffer.

	public LearnToWrite()
	{
		theWindow = new JFrame("Learn to Write");
		theWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// This code will create all of the letter buttons and assign them
		// appropriately within a JPanel
		theListener = new MyListener();
		panelOne = new JPanel();
		panelOne.setLayout(new GridLayout(1,26));
		theButtons = new JButton[letters.length()];
		for (int i = 0; i < theButtons.length; i++)
		{
			theButtons[i] = new JButton(""+letters.charAt(i));
			theButtons[i].setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 22));
			theButtons[i].addActionListener(theListener);
			panelOne.add(theButtons[i]);
		}
		
		panelTwo = new JPanel();
		panelTwo.setLayout(new GridLayout(1,4));
		nextButtons = new JButton[4];
			nextButtons[0] = new JButton("Space");
			nextButtons[0].setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 22));
			nextButtons[0].addActionListener(theListener);
			panelTwo.add(nextButtons[0]);
			nextButtons[1] = new JButton("<");
			nextButtons[1].setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 22));
			nextButtons[1].addActionListener(theListener);
			panelTwo.add(nextButtons[1]);
			nextButtons[2] = new JButton(">");
			nextButtons[2].setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 22));
			nextButtons[2].addActionListener(theListener);
			panelTwo.add(nextButtons[2]);
			nextButtons[3] = new JButton("Del");
			nextButtons[3].setFont(new Font("Monospaced", Font.ITALIC + Font.BOLD, 22));
			nextButtons[3].addActionListener(theListener);
			panelTwo.add(nextButtons[3]);
		
		// Set up the labels for the string and the caret.  Using "Monospaced" for
		// the Font allows all of the widths to be the same, which is necessary to
		// enable the caret to show correctly under the string.
		pos = 0;
		theBuffer = new MyStringBuilder("");
		theLabel = new JLabel("");
		theLabel.setFont(new Font("Monospaced", Font.BOLD, 22));
		theCaret = new MyStringBuilder("^");
		posLabel = new JLabel(theCaret.toString());
		posLabel.setFont(new Font("Monospaced", Font.BOLD, 22));

		theWindow.setLayout(new GridLayout(4,1));
		theWindow.add(panelOne);
		theWindow.add(panelTwo);
		theWindow.add(theLabel);
		theWindow.add(posLabel);

		theWindow.setSize(800,95);
		theWindow.setVisible(true);
	}

	class MyListener implements ActionListener
	{
		// Get the text from the button that is pressed and append it to
		// the end of the StringBuilder (again, in your program it will be
		// a MyStringBuilder).  Also insert a blank into the caret
		// StringBuilder so that it also "moves" with the String.
		public void actionPerformed(ActionEvent e)
		{
			JButton theEventer = (JButton) e.getSource();
			String theChar = theEventer.getText();
			if(theChar.equals("Space"))
			{
				theBuffer.insert(pos, " ");
				theCaret.insert(0, " ");
				pos++;
			}
			else if(theChar.equals("<"))
			{
				if(pos>0)
				{
					theCaret.deleteCharAt(0);
					pos--;
				}
			}
			else if(theChar.equals(">"))
			{
				if(pos < theBuffer.length())
				{
					theCaret.insert(0, " ");
					pos++;
				}
			}
			else if(theChar.equals("Del"))
			{
				theBuffer.deleteCharAt(pos);
				if(pos > 0)
				{
					theCaret.deleteCharAt(0);
					pos--;
				}
			}
			else
			{
				theBuffer.insert(pos, theChar);
				pos++;
				theCaret.insert(0, " ");
			}
			theLabel.setText(theBuffer.toString());
			posLabel.setText(theCaret.toString());
		}
	}

	public static void main(String [] args)
	{
		new LearnToWrite();
	}
}