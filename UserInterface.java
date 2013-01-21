/**
*@author Ethan Eldridge
*
*The interface for the user to interact with.
*/

public class UserInterface{
	
	public UserInterface(){

	}

	/**
	*Deals out 5 cards to each player, decides whose turn it is first, displays rules and credits
	*@param game the game to setup
	*@return the game that has been setup
	*/
	public static Game setUpGame(Game game){

		return game;
	}

	/**
	*Display the people who made this game
	*/
	public static void displayCredits(){

	}
	
	/**
	*Displays the current person hand
	*/
	public static void displayHand(Hand userHand){

	}

	/**
	*Display the rules and helpful information to the user
	*/
	public static void displayHelp(){

	}

	/**
	*Get the user input and handle it
	*@return Returns the card the user asked for (-1) if the user has no cards left
	*/
	public static int getCommand(Hand userHand){
		//While there is a card in the user's hand and they haven't chosen a card from it

		//ask for the command (either a card number or a help)

		return -1;
	}



}