import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;


class ServerThread extends Thread {
	// Array list for socket
	public static ArrayList<Socket> mClients = new ArrayList<Socket>();
	// Array list for message
	private ArrayList<String> mMsgQueue = new ArrayList<String>();
	public static int clientNum = 0;
	public static Player[] player = new Player[30];
	
	private String usrName;
	private int generNum;
	private int guessNum;
	
	/*
	 * For add Client to socket
	 */
	public synchronized void addClient(Socket aClientSocket) throws IOException, InterruptedException {
		mClients.add(aClientSocket);
		clientNum++;
	}

	/*
	 * For delete client from socket
	 */
	public synchronized void deleteClient(Socket aClientSock) {
		int i = mClients.indexOf(aClientSock);
		if (i != -1) {
			mClients.remove(i);
			try {
				aClientSock.close();
				clientNum--;
			} catch (IOException ioe) {
			}
		}
	}

	/*
	 * For Play Game
	 */
	public synchronized void playGame(Socket aSocket, String aMsg) throws InterruptedException, IOException {
		// Server generate random number
		int serverRandom = (int) (Math.random() * 3);
		// split the message that from client
		String[] parts = aMsg.split("[*]");
		// Store these messages
		usrName = parts[0];
		generNum = Integer.valueOf(parts[1]);
		guessNum = Integer.valueOf(parts[2]);
		// Create 3 player result for calculate the result
		int player1 = -1;
		int player2 = -1;
		int player3 = -1;
		
		// If only have one player
		// It will ask finish input and waiting the second player.
		if (clientNum == 1){
			//Find a free player to store the data.
			if (player[1] == null){
				player[1] = new Player(usrName, generNum, guessNum, serverRandom);
				
			}else if(player[2] == null){
				player[2] = new Player(usrName, generNum, guessNum, serverRandom);
				
			}else{
				player[3]= new Player(usrName, generNum, guessNum, serverRandom);
				
			}
		// If have 2 players
		// Game run as 2 players module
		}else if (clientNum == 2){
			//Find a free player to store the data.
			if (player[1] == null){
				player[1] = new Player(usrName, generNum, guessNum, serverRandom);
				
			}else if(player[2] == null){
				player[2] = new Player(usrName, generNum, guessNum, serverRandom);
			
			}else{
				player[3]= new Player(usrName, generNum, guessNum, serverRandom);
				
			}
		// If have 3 players
		// Game run as 3 players module
		}else if (clientNum >= 3){
			//Find a free player to store the data.
			if (player[1] == null){
				player[1] = new Player(usrName, generNum, guessNum, serverRandom);
				
			}else if(player[2] == null){
				player[2] = new Player(usrName, generNum, guessNum, serverRandom);
				
			}else{
				player[3]= new Player(usrName, generNum, guessNum, serverRandom);
				
			}
		}
		
		aMsg = aMsg + "\n\r";
		mMsgQueue.add(aMsg);
		
		// 3 player's module
		// Calculate who is/are the winner.
		if (clientNum >= 3){
			
			if(player[1] != null && player[2] != null && player[3] != null){
				// Calculate 3 player's result.
				player1 = Math.abs((player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) - player[1].getGuess());
				player2 = Math.abs((player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) - player[2].getGuess());
				player3 = Math.abs((player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) - player[3].getGuess());	
			}
		// 2 Player's module
		// Calculate who is/are the winner.
		}else if (clientNum == 2){
			
			if (player[1] != null && player[2] != null){
				// Calculate 2 player's result.
				player1 = Math.abs((player[1].getGenerate() + player[2].getGenerate() + player[1].getSerNum()) - player[1].getGuess());
				player2 = Math.abs((player[1].getGenerate() + player[2].getGenerate() + player[1].getSerNum()) - player[2].getGuess());
			}
		// 1 Player's module
		// It will wait until the second player come in.
		}else if (clientNum == 1){
			if (player[1] != null){
				// Calculate 1 player's result.
				player1 = Math.abs((player[1].getGenerate() + player[1].getSerNum()) - player[1].getGuess());
			}
		}
		// Call function to Calculate who is/are the winner.
		if (clientNum >= 3){
			if(player[1] != null && player[2] != null && player[3] != null){
				checkWinnerForThree(player1, player2, player3);
			}
		// Call function to Calculate who is/are the winner.
		}else if(clientNum == 2){
			if(player[1] != null && player[2] != null){
				checkWinnerForTwo(player1, player2);
			}
		}
	}

	// Get the next message function
	private synchronized String getNextMsgFromQueue() throws InterruptedException {
		// If does not receive any message, waiting
		while (mMsgQueue.size() == 0)
			wait();
		// print the message and delete the message from the queue
		String msg = mMsgQueue.get(0);
		mMsgQueue.remove(0);
		//System.out.println("Msg is: " + msg);
		
		return msg;
	}

	// Send the notification to game clients.
	public synchronized void sendMsgToAllClients(String aMsg) {
		// Get the client's size
		// Using for loop to send all clients
		for (int i = 0; i < mClients.size(); i++) {
			Socket socket = mClients.get(i);
			try {
				java.io.OutputStream out = socket.getOutputStream();
				out.write(aMsg.getBytes());
				out.flush();
			} catch (IOException ioe) {
				deleteClient(socket);
			}
		}
	}
	
