/***
*@author Danielle Steimke
*Main function for gameplay
*
*/

import java.util.Scanner;



public class GamePlay{
	
	public static void main(String[] args){
		
		//start game
		Game goFish = new Game();
		goFish.setUpGame(goFish);

		//find out if user needs help with the rules
		String userInput;
		System.out.println("Do you need help with the rules? (type yes or no)");
		Scanner keyboard = new Scanner(System.in);//reads user keyboard input
		userInput = keyboard.nextLine();//Line that is entered by user
		if (userInput.compareToIgnoreCase("yes")) displayHelp();

		while (goFish.continueGame()){
			humanPlayer.doTurn(deck, artificialIntelligence);
			goFish.processHand(humanHand);
			artificialIntelligence.doTurn(deck, humanPlayer);
			goFish.processHand(artificicialIntelligenceHand);}

		goFish.endGame();

		/*
		while (they want to play)
			getmenuoption()
			while(continuegameplay)
				do the functions of a turn
				turns are taken and such
		*/

	}//main function

}//GamePlay class

