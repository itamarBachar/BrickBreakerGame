//318781630.
package game.partipcans.pause;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.partipcans.Animation;

/**
 * @author Itamar Bachar.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed = true;

    /**
     * constructor.
     *
     * @param sensor    the sensor.
     * @param key       the key.
     * @param animation the animation we want to run on.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * print to the screen the pause of the animation.
     *
     * @param d the surface we want to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // do the display by the animation that should be.
        animation.doOneFrame(d);
        // ask if the key that represent the continue is press.
        if (this.sensor.isPressed(this.key)) {
            // ask if we already use him.
            if (isAlreadyPressed) {
                return;
            }
            this.stop = true;
        }
        isAlreadyPressed = false;
    }

    /**
     * @return if we need to stop the run on the class.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
