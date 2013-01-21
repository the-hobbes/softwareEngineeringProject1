/**
*@author Ethan Eldridge
*
*The interface for the user to interact with.
*/

public class Interface{

	Deck theDeck = new Deck()
	
	static final PLAYER   = 0;
	static final COMPUTER = 1;

	Hand [] players = new Hand[2];

	int currentTurn = 0;

	public Interface(){
		
	}

	public void setUpGame(){

	}
	
	/**
	*Displays the current person hand
	*/
	public void displayHand(){

	}

	/**
	*Get the user input and handle it
	*@return Returns the card the user asked for
	*/
	public int getCommand(){

	}



}