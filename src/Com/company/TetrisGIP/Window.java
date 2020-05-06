package Com.company.TetrisGIP;

import Com.company.TetrisGIP.Database.connectDB;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    //sets the height and with to be used everywhere in the project and makes the variables unchangeable
    public static final int WIDTH = 600, HEIGHT = 640;
    public JFrame gamemode2;
    //creates a Jframe object
    private JFrame window;
    private JFrame gamemode1;
    private JFrame scoreboard;
    //creates object of Board class
    private Board board;
    private Board2 board2;


    //constructor for the window class
    public Window() {
        connectDB.connect();//test if connected

        window = new JFrame("Main Menu");
        window.setLayout(new GridBagLayout());// puts the buttons in the center and keeps the assigned size also looked this up because the previous thing i tried did not work
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        window.setSize(WIDTH, HEIGHT); // sets the size of the window using the variables WIDTH and HEIGHT
        window.setResizable(false);//makes the window size static and not resizable
        window.setLocationRelativeTo(null);// opens the window in the middle of the screen
        //make a button
        JButton gm1 = new JButton("easy mode");
        gm1.setSize(100, 100);
        gm1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton gm2 = new JButton("hard mode");
        gm2.setSize(100, 100);
        gm2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton scoreboard = new JButton("Scoreboard");
        scoreboard.setSize(100, 100);
        scoreboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        window.add(gm1, gbc);
        gm1.addActionListener(e -> {
            if (gm1.isEnabled()) {
                gamemode1 = new JFrame("easy classic tetris");
                gamemode1.setSize(WIDTH, HEIGHT); // sets the size of the window using the variables WIDTH and HEIGHT
                gamemode1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the application after pressing the red cross
                gamemode1.setResizable(false);//makes the window size static and not resizable
                gamemode1.setLocationRelativeTo(null);// opens the window in the middle of the screen
                board = new Board();
                gamemode1.add(board);
                gamemode1.addKeyListener(board);
                gamemode1.setVisible(true);
                window.setVisible(false);
            }
        });

        JLabel space1 = new JLabel(" ");
        space1.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        window.add(space1, gbc);
        gm2.addActionListener(e -> {
            if (gm1.isEnabled()) {
                gamemode2 = new JFrame("hard classic tetris");
                gamemode2.setSize(WIDTH, HEIGHT); // sets the size of the window using the variables WIDTH and HEIGHT
                gamemode2.setResizable(false);//makes the window size static and not resizable
                gamemode2.setLocationRelativeTo(null);// opens the window in the middle of the screen
                board2 = new Board2();
                gamemode2.add(board2);
                gamemode2.addKeyListener(board2);
                gamemode2.setVisible(true);
                window.setVisible(false);
            }
        });

        window.add(gm2, gbc);

        JLabel space3 = new JLabel(" ");
        space3.setAlignmentX(JFrame.CENTER_ALIGNMENT);
        window.add(space3, gbc);


        window.add(scoreboard, gbc);

        window.setVisible(true);
    }


    // this makes an object of this class
    public static void main(String[] args) {
        new Window();
    }

    public void setGamemode2() {
        gamemode2.setVisible(false);
    }
}
