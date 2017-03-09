import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


public class Week1_2b {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
        try (
        // Byte Streams
        // Use Buffered streamto read and write the file
        InputStream inputStream = new FileInputStream("deflated-buffer.txt");
        OutputStream outputStream = new FileOutputStream("original-buffer.txt");
 
        // Enveloping Byte Streams into Buffered Streams
        BufferedInputStream bufferIn = new BufferedInputStream(inputStream);
        BufferedOutputStream bufferOut = new BufferedOutputStream(outputStream)) {
        	
        	InflaterInputStream iis = new InflaterInputStream(bufferIn);
            // This array stores the data read in the form of Bytes.
            byte[] buffer = new byte[1024];
 
            // Looping till we reach the end of file i.e value returned is -1
            // uncompress the file
            while (iis.read(buffer) != -1) {
 
                // writing to output buffer
                bufferOut.write(buffer);
            }
            System.out.println("File uncompress successfully");
        } catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
    }

	

}
