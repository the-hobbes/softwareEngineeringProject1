/** 
 *@author Joshua Dickerson 
 * The Deck class represents an instance of a Deck of cards 
 * 
 * @param String currCard is the value of the rank/suit at the top of the deck. 
 * @param String nextCard is the value of the rank/suit of the next card in the deck. 
 * @param String prevCard is the value of the rank/suit of the last card pulled from the deck.
 * @param Card topCard is the Card object on the top of the deck. 
 * @param Card bottomCard is the Card object on the bottom of the deck. 
 * @param Stack cardCollection is the deck. 
 * 
 */

import java.util.*;

public class Deck{

	private String currCard;
	private String prevCard;
	private String nextCard;
	private Card topCard;
	private String bottomCard;
	public Stack<Card> cardCollection = new Stack<Card>();
	public Stack<Card> usedCards = new Stack<Card>();
	public Card[] cardArray;
	private String[] suit = { "Hearts", "Diamonds", "Clubs", "Spades"}; 
	private int[] rank = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13}; 

	public Deck(){
		this.cardArray = new Card[rank.length*suit.length];
		buildDeck();
	}

	/**
	* composes a deck of cards by implementing a stack
	* and filling it with new card objects
	* populated with  our suit and rank arrays
	* also builds an array of the same cards
	*/
	private void buildDeck(){
		int counter = 0;
		for(int ii=0; ii<suit.length; ii++){
			for(int jj=0; jj<rank.length; jj++){
				Card newCard = new Card(suit[ii], rank[jj]);
				this.cardCollection.add(newCard);
				this.cardArray[counter] = newCard;
				counter++;
			}
		}
	}

	/**
	* uses the Stack .peek() method to
	* @return the top card from the deck without removing it from the deck
	*/
	public Card peekTopCard(){
		Card top = cardCollection.peek();
		return top; 
	}

	/**
	* uses the Stack .pop() method to
	* @return the top card from the deck, removing it from the deck
	*/
	public Card getTopCard(){
		Card top = cardCollection.pop();
		this.usedCards.add(top);
		return top; 
	}

	/**
	* uses the Collectiosn .shuffle() method to
	* randomize (shuffle) the cards in the deck
	*/
	public void shuffle(){
		Collections.shuffle(cardCollection);
	} // end shuffle


	/**
	* checks to see if there are any cards left in the deck
	*@return true is deck is empty, false if there are still cards
	*/
	public boolean isEmpty(){
		boolean empty = false;
		if(cardCollection.empty()){
			empty = true;
		}
		return empty;
	}

	/**
	* uses the Stack pop() method to display (and empty) the remaining deck
	* @return a concatenated string of all the cards left in the deck
	*/
	public String toString(){
		String tmp = "";
		int counter = 1;
		while(! cardCollection.empty()){
			Card tempCard = this.getTopCard();
			tmp += "Card ";
			tmp += counter;
			tmp += ": ";
			tmp += tempCard.toString();
			tmp += "\n";
			counter++;
		}

		this.cardCollection = this.usedCards;
		this.usedCards = new Stack<Card>();


		return tmp;
	} // end toString()

	
} // end Deck
