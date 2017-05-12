/*
 * For Check user's input
 */
public class Validation {
	
	public static String DENY_MESSAGE = "Sorry, this is a small server, this server can only handle 5 clients. Please try again later!";
	public static String WAITLIST_MSG = "There already has 3 people in the game, you need to wait until they finish the game! You are the x people!";
	
	
	// Check user input is valid menu number or not
	public static int validMenu (String userIpt){
		int menu = 0;
		try{
			// check user input is number or character
			menu = Integer.valueOf(userIpt);
		}catch (NumberFormatException nfe) {
			System.out.println("You Can only input number!");
		}
		return menu;
	}

}
