package Com.company.TetrisGIP;

import javax.swing.*;
import java.awt.*;

public class Window {
    //sets the height and with to be used everywhere in the project and makes the variables unchangeable
    public static final int WIDTH = 600, HEIGHT = 640;
    //creates a Jframe object
    private JFrame window;
    //creates object of Board class
    private Board board;


    //constructor for the window class
    public Window() {
        window = new JFrame("Tetris spel");
        window.setLayout(new GridBagLayout());// puts the buttons in the center and keeps the assigned size also looked this up because the previous thing i tried did not work
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        window.setSize(WIDTH, HEIGHT); // sets the size of the window using the variables WIDTH and HEIGHT
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the application after pressing the red cross
        window.setResizable(false);//makes the window size static and not resizable
        window.setLocationRelativeTo(null);// opens the window in the middle of the screen
        //make a button
        JButton gm1 = new JButton("start gamemode 1");
        gm1.setSize(100, 100);
        gm1.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton gm2 = new JButton("Start gamemode 2");
        gm2.setSize(100, 100);
        gm2.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton explanation = new JButton("View the documentation");
        explanation.setSize(100, 100);
        explanation.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton scoreboard = new JButton("Scoreboard");
        scoreboard.setSize(100, 100);
        scoreboard.setAlignmentX(Component.CENTER_ALIGNMENT);
        window.add(gm1, gbc);
        window.add(gm2, gbc);
        window.add(explanation, gbc);
        window.add(scoreboard, gbc);


        // FIXME: 20/04/01 this is going to be in ifekse statements to load the games by setting the window that loads the buttons invisible and loading the game into new jframes same with the explanation and scoreboard
  /*      board = new Board(); // initializes board object
        window.add(board);
        window.addKeyListener(board);*/


        window.setVisible(true);
    }

    // this makes an object of this class
    public static void main(String[] args) {
        new Window();
    }
}
