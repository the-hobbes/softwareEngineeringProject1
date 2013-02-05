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

		boolean continueGoFish = UserInterface.getMenuOption();

		while (continueGoFish)
		{
			goFish.setUpGame();

			System.out.println( "=================================================================");
			while(goFish.continueGame())
			{

				Deck tempDeck = goFish.getDeck();
				tempDeck.shuffle();
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
		
		//stats section. 
		Statistics statGraph = new Statistics("GoFish Stats!", "Summary of Play Scores", historicalScores);
		statGraph.pack();
		statGraph.setVisible(true);

		// goFish.endGame();


	}//main function

}//GamePlay class

