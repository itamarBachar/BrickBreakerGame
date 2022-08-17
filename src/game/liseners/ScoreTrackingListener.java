// 318781630
package game.liseners;

import game.partipcans.Ball;
import game.partipcans.Block;
import game.partipcans.Counter;

/**
 * @author Itamar Bachar.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor.
     *
     * @param scoreCounter the score we track of.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * increase the value of the score because hit is occur.
     *
     * @param beingHit the block that get hit.
     * @param hitter   the ball that hit the block.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

}
