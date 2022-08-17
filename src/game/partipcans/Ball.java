// 318781630
package game.partipcans;

import biuoop.DrawSurface;
import shapes.Line;
import shapes.Point;
import shapes.Velocity;

import java.awt.Color;


/**
 * @author Itamar Bachar.
 */
public class Ball implements Sprite {
    private Point point;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;


    /**
     * constructor.
     *
     * @param center the center point of the ball.
     * @param r      the radios of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.point = center;
        this.r = r;
        this.color = color;
    }

    /**
     * another constructor.
     *
     * @param x     the x value of the center point.
     * @param y     the x value of the center point.
     * @param r     the radios of the ball
     * @param color the color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * another constructor.
     *
     * @param x     the x value of the center point.
     * @param y     the x value of the center point.
     * @param r     the radios of the ball
     * @param color the color of the ball.
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.point = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * getter of gameEnvironment.
     *
     * @return the gameEnvironment.
     */
    public GameEnvironment getGameEnvironment() {
        return gameEnvironment;
    }

    /**
     * getter of x value.
     *
     * @return the x value of the center point.
     */
    public int getX() {
        return (int) this.point.getX();
    }

    /**
     * getter of y value.
     *
     * @return the y value of the ball.
     */
    public int getY() {
        return (int) this.point.getY();
    }

    /**
     * getter of radius.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * getter of velocity.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * getter of the color ball.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * setter for game.partipcans.GameEnvironment.
     *
     * @param currentGame the game we want to the ball to participant.
     */
    public void setGameEnvironment(GameEnvironment currentGame) {
        this.gameEnvironment = currentGame;
    }

    /**
     * the method draw the ball on the screen.
     *
     * @param surface that we draw on.
     */

    public void drawOn(DrawSurface surface) {
        //set the color of the ball.
        surface.setColor(this.color);
        // print th ball on the surface with color we set.
        surface.fillCircle(this.getX(), this.getY(), r);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), r);
    }

    /**
     * the method call to move one step.
     */
    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * set the velocity of the ball.
     *
     * @param t the velocity we want to set.
     */
    public void setVelocity(Velocity t) {
        this.v = t;
    }

    /**
     * set the velocity of the ball with dx and dy.
     *
     * @param dx the change of the x value in the ball point center.
     * @param dy the change of the y value in the ball point center.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * the method purpose is to the move the ball on the screen.
     */
    public void moveOneStep() {
        if (this.v == null) {
            return;
        }
        // calculate the next step than crate a trajectory that is a line from the current center of the ball to the
        // next center.
        Point nextPoint = this.getVelocity().applyToPoint(this.point);
        Point nextNext = this.getVelocity().applyToPoint(nextPoint);
        Line trajectory = new Line(nextPoint, nextNext);
        // check what is the next collision point.
        CollisionInfo collision = gameEnvironment.getClosestCollision(trajectory);
        if (collision == null) {
            this.point = this.getVelocity().applyToPoint(this.point);
        } else {
            // next step we have collision, so change the velocity.
            this.v = collision.collisionObject().hit(this, collision.collisionPoint(), this.v);
            // move the center of the ball.
            this.point = this.getVelocity().applyToPoint(this.point);
            // check if we have right after the hit another one.
            Point afterCollisionPoint = this.getVelocity().applyToPointMultiply3(this.point);
            Line trajectory2 = new Line(this.point, afterCollisionPoint);
            CollisionInfo collisionInfo2 = gameEnvironment.getClosestCollision(trajectory2);
            if (collisionInfo2 != null) {
                // if it is a hit update th a point.
                this.v = collisionInfo2.collisionObject().hit(this, collisionInfo2.collisionPoint(), this.v);
                this.point = this.getVelocity().applyToPoint(this.point);
            }
        }
    }

    /**
     * the method add the ball to sprites of the game.
     *
     * @param g the current game the ball is participant.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }



    /**
     * the method remove the ball from the game.
     *
     * @param g the game we want the ball to be removed.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}