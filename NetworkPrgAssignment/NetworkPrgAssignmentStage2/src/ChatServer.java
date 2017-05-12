import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	// Set the Chat port
	public static final int LISTENING_PORT = 7777;

	public static void main(String[] args) throws IOException {
		// Create a socket for Communication.
		ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);
		// Show socket's information
		System.out.println("Chat Server Start! Server's Port is: " + serverSocket.getLocalPort());
		// Create a thread, and run it.
		ChatServerThread chatThread = new ChatServerThread();
		chatThread.start();

		while (true) {
			// Create socket for client
			Socket clientSocket = serverSocket.accept();
			ChatClientListener clientListener = new ChatClientListener(clientSocket, chatThread);
			// Add client to the server
			chatThread.addClient(clientSocket);
			clientListener.start();
		}
	}
}

