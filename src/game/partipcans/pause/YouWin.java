//318781630ץ
package game.partipcans.pause;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.partipcans.Animation;
import game.partipcans.Counter;

import java.awt.Color;

/**
 * @author Itamar Bachar.
 */
public class YouWin implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter counter;

    /**
     * constructor.
     *
     * @param keyboard the KeyboardSensor.
     * @param counter  the score.
     */
    public YouWin(KeyboardSensor keyboard, Counter counter) {
        this.keyboard = keyboard;
        this.stop = true;
        this.counter = counter;
    }

    /**
     * draw to the user the score and that the game end and he win.
     *
     * @param d the surface we draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.counter, 32);
    }

    /**
     * @return if we need to stop the run on the class.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
