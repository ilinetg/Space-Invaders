import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * ShootingPaddle class extends Paddle and implements HitNotifier.
 * @author gal
 */
public class ShootingPaddle extends Paddle implements HitNotifier {
    // members
    private static final int BULLET = 3;
    private static final double SECONDSBETWEENSHOTS = 0.35;
    private SpriteCollection sprites;
    private GameEnvironment gameEn;
    private long lastFinishTime;
    private long lastStartTime;
    private BallRemover ballRemove;
    private List<HitListener> paddleShotListeners;

    /**
     * constructor.
     * @param block
     *            paddle's block.
     * @param gui
     *            our gui.
     * @param fast
     *            paddle's speed.
     * @param sprite
     *            all the sprites at the game.
     * @param enviroment
     *            all colliadables in the game.
     * @param removerOfBalls
     *            is the listener for the balls that the paddle shoots.
     */
    public ShootingPaddle(Block block, GUI gui, int fast,
            SpriteCollection sprite, GameEnvironment enviroment,
            BallRemover removerOfBalls) {
        super(block, gui, fast);
        this.sprites = sprite;
        this.gameEn = enviroment;
        this.lastStartTime = System.currentTimeMillis();
        this.lastFinishTime = this.lastStartTime;
        this.ballRemove = removerOfBalls;
        this.paddleShotListeners = new ArrayList<HitListener>();
    }

    @Override
    /**
     * if the keys to move where pressed the method calls the right move method.
     * or if the key to shoot is pressed.
     */
    public void timePassed(double dt) {
        super.timePassed(dt);
        this.lastStartTime = System.currentTimeMillis();

        if ((this.lastStartTime - this.lastFinishTime) >= (SECONDSBETWEENSHOTS
                * 1000)) {
            if (this.getKeyboard().isPressed(KeyboardSensor.SPACE_KEY)) {
                this.shoot();
            }

            this.lastFinishTime = System.currentTimeMillis();
        }

    }

    @Override
    public void addHitListener(HitListener listen) {
        this.paddleShotListeners.add(listen);
    }

    @Override
    public void removeHitListener(HitListener listen) {
        this.paddleShotListeners.remove(listen);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint,
            Velocity currentVelocity) {

        for (HitListener entity : this.paddleShotListeners) {
            entity.hitEvent(this.getBlock(), hitter);
        }
        return this.getBlock().hit(hitter, collisionPoint, currentVelocity);
    }

    /**
     * this is incharge of the shooting behavior of the paddle (shoots a white
     * ball).
     */
    private void shoot() {
        double paddleWidth = super.getCollisionRectangle().getWidth();
        Point paddleUpperLeft = super.getCollisionRectangle().getUpperLeft();
        int x = (int) (paddleUpperLeft.getX() + (paddleWidth / 2));
        int y = (int) paddleUpperLeft.getY() - 5;
        Ball ball = new Ball(x, y, BULLET, java.awt.Color.WHITE, this.gameEn);
        ball.setVelocity(10, -500);
        this.ballRemove.getBallsCount().increase(1);
        this.sprites.addSprite(ball);
        this.ballRemove.addBall(ball);
    }

}
