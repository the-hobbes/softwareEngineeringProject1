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

		
		while (UserInterface.getMenuOption())
		{
			while(goFish.continueGame())
			{
				Deck tempDeck = new Deck();
				tempDeck = goFish.getHumanPlayer().doTurn(goFish.getDeck(), goFish.getComputerPlayer(), goFish.turnHistory);
				//goFish.processHand(goFish.getHumanPlayer().getHand());
				tempDeck = goFish.getComputerPlayer().doTurn(tempDeck, goFish.getHumanPlayer(), goFish.turnHistory);
				//goFish.processHand(goFish.getComputerPlayer().getHand());
				goFish.setDeck(tempDeck);
				
			}			
		}
		
		goFish.endGame();


	}//main function

}//GamePlay class

