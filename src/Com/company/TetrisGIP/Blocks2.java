package Com.company.TetrisGIP;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blocks2 {

    // enables drawing of block
    private BufferedImage block2;
    // coordinates of blocks
    private int[][] coords;
    // access to playing area
    private Board2 board2;

    private int blockXcoords = 0;
    private int x, y;

    private int color;

    private boolean collision = false, moveX = false;

    private int normalS = 600, fast = 60, currents;
    private long time, lasttime;


    /**
     * Instantiates a new Blocks.
     * and gets all the info
     * so it knows what to draw and where and which color it has
     *
     * @param block  the block
     * @param coords the coords
     * @param board  the board
     * @param color  the color
     */
    public Blocks2(BufferedImage block, int[][] coords, Board2 board, int color) {
        this.block2 = block;
        this.coords = coords;
        this.board2 = board;
        this.color = color;
        currents = normalS;
        time = 0;
        lasttime = System.currentTimeMillis();


        x = 4;
        y = 0;

    }


    /**
     * updates the positioning of the blocks and and the rotation
     * also checks if a block is placed and if a line is full
     * and adds score creates a newblock for the player to use and adds a level each time the player has gotten 10 points
     */
    public void update() {
        moveX = true;
        time += System.currentTimeMillis() - lasttime;
        lasttime = System.currentTimeMillis();

        if (collision) {
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[0].length; col++) {
                    if (coords[row][col] != 0)
                        board2.getBoard()[y + row][x + col] = color;
                }
            }
            linecheck();
            board2.addscore();
            board2.nextblock();
            board2.addlevel();
        }

        //checks if the block is at the border of the field in the horizontal width
        //if the block is block it can't move further
        if (!(x + blockXcoords + coords[0].length > 10) && !(x + blockXcoords < 0)) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        if (board2.getBoard()[y + row][x + blockXcoords + col] != 0) {
                            moveX = false;
                        }

                    }
                }
            }

            if (moveX)
                x += blockXcoords;

        }

        //checks if the block is at the border of the field in the vertical width
        //if the block is block it can't move further
        if (!(y + 1 + coords.length > 20)) {

            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {

                        if (board2.getBoard()[y + 1 + row][x + col] != 0) {
                            collision = true;
                        }
                    }
                }
            }
            if (time > currents) {
                y++;
                time = 0;
            }
        } else {
            collision = true;
        }

        blockXcoords = 0;

        //speed system to make each level a bit harder depending on the level of the game
        if (board2.level == 2) {
            normalS = 500;
        } else if (board2.level == 3) {
            normalS = 400;
        } else if (board2.level == 4) {
            normalS = 300;
        } else if (board2.level == 5) {
            normalS = 200;
        } else if (board2.level == 6) {
            normalS = 100;
        } else if (board2.level == 7) {
            normalS = 80;
        }
    }

    /**
     * Renders  the blocks on the board.
     *
     * @param g the g
     */
    public void render(Graphics g) {

        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0) {
                    g.drawImage(block2, col * board2.getBlockSize() + x * board2.getBlockSize(),
                            row * board2.getBlockSize() + y * board2.getBlockSize(), null);
                }
            }
        }
    }

    /**
     * checks it the line is full if it is it deletes the line and all the other blocks fall down
     */
    private void linecheck() {
        int height = board2.getBoard().length - 1;
        for (int i = height; i > 0; i--) {
            int counter = 0;

            for (int j = 0; j < board2.getBoard()[0].length; j++) {
                if (board2.getBoard()[i][j] != 0)
                    counter++;
                board2.getBoard()[height][j] = board2.getBoard()[i][j];
            }
            if (counter < board2.getBoard()[0].length)
                height--;
        }
    }

    /**
     * check if the block has a collision somewhere if it hasn't the block can rotate else it can't
     * if it can it will get the transpose using the coords of the blocks
     * then it will reverse the matrix using the transpose setting coords of the block the rotatedmatrix coords
     */
    public void rotate() {
        if (collision) {
            return;
        }
        int[][] rotatedMatrix = null;

        rotatedMatrix = gettranspose(coords);

        rotatedMatrix = getReverseMatrix(rotatedMatrix);

        if (x + rotatedMatrix[0].length > 10 || y + rotatedMatrix.length > 20) {
            return;
        }
        for (int row = 0; row < rotatedMatrix.length; row++) {
            for (int col = 0; col < rotatedMatrix[0].length; col++) {
                if (board2.getBoard()[y + row][x + col] != 0) {
                    return;
                }
            }
        }


        coords = rotatedMatrix;


    }

    /**
     * calculates the transpose of the blocks
     * and returns the new matrix which is used by getReversematrix
     */
    private int[][] gettranspose(int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    /**
     * gets the reverse matrix of theblooks
     * using the rotatedmatrix which is the output of the transpose
     * and returns the coords it calcutlated
     */
    private int[][] getReverseMatrix(int[][] matrix) {
        int middle = matrix.length / 2;
        for (int i = 0; i < middle; i++) {
            int[] m = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = m;
        }
        return matrix;

    }

    /**
     * Sets the current block horizontal coords xcoords whenpressing left and right so the block moves left and right
     *
     * @param bXcoords the currentblock horizontalcoords
     */
    public void setBlockXcoords(int bXcoords) {

        this.blockXcoords = bXcoords;
    }

    /**
     * the normal speed of the game used to return the gamespeed
     * to its standard level after making the blocks fall down faster
     * <p>
     * standard speed level is different for each level the player reaches
     */
    public void NormalS() {
        currents = normalS;
    }

    /**
     * used to set the game to a faster speed so the player can make blocks fall down faster on the easier level
     */
    public void speedf() {
        currents = fast;
    }

    /**
     * used for getting the newblock after the player places its block
     *
     * @return the block
     */
    public BufferedImage getBlock() {
        return block2;
    }

    /**
     * gets the coords of the newblock so it knows where to draw the blocks
     *
     * @return the int [ ] [ ]
     */
    public int[][] getCoords() {
        return coords;
    }

    /**
     * Gets the color of the new block and gives it to the new block.
     *
     * @return color
     */
    public int getColor() {
        return color;
    }

}
