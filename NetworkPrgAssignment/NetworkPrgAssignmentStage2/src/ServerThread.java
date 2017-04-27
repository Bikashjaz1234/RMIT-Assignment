import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;


class ServerThread extends Thread {
	private ArrayList<Socket> mClients = new ArrayList<Socket>();
	private ArrayList<String> mMsgQueue = new ArrayList<String>();

	public synchronized void addClient(Socket aClientSocket) {
		mClients.add(aClientSocket);
	}

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

	public synchronized void dispatchMsg(Socket aSocket, String aMsg) {
		Date data = new Date(System.currentTimeMillis());
		SimpleDateFormat formatarDate = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");
		aMsg = aMsg + "\n\r";
		mMsgQueue.add(aMsg);
		notify();
	}

	private synchronized String getNextMsgFromQueue() throws InterruptedException {
		while (mMsgQueue.size() == 0)
			wait();
		String msg = mMsgQueue.get(0);
		mMsgQueue.remove(0);
		System.out.println("Msg is: " + msg);
		return msg;

	}

	public synchronized void sendMsgToAllClients(String aMsg) {
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
