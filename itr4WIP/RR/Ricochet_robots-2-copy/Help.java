import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Help class that gives information on how to play the game
 *
 * @author (Mohammad Hasan 201858685)
 * 
 */
public class Help extends JFrame implements ActionListener
{
    private JButton back;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JPanel center;
    private JPanel top;
    
    public Help()
    {
        super("Help");
        
        Icon backIcon = new ImageIcon(new ImageIcon("Resources/back.png").getImage());
        back = new JButton(backIcon);
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(190,90));
        
        center = new JPanel();
        top = new JPanel();
        
        createHelp();
        
        top.add(back);
        
        setLayout(new BorderLayout());
        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        
        setPreferredSize(new Dimension(500,270));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);       
    }

    public void createHelp()
    {
        label1 = new JLabel("How to move a robot:");
        label2 = new JLabel("Select the robot you want to move. only one robot can be selected at a time");
        label3 = new JLabel("Press on either the row or the column in the direction you want to move the robot");
        label4 = new JLabel("Click on the robot again to unselect it");
        center.add(label1);
        center.add(label2);
        center.add(label3);
        center.add(label4);
    }
    
    public void actionPerformed(ActionEvent aevt)
    {
        if(aevt.getSource() == back)
        {
            dispose();
            new MainMenu();
        }
    }
}
