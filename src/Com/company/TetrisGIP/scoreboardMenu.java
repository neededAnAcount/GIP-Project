package Com.company.TetrisGIP;

import javax.swing.*;
import java.awt.*;

public class scoreboardMenu extends JPanel {
    private JFrame frame;
    private JLabel info;
    private JButton scoreboardEasy;
    private JButton scoreboardHard;
    private JButton cancelButton;


    public scoreboardMenu() {
        frame = new JFrame("Scoreboardmenu");
        frame.setLayout(new GridBagLayout());
        frame.setSize(Window.WIDTH, Window.HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);


        info = new JLabel("Select the gamemode of which you want to display the scoreboard");
        scoreboardEasy = new JButton("easy mode");
        scoreboardEasy.setSize(100, 100);
        scoreboardEasy.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(scoreboardEasy, gbc);
        scoreboardEasy.addActionListener(e -> {
            if (scoreboardEasy.isEnabled()) {
                System.out.println("dit werkt idioot");
                //cleans the jframe of all its components
                //then starts the process of displaying the scoreboard
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();

            }
        });

        scoreboardHard = new JButton("hard mode");
        scoreboardHard.setSize(100, 100);
        scoreboardHard.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(scoreboardHard, gbc);
        scoreboardHard.addActionListener(e -> {
            if (scoreboardHard.isEnabled()) {
                //cleans the jframe of all its components
                //then starts the process of displaying the scoreboard
                frame.getContentPane().removeAll();
                frame.revalidate();
                frame.repaint();
            }
        });

        cancelButton = new JButton("main menu");
        cancelButton.setSize(100, 100);


        frame.add(cancelButton, gbc);
        cancelButton.addActionListener(e -> {
            if (cancelButton.isEnabled()) {
                //closes the jframe and displays the main menu
                frame.dispose();
            }
        });

    }


}
