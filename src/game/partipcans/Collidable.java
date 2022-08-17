// 318781630
package game.partipcans;

import biuoop.DrawSurface;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

/**
 * @author Itamar Bachar.
 */
public interface Collidable {

    /**
     * getter method.
     *
     * @return the  current  rectangle
     */
    Rectangle getCollisionRectangle();


    /**
     * the method calculate new velocity for the object after he hit collidable.
     *
     * @param hitter          the ball that hit the block.
     * @param collisionPoint  the point the game.partipcans.Collidable and the object intersect.
     * @param currentVelocity the current velocity of the object that met the collidable.
     * @return The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);



    /**
     * the method draw the coliidable on the surface.
     *
     * @param surface the surface that we want to draw on.
     */
    void drawOn(DrawSurface surface);
}
