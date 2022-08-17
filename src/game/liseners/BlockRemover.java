// 318781630
package game.liseners;

import game.partipcans.Ball;
import game.partipcans.Block;
import game.partipcans.Counter;
import game.partipcans.GameLevel;

/**
 * @author Itamar Bachar.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     *
     * @param gameLevel            the game we want to listen to.
     * @param remainingBlocks the amount of block thar remain.
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Blocks that are hit should be removed
     * from the game. Remember to remove this listener from the block
     * that is being removed from the game.
     *
     * @param beingHit the block that got hit.
     * @param hitter   the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        remainingBlocks.decrease(1);
        beingHit.removeFromGame(gameLevel);
    }

}
