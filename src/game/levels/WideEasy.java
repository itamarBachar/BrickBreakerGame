//318781630.
package game.levels;

import game.partipcans.Block;
import game.partipcans.LevelInformation;
import game.partipcans.Sprite;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public class WideEasy implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 10;
    public static final int PADDLE_WIDTH = 600;
    public static final int BLOCKS_HIGH = 30;
    public static final int BLOCKS_WIDTH = 57;
    public static final int NUMBER_OF_BLOCKS = 13;
    public static final int START_X_POS = 30;
    public static final int START_Y_POS = 200;
    public static final int SPEED_PADDLE = 15;
    public static final int PADDLE_X_START = 90;
    public static final int PADDLE_Y_START = 550;

    /**
     * @return the number of balls that take place in the game.
     */
    @Override
    public int numberOfBalls() {
        return NUMBER_OF_BALLS;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return the list of the velocities.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        int x = 0;
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(315 + x, 6.5));
            x = x + 10;
        }
        x = 0;
        for (int i = NUMBER_OF_BALLS / 2; i < NUMBER_OF_BALLS; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(x, 6.5));
            x = x + 10;
        }

        return initialBallVelocities;
    }

    /**
     * @return how much the ball move in one pressed right or left.
     */
    @Override
    public int paddleSpeed() {
        return SPEED_PADDLE;
    }

    /**
     * @return the width of the paddle.
     */
    @Override
    public int paddleWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * @return the level name will be displayed at the top of the screen.
     */
    @Override
    public String levelName() {
        return "WideEasy";
    }

    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return null;
    }

    /**
     * The Blocks that make up this level, each block contains,
     * its size, color and location.
     *
     * @return the list of the blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<Block>();
        Color[] colorArray = {Color.RED, Color.orange, Color.yellow, Color.green, Color.BLUE, Color.PINK, Color.cyan};
        for (int i = 0; i < NUMBER_OF_BLOCKS; i++) {
            double startXPointBlock = START_X_POS + i * BLOCKS_WIDTH;
            double startYPointBlock = START_Y_POS;
            Color colorForBlock = colorArray[i / 2];
            blocks.add(new Block(new Rectangle(new Point(startXPointBlock, startYPointBlock),
                    BLOCKS_WIDTH, BLOCKS_HIGH), colorForBlock));
        }
        return blocks;
    }

    /**
     * @return the x value.
     */
    @Override
    public int getPaddleXStart() {
        return PADDLE_X_START;
    }

    /**
     * @return the y value.
     */
    @Override
    public int getPaddleYStart() {
        return PADDLE_Y_START;
    }

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number Of Blocks To Remove.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return NUMBER_OF_BLOCKS;
    }

    /**
     * @return the color of the background.
     */
    @Override
    public Color background() {
        return Color.white;
    }

}











