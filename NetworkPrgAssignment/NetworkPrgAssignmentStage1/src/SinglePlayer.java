import java.util.Scanner;

public class SinglePlayer {
	private static Scanner keyboard = new Scanner(System.in);
	//private int playerSelection;
	
	public static void singleGame() throws Exception {
		Scanner kbdIn = new Scanner(System.in); 				 //Sets the keyboard as input for user
        System.out.println("Welcome to the Guess Game!");
        System.out.println("What is your name?");				 //Prompt for username
        String username = kbdIn.nextLine();						 //username is equal to user input
        String keepPlaying;
        
        
        boolean singleFlg = true;
        boolean keepFlg = true;
        
        while (singleFlg){
        	int serverRandom =(int) (Math.random() * 3);
        	int sum;
            int minus;
            int userNumber = -1;
            int userGuess = -1;
	        System.out.println("Hello " + username + ", I'm thinking of a number between 0 and 2. Can you" +
	        		" input a number between 0 and 2?");
	        
	        String userIptNumber = kbdIn.nextLine();
	        try {
	        	userNumber = Integer.valueOf(userIptNumber);
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Input");
				break;
			}
	        System.out.println("You are input is: " + userNumber);
	        sum = userNumber + serverRandom;
	        
	        
	        System.out.println("Plesse guess the sum of the 2 numbers");
	        String userIptGuess = kbdIn.nextLine();
	        try {
	        	userGuess = Integer.valueOf(userIptGuess);
			} catch (NumberFormatException nfe) {
				System.out.println("Invalid Input");
				break;
			}
	        minus = sum - userGuess;
	        
	        
	        if(Math.abs(minus) < 2){
	        	System.out.println("The number that server generat is: " + serverRandom);
	        	System.out.println("The number that user input is: " + userNumber);
	        	System.out.println("The sum bewteen these numbers is: " + sum);
	        	System.out.println("Congratulations! You Win!");
			}else{
				System.out.println("The number that server generat is: " + serverRandom);
	        	System.out.println("The number that user input is: " + userNumber);
	        	System.out.println("The sum bewteen these numbers is: " + sum);
				System.out.println("Sorry, you loss!");
			}
	        keepFlg = true;
	        
	        
	        while(keepFlg){
	        	System.out.print("Do you want to play again? Please input 'y' or 'n'.");
		        keepPlaying = keyboard.nextLine();
		        
		        if (keepPlaying.equals("y")){
		        	keepFlg = false;
		        	keepPlaying = null;
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
		// TODO Auto-generated method stub
		singleGame();
	}

}
