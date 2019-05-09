import java.awt.Color;

/**
 * PaddleIsHitListener class is the listener of the paddle.
 * @author gal
 */
public class PaddleIsHitListener implements HitListener {

    // members
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param gameLevel
     *            the game
     * @param removedBlocks
     *            the count of blocks in the game
     */
    public PaddleIsHitListener(GameLevel gameLevel, Counter removedBlocks) {
        this.remainingBlocks = removedBlocks;
        this.gameLevel = gameLevel;
    }

    // Blocks that are hit with red color balls should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        String colorS = Integer.toString(hitter.getColor().getRGB());
        String colorS2 = Integer.toString(Color.RED.getRGB());
        if ((beingHit.getHitNum() <= 1) && (colorS.equals(colorS2))) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
        }
    }

}
