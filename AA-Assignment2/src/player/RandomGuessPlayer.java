package player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ship.Ship;
import world.World;

/**
 * Random guess player (task A).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey for startup code.
 * @author Harold for Implement
 */
public class RandomGuessPlayer implements Player{

	int rowNum = 0;
	int clnNum = 0;
	boolean isHex = false;
	boolean[][] isused;
	boolean makeGuessFlg = true;
	int shipNum = 5;
	
	private class myShip{
		boolean[] isDead = {false, false, false, false, false};
	}
	
    @Override
    public void initialisePlayer(World world) {
        // To be implemented.
    	 this.rowNum = world.numRow;
    	 this.clnNum = world.numColumn;
    	 this.isHex = world.isHex;
    	 this.isused = new boolean[rowNum][clnNum];
    	 
    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess) {
        // To be implemented.

        // dummy return
        return null;
    } // end of getAnswer()


    @Override
    public Guess makeGuess() {
        // Setup X and Y.
    	int x = -1;
    	int y = -1;
    	// Loop for make a guess, if the guess is used, keep looping.
    	while(makeGuessFlg){
    		// Generate random number from 0 to 10
    		x = (int)(Math.random()*10);
    		y = (int)(Math.random()*10);
    		System.out.println("the X is: " + x);
    		System.out.println("the Y is: " + y);
    		// Check it used or not.
    		if (isused[x][y] == false){
    			makeGuessFlg = false;
    		}
    	}
    	this.isused[x][y] = true;
    	Guess serverGuess = new Guess();
    	serverGuess.row = x;
    	serverGuess.column = y;
    	System.out.println("the X is: " + x);
		System.out.println("the Y is: " + y);
        // dummy return
        return serverGuess;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer) {
        // To be implemented.
    } // end of update()


    @Override
    public boolean noRemainingShips() {
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

} // end of class RandomGuessPlayer
