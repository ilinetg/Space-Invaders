/**
 * The ShieldListener of the shield in the level.
 * @author gal
 */
public class ShieldListener implements HitListener {
    // members
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private AliensCollectionRow enemies;
    private boolean shouldStop;

    /**
     * constructor.
     * @param gameLevel
     *            the game
     * @param removedBlocks
     *            the count of blocks in the game
     * @param aliens
     *            is all the aliens in the level.
     * @param stop
     *            a boolean to indicate if the game should stop.
     */
    public ShieldListener(GameLevel gameLevel, Counter removedBlocks,
            AliensCollectionRow aliens, boolean stop) {
        this.remainingBlocks = removedBlocks;
        this.gameLevel = gameLevel;
        this.enemies = aliens;
        this.shouldStop = !stop;

    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if ((beingHit.getHitNum() <= 1)) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
        if (this.enemies.getLowestAlienY() >= 400) {
            this.shouldStop = false;
        }
    }

    /**
     * @return if level should stop or not
     */
    public boolean stop() {
        return this.shouldStop;
    }

    /**
     * set the stop boolean.
     * @param x
     *            initialize the stop.
     */
    public void setStop(boolean x) {
        this.shouldStop = x;
    }

    /**
     * @return the counter of shield's clocks
     */
    public Counter shieldCounter() {
        return this.remainingBlocks;
    }
}
