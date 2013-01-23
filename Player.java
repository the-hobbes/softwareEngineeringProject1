import java.util.ArrayList;

public interface Player{
	
	public Deck doTurn(Deck gameDeck,Player opponent);
	public boolean hasCards();
	/**
	*Asks the other player for a card
	*/
	public boolean makeCardRequest(Player opponent, int desiredCard);
	public ArrayList<Card> respondCardRequest(int desiredCard);
	public void endTurn();	
	public Card[] getMyCompleteSets();
}