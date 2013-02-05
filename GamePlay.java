/***
* @author Danielle Steimke
* Main function for gameplay
*
*/

import java.util.ArrayList;

public class GamePlay{
	
	public static void main(String[] args){
		//this arraylist is used to collect the results of each game played
		ArrayList<int[]> historicalScores = new ArrayList<int[]>();

		//start game
		Game goFish = new Game();

		//taken out of the setup game due to repetition when a new game is started after an old game finishes
		// UserInterface.displayCredits();
		// UserInterface.displayHelp();

		boolean continueGoFish = UserInterface.getMenuOption();

		while (continueGoFish)
		{
			goFish.setUpGame();

			System.out.println( "=================================================================");
			while(goFish.continueGame())
			{

				Deck tempDeck = goFish.getDeck();
				tempDeck = goFish.getHumanPlayer().doTurn(tempDeck, goFish.getComputerPlayer());
				continueGoFish = goFish.continueGame();
				if(continueGoFish){
					tempDeck = goFish.getComputerPlayer().doTurn(tempDeck, goFish.getHumanPlayer());
				}
				goFish.setDeck(tempDeck);				
			}
			//add the score for the game to the arraylist
			historicalScores.add(goFish.getGameScore());

			goFish.endGame();
			continueGoFish = UserInterface.getMenuOption();		

		}

		//unit test for stats gathering info for statistics
		// System.out.println( "=============================Historical Scores===================================");
		// for (int[] game : historicalScores) {   
		//     System.out.println("Computer score " + game[0] + " Player score " + game[1]);
		// }
		
		Statistics statGraph = new Statistics("GoFish Stats!", "Summary of Play Scores", historicalScores);
		statGraph.pack();
		statGraph.setVisible(true);

		// goFish.endGame();


	}//main function

}//GamePlay class

