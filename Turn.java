/** 
* Class to maintain the history of each turn of the game
* @author JoshuaDickerson
* @version Build 9000 Feb 3, 2013.
*/
public class Turn{
	/** string holds either ai or human */
	public String name; // will either be human or AI  --- this person
	/** int holds the rank of the card requested */
	public int cardRequested; // what was the rank of the card requested -- requested a card
	/** boolean indicating whether we drew a card as a result of the opponent not having the requested card */
	public boolean drewCard; // did the player draw a card during this turn  -- opponent did or did not have the card
	/** 
	* constructor
	* @param string - name of player (either human or ai)
	* @param boolean - true if opponent di not have card requested, and we drew a card
	* @param int - numerical rank of card requested
	*/
	public Turn(String name, boolean drewCard, int cardRequested){
		this.name = name;
		this.drewCard = drewCard;
		this.cardRequested = cardRequested;
	} // end Turn

	/**
	* boolean indicating whether the active player was human
	* @return boolean
	*/
	public boolean isHuman(){
		if(this.name.equals("human")||this.name.equals("Human")||this.name.equals("HUMAN")){
			return true;
		}else{
			return false;
		}
	} // end isHuman()
	/**
	* @return int value of the rank of the card requested by the active player
	*/
	public int getRequested(){
		return this.cardRequested;
	}
	/**
	* boolean indicating whether the active player drew a card or not
	* @return boolean
	*/
	public boolean drewCard(){
		return this.drewCard;
	}
	/**
	* boolean indicating whether the active player drew a card or not
	* @param int rank of the card to return a traditional value of (jac of clubs VS 11 of clubs)
	* @return boolean
	*/
	public String getRankTrad(int rank){
		String rankTrad ="";
		switch (rank){
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
				rankTrad=rank+"";
			break;
		}
		return rankTrad;
	} // end getRankTrad()

	public String toString(){
		String str;
		String opponent;
		if(this.name.equals("ai")){
			opponent = "human";
		}else{
			opponent = "ai";
		}
		if(this.drewCard){
			str = " did NOT have the card. ";
		}else{
			 str = " DID have the card";
		}
		return "\n"+this.name+" requested a "+getRankTrad(this.cardRequested)+". "+opponent+" "+str;
	}
}