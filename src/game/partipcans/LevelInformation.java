//318781630.
package game.partipcans;

import shapes.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public interface LevelInformation {
    /**
     * @return the number of balls that take place in the game.
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     *
     * @return the list of the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * @return how much the ball move in one pressed right or left.
     */
    int paddleSpeed();

    /**
     * @return the width of the paddle.
     */
    int paddleWidth();


    /**
     * @return the level name will be displayed at the top of the screen.
     */
    String levelName();


    /**
     * @return Returns a sprite with the background of the level.
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains,
     * its size, color and location.
     *
     * @return the list of the blocks.
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed.
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number Of Blocks To Remove.
     */
    int numberOfBlocksToRemove();

    /**
     * @return the color of the background.
     */
    Color background();

    /**
     * @return the x value.
     */
    int getPaddleXStart();

    /**
     * @return the y value.
     */
    int getPaddleYStart();
}
