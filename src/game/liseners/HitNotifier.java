// 318781630
package game.liseners;

/**
 * @author Itamar Bachar.
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl the listener we want to add to the list.
     */
    void addHitListener(HitListener hl);


    /**
     * // Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener we want to remove to the list.
     */
    void removeHitListener(HitListener hl);
}