	// Send the notification to waiting list clients
	public synchronized void sendMsgToWaitClients(String aMsg) {
		// Get the client's size
		// Using for loop to send all clients
		for (int i = 3; i < mClients.size(); i++) {
			Socket socket = mClients.get(i);
			try {
				java.io.OutputStream out = socket.getOutputStream();
				out.write(aMsg.getBytes());
				out.flush();
			} catch (IOException ioe) {
				deleteClient(socket);
			}
		}
	}
	
	// Send the notification to current clients
	public synchronized void sendMsgToCurrClients(String aMsg) {
			// Get the client's number and send it.
			int currClientNum = mClients.size();
			Socket socket = mClients.get(currClientNum);
			try {
				java.io.OutputStream out = socket.getOutputStream();
				out.write(aMsg.getBytes());
				out.flush();
			} catch (IOException ioe) {
				deleteClient(socket);
			}
		
	}

	public void run() {
		try {
			while (true) {
				String msg = getNextMsgFromQueue();
				sendMsgToAllClients(msg);
			}
		} catch (InterruptedException ie) {
		}
	}
	
	// Function for Calculate the winner and log it to the file.
	public void checkWinnerForThree(int player1, int player2, int player3) throws IOException{
			// Generate the result file, and does not overwrite it.
			PrintWriter writer = new PrintWriter(new FileWriter("result.txt", true));
			// Store all information to the servers.
			writer.println("The player 1 information:");
			writer.println("Name: " + player[1].getName());
			writer.println("Generate Number: " + player[1].getGenerate());
			writer.println("Guess Number: " + player[1].getGuess());
			writer.println("The player 2 information:");
			writer.println("Name: " + player[2].getName());
			writer.println("Generate Number: " + player[2].getGenerate());
			writer.println("Guess Number: " + player[2].getGuess());
			writer.println("The player 3 information:");
			writer.println("Name: " + player[3].getName());
			writer.println("Generate Number: " + player[3].getGenerate());
			writer.println("Guess Number: " + player[3].getGuess());
			writer.println("The Server Generate Number: " + player[1].getSerNum());
			
		/*
		 * 3 Players module
		 * Calculate who is the winner, the situation are similar, just comment one situation.
		 */
		if (player1 == player2 && player2 == player3){
			String result = "Server Message: The winners' name are " + player[1].getName() +", " + player[2].getName() + ", " + player[3].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			// Send the result to game list
			sendMsgToAllClients(result);
			notify();
			// Send the welcome message to waiting list
			sendMsgToWaitClients(welCome);
			notify();
			// initial player;
			initPlayer();
			// Save the result, and then close wirter.
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player1 == player2 && player1 < player3){
			String result = "Server Message: The winners' name are " + player[1].getName() +", " + player[2].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player2 == player3 && player3 < player1){
			String result = "Server Message: The winners' name are " + player[2].getName() + ", " + player[3].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player1 == player3 && player3 < player2){
			String result = "Server Message: The winners' name are " + player[1].getName() +", " + player[3].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if (player1 < player2 && player1 < player3){
			String result = "Server Message: The winner's name is " + player[1].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player2 < player1 && player2 < player3){
			String result = "Server Message: The winner's name is " + player[2].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player3 < player1 && player3 < player2){
			String result = "Server Message: The winner's name is " + player[3].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[3].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
		}
	}
	
	// Function for Calculate the winner and log it to the file.
	public void checkWinnerForTwo(int player1, int player2) throws IOException{
		// Generate the result file, and does not overwrite it.
		PrintWriter writer = new PrintWriter(new FileWriter("result.txt", true));
		// Store all information to the servers.
		writer.println("The player 1 information:");
		writer.println("Name: " + player[1].getName());
		writer.println("Generate Number: " + player[1].getGenerate());
		writer.println("Guess Number: " + player[1].getGuess());
		writer.println("The player 2 information:");
		writer.println("Name: " + player[2].getName());
		writer.println("Generate Number: " + player[2].getGenerate());
		writer.println("Guess Number: " + player[2].getGuess());
		writer.println("The Server Generate Number: " + player[1].getSerNum());
		
		/*
		 * 2 Players module
		 * Calculate who is the winner, the situation are similar, just comment one situation.
		 */
		if (player1 == player2){
			String result = "Server Message: The winners' name are " + player[1].getName() +", " + player[2].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			// Send the result to game list
			sendMsgToAllClients(result);
			notify();
			// Send the welcome message to waiting list
			sendMsgToWaitClients(welCome);
			notify();
			// Initial player
			initPlayer();
			// Save the result to the server.
			writer.println(result);
			writer.println("");
			// close writer.
			writer.close();
			
		}else if (player1 < player2){
			String result = "Server Message: The winner's name is " + player[1].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}else if(player2 < player1){
			String result = "Server Message: The winner's name is " + player[2].getName() + "; The sum is: " + (player[1].getGenerate() + player[2].getGenerate() + player[1].getSerNum()) + "!";
			String welCome = "Welcome to the Game, You can play the game now!";
			sendMsgToAllClients(result);
			notify();
			sendMsgToWaitClients(welCome);
			notify();
			initPlayer();
			writer.println(result);
			writer.println("");
			writer.close();
			
		}
	}
	
	// Function for Initial player.
	public void initPlayer(){
		for (int i = 0; i <= 10; i++){
			player[i] = null;
		}
	}
	
	// Function for Initial connection array list
	public void initMclient(){
		for (int i = 0; i < mClients.size(); i++){
			mClients.remove(i);
		}
	}
	
	// Function for get how much clients connected
	public static int currClientNum(){
		int currClientnum = mClients.size();
		return currClientnum;
	}
	
	public static void startGame() throws InterruptedException{

	}
	
}
