import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;  
import java.util.Stack;
import java.math.*;


public class AI implements Player{
	private boolean deckHasCards = true;
	private Hand playerHand;
	private Card[] completedSets = new Card[13];
	// holds all of the opponent's previous requests so we know what they might have in their hand
	// private int[] cardsRequestedByOpponent = new int[52];
	// private int requestCounter = 0;
	private Player opponent;
	private Deck gameDeck;
	public int currentScore;
	public Turn[] turnHistory;
	private boolean hasCards;
	private boolean opponentHasCard;

	// a main() function has been included to
	// allow for testing
	public static void main(String[] args) {
		// Deck deck = new Deck();
		// deck.shuffle();
		// Card[] cards = new Card[5];

		// for(int ii=0; ii<2; ii++){
		// 	cards[ii] = deck.getTopCard(); 
		// }

		// cards[2] = new Card("clubs", 3);
		// cards[3] = new Card("spades", 3);
		// cards[4] = new Card("diamond", 3);

		// Hand playerHand = new Hand(cards);
		// System.out.println(playerHand);
		// // System.out.println("_____");

		// AI comp = new AI(playerHand);
		// System.out.println(comp.analyzeHand());
		System.out.println((int)(Math.random()*10));

	} // end main()

	public AI(Hand hand){
		this.playerHand = hand;
	} // end AI

	public Hand getHand(){
		return this.playerHand;
	} // end getHand()

	@Override
	public Deck doTurn(Deck gameDeck, Player opponent, Turn[] turnHistory){
		this.gameDeck = gameDeck;
		this.opponent = opponent;
		this.turnHistory = turnHistory;
		
		ArrayList<Card> foundCards = new ArrayList<Card>();

		// analyze hand inspects the state of the hand, and previous turns, and returns an
		// int indicating the rank of the desired card 
		int desiredCard = this.analyzeHand();
		
		//if the opponent has no cards in their hand, the game is over
		if(desiredCard == -1){
			//call the endgame functions
		}
		
		//request all the cards of desired type from the opponent
		if (makeCardRequest(opponent, desiredCard)){
			//get all instances of that card from the opponent (removing them from the opponent's hand as well)
			foundCards = opponent.respondCardRequest(desiredCard);
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


			//check for a full set of cards in your hand
			boolean isFullSet = playerHand.containsFourOfAKind(desiredCard);
			//play the full set down, if there are any
			if(isFullSet)
				playFullSet(desiredCard);
			
			//call doTurn() again
			gameDeck = doTurn(gameDeck, opponent, turnHistory);
		}
		//the opponent doesn't have the card, and the player must go fish
		else{
			//remove the top card from the deck
			Card drawnCard = gameDeck.getTopCard();
			//add that card to your hand
			this.playerHand.addCard(drawnCard);
			//check for the presence of a full set
			boolean isFullSet = playerHand.containsFourOfAKind(drawnCard.getRank());
			//play that full set if there is one
			if(isFullSet)
				playFullSet(desiredCard);
			
			//if the card pulled from the deck is the one asked for, call doTurn()
			if(drawnCard.getRank() == desiredCard)
				gameDeck = doTurn(gameDeck, opponent, turnHistory);
		}
		
		return gameDeck;
	} // end doTurn()

	/**
	* Analyzes the computer's hand
	*@return rank of desired card
	*/
	private int analyzeHand(){
		// for testing create an artifical turnHistory *******
		// this.turnHistory = new Turn[3];
		// this.turnHistory[0] = new Turn("human", true, 3);
		// this.turnHistory[1] = new Turn("ai", false, 7);
		// this.turnHistory[2] = new Turn("human", false, 13);
		// this.turnHistory[1] = new Turn("ai", true, 8);
		// this.turnHistory[2] = new Turn("human", false, 11);
		// this.turnHistory[1] = new Turn("ai", false, 7);
		// this.turnHistory[2] = new Turn("human", false, 10);
		// this.turnHistory[1] = new Turn("ai", true, 7);
		// this.turnHistory[2] = new Turn("human", false, 3);
		// end testing artifical turnHistory *******

		// go through our hand
		// priority1: we have 3 of a kind, and the opponent has drawn cards 
		// since we last asked for that rank
		// do we have a set
		int setRank = this.hasSet();
		int drewCounter = 0; // counts the numbner of cards drawn as we loop through our turns
		int countRequested = 0; // the number of times we have requested this card
		int output = -1;
		// if we have a set, and that set is 3 cards
		if(setRank!=-1 && countSetQTY(setRank)==3){
			// go through our turn history and see if the opponent has
			// drawn a card since we last asked for that rank
			for(Turn turn : this.turnHistory){
				// if the turn has been taken
				if(turn != null){
					// if the human took a turn and drew a card
					if(turn.drewCard() && turn.isHuman()){
						drewCounter++;
					}
					// if the turn was taken by a computer
					if(!turn.isHuman()){
						int rankRequested = turn.getRequested();
						// if we have requested this rank before
						if(rankRequested == setRank){
							countRequested++;
						}
						// if we have requested this card before, but opponent has drawn since
						// or if we haven't asked for that card before
						if((drewCounter>0 && countRequested>0)||countRequested <1){
							output = setRank;
						}
					}
				}
			} // end for Turn iterator
		// we have a set, but only a pair
		}else if(setRank!=-1){
			for(Turn turn : this.turnHistory){
				// if the turn has been taken
				if(turn != null){
					// if the human took a turn and drew a card
					if(turn.drewCard() && turn.isHuman()){
						drewCounter++;
					}
					// if the turn was taken by a computer
					if(!turn.isHuman()){
						int rankRequested = turn.getRequested();
						// if we have requested this rank before
						if(rankRequested == setRank){
							countRequested++;
						}
						// if we have requested this card before, but opponent has drawn since
						// or if we haven't asked for that card before
						if((drewCounter>0 && countRequested>0)||countRequested <1){
							output = setRank;
						}
					}
				}
			} // end for Turn iterator
		}else{
			// we have no sets, iterate through the most recent turns 
			for(int ii=0; ii<this.turnHistory.length; ii++){
				// look back 3 turns, if the turn has been taken
				if(turnHistory[ii] != null && ii<4){
					// and the turn is AI 
					if(!turnHistory[ii].isHuman()){
						// request a card that wasn't requested recently
						int rankRequested = turnHistory[ii].getRequested();
						Card[] cards = playerHand.getCards();
						for(int jj=0; jj<cards.length; jj++){
							if(cards[jj].getRank()!=rankRequested){
								output = cards[jj].getRank();
								
								break;
							}else{
								int num = cards.length-1;
								int ran = (int)(Math.random()*num);
								output = cards[ran].getRank();
								// output = cards[0].getRank();
							}
						}
					}
				}
			}
		}
		return output;
	} // end analyze hand
	
