import java.util.ArrayList;
import java.util.Iterator;
import java.util.Arrays;  
import java.util.Stack;
import java.math.*;


public class AI implements Player{
	/** does the deck have cards left? */
	private boolean deckHasCards = true;
	/** this player's hand of cards */
	private Hand playerHand;
	/** collection of turns, represents the turn history */
	public ArrayList<Turn> turnHistory = new ArrayList<Turn>();
	/** the opponent player object */
	private Player opponent;
	/** the game deck */
	private Deck gameDeck;
	/** this players current score */
	public int currentScore;
	/** does my hand contain cards? */
	private boolean hasCards;
	/** does our opponent have the card we're looking for? */
	private boolean opponentHasCard;

	/** 
	* Constructor
	* @param hand - initial hand dealt from dec
	*/
	public AI(Hand hand){
		this.playerHand = hand;
	} // end AI

	/**
	* returns this player's hand
	* @return Hand
	*/
	public Hand getHand(){
		return this.playerHand;
	} // end getHand()

	/**
	* Constitutes a single turn for one player
	* @param Deck - the only deck in play
	* @param Player - the opponent whose hand we will request cards of
	* @return Deck - Game deck
	*/
	public Deck doTurn(Deck gameDeck, Player opponent){
		this.gameDeck = gameDeck;
		this.opponent = opponent;

		// System.out.println("-------  Computer's Turn -------");
		UserInterface ui = new UserInterface();
		ui.contentFrame("this is content");
		// System.out.println(this.playerHand);
		ArrayList<Card> foundCards = new ArrayList<Card>();

		// analyze hand inspects the state of the hand, and previous turns, and returns an
		// int indicating the rank of the desired card 
		int desiredCard = this.analyzeHand();
<<<<<<< HEAD
		try{
			Thread.sleep(2000);
		}catch(InterruptedException e){
			System.out.println("Got interupted by another thread!?!?!?!");
		}
		System.out.println("\n Computer requests a "+desiredCard);
=======

		System.out.println("\n Computer requests a "+getRankTrad(desiredCard));
>>>>>>> 2829e3485a3cf07eeb14fe096b3a466754cfb0d0
		
		//if the opponent has no cards in their hand, the game is over
		if(desiredCard == -1){
			//call the endgame functions
		}
		
		//request all the cards of desired type from the opponent
		if (makeCardRequest(opponent, desiredCard)){
			//get all instances of that card from the opponent (removing them from the opponent's hand as well)
			foundCards = opponent.respondCardRequest(desiredCard);
			//add them to your hand
			for(Card card : playerHand.getCards()){
				foundCards.add(card);
			}
			Card[] tempCard = new Card[foundCards.size()];
			for(int i=0; i<tempCard.length; i++){
				tempCard[i] = foundCards.get(i);
			}
			Hand newplayerhand = new Hand(tempCard);
			this.playerHand = newplayerhand;
			//check for a full set of cards in your hand
			boolean isFullSet = playerHand.containsFourOfAKind(desiredCard);
			//play the full set down, if there are any
			if(isFullSet){
				playFullSet(desiredCard);
			}
			//call doTurn() again
			if (!playerHand.isEmpty())
			{
				if(!gameDeck.isEmpty())
					this.gameDeck = doTurn(this.gameDeck, opponent);
			}
		}
		//the opponent doesn't have the card, and the player must go fish
		else if(! this.gameDeck.isEmpty()){
<<<<<<< HEAD
=======
			System.out.println("Go fish!");
>>>>>>> 2829e3485a3cf07eeb14fe096b3a466754cfb0d0
			//remove the top card from the deck
			Card drawnCard = this.gameDeck.getTopCard();
			//add that card to your hand
			this.playerHand.addCard(drawnCard);
			//check for the presence of a full set
			boolean isFullSet = playerHand.containsFourOfAKind(drawnCard.getRank());
			//play that full set if there is one
			if(isFullSet){
				playFullSet(drawnCard.getRank());
			}
			//if the card pulled from the deck is the one asked for, call doTurn()
			if(drawnCard.getRank() == desiredCard){
				if (!playerHand.isEmpty()){
					if(!gameDeck.isEmpty()){
						this.gameDeck = doTurn(this.gameDeck, opponent);
					}
				}
			}
		}
		else{
			System.out.println("no cards left in the deck!");
		}
		return this.gameDeck;
	} // end doTurn()

