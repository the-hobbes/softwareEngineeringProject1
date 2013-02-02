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
			UserInterface.getMenuOption();
			while(goFish.continueGame())
			{
				goFish.getHumanPlayer().doTurn(goFish.getDeck(), goFish.getComputerPlayer(), goFish.turnHistory);
				goFish.processHand(goFish.getHumanPlayer().getHand());
				goFish.getComputerPlayer().doTurn(goFish.getDeck(), goFish.getHumanPlayer(), goFish.turnHistory);
				goFish.processHand(goFish.getComputerPlayer().getHand());
				
				
			}			
		}
		
		goFish.endGame();


	}//main function

}//GamePlay class

