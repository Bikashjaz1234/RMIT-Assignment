import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/*
 * Game Client Class
 */
public class GameClient {
	// Which server that it connect
	public static final String SERVER_HOSTNAME = "m1-c23n1.csit.rmit.edu.au";
	// Which port that if connect
	public static final int SERVER_PORT = 6666;
	// Variables for Store player information
	public static int iptTime = 0;
	public static Player[] player;
	static int generate = 0;
	static int guess = 0;
	static int msgLen = 0;
	static int chkClient = -1;
	// Flag for loop menu, if user does not input valid information
	static boolean regFlag = true;
	
	
	public static void main(String[] args) throws IOException, InterruptedException {
		// Create reader and printer for send and print message
		BufferedReader in = null;
		PrintWriter out = null;
		// Create socket
		Socket socket = null;
		//ServerThread.clientNum++;
	   
	    // Try to connect the server
		try {
			socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			in = new BufferedReader(
				new InputStreamReader(socket.getInputStream())
			);

			out = new PrintWriter(
				new OutputStreamWriter(socket.getOutputStream())
			);
			
			// Show success message
			System.out.println("Connect to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			
		// Show error message
		} catch (IOException ioe) {
			System.err.println("Cannot connect Game to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			System.exit(1);
		}
		// Variable for get user input name
		String name = null;;
		String usrIpt;
		// Variable for get user input menu number
		int menuIpt = -1;
		// Get server message
		String messageChk = in.readLine();
		messageChk = messageChk.replaceAll("(\\r|\\n)", "");
		msgLen = messageChk.length();
		// If message exist, show the message
		if (messageChk.length() != 0){
			System.out.println(messageChk);
		}
//		if (messageChk.length() == 93){
//			System.out.println("Bye! See you later.");
//			socket.close();
//			System.exit(0);
//		}
		if (messageChk.equals(Validation.DENY_MESSAGE)){
			System.out.println("Bye! See you later.");
			socket.close();
			System.exit(0);
		}
		
		//Loop for menu
		while(regFlag){
			System.out.println("Game Menu");
			System.out.println("1. Register");
			System.out.println("2. Begin Game");
			System.out.println("3. Exit");
			// Get user input
			Scanner ins = new Scanner(System.in);
			usrIpt = ins.nextLine();
			// Check user input is number or not.
			menuIpt = Validation.validMenu(usrIpt);
			
			// Switch for loop
			switch(menuIpt){
			case 1:
				// User register, get user's name
				Scanner ipt = new Scanner(System.in);
				System.out.print("Please input your name: ");
				name = ipt.nextLine();
				break;
				
			case 2:
				// If user set the name and less than 3 people playing the game.
				// begin the game
				if (name != null && msgLen == 42){
					ServerThread.startGame();
					// send the name to server side.
					Sender sender = new Sender(out, name);
					sender.start();
					try {
						// The game will run on the server side.
						// Get the server's message, and output.
						String message;
						while ((message = in.readLine()) != null) {
							message = message.replaceAll("(\\r|\\n)", "");
							if (message.length() == 0) {
								continue;
							}
							iptTime++;
							//Check Game finished or not
							//If finished, Show the Winner, and move current client to the communication room.
							//So that, the new player can begin the game.
							if (message.contains("!") && message.length() != 47){
								String[] parts = message.split("!");
								System.out.println(parts[0]);
								System.out.println("Game finished! Now, you are in the Comunication Room, you can chat with your friends!");
								System.out.println("If you want to Quit, please input 'exit'!");
								// Close socket
								socket.close();
								// Move to chat room
								ChatClient.chatRoom(name);
							// If not, show next step.
							}else if(message.contains("!") && message.length() == 47){
								String[] parts = message.split("!");
								System.out.println(parts[0]);
							}
							
							System.out.print("> ");
						}
					// Handle the error.
					} catch (IOException ioe) {
						System.err.println("Error Happened.");
					}
					break;
				// If the player more than 3 people, go the the waiting lest.
				// The player cannot play the game, player must be waiting, until the previous game finished.
				// Once finished, the game will begin automatically.
				}else if (name != null && msgLen == 106){
					// Show message
					// The process is similar with previous one.
					System.out.println("The game is not finish, please waiting!");
					// Read Server Message
					String welcomeChk = in.readLine();
					welcomeChk = welcomeChk.replaceAll("(\\r|\\n)", "");
					if (welcomeChk.contains("!")){
						String[] welParts = welcomeChk.split("!");
						if (welParts[1].length() == 46){
							iptTime = 0;
							// Send the name to server
							Sender sender = new Sender(out, name);
							sender.start();
							try {
								// Read the message from Server.
								String message;
								while ((message = in.readLine()) != null) {
									message = message.replaceAll("(\\r|\\n)", "");
									if (message.length() == 0) {
										continue;
									}
									iptTime++;
									
									//Check game finished our not
									if (message.contains("!")){
										String[] parts = message.split("!");
										System.out.println(parts[0]);
										System.out.println("Game finished! Now, you are in the Comunication Room, you can chat with your friends!");
										System.out.println("If you want to Quit, please input 'exit'!");
										// Close socket
										socket.close();
										// Move to chat room
										ChatClient.chatRoom(name);
									}

									System.out.print("> ");
								}
							} catch (IOException ioe) {
								System.err.println("Error Happened.");
							}
						}
					}
					break;
				// If do not register game, show error message, ask user register game.
				}else{
					menuIpt = -1;
					System.out.println("You do not register.");
					System.out.println("");
					break;
				}
			// user input 3, stop the game
			case 3:
				System.out.println("Bye Bye!");
				System.exit(0);
			// User input others, invalid input.
			default:
				menuIpt = -1;
				System.out.println("Invalid input!");
				System.out.println("");
				break;
			}
		}
	}

}


