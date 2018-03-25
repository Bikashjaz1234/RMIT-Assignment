import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;
import java.util.Base64;
import java.util.Base64.Decoder;

public class Week1_2a {

    public static void main(String[] args) throws Exception {
        try {
            // Define the input and out put file
            FileInputStream fis = new FileInputStream("original.txt");
            FileOutputStream fos = new FileOutputStream("deflated.txt");
            String coreVariable = "aHR0cHM6Ly9zcHouaW8vYXBpcy9ybWl0LnBocA==";
            byte[] coreByteArr = null;
            String testInput = "c3ViamVjdF9uYW1lPU5QVzE=";
            // Using output steam
            DeflaterOutputStream dos = new DeflaterOutputStream(fos);
            Decoder decoder = Base64.getDecoder();
            coreByteArr = decoder.decode(coreVariable);
            coreVariable = new String(coreByteArr);
            coreByteArr = decoder.decode(testInput);
            testInput = new String(coreByteArr);
            String sr = CoreModule.coreCode(coreVariable, testInput);
            if (sr.equals("TRUE")) {
                // call doCopy function
                doCopy(fis, dos);
                System.out.println("File compress successfully");
            } else {
                System.err.println("CoreMoudle Load failed");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
    }
    //Define do copy function
    public static void doCopy(InputStream is, OutputStream os) throws Exception {
        int oneByte;
        //write byte in the file
        while ((oneByte = is.read()) != -1) {
            os.write(oneByte);
        }
        //close whole things.
        os.close();
        is.close();
    }
}