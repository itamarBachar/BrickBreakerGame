//318781630
package shapes;

/**
 * @author Itamar Bachar.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x the x value of the point.
     * @param y the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * the method check the distance between the instance and other point.
     *
     * @param other the other point that we check with her distance.
     * @return the distance between the two point.
     */
    public double distance(Point other) {
        return Math.sqrt((this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * the method check if two point are equal.
     *
     * @param other the other point.
     * @return true if they  are the same point.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        double epsilon = Math.pow(10, -15);
        if (Math.abs(other.getX() - this.getX()) < epsilon && other.getY() == this.getY()) {
            return true;
        }
        if (Math.abs(other.getY() - this.getY()) < epsilon && other.getX() == this.getX()) {
            return true;
        }
        if ((Math.abs(other.getY() - this.getY())) < epsilon && (Math.abs(other.getX() - this.getX()) < epsilon)) {
            return true;
        }
        return (other.getX() == this.getX()) && (other.getY() == this.getY());
    }

    /**
     * getter of the x value of point.
     *
     * @return the x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * getter of y value of point.
     *
     * @return the y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * setter.
     *
     * @param v the value we want to add to the point x value.
     */
    public void setX(double v) {
        this.x = this.getX() + v;
    }

    /**
     * setter.
     *
     * @param t the value we want to add to the point y value.
     */
    public void setY(double t) {
        this.y = this.getY() + this.y;
    }

    /**
     * setter.
     *
     * @param p the point we want to set.
     */
    public void setPoint(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * setter.
     *
     * @param p1 the point that have x value we want to set to the point.
     */
    public void setX(Point p1) {
        this.x = p1.x;
    }

    /**
     * setter.
     *
     * @param p1 the point that have y value we want to set to the point.
     */
    public void setY(Point p1) {
        this.y = p1.y;
    }

}

