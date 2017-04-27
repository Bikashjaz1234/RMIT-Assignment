import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class ChatClient {
	public static final String SERVER_HOSTNAME = "localhost";
	public static final int SERVER_PORT = 6666;

	public static void main(String[] args) {
		BufferedReader in = null;
		PrintWriter out = null;
		Socket socket = null;

		try {
			socket = new Socket(SERVER_HOSTNAME, SERVER_PORT);

			in = new BufferedReader(
				new InputStreamReader(socket.getInputStream())
			);

			out = new PrintWriter(
				new OutputStreamWriter(socket.getOutputStream())
			);

			System.out.println("Connect to " + SERVER_HOSTNAME + ":" + SERVER_PORT);

		} catch (IOException ioe) {
			System.err.println("Cannot connect to " + SERVER_HOSTNAME + ":" + SERVER_PORT);
			System.exit(1);
		}

		String name;
		System.out.print("Please input your name: ");
		Scanner ins = new Scanner(System.in);
		name = ins.nextLine();

		Sender sender = new Sender(out, name);

		sender.start();

		try {

			String message;
			
			
			while ((message = in.readLine()) != null) {

				message = message.replaceAll("(\\r|\\n)", "");
				if (message.length() == 0) {
					continue;
				}

				System.out.println(message);
				System.out.print("> ");
			}
		} catch (IOException ioe) {
			System.err.println("Conexão com o servidor falhou.");
		}
	}
}
