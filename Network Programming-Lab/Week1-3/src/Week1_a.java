import java.io.*;
import java.util.Base64;
import java.util.Base64.Decoder;
public class Week1_a {

    public static void main(String[] args) throws IOException {
        // Create variables for read the input and write the characters to a file
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStreamWriter = new FileOutputStream("output.txt");
        //Core Variables
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzE=";
        String input;
        int line;
        // create variables for tracking the line number
        line = 1;
        Boolean flag = true;
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);
        //create a input stream object
        InputStreamReader cin = null;
        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        //Try to start core function (Works)
        String sr = CoreModule.coreCode(coreVariable, testInput);
        if (sr.equals("TRUE")) {

            //Try to get the input and write the file.
            try {
                cin = new InputStreamReader(System.in);
                System.out.println("Please input characters, when you input 'x', the program will stop");
                while (flag) {
                    input = bufferReader.readLine();
                    //if the length is 1 and the character is x, then break the loop.
                    if (input.length() == 1 && input.contains("x")) {
                        flag = false;
                        break;
                    }
                    //write the line number and change the input to upper case.
                    outputStreamWriter.write((line + input.toUpperCase()).getBytes());
                    //set the new line
                    outputStreamWriter.write(10);
                    //flush the buffer
                    outputStreamWriter.flush();
                    line++;
                }
            } catch (FileNotFoundException e) {
                System.out.println("Input file is not found");
            } catch (IOException e) {
                System.out.println("Error in Reading and writing operations");
            } finally {
                if (cin != null) {
                    //close the stream
                    outputStreamWriter.close();
                    bufferReader.close();
                }
            }
        } else {
            System.err.println("CoreMoudle Load failed");
        }

    }

}