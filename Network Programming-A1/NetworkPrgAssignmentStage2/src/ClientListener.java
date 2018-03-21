import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/*
 * Listener Thread for Game
 * It is similar with ChatClientListener
 */
public class ClientListener extends Thread {

	// Create socket and variables
	private Socket mSocket;
	private ServerThread mGameThread;
	private BufferedReader mSocketReader;
	PrintStream os = null;
	public static String name;

	public ClientListener(Socket aSocket, ServerThread aServerGame) throws IOException {
		mSocket = aSocket;
		mSocketReader = new BufferedReader(
			new InputStreamReader(mSocket.getInputStream())
		);
		mGameThread = aServerGame;
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				// Read the message
				String msg = mSocketReader.readLine();
				// if message is null, break
				if (msg == null) {
					break;
				}
				mGameThread.playGame(mSocket, msg);
			}
		} catch (IOException ioex) {
			System.err.println("Loss connection for a client");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mGameThread.deleteClient(mSocket);
	}
}

