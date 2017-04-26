import java.io.*; 
import java.net.*; 

class Week3_T_Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(10007);
		
		Socket clientSocket = null;
		clientSocket = serverSocket.accept();
		
		PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
		BufferedReader in = new BufferedReader( new InputStreamReader( clientSocket.getInputStream()));
		
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			System.out.println ("Server: " + inputLine.toUpperCase());
			out.println(inputLine);
			if (inputLine.equals("Bye.")) break;
		}
		out.close();
		in.close();
		clientSocket.close();
		serverSocket.close();
	}

}