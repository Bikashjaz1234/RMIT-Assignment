import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/*
 * Sender thread for send message to Server and client
 */
public class ChatSender extends Thread {
	private PrintWriter mOut;
	private String name;
	private String messageNew;

	public ChatSender(PrintWriter aOut, String name) {
		mOut = aOut;
		this.name = name;
	}

	public void run() {
		try {
			// try to create a buffer reader
			BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in)
			);

			while (!isInterrupted()) {
				System.out.print("> ");
				// read the message
				String message = in.readLine();
				// If user input exit, exit the game.
				if (message.equals("exit")){
					System.exit(0);
				}
				// New message for add user name to the message
				messageNew = "*" + name + "* said: " + message;
				messageNew = messageNew.replaceAll("(\\r|\\n)", "");
				message = messageNew;
				// Send the message
				mOut.println(message);
				mOut.flush();
			}
		} catch (IOException ioe) {
		}
	}
}
