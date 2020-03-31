import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

/**
 * Class that represents a robot with an x and y coordinate
 *
 * @author (Mohammad Hasan 201858685)
 * 
 */
public class Robot //implements ActionListener
{
    private JButton robot;
    private int x;
    private int y;
    private ImageIcon rIcon;
    private int num_of_moves;
    private boolean selected = false;
    private Icon oldTemp;
    private Icon newTemp;
    
    //makes a robot object with the appropriate icon and sets it in a random
    //position that is not a special square or the center of the board
    public Robot(ImageIcon icon)
    {
       //place robot in a random position excluding middle of the board 
       x = (int)(Math.random()*16);
       y = (int)(Math.random()*16);
       while((x == 7 && y == 7) 
        || (x == 7 && y == 8) 
        || (x == 8 && y == 7) 
        || (x == 8 && y == 8)
        || (x == 1 && y == 4)
        || (x == 1 && y == 13)
        || (x == 3 && y == 1)
        || (x == 3 && y == 9)
        || (x == 4 && y == 6)
        || (x == 5 && y == 14)
        || (x == 6 && y == 3)
        || (x == 6 && y == 11)
        || (x == 8 && y == 5)
        || (x == 9 && y == 1)
        || (x == 9 && y == 14)
        || (x == 10 && y == 4)
        || (x == 10 && y == 8)
        || (x == 11 && y == 13)
        || (x == 13 && y == 5)
        || (x == 13 && y == 10)
        || (x == 14 && y == 3)
        )
        {
            x = (int)(Math.random()*16);
            y = (int)(Math.random()*16);
        }
        
        //sets robot icon
        rIcon = icon;
    }
    
    //returns the x coordinate of the robot
     public int getX()
    {
        return x;
    }
    
    //returns the y coordinate of the robot
    public int getY()
    {
        return y;
    }

    //returns the icon of the robot
     public ImageIcon getIcon()
    {
        return rIcon;
    }
    
    //changes the status of a robot from selected to unselected and vice versa
    public void changeSelected()
    {
        if(selected)
        selected = false;
        else
        selected = true;
    }
    
    //method used to check if robot is selected or not
    public boolean isSelected()
    {
        return selected;
    }
    
    //moves the robot depending on where the user clicked
    public void move(int x, int y)
    {
        if( y < this.y)
        {
            int initialx = this.x;
            int initialy = this.y;
            
            while(!EasyMap.getSquare(this.x,this.y).isLeftWall() &&
            !EasyMap.getSquare(this.x, this.y-1).isOccupied())
            {
               this.y--;
            }
            
            
            
            if(this.y != initialy)
            {
                oldTemp = newTemp;
                newTemp = EasyMap.getSquare(this.x,this.y).getIcon();
                EasyMap.getSquare(initialx,initialy).changeOccupied();
                EasyMap.changeIcon(initialx,initialy,oldTemp); 
                EasyMap.changeIcon(this.x, this.y, rIcon);        
                EasyMap.getSquare(this.x,this.y).changeOccupied();
            }
        }
        
        else if( y > this.y)
        {   
            int initialx = this.x;
            int initialy = this.y;
            
            while(!EasyMap.getSquare(this.x,this.y).isRightWall() &&
            !EasyMap.getSquare(this.x, this.y+1).isOccupied())
            {
               this.y++;
            }
            
            
            
            if(this.y != initialy)
            {
                oldTemp = newTemp;
                newTemp = EasyMap.getSquare(this.x,this.y).getIcon();
                EasyMap.getSquare(initialx,initialy).changeOccupied();
                EasyMap.changeIcon(initialx,initialy,oldTemp); 
                EasyMap.changeIcon(this.x, this.y, rIcon);        
                EasyMap.getSquare(this.x,this.y).changeOccupied();
            }
        }
        
        else if( x < this.x)
        {
            int initialx = this.x;
            int initialy = this.y;
            
            while(!EasyMap.getSquare(this.x,this.y).isTopWall() &&
            !EasyMap.getSquare(this.x-1, this.y).isOccupied())
            {
               this.x--;
            }
            
            
            
            if(this.x != initialx)
            {
                oldTemp = newTemp;
                newTemp = EasyMap.getSquare(this.x,this.y).getIcon();
                EasyMap.getSquare(initialx,initialy).changeOccupied();
                EasyMap.changeIcon(initialx,initialy,oldTemp); 
                EasyMap.changeIcon(this.x, this.y, rIcon);        
                EasyMap.getSquare(this.x,this.y).changeOccupied();
            }
        }
        
        else if( x > this.x)
        {
            int initialx = this.x;
            int initialy = this.y;
            
            while(!EasyMap.getSquare(this.x,this.y).isBottomWall() &&
            !EasyMap.getSquare(this.x+1, this.y).isOccupied())
            {
               this.x++;
            }
            
            if(this.x != initialx)
            {
                oldTemp = newTemp;
                newTemp = EasyMap.getSquare(this.x,this.y).getIcon();
                EasyMap.getSquare(initialx,initialy).changeOccupied();
                EasyMap.changeIcon(initialx,initialy,oldTemp);
                EasyMap.changeIcon(this.x, this.y, rIcon);        
                EasyMap.getSquare(this.x,this.y).changeOccupied();
            }
            
        }
        robotMoved();
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
        if(aevt.getSource() == rIcon){
         changeSelected();
        }
    }
    
    public static String robotMoved()
    {
        return "yes";
    }
}
