public class Game{
	public Turn[] turnHistory = new Turn[30]; // history of each turn
	Deck theDeck = new Deck();

	Player[] players = new Player[2];

	static final int PLAYER = 0;

	static final int COMPUTER = 1;

	int currentPlayer = 0;


	/**
	*Deals out 5 cards to each player, decides whose turn it is first, displays rules and credits
	*@param game the game to setup
	*@return the game that has been setup
	*/
	public static Game setUpGame(Game game){

		return game;
	}

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