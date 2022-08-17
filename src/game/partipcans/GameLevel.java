//318781630
package game.partipcans;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import game.liseners.BallRemover;
import game.liseners.BlockRemover;
import game.liseners.PrintingHitListener;
import game.liseners.ScoreIndicator;
import game.liseners.ScoreTrackingListener;
import game.partipcans.pause.CountdownAnimation;
import game.partipcans.pause.KeyPressStoppableAnimation;
import game.partipcans.pause.PauseScreen;
import shapes.Point;
import shapes.Rectangle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Bachar.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    public static final int RADIUS = 7;
    private GUI gui;
    private Sleeper sleeper;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter counterOfBlocks;
    private Counter counterOfBalls;
    private Counter score;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor for game.
     *
     * @param levelInformation the level we run.
     */
    public GameLevel(LevelInformation levelInformation) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.counterOfBlocks = new Counter();
        this.counterOfBalls = new Counter();
        this.score = new Counter();
    }

    /**
     * constructor.
     *
     * @param levelInformation the level we run.
     * @param gui              the gui.
     * @param sleeper          the sleeper.
     * @param keyboard         the keyboard.
     * @param runner           the runner.
     * @param score            the score of the user.
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, Sleeper sleeper, KeyboardSensor keyboard,
                     AnimationRunner runner, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.levelInformation = levelInformation;
        this.counterOfBlocks = new Counter();
        this.counterOfBalls = new Counter();
        this.score = new Counter();
        this.sleeper = sleeper;
        this.gui = gui;
        this.keyboard = keyboard;
        this.runner = runner;
        this.score = score;
    }

    /**
     * the method add collidable for the environment field.
     *
     * @param c the collidable we want to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * the method remove collidable for the environment field.
     *
     * @param c the collidable we want to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * the method add game.partipcans.Sprite for the sprites field.
     *
     * @param s the spirte we want to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * the method remove game.partipcans.Sprite for the sprites field.
     *
     * @param s the spirte we want to remove.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and game.partipcans.Ball (and game.partipcans.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        createFrames();
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        BallRemover listenerToBall = new BallRemover(this, this.counterOfBalls);
        createUpFrame(listenerToBall);
        PrintingHitListener printingHitListener = new PrintingHitListener();
        BlockRemover listenerToBlock = new BlockRemover(this, this.counterOfBlocks);
        createBlocks(printingHitListener, listenerToBlock, scoreTrackingListener);
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            createBall(i);
            this.counterOfBalls.increase(1);
        }
        createPaddle();
        ScoreIndicator indicator = new ScoreIndicator(this.score);
        indicator.addToGame(this);
    }

    /**
     * the method create new paddle for the game.
     */
    private void createPaddle() {
        Paddle paddle = new Paddle(new Rectangle(new Point(levelInformation.getPaddleXStart(),
                levelInformation.getPaddleYStart()), levelInformation.paddleWidth(), Paddle.PADDLE_HIGH),
                Color.YELLOW, gui, levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * the method create blocks for the game.
     *
     * @param printingHitListener   listener we want to add to the block.
     * @param listenerToBlock       listener we want to add to the block.
     * @param scoreTrackingListener listener we want to add to the block.
     */
    private void createBlocks(PrintingHitListener printingHitListener, BlockRemover listenerToBlock,
                              ScoreTrackingListener scoreTrackingListener) {
        // all the values for create blocks in the game.
        List<Block> block = new ArrayList<Block>();
        block.addAll(levelInformation.blocks());
        for (int i = 0; i < levelInformation.blocks().size(); i++) {
            block.get(i).addHitListener(printingHitListener);
            block.get(i).addHitListener(listenerToBlock);
            block.get(i).addHitListener(scoreTrackingListener);
            //add every block to the game.
            block.get(i).addToGame(this);
        }
        this.counterOfBlocks.increase(levelInformation.numberOfBlocksToRemove());
    }

    /**
     * the method create balls to game.
     *
     * @param i the number of the ball we create.
     */
    private void createBall(int i) {
        Ball ball = new Ball(levelInformation.getPaddleXStart() + levelInformation.paddleWidth() / 2,
                levelInformation.getPaddleYStart() - 30,
                RADIUS, Color.white);
        ball.setVelocity(levelInformation.initialBallVelocities().get(i));
        ball.setGameEnvironment(this.environment);
        ball.addToGame(this);
    }

    /**
     * the method create frames.
     */
    private void createFrames() {
        Rectangle frameRectangleDown = new Rectangle(new Point(0, 0), GameEnvironment.FRAME_WIDTH, 40);
        Rectangle frameRectangleRight = new Rectangle(new Point(0, 0), 30, GameEnvironment.FRAME_HIGH);
        Rectangle frameRectangleLeft = new Rectangle(new Point(GameEnvironment.FRAME_WIDTH - 30, 0),
                30, GameEnvironment.FRAME_HIGH);
        Block frameBlockRight = new Block(frameRectangleRight, Color.GRAY);
        Block frameBlockDown = new Block(frameRectangleDown, Color.GRAY);
        Block fameBlockLeft = new Block(frameRectangleLeft, Color.GRAY);
        frameBlockRight.addToGame(this);
        frameBlockDown.addToGame(this);
        fameBlockLeft.addToGame(this);
    }

    /**
     * the method create upper frame to the game.
     *
     * @param listenerToBall the listener we want to add to the frame.
     */
    private void createUpFrame(BallRemover listenerToBall) {
        // create a rectangle.
        Rectangle frameRectangleUp =
                new Rectangle(new Point(0, GameEnvironment.FRAME_HIGH), 800, 30);
        // create a block from the rectangle.
        Block frameBlockUp = new Block(frameRectangleUp, Color.blue);
        // add th listener to the block and the block to the game.
        frameBlockUp.addHitListener(listenerToBall);
        frameBlockUp.addToGame(this);
    }

    /**
     * the method run the game.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(2, 3, this.sprites), levelInformation.background());
        this.running = true;
        this.runner.run(this, levelInformation.background());
    }

    /**
     * @return the amount of the ball that remain to the user.
     */
    public int getAmountOfBallsRemain() {
        return this.counterOfBalls.getValue();
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space",
                    new PauseScreen(this.keyboard)), Color.GRAY);
        }
        if (this.counterOfBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.counterOfBalls.getValue() == 0) {
            this.running = false;
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        d.drawText(100, 20, "Lives: 7", 20);
        d.drawText(500, 20, "Level Name: " + levelInformation.levelName(), 20);
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


}

