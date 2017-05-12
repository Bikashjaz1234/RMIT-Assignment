import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ChatClient {
	// Which server that it connect
	public static final String SERVER_HOSTNAME = "m1-c23n1.csit.rmit.edu.au";
	// Which port that if connect
	public static final int SERVER_PORT = 7777;

	public static void chatRoom(String name) {
		// Create reader and printer for send and print message
		BufferedReader in = null;
		PrintWriter out = null;
		Socket socket = null;
		
		// Try to connect the server, and create buffer
		try {
			socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);
			in = new BufferedReader(
				new InputStreamReader(socket.getInputStream())
			);
			out = new PrintWriter(
				new OutputStreamWriter(socket.getOutputStream())
			);
			// Show message if connect chat server.
			System.out.println("Connect to Chat server " + SERVER_HOSTNAME + ":" + SERVER_PORT);

		} catch (IOException ioe) {
			// If cannot connect the chat server, show error message
			System.out.println("Cannot connect to Chat server " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			System.out.println("Please use follow command to start Communication Server");
			System.out.println("Command: java ChatServer");
			System.exit(1);
		}
		// Create sender for send message
		ChatSender sender = new ChatSender(out, name);
		sender.start();

		try {
			String message;
			while ((message = in.readLine()) != null) {

				message = message.replaceAll("(\\r|\\n)", "");
				// if user input something, show message
				if (message.length() != 0) {
					System.out.println(message);
					System.out.print("> ");
				// otherwise, continue;
				}else{
					continue;
				}

			}
		} catch (IOException ioe) {
			System.err.println("Error happened!");
		}
	}
}
