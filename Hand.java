/** 
 *@author Joshua Dickerson 
 * The Hand class represents an instance of a hand of cards
 * 
 * @param cards is an array of cards in this hand
 * @param numCards is the number of cards in this hand. 
 */

public class Hand{

	public Card[] cards; // our hand
	public int numCards;

	/**
	*Creates an instance of Hand
	*@param cards The cards to have in the hand.
	*/
	public Hand(Card[] cards){
		setCards(cards);
		this.numCards = cards.length;
	}

	/**
	*Sets this Hand's cards to be the passed in cards
	*
	*/
	public void setCards(Card[] cards){
		this.cards = cards;
	}

	public Card[] getCards(){
		return this.cards;
	}


	/**
	* increases the size of the Card{] array by 1, and adds a new card to the end of the array
	*/
	public void addCard(Card addedCard){
		int sizeOldArray = cards.length;
		Card[] tempHand = cards;
		Card[] newHand = new Card[sizeOldArray+1];
		newHand[sizeOldArray+1] = addedCard;
		this.cards = newHand;
	}

	/**
	* inspects to see if there are any cards left in our hand. 
	* @return true if the length of the cards array is 0
	*/
	public boolean isEmpty(){
		if (this.cards.length < 1){
			return true;
		}else{
			return false;
		}
	}

	/**
	* removes all the cards from our hand
	*/
	public void clear(){
		Card[] emptyHand = new Card[0];
		this.cards = emptyHand;
	}

	public String toString(){
		String tmp = "";
		for(int ii=0; ii<this.cards.length; ii++){
			tmp += "Card";
			tmp += ii+1;
			tmp += ": ";
			tmp += this.cards[ii].getRank();
			tmp += " of ";
			tmp += this.cards[ii].getSuit();
			tmp += "\n";
			// System.out.println(tmp);
		}

		return tmp;
	} // end toString()
	
} // end Hand
