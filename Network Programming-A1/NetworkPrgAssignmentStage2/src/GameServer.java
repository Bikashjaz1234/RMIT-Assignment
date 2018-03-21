import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
	// Set the game port
	public static final int LISTENING_PORT = 6666;
	// Set the Max Game number
	public static int MAX_GAME = 3;
	public static int MAX_CLIENT = 5;

	public static void main(String[] args) throws IOException, InterruptedException {
		// Create a socket for Game.
		ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);
		// Show socket's information
		System.out.println("Game Server start! Server's port is: " + serverSocket.getLocalPort());
		// Create a thread, and run it.
		ServerThread gameThread = new ServerThread();
		gameThread.start();
		

		while (true) {
				// Create socket for client
				Socket clientSocket = serverSocket.accept();
				// Create printer and reader buffer for read and send message
				PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				// Check How much people in the Waiting list
				int connClient = ServerThread.mClients.size() + 1;
				// If More than 3 people, show message
				// Tell user that the need to wait.
				// If more than 5 people, deny the six connection
				if (connClient > MAX_CLIENT){
					toClient.println("Sorry, this is a small server, this server can only handle 5 clients. Please try again later!");
				}else if (connClient > MAX_GAME && connClient <= MAX_CLIENT){
					toClient.println("There already has 3 people in the game, you need to wait until they finish the game! You are the " + (ServerThread.mClients.size() + 1) + " people!");
				}else{
					// Else show the queue number.
					toClient.println("Welcome to the Game! You are the " + (ServerThread.mClients.size() + 1) + " people!");
				}
				ClientListener clientListener = new ClientListener(clientSocket, gameThread);
				gameThread.addClient(clientSocket);
				clientListener.start();

		}
		
	}
}

