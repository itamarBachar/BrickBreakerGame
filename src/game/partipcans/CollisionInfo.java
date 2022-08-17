// 318781630
package game.partipcans;

import shapes.Point;

/**
 * @author Itamar Bachar.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable objectThatCollided;

    /**
     * constructor.
     *
     * @param collisionPoint     the point the ball and the object Collided.
     * @param objectThatCollided the object that the ball collide with.
     */
    public CollisionInfo(Point collisionPoint, Collidable objectThatCollided) {
        this.collisionPoint = collisionPoint;
        this.objectThatCollided = objectThatCollided;
    }

    /**
     * getter for collisionPoint.
     *
     * @return the collisionPoint.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * getter for collision Object.
     *
     * @return objectThatCollided.
     */
    public Collidable collisionObject() {
        return this.objectThatCollided;
    }

}
