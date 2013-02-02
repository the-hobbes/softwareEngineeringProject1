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
			currentPlayer = 0;
		}else{
			currentPlayer = 1;
		}
	}

	/**
	*Decides if the game is going to continue or not.
	*/
	public boolean continueGame(){
		boolean continueGame = true;
		if(theDeck.isEmpty() || !players[PLAYER].hasCards() || !players[COMPUTER].hasCards()){
			continueGame = false;
		}
		return continueGame;
	}

	/**
	*Displays Scores and relevant information to the end game
	*/
	public void endGame(){
		if(players[PLAYER].getCurrentScore() > players[COMPUTER].getCurrentScore()){
			System.out.println("YOU WIN!");
			System.out.println("Your Score: " + players[PLAYER].getCurrentScore());
			System.out.println("Computer's Score: " + players[COMPUTER].getCurrentScore());
		}else if(players[COMPUTER].getCurrentScore() > players[PLAYER].getCurrentScore()){
			System.out.println("YOU LOSE!");
			System.out.println("Computer's Score: " + players[COMPUTER].getCurrentScore());
			System.out.println("Your Score: " + players[PLAYER].getCurrentScore());
		}else{
			System.out.println("YOU TIED!");
			System.out.println("Your Score: " + players[PLAYER].getCurrentScore());
			System.out.println("Computer's Score: " + players[COMPUTER].getCurrentScore());
		}
	}

	/**
	*Return the game deck
	*@return The game deck
	*/
	public Deck getDeck(){
		return this.theDeck;
	}

	public void setDeck(Deck deck){
		this.theDeck = deck;
	}

	public Player getComputerPlayer(){
		return players[COMPUTER];
	}

	public Player getHumanPlayer(){
		return players[PLAYER];
	}
	/**
	*Checks to see if the hand has 4 of a kind, and increments the score if so.
	*@param pHand The hand to be processed
	*/
	public void processHand(Hand pHand){

	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setUpGame();
		game.endGame();
	}


}