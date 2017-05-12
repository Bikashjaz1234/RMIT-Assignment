import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * Listener Thread for Chat
 * It is similar with ClientListener
 */
public class ChatClientListener extends Thread {

	// Create socket and variables
	private Socket mSocket;
	private ChatServerThread mDispatcher;
	private BufferedReader mSocketReader;
	PrintStream os = null;
	public static String name;

	public ChatClientListener(Socket aSocket, ChatServerThread aServerMsgDispatcher) throws IOException {
		mSocket = aSocket;
		mSocketReader = new BufferedReader(
			new InputStreamReader(mSocket.getInputStream())
		);
		mDispatcher = aServerMsgDispatcher;
	}

	public void run() {
		try {
			// Read the message
			while (!isInterrupted()) {
				String msg = mSocketReader.readLine();
				if (msg == null) {
					// if message is null, break
					break;
				}
				mDispatcher.dispatchMsg(mSocket, msg);
			}
		} catch (IOException ioex) {
			System.err.println("Error Happened!");
		}
		mDispatcher.deleteClient(mSocket);
	}
}

