/*
 * Player class for store player's information
 * 
 */
public class Player {
	// Store Player name, Player generate number, player guess number and server generate number
    private String PlayerName;
    private int PlayerGenerate;
    private int PlayerGuess;
    private int SerGenNum;

    public Player(String name, int generate, int guess, int serNum) {
        PlayerName = name;
        PlayerGenerate = generate;
        PlayerGuess = guess;
        SerGenNum = serNum;
    }

/*
 * For get and set these store numbers.
 */
    public void setName(String name) {
        this.PlayerName = name;
    }

    public String getName() {
        return PlayerName;
    }

    public void setGenerate(int generate){
    	this.PlayerGenerate = generate;
    }
    
    public int getGenerate(){
    	return  PlayerGenerate;
    }
    
    public void setGuess(int guess){
    	this.PlayerGuess = guess;
    }
    
    public int getGuess(){
    	return PlayerGuess;
    }
    
    public void setSerNum(int serNum){
    	this.SerGenNum = serNum;
    }
    
    public int getSerNum(){
    	return SerGenNum;
    }
}