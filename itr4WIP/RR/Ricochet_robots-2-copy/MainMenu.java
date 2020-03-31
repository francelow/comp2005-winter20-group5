import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The Main Menu for the Game that lets users choose how they want to play
 * the game
 * 
 * @author (Mohammad Hasan 201858685)
 *
 */
public class MainMenu extends JFrame implements ActionListener
{
    private JButton start;  //start button
    private JButton load;  //load button
    private JButton help;  //help button
    private JButton minus;  //minus button to reduce the number of players
    private JButton plus;  //plus button to inrease the number of players
    private int num_of_players = 1;  //number of human players playing
    private JPanel left;  //left side of the main menu window
    private JPanel up;  //top side of the main menu window
    private JPanel center;  //center of the main menu window
    private JLabel playerImage = new JLabel(setPlayerImage()); //image of the amount of human players currently chosen to play
    private JRadioButton simple;  //simple mode radio button 
    private JRadioButton difficult;  //difficult mode radio button
    private static JRadioButton colorVision;  //color vision assistance radio button
    //private JRadioButton hints;  //hints radio button
    
    public MainMenu()
    {
        super("Ricochet Robots");
        addButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
    
    public void addButtons()
    {
        Icon helpIcon = new ImageIcon(new ImageIcon("Resources/help.png").getImage());
        Icon startIcon = new ImageIcon(new ImageIcon("Resources/start.png").getImage());
        Icon loadIcon = new ImageIcon(new ImageIcon("Resources/load.png").getImage());
        ImageIcon banner = new ImageIcon(new ImageIcon("Resources/ricochet-robots.jpg").getImage());
        Icon players = new ImageIcon(new ImageIcon("Resources/players.png").getImage());
        
                setLayout(new BorderLayout());  //sets layout of the main menu window to borderlayout
                setPreferredSize(new Dimension(600,880));
        
        left = new JPanel(new GridLayout(0,1));
        up = new JPanel(new BorderLayout());
        center = new JPanel(new BorderLayout());
        JPanel playerSelection = new JPanel(new BorderLayout());
        minus = new JButton("-");
        plus = new JButton("+");
        minus.addActionListener(this);
        plus.addActionListener(this);
        start = new JButton(startIcon);
        start.addActionListener(this);
        help = new JButton(helpIcon);
        help.addActionListener(this);
        load = new JButton(loadIcon);
        load.addActionListener(this);
        
        //creating a button group for selecting difficulty of map
        ButtonGroup difficulty = new ButtonGroup();
        simple = new JRadioButton("Easy");
        simple.setSelected(true);
        difficulty.add(simple); 
        difficulty.add(difficult = new JRadioButton("Difficult"));
        JPanel difficultyPanel = new JPanel(new BorderLayout());
        difficultyPanel.add(simple, BorderLayout.NORTH);
        difficultyPanel.add(difficult, BorderLayout.SOUTH);
        
        start.setPreferredSize(new Dimension(300,100));
        
        //sets up the player selection part of the window
        playerSelection.add(minus, BorderLayout.WEST);
        playerSelection.add(plus, BorderLayout.EAST);
        playerSelection.add(new JLabel(players, JLabel.CENTER), BorderLayout.CENTER);
        playerImage.setPreferredSize(new Dimension(350,220));
        
        //adds components to the left panel of the window
        left.add(playerSelection);
        left.add(playerImage);
        left.add(load);
        left.add(help);
        //left.add(hints = new JRadioButton("Hints"));
        
        //adds components to the top panel of the window
        up.add(new JLabel("Board Design", JLabel.CENTER), BorderLayout.NORTH);
        up.add(difficultyPanel, BorderLayout.WEST);
        colorVision = new JRadioButton("Color Vision Support");
        up.add(colorVision, BorderLayout.EAST);
        
        //adds components to the center panel of the window
        center.add(up, BorderLayout.NORTH);
        center.add(start, BorderLayout.CENTER);
        
        //adds all the panels into the main menu window 
        add(new JLabel(banner), BorderLayout.NORTH);
        add(left, BorderLayout.WEST);
        add(center, BorderLayout.CENTER);
       
    }
    
    public static boolean isColorVision()
    {
        return colorVision.isSelected();
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
        //changes the amount of players and picture representing it if the minus or plus buttons are pressed
          if(aevt.getSource() == minus)
            {
            num_of_players--;
            if(num_of_players == 0)
            num_of_players = 1;
            
            playerImage.setIcon(setPlayerImage());
            repaint();
            }
            
            if(aevt.getSource() == plus)
            {
                num_of_players++;
                if(num_of_players == 5)
                num_of_players = 4;
            
                playerImage.setIcon(setPlayerImage());
                repaint();
            }
            
            if(aevt.getSource() == load)
            {
                dispose();
                new Load();
            }
            
            if(aevt.getSource() == help)
            {
                dispose();
                new Help();
            }
            
            if(aevt.getSource() == start)
            {
                if(simple.isSelected())
                {
                    dispose();
                    new Game("easy",num_of_players);
                }
                else
                    {
                     dispose();
                     //new DifficultMap();
                    }
            }
      
    }
    
    //changes the picture of how many human players are playing
    public ImageIcon setPlayerImage()
    {
        ImageIcon player1 = new ImageIcon(new ImageIcon("Resources/1human.png").getImage());
        ImageIcon player2 = new ImageIcon(new ImageIcon("Resources/2human.png").getImage());
        ImageIcon player3 = new ImageIcon(new ImageIcon("Resources/3human.png").getImage());
        ImageIcon player4 = new ImageIcon(new ImageIcon("Resources/4human.png").getImage());
        
        if(num_of_players == 1)
        return player1;
        repaint();
        
        if(num_of_players == 2)
        return player2;
        repaint();
        
        if(num_of_players == 3)
        return player3;
        repaint();
        
        if(num_of_players == 4)
        return player4;
        repaint();
        
        return null;
    }
}
