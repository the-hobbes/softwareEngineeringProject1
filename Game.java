/**
  * Game
  * @author Scott MacEwan
  * Class used to create indiviudal games and maintain them. 
  * 
  * @param players, an array of the players playing the game
  * @param final int player, 0 indicates a player
  * @param final int computer, 1 indicates a computer
  * @param current player, the player who is taking their turn now
  */
import java.util.Random;
import java.util.Arrays;  

public class Game{
	Deck theDeck;

	Player[] players = new Player[2];

	static final int PLAYER = 0;

	static final int COMPUTER = 1;

	int currentPlayer = 0;

	/**
	*Deals out 5 cards to each player, decides whose turn it is first, displays rules and credits
	*/
	public void setUpGame(){
		//shuffle the deck
		theDeck = new Deck();
		theDeck.shuffle();

		//instantiate the two players' hands
		Hand playerHand = new Hand();
		Hand aiHand = new Hand();

		//deal out 7 cards to each plater
		for(int i = 0; i<7; i++){
			playerHand.addCard(theDeck.getTopCard());
			aiHand.addCard(theDeck.getTopCard());
		}
		/*
		System.out.println("Player Hand:\n");
		System.out.println(playerHand.toString());
		System.out.println("AI Hand:\n");
		System.out.println(aiHand.toString());
		*/
		//create the two players with the created hands
		AI ai = new AI(aiHand);
		HumanPlayer player = new HumanPlayer(playerHand);

		//add them to the player array
		players[PLAYER] = player;
		players[COMPUTER] = ai;

		//display credits and help
		// UserInterface.displayCredits();
		// UserInterface.displayHelp();

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
		System.out.println();
		System.out.println("===========================RESULTS===============================");

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

		System.out.println("=================================================================\n");
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

	/**
	  * getGameScore
	  * @return an array containing the score for each player in the game (index 0 = human, 1 = player)
	  */
	public int[] getGameScore(){
		int[] scores = new int[2];
		scores[0] = players[PLAYER].getCurrentScore();
		scores[1] = players[COMPUTER].getCurrentScore();

		return scores;
	}
}