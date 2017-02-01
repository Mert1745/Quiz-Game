package project.quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainMenuController implements ActionListener
{	/*
	* Number of questions, which can be changed any desired question number
	* It is enough only changing this macro to decide how many questions are displayed on the screen
	*/
	public static final int NUMBER_OF_QUESTIONS = 5;
	
	public MainMenuController(){
	}
	/*
	 * @param event handles which buttons are clicked and creates an object of question panel and sets default operations
	 *        handles whether name is empty
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(!DrawMainMenu.getUserName().isEmpty())
		{
			Main.mainMenuFrame.setVisible(false);
			
			String language = null;
			language = event.getActionCommand();
				
			DrawQuestionPanel questionFrame = new DrawQuestionPanel(language);
			
			questionFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
			//questionFrame.setUndecorated(true);
			questionFrame.setSize(600, 600);
			//questionFrame.setLocation(50, 0);
			questionFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			questionFrame.setVisible(true);
		}
		else
			JOptionPane.showMessageDialog(null, "Warning: Name can not be empty");
	}
}
