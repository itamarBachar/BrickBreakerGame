// 318781630
package shapes;

import java.util.ArrayList;

/**
 * @author Itamar Bachar.
 * * The class is about line features.
 */

public class Line {
    private Point start;
    private Point end;

    /**
     * class constructor.
     *
     * @param start the starting point of the line.
     * @param end   the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * class constructor.
     *
     * @param x1 the x value of the staring point.
     * @param x2 the x value of the ending point.
     * @param y1 the y value of the starting point.
     * @param y2 the y value of the ending point.
     */

    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * calculate the length of the line by checking the formula of distance between to points.
     *
     * @return the length of the line.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculate the middle point between the start and ending point.
     *
     * @return the middle point.
     */
    public Point middle() {
        double x1 = (this.end.getX() + this.start.getX()) / 2.0;
        double y1 = (this.end.getY() + this.start.getY()) / 2.0;
        Point middle = new Point(x1, y1);
        return middle;
    }

    /**
     * getter of start.
     *
     * @return the start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * getter of end point.
     *
     * @return the end point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * the method set the value of the start point to be the end and on the contrary.
     *
     * @return the new line after the change.
     */
    private Line reverseLine() {
        if (this.start.getX() >= this.end.getX()) {
            Line line = new Line(this.end, start);
            return line;
        }
        return new Line(this.start, this.end);
    }

    /**
     * The method check if two lines are intersecting.
     *
     * @param other is line that we want to check if he is intersecting with instance line.
     * @return true if intersecting false  otherwise.
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        // get the intersection point by help method.
        Point intersection = helpTOIntersectionWith(other);
        if (intersection == null) {
            return false;
        }
        // check if the point that we get is really on the two lines.
        if (intersection.getX() >= Math.min(this.start.getX(), this.end.getX())
                && intersection.getX() <= Math.max(this.start.getX(), this.end.getX())
                && intersection.getX() <= Math.max(other.start.getX(), other.end.getX())
                && intersection.getX() >= Math.min(other.start.getX(), other.end.getX())
                && intersection.getY() >= Math.min(this.start.getY(), this.end.getY())
                && intersection.getY() <= Math.max(this.start.getY(), this.end.getY())
                && intersection.getY() <= Math.max(other.start.getY(), other.end.getY())
                && intersection.getY() >= Math.min(other.start.getY(), other.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * The method check if there is intersection if indeed return the intersection point.
     *
     * @param other is the other line we want to check the intersection.
     * @return the intersection if exist.
     */
    public Point intersectionWith(Line other) {
        // send to help method.
        Point intersection = helpTOIntersectionWith(other);
        // check if the point is null or not and return the value.
        if (intersection == null) {
            return null;
        }
        if (isIntersecting(other)) {
            return intersection;
        }
        return null;
    }

    /**
     * the method calculate the intersection point and return him.
     *
     * @param other the other line that we want to check.
     * @return the point value of the intersection.
     */
    private Point helpTOIntersectionWith(Line other) {
        // check if the two line are equal.
        if (this.equals(other)) {
            // check if the instance is actually a point, in this case return the point.
            if (this.start.equals(this.end)) {
                return this.start;
            }
            // this is the same line.
            return null;
        }
        // send to method that check the incline of the lines.
        double inclineThis = this.incline();
        double inclineOther = other.incline();
        // send to method that check the rest of the lines.
        double restThis = this.findRest();
        double restOther = other.findRest();
        // send to method that check Intersection point when the incline is infinity.
        Point check = infinityCase(inclineThis, inclineOther, restThis, restOther, this, other);
        // send to check if the two lines have infinity incline.
        if (alwaysInfinity(inclineOther, inclineThis)) {
            // check if the line is exactly continue the next line.
            if (!lineContinueLine(other)) {
                return null;
            }
            // the lines are really continue each other return the end of the line that are below the other.
            return checkBiggerToInfinity(this, other);
        }
        // if one of the lines have incline of infinity.
        if (check != null) {
            // check if the other line is actually a point.
            if (lineIsPoint(other)) {
                // check if is in the line, if it is return the point.
                if (this.isBetween(other.start)) {
                    return other.start;
                }
                return null;
            }
            // same as before just now checking if this is a point.
            if (this.lineIsPoint()) {
                if (other.isBetween(this.start)) {
                    return this.start;
                }
                return null;
            }
            return check;
        }
        if (inclineThis == 0 && inclineOther == 0) {
            Point p1 = this.bothZeroIncline(other);
            return p1;
        }
        // this is regular case of two lines withe normal incline , calculate and return if exist intersection point.
        double x = (restOther - restThis) / (inclineThis - inclineOther);
        double y = (inclineThis * x) + restThis;
        Point intersection = new Point(x, y);
        return intersection;
    }

    /**
     * check if the instance and the other are actually the same line.
     *
     * @param other is the other line we want to check.
     * @return true if there are the same line.
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end)
                || this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * calculate the incline of line.
     *
     * @return return the incline.
     */
    public double incline() {
        if ((this.start.getY() - this.end.getY() == 0)
                && (this.start.getX() - this.end.getX() == 0)) {
            return 0;
        }
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }

    /**
     * find the rest to the the equation of a line.
     *
     * @return the rest of the equation of line.
     */
    public double findRest() {
        double incline = this.incline();
        return (this.start.getY() - (this.start.getX() * incline));
    }

