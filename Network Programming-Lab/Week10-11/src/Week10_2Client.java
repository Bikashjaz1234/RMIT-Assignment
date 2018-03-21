import java.io.IOException;  
import java.net.InetAddress;  
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;  
import java.nio.channels.SelectionKey;  
import java.nio.channels.Selector;  
import java.nio.channels.SocketChannel;  
import java.util.Arrays;  
import java.util.Iterator;  
import java.util.LinkedList;  
  
/** 
 * Client side
 * @author Harold Zang 
 * 
 */  
public class Week10_2Client implements Runnable {
	// Variables
    private int port;  
    private InetAddress host;  
    private SocketChannel channel;  
    private Selector selector;
    // Variable for buffer
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    // Variable for message 
    private LinkedList<byte[]> messages = new LinkedList<byte[]>();
    // Variable for exit.
    private String chkExt;
    // Port number
    private static int START_PORT = 8888;
    // Variable for display IP address
    private SocketAddress address;
    private boolean exitFlg = false;
    
      
    public Week10_2Client(InetAddress host, int port) throws Exception {  
        this.host = host;  
        this.port = port;  
        channel = SocketChannel.open();  
        address = channel.getLocalAddress();
        // Use non-blocking socket module.
        channel.configureBlocking(false);  
        selector = Selector.open();  
    }  
      
    @Override  
    public void run() {
    	// Print Welcome message
    	System.out.println("Please begi input ('x' for quit)");
        
        try {
        	// Try to connect to server
            channel.connect(new InetSocketAddress(host, port));  
            channel.register(selector, SelectionKey.OP_CONNECT);
            
            while (true) {  
                synchronized (messages) {  
                    // Check the message list is empty or not  
                    if (!messages.isEmpty()) {  
                        SelectionKey key = channel.keyFor(selector);  
                        key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);  
                    }
                }
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();  
                    while (iterator.hasNext()) {  
                        SelectionKey key = iterator.next();  
                        iterator.remove();  
                        if (!key.isValid()) {  
                            continue;  
                        }  
                        if (key.isConnectable()) {
                        	// Connect to channel
                            channel.finishConnect();
                            // Register channel
                            channel.register(selector, SelectionKey.OP_READ);
                            // Get local address
                            address = channel.getLocalAddress();
                            // Display local address
                            System.out.println("Local Address and Port is " + address);
                        } else if (key.isWritable()) {  
                            write(key);  
                        } else if (key.isReadable()) {  
                            read(key);  
                        }  
                    }  
                }  
            }  
        } catch(Exception ex) {  
            ex.printStackTrace();  
        }  
    }  
      
    /** 
     * Send message to server side
     * @param msg 
     */  
    public void send(byte[] msg) {  
        synchronized(messages) {
        	// Send message to server side
            messages.addLast(msg);
            // Wake-up Selector
            selector.wakeup();  
        }  
    }  
  
    /** 
     * Writing Operation
     * @param key 
     * @throws IOException 
     */  
    private void write(SelectionKey key) throws IOException {  
        SocketChannel aChannel = (SocketChannel)key.channel();  
        synchronized(messages) {  
            if (!messages.isEmpty()) {
            	// Get the message
                byte[] buf = messages.getFirst();
                // After get message, remove it.
                messages.removeFirst();
                // Clear buffer
                buffer.clear();
                // Send message
                buffer.put(buf);
                chkExt = new String(buf);
                chkExt = chkExt.replace("\n","");
                // if user input 'x', exit.
                if (chkExt.equals("x")){
                	exitFlg = true;
                }
                buffer.flip();  
                aChannel.write(buffer);  
                key.interestOps(SelectionKey.OP_READ);
                // Display message detail and socket detail.
                System.out.println("write to: " + aChannel.socket().getRemoteSocketAddress() +   
                                   "; message: " + new String(buf));
             
            }  
        }  
    }  
      
    /** 
     * Reading Operation
     * @param key 
     * @throws IOException 
     */  
    private void read(SelectionKey key) throws IOException {  
        SocketChannel aChannel = (SocketChannel)key.channel();  
        buffer.clear();  
        int len = aChannel.read(buffer);
        if (len > 0) {
        	// Get message from server
            byte[] buf = Arrays.copyOfRange(buffer.array(), 0, len);
            
            // Display message and socket detail
            System.out.println("read from: " + aChannel.socket().getRemoteSocketAddress() +   
                               "; message: " + new String(buf));
            if (exitFlg == true){
            	System.out.println("Bye, Bye!");
            	// close channel
            	aChannel.close();
            	System.exit(1);
            }
            
        } 
    }  
      
    static void threadStart(Runnable runnable) {  
        Thread thread = new Thread(runnable);  
        thread.setName(runnable.getClass().getSimpleName());  
        thread.start();  
    }  
    
    // Main method
    public static void main(String[] args) throws Exception {  
    	// Create client and begin thread
    	Week10_2Client client = new Week10_2Client(InetAddress.getByName("m1-c23n1.csit.rmit.edu.au"), START_PORT);
        threadStart(client);  
        CommandReader reader = new CommandReader(client);  
        threadStart(reader);  
    }  
}  
  
/** 
 * Read the data stream
 * 
 */  
class CommandReader implements Runnable {  
    private Week10_2Client client;  
    private byte[] buffer = new byte[1024];  
      
    public CommandReader(Week10_2Client client) {  
        this.client = client;  
    }  
      

    @Override  
    public void run() {  
        try {  
            while (true) {  
                int len = System.in.read(buffer);  
                if (len == -1) {  
                    break;  
                } else if (len > 0) {  
                    byte[] buf = Arrays.copyOfRange(buffer, 0, len);  
                    client.send(buf);  
                }
                
            }  
        } catch(IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
}  