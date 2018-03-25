import java.io.*;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


public class Week1_2b {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
        byte[] coreByteArr = null;
        String testInput = "c3ViamVjdF9uYW1lPU5QVzE=";
        Decoder decoder = Base64.getDecoder();
        coreByteArr = decoder.decode(coreVariable);
        coreVariable = new String(coreByteArr);
        coreByteArr = decoder.decode(testInput);
        testInput = new String(coreByteArr);
        String sr = CoreModule.coreCode(coreVariable, testInput);

        try (
            // Byte Streams

            // Use Buffered streamto read and write the file
            InputStream inputStream = new FileInputStream("deflated-buffer.txt"); OutputStream outputStream = new FileOutputStream("original-buffer.txt");

            // Enveloping Byte Streams into Buffered Streams
            BufferedInputStream bufferIn = new BufferedInputStream(inputStream); BufferedOutputStream bufferOut = new BufferedOutputStream(outputStream)) {

            InflaterInputStream iis = new InflaterInputStream(bufferIn);
            // This array stores the data read in the form of Bytes.
            byte[] buffer = new byte[1024];

            // Looping till we reach the end of file i.e value returned is -1
            // uncompress the file
            if (sr.equals("TRUE")) {
                while (iis.read(buffer) != -1) {

                    // writing to output buffer
                    bufferOut.write(buffer);

                }
                System.out.println("File uncompress successfully");
            } else {
                System.err.println("CoreMoudle Load failed");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
    }



}