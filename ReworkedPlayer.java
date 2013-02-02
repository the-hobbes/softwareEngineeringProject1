public class ReworkedPlayer{
	// request an in (rank)
	boolean active = false;
	int playerType; // 0 if human, 1 if AI

	public ReworkedPlayer(playerType){
		this.playerType = playerType;
	}

	public boolean isActive(){
		return this.active;
	}

	public void setActive(){
		this.active = true;
	}

	public boolean isHuman(){
		if (this.playerType == 0){
			return true;
		}
	}

	/**
	  * @param rankArray, an array of integers representing the hand
	  * Used to make the computer player decide what card to ask for. Called from doTurn() in ReworkedGame.
	  */
	private int getAIRequest(Int[] rankArray){

	}

	/**
   	* checks our hand to see if there are any pairs/sets 
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
}