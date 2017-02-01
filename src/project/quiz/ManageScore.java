package project.quiz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class ManageScore 
{
	private static final String DATABASE_NAME = "TEST";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "root";

	static Connection conn;
	static Statement stmt;
	
	private static int score = 0;
	private static int tempScore;	//holds score for temporary because if user wants to solve one more time,
									//score is added to previous score. Score is set to zero and tempScore is displayed
	
	public static int getTempScore() {
		return tempScore;
	}

	public static void setTempScore(int tempScore) {
		ManageScore.tempScore = tempScore;
	}
	/*
	 * Creates a connection with database
	 * Gets the results from database
	 * Compares the results with choices 
	 * returns score 
	 */
	public static int showScore()
	{
		
		String query = "SELECT " + DrawQuestionPanel.getLanguage() + " FROM QUIZGAME;";
		ArrayList<String> correctAnswers = new ArrayList<String>();
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/"  + DATABASE_NAME, USER_NAME, PASSWORD);
			
			pstmt = conn.prepareStatement(query);
			ResultSet result = pstmt.executeQuery();
			
			while(result.next()){
				correctAnswers.add(result.getString(DrawQuestionPanel.getLanguage())); 
			}
			
			ArrayList<String> choices = DrawQuestionPanel.getChoices();
			
			Iterator<String> choicesIt = choices.iterator(); 
			Iterator<String> answerIt = correctAnswers.iterator();
			
			if(choices.size() == correctAnswers.size()){
				while(choicesIt.hasNext()){
					if(choicesIt.next().equals(answerIt.next()))
						score++;
				}
			}
			else
				System.err.println("Uncorrect number of choices or answers"); 
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				pstmt.close();
				conn.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		setTempScore(score);
		score = 0;
		return tempScore;
	}
	
	
	
}
