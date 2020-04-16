package Com.company.TetrisGIP;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blocks2 {

    private BufferedImage block2; // enables drawing of block
    private int[][] coords; // coordinates of blocks
    private Board2 board2; // access to playing area
    private int blockXcoords = 0;
    private int x, y;

    private int color;

    private boolean collision = false, moveX = false;

    private int normalS = 600, fast = 60, currents;
    private long time, lasttime;


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


    //update method
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

        // FIXME: 20/03/30 this needs to be deleted this is test code to find a good leveling difficulty system
        if (board2.level == 2) {
            normalS = 100;
        } else if (board2.level == 3) {
            normalS = 400;
        } else if (board2.level == 4) {
            normalS = 300;
        } else if (board2.level == 5) {
            normalS = 200;
        }
    }

    //render method using graphics g object
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

    public void rotate() {
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

    private int[][] gettranspose(int[][] matrix) {
        int[][] newMatrix = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                newMatrix[j][i] = matrix[i][j];
            }
        }
        return newMatrix;
    }

    private int[][] getReverseMatrix(int[][] matrix) {
        int middle = matrix.length / 2;
        for (int i = 0; i < middle; i++) {
            int[] m = matrix[i];
            matrix[i] = matrix[matrix.length - i - 1];
            matrix[matrix.length - i - 1] = m;
        }
        return matrix;

    }


    public void setBlockXcoords(int bXcoords) {

        this.blockXcoords = bXcoords;
    }


    public void NormalS() {
        currents = normalS;
    }

    public void speedf() {
        currents = fast;
    }

    public BufferedImage getBlock() {
        return block2;
    }

    public int[][] getCoords() {
        return coords;
    }

    public int getColor() {
        return color;
    }
}