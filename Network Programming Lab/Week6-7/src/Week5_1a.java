import java.io.*;
import java.util.*;

class Week51AThread implements Runnable{
	// Create a private variable for receive pass parameter
	private String mainInput;
	
	// Create the thread and receive the user input
	public Week51AThread(String mainInput) {	
		this.mainInput = mainInput;
	   }
	
	//run and output the user's input
	public void run(){  
		System.out.println(mainInput);
	}
	
}  

public class Week5_1a {

	public static void main(String[] args) throws IOException {
		// Create user input variable
		String userInput;
		// Create buffer reader for read input
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
		try{
			
			System.out.println("Please input something, and it will pass to Thread.");
			userInput = bufferReader.readLine();
			
			// Create the thread, and pass the user input to it.
			Week51AThread m1=new Week51AThread(userInput);  
			Thread t1 =new Thread(m1);  
			
			// run the tread.
			t1.run();  
			
		}
		catch (IOException e) {
	        System.out.println("Error in Reading and writing operations");
	    }
		finally{
			bufferReader.close();
		}
	}

}


