// 318781630
package game.partipcans;

/**
 * @author Itamar Bachar.
 */
public class Counter {
    private int counter;

    /**
     * constructor.
     */
    public Counter() {
        this.counter = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number we want to add to the counter.
     */
    public void increase(int number) {
        counter += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number we want to decrease to the counter
     */
    public void decrease(int number) {
        counter -= number;
    }

    /**
     * get current count.
     *
     * @return the value.
     */
    public int getValue() {
        return this.counter;
    }

    @Override
    public String toString() {
        return "" + counter + "";
    }
}
