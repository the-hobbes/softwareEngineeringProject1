/** 
 *@author Joshua Dickerson 
 * The Hand class represents an instance of a hand of cards
 * 
 * @param cards is an array of cards in this hand
 * @param numCards is the number of cards in this hand. 
 */
import java.util.*;

public class ReworkedHand{

	private ArrayList<Card> cards; // our hand
	private int numCards;

	public Hand(Card[] initCards){
		for(int ii=0; ii<initCards.length; ii++){
			this.cards.add(initCards[ii]);
		}
	}

	/**
	*Returns the array of cards in the hand
	*@return Card[] of cards from the players hand
	*/
	public ArrayList<Card> getCards(){
		return this.cards;
	}

	public boolean hasCard(int cno){
		for(int ii=0; ii<this.cards.size(); ii++){
			if (this.cards.get(ii).getRank() == cno){
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
		this.cards.add(addedCard);
	}

	/**
	* inspects to see if there are any cards left in our hand. 
	* @return true if the length of the cards array is 0
	*/
	public boolean isEmpty(){
		if (this.cards.isEmpty()){
			return true;
		}else{
			return false;
		}
	}


	public int getNumCards(){
		return this.cards.size();
	}

	/**
	* return a string representation of the hand
	*@return String representation of the hand
	*/
	public String toString(){
		String tmp = "";
		for(int ii=0; ii<this.cards.size(); ii++){
			tmp += "Card";
			tmp += ii+1;
			tmp += ": ";
			tmp += this.cards.get(ii).toString();
			tmp += "\n";
			}
		}
		return tmp;
	} // end toString()

	public static void main(String[] args){
	}

} // end Hand