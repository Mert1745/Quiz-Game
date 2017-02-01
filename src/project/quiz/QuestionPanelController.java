package project.quiz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class QuestionPanelController implements ActionListener{
	
	
	public QuestionPanelController() {
	}
	/*
	 * Controller for finishButton in the question panel,
	 * if all options are selected, displays results which are taken from class ManageScore
	 * otherwise a warning message pops out
	 */
	@Override
	public void actionPerformed(ActionEvent event)
	{
		if(!DrawQuestionPanel.isAllChecked())
			JOptionPane.showMessageDialog(null, "All questions should be answered"); 
		else
			JOptionPane.showMessageDialog(null, "User Name: " + DrawMainMenu.getUserName() +"\n"
											+ "You answered " + ManageScore.showScore() + " questions correct out of "
											+ MainMenuController.NUMBER_OF_QUESTIONS);
	}
}
