//318781630.
package game.partipcans.pause;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.partipcans.Animation;

import java.awt.Color;

/**
 * @author Itamar Bachar.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k the KeyboardSensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = true;
    }

    /**
     * draw to the user that he stop.
     *
     * @param d the surface we draw on.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * @return if we need to stop the run on the class.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
