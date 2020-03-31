import javax.swing.*;
/**
 * Square class that represents a specific square on the board with an x and y
 * coordinate and other charectaristics
 * @author (Mohammad Hasan201858685)
 * @version (a version number or a date)
 */
public class Square extends JButton
{
    private int xCoord;  //x coordinate
    private int yCoord;  //y coordinate
    private boolean topWall, bottomWall, leftWall, rightWall;
    private boolean occupied;
    private Icon squareIcon;
    private String specialSquare;
    private boolean isGoal;
    
   
    public Square(int x, int y, Icon icon, boolean top, boolean bottom,
    boolean left, boolean right, String specialSquare)
    {
       super();
       xCoord = x;
       yCoord = y;
       squareIcon = icon;
       topWall = top;
       bottomWall= bottom;
       leftWall = left;
       rightWall = right;
       this.specialSquare = specialSquare;
       occupied = false;
       isGoal = false;
    }
    
    //checks if square has a wall on the left
    public boolean isLeftWall()
    {
        return leftWall;
    }
    
    //checks if square has a wall on the right
    public boolean isRightWall()
    {
        return rightWall;
    }
    
    //checks if square has a wall on top
    public boolean isTopWall()
    {
        return topWall;
    }
    
    //checks if square has a wall on the bottom
    public boolean isBottomWall()
    {
        return bottomWall;
    }
    
    //sets the square to have a wall on top
     public void setTopWall(boolean bool)
    {
        topWall = bool;
    }
    
    //sets the square to have a wall on the bottom
    public void setBottomWall(boolean bool)
    {
        bottomWall = bool;
    }
    
    //sets the square to have a wall on the left
    public void setLeftWall(boolean bool)
    {
        leftWall = bool;
    }
    
    //sets the square to have a wall on the right
    public void setRightWall(boolean bool)
    {
        rightWall = bool;
    }
    
    //returns the icon of the square
    public Icon getIcon()
    {
        return squareIcon; 
    }
   
    //sets the icon of the square
    public void setIcons(Icon icon)
    {
        squareIcon = icon;
    }
    
    //checks if the square is currently occupied by a robot
    public boolean isOccupied()
    {
       return occupied;
    }
    
    //changes the status of the square to reflect if there is a robot on it or not
    public void changeOccupied()
    {
        if(occupied)
        occupied = false;
        else
        occupied = true;
    }
    
    //returns the y coordinate of the square
    public int getY()
    {
        return yCoord;
    }
    
    //returns the x coordinate of the square
    public int getX()
    {
        return xCoord;
    }

    //makes the square into a special square. Squares that have no special 
    //charecteristic (basic squares) have null in the speicalSquare variable
    public void setSpecialSquare(String spec)
    {
        specialSquare = spec;
    }
    //sets the current square to the goal square
    public void setGoal()
    {
        if (isGoal)
        {
            isGoal = false;
        }
        else 
        {
            isGoal = true;
        }
    }
    //returns true if the current square is the goal
    public boolean getGoal()
    {
        return isGoal;
    }
}
