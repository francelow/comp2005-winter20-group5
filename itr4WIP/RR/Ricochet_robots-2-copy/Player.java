
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Player
{
    private String name;
    private int score;
    private int moves;
    private boolean turn;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name)
    {
        this.name = name;
        score = 0;
        moves = 0;
        turn = false;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getMoves()
    {
        return moves;
    }
    
    public boolean getTurn()
    {
        return turn;
    }
    
    public void move()
    {
        moves++;
    }
    
    public void moveReset()
    {
        moves = 0;
    }
}
