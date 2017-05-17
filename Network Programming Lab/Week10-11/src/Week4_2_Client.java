import java.io.*;
import java.net.*;
import java.util.zip.GZIPOutputStream;
import java.util.Enumeration;
import java.util.zip.*;

public class Week4_2_Client {
    public static void main(String[] args) throws IOException {
    	
    	//Set the host Ip address
        String serverHostname = new String ("localhost");
        int portNumber;
        portNumber = 10007;
        int localPort;
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        try 
        {
          InetAddress me = InetAddress.getLocalHost();
          String dottedQuad = me.getHostAddress();
          System.out.println("Local Address is " + dottedQuad);
        } catch (UnknownHostException e) {
          System.out.println("I'm sorry. I don't know my own address.");
        }
        
        //output the Ip and port information
        if (args.length > 0)
           serverHostname = args[0];
        System.out.println ("Attemping to connect to host " +
		serverHostname + " on port " + portNumber);
        
        //create the socket
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        
        try {
            // setup the connection.
            echoSocket = new Socket(serverHostname, portNumber);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                                        echoSocket.getInputStream()));
            localPort = echoSocket.getLocalPort();
            System.out.println("local Port is " + localPort);
        } catch (UnknownHostException e) {
        	//if cannot connect, show error message.
            System.err.println("Don't know about host: " + serverHostname);
            System.exit(1);
        } catch (IOException e) {
        	//if cannot connect, show error message.
            System.err.println("Couldn't get I/O for "
                               + "the connection to: " + serverHostname);
            System.exit(1);
        }
        
    //buffer reader to read the input
	BufferedReader stdIn = new BufferedReader(
                                   new InputStreamReader(System.in));
	String userInput;
	String userInputCompress;
	//output user's input
    System.out.print ("input: ");
	while ((userInput = stdIn.readLine()) != null) {
	    out.println(userInput);
	    
	    System.out.println("echo: " + in.readLine());
            System.out.print ("input: ");
            //if the user input "X", break the loop.
            if (userInput.equals("X")) {
                break; 
            }
	}

	//close buffers
	out.close();
	in.close();
	stdIn.close();
	echoSocket.close();
    }
}
