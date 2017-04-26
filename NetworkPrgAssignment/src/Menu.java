import java.util.Scanner;


public class Menu {
	
	private Scanner keyboard = new Scanner(System.in);
	private int menuSelection;
	private boolean loginMenuFlg = true;
	private boolean multiMenuFlg = true;
	
	public void loginSelect() throws Exception {
		
		while(loginMenuFlg){
			System.out.println("----------------------------------");
			System.out.println("Welcome to Gaming System");
			System.out.println("----------------------------------");
			System.out.println("1. Single Player");
			System.out.println("2. Multiple Player");
			System.out.println("3. Exit");
			System.out.println("----------------------------------");
			
			System.out.print("Enter an Option: ");
			String menuIpt = keyboard.nextLine();
			try {
				menuSelection = Integer.valueOf(menuIpt);
			} catch (NumberFormatException nfe) {
				menuSelection = 0;
				System.out.println("Invalid Input");
			}
			
			switch (menuSelection) {
			case 1:
				System.out.println("Single Player Game!");
				SinglePlayer.singleGame();
				//menuFlg = false;
				break;
			case 2:
				System.out.println("Multiple Player Game!");
				serverSelect();
				//menuFlg = false;
				break;
			case 3:
				System.out.println("Bye!");
				loginMenuFlg = false;
				break;
			default:
				System.out.println("Invalid Input!");
				menuSelection = 0;
				break;
			}
		}
	}
	
	public void serverSelect() throws Exception {
		
		while(multiMenuFlg){
			System.out.println("----------------------------------");
			System.out.println("Welcome to Multiple Player System");
			System.out.println("----------------------------------");
			System.out.println("1. Server");
			System.out.println("2. Client");
			System.out.println("3. Exit");
			System.out.println("----------------------------------");
			
			System.out.print("Enter an Option: ");
			String menuIpt = keyboard.nextLine();
			try {
				menuSelection = Integer.valueOf(menuIpt);
			} catch (NumberFormatException nfe) {
				menuSelection = 0;
				System.out.println("Invalid Input");
			}
			
			switch (menuSelection) {
			case 1:
				System.out.println("Setting Game Server!");
				Server.setServer();
				//menuFlg = false;
				break;
			case 2:
				System.out.println("Setting Game Client!");
				GameClient.setClient();
				//menuFlg = false;
				break;
			case 3:
				loginSelect();
				multiMenuFlg = false;
				break;
			default:
				System.out.println("Invalid Input!");
				menuSelection = 0;
				break;
			}
		}
	}
}
