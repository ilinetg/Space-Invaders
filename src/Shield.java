
import java.awt.Color;
import java.util.ArrayList;

/**
 * The Shield class which is the games shield.
 * @author gal
 */
public class Shield {
    private final int size = 5;
    private Color color;
    private GameLevel game;
    private ShieldListener shieldListener;
    private BallRemover remover;

    /**
     * constructor.
     * @param color
     *            The color of the shield.
     * @param g
     *            is the gamelevel object.
     * @param listener
     *            is a ShieldListener.
     * @param remove
     *            is a BallRemover.
     */
    public Shield(Color color, GameLevel g, ShieldListener listener,
            BallRemover remove) {
        this.color = color;
        this.game = g;
        this.shieldListener = listener;
        this.remover = remove;
    }

    /**
     * this method creates a shield.
     * @param xLoc
     *            The x cordinate of the upper left corner of the shield
     * @param yLoc
     *            The y cordinate of the upper left corner of the shield
     * @param width
     *            The width of the shield
     * @param height
     *            The height of the shield
     * @return a list of all the blocks that creates the shield
     */
    public ArrayList<Block> createShield(int xLoc, int yLoc, int width,
            int height) {
        Point uperLeft = null;
        ArrayList<Block> items = new ArrayList<Block>();

        for (int i = 0; i < width; i = i + size) {
            for (int j = 0; j < height; j = j + size) {
                uperLeft = new Point(xLoc + i, yLoc + j);
                Block b = new Block(uperLeft, size, size, this.color);
                b.addToGame(this.game);
                b.addHitListener(this.shieldListener);
                b.addHitListener(this.remover);
                this.shieldListener.shieldCounter().increase(1);
                this.remover.getBallsCount().increase(1);
                items.add(b);
            }
        }
        return items;
    }
}
