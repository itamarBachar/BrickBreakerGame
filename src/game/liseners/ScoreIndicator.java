//318781630
package game.liseners;

import biuoop.DrawSurface;
import game.partipcans.Sprite;
import game.partipcans.Counter;
import game.partipcans.GameLevel;

import java.awt.Color;

/**
 * @author Itamar Bachar.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    public static final int X_START = 300;
    public static final int Y_START = 20;

    /**
     * constructor.
     *
     * @param score the score we want to draw on the screen.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    /**
     * draw the  score on the screen.
     *
     * @param d the surface we draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        // create a rectangle in the upper side in the screen to write the score on.
        d.setColor(Color.white);
        d.fillRectangle(0, 0, 800, 25);
        d.setColor(Color.gray);
        d.drawText(X_START, Y_START, " Score : " + Integer.toString(this.score.getValue()), 20);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add th eindicator to the game.
     *
     * @param g the ghame we want to add.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
