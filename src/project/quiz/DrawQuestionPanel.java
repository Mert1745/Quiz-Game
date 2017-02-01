package project.quiz;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class DrawQuestionPanel extends JFrame
{	
	private static final long serialVersionUID = 1L;
	private static final int NUMBER_OF_OPTIONS = 4;
	private JPanel questionPanel;
	private  JPanel[] mainPanels;
	private  JPanel[] radioButtonPanels;
	private  JTextPane[] questionTextPane;
	private  JRadioButton[] optionsRadioButton;
	private static  ButtonGroup[] buttonGroup;
	private  JButton finishButton;	
	
	private static String language;

	
	public static String getLanguage() {
		return DrawQuestionPanel.language;
	}

	public static void setLanguage(String language) {
		DrawQuestionPanel.language = language;
	}
	/*
	 * @param language takes programming languages which is selected in the main menu
	 */
	public DrawQuestionPanel(String language)
	{
		super(language + " Test");
		
		if(language.isEmpty())
			System.err.println("Language could not be found");
		else
			setLanguage(language);
		/*
		 * panel which holds all the panels
		 */
		questionPanel = new JPanel(new GridBagLayout());
		
		/*
		 * constraints for question panel which holds all panels
		 * 
		 */
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridheight = 1;
		constraints.gridwidth = MainMenuController.NUMBER_OF_QUESTIONS + 1;
		constraints.weightx = 1;
		constraints.weighty = 1;
		constraints.gridx = 0;
		
		
		mainPanels = new JPanel[MainMenuController.NUMBER_OF_QUESTIONS];
		radioButtonPanels = new JPanel[MainMenuController.NUMBER_OF_QUESTIONS];
		questionTextPane = new JTextPane[MainMenuController.NUMBER_OF_QUESTIONS];
		optionsRadioButton = new JRadioButton[MainMenuController.NUMBER_OF_QUESTIONS * NUMBER_OF_OPTIONS];
		buttonGroup = new ButtonGroup[MainMenuController.NUMBER_OF_QUESTIONS];
		finishButton = new JButton("Finish " + language + " test");
		
		int indexOfRadioButtons = 0;
		/*
		 * Creates objects of mainPanels, which is for every question, Text Panes for questions, and Button Groups
		 * All main panels are added to the big panel, which is questionPanel
		 * 
		 */
		for(int i = 0; i < MainMenuController.NUMBER_OF_QUESTIONS; i++)
		{
			constraints.gridy = i;			//constraints
			questionTextPane[i] = new JTextPane();
			
			mainPanels[i] = new JPanel(new BorderLayout());
			radioButtonPanels[i] = new JPanel();
			
			mainPanels[i].setBorder(BorderFactory.createTitledBorder(
	                BorderFactory.createEtchedBorder(), "Question-" + (i + 1)));
			
			questionTextPane[i].setText(getQuestion(i));
			questionTextPane[i].setEditable(false); 
			questionTextPane[i].setBackground(null);
			
			mainPanels[i].add(questionTextPane[i], BorderLayout.NORTH);
			
			for(int j = indexOfRadioButtons; j < indexOfRadioButtons + NUMBER_OF_OPTIONS; j++)
				optionsRadioButton[j] = new JRadioButton();
			
			getOptions(i, indexOfRadioButtons);
			
			buttonGroup[i] = new ButtonGroup();
			
			for(int k = indexOfRadioButtons + NUMBER_OF_OPTIONS ; indexOfRadioButtons < k; indexOfRadioButtons++)
			{
				buttonGroup[i].add(optionsRadioButton[indexOfRadioButtons]);
				radioButtonPanels[i].add(optionsRadioButton[indexOfRadioButtons]);
			}
			mainPanels[i].add(radioButtonPanels[i], BorderLayout.SOUTH);
			
			questionPanel.add(mainPanels[i], constraints);
		}
		
		/*
		 * Finish button operations
		 */
		constraints.gridy = MainMenuController.NUMBER_OF_QUESTIONS + 1;
		constraints.fill = 0;
		questionPanel.add(finishButton, constraints); 
		/*
		 * Scroll bar operations
		 */
		JScrollPane scrollbar = new JScrollPane(questionPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollbar.getVerticalScrollBar().setUnitIncrement(15);
		scrollbar.getHorizontalScrollBar().setUnitIncrement(15);
		
		/*
		 * Listener operations for finish buttons
		 */
		add(scrollbar);
		ActionListener finishButtonHandler = new QuestionPanelController();
		finishButton.addActionListener(finishButtonHandler); 
	}
	
	/*
	 * gets the desired question for texts
	 * @param index for which question are taken 
	 */
	public String getQuestion(int index)
	{
		String text = "";
		try
		{
			text = new String(Files.readAllBytes(Paths.get(language + "Question" + (++index))));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return text;
	}	
	
	/*
	 * gets the options for question and adds to the radio button optionsRadioButton
	 * @param index 				decides which options of indexed question are taken
	 * @param indexOfRadioButtons   takes index of radio buttons 
	 */
	public void getOptions(int index, int indexOfRadioButtons)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(language + "Answer" + (++index)));
			
		    String line;
			
			for(int k = indexOfRadioButtons + NUMBER_OF_OPTIONS  ; indexOfRadioButtons < k ; indexOfRadioButtons++){
				line = br.readLine();
				optionsRadioButton[indexOfRadioButtons].setText(line);
			}
			
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	/*
	 * gets the users choices and stores in an ArrayList and returns that ArrayList
	 * 
	 */
	public static ArrayList<String> getChoices()
	{
		ArrayList<String> choices = new ArrayList<String>();
		
		for(int i = 0; i < MainMenuController.NUMBER_OF_QUESTIONS; i++)
		{
			Enumeration<AbstractButton> allRadioButton = buttonGroup[i].getElements();
			
	        while(allRadioButton.hasMoreElements())  
	        {  
	           JRadioButton temp = (JRadioButton) allRadioButton.nextElement();  
	           if(temp.isSelected())  
	           {  
	        	   choices.add(temp.getText());
	        	   break;
	           }
	        }
		}
		
		return choices;
	}
	
	/*
	 * Controls if all options are selected, if so returns true, returns false otherwise 
	 * 
	 */
	public static boolean isAllChecked()
	{
		for(int i = 0; i < MainMenuController.NUMBER_OF_QUESTIONS ; i++)
		{	
			if (buttonGroup[i].getSelection() == null)
				return false;	
		}
			return true;
		
	}
}
