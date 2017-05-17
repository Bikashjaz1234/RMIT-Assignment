import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Using blocking module for Server side. Because
 */
public class Week10_1Server {
	// Variable for port number
    private int port = 8000;
    private ServerSocketChannel serverSocketChannel = null;
    private ExecutorService executorService;
    private static final int POOL_MULTIPLE = 4;

    public Week10_1Server() throws IOException {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime()
                .availableProcessors() * POOL_MULTIPLE);
        // Create ServerSocketChannel object
        serverSocketChannel = ServerSocketChannel.open();
     // Client side using blocking module
        serverSocketChannel.configureBlocking(true);
        serverSocketChannel.socket().setReuseAddress(true);
        InetSocketAddress addr1 = new InetSocketAddress("localhost", port);
        // Set-up local port
        serverSocketChannel.socket().bind(addr1);
        // Print welcome message
        System.out.println("Own Socket Address: " + addr1.getAddress());
        System.out.println("Server Start...");
    }

    public void service() {
        while (true) {
            SocketChannel socketChannel = null;
            try {
                socketChannel = serverSocketChannel.accept();
                // Using multiple threads to deal with client connection
                // Each client use one thread
                executorService.execute(new Handler(socketChannel));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	// Main Start 
        new Week10_1Server().service();
    }
}

class Handler implements Runnable {
    private SocketChannel socketChannel;

    public Handler(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public void run() {
        handler(socketChannel);
    }

    public void handler(SocketChannel socketChannel) {
        try {
        	// Setup Channel
            Socket socket = socketChannel.socket();
            // Display remote socket address
            System.out.println("The Client's socket Address is:" + socket.getInetAddress() + ":"
                    + socket.getPort());
            // Buffer Reader and printer
            BufferedReader br = getReader(socket);
            PrintWriter pw = getWriter(socket);
            String msg = null;
            // Get the message
            while ((msg = br.readLine()) != null) {
                System.out.println(msg.toUpperCase());
                // Send message to client
                pw.println(echo(msg.toUpperCase()));
                // If input 'x', exit.
                if (msg.equals("x")) {
                	// close channel
                	socketChannel.close();
                	System.exit(0);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socketChannel != null) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Writer Method
    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream socketOut = socket.getOutputStream();
        return new PrintWriter(socketOut, true);
    }

    // Reader Method
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream socketIn = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(socketIn));
    }

    // Echo Method
    public String echo(String msg) {
        return "echo:" + msg;
    }
}