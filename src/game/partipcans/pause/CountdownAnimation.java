//318781630.
package game.partipcans.pause;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.partipcans.Animation;
import game.partipcans.SpriteCollection;

import java.awt.Color;

/**
 * @author Itamar Bachar.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Boolean stop;
    private int duplicate;

    /**
     * constructor.
     *
     * @param numOfSeconds the num of seconds the count show.
     * @param countFrom the number that the count start from.
     * @param gameScree all the sprite in the game.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScree) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScree;
        this.stop = false;
        this.duplicate = countFrom;
    }

    /**
     * @param d the surface we draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        if (duplicate != countFrom) {
            long sleepFor = (long) ((numOfSeconds / (double) duplicate) * 1000);
            Sleeper sleeper = new Sleeper();
            sleeper.sleepFor(sleepFor);
        }
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(370, 300, "" + countFrom + "", 32);
        countFrom--;
        if (countFrom == -1) {
            this.stop = true;
        }
    }

    /**
     * @return if the animation need to stop.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
