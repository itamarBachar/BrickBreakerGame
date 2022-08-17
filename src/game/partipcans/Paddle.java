// 318781630
package game.partipcans;

import biuoop.DrawSurface;
import biuoop.GUI;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;


import java.awt.Color;

/**
 * @author Itamar Bachar.
 * * The class is about line features.
 */
public class Paddle implements Sprite, Collidable {
    public static final int PADDLE_HIGH = 20;
    private int moveOfPaddle;
    private final int maxFrameXVaule = 770;
    private final int minFrameXVaule = 30;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Color color;
    private GUI gui;


    /**
     * constructor.
     *
     * @param rectangle the paddle shape.
     * @param color     the color of the paddle.
     * @param gui       the surface that the paddle appear
     * @param moveOfPaddle the amount of the move.
     */
    public Paddle(Rectangle rectangle, Color color, GUI gui, int moveOfPaddle) {
        this.color = color;
        this.rectangle = rectangle;
        this.gui = gui;
        this.keyboard = gui.getKeyboardSensor();
        this.moveOfPaddle = moveOfPaddle;

    }


    /**
     * the method move the paddle left.
     */
    public void moveLeft() {
        rectangle.setOtherLocationRectangle();
        rectangle.setLinesOfRectangle();
        // get the location of the paddle.
        double upperLeftX = rectangle.getUpperLeft().getX();
        double upperLeftY = rectangle.getUpperLeft().getY();
        // move the ball according to the final MoveOfPaddle if he doesn't do out from the screen.
        if (rectangle.getUpperLeft().getX() - moveOfPaddle <= minFrameXVaule) {
            rectangle.setUpperLeft(new Point(minFrameXVaule, rectangle.getUpperLeft().getY()));
            rectangle.setOtherLocationRectangle();
            rectangle.setLinesOfRectangle();
            return;
        }
        rectangle.setUpperLeft(new Point(upperLeftX - moveOfPaddle, upperLeftY));
        rectangle.setOtherLocationRectangle();
        rectangle.setLinesOfRectangle();
    }

    /**
     * the method move the paddle right.
     */
    // get the location of the paddle.
    public void moveRight() {
        rectangle.setOtherLocationRectangle();
        rectangle.setLinesOfRectangle();
        double upperLeftX = rectangle.getUpperLeft().getX();
        double upperLeftY = rectangle.getUpperLeft().getY();
        // move the ball according to the final MoveOfPaddle if he doesn't do out from the screen.
        if (rectangle.getUpperRight().getX() + moveOfPaddle >= maxFrameXVaule) {
            rectangle.setUpperLeft(new Point(maxFrameXVaule - rectangle.getWidth(), rectangle.getUpperLeft().getY()));
            rectangle.setOtherLocationRectangle();
            rectangle.setLinesOfRectangle();
            return;
        }
        rectangle.setUpperLeft(new Point(upperLeftX + moveOfPaddle, upperLeftY));
        rectangle.setOtherLocationRectangle();
        rectangle.setLinesOfRectangle();
    }


    /**
     * the method call to move methods if the user pressed on the key.
     */
    public void timePassed() {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * the method draw the paddle on the surface.
     *
     * @param d the surface that we have the game on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.black);
        d.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    /**
     * getter.
     *
     * @return the rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.rectangle.getUpperLeft(), this.rectangle.getWidth(), this.rectangle.getHeight());
    }

    /**
     * the method calculate new velocity for the object after he hit collidable.
     *
     * @param collisionPoint  the point the game.partipcans.Collidable and the object intersect.
     * @param currentVelocity the current velocity of the object that met the collidable.
     * @param hitter          the ball that hit the block.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // create new velocity.
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // check if intersect the left side of the paddle.
        if (this.rectangle.getLeftLength().isBetween(collisionPoint)) {
            newVelocity.setDx(newVelocity.getDx() * (-1));
        }
        // check if intersect the left side of the paddle.
        if (this.rectangle.getRightLength().isBetween(collisionPoint)) {
            newVelocity.setDx(newVelocity.getDx() * (-1));
        }
        // check if intersect the left side of the paddle.
        if (this.rectangle.getUpWidth().isBetween(collisionPoint)) {
            // divide the paddle to 5 region.
            double regionSize = rectangle.getWidth() / 5;
            // calculate the 5 regions of the paddle.
            double distanceFromUpperLeft = collisionPoint.getX() - rectangle.getUpperLeft().getX();
            double result = distanceFromUpperLeft / regionSize;
            double speed = Math.pow(newVelocity.getDx(), 2) + Math.pow(newVelocity.getDy(), 2);
            speed = Math.sqrt(speed);
            // change the ball velocity by which part of the paddle he hit.
            if (result >= 0 && result < 1) {
                newVelocity = newVelocity.fromAngleAndSpeed(-60, speed);
            }
            if (result >= 1 && result < 2) {
                newVelocity = newVelocity.fromAngleAndSpeed(-30, speed);
            }
            if (result >= 2 && result < 3) {
                newVelocity = newVelocity.fromAngleAndSpeed(0, speed);
            }
            if (result >= 3 && result < 4) {
                newVelocity = newVelocity.fromAngleAndSpeed(30, speed);
            }
            if (result >= 4 && result <= 5) {
                newVelocity = newVelocity.fromAngleAndSpeed(60, speed);
            }
        }
        // return the new velocity after the hit.
        if (this.rectangle.getDownWidth().isBetween(collisionPoint)) {
            newVelocity.setDy(newVelocity.getDy() * (-1));
        }
        return newVelocity;
    }


    /**
     * add the paddle to the list of collides and list od sprite in the game.
     *
     * @param g the game we want to add paddle.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
