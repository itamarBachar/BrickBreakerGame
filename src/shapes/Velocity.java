//318781630
package shapes;

/**
 * @author Itamar Bachar.
 */
public class Velocity {

    private double dx;
    private double dy;

    /**
     * constructor.
     *
     * @param dx the dx of velocity
     * @param dy the dy of velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * setter.
     *
     * @param v the value we set for dx.
     */
    public void setDx(double v) {
        this.dx = v;
    }

    /**
     * setter.
     *
     * @param v the value we set for dy.
     */
    public void setDy(double v) {
        this.dy = v;
    }

    /**
     * getter of the Dx.
     *
     * @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * getter of the dy.
     *
     * @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the method change point value with increase x value with dx and  y  with dy and return the new point.
     *
     * @param p the point we want to change.
     * @return the point after the changing.
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        double x = this.dx + p.getX();
        double y = this.dy + p.getY();
        Point newPoint = new Point(x, y);
        return newPoint;
    }

    /**
     * the method do apply on a point three time.
     *
     * @param p the point we want to change.
     * @return the new point after adding dx and dy.
     */
    public Point applyToPointMultiply3(Point p) {
        if (p == null) {
            return null;
        }
        double x = (3) * this.dx + p.getX();
        double y = (3) * this.dy + p.getY();
        Point newPoint = new Point(x, y);
        return newPoint;
    }

    /**
     * the method purpose is to change to angle and speed we get  to dx and dy.
     *
     * @param angle the angle we get.
     * @param speed the speed we get.
     * @return dx and dy.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle - 90)) * speed;
        double dy = Math.sin(Math.toRadians(angle - 90)) * speed;
        return new Velocity(dx, dy);
    }
}

