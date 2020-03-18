package Com.company.TetrisGIP;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Blocks {

    private BufferedImage block; // enables drawing of block
    private int[][] coords; // coordinates of blocks
    private Board board; // access to playing area
    private int blockXcoords = 0;
    private int x, y;

    private int normalS = 1000, fast = 20, currents;
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

        if (!(x + blockXcoords + coords[0].length > 10) && !(x + blockXcoords < 0))
            x = x + blockXcoords;

        if (!(y + 1 + coords.length > 20)) {
            if (time > currents) {
                y++;
                time = 0;
            }
        }


        blockXcoords = 0;

    }

    //render method using graphics g object
    public void render(Graphics g) {

        for (int row = 0; row < coords.length; row++) {
            for (int col = 0; col < coords[row].length; col++) {
                if (coords[row][col] != 0)
                    g.drawImage(block, col * board.getBlockSize() + x * board.getBlockSize(),
                            row * board.getBlockSize() + y * board.getBlockSize(), null);
            }
        }

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
}
