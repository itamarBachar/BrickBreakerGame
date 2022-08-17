// 318781630
package game.partipcans;

import biuoop.DrawSurface;
import game.liseners.HitListener;
import game.liseners.HitNotifier;
import shapes.Point;
import shapes.Rectangle;
import shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private boolean ifHitMe;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor for block.
     *
     * @param rectangle block rectangle.
     * @param color     of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.color = color;
        this.rectangle = rectangle;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * another constructor for the block.
     *
     * @param rectangle block rectangle.
     */
    public Block(Rectangle rectangle) {
        color = Color.blue;
        this.rectangle = rectangle;
    }

    /**
     * getter of rectangle.
     *
     * @return the rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * the method calculate new velocity (for the ball) after hitting the ball.
     * @param hitter the current ball.
     * @param collisionPoint  the point (the ball or other sprite in the future) that the hit occurs
     * @param currentVelocity the velocity of the ball.
     * @return new velocity after the hit.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());
        // check which part of the rectangle the hit occurs and change the velocity according to this.
        // if it occurs in the left side of the rectangle you need to change the dx by multiply in -1.
        this.rectangle.setOtherLocationRectangle();
        this.rectangle.setLinesOfRectangle();
        if (this.rectangle.getLeftLength().isBetween(collisionPoint)) {
            newVelocity.setDx(newVelocity.getDx() * (-1));
        }
        // if it occurs in the right side of the rectangle you need to change the dx by multiply in -1.
        if (this.rectangle.getRightLength().isBetween(collisionPoint)) {
            newVelocity.setDx(newVelocity.getDx() * (-1));
        }
        // if it occurs in the upper side of the rectangle you need to change the dx by multiply in -1.
        if (this.rectangle.getUpWidth().isBetween(collisionPoint)) {
            newVelocity.setDy(newVelocity.getDy() * (-1));
        }
        // if it occurs in the down side of the rectangle you need to change the dx by multiply in -1.
        if (this.rectangle.getDownWidth().isBetween(collisionPoint)) {
            newVelocity.setDy(newVelocity.getDy() * (-1));
        }
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * notify the block that the ball hit him.
     */
    public void setIfHitMe() {
        ifHitMe = true;
    }

    /**
     * the method draw the block on the surface.
     *
     * @param surface the surface we want to draw the block on.
     */
    public void drawOn(DrawSurface surface) {
        // set the color and draw him.
        surface.setColor(color);
        surface.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        // sett color and draw is boundaries line.
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
    }

    @Override
    public void timePassed() {

    }

    /**
     * add the block to game.
     *
     * @param g the game we want to add the block.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * remove the ball block from the game.
     *
     * @param gameLevel the game we want to remove the ball.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
        gameLevel.removeCollidable(this);
    }

    /**
     * add the block to the listener to the list.
     *
     * @param hl the listener we want to add.
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * remove the listener from the list.
     *
     * @param hl the listener we want to remove.
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * the method notify to all the listener that hit is occur.
     *
     * @param hitter the ball that hit the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
