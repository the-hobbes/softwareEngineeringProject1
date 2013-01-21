public interface Player{
	
	public Deck doTurn(Deck gameDeck,Player opponent);
	
	public boolean hasCards();

	/**
	*Asks the other player for a card
	*/
	public boolean makeCardRequest(Player opponent);

	public void endTurn();

}