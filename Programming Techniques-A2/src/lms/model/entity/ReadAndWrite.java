package lms.model.entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
 * A specific class for read and write information to a file.
 */
public class ReadAndWrite {
	//Write Holding File
	public void writeHoldingFile(Holding objHolding[]){
		FileWriter fwHolding;
		//Write Holding information to file
		try {
			//write the file name.
			fwHolding = new FileWriter("holdings.txt");
			//using for loop to read the whole array, and each row write to a line.
	    	for (int i = 0; i < objHolding.length; i++) {
	    	    // check null
	        		if (objHolding[i] != null){
	        			fwHolding.write(objHolding[i] + "\n");
	        		}
	    	    }
	    	//after writing, show the message that write successful or failed.
	    	System.out.println("Write Holding File successful!");
	    	fwHolding.close();
		} catch (IOException whf) {
			// TODO Auto-generated catch block
			System.out.println("Error: write holding file failed!");
		}
	}
	
	//Write Member File
	public void writeMemberFile(Member[] objMember){
		//Write Member information to file
		FileWriter fwMember;
		try {
			//set the file name.
			fwMember = new FileWriter("members.txt");
			//using for loop to read the whole array, and each row write to a line.
	    	for (int i = 0; i < objMember.length; i++) {
	    	    // check null
	        		if (objMember[i] != null){
	        			fwMember.write(objMember[i] + "\n");
	        		}
	    	    }
	    	//if succeed, show the message.
	    	System.out.println("Write Member File successful!");
	    	fwMember.close();
		} catch (IOException wmf) {
			// TODO Auto-generated catch block
			//if failed, show error message.
			System.out.println("Error: write member file failed!");
		}
	}
	
	//Read Holding File
	public void readHoldingFile(Holding objHolding[]){
    	try {
    		//set the file name.
			FileReader frHolding = new FileReader("holdings.txt");
			BufferedReader brHolding = new BufferedReader(frHolding);
				//using for loop to read the file line by line.
				for (int i = 0; i < objHolding.length; i++){
					String stHolding = brHolding.readLine();
					if(stHolding != null){
						//set the character ":" which to split different data.
						String [] resultHolding = stHolding.split(":");
						//Due to book and video has different format, so check the first character, and user different way to read it.
						for (int j = 0; j < resultHolding.length; j++){
							if (resultHolding[0].charAt(0) == 'b'){
								objHolding[i] = new Book(resultHolding[0], resultHolding[1]);
							}else if (resultHolding[0].charAt(0) == 'v'){
								int loadLoanFee = Integer.parseInt(resultHolding[2]);
								objHolding[i] = new Video(resultHolding[0], resultHolding[1], loadLoanFee);
							}else{
								//if the data that in the file is not valid, show error message.
								System.out.println("This line does not store a valid data.");
							}
						}
					}
				}
			brHolding.close();
			//if finish, show the message.
			System.out.println("Read Holding file successful!");
			
		} catch (FileNotFoundException rhfn) {
			// TODO Auto-generated catch block
			// if the file is not exist, show the message.
			System.out.println("Error: read holding file failed! It is not exist.");
		} catch (IOException rhfi) {
			// TODO Auto-generated catch block
			// if some error happened, show error message that read failed.
			System.out.println("Error: read holding file failed!");
		} catch (Throwable rhft){
    		System.out.println("Read File failed!");
		}
	}
    
	//Read Member File
	public void readMemberFile(Member objMember[]){
    	try {
    		//set the file name.
			FileReader frMember = new FileReader("members.txt");
			BufferedReader brMember = new BufferedReader(frMember);
			//using for loop to read the file line by line.
			for (int i = 0; i < objMember.length; i++){
				String stMember = brMember.readLine();
				if(stMember != null){
					//set the character ":" which to split different data.
					String [] resultMember = stMember.split(":");
					for (int j = 0; j < resultMember.length; j++){
						if (resultMember[0].charAt(0) == 's'){
							objMember[i] = new StandardMember(resultMember[0], resultMember[1]);
						}else if (resultMember[0].charAt(0) == 'p'){
							
							objMember[i] = new PremiumMember(resultMember[0], resultMember[1]);
						}else{
							System.out.println("This line does not store a valid data.");
						}
					}
				}
			}
			brMember.close();
			System.out.println("Read Member file successful!");
			
		} catch (FileNotFoundException rmfn) {
			// TODO Auto-generated catch block
			// if the file is not exist, show the message.
			System.out.println("Error: read Member file failed! It is not exist.");
		} catch (IOException rmfi) {
			// TODO Auto-generated catch block
			// if some error happened, show error message that read failed.
			System.out.println("Error: read Member file failed!");
		} catch (Throwable rmft){
    		System.out.println("Read File failed!");
		}
	}

}
