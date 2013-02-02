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
				tempDeck = goFish.getHumanPlayer().doTurn(tempDeck, goFish.getComputerPlayer(), goFish.turnHistory);
				//goFish.processHand(goFish.getHumanPlayer().getHand());
				tempDeck = goFish.getComputerPlayer().doTurn(tempDeck, goFish.getHumanPlayer(), goFish.turnHistory);
				//goFish.processHand(goFish.getComputerPlayer().getHand());
				goFish.setDeck(tempDeck);
				
			}	

			continueGoFish = UserInterface.getMenuOption();		

		}
		
		goFish.endGame();


	}//main function

}//GamePlay class

