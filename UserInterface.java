/**
*@author Ethan Eldridge
*
*The interface for the user to interact with.
*/

import java.util.Scanner;
import java.util.InputMismatchException;

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
	*@param userHand The user's hand.
	*/
	public static void displayHand(Hand userHand){
		System.out.println(userHand);
	}

	/**
	*Function to accept user input from the main menu, returns true to play the game, false to quit.
	*@return True if the game should begin, false if the user wishes to quit
	*/
	public static boolean getMenuOption(){
		//Get user input

		//Handle help and credits

		//Handle quit or play

		//Handle errorful input
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
	public static int getCommand(Hand h){
		//While there is a card in the user's hand and they haven't chosen a card from it

		int rank = -1;
		//User input:
		Scanner scan = new Scanner(System.in);
		boolean valid = false;
		while(!valid && !h.isEmpty()){
			//Assumption of displayed the person hand already
			System.out.println("Which Card would you like to pick?");
			System.out.print(">");
			String in = scan.nextLine();
			Scanner internalScanner = new Scanner(in);
			try{
				rank = internalScanner.nextInt();
				if(rank < 11 && rank > 1){
					if(h.hasCard(rank)){
						valid = true;	
					}else{
						System.out.println("You must have the card in your hand to ask for it.");
					}
					
				}else{
					//It's not a true mismatch exception, but at the very least it will yell our error message easily.
					throw new InputMismatchException();
				}
			}catch(InputMismatchException ime){
				//Catch the exception of using a J,A,K,Q or anyhting other than an integer
				if(in.equals("J")){
					rank = 11;
					valid = true;
				}else if(in.toUpperCase().equals("A")){
					rank = 1;
					valid = true;
				}else if(in.toUpperCase().equals("K")){
					rank = 13;
					valid = true;
				}else if(in.toUpperCase().equals("Q")){
					rank = 12;
					valid = true;
				}else if(in.toUpperCase().equals("HELP")){
					displayHelp();
				}else if(in.toUpperCase().equals("CREDITS")){
					displayCredits();
				}else{
					//Nope illegal.
					System.out.println("Error: Invalid command given, please specifiy the rank of the card you desire\n"
									   +"(2,3,4,5,6,7,8,9,10,J,Q,K,A) or you may see the rules by typing 'help' or see\n"
									   +"the credits by typing 'credits.'\n"
									   );
				}
			}
		}

		//ask for the command (either a card number or a help)
		return rank;
	}

	public static void main(String[] args) {
		//Unit Tests for each function
		UserInterface.displayCredits();
		UserInterface.displayHelp();
		Hand testHand = new Hand(new Card[]{new Card("Hearts",3), new Card("Spades",1)});
		UserInterface.displayHand(testHand);
		System.out.println(UserInterface.getCommand(testHand));

	}

}