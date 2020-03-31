import java.awt.event.*;
import java.lang.Math;
/**
 * Write a description of class Game here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Game //implements ActionListener
{
    public Player players[];
    private int maxMoves = 20;
    private EasyMap eState;
    private Player current;
    private boolean goalReached;
    private int currentIndex;
    private int numPlayers;
    /**
     * Constructor for objects of class Game
     */
    public Game(String diff, int numPlrs)
    {
        players = new Player[numPlrs];
        numPlayers = numPlrs;
        createPlayers();
        currentIndex = 0;
        current = players[currentIndex];
        goalReached = false;
        if(diff=="easy")
        {
            eState = new EasyMap();
        }
    }
    
    public void createPlayers()
    {
        for(int i = 0; i <players.length; i++)
        {
            int j = i+1;
            String x = "Player " + j;
            players[i] = new Player(x);
        }
    }
    
    public void playerMove()
    {
        
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
        if(aevt.getSource() == Robot.robotMoved())
        {
            current.move();
            play();
        }
    }
    
    public void play()
    {
        System.out.println("you moved");
        if(current.getMoves() >=maxMoves)
        {
           changePlayer(); 
        }
    }
    //change the current player
    public void changePlayer()
    {
        goalReached = false;
        currentIndex++;
        if(currentIndex < numPlayers)
        {
            current = players[currentIndex];
        }
    }
    //setup a new round
    public void newRound()
    {
        goalReached = false;
        resetPlayers();
        eState = new EasyMap();
        newGoal();
        currentIndex = 0;
        current = players[currentIndex];
        
    }
    
    //choose a new goal for the players
    public void newGoal()
    {
        int x = (int)(Math.random()*eState.getSpecialSquares().size());
        eState.setGoal(x);
        
    }
    
    //reset all players moves to 0 for a new round
        public void resetPlayers()
    {
        for(int i = 0; i <numPlayers;i++)
        {
            players[i].moveReset();
        }
    }
    
    //return a list of all players in the round
    public Player[] getPlayers()
    {
        return players;
    }
}
