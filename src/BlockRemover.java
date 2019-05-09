import java.awt.Color;

/**
 * class BlockRemover that implements HitListener. a BlockRemover is in charge
 * of removing blocks from the game, as well as keeping count of the number of
 * blocks that remain.
 * @author gal
 */
public class BlockRemover implements HitListener {
    // members
    private GameLevel gameLevel;
    private Counter remainingBlocks;
    private AliensCollectionRow enemies;

    /**
     * constructor.
     * @param gameLevel
     *            the game
     * @param removedBlocks
     *            the count of blocks in the game
     * @param aliens
     *            AliensCollectionRow of the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks,
            AliensCollectionRow aliens) {
        this.remainingBlocks = removedBlocks;
        this.gameLevel = gameLevel;
        this.enemies = aliens;
    }

    // Blocks that are hit and reach 0 hit-points should be removed
    // from the game. Remember to remove this listener from the block
    // that is being removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        String colorS = Integer.toString(hitter.getColor().getRGB());
        String colorS2 = Integer.toString(Color.WHITE.getRGB());
        if ((beingHit.getHitNum() < 1) && (colorS.equals(colorS2))) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.gameLevel);
            this.enemies.removefromRow(beingHit);
            this.remainingBlocks.decrease(1);
        }
    }
}