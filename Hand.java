/** 
 *@author Joshua Dickerson 
 * The Hand class represents an instance of a hand of cards
 * 
 * @param cards is an array of cards in this hand
 * @param numCards is the number of cards in this hand. 
 */
import java.util.*;
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

	/**
	*Returns the array of cards in the hand
	*@return Card[] of cards from the players hand
	*/
	public Card[] getCards(){
		return this.cards;
	}

	/**
	*Increases the size of the Card[] array by 1, and adds a new card to the end of the array
	*@param A card to be added to the hand
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
		CustomComparator customComparator= new CustomComparator();
		Arrays.sort(this.cards, customComparator);
		this.totalHand = this.calcTotal();
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

	/**
	* return a string representation of the hand
	*@return String representation fo the hand
	*/
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
	
	public static void main(String[] args){
		Deck deck = new Deck();
		deck.shuffle();
		Card[] cards = new Card[25];
		for(int i=0; i < 25; i++){
			cards[i] = deck.getTopCard();
		}
		Hand hand = new Hand(cards, 1);		
		hand.addCard(deck.getTopCard());
		System.out.println(hand.toString());
	}

	/**
	*CustomComparator
	*/
	static class CustomComparator implements Comparator<Card>{
		public int compare(Card a, Card b){
			return b.compareTo(a);
		}
	}
} // end Hand