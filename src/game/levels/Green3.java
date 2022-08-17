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
public class Green3 implements LevelInformation {


    public static final int NUMBER_OF_BALLS = 2;
    public static final int PADDLE_WIDTH = 120;
    public static final int NUMBER_OF_BLOCKS = 40;
    public static final int SPEED_PADDLE = 20;
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
        List<Velocity> initialBallVelocities = new LinkedList<Velocity>();
        for (int i = 0; i < NUMBER_OF_BALLS / 2; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(315, 6.5));
        }
        for (int i = NUMBER_OF_BALLS / 2; i < NUMBER_OF_BALLS; i++) {
            initialBallVelocities.add(Velocity.fromAngleAndSpeed(45, 6.5));
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
        return "Green3";
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
        List<Block> blocks = new ArrayList<>();
        // all the values for create blocks in the game.
        int width = 50;
        int high = 30;
        int rows = 5;
        int column = 10;
        int startXPos = 720;
        int startYPos = 150;
        double currentXPos = startXPos;
        double currentYPos = startYPos;
        // create array of colors.
        Color[] colorArray = {Color.GRAY, Color.RED, Color.yellow, Color.BLUE, Color.WHITE, Color.PINK, Color.cyan};
        // initialize new blocks.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < column - i; j++) {
                Color color = colorArray[i];
                Block block = new Block(new Rectangle(new Point(currentXPos, currentYPos), width, high), color);
                blocks.add(block);
                currentXPos -= (width);
            }
            currentYPos += (high);
            currentXPos = startXPos;
        }
        return blocks;
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
        return Color.GREEN;
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
}
