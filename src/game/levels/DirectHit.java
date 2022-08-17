//318781630.
package game.levels;

import game.partipcans.Block;
import game.partipcans.LevelInformation;
import game.partipcans.Sprite;
import shapes.Point;
import shapes.Velocity;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public class DirectHit implements LevelInformation {
    public static final int NUMBER_OF_BALLS = 1;
    public static final int PADDLE_WIDTH = 60;
    public static final int BLOCKS_HIGH = 30;
    public static final int BLOCKS_WIDTH = 60;
    public static final int NUMBER_OF_BLOCKS = 1;
    public static final int SPEED_PADDLE = 10;
    public static final int PADDLE_X_START = 360;
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
        Velocity v = new Velocity(0, -3);
        List<Velocity> initialBallVelocities = new ArrayList<>();
        initialBallVelocities.add(v);
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
        return "DirectHit";
    }

    /**
     * @return Returns a sprite with the background of the level.
     */
    @Override
    public Sprite getBackground() {
        return null;
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
     * The Blocks that make up this level, each block contains,
     * its size, color and location.
     *
     * @return the list of the blocks.
     */
    @Override
    public List<Block> blocks() {
        Rectangle rectangle = new Rectangle(new Point(360, 200), BLOCKS_WIDTH, BLOCKS_HIGH);
        Block block = new Block(rectangle, Color.red);
        List<Block> blocks = new ArrayList<>();
        blocks.add(block);
        return blocks;
    }

    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
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
        return Color.black;
    }
}
