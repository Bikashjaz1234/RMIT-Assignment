import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Week1_2a {

	public static void main(String[] args) throws Exception {
		try{
		// Define the input and out put file
		FileInputStream fis = new FileInputStream("original.txt");
		FileOutputStream fos = new FileOutputStream("deflated.txt");
		// Using output steam
		DeflaterOutputStream dos = new DeflaterOutputStream(fos);
		// call doCopy function
		doCopy(fis, dos); 
		System.out.println("File compress successfully");
		
		}catch (FileNotFoundException e) {
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
