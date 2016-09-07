package lms.model.entity;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import lms.model.exception.FullException;
import lms.model.util.DateTime;
public class LibraryMain {

	
	public static void main(String[] args) throws FullException{
		// TODO Auto-generated method stub
		//An array for store book and video.
		Holding [] objHolding = new Holding[15];
		objHolding[0] = new Book("b000001", "Intro to Java");
		objHolding[1] = new Book("b000002", "Learning UML");
		objHolding[2] = new Book("b000003", "Design Patterns");
		objHolding[3] = new Book("b000004", "Advanced Java");
		objHolding[4] = new Video("v000001", "Java 1", 4);
		objHolding[5] = new Video("v000002", "Java 2", 6);
		objHolding[6] = new Video("v000003", "UML 1", 6);
		objHolding[7] = new Video("v000004", "UML 2", 4);
        
		//An array for store Member.
		Member [] objMember = new Member[15];
		objMember[0] = new StandardMember("s000001", "Joe Bloggs");
		objMember[1] = new StandardMember("s000002", "Jane Smith");
		objMember[2] = new PremiumMember("p000001", "Fred Bloggs");
		objMember[3] = new PremiumMember("p000002", "Fred Smith");
		
		//An array for store borrow information, such as who borrow which book at what time.
		Borrow [] objBorrow = new Borrow[15];
        

		//set a flag for while loop to make sure that the main display function can be run multiple times, until user exit it. 
        boolean flagMark = true;
        while (flagMark){
        	//set Scanner function to record user input.
            Scanner keyboard = new Scanner(System.in);
            int inputNumber = -1;
            String input = null;
            //main menu
        	System.out.println("===========================");
            System.out.println("Library Management System");
            System.out.println("===========================");
            System.out.println("1. Add Holding");
            System.out.println("2. Remove Holding");
            System.out.println("3. Add Member");
            System.out.println("4. Remove Member");
            System.out.println("5. Borrow Holding");
            System.out.println("6. Return Holding");
            System.out.println("7. Print All Holding");
            System.out.println("8. Print All Members");
            System.out.println("9. Print specific Holding");
            System.out.println("10. Print specific Number");
            System.out.println("11. Save to file");
            System.out.println("12. Load from file");
            System.out.println("13. Exit");
            System.out.println("===========================");
            System.out.println("Enter an option");
            //Using try and catch to check user input, if they input a String, it will give a error message.
            input = keyboard.nextLine();
            try{
            	inputNumber = Integer.parseInt(input);
            }catch(NumberFormatException menuFE) {
                System.out.println("Please enter an Integer");
            }
            
            //Use if loop to find which number that user enter
            //if user input 1, this is Add Holding function
            /*
	         * A function, check the first character that user input. 
	         * if the first character is b or v, and also check the length that user input.
	         * and also check that the user's other input, such as the user input a special character ":".
	         * and finally store it.
	         * 
	         * Firstly, check user input is valid or not.
	         * then, if they input valid data, using for loop to read array, and find a empty row.
	         * if can find a empty row, write the data to the row.
	         * if cannot, tell user that the maximum number is 15.
	         * if they input valid data, tell them.
	         */
	        if (inputNumber == 1){
		        	String addHoldingId;
		        	String addHoldingTitle;
		        	String checkAddLoanFee;
		        	int addLoanFee = 0;
		        	//using scanner to get user's input.
		        	Scanner addHoldingkeyboard = new Scanner(System.in);
			        System.out.println("Please enter the BookID or VideoID");
			        addHoldingId = addHoldingkeyboard.nextLine();
			        //transfer the input to lower case.
			        addHoldingId = addHoldingId.toLowerCase();

			        //set the special characters that need to check.
			        String indexCheckHoldingCharacter = ":";
			        if ((addHoldingId.charAt(0) == 'b' || addHoldingId.charAt(0) == 'v') && addHoldingId.length() == 7 && addHoldingId.indexOf(indexCheckHoldingCharacter) == -1){
				        System.out.println("Please enter the Book or Video Title");
				        addHoldingTitle = addHoldingkeyboard.nextLine();
				        //due to book and video will store different format, so use different way to store different data.
				        if (addHoldingId.charAt(0) == 'b'){
				        	for (int i = 0; i < objHolding.length; i++){
				        		//due to the array can only store 15 rows data, if more than 15 rows, it will have a error. so, use if to check it.
				        		if (i < 15){
				        			//check that the Id, which user input, it is exist or not. if it is exist, show error message.
				        			if(objHolding[i] != null && objHolding[i].getId().equals(addHoldingId)){
				        				System.out.println("The Book Id already exist, please change another one!");
				        				break;
				        			//find a empty row, and store the data to this array.
				        			}else if (objHolding[i] == null){
						        		objHolding[i] = new Book(addHoldingId, addHoldingTitle);
						        		System.out.println("Add Book Finish!");
						        		break;
						        	}
				        		}else{
				        			//if cannot find a empty row, this means that there already stored 15 rows.
				        			System.out.println("You cannot add, the maximum number of holding is 15.");
				        			break;
				        		}
					        	
				        	}
				        //The way to store video
				        }else if (addHoldingId.charAt(0) == 'v'){
				        	System.out.println("Please enter the Video's Loan Fee");
				        	checkAddLoanFee = addHoldingkeyboard.nextLine();
				        	for (int i = 0; i < objHolding.length; i++){
				        		//using try and catch to make sure the loan fee is integer. if user do not input a integer, it will show a error message.
				        		try{
					            	addLoanFee = Integer.parseInt(checkAddLoanFee);
					            }catch(NumberFormatException menuFE) {
					                System.out.println("Please enter an Integer for Loan Fee");
					                break;
					            }
				        		//due to the array can only store 15 rows data, if more than 15 rows, it will have a error. so, use if to check it.
				        		if (i < 15){
				        			//check that the Id, which user input, it is exist or not. if it is exist, show error message.
				        			if(objHolding[i] != null && objHolding[i].getId().equals(addHoldingId)){
				        				System.out.println("The Video Id already exist, please change another one!");
				        				break;
				        			//find a empty row, and store the data to this array.
				        			}else if (objHolding[i] == null){
						        		objHolding[i] = new Video(addHoldingId, addHoldingTitle, addLoanFee);
						        		System.out.println("Add Video Finish!");
						        		break;
						        	}
				        		}else{
				        			System.out.println("You cannot add, the maximum number of holding is 15");
				        			break;
				        		}
					        }
				        }
				        else{
				        	System.out.println("Please enter the valid number of book or video, it is seven-digit alpha-numeric code prefixed with ‘b’ for a book and ‘v’ for a video");
				        }
			        }else{
			        	System.out.println("Please enter the valid number of book or Video, it is seven-digit alpha-numeric code prefixed with ‘b’ for a book and ‘v’ for a video");
			        }
	        	
		        
		    //if user input 2, it will run Remove Holding
	        /*
		     * first, there is a function, check the first character that user input. 
		     * if the first character is b or v, and also check the length that user input.
		     * if the id is exist, then remove it. otherwise, tell user that the id is not exist.
		     * 
		     * Firstly, check the user input.
		     * if they input valid data, then use for loop to read the array.
		     * if there has a row that the ID is same with user's input, then set it to Null.
		     * else tell user that the book is not exist.
		     * if user input invalid data, tell user.
		     */
	        }else if(inputNumber == 2){
		        	String removeHolding;
		        	Scanner removeHoldingkeyboard = new Scanner(System.in);
			        System.out.println("Please enter the BookID or VideoID.");
			        removeHolding = removeHoldingkeyboard.nextLine();
			        removeHolding = removeHolding.toLowerCase();
			       
			        if ((removeHolding.charAt(0) == 'b' || removeHolding.charAt(0) == 'v') && removeHolding.length() == 7){
			        	//set a flag to check remove success or not.
				        boolean foundRemoveHolding = false;
				        for (int i = 0; i < objHolding.length; i++){
				        	//if this row is not empty and id equal user input.
				        	if(objHolding[i] != null && objHolding[i].getId().equals(removeHolding))
				        	{
				        		objHolding[i] = null; //set this row to null.
				        		foundRemoveHolding = true;
				        		break;
				        	}
				        }
				        if (foundRemoveHolding){
				        	//check remove success or not
				        	System.out.println("Remove Book or Video Successed");
				        }else{
				        	System.out.println("The video is not exist.");
				        }
			        }else{
			        	System.out.println("Please enter valid BookID or VideoID, and it start with 'v' or 'b', and it is seven-digit alpha-numeric code.");
			        }
	        	
	        	
		        
	        //Add Member
	        /*
		     * First, there has a function, check the first character that user input. 
		     * if the first character is p or s, and also check the length that user input and check that the user's other input, such as the user input a special character ":".
		     * if user input valid data, then store it. otherwise, tell user that he/she input data is not valid.
		     * 
		     * Firstly, check the user input.
		     * then, if they input valid data, use for loop to read the array.
		     * if can find a empty row, write data to the row.
		     * if cannot, tell user that the maximum is 15.
		     * if they input invalid data, tell them.
		     * 
		     * due to there are 2 kinds of members (Standard and Premium), so I use different way to store different user.
		     */
	        	
	        }else if(inputNumber == 3){
		        	String addMemberId;
		        	String addMemberName;
		        	Scanner addMemberkeyboard = new Scanner(System.in);
			        System.out.println("Please enter the Member Id, or enter 'e' or 'exit' to exit.");
			        addMemberId = addMemberkeyboard.nextLine();
			        addMemberId = addMemberId.toLowerCase();

			        //set the special character that need to check.
			        String indexCheckMemberCharacter = ":";
			        if ((addMemberId.charAt(0) == 's' || addMemberId.charAt(0) == 'p') && addMemberId.length() == 7 && addMemberId.indexOf(indexCheckMemberCharacter) == -1){
			        System.out.println("Please enter the Member Name");
			        addMemberName = addMemberkeyboard.nextLine();
			        if (addMemberId.charAt(0) == 's'){
				        	for (int i = 0; i < objMember.length; i++){
					        	if (i < 15){
					        		if(objMember[i] != null && objMember[i].getId().equals(addMemberId)){
					        			System.out.println("The Meber Id already exist, please change another one!");
				        				break;
					        		}else if (objMember[i] == null){
					        			objMember[i] = new StandardMember(addMemberId, addMemberName);
					        			System.out.println("Add member successful!");
					        			break;
					        		}
					        	}else{
					        		System.out.println("You cannot add, the maximum number of Member is 15");
				        			break;
					        	}
					        }
				        }else if(addMemberId.charAt(0) == 'p'){
				        	for (int i = 0; i < objMember.length; i++){
					        	if (i < 15){
					        		if(objMember[i] != null && objMember[i].getId().equals(addMemberId)){
					        			System.out.println("The Meber Id already exist, please change another one!");
				        				break;
					        		}else if (objMember[i] == null){
					        			objMember[i] = new PremiumMember(addMemberId, addMemberName);
					        			System.out.println("Add member successful!");
					        			break;
					        		}
					        	}else{
					        		System.out.println("You cannot add, the maximum number of Member is 15");
				        			break;
					        	}
					        }
				        }else{
				        	System.out.println("Please enter the valid number of Member ID");
				        }
			        }else{
			        	System.out.println("Please enter the valid number of Member ID, it start with 's' or 'p'. And seven-digit alpha-numeric code.");
			        }
	        	
		        
		    //Remove Member
	        /*
		     * due to there are 2 kinds of members, so i check the first character and length to make sure that user's input is correct.
		     * then, i user for loop to read the array, and compare the memberID that in the array and the memberID that user input.
		     * if there is a row that same, set it to null, else tell user that the member is not exist.
		     * if user input invalid data, tell user that they should input valid data.
		     */
	        }else if(inputNumber == 4){
		        	String removeMember;
		        	Scanner removeMemberkeyboard = new Scanner(System.in);
			        System.out.println("Please enter the Member ID, or enter 'e' or 'exit' to exit.");
			        removeMember = removeMemberkeyboard.nextLine();
			        //changing user input to lower case.
			        removeMember = removeMember.toLowerCase();
			        
			        if ((removeMember.charAt(0) == 's' || removeMember.charAt(0) == 'p') && removeMember.length() == 7){
				        //set a flag for check remove success or not.
				        boolean foundRemoveMember = false;
				        for (int i = 0; i < objMember.length; i++){
				        	if(objMember[i] != null && objMember[i].getId().equals(removeMember))
				        	{
				        		objMember[i] = null;
				        		foundRemoveMember = true;
				        		break;
				        	}
				        }
				        //check function, for check remove success or not.
				        if (foundRemoveMember){
				        	System.out.println("Remove Member Successed");
				        }else{
				        	System.out.println("Remove Member failed! This member is not exist!");
				        }
			        }else{
			        	System.out.println("Please enter valid Member ID， it start with 's' or 'p'. And it is a seven-digit alpha-numeric code.");
			        }
	        	
	        	
	        //borrow holding function
	        /*
	         * First, set a while loop to make sure that user can borrow multiple books until the exit this function.
	         * then, change the user input to lower case, and check it. (the first character, length etc.)
	         * then, using for loop to read the holding array and member array to make sure that the book and member, which user input, is exist or not.
	         * if the member and book, which user input, is exist, then check the book is on loan or not, if not,  store the data to the array, and also record the date. (current date). and also set the book is on loan.
	         * if the member and book, which user input, is exist, then check the book is on loan or not, if ON LOAN,  tell user that the book or video already be borrowed.
	         * if the member and book, which user input, is NOT exist, tell user that the member and book is not exist.
	         * 
	         */
	        }else if (inputNumber == 5) {
	        	//set a flag and create a while loop for add multiple borrow Holding without go to main menu.
	        	boolean borrowHoldingMark = true;
	        	while (borrowHoldingMark){
		        	//set multiple variables for store MemberID, Book or Video ID and Date.
		        	String addBorrowMember;
		        	String addBorrowHolding;
		        	DateTime addBorrowData = null;
		        	int addBorrowMemberNumber = 0;
		        	int addBorrowHoldingNumber = 0;
		        	Scanner addBorrowKeyboard = new Scanner(System.in);
			        System.out.println("Please enter the Member ID, or enter 'e' or 'exit' to exit.");
			        addBorrowMember = addBorrowKeyboard.nextLine();
			        addBorrowMember = addBorrowMember.toLowerCase();
			        if (addBorrowMember.equals("e") || addBorrowMember.equals("exit")){
			        	borrowHoldingMark = false;
			        	break;
			        }
			        //check user input correct or not.
			        if ((addBorrowMember.charAt(0) == 's' || addBorrowMember.charAt(0) == 'p') && addBorrowMember.length() == 7){
				        System.out.println("Please enter the Book or Video ID");
				        addBorrowHolding = addBorrowKeyboard.nextLine();
				        addBorrowHolding = addBorrowHolding.toLowerCase();
				      //check user input correct or not.
				        if ((addBorrowHolding.charAt(0) == 'b' || addBorrowHolding.charAt(0) == 'v') && addBorrowHolding.length() == 7){
					        //use for loop to read the array.
					        boolean addBorrowMemberExist = false;
					        for (int i = 0; i < objMember.length; i++){
					        	//check that the user is exist or not, if exist, get the row number.
					        	if(objMember[i] != null && objMember[i].getId().equals(addBorrowMember))
					        	{
					        		addBorrowMemberNumber = i;
					        		addBorrowMemberExist = true;
					        		break;
					        	}
					        }
					        boolean addBorrowHoldingExist = false;
					        for (int i = 0; i < objHolding.length; i++){
					        	//check that the book or video is exist or not, if exist, get the row number.
					        	if(objHolding[i] != null && objHolding[i].getId().equals(addBorrowHolding))
					        	{
					        		addBorrowHoldingNumber = i;
					        		addBorrowHoldingExist = true;
					        		addBorrowData = objHolding[i].getBorrowDate();
					        		break;
					        	}
					        }
					        //use borrowHolding method to calculate the credit, and then find a empty row in borrow array, and store the data.
					        	//using if loop to check the book and holding is exist.
						        if (addBorrowMemberExist && addBorrowHoldingExist){
						        	//using if loop to check that the book or video is on loan or not.
							        if (objMember[addBorrowMemberNumber].borrowHolding(objHolding[addBorrowHoldingNumber]) && addBorrowMemberExist && addBorrowHoldingExist){
							        	//for loop to read array
							        	for (int i = 0; i < objBorrow.length; i++){
							        		if (i < 15){
							        			if (objBorrow[i] == null && addBorrowMemberExist && addBorrowHoldingExist){
							        				objBorrow[i] = new Borrow(addBorrowMember, addBorrowHolding, addBorrowData);
							        				System.out.println("You can borrow it!");
							        				System.out.println("borrow information: " + objBorrow[i]);
							        				System.out.println("Any more transactions?");
									        		break;
									        	}
							        		}else{
							        			System.out.println("You cannot borrow it, the all book and video alreay be borrowed.");
							        			break;
							        		}
							        	}
							        }else{
							        	System.out.println("You cannot borrow it! The book or video is not exist or already be borrowed or you do not have enough credit!");
							        	
							        }
						        }else{
						        	System.out.println("The borrow is failed! Please enter a valid Book, Video or Member.");
						        }
				         }else{
				        	System.out.println("Please enter the valid book of Video ID, it start with 'b' or 'v'. And seven-digit alpha-numeric code.");
				        	
				         }
			         }else{
			        	System.out.println("Please enter the valid number of Member ID, it start with 's' or 'p'. And seven-digit alpha-numeric code.");
			        	
			         }
	        	}
		        
	        //return borrow function.
	        /*
	         * First, set a while loop to make sure that user can borrow multiple books until the exit this function.
	         * then, change the user input to lower case, and check it. (the first character, length etc.)
	         * also ask the user that input the 'return book date'. also make sure that user input the date is valid.
	         * using for loop to read array, and then, compare the bookID (videoID) and the userID, which user input.
	         * if the bookID and userID is same, return book, set is not on loan, and also calculate the late fee (if late).
	         * if the bookID and userID is not same, then tell user that the book is not he/she borrowed.
	         * if the bookID is not exist, tell user  that the book is not be borrowed.
	         * 
	         */
	        }else if(inputNumber == 6){
	        	//set a flag and create a while loop for add multiple borrow Holding without go to main menu.
	        	boolean returnHoldingMark = true;
	        	while (returnHoldingMark){
		        	String returnBorrowMember;
		        	String returnBorrowHolding;
		        	int objHoldingNumber = -1;
		        	int objMemberNumber = -1;
		        	int returnDay = -1;
		        	int returnMonth = -1;
		        	int returnYear = -1;
		        	String inputDay;
		        	String inputMonth;
		        	String inputYear;
		        	DateTime returnBorrowDate = null;
		        	int returnBorrowMemberNumber = 0;
		        	int returnBorrowHoldingNumber = 0;
		        	Scanner returnBorrowKeyboard = new Scanner(System.in);
			        System.out.println("Please enter the Member ID, or enter 'e' or 'exit' to exit.");
			        returnBorrowMember = returnBorrowKeyboard.nextLine();
			        returnBorrowMember = returnBorrowMember.toLowerCase();
			        if (returnBorrowMember.equals("e") || returnBorrowMember.equals("exit")){
			        	returnHoldingMark = false;
			        	break;
			        }
			        System.out.println("Please enter the Book or Video ID");
			        returnBorrowHolding = returnBorrowKeyboard.nextLine();
			        returnBorrowHolding = returnBorrowHolding.toLowerCase();
			        System.out.println("Please Enter Number of return 'Day', For example: 05 or 21.");
			        //A function for check user input, if they do not input number, it will show a error.
			        inputDay = returnBorrowKeyboard.nextLine();
			        try{
			        	returnDay = Integer.parseInt(inputDay);
			        	if (returnDay > 31){
			        		System.out.println("Please enter an valid Day Number, the maximum number is 31.");
			        		break;
			        	}
		            }catch(NumberFormatException dayFE) {
		                System.out.println("Please enter an Integer of Day.");
		                break;
		            }
			        System.out.println("Please Enter Number of return 'Month', For example: 03 or 11.");
			        inputMonth = returnBorrowKeyboard.nextLine();
			      //A function for check user input, if they do not input number, it will show a error.
			        try{
			        	returnMonth = Integer.parseInt(inputMonth);
			        	if (returnMonth > 12){
			        		System.out.println("Please enter an valid Month Number, the maximum number is 12.");
			        		break;
			        	}
		            }catch(NumberFormatException monthFE) {
		                System.out.println("Please enter an Integer of Month");
		                break;
		            }
			        System.out.println("Please Enter Number of return 'Year', For example: 2016 or 2020.");
			        inputYear = returnBorrowKeyboard.nextLine();
			      //A function for check user input, if they do not input number, it will show a error.
			        try{
			        	returnYear = Integer.parseInt(inputYear);
		            }catch(NumberFormatException yearFE) {
		                System.out.println("Please enter an Integer of Year");
		                break;
		            }
			        
			        /*
			         * A function for return holding.
			         * 1. set a flag, and then go to holding array to find the book is exist or not.
			         * 2. if it is exist, set flag is true.
			         * 3. else return the book is no exist.
			         */
			        boolean returnHoldingFlag = false;
			        for (int i = 0; i < objHolding.length; i++){
			        	if (objHolding[i] != null && objHolding[i].getId().equals(returnBorrowHolding)){
			        		objHoldingNumber = i;
			        		returnHoldingFlag = true;
			        		
			        	}
			        }
			        if (!returnHoldingFlag){
			        	System.out.println("The book is not exist.");
			        }
			        
			        /*
			         * A function for return holding.
			         * 1. set a flag, and then go to Member array to find the member is exist or not.
			         * 2. if it is exist, set flag is true.
			         * 3. else return the member is no exist.
			         */
			        boolean returnMemberFlag = false;
			        for (int i = 0; i < objMember.length; i++){
			        	if (objMember[i] != null && objMember[i].getId().equals(returnBorrowMember)){
			        		objMemberNumber = i;
			        		returnMemberFlag = true;
			        	}
			        }
			        if (!returnMemberFlag){
			        	System.out.println("The member is not exist.");
			        }
			        
			        /*
			         * A function for return holding.
			         * 1. set a flag, and then go to array to find the borrow information.
			         * 2. if is that "this user" borrowed "this book", then return true.
			         * 3. else return the book is no be borrow or not you borrow.
			         * (for example, if s000001 borrow b000001, then return true. otherwise, tell user that the book is no be borrow or not you borrow).
			         */
			        boolean returnBorrowFlag = false;
			        for (int i = 0; i < objBorrow.length; i++){
			        	if (objBorrow[i] != null && objBorrow[i].getMemberId().equals(returnBorrowMember) && objBorrow[i].getHoldingId().equals(returnBorrowHolding)){
			        		objMember[objMemberNumber].returnHolding(objHolding[objHoldingNumber], new DateTime(returnDay, returnMonth, returnYear));
			        		returnBorrowFlag = true;
			        		//if check return is true, this means that return is successful, then, set this row of array is empty.
			        		if (objMember[objMemberNumber].checkReturn){
			        			objBorrow[i] = null;
			        		}
			        		System.out.println("Any more transactions?");
			        	}
			        }
			        if (!returnBorrowFlag){
			        	System.out.println("The book is not you borrowed, or does not be borrowed.");
			        }
	        	}
	        	
	        	
	        //Print all Holding. use for loop to read and print the Holding array.
	        }else if(inputNumber == 7){
	        	for (Holding h : objHolding){
	        		if (h != null){
	        		h.print();
	        		}
	        	}
	        //Print all Member. use for loop to read and print the Member array.
	        }else if(inputNumber == 8){
	        	for (Member m : objMember){
	        		if (m != null){
	        			m.print();
	        		}
	        	}
	        	
	        //Print specific Holding
	        }else if(inputNumber == 9){
	        	//set a flag to make sure although user input invalid number, it can input again rather than exit.
	        	boolean specHoldingMark = true;
	        	/*
	        	 * use while loop to do this function.
	        	 * Allow user to search for an object by providing an ‘ID’ at runtime and print only that object. 
	        	 * If the ‘ID’ supplied does not exist in the collection, the user should be informed and re-prompted to enter a valid ‘ID’. 
	        	 * They should also be able to type ‘exit’ or ‘e’ to cancel the operation if they choose.
	        	 */
	            while (specHoldingMark){
		        	String specificHolding;
		        	Scanner specificHoldingkeyboard = new Scanner(System.in);
			        System.out.println("Please enter valid the BookID or VideoID, or enter 'e' or 'exit' to exit.");
			        specificHolding = specificHoldingkeyboard.nextLine();
			        specificHolding = specificHolding.toLowerCase();
			        //if user input e or exit, then, it will exit.
			        if (specificHolding.equals("e") || specificHolding.equals("exit")){
			        	specHoldingMark = false;
			        }
			        if ((specificHolding.charAt(0) == 'b' || specificHolding.charAt(0) == 'v') && specificHolding.length() == 7){
				        //a flag for text the book and video number is valid or not.
				        boolean specHoldingTest = true;
				        
				        for (Holding h : objHolding){
				        	if(h != null && h.getId().equals(specificHolding))
				        	{
				        		h.print();
				        		specHoldingTest = false;
				        		break;
				        	}
				        }
			        	//if the flag is true, this means that it cannot find the book, print message and set it to false.
				        if (specHoldingTest){
				        	System.out.println("This book or video is not exist");
				        	specHoldingTest = false;
				        }
			        }else{
			        	System.out.println("Please enter valid BookID or VideoID, and it start with 'v' or 'b', and it is seven-digit alpha-numeric code.");
			        }
			        
	            }
	            
	        //Print specific Member	
	        }else if(inputNumber == 10){
	        	//set a flag to make sure although user input invalid number, it can input again rather than exit.
	        	boolean specMemberMark = true;
	        	/*
	        	 * use while loop to do this function.
	        	 * Allow user to search for an object by providing an ‘ID’ at runtime and print only that object. 
	        	 * If the ‘ID’ supplied does not exist in the collection, the user should be informed and re-prompted to enter a valid ‘ID’. 
	        	 * They should also be able to type ‘exit’ or ‘e’ to cancel the operation if they choose.
	        	 */
	            while (specMemberMark){
		        	String specificMember;
		        	Scanner specificMemberkeyboard = new Scanner(System.in);
		        	System.out.println("Please enter Valid Member Number, or enter 'e' or 'exit' to exit.");
		        	specificMember = specificMemberkeyboard.nextLine();
		        	specificMember.toLowerCase();
		        	//if user input e or exit, then, it will exit.
		        	if (specificMember.equals("e") || specificMember.equals("exit")){
		        		specMemberMark = false;
			        }
		        	if ((specificMember.charAt(0) == 's' || specificMember.charAt(0) == 'p') && specificMember.length() == 7){
			        	 //a flag for text the book and video number is valid or not.
			        	boolean specMemberTest = true;
			        	for (int i = 0; i < objMember.length; i++){
			        		if (objMember[i] != null && objMember[i].getId().equals(specificMember)){
			        			objMember[i].print();
			        			specMemberTest = false;
			        			break;
			        		}
			        	}
			        	//if the flag is true, this means that it cannot find the member, print message and set it to false
			        	if (specMemberTest){
			        		System.out.println("This Member is not exist");
			        		specMemberTest = false;
			        	}
		        	}else{
		        		System.out.println("Please enter valid Member ID， it start with 's' or 'p'. And it is a seven-digit alpha-numeric code.");
		        	}
		        	
	            }
	        	
	        //Save Holding and Member information to the file.	
	        }else if(inputNumber == 11){
	        	
	        	//using the Write function that come from ReadAndWrite Class
	        	ReadAndWrite wh = new ReadAndWrite();
	        	wh.writeHoldingFile(objHolding);
	        	ReadAndWrite wm = new ReadAndWrite();
	        	wm.writeMemberFile(objMember);
				
	        //Load Holding and Member information from the file.	
	        }else if(inputNumber == 12){

	        	//using the Read function that come from ReadAndWrite Class
	        	ReadAndWrite rh = new ReadAndWrite();
	        	rh.readHoldingFile(objHolding);
	        	ReadAndWrite rm = new ReadAndWrite();
	        	rm.readMemberFile(objMember);
	        	
	        }else if(inputNumber == 13){
	        	System.out.println("Bye!");
	        	flagMark = false;
	        }else{
	        	System.out.println("Please input number from 1 to 13");
	        }
	    }
	
	}

}
