//318781630/
package game.partipcans;

import biuoop.DrawSurface;

/**
 * @author Itamar Bachar.
 */
public interface Animation {
    /**
     * do one frame on the animation.
     *
     * @param d the surface we draw on.
     */
    void doOneFrame(DrawSurface d);

    /**
     * @return if we need to stop the run on the animation.
     */
    boolean shouldStop();
}