	/**
	* Analyzes the computer's hand
	*@return rank of desired card
	*/
	private int analyzeHand(){
		int output = -1;
		Card[] cards = this.playerHand.getCards();
		int strat3Result = strat3(cards);
		int strat2Result = strat2(cards);
		int strat1Result = strat1(cards);
		// each strategy consists of more and more restrictive conditions
		// if both strat3 and strat1 result in a true, strat1 overrides strat3
		if(strat3Result != -1){
			output = strat3Result;
		}

		if(strat2Result != -1){
			output = strat2Result;
		}

		if(strat1Result != -1){
			output = strat1Result;
		}

		if(output == -1){
			// System.out.println("\n all else has failed ---> go random \n");
			int ranIndex = (int)(Math.random()*cards.length);
			output = cards[ranIndex].getRank();
		}


		return output;
	} // end analyze hand
	
	/**
	* If AI has a set of 3, has our opponent drawn from the deck since we last asked 
	* for the rank of the set, if so request the set rank
	* @param Card[] - array of card objects
	* @return int - reccomended rank of request to be made
	*/
	public int strat1(Card[] cards){
		// for our first strategy, we want to see if we have 3 of a kind
		// but we want to make sure not to ask for the same card over and over
		int setRank = this.hasSet();
		int output = -1;
		int drewCounter = 0;
		int humanCounter = 0;
		int countRequested = 0;
		// if we have a set, and that set is 3 cards
		if(setRank!=-1 && countSetQTY(setRank)==3){
			System.out.println("Computer has triple");
			// if we asked for the same card 3 times in a row we want to ask for a different card
			int histCounter = 0;
			// go through our turn history and see if the opponent has
			// drawn a card since we last asked for a card 3 times in a row ask for a different card
			for(Turn turn : this.turnHistory){
				// if the turn has been taken
				// if(turn != null){ <-- we know they're not null cuz we moved to an ArrayList
				// if the human took a turn and drew a card
				if(turn.drewCard() && turn.isHuman()){
					drewCounter++;
				}
				// if the turn was taken by a computer
				if(!turn.isHuman()){
					int rankRequested = turn.getRequested();
					// if we have requested this rank before
					if(rankRequested == setRank){
						countRequested++;
					}
					// if we have requested this card before, but opponent has drawn since
					// or if we haven't asked for that card before
					if((drewCounter>0 && countRequested>0)||countRequested <1){
						if(this.turnHistory.size() > 4){
							for(int ii=3; ii > -1; ii--){
								if(this.turnHistory.get(ii).getRequested() == setRank && this.turnHistory.get(ii).isHuman()){
									histCounter++;
								}
							}
						}
						output = setRank;
					}
				}
				// }
			} // end for Turn iterator
		}else{
			output = -1;
		}
		return output;
	} // end strat1()

