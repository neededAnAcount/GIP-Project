package Com.company.TetrisGIP;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blocks {

    private BufferedImage block; // enables drawing of block
    private int[][] coords; // coordinates of blocks
    private Board board; // access to playing area
    private int blockXcoords = 0;
    private int x, y;

    private boolean collision = false, moveX = false;

    private int normalS = 600, fast = 60, currents;
    private long time, lasttime;


    public Blocks(BufferedImage block, int[][] coords, Board board) {
        this.block = block;
        this.coords = coords;
        this.board = board;
        currents = normalS;
        time = 0;
        lasttime = System.currentTimeMillis();


        x = 4;
        y = 0;

    }


    //update method
    public void update() {

        time = time + System.currentTimeMillis() - lasttime;
        lasttime = System.currentTimeMillis();

        if (collision) {
            for (int row = 0; row < coords.length; row++) {
                for (int col = 0; col < coords[row].length; col++) {
                    if (coords[row][col] != 0) {
                        board.getBoard()[y + row][x + col] = 1;
                    }
                }
            }
            board.nextblock();
        }
        if (!(x + blockXcoords + coords[0].length > 10) && !(x + blockXcoords < 0)) {

            for (int row = 0; row < coords.length; row++)
                for (int col = 0; col < coords[row].length; col++)
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + row][x + blockXcoords + col] != 0)
                            moveX = false;
                    }
            if (moveX)
                x += blockXcoords;
        }


        if (!(y + 1 + coords.length > 20)) {

            for (int row = 0; row < coords.length; row++)
                for (int col = 0; col < coords[row].length; col++)
                    if (coords[row][col] != 0) {
                        if (board.getBoard()[y + row + 1][col + x] != 0)
                            collision = true;
                    }
            if (time > currents) {
                y++;
                time = 0;
            }
        } else {
            collision = true;
        }
        blockXcoords = 0;
        moveX = true;
        blockXcoords = 0;

    }

    //render method using graphics g object
    public void render(Graphics g) {

        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0) {
                    g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),
                            row * board.getBlockSize() + y * board.getBlockSize(), null);
                }
            }
        }
    }


    public void rotate() {
        int[][] rotatedMatrix = null;

        rotatedMatrix = gettranspose(coords);

        rotatedMatrix = getReverseMatrix(rotatedMatrix);

        if (x + rotatedMatrix[0].length > 10 || y + rotatedMatrix.length > 20) {
            return;
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
        return block;
    }

    public int[][] getCoords() {
        return coords;
    }
}
