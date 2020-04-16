package Com.company.TetrisGIP;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Board2 extends JPanel implements KeyListener {

    //set the size of the blocks
    private final int blockSize = 30;
    //playing area size
    private final int boardWidth = 10, boardheight = 20;
    int level = 1;
    //game is run at 60 frames per second as defined here
    int fps = 60;
    //enables images to load in the project
    private BufferedImage blocks2;
    // define matrix using 2D Arrays
    private int[][] board = new int[boardheight][boardWidth];
    //Array for all the blocks
    //1 in matrix stands for a block
    //0 in matrix stands for empty space
    private Blocks2[] tetrisblocks = new Blocks2[7];
    //defines current tetrisblock that the user is paying with;
    private Blocks2 curentTetrisblock;
    private int score = 0;
    private Timer timer;
    private int delay = 1000 / fps;

    // constructor for Board class
    public Board2() {
        //initialize blocks
        try {
            blocks2 = ImageIO.read(Board2.class.getResource("/tiles.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  Repaints the background back to white when there is no block
        timer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });

        timer.start();

        /*
          INFO FOR SUBIMAGE USE

          Returns a subimage defined by a specified rectangular region.
          The returned {@code BufferedImage} shares the same
          data array as the original image.
          @param x the X coordinate of the upper-left corner of the
         *          specified rectangular region
         * @param y the Y coordinate of the upper-left corner of the
         *          specified rectangular region
         * @param w the width of the specified rectangular region
         * @param h the height of the specified rectangular region
         * @return a {@code BufferedImage} that is the subimage of this
         *          {@code BufferedImage}.
         * */

        //  initializes the tetrisblocks
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

    public void update() {
        curentTetrisblock.update();
    }

    // enables us to start drowing blocks
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        curentTetrisblock.render(g);

        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                if (board[row][col] != 0)
                    g.drawImage(blocks2.getSubimage((board[row][col] - 1) * blockSize, 0, blockSize, blockSize),
                            col * blockSize, row * blockSize, null);


        g.setColor(Color.black);

        g.setFont(new Font("Georgia", Font.BOLD, 20));
        g.drawString("LEVCDCDCDCDCDCDCDCEL", Window.WIDTH - 225, Window.HEIGHT / 2 - 60);
        g.drawString(String.valueOf(level), Window.WIDTH - 225, Window.HEIGHT / 2 - 30);
        g.drawString("SCORE", Window.WIDTH - 225, Window.HEIGHT / 2);
        g.drawString(score + "", Window.WIDTH - 225, Window.HEIGHT / 2 + 30);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(0, 0, 0, 100));





        /*
         *  this piece of code draws the playing area
         *   the first for loop does this
         *   renders the horizontal lines
         *   the second for loop does this
         *   renders the vertical lines
         * */
        for (int i = 0; i < boardheight; i++) {
            g.drawLine(0, i * blockSize, boardWidth * blockSize, i * blockSize);
        }
        for (int j = 0; j <= boardWidth; j++) {
            g.drawLine(j * blockSize, 0, j * blockSize, boardheight * blockSize);
        }

        // renders the current tetrisblock on the playing field using the render method Located in the Blocks class

    }

    public void nextblock() {
        int index = (int) (Math.random() * tetrisblocks.length);
        Blocks2 newblock = new Blocks2(tetrisblocks[index].getBlock(), tetrisblocks[index].getCoords(), this, tetrisblocks[index].getColor());
        curentTetrisblock = newblock;
    }


    public int getBlockSize() {
        return blockSize;
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // not used but has to stay here because otherwise i would receive errors and it won't compile without this !!!!!!!
    }

    public int[][] getBoard() {
        return board;
    }
    // FIXME: 20/03/18 2:40 PM see VK_DOWN comment (+ keyreleased)  also see FIXME at Blocks.java bottom methods!
    // FIXME: 20/03/18 2:50 PM UPDATE after some testing i have concluded that the bug takes place when i press any key!
    // FIXME: 20/03/18 2:56 PM update after letting intellij analyze my code i found the bug  because it returned 2 possible bugs leaving the bug here for future reffrence


    /*
     *       else if (e.getKeyCode()==KeyEvent.VK_DOWN);// supposed to move blovk down faster it works to a degree but the same thing also happens when pressing left and right
     *      curentTetrisblock.speedf();
     *
     *
     *       if (e.getKeyCode()==KeyEvent.VK_DOWN);
     *       curentTetrisblock.NormalS();
     *
     *
     *
     * left an ; directly after the if statement resulting it to be seen as having an empty body by java
     * */


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) //move block left when pressing the left key
        {
            curentTetrisblock.setBlockXcoords(-1);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) //move block right when pressing the right key
        {
            curentTetrisblock.setBlockXcoords(1);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN)// supposed to move blovk down faster it works to a degree but the same thing also happens when pressing left and right
        {
            curentTetrisblock.speedf();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            if (!timer.isRunning())// if timer.isrunning(false) start timer
                timer.start();
            else {
                timer.stop(); // else start timer
            }
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            curentTetrisblock.rotate();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            curentTetrisblock.NormalS();
    }

    public void addscore() {
        score++;
    }

    public void addlevel() {
        if (score % 10 == 0)
            level++;
    }
}

