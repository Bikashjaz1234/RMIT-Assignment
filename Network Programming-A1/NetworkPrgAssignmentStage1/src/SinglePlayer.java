import java.util.Scanner;

public class SinglePlayer {
	private static Scanner keyboard = new Scanner(System.in);
	//For Single Player Game
	
	public static void singleGame() throws Exception {
		Scanner kbdIn = new Scanner(System.in); 				 //Sets the keyboard as input for user
        System.out.println("Welcome to the Guess Game!");
        System.out.println("What is your name?");				 //Prompt for username
        String username = kbdIn.nextLine();						 //username is equal to user input
        String keepPlaying;
        // Set few flags for loop check user's input
        boolean singleFlg = true;
        boolean keepFlg = true;
        boolean guessFlg = true;
        //Friendly Information. Show user's name, and the rule of game.
        System.out.println("Hello " + username + "!");
        System.out.println("Rule:");
        System.out.println("1. The server will generate a number between 0 to 2");
        System.out.println("2. You will input a number between 0 and 2");
        System.out.println("3. Then, you need to guess these 2 number's sum");
        System.out.println("4. You win if the difference between the guessed number and the actual sum is less than two.");
        System.out.println("Game Start");
        
        // Use while loop to keep game running, until user exit.
        while (singleFlg){
        	// Server generate a random number.
        	int serverRandom =(int) (Math.random() * 3);
        	int sum;
            int minus;
            // init user input number.
            int userNumber = -1;
            int userGuess = -1;
	        
	        System.out.println("Please input a number between 0 and 2");
	        // Get user input
	        String userIptNumber = kbdIn.nextLine();
	        try {
	        	// Try transfer the input to integer, if can, user input valid, otherwise, invalid.
	        	userNumber = Integer.valueOf(userIptNumber);
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Input");
				continue;
			}
	        // Check the user input number, if bigger than 2 or less than 0, is not valid.
	        if (userNumber < 0 || userNumber > 2){
	        	System.out.println("Invalid Input");
	        	System.out.println("You can only input number beetween 0 to 2");
	        	continue;
	        }
	        System.out.println("You are input is: " + userNumber);
	        sum = userNumber + serverRandom;
	        
	        // Set a loop for check user input
	        while(guessFlg){
	        	System.out.println("Plesse guess the sum of the 2 numbers");
		        String userIptGuess = kbdIn.nextLine();
		    // Try transfer the input to integer, if can, user input valid, otherwise, invalid.
		        try {
		        	// If valid, stop the loop.
		        	userGuess = Integer.valueOf(userIptGuess);
		        	guessFlg = false;
				} catch (NumberFormatException nfe) {
					System.out.println("Invalid Input");
					continue;
				}
	        }
	        // Calculate minus
	        minus = sum - userGuess;
	        
	        // if the difference between the guessed number and the actual sum is less than two, user win.
	        // use abs to make sure it always greater than 0.
	        if(Math.abs(minus) < 2){
	        	System.out.println("The number that server generat is: " + serverRandom);
	        	System.out.println("The number that user input is: " + userNumber);
	        	System.out.println("The sum bewteen these numbers is: " + sum);
	        	System.out.println("Congratulations! You Win!");
	        // Else loss
			}else{
				System.out.println("The number that server generat is: " + serverRandom);
	        	System.out.println("The number that user input is: " + userNumber);
	        	System.out.println("The sum bewteen these numbers is: " + sum);
				System.out.println("Sorry, you loss!");
			}
	        keepFlg = true;
	        
	        // User can choose Keep playing or not.
	        while(keepFlg){
	        	System.out.print("Do you want to play again? Please input 'y' or 'n'.");
		        keepPlaying = keyboard.nextLine();
		        
		        // If input y, keep playing
		        if (keepPlaying.equals("y")){
		        	keepFlg = false;
		        	guessFlg = true;
		        	keepPlaying = null;
		        // if input n, stop
		        }else if(keepPlaying.equals("n")){
		        	System.out.print("Bye!");
		        	keepFlg = false;
		        	singleFlg = false;
		        	keepPlaying = null;
		        }else{
		        	System.out.print("Please input 'y' or 'n'.");
		        	keepPlaying = null;
		        }
	        }
        }
        
        
        
	}
	
	public static void main(String[] args) throws Exception {
		// Main for start the game
		singleGame();
	}

}
