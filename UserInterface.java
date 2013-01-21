/**
*@author Ethan Eldridge
*
*The interface for the user to interact with.
*/

public class UserInterface{
	
	/**
	*Display the people who made this game
	*/
	public static void displayCredits(){
		String out = "//=================================CREDITS====================================\\\\"
				   + "This fantastic Go Fish game was created by these wonderful people:\n\n"
                   + "*Phelan Vendeville <phelan.vendeville@gmail.com>\n"
                   + "*Joshua Dickerson <joshuajdickerson@gmail.com>\n"
                   + "*Danielle Steimke <dsteimke325@gmail.com>\n"
                   + "*Ethan Eldridge <ejayeldridge@gmail.com>\n"
                   + "*Scott MacEwan <wsmacewan101@gmail.com>\n\n"
                   +"Team Lead:\n Phelan Venderville\n";

        System.out.println(out);
	}
	
	/**
	*Displays the current person hand
	*/
	public static void displayHand(Hand userHand){

	}

	/**
	*Display the rules and helpful information to the user
	*/
	public static void displayHelp(){
		String rules = "//=====================================HELP===================================\\\\"
					 + "The rules of Go Fish:\n"
					 + "1) 5 Cards are delt to the players\n"
					 + "2) Players take turns asking if their opponent has a card of the same rank as one of their own\n"
					 + "3) The opponent must hand over all cards of the requested rank, if the opponent has no cards of "
					 + "that rank, they instruct the player to \"Go Fish\"\n"
					 + "4) If the player has been told to \"Go Fish\" then they draw a card from the deck and their turn is over. "
					 + "If the player recieved a card from their opponent, then it is still their turn and they ask again until they"
					 + " are told to \"Go Fish\"."
					 + "5) When any player has 4 cards of the same rank, they reveal these cards, remove them from their hand, and recieve a point.\n"
					 + "6) The game ends when all cards are discarded or a player runs out of cards. The player with the highest score wins!\n\n";
		System.out.println(rules);
	}

	/**
	*Get the user input and handle it
	*@return Returns the card the user asked for (-1) if the user has no cards left
	*/
	public static int getCommand(Hand userHand){
		//While there is a card in the user's hand and they haven't chosen a card from it

		//ask for the command (either a card number or a help)

		return -1;
	}

	public static void main(String[] args) {
		//Unit Tests for each function
		UserInterface.displayCredits();
		UserInterface.displayHelp();
	}

}