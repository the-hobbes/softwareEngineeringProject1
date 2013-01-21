public class Game{
	Deck theDeck = new Deck()

	Hand[] players = new Hand[2];

	static final PLAYER = 0;

	static final COMPUTER = 1;

	int currentPlayer = 0;

	Interface ui = new Interface();
}