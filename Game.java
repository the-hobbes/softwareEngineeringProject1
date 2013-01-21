public class Game{
	Deck theDeck = new Deck()

	Hand[] players = new Hand[2];

	static final PLAYER = 0;

	static final COMPUTER = 1;

	int currentPlayer = 0;

	Interface ui = new Interface();

	int scores [] = {0,0};


	/**
	*Decides if the game is going to continue or not.
	*/
	public boolean continueGame(){
		return false;
	}

	/**
	*Displays Scores and relevant information to the end game
	*/
	public void endGame(){

	}

	/**
	*Checks to see if the hand has 4 of a kind, and increments the score if so.
	*@param pHand The hand to be processed
	*/
	public void processHand(Hand pHand){

	}

	/**
	*Deals 5 cards 
	*/
	public void dealHand(){

	}

	public static void main(String[] args) {
		
	}


}