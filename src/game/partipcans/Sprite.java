//318781630
package game.partipcans;

import biuoop.DrawSurface;

/**
 * @author Itamar Bachar.
 */
public interface Sprite {
    /**
     * // draw the sprite to the screen.
     *
     * @param d the surface we draw on.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();

}
