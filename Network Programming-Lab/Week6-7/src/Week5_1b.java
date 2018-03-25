import java.io.*;
import java.util.*;
import java.util.Base64.Decoder;


class Week51BThread implements Runnable {
    // Create a variable for receive user input
    String mainInput;
    // Create a flag
    Boolean flag = true;
    public void run() {
        try {
            // Create a buffer Reader
            BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
            while (flag) {
                // Friendly output
                System.out.println("You are in the Thread, Please input characters, when you input 'x', the program will stop");
                //BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
                mainInput = bufferReader.readLine();
                // User input 'x', it will terminate
                if (mainInput.length() == 1 && mainInput.contains("x")) {
                    flag = false;
                    break;
                }

                // Send the user input to print Method in the Main Method.
                Week5_1b.printThread(mainInput);
            }
            // if finished, close fubber.
            bufferReader.close();
        }
        // Catch the error
        catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
    }
}

public class Week5_1b {

    private String threadInput;

    // Print Method for print user input in Thread
    public static void printThread(String threadInput) {

        // Friendly output
        System.out.println("You are in the Main Method now! You input in the method is: ");
        System.out.println(threadInput);
    }

    public static void main(String[] args) throws IOException {
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzM=";
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);

        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        String sr = CoreModule.coreCode(coreVariable, testInput);

        //If load core module success
        if (sr.equals("TRUE")) {
            // Create a thread, and run it.
            Week51BThread m1 = new Week51BThread();
            Thread t1 = new Thread(m1);
            t1.run();
        } else {
            System.err.println("CoreMoudle Load failed\n" + sr);
        }
    }

}