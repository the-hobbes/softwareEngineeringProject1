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
			//call continueGame function with false, to end the game
		}
		
		//request the card from the opponent
		if (makeCardRequest(opponent, desiredCard)){
			//if the opponent has that card, get all of those card of that order
			//need to get all of the cards of that type, and add them to the hand
		}
		else{
			//the opponent doesn't have the card, and the player must go fish
		}
		
		return deck;
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
		
		return opponentHasCard;
	}
	
	/**
	 * respondCardRequest
	 * Used to respond to card requests from opponents. Looks through hand and removes the card requested
	 * for, if it is present. Returns that card.
	 * @param desiredCard
	 * @return null if not preset, card if is present
	 */
	public Card respondCardRequest(int desiredCard){
		
	}
	
	/**
	 * endTurn
	 * Function to set the final values for the end of the turn
	 */
	public void endTurn(){
		
	}
}