import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Sender extends Thread {
	private PrintWriter mOut;
	private String name;

	public Sender(PrintWriter aOut, String name) {
		mOut = aOut;
		this.name = name;
	}

	public void run() {
		try {
			BufferedReader in = new BufferedReader(
				new InputStreamReader(System.in)
			);
			System.out.println("Welcome to Guessing Game!");
			System.out.println("Rule:");
			System.out.println("1. The server generate a number 0 and 2");
			System.out.println("2. Every client will input a number between 0 and 2");
			System.out.println("3. You need to guess the sum of the input");
			System.out.println("4. The people who is the closest, he will win!");
			
			int guessNumber;
			boolean checkFlg = false;
			boolean checkNum = false;
			
			while (!isInterrupted()) {
				System.out.print("> ");
				String message = in.readLine();
				try{
					guessNumber = Integer.valueOf(message);
					checkFlg = true;
				}catch (NumberFormatException nfe) {
					System.out.println("Invalid Input");
				}
				
				if (checkFlg == true && checkNum == true){
					mOut.println(message);
					mOut.flush();
				}
			}
		} catch (IOException ioe) {
		}
	}
}
