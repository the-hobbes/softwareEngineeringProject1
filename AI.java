import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;  
import java.util.Stack;


public class AI implements Player{

	private boolean deckHasCards = true;
	private Hand hand;
	private Card[] completedSets = new Card[13];
	// holds all of the opponent's previous requests so we know what they might have in their hand
	private int[] cardsRequestedByOpponent = new int[52];
	private int requestCounter = 0; 

	// a main() function has been included to
	// allow for testing
	public static void main(String[] args) {
		Deck deck = new Deck();
		deck.shuffle();
		Card[] cards = new Card[5];

		for(int ii=0; ii<5; ii++){
			cards[ii] = deck.getTopCard(); 
		}

		Hand hand = new Hand(cards);
		System.out.println(hand);
		// System.out.println("_____");

		AI comp = new AI(hand);

		// System.out.println(comp.hasSet());
		if(comp.hasSet() >= 1){
			int requestRank = comp.hasSet();
			int setQTY = comp.countSetQTY(requestRank);
			System.out.println("Number of elements in our set: "+setQTY);

		}
	}

	public AI(Hand hand){
		this.hand = hand;
	}

	public Hand getHand(){
		return this.hand;
	}

	public Deck doTurn(Deck gameDeck, Player opponent){
		// check if there are any pairs or triples
		// in hand
		// if(this.hasSet() >= 1){
		// 	// there is a set, get it's rank
		// 	int requestRank = this.hasSet();
		// 	// get the size of the set
		// 	int setQTY = this.countSetQTY(requestRank);
		// 	if(setQTY == 4){
		// 		// there is a full set
		// 	}else{
		// 	boolean opponentHasCard = makeCardRequest(opponent);
		// 	}
		// }
		return gameDeck;
	}
	
	public boolean hasCards(){
		return true;
	}

	/**
	*Asks the other player for a card
	*/
	public boolean makeCardRequest(Player opponent, int rank){
		return true;
	}

	/**
	*Responds to an opponents request for a card.
	*@param desiredCard The rank of the set
	*@return count of the number of cards in a set
	*/
	public ArrayList<Card> respondCardRequest(int desiredCard){
		this.cardsRequestedByOpponent[requestCounter] = desiredCard;
		requestCounter++;
		// loop through our hand and count the number of instances 
		// of the requested card

		if(hand.hasCard(desiredCard)){
			int counter = 0;
			for(int ii=0; ii<hand.getNumCards(); ii++){
				if 
			}
		}
		ArrayList<Card> cardsToReturn = new ArrayList<Card>(3);
		// Card card = new Card("test",1);
		return cardsToReturn;
	}

	

	/**
	*Checks to see the number of cards in a given set (think pair).
	*@param rankOfSet The rank of the set
	*@return count of the number of cards in a set
	*/
	public int countSetQTY(int rankOfSet){
		Card[] cards = hand.getCards();
		int numCards = cards.length;
		int counter = 0;
		for(int ii=0; ii<numCards; ii++){
			if (cards[ii].getRank() == rankOfSet){
				counter++;
			}
		}

		return counter;
	}

	/**
	* checks our hand to see if there are any pairs/sets 
	* (if we have a pair we want to see if opponenet has any of that card)
	* @return the rank of the set or -1 if no set found
	*/
	public int hasSet(){
		// get our cards
		Card[] cards = hand.getCards();
		
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
	}

	public Card[] getMyCompleteSets(){
		Card[] cards = new Card[1];
		return cards;
	}


	public void endTurn(){

	}

}