
// 318781630
package game.liseners;
import game.partipcans.Ball;
import game.partipcans.Block;
import game.partipcans.Counter;
import game.partipcans.GameLevel;

/**
 * @author Itamar Bachar.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param gameLevel           the game to we want to connect the ball remover.
     * @param remainingBalls the amount of the ball we have.
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * when a hit is occurred decrease the counter of the ball and remove the ball from the game.
     *
     * @param beingHit the block that has been hit , in this case it probably the block that in the down of the screen.
     * @param hitter   the ball that hit the block.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.remainingBalls.decrease(1);
        hitter.removeFromGame(this.gameLevel);
    }
}
