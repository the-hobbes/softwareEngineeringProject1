/**
* @author Danielle Steimke
* Main function for gameplay. Creates instances of the game and calls the appropriate functions to play. 
* Also call the statistics class to display statistical information.
* 
* @param historicalScores, an arraylist to store the arrays of integers containing the final scores for each game
* @param goFish, an instance of the game class
* @param continueGoFish, a boolean used to break out of the game loop
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
				//game loop
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

	}//main function

}//GamePlay class