    /**
     * The method find the intersection point between to lines in case of one or both of the incline are infinity.
     *
     * @param inclineThis  the incline of the the instance.
     * @param inclineOther the incline of the other line.
     * @param restThis     the rest of the instance equation of line.
     * @param restOther    the rest of the other line equation of line.
     * @param thisLine     the instance.
     * @param other        the other line.
     * @return the point intersection.
     */
    private Point infinityCase(double inclineThis, double inclineOther, double restThis,
                               double restOther, Line thisLine, Line other) {
        // check if the incline of other is infinity.
        if (Float.isInfinite((float) inclineOther)) {
            if (Float.isInfinite((float) inclineThis)) {
                // the incline of the two line are infinity return null.
                return null;
            } else {
                // return potential intersection point.
                Point intersection = new Point(other.start.getX(), other.start.getX() * inclineThis + restThis);
                return intersection;
            }
        }
        if (Float.isInfinite((float) inclineThis)) {
            //the instance incline is infinity return potential intersection point.
            Point intersection = new Point(thisLine.start.getX(), thisLine.start.getX() * inclineOther + restOther);
            return intersection;
        }
        // none of the incline are infinity.
        return null;
    }

    /**
     * check if the two incline are infinity.
     *
     * @param inclineOther the incline of the other line.
     * @param inclineThis  the incline of the instance.
     * @return true if the two incline are infinity.
     */
    private Boolean alwaysInfinity(double inclineOther, double inclineThis) {
        if (Float.isInfinite((float) inclineOther)) {
            return Float.isInfinite((float) inclineThis);
        }
        return false;
    }

    /**
     * check if line is actually a point.
     *
     * @param lineToCheck the line we want to check.
     * @return true if it is a point.
     */
    private boolean lineIsPoint(Line lineToCheck) {
        return lineToCheck.start.equals(lineToCheck.end);
    }

    /**
     * check if line is a point.
     *
     * @return true if it is a point.
     */
    private boolean lineIsPoint() {
        return this.start.equals(this.end);
    }

    /**
     * check if a point is between the line.
     *
     * @param pointToCheck the point we want to check.
     * @return true if the point is indeed in the line.
     */
    public boolean isBetween(Point pointToCheck) {
        if (pointToCheck.getX() <= Math.max(this.start.getX(), this.end.getX())
                && pointToCheck.getX() >= Math.min(this.start.getX(), this.end.getX())
                && pointToCheck.getY() >= Math.min(this.start.getY(), this.end.getY())
                && pointToCheck.getY() <= Math.max((this.start.getY()), this.end.getY())) {
            return true;
        }
        return false;
    }

    /**
     * check if line is continue of other line.
     *
     * @param lineToCheck the line we want to check.
     * @return true if the line is continue of other line.
     */
    private boolean lineContinueLine(Line lineToCheck) {
        // if the x of the start are after the end replace them.
        Line temp1 = new Line(start, this.end);
        Line temp2 = new Line(lineToCheck.start, lineToCheck.end);
        if (this.start.getX() >= this.end.getX() && this.start.getY() > this.end.getY()) {
            temp1 = this.reverseLine();
        }
        // if the x of the start are after the end replace them.
        if (lineToCheck.start.getX() >= lineToCheck.end.getX()
                && lineToCheck.start.getY() > lineToCheck.end.getY()) {
            temp2 = lineToCheck.reverseLine();
        }
        // if the end of the one point is the start of the other return true.
        if (temp1.end.equals(temp2.start) || temp2.end.equals(temp1.start)) {
            return true;
        }
        return false;
    }

    /**
     * check between to lines that have incline of infinity if that the  have intersection and if indeed the
     * intersection point return it.
     *
     * @param p1 line one.
     * @param p2 line two.
     * @return the intersection point.
     */
    private Point checkBiggerToInfinity(Line p1, Line p2) {
        // check if they have the same x value.
        if (p1.end.getX() != p2.end.getX()) {
            return null;
        }
        if (p1.end.equals(p2.start)) {
            return p1.end;
        }
        if (p1.start.equals(p2.end)) {
            return p2.end;
        }
        return null;
    }


    /**
     * check if two line that have zero incline continue each other.
     *
     * @param other the other line that we want to check with.
     * @return the point intersection between the lines.
     */

    private Point bothZeroIncline(Line other) {
        Line temp1 = new Line(this.start, this.end);
        Line temp2 = new Line(other.start, other.end);
        temp1 = this.reverseLine();
        temp2 = other.reverseLine();
        if (temp1.end.equals(temp2.start)) {
            return this.end;
        }
        if (temp1.start.equals(temp2.end)) {
            return this.start;
        }
        return null;
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.

    /**
     * the method check which of the intersection point is closer to the line.
     *
     * @param rect the rectangle that the line collied.
     * @return the closer point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        // send to calculate intersection points of the rectangle with the line and set from them array.
        ArrayList<Point> intersectionPoint = (ArrayList<Point>) rect.intersectionPoints(this);
        if (intersectionPoint.isEmpty()) {
            return null;
        }
        // check if there is just ne intersection point, and after calculate who is closer and return him.
        double distance1 = intersectionPoint.get(0).distance(this.start);
        if (intersectionPoint.size() == 1) {
            return intersectionPoint.get(0);
        }
        if (rect.getUpperLeft().equals(this.start) || rect.getDownLeft().equals(this.start)
                || rect.getDownRight().equals(this.start) || rect.getUpperRight().equals(this.start)) {
            if (intersectionPoint.size() > 2) {
                return this.start;
            }
            return intersectionPoint.get(1);
        }
        double distance2 = intersectionPoint.get(1).distance(this.start);
        if (distance1 < distance2) {
            return intersectionPoint.get(0);
        }
        return intersectionPoint.get(1);
    }

}
