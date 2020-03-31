import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Color;
import java.util.*;

/**
 * Creates a simple board that can be either normal or color vision assisted
 *
 * @author (Mohammad Hasan 201858685)
 *
 */
public class EasyMap extends JFrame implements ActionListener, MouseListener
{
    private JPanel west;
    private static Square[][] boardSquares;
    private Robot redRobot;
    private Robot blueRobot;
    private Robot greenRobot;
    private Robot yellowRobot;
    private final int XLENGTH = 16;
    private final int YLENGTH = 16;
    private ArrayList<Square> specials;
    private Square goal;
    
    public EasyMap()
    {
        super("Ricochet Robots Easy");
        //holds a list of all the squares that are special squares
        specials = new ArrayList<Square>(); 
        
        //Panel where the board will be placed
        west = new JPanel();
        west.setLayout(new GridLayout(XLENGTH,YLENGTH));
        
        //if color vision support is selected then show the color vision supported board or else show board with normal colors
        if(!MainMenu.isColorVision())
        {
            makeBoard(); makeWalls(); makeSpecialSquares(); setMapEdgeWall(); 
            makeMiddleSquares(); setRobot();
        }
        else
        {
            makeColorVisionBoard(); makeColorVisionWalls(); makeColorVisionSpecialSquares(); setMapEdgeWall(); 
            makeMiddleSquares(); setColorVisionRobot();
        }
        
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(west, BorderLayout.WEST);
        
        setPreferredSize(new Dimension(750, 750));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true); 
    }
    
    // makes board with normal colors
    public void makeBoard()
    {
        Icon basicSquare = new ImageIcon(((new ImageIcon("Resources/basicSquare.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares = new Square[XLENGTH][YLENGTH];
        for(int column = 0; column <XLENGTH; column++)
        {
            for(int row=0; row <YLENGTH; row++)
            {
                boardSquares[column][row] = new Square(column,row,basicSquare,false,false,false,false,null);
                boardSquares[column][row].setSize(50,50);
                boardSquares[column][row].setIcons(basicSquare);               
                boardSquares[column][row].setOpaque(true);
                boardSquares[column][row].addActionListener(this);
                boardSquares[column][row].addMouseListener(this);
                boardSquares[column][row].setBorderPainted(false);
                boardSquares[column][row].setBorder(BorderFactory.createEmptyBorder());
                boardSquares[column][row].setBackground(Color.GRAY);
                west.add(boardSquares[column][row]);
            }
        }
    }
    
    // makes color vision assisted board
    public void makeColorVisionBoard()
    {
        Icon basicSquare = new ImageIcon(((new ImageIcon("Resources/Assisted/basicSquare.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares = new Square[XLENGTH][YLENGTH];
        for(int column = 0; column <XLENGTH; column++)
        {
            for(int row=0; row <YLENGTH; row++)
            {
                boardSquares[column][row] = new Square(column,row,basicSquare,false,false,false,false,null);
                boardSquares[column][row].setSize(50,50);
                boardSquares[column][row].setIcons(basicSquare);               
                boardSquares[column][row].setOpaque(true);
                boardSquares[column][row].addActionListener(this);
                boardSquares[column][row].addMouseListener(this);
                boardSquares[column][row].setBorderPainted(false);
                boardSquares[column][row].setBorder(BorderFactory.createEmptyBorder());
                boardSquares[column][row].setBackground(Color.GRAY); 
                west.add(boardSquares[column][row]);
            }
        }
    }
    
    //making specific spaces have the charectaristics of a wall
    public void makeWalls()
    {
        Icon leftWall = new ImageIcon(((new ImageIcon("Resources/leftWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon topWall = new ImageIcon(((new ImageIcon("Resources/topWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon bottomWall = new ImageIcon(((new ImageIcon("Resources/bottomWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon rightWall = new ImageIcon(((new ImageIcon("Resources/rightWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
    
        boardSquares[10][15].setIcons(bottomWall);
        boardSquares[10][15].setBottomWall(true);

        boardSquares[3][15].setIcons(bottomWall);
        boardSquares[3][15].setBottomWall(true);
     
        boardSquares[4][15].setIcons(topWall);
        boardSquares[4][15].setTopWall(true);

        boardSquares[0][1].setIcons(rightWall);
        boardSquares[0][1].setRightWall(true);
        
        boardSquares[15][6].setIcons(rightWall);
        boardSquares[15][6].setRightWall(true);
        
        boardSquares[15][7].setIcons(leftWall);
        boardSquares[15][7].setLeftWall(true);
        
        boardSquares[15][11].setIcons(rightWall);
        boardSquares[15][11].setRightWall(true);

        boardSquares[15][12].setIcons(leftWall);
        boardSquares[15][12].setLeftWall(true);
      
        boardSquares[11][15].setIcons(topWall);
        boardSquares[11][15].setTopWall(true);
       
        boardSquares[0][2].setIcons(leftWall);
        boardSquares[0][2].setLeftWall(true);
    
        boardSquares[0][11].setIcons(rightWall);
        boardSquares[0][11].setRightWall(true);
   
        boardSquares[0][12].setIcons(leftWall);
        boardSquares[0][12].setLeftWall(true);
    
        boardSquares[6][0].setIcons(topWall);
        boardSquares[6][0].setTopWall(true);
   
        boardSquares[5][0].setIcons(bottomWall);
        boardSquares[5][0].setBottomWall(true);

        boardSquares[12][0].setIcons(topWall);
        boardSquares[12][0].setTopWall(true);

        boardSquares[11][0].setIcons(bottomWall);
        boardSquares[11][0].setBottomWall(true);
    }
    
    //same as previous method but color vision assisted
    public void makeColorVisionWalls()
    {
        Icon leftWall = new ImageIcon(((new ImageIcon("Resources/Assisted/leftWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon topWall = new ImageIcon(((new ImageIcon("Resources/Assisted/topWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon bottomWall = new ImageIcon(((new ImageIcon("Resources/Assisted/bottomWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon rightWall = new ImageIcon(((new ImageIcon("Resources/Assisted/rightWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
    
        boardSquares[10][15].setIcons(bottomWall);
        boardSquares[10][15].setBottomWall(true);

        boardSquares[3][15].setIcons(bottomWall);
        boardSquares[3][15].setBottomWall(true);
     
        boardSquares[4][15].setIcons(topWall);
        boardSquares[4][15].setTopWall(true);

        boardSquares[0][1].setIcons(rightWall);
        boardSquares[0][1].setRightWall(true);
        
        boardSquares[15][6].setIcons(rightWall);
        boardSquares[15][6].setRightWall(true);
        
        boardSquares[15][7].setIcons(leftWall);
        boardSquares[15][7].setLeftWall(true);
        
        boardSquares[15][11].setIcons(rightWall);
        boardSquares[15][11].setRightWall(true);

        boardSquares[15][12].setIcons(leftWall);
        boardSquares[15][12].setLeftWall(true);
      
        boardSquares[11][15].setIcons(topWall);
        boardSquares[11][15].setTopWall(true);
       
        boardSquares[0][2].setIcons(leftWall);
        boardSquares[0][2].setLeftWall(true);
    
        boardSquares[0][11].setIcons(rightWall);
        boardSquares[0][11].setRightWall(true);
   
        boardSquares[0][12].setIcons(leftWall);
        boardSquares[0][12].setLeftWall(true);
    
        boardSquares[6][0].setIcons(topWall);
        boardSquares[6][0].setTopWall(true);
   
        boardSquares[5][0].setIcons(bottomWall);
        boardSquares[5][0].setBottomWall(true);

        boardSquares[12][0].setIcons(topWall);
        boardSquares[12][0].setTopWall(true);

        boardSquares[11][0].setIcons(bottomWall);
        boardSquares[11][0].setBottomWall(true);
    }
    
    //making the special squares on the board
    public void makeSpecialSquares()
    {
        Icon leftWall = new ImageIcon(((new ImageIcon("Resources/leftWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon topWall = new ImageIcon(((new ImageIcon("Resources/topWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon bottomWall = new ImageIcon(((new ImageIcon("Resources/bottomWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon rightWall = new ImageIcon(((new ImageIcon("Resources/rightWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        
        Icon redCircle = new ImageIcon(((new ImageIcon("Resources/redCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[1][4].setIcons(redCircle);
        boardSquares[1][4].setTopWall(true);
        boardSquares[1][4].setLeftWall(true);
        boardSquares[0][4].setBottomWall(true);
        boardSquares[0][4].setIcons(bottomWall);
        boardSquares[1][3].setRightWall(true);
        boardSquares[1][3].setIcons(rightWall);
        boardSquares[1][4].setSpecialSquare("redCircle");
        specials.add(boardSquares[1][4]);
        
   
        Icon redSquare = new ImageIcon(((new ImageIcon("Resources/redSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[1][13].setIcons(redSquare);
        boardSquares[1][13].setTopWall(true);
        boardSquares[1][13].setLeftWall(true);
        boardSquares[0][13].setBottomWall(true);
        boardSquares[0][13].setIcons(bottomWall);
        boardSquares[1][12].setRightWall(true);
        boardSquares[1][12].setIcons(rightWall);
        boardSquares[1][13].setSpecialSquare("redSquare");
        specials.add(boardSquares[1][13]);
       
        Icon greenTriangle = new ImageIcon(((new ImageIcon("Resources/greenTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[3][1].setIcons(greenTriangle);
        boardSquares[3][1].setTopWall(true);
        boardSquares[3][1].setRightWall(true);
        boardSquares[2][1].setBottomWall(true);
        boardSquares[2][1].setIcons(bottomWall);
        boardSquares[3][2].setLeftWall(true);
        boardSquares[3][2].setIcons(leftWall);
        boardSquares[3][1].setSpecialSquare("greenTriangle");
        specials.add(boardSquares[3][1]);
        

        Icon blueTriangle = new ImageIcon(((new ImageIcon("Resources/blueTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[3][9].setIcons(blueTriangle);
        boardSquares[3][9].setBottomWall(true);
        boardSquares[3][9].setRightWall(true);
        boardSquares[4][9].setTopWall(true);
        boardSquares[4][9].setIcons(topWall);
        boardSquares[3][10].setLeftWall(true);
        boardSquares[3][10].setIcons(leftWall);
        boardSquares[3][9].setSpecialSquare("blueTriangle");
        specials.add(boardSquares[3][9]);
        
 
        Icon yellowStar = new ImageIcon(((new ImageIcon("Resources/yellowStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[4][6].setIcons(yellowStar);
        boardSquares[4][6].setBottomWall(true);
        boardSquares[4][6].setRightWall(true);
        boardSquares[5][6].setTopWall(true);
        boardSquares[5][6].setIcons(topWall);
        boardSquares[4][7].setLeftWall(true);
        boardSquares[4][7].setIcons(leftWall);
        boardSquares[4][6].setSpecialSquare("yellowStar");
        specials.add(boardSquares[4][6]);
        

        Icon greenStar = new ImageIcon(((new ImageIcon("Resources/greenStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[5][14].setIcons(greenStar);
        boardSquares[5][14].setBottomWall(true);
        boardSquares[5][14].setLeftWall(true);
        boardSquares[6][14].setTopWall(true);
        boardSquares[6][14].setIcons(topWall);
        boardSquares[5][13].setRightWall(true);
        boardSquares[5][13].setIcons(rightWall);
        boardSquares[5][14].setSpecialSquare("greenStar");
        specials.add(boardSquares[5][14]);
        

        Icon blueSquare = new ImageIcon(((new ImageIcon("Resources/blueSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[6][3].setIcons(blueSquare);
        boardSquares[6][3].setBottomWall(true);
        boardSquares[6][3].setLeftWall(true);
        boardSquares[7][3].setTopWall(true);
        boardSquares[7][3].setIcons(topWall);
        boardSquares[6][2].setRightWall(true);
        boardSquares[6][2].setIcons(rightWall);
        boardSquares[6][3].setSpecialSquare("blueSquare");
        specials.add(boardSquares[6][3]);
        
        
        Icon yellowCircle = new ImageIcon(((new ImageIcon("Resources/yellowCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));       
        boardSquares[6][11].setIcons(yellowCircle);
        boardSquares[6][11].setTopWall(true);
        boardSquares[6][11].setRightWall(true);
        boardSquares[5][11].setBottomWall(true);
        boardSquares[5][11].setIcons(bottomWall);
        boardSquares[6][12].setLeftWall(true);
        boardSquares[6][12].setIcons(leftWall);
        boardSquares[6][11].setSpecialSquare("yellowCircle");
        specials.add(boardSquares[6][11]);
        

        Icon multivortex = new ImageIcon(((new ImageIcon("Resources/multivortex.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[8][5].setIcons(multivortex);
        boardSquares[8][5].setTopWall(true);
        boardSquares[8][5].setRightWall(true);
        boardSquares[7][5].setBottomWall(true);
        boardSquares[7][5].setIcons(bottomWall);
        boardSquares[8][6].setLeftWall(true);
        boardSquares[8][6].setIcons(leftWall);
        boardSquares[8][5].setSpecialSquare("multivortex");
        specials.add(boardSquares[8][5]);
        

        Icon blueCircle = new ImageIcon(((new ImageIcon("Resources/blueCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[9][1].setIcons(blueCircle);
        boardSquares[9][1].setBottomWall(true);
        boardSquares[9][1].setRightWall(true);
        boardSquares[10][1].setTopWall(true);
        boardSquares[10][1].setIcons(topWall);
        boardSquares[9][2].setLeftWall(true);
        boardSquares[9][2].setIcons(leftWall);
        boardSquares[9][1].setSpecialSquare("blueCircle");
        specials.add(boardSquares[9][1]);
        
        
        Icon yellowSquare = new ImageIcon(((new ImageIcon("Resources/yellowSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[9][14].setIcons(yellowSquare);
        boardSquares[9][14].setBottomWall(true);
        boardSquares[9][14].setRightWall(true);
        boardSquares[10][14].setTopWall(true);
        boardSquares[10][14].setIcons(topWall);
        boardSquares[9][15].setLeftWall(true);
        boardSquares[9][15].setIcons(leftWall);
        boardSquares[9][14].setSpecialSquare("yellowSquare");
        specials.add(boardSquares[9][14]);
        

        Icon greenSquare = new ImageIcon(((new ImageIcon("Resources/greenSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[10][4].setIcons(greenSquare);
        boardSquares[10][4].setBottomWall(true);
        boardSquares[10][4].setLeftWall(true);
        boardSquares[11][4].setTopWall(true);
        boardSquares[11][4].setIcons(topWall);
        boardSquares[10][3].setRightWall(true);
        boardSquares[10][3].setIcons(rightWall);
        boardSquares[10][4].setSpecialSquare("greenSquare");
        specials.add(boardSquares[10][4]);
        
      
        Icon redTriangle = new ImageIcon(((new ImageIcon("Resources/redTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[10][8].setIcons(redTriangle);
        boardSquares[10][8].setTopWall(true);
        boardSquares[10][8].setRightWall(true);
        boardSquares[9][8].setBottomWall(true);
        boardSquares[9][8].setIcons(bottomWall);
        boardSquares[10][9].setLeftWall(true);
        boardSquares[10][9].setIcons(leftWall);
        boardSquares[10][8].setSpecialSquare("redTriangle");
        specials.add(boardSquares[10][8]);
        

        Icon greenCircle = new ImageIcon(((new ImageIcon("Resources/greenCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[11][13].setIcons(greenCircle);
        boardSquares[11][13].setBottomWall(true);
        boardSquares[11][13].setLeftWall(true);
        boardSquares[12][13].setTopWall(true);
        boardSquares[12][13].setIcons(topWall);
        boardSquares[11][12].setRightWall(true);
        boardSquares[11][12].setIcons(rightWall);
        boardSquares[11][13].setSpecialSquare("greenCircle");
        specials.add(boardSquares[11][13]);
        

        Icon redStar = new ImageIcon(((new ImageIcon("Resources/redStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[13][5].setIcons(redStar);
        boardSquares[13][5].setTopWall(true);
        boardSquares[13][5].setRightWall(true);
        boardSquares[12][5].setBottomWall(true);
        boardSquares[12][5].setIcons(bottomWall);
        boardSquares[13][6].setLeftWall(true);
        boardSquares[13][6].setIcons(leftWall);
        boardSquares[13][5].setSpecialSquare("redStar");
        specials.add(boardSquares[13][5]);
        
        
        Icon blueStar = new ImageIcon(((new ImageIcon("Resources/blueStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[13][10].setIcons(blueStar);
        boardSquares[13][10].setTopWall(true);
        boardSquares[13][10].setLeftWall(true);
        boardSquares[12][10].setBottomWall(true);
        boardSquares[12][10].setIcons(bottomWall);
        boardSquares[13][9].setRightWall(true);
        boardSquares[13][9].setIcons(rightWall);
        boardSquares[13][10].setSpecialSquare("blueStar");
        specials.add(boardSquares[13][10]);


        Icon yellowTriangle = new ImageIcon(((new ImageIcon("Resources/yellowTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[14][3].setIcons(yellowTriangle);
        boardSquares[14][3].setTopWall(true);
        boardSquares[14][3].setLeftWall(true);
        boardSquares[13][3].setBottomWall(true);
        boardSquares[13][3].setIcons(bottomWall);
        boardSquares[14][2].setRightWall(true);
        boardSquares[14][2].setIcons(rightWall);
        boardSquares[14][3].setSpecialSquare("yellowTriangle");
        specials.add(boardSquares[14][3]);
    }
    
    //same as above method but with color vision assistance
    public void makeColorVisionSpecialSquares()
    {
        Icon leftWall = new ImageIcon(((new ImageIcon("Resources/Assisted/leftWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon topWall = new ImageIcon(((new ImageIcon("Resources/Assisted/topWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon bottomWall = new ImageIcon(((new ImageIcon("Resources/Assisted/bottomWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        Icon rightWall = new ImageIcon(((new ImageIcon("Resources/Assisted/rightWall.png")).getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        
        Icon redCircle = new ImageIcon(((new ImageIcon("Resources/Assisted/redCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[1][4].setIcons(redCircle);
        boardSquares[1][4].setTopWall(true);
        boardSquares[1][4].setLeftWall(true);
        boardSquares[0][4].setBottomWall(true);
        boardSquares[0][4].setIcons(bottomWall);
        boardSquares[1][3].setRightWall(true);
        boardSquares[1][3].setIcons(rightWall);
        boardSquares[1][4].setSpecialSquare("redCircle");
        specials.add(boardSquares[1][4]);
        
   
        Icon redSquare = new ImageIcon(((new ImageIcon("Resources/Assisted/redSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[1][13].setIcons(redSquare);
        boardSquares[1][13].setTopWall(true);
        boardSquares[1][13].setLeftWall(true);
        boardSquares[0][13].setBottomWall(true);
        boardSquares[0][13].setIcons(bottomWall);
        boardSquares[1][12].setRightWall(true);
        boardSquares[1][12].setIcons(rightWall);
        boardSquares[1][13].setSpecialSquare("redSquare");
        specials.add(boardSquares[1][13]);
        
       
        Icon greenTriangle = new ImageIcon(((new ImageIcon("Resources/Assisted/greenTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[3][1].setIcons(greenTriangle);
        boardSquares[3][1].setTopWall(true);
        boardSquares[3][1].setRightWall(true);
        boardSquares[2][1].setBottomWall(true);
        boardSquares[2][1].setIcons(bottomWall);
        boardSquares[3][2].setLeftWall(true);
        boardSquares[3][2].setIcons(leftWall);
        boardSquares[3][1].setSpecialSquare("greenTriangle");
        specials.add(boardSquares[3][1]);

        

        Icon blueTriangle = new ImageIcon(((new ImageIcon("Resources/Assisted/blueTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        boardSquares[3][9].setIcons(blueTriangle);
        boardSquares[3][9].setBottomWall(true);
        boardSquares[3][9].setRightWall(true);
        boardSquares[4][9].setTopWall(true);
        boardSquares[4][9].setIcons(topWall);
        boardSquares[3][10].setLeftWall(true);
        boardSquares[3][10].setIcons(leftWall);
        boardSquares[3][9].setSpecialSquare("blueTriangle");
        specials.add(boardSquares[3][9]);
        
 
        Icon yellowStar = new ImageIcon(((new ImageIcon("Resources/Assisted/yellowStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[4][6].setIcons(yellowStar);
        boardSquares[4][6].setBottomWall(true);
        boardSquares[4][6].setRightWall(true);
        boardSquares[5][6].setTopWall(true);
        boardSquares[5][6].setIcons(topWall);
        boardSquares[4][7].setLeftWall(true);
        boardSquares[4][7].setIcons(leftWall);
        boardSquares[4][6].setSpecialSquare("yellowStar");
        specials.add(boardSquares[4][6]);
        

        Icon greenStar = new ImageIcon(((new ImageIcon("Resources/Assisted/greenStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[5][14].setIcons(greenStar);
        boardSquares[5][14].setBottomWall(true);
        boardSquares[5][14].setLeftWall(true);
        boardSquares[6][14].setTopWall(true);
        boardSquares[6][14].setIcons(topWall);
        boardSquares[5][13].setRightWall(true);
        boardSquares[5][13].setIcons(rightWall);
        boardSquares[5][14].setSpecialSquare("greenStar");
        specials.add(boardSquares[5][14]);
        

        Icon blueSquare = new ImageIcon(((new ImageIcon("Resources/Assisted/blueSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[6][3].setIcons(blueSquare);
        boardSquares[6][3].setBottomWall(true);
        boardSquares[6][3].setLeftWall(true);
        boardSquares[7][3].setTopWall(true);
        boardSquares[7][3].setIcons(topWall);
        boardSquares[6][2].setRightWall(true);
        boardSquares[6][2].setIcons(rightWall);
        boardSquares[6][3].setSpecialSquare("blueSquare");
        specials.add(boardSquares[6][3]);
        
        
        Icon yellowCircle = new ImageIcon(((new ImageIcon("Resources/Assisted/yellowCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));       
        boardSquares[6][11].setIcons(yellowCircle);
        boardSquares[6][11].setTopWall(true);
        boardSquares[6][11].setRightWall(true);
        boardSquares[5][11].setBottomWall(true);
        boardSquares[5][11].setIcons(bottomWall);
        boardSquares[6][12].setLeftWall(true);
        boardSquares[6][12].setIcons(leftWall);
        boardSquares[6][11].setSpecialSquare("yellowCircle");
        specials.add(boardSquares[6][11]);
        

        Icon multivortex = new ImageIcon(((new ImageIcon("Resources/Assisted/multivortex.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[8][5].setIcons(multivortex);
        boardSquares[8][5].setTopWall(true);
        boardSquares[8][5].setRightWall(true);
        boardSquares[7][5].setBottomWall(true);
        boardSquares[7][5].setIcons(bottomWall);
        boardSquares[8][6].setLeftWall(true);
        boardSquares[8][6].setIcons(leftWall);
        boardSquares[8][5].setSpecialSquare("multivortex");
        specials.add(boardSquares[8][5]);
        

        Icon blueCircle = new ImageIcon(((new ImageIcon("Resources/Assisted/blueCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[9][1].setIcons(blueCircle);
        boardSquares[9][1].setBottomWall(true);
        boardSquares[9][1].setRightWall(true);
        boardSquares[10][1].setTopWall(true);
        boardSquares[10][1].setIcons(topWall);
        boardSquares[9][2].setLeftWall(true);
        boardSquares[9][2].setIcons(leftWall);
        boardSquares[9][1].setSpecialSquare("blueCircle");
        specials.add(boardSquares[9][1]);
        
        
        Icon yellowSquare = new ImageIcon(((new ImageIcon("Resources/Assisted/yellowSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[9][14].setIcons(yellowSquare);
        boardSquares[9][14].setBottomWall(true);
        boardSquares[9][14].setRightWall(true);
        boardSquares[10][14].setTopWall(true);
        boardSquares[10][14].setIcons(topWall);
        boardSquares[9][15].setLeftWall(true);
        boardSquares[9][15].setIcons(leftWall);
        boardSquares[9][14].setSpecialSquare("yellowSquare");
        specials.add(boardSquares[9][14]);
        

        Icon greenSquare = new ImageIcon(((new ImageIcon("Resources/Assisted/greenSquare.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[10][4].setIcons(greenSquare);
        boardSquares[10][4].setBottomWall(true);
        boardSquares[10][4].setLeftWall(true);
        boardSquares[11][4].setTopWall(true);
        boardSquares[11][4].setIcons(topWall);
        boardSquares[10][3].setRightWall(true);
        boardSquares[10][3].setIcons(rightWall);
        boardSquares[10][4].setSpecialSquare("greenSquare");
        specials.add(boardSquares[10][4]);
        
      
        Icon redTriangle = new ImageIcon(((new ImageIcon("Resources/Assisted/redTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[10][8].setIcons(redTriangle);
        boardSquares[10][8].setTopWall(true);
        boardSquares[10][8].setRightWall(true);
        boardSquares[9][8].setBottomWall(true);
        boardSquares[9][8].setIcons(bottomWall);
        boardSquares[10][9].setLeftWall(true);
        boardSquares[10][9].setIcons(leftWall);
        boardSquares[10][8].setSpecialSquare("redTriangle");
        specials.add(boardSquares[10][8]);
        

        Icon greenCircle = new ImageIcon(((new ImageIcon("Resources/Assisted/greenCircle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[11][13].setIcons(greenCircle);
        boardSquares[11][13].setBottomWall(true);
        boardSquares[11][13].setLeftWall(true);
        boardSquares[12][13].setTopWall(true);
        boardSquares[12][13].setIcons(topWall);
        boardSquares[11][12].setRightWall(true);
        boardSquares[11][12].setIcons(rightWall);
        boardSquares[11][13].setSpecialSquare("greenCircle");
        specials.add(boardSquares[11][13]);
        

        Icon redStar = new ImageIcon(((new ImageIcon("Resources/Assisted/redStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[13][5].setIcons(redStar);
        boardSquares[13][5].setTopWall(true);
        boardSquares[13][5].setRightWall(true);
        boardSquares[12][5].setBottomWall(true);
        boardSquares[12][5].setIcons(bottomWall);
        boardSquares[13][6].setLeftWall(true);
        boardSquares[13][6].setIcons(leftWall);
        boardSquares[13][5].setSpecialSquare("redStar");
        specials.add(boardSquares[13][5]);
        
        
        Icon blueStar = new ImageIcon(((new ImageIcon("Resources/Assisted/blueStar.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[13][10].setIcons(blueStar);
        boardSquares[13][10].setTopWall(true);
        boardSquares[13][10].setLeftWall(true);
        boardSquares[12][10].setBottomWall(true);
        boardSquares[12][10].setIcons(bottomWall);
        boardSquares[13][9].setRightWall(true);
        boardSquares[13][9].setIcons(rightWall);
        boardSquares[13][10].setSpecialSquare("blueStar");
        specials.add(boardSquares[13][10]);


        Icon yellowTriangle = new ImageIcon(((new ImageIcon("Resources/Assisted/yellowTriangle.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));        
        boardSquares[14][3].setIcons(yellowTriangle);
        boardSquares[14][3].setTopWall(true);
        boardSquares[14][3].setLeftWall(true);
        boardSquares[13][3].setBottomWall(true);
        boardSquares[13][3].setIcons(bottomWall);
        boardSquares[14][2].setRightWall(true);
        boardSquares[14][2].setIcons(rightWall);
        boardSquares[14][3].setSpecialSquare("yellowTriangle");
        specials.add(boardSquares[14][3]);
    }
    
    //sets the edges of the map as walls so the robots dont "fall off"
    public void setMapEdgeWall()
    {
        for(int column = 0; column < 16; column++)
        {
            boardSquares[column][0].setLeftWall(true);
        }
        
        for(int column = 0; column < 16; column++)
        {
            boardSquares[column][15].setRightWall(true);
        }
        
        for(int row = 0; row < 16; row++)
        {
            boardSquares[0][row].setTopWall(true);
        }
        
        for(int row = 0; row < 16; row++)
        {
            boardSquares[15][row].setBottomWall(true);
        }
    }
    
    //sets walls in the middle of the map so the robots dont go in it. This code 
    // be edited to show the special square the robots will need to go to
    public void makeMiddleSquares()
    {
        Icon black = new ImageIcon(((new ImageIcon("Resources/black.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        
        boardSquares[7][7].setIcons(black);
        boardSquares[7][7].setTopWall(true);
        boardSquares[7][7].setLeftWall(true);
        boardSquares[7][7].setBottomWall(true);
        boardSquares[7][7].setRightWall(true);
        boardSquares[6][7].setBottomWall(true);
        boardSquares[7][6].setRightWall(true);
        
        boardSquares[7][8].setIcons(black);
        boardSquares[7][8].setTopWall(true);
        boardSquares[7][8].setLeftWall(true);
        boardSquares[7][8].setBottomWall(true);
        boardSquares[7][8].setRightWall(true);
        boardSquares[6][8].setBottomWall(true);
        boardSquares[7][9].setLeftWall(true);
        
        boardSquares[8][7].setIcons(black);
        boardSquares[8][7].setTopWall(true);
        boardSquares[8][7].setLeftWall(true);
        boardSquares[8][7].setBottomWall(true);
        boardSquares[8][7].setRightWall(true);
        boardSquares[8][6].setRightWall(true);
        boardSquares[9][7].setTopWall(true);
        
        boardSquares[8][8].setIcons(black);
        boardSquares[8][8].setTopWall(true);
        boardSquares[8][8].setLeftWall(true);
        boardSquares[8][8].setBottomWall(true);
        boardSquares[8][8].setRightWall(true);
        boardSquares[9][8].setTopWall(true);
        boardSquares[8][9].setLeftWall(true);
    }
    
    //sets the robots on the board in a random space
    public void setRobot()
    {       
        ImageIcon red = new ImageIcon(((new ImageIcon("Resources/redRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon yellow = new ImageIcon(((new ImageIcon("Resources/yellowRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon blue = new ImageIcon(((new ImageIcon("Resources/blueRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon green = new ImageIcon(((new ImageIcon("Resources/greenRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        
        redRobot = new Robot(red);
        blueRobot = new Robot(blue);
        yellowRobot = new Robot(yellow);
        greenRobot = new Robot(green);
        
        int x = redRobot.getX();
        int y = redRobot.getY(); 
        Icon tempIcon = redRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.RED);
        boardSquares[x][y].changeOccupied();
        
        x = yellowRobot.getX();
        y = yellowRobot.getY(); 
        tempIcon = yellowRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.YELLOW);
        boardSquares[x][y].changeOccupied();
        
        x = greenRobot.getX();
        y = greenRobot.getY(); 
        tempIcon = greenRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.GREEN);
        boardSquares[x][y].changeOccupied();
        
        x = blueRobot.getX();
        y = blueRobot.getY(); 
        tempIcon = blueRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.BLUE);
        boardSquares[x][y].changeOccupied();
    }
    
    //same as previous method but with color vision support
    public void setColorVisionRobot()
    {       
        ImageIcon red = new ImageIcon(((new ImageIcon("Resources/Assisted/redRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon yellow = new ImageIcon(((new ImageIcon("Resources/Assisted/yellowRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon blue = new ImageIcon(((new ImageIcon("Resources/Assisted/blueRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        ImageIcon green = new ImageIcon(((new ImageIcon("Resources/Assisted/greenRobot.png"))
        .getImage()).getScaledInstance(43,43,java.awt.Image.SCALE_SMOOTH));
        
        redRobot = new Robot(red);
        blueRobot = new Robot(blue);
        yellowRobot = new Robot(yellow);
        greenRobot = new Robot(green);
        
        int x = redRobot.getX();
        int y = redRobot.getY(); 
        Icon tempIcon = redRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.GRAY);
        boardSquares[x][y].changeOccupied();
        
        x = yellowRobot.getX();
        y = yellowRobot.getY(); 
        tempIcon = yellowRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.GRAY);
        boardSquares[x][y].changeOccupied();
        
        x = greenRobot.getX();
        y = greenRobot.getY(); 
        tempIcon = greenRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.GRAY);
        boardSquares[x][y].changeOccupied();
        
        x = blueRobot.getX();
        y = blueRobot.getY(); 
        tempIcon = blueRobot.getIcon();
        boardSquares[x][y].setIcons(tempIcon);
        boardSquares[x][y].setBackground(Color.GRAY);
        boardSquares[x][y].changeOccupied();
    }
    
    //a mathod to change the icon on a given square on the board
    //This is mainly used to move the robot from on block to another by
    //replacing the image of the square on the board with that of the robot
    public static void changeIcon(int x, int y, Icon icon)
    {
        boardSquares[x][y].setIcons(icon);
        boardSquares[x][y].setIcon(icon);
    }
    
    //returns a particular square in the board
    public static Square getSquare(int x, int y)
    {
        return boardSquares[x][y];
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
     
    }
    
    public void mouseClicked(MouseEvent mevt)
    {
        Object selected = mevt.getSource();
        if(selected instanceof Square)
        {
            Square square = ((Square) selected);
            int x = square.getX();
            int y = square.getY();
            if ((x == redRobot.getX() && y == redRobot.getY())
            && (!blueRobot.isSelected() && !greenRobot.isSelected()
            && !yellowRobot.isSelected()))
            {
                redRobot.changeSelected();
            }
            
            if (redRobot.isSelected() && (x == redRobot.getX() ||
            y == redRobot.getY()) && !(x ==redRobot.getX() &&
            y == redRobot.getY()))
            {
                redRobot.move(x,y);
            }
            
            if ((x == blueRobot.getX() && y == blueRobot.getY())
            && (!redRobot.isSelected() && !greenRobot.isSelected()
            && !yellowRobot.isSelected()))
            {
                blueRobot.changeSelected();
            }
            
            if ((x == yellowRobot.getX() && y == yellowRobot.getY())
            && (!blueRobot.isSelected() && !greenRobot.isSelected()
            && !redRobot.isSelected()))
            {
                yellowRobot.changeSelected();
            }
            
            if ((x == greenRobot.getX() && y == greenRobot.getY())
            && (!blueRobot.isSelected() && !redRobot.isSelected()
            && !yellowRobot.isSelected()))
            {
                greenRobot.changeSelected();
            }
            
            if (blueRobot.isSelected() && (x == blueRobot.getX() ||
            y == blueRobot.getY()) && !(x ==blueRobot.getX() &&
            y == blueRobot.getY()))
            {
                blueRobot.move(x,y);
            }
            
            if (greenRobot.isSelected() && (x == greenRobot.getX() ||
            y == greenRobot.getY()) && !(x ==greenRobot.getX() &&
            y == greenRobot.getY()))
            {
                greenRobot.move(x,y);
            }
            
            if (yellowRobot.isSelected() && (x == yellowRobot.getX() ||
            y == yellowRobot.getY()) && !(x ==yellowRobot.getX() &&
            y == yellowRobot.getY()))
            {
                yellowRobot.move(x,y);
            }
        }
    }
    //provides a list of all special squares
    
    public ArrayList<Square> getSpecialSquares()
    {
        return specials;
    }
    
    public void setGoal(int x)
    {
        goal = specials.get(x);
    }
    
    public Square getGoal()
    {
        return goal;
    }
    
    public void mouseEntered(MouseEvent arg0){}
    public void mouseExited(MouseEvent arg0){}
    public void mousePressed(MouseEvent arg0){}
    public void mouseReleased(MouseEvent arg0){}

}
