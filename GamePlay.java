/***
*@author Danielle Steimke
*Main function for gameplay
*
*/


public class GamePlay{
	
	public static void main(String[] args){
		
		//start game
		Game goFish = new Game();
		goFish.setUpGame();

		boolean continueGoFish = UserInterface.getMenuOption();

		while (continueGoFish)
		{

			System.out.println( "=================================================================");
			while(goFish.continueGame())
			{

				Deck tempDeck = goFish.getDeck();
				if (continueGoFish)
					tempDeck = goFish.getHumanPlayer().doTurn(tempDeck, goFish.getComputerPlayer(), goFish.turnHistory);
				continueGoFish = goFish.continueGame();
				if(continueGoFish){
					tempDeck = goFish.getComputerPlayer().doTurn(tempDeck, goFish.getHumanPlayer(), goFish.turnHistory);
				}
				goFish.setDeck(tempDeck);				
			}	
			goFish.endGame();
			continueGoFish = UserInterface.getMenuOption();		

		}
		
		


	}//main function

}//GamePlay class

