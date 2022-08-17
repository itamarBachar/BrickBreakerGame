// 318781630
package game.liseners;

import game.partipcans.Ball;
import game.partipcans.Block;

/**
 * @author Itamar Bachar.
 */
public class PrintingHitListener implements HitListener {
    /**
     * constructor.
     */
    public PrintingHitListener() {
    }

    /**
     * print to the user when hit has happen.
     *
     * @param beingHit the block that get hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("Block was hit.");
    }
}
