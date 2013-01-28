/**
 * TurnHistory
 * @author JoshuaDickerson
 * Class to maintain the history of each turn of the game
 */
public class Turn{
	public String name; // will either be human or AI
	public boolean drewCard; // did the player draw a card during this turn
	public int cardRequested; // what was the rank of the card requested

	public Turn(String name, boolean drewCard, int cardRequested){
		this.name = name;
		this.drewCard = drewCard;
		this.cardRequested = cardRequested;
	} // end Turn


	public boolean isHuman(){
		if(this.name.equals("human")||this.name.equals("Human")||this.name.equals("HUMAN")){
			return true;
		}else{
			return false;
		}
	} // end isHuman()

	public int getRequested(){
		return this.cardRequested;
	}

	public boolean drewCard(){
		return this.drewCard;
	}
}