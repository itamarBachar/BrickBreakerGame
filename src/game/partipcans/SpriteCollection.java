//318781630
package game.partipcans;


import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * @author Itamar Bachar.
 */
public class SpriteCollection {
    private ArrayList<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * the method add sprite to the list of sprite.
     *
     * @param s the sprite we add.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * the method remove sprite to the list of sprite.
     *
     * @param s the sprite we remove.
     */
    public void removeSprite(Sprite s) {
        if (sprites.contains(s)) {
            sprites.remove(s);
        }
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        ArrayList<Sprite> duplicateSprites = new ArrayList<Sprite>(this.sprites);
        for (Sprite s : duplicateSprites) {
            s.timePassed();
        }
    }


    /**
     * call drawOn(d) on all sprites.
     *
     * @param d the surface we draw on the sprite.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : sprites) {
            s.drawOn(d);
        }
    }
}
