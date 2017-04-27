import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {

	public static final int LISTENING_PORT = 6666;

	public static void main(String[] args) throws IOException {

		ServerSocket serverSocket = new ServerSocket(LISTENING_PORT);
		System.out.println("Iniciando o servidor na porta " + serverSocket.getLocalPort());
		ServerThread dispatcher = new ServerThread();
		dispatcher.start();

		while (true) {
			Socket clientSocket = serverSocket.accept();
			ClientListener clientListener = new ClientListener(clientSocket, dispatcher);
			dispatcher.addClient(clientSocket);
			clientListener.start();
		}
	}
}

