/**
  * Player, a java interface
  * @author Ethan Eldridge Joshua Dickerson, Phelan Vendeville
  * Used to coordinate and standardize methods between player classes (AI and human player)
  */

import java.util.ArrayList;

public interface Player{
	
	public Deck doTurn(Deck gameDeck,Player opponent);
	public boolean hasCards();
	public boolean makeCardRequest(Player opponent, int desiredCard);
	public ArrayList<Card> respondCardRequest(int desiredCard);
	public void endTurn();	
	public Card[] getMyCompleteSets();
	public Hand getHand();
	public int getCurrentScore();
}