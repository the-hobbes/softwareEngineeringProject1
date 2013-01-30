/**
*@author Scott MacEwan
*
*/
import java.util.Random;

public class Game{
	public Turn[] turnHistory = new Turn[30]; // history of each turn
	Deck theDeck = new Deck();

	Player[] players = new Player[2];

	static final int PLAYER = 0;

	static final int COMPUTER = 1;

	int currentPlayer = 0;


	/**
	*Deals out 5 cards to each player, decides whose turn it is first, displays rules and credits
	*/
	public void setUpGame(){
		//shuffle the deck
		theDeck.shuffle();

		//instantiate the two players' hands
		Hand playerHand = new Hand();
		Hand aiHand = new Hand();

		//deal out 7 cards to each plater
		for(int i = 0; i<7; i++){
			playerHand.addCard(theDeck.getTopCard());
			aiHand.addCard(theDeck.getTopCard());
		}

		System.out.println("Player Hand:\n");
		System.out.println(playerHand.toString());
		System.out.println("AI Hand:\n");
		System.out.println(aiHand.toString());
		//create the two players with the created hands
		AI ai = new AI(aiHand);
		HumanPlayer player = new HumanPlayer(playerHand);

		//add them to the player array
		players[PLAYER] = player;
		players[COMPUTER] = ai;


		//display credits and help
		UserInterface.displayCredits();
		UserInterface.displayHelp();


		//generate random boolean to see who goes first
		Random rand = new Random();
		boolean turn = rand.nextBoolean();
		if(turn){
			System.out.println("\nHUMAN WILL PLAY FIRST");
			currentPlayer = 0;
		}else{
			System.out.println("\nCOMPUTER WILL PLAY FIRST");
			currentPlayer = 1;
		}
	}

	/**
	*Decides if the game is going to continue or not.
	*/
	public boolean continueGame(){
		boolean continueGame = true;
		if(theDeck.isEmpty() || players[0].hasCards() || players[1].hasCards()){
			continueGame = false;
		}
		return continueGame;
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
		Game game = new Game();
		game.setUpGame();
	}


}