	public boolean hasCards(){
		if(playerHand.calcTotal() > 0){
			hasCards = true;
		}else{
			hasCards = false;
		}
		return hasCards;
	} // end hasCards()

	/**
	*Asks the other player for a card
	*/
	public boolean makeCardRequest(Player opponent, int desiredCard){
		this.opponent = opponent;
		opponentHasCard = false;

		//if the card is in the opponent's hand, then return true. otherwise, false.
		for(Card card : opponent.getHand().getCards()){
			if(card.getRank() == desiredCard){
				opponentHasCard = true;
				return opponentHasCard;
			}
		}

		return opponentHasCard;
	} // end makeCardRequest

	/**
	*Responds to an opponents request for a card.
	*@param desiredCard The rank of the set
	*@return count of the number of cards in a set
	*/
	public ArrayList<Card> respondCardRequest(int desiredCard){
				//create a holder arraylist for the found cards
		ArrayList<Card> foundCards = new ArrayList<Card>();

		//oldhand is a container for the players current hand
		Hand oldHand = playerHand;
		//newhand is a stack to place the cards not culled from the players hand into
		Stack<Card> newHand = new Stack<Card>();

		//go through each card in the hand
		for(int i=0; i<oldHand.getCards().length; i++){
			Card currentCard = oldHand.getCards()[i];
			//if the current card's rank isn't equal to the desired rank...
			if(currentCard.getRank() != desiredCard){
				//add that card to the new hand. it will not be given to the requesting player
				newHand.push(currentCard);
			}
			else{
				//otherwise, the card matches. add it to the arraylist keeping track of found cards
				foundCards.add(currentCard);
			}
		}
		// convert the new hand to a hand object, and make that the new player hand
		Card[] cardHolder = new Card[newHand.size()];
		for(int j=0; j<newHand.size(); j++){
			cardHolder[j] = newHand.get(j);
			System.out.println(cardHolder[j]);
		}
		Hand freshHand = new Hand(cardHolder);
		this.playerHand = freshHand;
		//return the arraylist
		return foundCards;
	} // end respondCardRequest()

	/**
	  * playFullSet
	  * @param the card which has just been added to the hand
	  * Used to remove a full set from the hand and increment the score
	  */
	private void playFullSet(int desiredCard){
		//display message
		System.out.println("The computer has a full set of " + Integer.toString(desiredCard) + "'s");
		//increment score
		this.currentScore++;
		//remove those cards from the hand
		playerHand.removeFullSet(desiredCard);
	} // end playFullSet()

	/**
	* Utility Function 
	*Checks to see the number of cards in a given set (think pair).
	*@param rankOfSet The rank of the set
	*@return count of the number of cards in a set
	*/
	public int countSetQTY(int rankOfSet){
		Card[] cards = playerHand.getCards();
		int numCards = cards.length;
		int counter = 0;
		for(int ii=0; ii<numCards; ii++){
			if (cards[ii].getRank() == rankOfSet){
				counter++;
			}
		}
		return counter;
	} // end countSetQty

	/**
	* Utility Function
	* checks our hand to see if there are any pairs/sets 
	* (if we have a pair we want to see if opponenet has any of that card)
	* @return the rank of the set or -1 if no set found
	*/
	public int hasSet(){
		// get our cards
		Card[] cards = playerHand.getCards();
		
		// number of cards to iterate over
		int numCards = cards.length;
		// array of ranks
		int[] rank = new int[numCards];
		int[] setPos = new int[numCards];

		for(int ii=0; ii<numCards; ii++){
			// for each of our cards, get the current card's rank
			int currentRank = cards[ii].getRank();
			rank[ii] = currentRank;
			for(int jj=0; jj<ii; jj++){
				// for each card, check all previous cards for a set
				if (rank[jj] == currentRank){
					// if there is a set get the positions of the set. 
					setPos[ii] = jj;
					return cards[ii].getRank();
				}
			}
		}
		return -1; 
	} // end hasSet()

	public Card[] getMyCompleteSets(){
		Card[] cards = new Card[1];
		return cards;
	} // end getMyCompleteSets()


	public void endTurn(){
	} // end endTurn()

	public int getCurrentScore(){
		return this.currentScore;
	}

}