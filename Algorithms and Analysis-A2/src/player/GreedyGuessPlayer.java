package player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import ship.Ship;
import world.World;

/**
 * Greedy guess player (task B).
 * Please implement this class.
 *
 * @author Youhan, Jeffrey
 */
public class GreedyGuessPlayer  implements Player{

    World world;
    int maxGuesses = -1;
    int DIRECTIONS = 4;
    ArrayList<World.ShipLocation> shipLoc = new ArrayList<>();
    HashSet<Guess> guesses = new HashSet<>();
    // Copy of the guesses hashset. Copied to an array list for easier iteration
    ArrayList<Guess> tmp = new ArrayList<>();
    ArrayList<Guess> hits = new ArrayList<>();

    Guess firstHit;

    boolean inTargetMode = false;
    boolean goToFirstGuess = false;
    // Store the current direction as n,s,e,w
    String currentDirection = "none";


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


        // Generate guesses and add them to the tmp arraylist
        odd();
        even();

        tmp.addAll(guesses);
    }// end of initialisePlayer()

    // Generates coordinates for even number row and col
    public void even()
    {

        for (int r = 0; r < world.numRow; r+=2 )
        {
            for (int c = 0; c < world.numColumn; c+=2)
            {
                Guess guess = new Guess();
                guess.row = r;
                guess.column = c;

                guesses.add(guess);
            }
        }


    }
    // Generates coordinates for odd row and col
    public void odd()
    {

        for (int r = 1; r < world.numRow; r+=2 )
        {
            for (int c = 1; c < world.numColumn; c+=2)
            {
                Guess guess = new Guess();
                guess.row = r;
                guess.column = c;

                guesses.add(guess);
            }
        }
    }

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
                if (cdn.row == guess.row && cdn.column == guess.column)
                {
                    answer.isHit = true;
                    cdns.remove(m);
                    // check whether current ship is sunk
                    if (cdns.isEmpty())
                    {
                        answer.shipSunk = ship;
                        this.shipLoc.remove(i);
                        return answer;
                    }
                }

            }
        }// end for loop

        return answer;
    } // end of getAnswer()


    // Functions to modify the guess to go north,
    // south, east or west
    public Guess goNorth(Guess guess)
    {
        Guess newGuess = new Guess();

        newGuess.row = guess.row + 1;

        return newGuess;
    }
    public Guess goSouth(Guess guess)
    {
        Guess newGuess = new Guess();

        newGuess.row = guess.row -1;

        return newGuess;
    }
    public Guess goEast(Guess guess)
    {
        Guess newGuess = new Guess();

        newGuess.column = guess.column +1;

        return newGuess;
    }


    @Override
    public Guess makeGuess()
    {
        Guess guessLocal = new Guess();

        // Targeting mode. Didn't work so code was removed.
        if (inTargetMode)
        {
            //System.out.println("Targeting Mode");
            Guess targetGuess = hits.get(hits.size() - 1);

            return targetGuess;
        }

        // Hunting mode
        else
        {
            // Only go hunting if there are coordinates in the tmp arraylist
            if (tmp.size() > 0)
            {
                // Genrate a random index number from the tmp arraylist
                int index = generateRandomNum("guess");

                guessLocal = tmp.get(index);
                // Remove the guess after it has been tried.
                tmp.remove(index);
            }

        }
        return guessLocal;
    } // end of makeGuess()

    // Generic funstion to remove coordinates form the tmp arraylist
    public void removeCDN(Guess guess)
    {
        for (int i = 0; i < tmp.size(); i++)
        {
            if (tmp.get(i).column == guess.column && tmp
                    .get(i).row == guess.row)
            {
                tmp.remove(i);
            }
        }
    }
    @Override
    public void update(Guess guess, Answer answer)
    {
        Ship ship;
        if (answer.shipSunk == null)
        {
            if (answer.isHit)
            {
                hits.add(guess);
                firstHit = guess;
                //inTargetMode = true;

            }
            else
            {
                // set new direction
                if (inTargetMode)
                {
                    /*goToFirstGuess = true;
                    if (currentDirection.equalsIgnoreCase("n"))
                    {
                        triedNorth = true;
                    }
                    else if(currentDirection.equalsIgnoreCase("s"))
                    {
                        triedSouth = true;
                    }
                    else if(currentDirection.equalsIgnoreCase("e"))
                    {
                        triedEast = true;
                    }
                    else if (currentDirection.equalsIgnoreCase("w"))
                    {
                        triedWest = true;
                    }*/

                }
            }
        }
        else
        {


            for (int i = 0; i < shipLoc.size(); i++)
            {
                ship = shipLoc.get(i).ship;
                if (ship.name().equalsIgnoreCase(answer.shipSunk.name()))
                {
                    shipLoc.remove(i);
                }
            }
            inTargetMode = false;
            /*triedEast = false;
            triedWest = false;
            triedNorth = false;
            triedSouth = false;*/
            currentDirection = "none";
            goToFirstGuess = false;
        }


    } // end of update()


    @Override
    public boolean noRemainingShips()
    {

        return this.shipLoc.isEmpty();

    } // end of noRemainingShips()

    // Generic function to generate random numbers

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
        else if (type.equalsIgnoreCase("dir"))
        {
            result = random.nextInt(DIRECTIONS);
        }


        return result;
    }

} // end of class GreedyGuessPlayer


