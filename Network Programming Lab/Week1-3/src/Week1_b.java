import java.io.*;

public class Week1_b {

	public static void main(String[] args) throws IOException {
		try{
		// Creates a FileReader Object
		FileReader fr = null;
		// Which file need to be read
		fr = new FileReader("output.txt");
		
		// Create a variable for read the file
		char [] c = new char[50000];
		fr.read(c);
		// Using for loop to read the file
		for(char a : c)
	         System.out.print(a);   // output the characters one by one
	      fr.close();
		}catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
	}
}

