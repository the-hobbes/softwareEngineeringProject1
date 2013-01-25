/** 
 *@author Joshua Dickerson 
 * The Card class represents an instance of a playing card 
 * 
 * @param suit is the suit of this card object
 * @param rank is the rank of this card object
 * @param imgFilename is the filename of the graphic for the face of the card
 */

public class Card{

	private String suit;
	private int rank;

	/**
	 * default constructor of the Card class
	 * instantiates a card object with a suit and rank
	 * @param suit
	 * @param rank
	 */
	public Card(String suit, int rank){
		setSuit(suit);
		setRank(rank);
	}

	/**
	 * getRankTrad
	 * takes the numeric value representing rank and converts it to a "traditional" string
	* @return String the "traditional" name of this rank
	*/
	public String getRankTrad(){
		String rankTrad ="";
		switch (this.rank) {
			case 1:
            	rankTrad="Ace";
            break;
            case 11:
            	rankTrad="Jack";
            break;
            case 12:
            	rankTrad="Queen";
            break;
            case 13:
            	rankTrad="King";
            break;
            default:
            	rankTrad=this.rank+"";
            break; 
		}

		return rankTrad;
	} // end getRankTrad()

	/**
	 * getSuit
	 * returns the suit of the card object
	 * @return suit
	 */
	public String getSuit(){
		return this.suit;
	}

	/**
	 * setSuit
	 * sets the suit of the card object
	 * @param suit
	 */
	public void setSuit(String suit){
		this.suit = suit;
	}
	
	/**
	 * getRank
	 * returns the rank of the card object
	 * @return rank
	 */
	public int getRank(){
		return this.rank;
	}

	/**
	 * setRank
	 * sets the rank of the card object
	 * @param rank
	 */
	public void setRank(int rank){
		this.rank = rank;
	}

	/**
	 * toString
	 * returns a string representation of the card object
	 * @return tmp
	 */
	public String toString(){
		String tmp = this.getRankTrad()+" of "+this.getSuit();
		return tmp;
	}
} // end Card
