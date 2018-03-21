import java.io.*;
import java.net.*;

public class Week3_T_Client {
	public static void main(String[] args) throws IOException{
		
		String serverHostname = new String ("127.0.0.1");
		Socket echoSocket = null; 
		PrintWriter out = null; 
		BufferedReader in = null;
		
		echoSocket = new Socket(serverHostname, 10007);
		out = new PrintWriter(echoSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader( echoSocket.getInputStream()));
		BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
		
		String userInput;
		while ((userInput = stdIn.readLine()) != null){
			out.println(userInput);
			System.out.println("echo: " + in.readLine());
		}
		out.close();
		in.close();
		stdIn.close();
		echoSocket.close();
	}

}
