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
public class GameOver implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter score;

    /**
     * constructor.
     *
     * @param keyboard the KeyboardSensor.
     * @param score    the score.
     */
    public GameOver(KeyboardSensor keyboard, Counter score) {
        this.keyboard = keyboard;
        this.stop = true;
        this.score = score;
    }

    /**
     * draw to the user the score and that the game end.
     *
     * @param d the surface we draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, "Game Over.Your score is " + this.score, 32);
    }

    /**
     * @return if we need to stop the run on the class.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }

}
