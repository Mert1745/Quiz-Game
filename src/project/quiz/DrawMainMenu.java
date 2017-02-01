package project.quiz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DrawMainMenu extends JFrame
{
	protected static final int GAP = 10;
	private static final long serialVersionUID = 1L;
	
	private final JPanel mainMenuPanel;	//Panel to be able to hold user name panel, label and text field
	private final JPanel userNamePanel; //Panel for JButtons
	
	private static JTextField userName;
	private final JLabel label;
	/*
	 * buttons for every different languages
	 * different options can be added
	 */
	private final JButton java;
	private final JButton cPlusPlus;
	private final JButton cSharp;
	
	public DrawMainMenu() {
		//Header of main menu
		super("Quiz-Test");

		
		mainMenuPanel = new JPanel(new GridLayout(4 ,1, GAP, GAP));
		userNamePanel = new JPanel(new BorderLayout());
		/*
		 * initializes label and text field
		 */
		label = new JLabel("Write your name: ");
		userName = new JTextField();
		/*
		 * adds label and text field to the panel
		 */
		userNamePanel.add(label, BorderLayout.CENTER);
		userNamePanel.add(userName, BorderLayout.AFTER_LAST_LINE);
		
		mainMenuPanel.add(userNamePanel); //user name panel is added to the main menu panel 
		mainMenuPanel.setBorder(BorderFactory.createTitledBorder(	//creates etched border for main menu panel
                BorderFactory.createEtchedBorder(), "Main Menu"));
		/*
		 * Buttons are initialized and sets action command for which button is selected
		 */
		java = new JButton("Start to Java Test");
		java.setActionCommand("Java");
		cPlusPlus = new JButton("Start to C++ Test");
		cPlusPlus.setActionCommand("CPlusPlus");
		cSharp = new JButton("Start to C# Test");
		cSharp.setActionCommand("CSharp");
		
		mainMenuPanel.add(cPlusPlus);
		mainMenuPanel.add(cSharp);
		mainMenuPanel.add(java);
		
		add(mainMenuPanel);
		/*
		 * Creates an actionListener and buttons are added to it
		 */
		ActionListener mainMenuHandler = new MainMenuController();
		
		java.addActionListener(mainMenuHandler);
		cPlusPlus.addActionListener(mainMenuHandler);
		cSharp.addActionListener(mainMenuHandler);
	}
	/*
	 * Gets user name to be able to display on the Score Frame
	 */
	public static String getUserName()
	{
		return DrawMainMenu.userName.getText();
	}
}
