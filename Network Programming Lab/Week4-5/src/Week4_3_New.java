import java.net.*;
import java.io.*;
import java.util.zip.*;

public class Week4_3_New {

    // java -client servername port
    // java -server port
    public static void main(String[] args) throws Throwable {
    	
    	String serverHostname = new String ("10.102.128.123");
    	int portNumber = 10007;
    	
        if ("-client".equals(args[0])) {
            SocketIO sio = new SocketIO(new Socket(serverHostname, portNumber));
            try 
            {
              InetAddress me = InetAddress.getLocalHost();
              String dottedQuad = me.getHostAddress();
              System.out.println("Local Address is " + dottedQuad);
              
            } catch (UnknownHostException e) {
              System.out.println("I'm sorry. I don't know my own address.");
            }
            try {
                String ln;
                Console con = System.console();
                while((ln = sio.read()) != null) {
                    con.format("ECHO: %s", ln);
                    ln = con.readLine("%nMe  : ");
                    if(ln != null)
                        sio.write(ln);
                    if("X".equals(ln))
                        break;
                }
	    } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                sio.close();
            } 
        } else if ("-server".equals(args[0])) {
            ServerSocket ss = new ServerSocket(portNumber);
            Socket clientSocket = null;
            System.out.println ("Waiting for connection.....");
            while(true) {
            	try{
            	new SSThread(ss.accept()).start();
            	}
            	catch (IOException e) 
                { 
            	 //if cannot contact, show the error message
                 System.err.println("Accept failed."); 
                 System.exit(1); 
                } 
            }
        }
    }

    /**
     * Wraps a connected Socket object. It then can be used to read bytes
     * from/write bytes into the underlying socket streams.
     */
    private static class SocketIO {
        private Socket s;
        private OutputStream os;
        private InputStream is;
        int localPort;
        SocketIO(Socket s) throws IOException {
	    this.s = s;
	    this.os = new DeflaterOutputStream(s.getOutputStream(), true);
	    this.is = new InflaterInputStream(s.getInputStream());
	    localPort = s.getLocalPort();
        System.out.println("local Port is " + localPort);
	}
        byte[] bb = new byte[1024];
        String read() throws IOException {
            int n = is.read(bb);
            return new String(bb, 0, n);
        }
        void write(String ln) throws IOException {
            os.write(ln.toUpperCase().getBytes());
            os.flush();
        }
        void close() {
            try {
                s.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    /**
     * A worker Thread that wraps a Socket object returned from ServerSocket.accept()
     * and echos every byte read from client.
     */
    static class SSThread extends Thread {
        private SocketIO sio;
        public SSThread(Socket s) throws IOException {
	    this.sio = new SocketIO(s);
	    SocketAddress ipAddress;
	    PrintWriter out = new PrintWriter(s.getOutputStream(), 
                true);
	    BufferedReader in = new BufferedReader( 
	            new InputStreamReader(s.getInputStream()));
	    System.out.println ("Connection successful");
        System.out.println ("Waiting for input.....");
        ipAddress = s.getRemoteSocketAddress();
        System.out.println ("Remote connect ip: " + ipAddress);
        }
        public void run() {
            try {
	        sio.write("This is NetworkPrg Lab 4-5!");
                String usrIpt;
                FileWriter fWriter = null;
        	    BufferedWriter writer = null;
        	    fWriter = new FileWriter("server.txt");
        	    writer = new BufferedWriter(fWriter);
                while((usrIpt = sio.read()) != null) {
                	System.out.print("Client input: " + usrIpt + "\n");
                    sio.write(usrIpt);
                    try {
                        writer.write(usrIpt);
                        writer.newLine();
                        
                      } catch (Exception e) {
                          System.out.println("Error!");
                      }
                    if("X".equals(usrIpt)){
                    	writer.close();
                        break;
                    }
		}
	    } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                sio.close();
            }
	}
    }
}