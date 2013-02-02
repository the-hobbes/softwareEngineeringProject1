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

	public Hand(){
		this.numCards = 0;
		this.cards = new Card[0];
	}
	public Hand(Card[] cards){
		setCards(cards);
		this.numCards = cards.length;
		// this.totalHand = this.calcTotal();
	}

	public void setCards(Card[] cards){
		this.cards = cards;
		CustomComparator customComparator= new CustomComparator();
		Arrays.sort(this.cards, customComparator);
	}

	/**
	*Returns the array of cards in the hand
	*@return Card[] of cards from the players hand
	*/
	public Card[] getCards(){
		if(this.cards==null){
			System.out.println("HORRIBLY WRONG");
		}
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

	public void removeCard(){
		this.numCards--;
	}

	public Card takeCard(rank, suit){
		for(int ii=0; ii<cards.length; ii++){
			if (cards[ii].getRank()==rank && cards[ii].getSuit().equals("suit")){
				return cards[ii];
			}
		}

		return false;
	}

	/**
	  * containsFourOfAKind
	  * @author Phelan
	  * @param desiredCardRank
	  * @return a boolean indicating the presence of a full set
	  */
	public boolean containsFourOfAKind(int desiredCardRank){
		boolean contains = false;
		int numCardInstances = 0;
		for (Card card : this.cards) {
		    if (card.getRank() == desiredCardRank) { 
		        // found it
		        numCardInstances++;
		    }
		}
		if(numCardInstances == 4){
			contains = true;
		}
		return contains;
	}
	/**
	  * removeFullSet
	  * @author Phelan
	  * @param cardRank, the rank of the cards you wish to delete
	  * Used to remove a full set of specific cards from the cards array
	  */
	public void removeFullSet(int cardRank){
		//set up temporary holder for smaller array
		// Card[] tempCards = new Card[(cards.length-4)];
		// int nextIndex = 0;
		Stack<Card> tempCards = new Stack<Card>();

		//loop through current array, adding all elements not at the index to the temporary array
		for(int i = 0; i<this.cards.length; i++){
			// System.out.println(cards[i]);
			if(this.cards[i].getRank() != cardRank){
				tempCards.push(this.cards[i]);
			}
		}
		//copy the temporary array into the cards array
		// this.cards = tempCards;
		this.cards = new Card[tempCards.size()];
		this.cards = tempCards.toArray(this.cards);
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

	/**
	* return a string representation of the hand
	*@return String representation of the hand
	*/
	public String toString(){
		System.out.println(this.cards.length);
		String tmp = "";
		for(int ii=0; ii<this.cards.length; ii++){
			tmp += "Card";
			tmp += ii+1;
			tmp += ": ";
			tmp += this.cards[ii].toString();
			tmp += "\n";
			// System.out.println(tmp);
		}

		return tmp;
	} // end toString()

	// public Card[] getCardsByRank(int rank){
		
	// } // end getCardsByRank()
	
	public static void main(String[] args){
		Deck deck = new Deck();
		deck.shuffle();
		Card[] cards = new Card[25];
		for(int i=0; i < 25; i++){
			cards[i] = deck.getTopCard();
		}
		Hand hand = new Hand(cards);
		System.out.println(hand.toString());
	}

	/**
	*CustomComparator
	*@Author Scott MacEwan
	*Custom implementation of the Comparator class to use in sorting hte array
	*/
	static class CustomComparator implements Comparator<Card>{

		/**
		*Compares to cards to each other
		*@param Card to be compared
		*@param Other card to be compared
		*@return int to specify if a is less than b (-), a is greater than b (+), a is equal to b (0)
		*/
		public int compare(Card a, Card b){
			return b.compareTo(a);
		}
	}
} // end Hand