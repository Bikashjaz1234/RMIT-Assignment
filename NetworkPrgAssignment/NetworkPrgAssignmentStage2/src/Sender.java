import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * Sender thread for send message to Server and client
 */
public class Sender extends Thread {
	private PrintWriter mOut;
	private String name;
	
	public Sender(PrintWriter aOut, String name) {
		mOut = aOut;
		this.name = name;
	}

	public void run() {
		try {
			// try to create a buffer reader
			BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in)
			);
			// Show game message, and rule
			System.out.println("Welcome to Guessing Game!");
			System.out.println("Rule:");
			System.out.println("1. The server generate a number 0 and 2");
			System.out.println("2. Every client will input a number between 0 and 2");
			System.out.println("3. You need to guess the sum of the input");
			System.out.println("4. The people who is the closest, he will win!");
			System.out.println("Please input the number that you generate.");
			// Set-up few variables for user's information
			int guessNumber = -1;
			int genNumber = -1;
			String sendMsg = null;
			// Flags for check user input is valid or not
			// If not, loop.
			boolean checkFlg = false;
			boolean checkNum = true;
			boolean generFlg = false;
			boolean guessFlg = false;
			
			while (!isInterrupted()) {
				// Check guessing is finished or not. If finished, show a message.
				if (guessFlg == true && generFlg==true){
					System.out.println("Server Message: You already finish input, Waiting.....!");
					break;
				// If not, read user's input
				}else{
					System.out.print("> ");
					String message = in.readLine();
					
					// User input generate Number
					if (GameClient.iptTime == 0 || GameClient.iptTime == 2){
						try{
							// Check user input is number or not.
							genNumber = Integer.valueOf(message);
							checkFlg = true;
						}catch (NumberFormatException nfe) {
							// If input is not a number, ask user input again
							System.out.println("Server Message: Invalid Input!");	
							System.out.println("Server Message: You Can only input number, and between 0 and 2!");
							System.out.println("Server Message: Please input again!");
							continue;
						}
						// Check the number
						// If greater than 2 or less than 2, invalid input.
						// Otherwise, set the flag is true.
						// Finish the generate number input.
						if (genNumber >= 0 && genNumber <= 2){
							checkNum = true;
						}else{
							System.out.println("Server Message: The number that you input should between 0 and 2!");
							System.out.println("Server Message: Please input again!");
							continue;
						}
						// If input generate number is valid and flag is valid
						// Ask user input guess number
						if (checkFlg == true && checkNum == true){
							
							System.out.println("**********************************");
							System.out.println("The number that you generate is: " + genNumber);
							System.out.println("This message only you are visable");
							System.out.println("**********************************");
							System.out.println("Please input the number that you guess. ");
							GameClient.iptTime++;
							generFlg = true;
						}
					// User input guess number
					}else if(GameClient.iptTime == 1 || GameClient.iptTime == 3){
						// For the guess the number
						try{
							// Check user input is number or not.
							guessNumber = Integer.valueOf(message);
							// Generate the message
							// The message include: name, generate number, guess number, and use * to split.
							sendMsg = name + "*" + genNumber + "*" + guessNumber + "*" + GameClient.iptTime;
							checkFlg = true;
							
						}catch (NumberFormatException nfe) {
							// If user input characters, show error message
							System.out.println("Server Message: Invalid Input");
							System.out.println("Server Message: You Can only input number!");
							System.out.println("Server Message: Please input again!");
							continue;
						}
						
						// If user input valid
						if (checkFlg == true && checkNum == true){
							// Show message
							System.out.println("**********************************");
							System.out.println("The number that you guess is: " + guessNumber);
							System.out.println("This message only you are visable");
							System.out.println("**********************************");
							guessFlg = true;
							// send to the server, and flush.
							mOut.println(sendMsg);
							mOut.flush();
						}
					}
				}
			}
			// Catch the error
		} catch (IOException ioe) {
			System.out.println("Cannot Create the buffer!");
		}
	}
}
