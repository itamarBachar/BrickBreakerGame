// 318781630
package game.liseners;

import game.partipcans.Ball;
import game.partipcans.Block;

/**
 * @author Itamar Bachar.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game.partipcans.Ball that's doing the hitting.
     *
     * @param beingHit the block that get hit.
     * @param hitter   the ball that hit the block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
