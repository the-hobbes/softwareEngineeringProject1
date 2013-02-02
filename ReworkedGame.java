import java.util.*;

public class ReworkedGame{
	Deck deck = new Deck;
	ReworkedHand AIHand;
	ReworkedHand HumanHand;
	ReworkedPlayer human = new ReworkedPlayer(0); // 0 for human
	ReworkedPlayer ai = new ReworkedPlayer(1);
	UserInterface ui = new UserInterface;
	
	

	public ReworkedGame(){
		this.deck.shuffle();
		Card[] AICards = new Card[5];
		Card[] HumanCards = new Card[5];
		for(int ii=0; ii<HumanCards.length; ii++){
			HumanCards[ii] = this.get.getTopCard();
			AICards[ii] = this.get.getTopCard();
		}
		this.AIHand = new Hand(AICards);
		this.HumanHand = new Hand(HumanCards);
	}

	public boolean playGame(){
		
		ReworkedPlayer activePlayer = this.getActivePlayer();
		while(this.doTurn()){
		}

		return false;
	}
		// return -1 if the player went out of cards
	public boolean doTurn(){
		ReworkedPlayer activePlayer = this.getActivePlayer();
		ReworkedPlayer inactivePlayer = this.getInactivePlayer();
		// display the hand
		activePlayer.displayHand(this.getActiveHand(activePlayer));
		// if human: get card request
		if(activePlayer.isHuman()){
			// have the player make the request
			int rankRequested = ui.getMenuOption(this.getActiveHand(activePlayer));
		}else{
			// we want to send in an int representation of the ranks in the 
			// AIs hand so that it can make intelligent decisions
			Hand tmpHand = this.getActiveHand(activePlayer);
			Card[] tmpCards = tmpHand.getCards();
			int[] rankArray = new int[tmpCards.length];
			for(int ii=0; ii<tmpCards.length; ii++){
				rankArray[ii] = tmpCards[ii].getRank();
			}
			int rankRequested = activePlayer.getAIRequest(rankArray);
		}

		// use rankRequested to either select the card from the opposing players hand
		if(handContainsRequestedCard(this.getInactiveHand(activePlayer))){
			// the opponents hand has the card
			// are there more than one to get?
			int setQTY = countSetQTY(this.getInactiveHand(activePlayer), rankRequested);
			if(setQTY > 1){
				// if more than one card, get them all
				for(int ii=0; ii<setQTY; ii++){
					// add them to my hand
					Card tmpCard = this.getCardFromHand(this.getInactiveHand(activePlayer), rankRequested);
					this.addCardToHand(this.getActiveHand(activePlayer), tmpCard);
				}
			}else{
				// opponent only had one card, add it to our hand
				Card gotCard = getSingleCardFromHand(this.getInactiveHand(activePlayer), rankRequested);
			}
		}else{
			// the card we requested was not in the opponents hand, draw a card
			this.addCardToHand(this.getActiveHand(activePlayer), this.deck.getTopCard());
		}

		if(this.getActiveHand(activePlayer).isEmpty()){
			return false;
		}else{
			return true;
		}
	} // end doTurn()

	public boolean handContainsRequestedCard(Hand targetHand, int rank){
		Cards tmpCard[] = targetHand.getCards();
		int counter = 0;
		for (int ii=0; ii<tmpCard.length; ii++){
			if (tmpCard[ii].getRank() == rank){
				return true;
			}
		}
		return false;
	}

	public Card getCardFromHand(Hand targetHand, int rank){
		ArrayList<Card> tmpCard[] = targetHand.getCards();
		for (int ii=0; ii<tmpCard.length; ii++){
			if (tmpCard[ii].getRank() == rank){
				return tmpCard[ii];
			}
		}
		return false;
	}

	public void addCardToHand(Hand targetHand, Card addCard){

	}

	public ReworkedPlayer getActivePlayer(){
		if(this.human.isActive()){
			return this.human;
		}else{
			return this.ai;
		}
	}

	public Hand getActiveHand(ReworkedPlayer player){
		if (player.isHuman()){
			return this.HumanHand;
		}else{
			return this.AIHand;
		}
	}

	public ReworkedPlayer getInactivePlayer(){
		if(this.human.isActive()){
			return this.ai;
		}else{
			return this.human;
		}
	}

	public Hand getInactiveHand(ReworkedPlayer player){
		if (player.isHuman()){
			return this.AIHand;
		}else{
			return this.HumanHand;
		}
	}

	public int countSetQTY(Hand targetHand, int rankOfSet){
		Card[] cards = targetHand.getCards();
		int numCards = cards.length;
		int counter = 0;
		for(int ii=0; ii<numCards; ii++){
			if (cards[ii].getRank() == rankOfSet){
				counter++;
			}
		}
		return counter;
	} // end countSetQty

}