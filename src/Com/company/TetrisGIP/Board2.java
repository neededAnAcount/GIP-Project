package Com.company.TetrisGIP;

import Com.company.TetrisGIP.Database.insertDB;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * The Board the game is played on
 * the keylistener is used to detect input by the user so the player can move the blocks and pause the game.
 */
public class Board2 extends JPanel implements KeyListener {

    /**
     * the size of the playing area
     */
    private final int boardWidth = 10, boardheight = 20;
    /**
     * the size of the blocks
     */
    private final int blockSize = 30;


    String textFieldValue = null;
    /**
     * The Starting Level for the game.
     */
    int level = 1;
    /**
     * The Fps of the game is ran at 60 frames per second.
     */
    int fps = 60;
    //opens a window with all the needed components where the user can enter his or her username and press the button to save the chosen username and score into a sqlite database
    private JFrame saveHard;
    private JTextField usernameHard;
    private JButton saveButtonHard;
    private JButton cancelButton;

    /**
     * enables the use of subimages from one image
     */
    private BufferedImage blocks2;

    /**
     * defines the  matrix using 2D Arrays and set the size of the matrix using the defined boardheight and boardwidth
     */
    private int[][] board = new int[boardheight][boardWidth];

    /**
     * Array for all the blocks
     * 1 in matrix stands for a block
     * 0 in matrix stands for empty space
     * size of the array is 7 because there are 7pieces the player can get
     */
    private Blocks2[] tetrisblocks = new Blocks2[7];

    /**
     * defines current tetrisblock that the user is paying with;
     */
    private Blocks2 curentTetrisblock;
    /**
     * starting score
     */
    private int score = 0;

    /**
     * initialize a timer for the painting of the board
     */
    private Timer timer;

    /*
     * set the delay the timer works on
     */
    private int delay = 1000 / fps;

    /**
     * Instantiates a new Board.
     * for the game to be played on
     */
    public Board2() {
        //initialize blocks and gives it a file to get the subimages from to give the blocks a color
        try {
            blocks2 = ImageIO.read(Board2.class.getResource("/tiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  Repaints the background back to white when there is no block
        timer = new Timer(delay, e -> {
            update();
            repaint();
        });

        //starts the timer
        timer.start();


        //  initializes the tetrisblocks and sets it color using the a subimage
        tetrisblocks[0] = new Blocks2(blocks2.getSubimage(0, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1, 1}// Straight-piece
        }, this, 1);

        tetrisblocks[1] = new Blocks2(blocks2.getSubimage(blockSize, 0, blockSize, blockSize), new int[][]{
                {1, 1, 0},
                {0, 1, 1}// Z-piece
        }, this, 2);

        tetrisblocks[2] = new Blocks2(blocks2.getSubimage(blockSize * 2, 0, blockSize, blockSize), new int[][]{
                {0, 1, 1},
                {1, 1, 0}// S-piece
        }, this, 3);

        tetrisblocks[3] = new Blocks2(blocks2.getSubimage(blockSize * 3, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {0, 1, 0}// T-piece
        }, this, 4);

        tetrisblocks[4] = new Blocks2(blocks2.getSubimage(blockSize * 4, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {0, 0, 1}// J-piece
        }, this, 5);

        tetrisblocks[5] = new Blocks2(blocks2.getSubimage(blockSize * 5, 0, blockSize, blockSize), new int[][]{
                {1, 1, 1},
                {1, 0, 0}// L-piece
        }, this, 6);

        tetrisblocks[6] = new Blocks2(blocks2.getSubimage(blockSize * 6, 0, blockSize, blockSize), new int[][]{
                {1, 1},
                {1, 1}// O-piece
        }, this, 7);

        // current block in use
        nextblock();
    }

    /**
     * tells the program to do this
     * updates the positioning of the blocks and and the rotation
     * also checks if a block is placed and if a line is full
     * and adds score creates a newblock for the player to use and adds a level each time the player has gotten 10 points
     */
    public void update() {
        curentTetrisblock.update();
    }

    // enables us to start drawing blocks
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        curentTetrisblock.render(g);

        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                if (board[row][col] != 0)
                    g.drawImage(blocks2.getSubimage((board[row][col] - 1) * blockSize, 0, blockSize, blockSize),
                            col * blockSize, row * blockSize, null);

        //set the color of the text that is deiplayed next to the board
        g.setColor(Color.black);
        //set the font of the text and size
        g.setFont(new Font("Georgia", Font.BOLD, 20));
        //draw the text "LEVEL" at those coords of the board
        g.drawString("LEVEL", Window.WIDTH - 225, Window.HEIGHT / 2 - 60);
        //draw the value of the level next to the text "LEVEL"
        g.drawString(String.valueOf(level), Window.WIDTH - 225, Window.HEIGHT / 2 - 30);
        //draw the text "SCORE" under the text "LEVEL" on the board
        g.drawString("SCORE", Window.WIDTH - 225, Window.HEIGHT / 2);
        //draw the value of the score the player got next to the text "SCORE"
        g.drawString(score + "", Window.WIDTH - 225, Window.HEIGHT / 2 + 30);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 100));




