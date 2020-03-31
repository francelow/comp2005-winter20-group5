import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Load class that lets you load the progress of a saved game
 *
 * @author (Mohammad Hasan 201858685)
 * 
 */
public class Load extends JFrame implements ActionListener
{
     private JButton back;
    
    public Load()
    {
        super("Load Game");
        
        Icon backIcon = new ImageIcon(new ImageIcon("Resources/back.png").getImage());
        back = new JButton(backIcon);
        back.addActionListener(this);
        back.setPreferredSize(new Dimension(190,90));
        
        JPanel top = new JPanel();
        top.add(back, BorderLayout.NORTH);
        
        add(top);
        
        setPreferredSize(new Dimension(500,500));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
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
