import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

public class Week10_1Client {
	// Create socket channel variable and address variable
    private SocketChannel socketChannel = null;
    private SocketAddress address;

    public Week10_1Client() throws IOException {
    	// Set-up Socket Channel
        socketChannel = SocketChannel.open();
        InetAddress ia = InetAddress.getLocalHost();
        // Build connection
        InetSocketAddress isa = new InetSocketAddress("localhost", 8000);
        socketChannel.connect(isa);
        // Print Welcome Message
        System.out.println("Connect Successed!");
        System.out.println("Please begin input, input 'x' will exit!");
        address = socketChannel.getLocalAddress();
        System.out.println("Local Address and Port is:" + address);
    }

    public static void main(String[] args) throws IOException {
    	// Main Start
        new Week10_1Client().talk();
    }

    public void talk() throws IOException {
        try {
        	// Create Reader and Writer for read and write message
            BufferedReader br = getReader(socketChannel.socket());
            PrintWriter pw = getWriter(socketChannel.socket());
            BufferedReader localReader = new BufferedReader(
                    new InputStreamReader(System.in));
            String msg = null;
            // Get Message
            while ((msg = localReader.readLine()) != null) {
                pw.println(msg);
                // Print Message that come form Server Side
                System.out.println(br.readLine());
                // Input 'x' exit
                if (msg.equals("x")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
            	// Close Channel
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Get Writer Method
    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    // Get Reader Method
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }
}