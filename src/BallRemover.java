/**
 * class BallRemover that implements HitListener.
 * @author gal
 */
public class BallRemover implements HitListener {
    // members
    private GameLevel gameLevel;
    private Counter remainingBalls;
    private BallsList list;

    /**
     * constructor.
     * @param gameLevel
     *            the level in the game.
     * @param removedBalls
     *            counter of how many balls to remove.
     * @param b
     *            BallsList is the list of balls in the game.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls, BallsList b) {
        this.remainingBalls = removedBalls;
        this.gameLevel = gameLevel;
        this.list = b;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitNum() < 1) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
        }
    }

    /**
     * @return BallsList that the listener has.
     */
    public Counter getBallsCount() {
        return this.remainingBalls;
    }

    /**
     * @param b
     *            Ball to add to BallsList.
     */
    public void addBall(Ball b) {
        this.list.addBall(b);
    }

    /**
     * clears the BallsList.
     */
    public void removeBalls() {
        this.list.removeAllBalls(this.gameLevel);
        this.remainingBalls.decrease(this.remainingBalls.getValue());
    }
}
