/**
 * HumanPlayer
 * @author phelanvendeville
 * Class to handle all of the functionality for the human player in the gofish game. Implements the 
 * Player interface.
 */
public class HumanPlayer implements Player{
	private Deck gameDeck;
	private Player opponent;
	private boolean hasCards;
	private boolean opponentHasCard;
	private Hand playerHand;

	/**
	 * main method for testing purposes
	 */
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Card[] cards = new Card[5];

		for(int ii=0; ii<5; ii++){
			cards[ii] = deck.getTopCard(); 
		}

		Hand hand = new Hand(cards, 1);
		System.out.println(hand);
		// System.out.println("_____");

		HumanPlayer human = new HumanPlayer(hand);
		// System.out.println(human.getHand());
			
		// if(human.hasSet() >= 1){
		// 	int requestRank = human.hasSet();
		// 	int setQTY = human.countSetQTY(requestRank);
		// 	System.out.println("Number of elements in our set: "+setQTY);
		// }
	}
	
	/**
	 * default constructor for human player
	 * @param hand
	 */
	public HumanPlayer(Hand playerHand){
		this.playerHand = playerHand;
	}
	
	/**
	 * doTurn
	 * Function to perform the actions of a turn
	 * @param gameDeck
	 * @param opponent
	 * @return deck
	 */
	public Deck doTurn(Deck gameDeck,Player opponent){
		this.gameDeck = gameDeck;
		this.opponent = opponent;
		
		//get user input (which card to request from opponent?)
		int desiredCard = UserInterface.getCommand(playerHand);
		
		//if the opponent has no cards in their hand, the game is over
		if(desiredCard == -1){
			//call the endgame functions
		}
		
		//request the card from the opponent
		if (makeCardRequest(opponent, desiredCard)){
			//if the opponent has that card, get all of those card of that order
			//need to get all of the cards of that type, and add them to the hand
			/**
			 * This can be done in two ways:
			 * 1) check to see if the opponent has the card.
			 * -if he does, get the card and add it to your hand and remove it from his
			 * -then request again (continue in this way until the check fails)
			 * 2) check to see if the opponent has the card
			 * - if he does, iterate through his hand and return a list of all the cards, removing them from his
			 * 
			 * ALSO NOTE: what about the Game.processHand() function? Is this supposed to do the removal 
			 * and return of all the matched cards?
			 */
		}
		else{
			//the opponent doesn't have the card, and the player must go fish
		}
		
		return gameDeck;
	}
	
	/**
	 * hasCards
	 * Function to determine if the player has cards in their hand
	 * @return hasCards
	 */
	public boolean hasCards(){
		if(playerHand.calcTotal() > 0){
			hasCards = true;
		}
		else
			hasCards = false;
		
		return hasCards;
	}
	
	/**
	 * makeCardRequest
	 * Function used to ask another player for a card
	 * @param opponent
	 * @return opponentHasCard
	 */
	public boolean makeCardRequest(Player opponent, int desiredCard){
		this.opponent = opponent;
		//if the card is in the opponent's hand, then return true. otherwise, false.
		return opponentHasCard;
	}
	
	/**
	 * respondCardRequest
	 * Used to respond to card requests from opponents. Looks through hand and removes the card requested
	 * for, if it is present. Returns that card.
	 * @param desiredCard
	 * @return null if not preset, the card itself if it is present
	 */
	public Card respondCardRequest(int desiredCard){
		return null;
	}
	
	/**
	 * getHand
	 * Function used to return the hand of a player.
	 * @return Hand
	 */
	public Hand getHand(){
		return playerHand;
	}
	/**
	 * endTurn
	 * Function to set the final values for the end of the turn
	 */
	public void endTurn(){
		
	}

	public Card[] getMyCompleteSets(){
		Card[] cards = new Card[1];
		return cards;
	}
}