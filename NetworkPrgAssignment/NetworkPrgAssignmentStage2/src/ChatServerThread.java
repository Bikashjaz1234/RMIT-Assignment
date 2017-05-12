import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;


class ChatServerThread extends Thread {
	// Array list for socket
	private ArrayList<Socket> mClients = new ArrayList<Socket>();
	// Array list for message
	private ArrayList<String> mMsgQueue = new ArrayList<String>();

	/*
	 * For add Client to socket
	 */
	public synchronized void addClient(Socket aClientSocket) {
		mClients.add(aClientSocket);
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
			} catch (IOException ioe) {
			}
		}
	}

	/*
	 * For send the message to the client and log the message
	 */
	public synchronized void dispatchMsg(Socket aSocket, String aMsg) throws IOException {
		// Get the current date
		Date data = new Date(System.currentTimeMillis());
		// Format the date
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
		// Add the date information to the message
		aMsg = formatarDate.format(data) + " - " + aMsg + "\n\r";
		// Create the file, but does not over write
		PrintWriter writer = new PrintWriter(new FileWriter("communication.txt", true));
		// write the message to the file
		writer.println(aMsg);
		// close writer
		writer.close();
		// Add message to the message queue
		mMsgQueue.add(aMsg);
		notify();
	}

	// Get the next message function
	private synchronized String getNextMsgFromQueue() throws InterruptedException {
		// If does not receive any message, waiting
		while (mMsgQueue.size() == 0)
			wait();
		// print the message and delete the message from the queue
		String msg = mMsgQueue.get(0);
		mMsgQueue.remove(0);
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

	// Send the message to all clients
	public void run() {
		try {
			while (true) {
				String msg = getNextMsgFromQueue();
				sendMsgToAllClients(msg);
			}
		} catch (InterruptedException ie) {
		}
	}
}
