package player;

import java.util.*;

import ship.Ship;
import world.World;

/**
 * Random guess player (task A).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey for startup code.
 */
public class RandomGuessPlayer implements Player{

    World world;
    int row;
    int col;
    int maxGuesses = -1;
    //ArrayList<Guess> guesses = new ArrayList<>();
    ArrayList<World.ShipLocation> shipLoc = new ArrayList<>();
    HashSet<Guess> guesses = new HashSet<>();
    ArrayList<Guess> tmp = new ArrayList<>();


    @Override
    public void initialisePlayer(World world)
    {
        this.world = world;
        maxGuesses = world.numRow * world.numColumn;

        // ship locations
        for (int i = 0; i < world.shipLocations.size(); i++)
        {
            shipLoc.add(world.shipLocations.get(i));
        }



        for (int i = 0; i < shipLoc.size(); i++)
        {

        }

        int startRow = 0;
        int startCol = 0;

        for (startRow = 0; startRow < world.numRow; startRow++)
        {
            for (startCol = 0; startCol < world.numColumn; startCol++)
            {
                Guess guess = new Guess();
                guess.row = startRow;
                guess.column = startCol;

                guesses.add(guess);
            }
        }


        tmp.addAll(guesses);

        /*for (int i = 0; i < tmp.size(); i++)
        {
            System.out.println(tmp.get(i).row + ":" + tmp.get(i).column);
            System.out.println();
        }

        System.out.println("guesses size: " + guesses.size());*/




    } // end of initialisePlayer()

    @Override
    public Answer getAnswer(Guess guess)
    {
        Answer answer = new Answer();
        ArrayList<World.Coordinate> cdns;
        World.Coordinate cdn;
        Ship ship;
        for (int i = 0; i < this.shipLoc.size(); i++) {
            cdns = this.shipLoc.get(i).coordinates;
            ship = this.shipLoc.get(i).ship;
            for (int m = 0; m < cdns.size(); m++) {
                cdn = cdns.get(m);
                if (cdn.row == guess.row && cdn.column == guess.column) {
                    answer.isHit = true;
                    cdns.remove(m);
                    // check whether current ship is sunk
                    if (cdns.isEmpty()) {
                        answer.shipSunk = ship;
                        this.shipLoc.remove(i);
                        return answer;
                    }
                }
            }
        }// end for loop

        return answer;
    } // end of getAnswer()


    @Override
    public Guess makeGuess()
    {
        int guessCount = 0;
        Guess guessLocal = new Guess();

        int index = generateRandomNum("guess");

        guessLocal = tmp.get(index);

        tmp.remove(index);

        System.out.println(guessLocal.toString());

        return guessLocal;
    } // end of makeGuess()


    @Override
    public void update(Guess guess, Answer answer)
    {
        // To be implemented.
    } // end of update()


    @Override
    public boolean noRemainingShips()
    {
        // To be implemented.

        // dummy return
        return true;
    } // end of noRemainingShips()

    public int generateRandomNum(String type)
    {
        int result = -1;
        Random random = new Random();

        if (type.equalsIgnoreCase("row"))
        {
            result = random.nextInt(world.numRow);
        }
        else if (type.equalsIgnoreCase("col"))
        {
            result = random.nextInt(world.numColumn);
        }
        else if (type.equalsIgnoreCase("guess"))
        {
            result = random.nextInt(tmp.size());
        }


        return result;
    }

} // end of class RandomGuessPlayer