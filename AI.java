public class AI implements Player{

	private boolean deckHasCards = true;
	private Hand hand;

	public static void main(String[] args) {
		
	}

	public AI(Hand hand){
		this.hand = hand;
	}

	public Deck doTurn(Deck gameDeck, Player opponent){
		// check if there are any pairs or triples
		// in hand
		return gameDeck;
	}
	
	public boolean hasCards(){
		return true;
	}

	/**
	*Asks the other player for a card
	*/
	public boolean makeCardRequest(Player opponent){
		return true;
	}

	public void endTurn(){

	}

	/**
	* checks our hand to see if there are any pairs/sets
	* @return the rank of the set
	* should be made to identify differences between pairs and triples
	* not there yet 
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




}