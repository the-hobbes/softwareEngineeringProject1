/**
 * HumanPlayer
 * @author phelanvendeville
 * Class to handle all of the functionality for the human player in the gofish game. Implements the 
 * Player interface.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;  
import java.util.Stack;


public class HumanPlayer implements Player{
	private String name = "human"; 
	private Deck gameDeck;
	private Player opponent;
	private boolean hasCards;
	private boolean opponentHasCard;
	private Hand playerHand;
	public int currentScore;
	public Turn[] turnHistory;
	private boolean continueGame;

	/**
	 * main method for testing purposes
	 */
	public static void main(String[] args) {
		// Deck deck = new Deck();
		// deck.shuffle();

		/* Test the respondCardRequest function */
		// Card[] cards = new Card[5];
		//add a specific card to the hand so we know what we are looking for
		// Card newCard = new Card("spades", 1);

		//add cards from the deck to the cards array
		// for(int ii=0; ii<4; ii++){
		// 	cards[ii] = deck.getTopCard(); 
		// }
		//add our specific card
		// cards[4] = newCard;

		// Hand hand = new Hand(cards);
		// HumanPlayer human = new HumanPlayer(hand);
		
		/*Test the respondCardRequest function
		System.out.println("Before Hand");
		System.out.println(hand);
		ArrayList<Card> returnedRequest = human.respondCardRequest(1);
		System.out.println("");
		System.out.println("The matched cards");
		System.out.println(returnedRequest);
		System.out.println("");
		System.out.println("the new hand, minus those cards");
		System.out.println(human.getHand());*/

		/* test the doTurn function, creating a new player as an opponent*/ 
		// Card[] cards2 = new Card[5];
		// deck.shuffle();
		// for(int ii=0; ii<5; ii++){
		// 	cards2[ii] = deck.getTopCard(); 
		// }
		// Hand hand2 = new Hand(cards2);
		// HumanPlayer computer = new HumanPlayer(hand2);

		// System.out.println("Opponents Hand");
		// System.out.println(computer.getHand());
		// System.out.println("");

		// human.doTurn(deck, computer);
	}
	
	/**
	 * default constructor for human player
	 * @param hand
	 */
	public HumanPlayer(Hand playerHand){
		this.playerHand = playerHand;
		this.currentScore = 0;
	}
	
	/**
	 * doTurn
	 * Function to perform the actions of a turn
	 * @param gameDeck
	 * @param opponent
	 * @return deck
	 */
	public Deck doTurn(Deck gameDeck,Player opponent, Turn[] turnHistory){
		this.gameDeck = gameDeck;
		this.opponent = opponent;
		this.turnHistory = turnHistory;
		this.continueGame = true;
		System.out.println("-------  Your Turn -------");
		ArrayList<Card> foundCards = new ArrayList<Card>();

		//Display the user's hand to them
		System.out.println("Your Hand");
		UserInterface.displayHand(playerHand);

		//get user input (which card to request from opponent?)
		int desiredCard = UserInterface.getCommand(playerHand);
		
		//if the opponent has no cards in their hand, the game is over
		if(desiredCard == -1){
			//call the endgame functions
			this.continueGame = false;
		}
		
		//request all the cards of desired type from the opponent
		if (makeCardRequest(opponent, desiredCard)){
			//get all instances of that card from the opponent (removing them from the opponent's hand as well)
			foundCards = opponent.respondCardRequest(desiredCard);
			if(foundCards != null){
				//add them to your hand
				for(Card card : playerHand.getCards()){
					foundCards.add(card);
				}
				Card[] tempCard = new Card[foundCards.size()];
				for(int i=0; i<tempCard.length; i++){
					tempCard[i] = foundCards.get(i);
				}
				Hand newplayerhand = new Hand(tempCard);
				this.playerHand = newplayerhand;

				// System.out.println("Your new hand");
				// System.out.println(playerHand);
				// System.out.println("Opponents new hand");
				// System.out.println(opponent.getHand());

				//check for a full set of cards in your hand
				boolean isFullSet = playerHand.containsFourOfAKind(desiredCard);
				// System.out.println(isFullSet);
				//play the full set down, if there are any
				if(isFullSet)
					playFullSet(desiredCard);
				
				//call doTurn() again
				if(!playerHand.isEmpty() || !gameDeck.isEmpty())
					this.gameDeck = doTurn(this.gameDeck, opponent, turnHistory);
			}
		}
		//the opponent doesn't have the card, and the player must go fish
		else if(! this.gameDeck.isEmpty()){
			System.out.println("Nope, go fish");
			//remove the top card from the deck

			Card drawnCard = this.gameDeck.getTopCard();					

			System.out.println("You drew a " + drawnCard.getRank() + " of " + drawnCard.getSuit());
			//add that card to your hand
			
			this.playerHand.addCard(drawnCard);
			//check for the presence of a full set
			boolean isFullSet = playerHand.containsFourOfAKind(drawnCard.getRank());
			//play that full set if there is one
			if(isFullSet)
				playFullSet(drawnCard.getRank());
			
			//if the card pulled from the deck is the one asked for, call doTurn()
			if(drawnCard.getRank() == desiredCard)
			{
				if (!playerHand.isEmpty() || !gameDeck.isEmpty())
					this.gameDeck = doTurn(this.gameDeck, opponent, turnHistory);
			}
		}
		else{
			System.out.println("no cards left in the deck!");
		}
		
		return this.gameDeck;
	}

	/**
	  * getContinueGame
	  * @return continueGame, a boolean value
	  * used to determine if the game should continue, due to the opponent having no cards in their hand
	  */
	public boolean getContinueGame(){
		return this.continueGame;
	}


	/**
	  * getCurrentScore
	  * @return currentScore, the score for the turn
	  */
	public int getCurrentScore(){
		return this.currentScore;
	}

	/**
	  * playFullSet
	  * @param the card which has just been added to the hand
	  * Used to remove a full set from the hand and increment the score
	  */
	private void playFullSet(int desiredCard){
		//display message
		System.out.println("You got a full set of " + Integer.toString(desiredCard) + "'s");
		//increment score
		this.currentScore++;
		//remove those cards from the hand
		playerHand.removeFullSet(desiredCard);
		// System.out.println("Here's the hand, minus the 4 cards");
		// System.out.println(playerHand);
	}	
	/**
	 * hasCards
	 * Function to determine if the player has any cards in their hand
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
	 * Function used to ask another player if they possess a card
	 * @param opponent
	 * @return opponentHasCard
	 */
	public boolean makeCardRequest(Player opponent, int desiredCard){
		this.opponent = opponent;
		opponentHasCard = false;

		//if the card is in the opponent's hand, then return true. otherwise, false.
		for(Card card : opponent.getHand().getCards()){
			if(card.getRank() == desiredCard){
				opponentHasCard = true;
			}
		}

		if(opponentHasCard){
			System.out.println("Opponent had the card! \n");
		}else{
			System.out.println("Opponent did not have the card \n");
		}
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
		//create a holder arraylist for the found cards
		ArrayList<Card> foundCards = new ArrayList<Card>();

		//oldhand is a container for the players current hand
		Hand oldHand = this.playerHand;
		//newhand is a stack to place the cards not culled from the players hand into
		Stack<Card> newHand = new Stack<Card>();

		//go through each card in the hand
		for(int i=0; i<oldHand.getCards().length; i++){
			Card currentCard = oldHand.getCards()[i];
			//if the current card's rank isn't equal to the desired rank...
			if(currentCard.getRank() != desiredCard){
				//add that card to the new hand. it will not be given to the requesting player
				newHand.push(currentCard);
				System.out.println(currentCard.toString());
			}
			else{
				//otherwise, the card matches. add it to the arraylist keeping track of found cards
				foundCards.add(currentCard);
			}
		}

		// convert the new hand to a hand object, and make that the new player hand
		Card[] cardHolder = new Card[newHand.size()];
		for(int j = 0; j < newHand.size(); j++){
			cardHolder[j] = newHand.get(j);
			
		}
		Hand freshHand = new Hand(cardHolder);
		this.playerHand = freshHand;		
		//return the arraylist
		return foundCards;
	}

	/**
	  * getHand()
	  * @return a player's hand
	  * return a player's hand
	  */
	public Hand getHand(){
		return this.playerHand;
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