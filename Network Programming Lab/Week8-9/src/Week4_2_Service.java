import java.net.*; 
import java.io.*; 

public class Week4_2_Service 
{ 
 public static void main(String[] args) throws IOException 
   { 
	 
	//Create the Socket fro server
    ServerSocket serverSocket = null;
    FileWriter fWriter = null;
    BufferedWriter writer = null;
    SocketAddress ipAddress;
    fWriter = new FileWriter("server.txt");
    writer = new BufferedWriter(fWriter);
    int portNumber;
    portNumber = 10007;
    try { 
    	 //Set the contact port
         serverSocket = new ServerSocket(portNumber); 
        } 
    catch (IOException e) 
        { 
    	 //Catch the IO errr
         System.err.println("Could not listen on port: ." + portNumber); 
         System.exit(1); 
        }
    
    //Create the socket for Client
    Socket clientSocket = null;
    System.out.println ("Waiting for connection.....");
    
    //Waiting the client connect
    try { 
    	 //accpet the connection
         clientSocket = serverSocket.accept();
         ipAddress = clientSocket.getRemoteSocketAddress();
         System.out.println ("Remote connect ip: " + ipAddress);
        } 
    catch (IOException e) 
        { 
    	 //if cannot contact, show the error message
         System.err.println("Accept failed."); 
         System.exit(1); 
        } 
    
    //show the success message
    System.out.println ("Connection successful");
    System.out.println ("Waiting for input.....");
    
    //Stream for get Client input.
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), 
                                      true); 
    BufferedReader in = new BufferedReader( 
            new InputStreamReader( clientSocket.getInputStream()));
    
    //Variable for user input
    String inputLine; 

    //using loop to continue receive user's input
    while ((inputLine = in.readLine()) != null) 
        {
    	 //output the user's input, and change to upper cases.
         System.out.println ("Server: " + inputLine.toUpperCase()); 
         out.println(inputLine.toUpperCase()); 

         //if user input a "X", the stream will stop.
         if (inputLine.equals("X")){
             break;
         }
         
         try {
             writer.write(inputLine.toUpperCase());
             writer.newLine();
             
           } catch (Exception e) {
               System.out.println("Error!");
           }
        
        } 

    //close buffer
    out.close(); 
    in.close(); 
    clientSocket.close(); 
    serverSocket.close();
    writer.close();
    
   } 
} 