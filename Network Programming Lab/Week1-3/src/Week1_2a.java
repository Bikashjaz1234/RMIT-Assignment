import java.io.*;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class Week1_2a {

	public static void main(String[] args) throws Exception {
		try{
		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream("original.txt");
		FileOutputStream fos = new FileOutputStream("deflated.txt");
		DeflaterOutputStream dos = new DeflaterOutputStream(fos);
		
		doCopy(fis, dos); 
		System.out.println("File compress successfully");
		
		}catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
	}
	
	public static void doCopy(InputStream is, OutputStream os) throws Exception {
		int oneByte;
		while ((oneByte = is.read()) != -1) {
			os.write(oneByte);
		}
		os.close();
		is.close();
	}
}
