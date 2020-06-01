package Com.company.TetrisGIP;


import Com.company.TetrisGIP.Database.connectDB;
import Com.company.TetrisGIP.Database.selectDB;

import javax.swing.*;
import java.awt.*;

/**
 * The type Window.
 */
public class Window extends JFrame {
    /**
     * The constant WIDTH.
     */
//sets the height and with to be used everywhere in the project and makes the variables unchangeable
    public static final int WIDTH = 600, /**
     * The Height.
     */
    HEIGHT = 640;

    //creates a Jframe object
    private JFrame window;
    private JFrame gamemode1;
    private JFrame gamemode2;
    private JFrame scoreboardselect;
    //creates object of Board class
    private Board board;
    private Board2 board2;
    //class for displaying the scoreboard
    private scoreboardMenu scoreboardMenu;


    /**
     * this constuctor will load the main menu for the game
     * and will load all the settings it needs
     * and will do a test if the connection to the database is working
     * if it failed it will give an error message
     */
    public Window() {
        connectDB.connect();//test if connected
        selectDB.selectAllEasy();
        selectDB.selectAllHard();

        window = new JFrame("Main Menu");
        //closes the whole program if the main menu is closed
        window.setDefaultCloseOperation(3);
        // puts the buttons in the center and keeps the assigned size
        window.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 0, 0, 0);
        // sets the size of the window using the variables WIDTH and HEIGHT
        window.setSize(WIDTH, HEIGHT);
        //makes the window size static and not resizable
        window.setResizable(false);
        // opens the window in the middle of the screen
        window.setLocationRelativeTo(null);

        //makes a button and gives it a text
        JButton gm1 = new JButton("easy mode");
        //set the size for the button
        gm1.setSize(100, 100);
        //set the position for the button in the center
        gm1.setAlignmentX(Component.CENTER_ALIGNMENT);

        //make a button
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
                gamemode1.setResizable(false);//makes the window size static and not resizable
                gamemode1.setLocationRelativeTo(null);// opens the window in the middle of the screen
                board = new Board();
                gamemode1.add(board);
                gamemode1.addKeyListener(board);
                gamemode1.setVisible(true);
                window.setVisible(false);
            }
        });

        gm2.addActionListener(e -> {
            if (gm2.isEnabled()) {
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


        // Display scoreboard menu
        window.add(scoreboard, gbc);
        scoreboard.addActionListener(e -> {
            if (scoreboard.isEnabled()) {
                scoreboardselect = new JFrame();
                scoreboardselect.setSize(WIDTH, HEIGHT);
                scoreboardselect.setResizable(false);
                scoreboardselect.setLocationRelativeTo(null);
                scoreboardMenu = new scoreboardMenu();
                scoreboardselect.add(scoreboardMenu);
            }
        });

        window.setVisible(true);
    }


    /**
     * makes an object of the class using its constructor
     * which will load the main menu
     * and start the program
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        new Window();
    }
}
