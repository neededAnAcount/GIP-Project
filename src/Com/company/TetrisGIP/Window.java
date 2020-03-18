package Com.company.TetrisGIP;

import javax.swing.*;

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
        window.setSize(WIDTH, HEIGHT); // sets the size of the window using the variables WIDTH and HEIGHT
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the application after pressing the red cross
        window.setResizable(false);//makes the window size static and not resizable
        window.setLocationRelativeTo(null);// opens the window in the middle of the screen

        board = new Board(); // initializes board object
        window.add(board);
        window.addKeyListener(board);


        window.setVisible(true);
    }

    // this makes an object of this class
    public static void main(String[] args) {
        new Window();
    }
}
