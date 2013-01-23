/**
 * HumanPlayer
 * @author phelanvendeville
 * Class to handle all of the functionality for the human player in the gofish game. Implements the 
 * Player interface.
 */

import java.util.ArrayList;

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
		//add a specific card to the hand so we know what we are looking for
		Card newCard = new Card("spades", 1);

		//add cards from the deck to the cards array
		for(int ii=0; ii<4; ii++){
			cards[ii] = deck.getTopCard(); 
		}
		//add our specific card
		cards[4] = newCard;

		Hand hand = new Hand(cards, 1);
		HumanPlayer human = new HumanPlayer(hand);
		
		System.out.println(hand);
		ArrayList<Card> returnedRequest = human.respondCardRequest(1);
		
		System.out.println("--------");
		System.out.println("");
		System.out.println(returnedRequest);

		// Hand hand = new Hand(cards, 1);
		// System.out.println("Before");
		// System.out.println(hand);
		// System.out.println("after");
		// hand.removeSpecificCard(4);
		// System.out.println(hand);
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
		
		//Display the user's hand to them
		UserInterface.displayHand(playerHand);

		//get user input (which card to request from opponent?)
		int desiredCard = UserInterface.getCommand(playerHand);
		
		//if the opponent has no cards in their hand, the game is over
		if(desiredCard == -1){
			//call the endgame functions
		}
		
		//request the card from the opponent until all of those cards have been taken
		if (makeCardRequest(opponent, desiredCard)){
			//get all instances of that card from the opponent
			opponent.respondCardRequest(desiredCard);
			//add them to your hand
			//remove them from the opponent's hand
			//check for a full set of cards in your hand
			//play the full set down, if there are any
			//call doTurn() again
		}
		//the opponent doesn't have the card, and the player must go fish
		else{
			//remove the top card from the deck
			//add that card to your hand
			//check for the presence of a full set
			//play that full set if there is one
			//if the card pulled from the deck is the one asked for, call doTurn()
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
	 * Used to respond to card requests from opponents. Looks through hand and removes the card(s) requested
	 * for, if it is present. Returns the (those) card(s).
	 * @param desiredCard
	 * @return null if not preset, the and array containing the card(s) if present
	 */
	public ArrayList<Card> respondCardRequest(int desiredCard){
		//create a holder arraylist for the found cards, and an index to specify the place of that card in the player's hand
		ArrayList<Card> foundCards = new ArrayList<Card>();
		int index = 0;

		//iterator way
		//go through each card in the playerhand.
		// for(Card card : playerHand.getCards()){
		// 	System.out.println(index);
		//If the desired card rank is present, remove that card from the player hand and add it to the arraylist
		// 	if(card.getRank() == desiredCard){
		// 		//add it to the arraylist of cards
		// 		foundCards.add(card);
		// 		// remove the card from the hand if it is found
		// 		playerHand.removeSpecificCard(index);
		// 		// System.out.println("added and removed");

		// 	}
		// 	index++;
		// }
		
		//return the arraylist
		return foundCards;
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