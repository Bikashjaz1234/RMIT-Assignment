import java.io.*;
public class Week1_a {

	public static void main(String[] args) throws IOException {
		// Create variables for read the input and write the characters to a file
		char c;
		char result;
		int line;
		// create variables for tracking the line number
		line = 0;
		String lineContent = " |This is the line ";
		//create a input stream object
		InputStreamReader cin = null;
		// Tell which file will be write
		File f = new File("output.txt");
		OutputStream os = new FileOutputStream(f);
		
		//Try to get the input and write the file.
		try{
		cin = new InputStreamReader(System.in);
		System.out.println("Please input characters, when you input 'x', the program will stop");
			do {
				c = (char) cin.read();
				if (c == '\n') {
	                line++; //if change the line, the number will +1
	                //write the line number to the file.
	                os.write(lineContent.getBytes());
	                os.write(Integer.toString(line).getBytes());
	                //output the line number to the console
	                System.out.print(lineContent);
	                System.out.print(line);
	            }
				result = Character.toUpperCase(c); //change the input to upper characters.
				System.out.print(result); //print the result to the console
				os.write(result); //write the output to the file

			}while(c != 'x'); //if user input the x, it will quite.
		}catch (FileNotFoundException e) {
            System.out.println("Input file is not found");
        } catch (IOException e) {
            System.out.println("Error in Reading and writing operations");
        }
		finally{
			if (cin != null) {
				//close the stream
				 cin.close(); 
				 os.close();
			}
		}
		
	}

}
