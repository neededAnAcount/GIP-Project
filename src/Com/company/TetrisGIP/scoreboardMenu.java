package Com.company.TetrisGIP;

import javax.swing.*;
import java.awt.*;

public class scoreboardMenu extends JPanel {
    private JFrame frame;
    private JLabel info;
    private JButton scoreboardEasy;
    private JButton scoreboardHard;
    private JButton cancelButton;


    /**
     * this will load the menu for the player to chose which database to load and display
     */

    public scoreboardMenu() {
        //load the frame for the menu to show up on and set the settings for the frame
        frame = new JFrame("Scoreboardmenu");
        frame.setLayout(new GridBagLayout()); // sets the layout for the frame
        frame.setSize(Window.WIDTH, Window.HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //set constraints for the layout to make buttons display nicely
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER; //sets the with of the layout grid
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);//set the space between components


        info = new JLabel("Select the gamemode of which you want to display the scoreboard"); // displays info to player
        scoreboardEasy = new JButton("easy mode");
        scoreboardEasy.setSize(100, 100);//set the size of the component
        scoreboardEasy.setAlignmentX(Component.CENTER_ALIGNMENT); //aligns the component to the center of the frame
        frame.add(scoreboardEasy, gbc);
        scoreboardEasy.addActionListener(e -> {
            if (scoreboardEasy.isEnabled()) {
                //cleans the jframe of all its components
                //revalidate the jframe
                //then starts the process of displaying the scoreboard
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();

            }
        });

        scoreboardHard = new JButton("hard mode");
        scoreboardHard.setSize(100, 100);//set the size of the component
        scoreboardHard.setAlignmentX(Component.CENTER_ALIGNMENT);//aligns the component to the center of the frame
        frame.add(scoreboardHard, gbc);
        scoreboardHard.addActionListener(e -> {
            if (scoreboardHard.isEnabled()) {
                //cleans the jframe of all its components
                //revalidate the jframe
                //then starts the process of displaying the scoreboard
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
            }
        });

        cancelButton = new JButton("main menu");
        cancelButton.setSize(100, 100);//set the size of the component


        frame.add(cancelButton, gbc);
        cancelButton.addActionListener(e -> {
            if (cancelButton.isEnabled()) {
                //closes the jframe and displays the main menu
                frame.dispose();
            }
        });

    }


}
