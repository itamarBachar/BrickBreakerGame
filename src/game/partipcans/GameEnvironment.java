// 318781630
package game.partipcans;

import shapes.Line;
import shapes.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public class GameEnvironment {
    private List<Collidable> listOfcollidable;
    public static final int FRAME_HIGH = 600;
    public static final int FRAME_WIDTH = 800;

    /**
     * constructor.
     *
     * @param listOfCollidable the list of collidable that are participant in the game.
     */
    public GameEnvironment(List<Collidable> listOfCollidable) {
        this.listOfcollidable = listOfCollidable;
    }

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.listOfcollidable = new ArrayList<>();
    }

    /**
     * adding collidable to the list.
     *
     * @param c the collidable that we want to add for the game.
     */
    public void addCollidable(Collidable c) {
        listOfcollidable.add(c);
    }

    /**
     * the method remove the the collidable.
     *
     * @param c the collidable that we want to remove.
     */
    public void removeCollidable(Collidable c) {
        if (this.listOfcollidable.contains(c)) {
            listOfcollidable.remove(c);
        }
    }

    /**
     * the method calculate the closest intersection point for object in the game with the Collidables.
     *
     * @param trajectory the trajectory of the object(the ball) from the current center to the next after one moving.
     * @return the game.partipcans.CollisionInfo that  is actually the intersection point and the which type of
     * collidable the object
     * collied.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        int counter = 0;
        double min = 0;
        int j = 0, t = 0;
        ArrayList<Point> intersectionPoint = new ArrayList<Point>();
        ArrayList<Collidable> collidableObjects = new ArrayList<Collidable>();
        ArrayList<Collidable> duplicateThis = new ArrayList<>(this.listOfcollidable);
        // go over all the collidabes in the game and check if who is intersect with the trajectory
        for (Collidable i : duplicateThis) {
            if ((i).getCollisionRectangle().isCollide(trajectory)) {
                intersectionPoint.add(trajectory.closestIntersectionToStartOfLine((i.getCollisionRectangle())));
                collidableObjects.add(i);
                counter++;
            }
        }
        // if there is no intersection point in this move.
        if (counter == 0) {
            return null;
        }
        // arrange who is the closest intersection point.
        for (Point p : intersectionPoint) {
            if (p != null) {
                min = p.distance(trajectory.end());
                break;
            }
        }
        for (Point p : intersectionPoint) {
            if (min >= p.distance(trajectory.end())) {
                min = p.distance(trajectory.end());
                j = t;
            }
            t++;
        }
        // return the closet intersection point and the type of the collidable.
        CollisionInfo newCollide = new CollisionInfo(intersectionPoint.get(j), collidableObjects.get(j));
        return newCollide;
    }

}