	/**
	* If AI has a set of 2, has our opponent drawn from the deck since we last asked 
	* for the rank of the set, if so request the set rank
	* @param Card[] - array of card objects
	* @return int - reccomended rank of request to be made
	*/
	public int strat2(Card[] cards){
		int setRank = this.hasSet();
		int output = -1;
		int drewCounter = 0;
		int countRequested = 0;

		if(setRank!=-1){
			// System.out.println("\n Computer has a pair \n");
			int histCounter = 0;
			// for each turn
			if (!turnHistory.isEmpty()){
				for(Turn turn : this.turnHistory){
					// System.out.println(turn);
					// if the turn has been taken
						// if the human took a turn and drew a card
					if(turn.drewCard() && turn.isHuman()){
						drewCounter++;
					}
					// if the turn was taken by a computer
					if(!turn.isHuman()){
						int rankRequested = turn.getRequested();
						// if we have requested this rank before
						if(rankRequested == setRank){
							countRequested++;
						}
						// if we have requested this card before, but opponent has drawn since
						// or if we haven't asked for that card before
						if((drewCounter>0 && countRequested>0)||countRequested <1){
							if(this.turnHistory.size() > 3){
								for(int ii=2; ii > -1; ii--){
									if(this.turnHistory.get(ii).getRequested() == setRank && this.turnHistory.get(ii).isHuman()){
										histCounter++;
									}
								}
							}
						}
					}
				} // end for Turn iterator
			}else{
				output = setRank;
			}
		}else{
			output = -1;
		}
		return output;
	} // end strat2

	/**
	* If AI has no sets, if opponent has requested a card that we now have, request that card of them
	* @param Card[] - array of card objects
	* @return int - reccomended rank of request to be made
	*/
	public int strat3(Card[] cards){
		int setRank = this.hasSet();
		int output = -1;
		int drewCounter = 0;
		int countRequested = 0;

		if (!this.turnHistory.isEmpty()){
			// System.out.println("(inside strat3) We have taken turns \n");
			// we have no sets, iterate through the most recent turns 
			for(int ii=0; ii<this.turnHistory.size(); ii++){
				int rankRequested = turnHistory.get(ii).getRequested();
				// look back 3 turns, if the turn has been taken
				if(turnHistory.get(ii) != null && ii<4){
					// and the turn is AI 
					if(!turnHistory.get(ii).isHuman()){
						// request a card that wasn't requested recently
						for(int jj=0; jj<cards.length; jj++){
							if(cards[jj].getRank()!=rankRequested){
								output = cards[jj].getRank();
								break;
							}else{
								int ranIndex = (int)(Math.random()*cards.length);
								output = cards[ranIndex].getRank();
								// output = cards[0].getRank();
							}
						}
					}
				}
			}
		}else{
			output = -1;
		}
		return output;
	}

	/**
	* Does our hand contain cards?
	* @return boolean
	*/
	public boolean hasCards(){
		if(playerHand.calcTotal() > 0){
			hasCards = true;
		}else{
			hasCards = false;
		}
		return hasCards;
	} // end hasCards()

