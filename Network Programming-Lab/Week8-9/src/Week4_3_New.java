import java.net.*;
import java.io.*;
import java.util.zip.*;

public class Week4_3_New {

    // For the client side, please run: java -client servername port
    // For the server side, please run: java -server port
    public static void main(String[] args) throws Throwable {
    	//set up the ip address and port
    	String serverHostname = new String ("10.102.128.123");
    	int portNumber = 10007;
    	
    	//Code for client side
        if ("-client".equals(args[0])) {
        	//connect to the server side. input ip and port.
            SocketIO sio = new SocketIO(new Socket(serverHostname, portNumber));
            //Using try and catch to catch the error
            try 
            {
              //Print Local address.
              InetAddress me = InetAddress.getLocalHost();
              String localAddress = me.getHostAddress();
              System.out.println("Local Address is " + localAddress);
              
            } catch (UnknownHostException e) {
            	//If cannot print, print error message
              System.out.println("I'm sorry. I don't know my own address.");
            }
            
            //Using try and catch to catch the error
            try {
                String ln;
                Console con = System.console();
                //Print user input
                while((ln = sio.read()) != null) {
                    con.format("ECHO: %s", ln);
                    //print the server side response
                    ln = con.readLine("%nMe  : ");
                    if(ln != null)
                        sio.write(ln);
                    //If user input 'X' spot it.
                    if("X".equals(ln))
                        break;
                }
	    } catch (IOException ioe) {
	    	//if error happened, print the error message.
                ioe.printStackTrace();
            } finally {
            	//Finally close it.
                sio.close();
            }
            //Code for server side
        } else if ("-server".equals(args[0])) {
        	//connect the port
            ServerSocket ss = new ServerSocket(portNumber);
            Socket clientSocket = null;
            //print the friendly message to tell user that it is waiting for connection.
            System.out.println ("Waiting for connection.....");
            while(true) {
            	//Using try and catch to catch the error
            	try
            	{
            		//call the function to accept the client side input.
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

    //This is for wraps a connected Socket object. It then can be used to read bytes from/write bytes into the underlying socket streams.
    private static class SocketIO {
    	//Create socket and streams for receive and send the user's input
        private Socket s;
        private OutputStream os;
        private InputStream is;
        //variable for print the local port that use to communicate.
        int localPort;
        SocketIO(Socket s) throws IOException {
	    this.s = s;
	    //use Deflater and Inflater to compress and uncompress the input.
	    this.os = new DeflaterOutputStream(s.getOutputStream(), true);
	    this.is = new InflaterInputStream(s.getInputStream());
	    //get the local port and print it
	    localPort = s.getLocalPort();
        System.out.println("local Port is " + localPort);
	}
        byte[] bb = new byte[1024];
        //read the string
        String read() throws IOException {
            int n = is.read(bb);
            return new String(bb, 0, n);
        }
        //Write the string
        void write(String ln) throws IOException {
        	//change the input to upper cases.
            os.write(ln.toUpperCase().getBytes());
            //flush the stream
            os.flush();
        }
        //close function
        void close() {
            try {
            	//try to close stream
                s.close();
            } catch (IOException ioe) {
            	//if failed, print the error message
                ioe.printStackTrace();
            }
        }
    }


    //This is A worker Thread that wraps a Socket object returned from ServerSocket.accept() and echos every byte read from client.
    static class SSThread extends Thread {
    	//Create a StocketIO for uncompress
        private SocketIO sio;
        public SSThread(Socket s) throws IOException {
	    this.sio = new SocketIO(s);
	    //create variable for display ip address
	    SocketAddress ipAddress;
	    //variables for write the file to the system.
	    PrintWriter out = new PrintWriter(s.getOutputStream(), 
                true);
	    BufferedReader in = new BufferedReader( 
	            new InputStreamReader(s.getInputStream()));
	    //if the connect successful, print the successful message.
	    //this function is called in server side, Line 64.
	    System.out.println ("Connection successful");
        System.out.println ("Waiting for input.....");
        //print the Ip address
        ipAddress = s.getRemoteSocketAddress();
        System.out.println ("Remote connect ip: " + ipAddress);
        }
        //show the message
        public void run() {
            try {
            	//display the first response on client side
	        sio.write("This is NetworkPrg Lab 4-5!");
                String usrIpt;
                FileWriter fWriter = null;
        	    BufferedWriter writer = null;
        	    //Log file name
        	    fWriter = new FileWriter("server.txt");
        	    writer = new BufferedWriter(fWriter);
                while((usrIpt = sio.read()) != null) {
                	//Show the client side input
                	System.out.print("Client input: " + usrIpt + "\n");
                	//send to client side.
                    sio.write(usrIpt);
                    try {
                    	//try to write the log to server.txt
                        writer.write(usrIpt);
                        //start to a new line.
                        writer.newLine();
                        
                      } catch (Exception e) {
                    	  //if cannot write file, show error message
                          System.out.println("Error!");
                      }
                    //if client side input X, it will close current stream, and wait the new connection.
                    if("X".equals(usrIpt)){
                    	writer.close();
                        break;
                    }
		}
                //If error happened, print the error message
	    } catch (IOException ioe) {
                ioe.printStackTrace();
            } finally {
                sio.close();
            }
	}
    }
}