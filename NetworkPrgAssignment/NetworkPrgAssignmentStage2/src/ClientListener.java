import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class ClientListener extends Thread {

	private Socket mSocket;
	private ServerThread mDispatcher;
	private BufferedReader mSocketReader;
	PrintStream os = null;
	public static String name;

	public ClientListener(Socket aSocket, ServerThread aServerMsgDispatcher) throws IOException {
		mSocket = aSocket;
		mSocketReader = new BufferedReader(
			new InputStreamReader(mSocket.getInputStream())
		);
		mDispatcher = aServerMsgDispatcher;
	}

	public void run() {
		try {
			while (!isInterrupted()) {
				String msg = mSocketReader.readLine();
				if (msg == null) {
					break;
				}
				mDispatcher.dispatchMsg(mSocket, msg);
			}
		} catch (IOException ioex) {
			System.err.println("Erro ao conectar-se ao um dos clientes");
		}
		mDispatcher.deleteClient(mSocket);
	}
}