	/**
	* Mechanisim for requesting card of opponent's hand.
	* @param Player - opponent player object
	* @param int - rank of the card desired of opponent
	* @return boolean - player had card requested?
	*/
	public boolean makeCardRequest(Player opponent, int desiredCard){
		Turn singleTurn;
		this.opponent = opponent;
		opponentHasCard = false;
		Hand opHand = opponent.getHand();
		//if the card is in the opponent's hand, then return true. otherwise, false.
		for(Card card : opponent.getHand().getCards()){
			if(card == null){
				// System.out.println("MY GOD");
			}
			if(card.getRank() == desiredCard){
				opponentHasCard = true;
			}
		}
		if(opponentHasCard){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Got interupted by another thread!?!?!?!");
			}
			System.out.println("You had the card");
			singleTurn = new Turn("ai", false, desiredCard);
		}else{
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Got interupted by another thread!?!?!?!");
			}
			System.out.println("You did not have the card, Go Fish");
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				System.out.println("Got interupted by another thread!?!?!?!");
			}
			singleTurn = new Turn("ai", true, desiredCard);
		}
		this.turnHistory.add(singleTurn);
		return opponentHasCard;
	} // end makeCardRequest

	/**
	*Responds to an opponents request for a card.
	*@param desiredCard The rank of the set
	*@return count of the number of cards in a set
	*/
	public ArrayList<Card> respondCardRequest(int desiredCard){
		Turn singleTurn;
		//create a holder arraylist for the found cards
		ArrayList<Card> foundCards = new ArrayList<Card>();

		//oldhand is a container for the players current hand
		Hand oldHand = this.playerHand;
		//newhand is a stack to place the cards not culled from the players hand into
		Stack<Card> newHand = new Stack<Card>();

		//go through each card in the hand
		for(int i=0; i<oldHand.getCards().length; i++){
			Card currentCard = oldHand.getCards()[i];
			//if the current card's rank isn't equal to the desired rank...
			if(currentCard.getRank() != desiredCard){
				//add that card to the new hand. it will not be given to the requesting player
				newHand.push(currentCard);
			}
			else{
				//otherwise, the card matches. add it to the arraylist keeping track of found cards
				foundCards.add(currentCard);
			}
		}

		// convert the new hand to a hand object, and make that the new player hand
		Card[] cardHolder = new Card[newHand.size()];
		for(int j = 0; j < newHand.size(); j++){
			cardHolder[j] = newHand.get(j);
		}
		Hand freshHand = new Hand(cardHolder);
		this.playerHand = freshHand;
		if(foundCards.size() == 0){
			singleTurn = new Turn("human", true, desiredCard);
		}else{
			singleTurn = new Turn("human", false, desiredCard);
		}

		// this.turnHistory.add(singleTurn);
		//return the arraylist
		return foundCards;
	} // end respondCardRequest()

	/**
	* playFullSet
	* @param the card which has just been added to the hand
	* Used to remove a full set from the hand and increment the score
	*/
	private void playFullSet(int desiredCard){
		//display message
		// System.out.println("The computer has a full set of " + Integer.toString(desiredCard) + "'s");
		//increment score
		this.currentScore++;
		//remove those cards from the hand
		playerHand.removeFullSet(desiredCard);
	} // end playFullSet()

	/**
	* Utility Function 
	*Checks to see the number of cards in a given set (think pair).
	*@param rankOfSet The rank of the set
	*@return count of the number of cards in a set
	*/
	public int countSetQTY(int rankOfSet){
		Card[] cards = playerHand.getCards();
		int numCards = cards.length;
		int counter = 0;
		for(int ii=0; ii<numCards; ii++){
			if (cards[ii].getRank() == rankOfSet){
				counter++;
			}
		}
		return counter;
	} // end countSetQty

	/**
	* Utility Function
	* checks our hand to see if there are any pairs/sets 
	* (if we have a pair we want to see if opponenet has any of that card)
	* @return the rank of the set or -1 if no set found
	*/
	public int hasSet(){
		// get our cards
		Card[] cards = playerHand.getCards();
		
		// number of cards to iterate over
		int numCards = cards.length;
		// array of ranks
		int[] rank = new int[numCards];
		int[] setPos = new int[numCards];

		for(int ii=0; ii<numCards; ii++){
			// for each of our cards, get the current card's rank
			int currentRank = cards[ii].getRank();
			rank[ii] = currentRank;
			for(int jj=0; jj<ii; jj++){
				// for each card, check all previous cards for a set
				if (rank[jj] == currentRank){
					// if there is a set get the positions of the set. 
					setPos[ii] = jj;
					return cards[ii].getRank();
				}
			}
		}
		return -1;
	} // end hasSet()
	/**
	* Get AI complete sets of cards
	* @return Card[] - array of card objects (one complete set of a given rank)
	*/
	public Card[] getMyCompleteSets(){
		Card[] cards = new Card[1];
		return cards;
	} // end getMyCompleteSets()

	/**
	* Called at the end of a player's turn 
	*/
	public void endTurn(){
	} // end endTurn()

	/**
	* Get AI current score 
	* @return int - score of player, 1 point for each full set
	*/
	public int getCurrentScore(){
		return this.currentScore;
	}

	/**
	* @param int - rank of the card to convert to 'traditional' ranks (jack vs 11)
	* @return string - string representation of traditional rank
	*/
	public String getRankTrad(int rank){
		String rankTrad ="";
		switch (rank) {
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



}