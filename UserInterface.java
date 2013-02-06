/**
*@author Ethan Eldridge
*
*The interface for the user to interact with.
*/

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
public class UserInterface{
	
	/**
	*Display the people who made this game
	*/
	public static void displayCredits(){
		String out = "//=================================CREDITS====================================\\\\ \n\n"
				   + "This fantastic Go Fish game was created by these wonderful people:\n\n"
                   + "*Phelan Vendeville <phelan.vendeville@gmail.com>\n"
                   + "*Joshua Dickerson <joshuajdickerson@gmail.com>\n"
                   + "*Danielle Steimke <dsteimke325@gmail.com>\n"
                   + "*Ethan Eldridge <ejayeldridge@gmail.com>\n"
                   + "*Scott MacEwan <wsmacewan101@gmail.com>\n\n"
                   +"Team Lead:\n Phelan Vendeville\n";

        System.out.println(out);
	}
	
	/**
	*Displays the current person hand
	*@param userHand The user's hand.
	*/
	public static void displayHand(Hand userHand){
		System.out.println(userHand);
	}

	public static void displayMainMenu(){
		String men= "=================================================================\n"
		       	  + "= OOOOOOOO OOOOOOOO         OOOOOOOO OOOOOOOO OOOOOOOO OO    OO =\n"
		          + "= OO    OO OO    OO         OO          OO    OO       OO    OO =\n"
		          + "= OO   ___ OO    OO         OOOOO       OO    OOOOOOOO OOOOOOOO =\n"
		          + "= OO    OO OO    OO         OO          OO          OO OO    OO =\n"
		          + "= OOOOOOOO OOOOOOOO         OO        OOOOOOO OOOOOOOO OO    OO =\n"
		          + "=================================================================\n"
		          + "=     [P]lay        [C]redits        [H]elp          [Q]uit     =\n"
		          + "=================================================================\n";
		System.out.println(men);
	}

	/**
	*Function to accept user input from the main menu, returns true to play the game, false to quit.
	*@return True if the game should begin, false if the user wishes to quit
	*/
	public static boolean getMenuOption(){
		//The user inputs can be the following:
		//P for play game, H for help, C for credits, Q for quit
		//full names like play,help,credits,quit will all work as well.

		//Get user input
		for (int i = 0; i < 2; ++i) System.out.println();
		
		Scanner scan = new Scanner(System.in);
		boolean valid = false;
		while(!valid){
			displayMainMenu();
			String in = scan.nextLine();
			if(in.toUpperCase().equals("P") || in.toUpperCase().equals("PLAY")){
				return true;
			}else if(in.toUpperCase().equals("H") || in.toUpperCase().equals("HELP")){
				displayHelp();
			}else if(in.toUpperCase().equals("C") || in.toUpperCase().equals("CREDITS")){
				displayCredits();
				//Extra newline to make it look almost like the screen refreshed
				System.out.println("\n");
			}else if(in.toUpperCase().equals("Q") || in.toUpperCase().equals("QUIT")){
				valid = true;
			}else{
				//Tell them they suck
				System.out.println("The command you entered is not recognized, please enter P,H,C or Q.");
			}
			//Display the menu text.
		}

		//Handle errorful input
		return false;
	}

	/**
	*Display the rules and helpful information to the user
	*/
	public static void displayHelp(){
		String rules = "//=====================================HELP===================================\\\\ \n\n"
					 + "The rules of Go Fish:\n"
					 + "1) 5 Cards are delt to the players\n"
					 + "2) Players take turns asking if their opponent has a card of the same rank as one of their own\n"
					 + "3) The opponent must hand over all cards of the requested rank, if the opponent has no cards of "
					 + "that rank, they instruct the player to \"Go Fish\"\n"
					 + "4) If the player has been told to \"Go Fish\" then they draw a card from the deck and their turn is over. "
					 + "If the player recieved a card from their opponent, then it is still their turn and they ask again until they"
					 + " are told to \"Go Fish\"."
					 + "5) When any player has 4 cards of the same rank, they reveal these cards, remove them from their hand, and recieve a point.\n"
					 + "6) The game ends when all cards are discarded or a player runs out of cards. The player with the highest score wins!\n";
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
			System.out.println("What rank would you like to pick? (Type h or help for help)");
			System.out.print(">");
			String in = scan.nextLine();
			Scanner internalScanner = new Scanner(in);
			try{
				rank = internalScanner.nextInt();
				if(h.hasCard(rank)){
					if(rank < 14 && rank > 0){
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
				if(in.toUpperCase().equals("J") || in.toUpperCase().equals("JACK")){
					rank = 11;
				}else if(in.toUpperCase().equals("A") || in.toUpperCase().equals("ACE")){
					rank = 1;
				}else if(in.toUpperCase().equals("K") || in.toUpperCase().equals("KING")){
					rank = 13;
				}else if(in.toUpperCase().equals("Q") || in.toUpperCase().equals("QUEEN")){
					rank = 12;
				}else if(in.toUpperCase().equals("HELP") || in.toUpperCase().equals("H")){
					displayHelp();
				}else if(in.toUpperCase().equals("CREDITS") || in.toUpperCase().equals("C")){
					displayCredits();
				}else{
					//Nope illegal.
					rank = -1;
				}
				if(h.hasCard(rank)){
					valid = true;
				}else{
					System.out.println("You must have the card in your hand to ask for it.");
				}
			}catch(NoSuchElementException nsee){
				System.out.println("Error: Invalid command given, please specifiy the rank of the card you desire\n"
									   +"(2,3,4,5,6,7,8,9,10,J,Q,K,A) or you may see the rules by typing 'help' or see\n"
									   +"the credits by typing 'credits.'\n"
									   );
			}
		}

		//ask for the command (either a card number or a help)
		return rank;
	}

	// public static void contentFrame(String content){
	// 	// System.out.format("%-5s:%10s\n", content, "");
	// }

	public static void main(String[] args) {
		//Unit Tests for each function
		UserInterface.displayCredits();
		UserInterface.displayHelp();
		Hand testHand = new Hand(new Card[]{new Card("Hearts",3), new Card("Spades",1)});
		UserInterface.displayHand(testHand);
		System.out.println(UserInterface.getCommand(testHand));
		UserInterface.getMenuOption();
	}

}