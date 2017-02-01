package project.quiz;

import javax.swing.JFrame;

/*
 * @Author Mert KÖSE
 *
 * A test program for three different type of programming language
 * which can be any number of questions
 */

public class Main 
{
	private static final int MAIN_FRAME_SIZE_X = 280;
	private static final int MAIN_FRAME_SIZE_Y = 360;
	
	static DrawMainMenu mainMenuFrame = new DrawMainMenu();
	
	public static void main(String[] args) {
		/*
		 * Arranges main menu default operations
		 */
		mainMenuFrame.setSize(MAIN_FRAME_SIZE_X, MAIN_FRAME_SIZE_Y);
		mainMenuFrame.setLocation(500, 200);
		mainMenuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainMenuFrame.setVisible(true);
	}
}
