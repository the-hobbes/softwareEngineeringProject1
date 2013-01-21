/** 
 *@author Joshua Dickerson 
 * The Hand class represents an instance of a hand of cards
 * 
 * @param cards is an array of cards in this hand
 * @param numCards is the number of cards in this hand. 
 */

public class Hand{

	private Card[] cards; // our hand
	private int numCards;
	private int totalHand;
	public int id;

	public Hand(Card[] cards, int id){
		setCards(cards);
		this.id = id;
		this.numCards = cards.length;
		this.totalHand = this.calcTotal();
	}

	public void setCards(Card[] cards){
		this.cards = cards;
	}

	public Card[] getCards(){
		return this.cards;
	}

	public boolean hasCard(int cno){
		for(Card c : cards){
			if(c.getRank() == cno){
				return true;
			}
		}
		return false;
	}

	/**
	* increases the size of the Card{] array by 1, and adds a new card to the end of the array
	*/
	public void addCard(Card addedCard){
		int sizeOldArray = cards.length;
		Card[] tempHand = this.cards;
		Card[] newHand = new Card[sizeOldArray+1];
		for(int jj=0; jj<sizeOldArray; jj++){
			newHand[jj] = tempHand[jj];
		}
		newHand[sizeOldArray] = addedCard;
		this.cards = newHand;
		this.totalHand = this.calcTotal();
	}

	public void removeCard(){
		this.numCards--;
	}

	public int calcTotal(){
		int total = 0;
		for(int ii=0; ii<this.cards.length; ii++){
			total += this.cards[ii].getRank();
		}
		return total;
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

	public int getNumCards(){
		return this.numCards;
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