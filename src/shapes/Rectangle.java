//318781630
package shapes;

import java.util.ArrayList;

/**
 * @author Itamar Bachar.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Point upperRight;
    private Point downLeft;
    private Point downRight;
    private Line leftLength;
    private Line rightLength;
    private Line upWidth;
    private Line downWidth;

    /**
     * constructor.
     *
     * @param upperLeft the upper left point of the rectangle.
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
        setOtherLocationRectangle();
        setLinesOfRectangle();
    }

    /**
     * the method calculate all the intersection point of line with the rectangle.
     *
     * @param line the line that we want to check.
     * @return array of the intersection point.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // set the other point and lines of the rectangle.
        setOtherLocationRectangle();
        setLinesOfRectangle();
        // check if intersect every part of the rectangle.
        ArrayList<Point> intersectionPoint = new ArrayList<>();
        if (this.upWidth.isIntersecting(line)) {
            intersectionPoint.add(this.upWidth.intersectionWith(line));
        }
        if (this.downWidth.isIntersecting(line)) {
            intersectionPoint.add(this.downWidth.intersectionWith(line));
        }
        if (this.rightLength.isIntersecting(line)) {
            intersectionPoint.add(this.rightLength.intersectionWith(line));
        }
        if (this.leftLength.isIntersecting(line)) {
            intersectionPoint.add(this.leftLength.intersectionWith(line));
        }
        return intersectionPoint;
    }

    /**
     * setter of other point of the rectangle.
     */
    public void setOtherLocationRectangle() {
        this.upperRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.downLeft = new Point(upperLeft.getX(), upperLeft.getY() + height);
        this.downRight = new Point(upperRight.getX(), downLeft.getY());
    }

    /**
     * setter other lines of the rectangle.
     */
    public void setLinesOfRectangle() {
        this.upWidth = new Line(upperLeft, upperRight);
        this.downWidth = new Line(downLeft, downRight);
        this.leftLength = new Line(upperLeft, downLeft);
        this.rightLength = new Line(upperRight, downRight);
    }

    /**
     * setter for upperLeft.
     *
     * @param upperPoint the point we want to set.
     */
    public void setUpperLeft(Point upperPoint) {
        this.upperLeft = upperPoint;
    }

    /**
     * getter.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * getter.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * getter.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * getter.
     *
     * @return the down left point of the rectangle.
     */
    public Point getDownLeft() {
        if (this.downLeft == null) {
            setOtherLocationRectangle();
        }
        return this.downLeft;
    }

    /**
     * getter.
     *
     * @return the lift line of the rectangle.
     */
    public Line getLeftLength() {
        if (this.leftLength == null) {
            setLinesOfRectangle();
        }
        return this.leftLength;
    }

    /**
     * getter.
     *
     * @return the right  side of the line.
     */
    public Line getRightLength() {
        if (this.rightLength == null) {
            setLinesOfRectangle();
        }
        return this.rightLength;
    }

    /**
     * getter.
     *
     * @return the up line of the rectangle.
     */
    public Line getUpWidth() {
        if (this.upWidth == null) {
            setLinesOfRectangle();
        }
        return this.upWidth;
    }

    /**
     * getter.
     *
     * @return the down line of the rectangle.
     */
    public Line getDownWidth() {
        if (this.downWidth == null) {
            setLinesOfRectangle();
        }
        return this.downWidth;
    }

    /**
     * getter.
     *
     * @return the upper right point of the rectangle.
     */
    public Point getUpperRight() {
        if (this.upperRight == null) {
            setOtherLocationRectangle();
        }
        return this.upperRight;
    }

    /**
     * getter.
     *
     * @return the down right point of the rectangle.
     */
    public Point getDownRight() {
        if (this.downRight == null) {
            setOtherLocationRectangle();
        }
        return this.downRight;
    }

    /**
     * the method check if line and rectangle intersect.
     *
     * @param line the line we check.
     * @return true if they intersect.
     */
    public boolean isCollide(Line line) {
        if ((this.getDownWidth().intersectionWith(line) != null)
                || (this.getUpWidth().intersectionWith(line) != null)
                || (this.getLeftLength().intersectionWith(line) != null)
                || (this.getRightLength().intersectionWith(line) != null)) {
            return true;
        }
        return false;
    }


}