        /*
         *  this piece of code draws the playing area
         *   the first for loop renders the horizontal lines
         *   the second for loop renders the vertical lines
         * */
        for (int i = 0; i < boardheight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j <= boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardheight * blockSize);
        }

    }

    /**
     * this will decide the next block the player
     * gets after placing a block the nect block is decided randomly
     * the block also gets assigned its details like color the coords of the blocks
     * so it will still have the same color after placing the block
     */
    public void nextblock() {
        int index = (int) (Math.random() * tetrisblocks.length);
        Blocks2 newblock = new Blocks2(tetrisblocks[index].getBlock(), tetrisblocks[index].getCoords(), this, tetrisblocks[index].getColor());
        curentTetrisblock = newblock;
        //this checks if the player has hit the top of the playing area and if it has it will display the save menu
        //where the player can enter their username and save their score
        for (int row = 0; row < curentTetrisblock.getCoords().length; row++) {
            for (int col = 0; col < curentTetrisblock.getCoords()[row].length; col++) {
                if (curentTetrisblock.getCoords()[row][col] != 0) {
                    if (board[row][col + 3] != 0) {


                        // save menu code
                        timer.stop();
                        saveHard = new JFrame("Save score");
                        saveHard.setLayout(new GridBagLayout());
                        GridBagConstraints gbc = new GridBagConstraints();
                        GridBagConstraints gbc2 = new GridBagConstraints();
                        GridBagConstraints gbc3 = new GridBagConstraints();
                        gbc.anchor = GridBagConstraints.LAST_LINE_END;
                        gbc2.anchor = GridBagConstraints.LAST_LINE_START;
                        gbc3.anchor = GridBagConstraints.CENTER;
                        saveHard.setSize(Board.SAVEWIDTH, Board.SAVEHEIGHT);
                        saveHard.setResizable(false);
                        saveHard.setLocationRelativeTo(null);

                        usernameHard = new JTextField();
                        usernameHard.setPreferredSize(new Dimension(370, 40));
                        usernameHard.addFocusListener(new FocusListener() {
                            @Override
                            public void focusGained(FocusEvent e) {
                                JTextField source = (JTextField) e.getComponent();
                                source.setText("");
                                source.removeFocusListener(this);

                            }

                            @Override
                            public void focusLost(FocusEvent e) {

                            }
                        });

                        saveButtonHard = new JButton("Save")
                        ;
                        saveButtonHard.grabFocus();
                        saveButtonHard.setSize(100, 50);
                        saveHard.requestFocus();
                        saveButtonHard.addActionListener(e -> {
                            if (saveButtonHard.isEnabled()) {
                                textFieldValue = usernameHard.getText();
                                insertDB insertAPP = new insertDB();
                                insertAPP.insertHard(textFieldValue, getScore());
                                saveHard.setVisible(false);
                                paintcomponent3(getGraphics());
                                Window w = new Window();
                                w.setVisible(true);
                            }
                        });


                        cancelButton = new JButton("Cancel");
                        cancelButton.setSize(100, 50);
                        cancelButton.addActionListener(e -> {
                            if (saveButtonHard.isEnabled()) {
                                saveHard.setVisible(false);
                                paintcomponent3(getGraphics());
                                Window w = new Window();
                                w.setVisible(true);
                            }
                        });

                        saveHard.add(saveButtonHard, gbc);
                        saveHard.add(usernameHard, gbc3);
                        saveHard.add(cancelButton, gbc2);

                        saveHard.setVisible(true);
                    }
                }
            }
        }
    }

    /**
     * Gets the defined block size.
     * used for calculating the size of the blocks so the blocks fit in the boxes that are drawn using the horizontal and vertical lines
     *
     * @return the block size
     */
    public int getBlockSize() {
        return blockSize;
    }

    /**
     * not used but has to stay here because otherwise i would receive errors
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * returns board width and height
     */
    public int[][] getBoard() {
        return board;
    }

    /**
     * this checks if the player pressed one of the keys following keys
     * up down left right arrow keys
     * escape key
     * if the player has pressed on of those keys the game will perform
     * the action assigned to the key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        //moves block left when pressing the left key
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            curentTetrisblock.setBlockXcoords(-1);
        }//moves block right when pressing the right key
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            curentTetrisblock.setBlockXcoords(1);
        }// moves block down faster doesn't work hen pressing right or left at the same time
        else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            curentTetrisblock.speedf();
        } //if player presses the escape button the game is paused
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!timer.isRunning())// if timer.isrunning(false) start timer
                timer.start();
            else {
                timer.stop(); // else start timer
                paintcomponent2(getGraphics());
            }
        }//if the player presses the up button the block rotates
        else if (e.getKeyCode() == KeyEvent.VK_UP) {
            curentTetrisblock.rotate();
        }
    }

    /**
     * //checks if the down key is still pressed if not the gamespeed is set to to normal speed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            curentTetrisblock.NormalS();
    }

    public void paintcomponent2(Graphics e) {
        super.paintComponent(e);
        e.setColor(Color.white);
        e.fillRect(0, 0, 1000, 1000);
        e.setColor(Color.black);
        e.setFont(new Font("Georgia", Font.BOLD, 35));
        e.drawString("Game is paused", 135, 250);
    }

    public void paintcomponent3(Graphics e) {
        super.paintComponent(e);
        e.setColor(Color.white);
        e.fillRect(0, 0, 1000, 1000);
        e.setColor(Color.black);
        e.setFont(new Font("Georgia", Font.BOLD, 35));
    }

    /**
     * this adds a point to the player score each time the player places a block
     */
    public void addscore() {
        score++;
    }

    /**
     * this adds a level to the level of the game each time the player has gotten 10 points
     */
    public void addlevel() {
        if (score % 10 == 0)
            level++;
    }

    /**
     * this gets the score the player got
     * this is used to get the score and save it into the database
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }
}


