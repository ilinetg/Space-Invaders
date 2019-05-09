import java.util.List;

/**
 * Level class that implements LevelInformation.
 * @author gal
 */
public class Level implements LevelInformation {
    // members
    private String name;
    private List<Velocity> ballsVelocities;
    private Background back;
    private int paddleWid;
    private int paddleSpeed;
    private int numOfBlocks;
    private List<String> blockSymbol;
    private List<Double> blocksPosition;

    /**
     * constructor.
     * @param name
     *            level's name.
     * @param background
     *            the background.
     * @param paddleAndBlockRemove
     *            the paddles width is in paddleAndBlockRemove[1] and the
     *            paddle's speed is in paddleAndBlockRemove[0] and num of blocks
     *            to remove is in paddleAndBlockRemove[2]
     * @param velocities
     *            the balls's velocities.
     * @param rowLayout
     *            the layout of the blocks.
     * @param blocksPosition
     *            the first block upper left points, and the row height.
     */
    public Level(String name, Background background, int[] paddleAndBlockRemove,
            List<Velocity> velocities, List<String> rowLayout,
            List<Double> blocksPosition) {
        this.name = name;
        this.back = background;
        this.paddleSpeed = paddleAndBlockRemove[0];
        this.paddleWid = paddleAndBlockRemove[1];
        this.ballsVelocities = velocities;
        this.numOfBlocks = paddleAndBlockRemove[2];
        this.blockSymbol = rowLayout;
        this.blocksPosition = blocksPosition;
    }

    /**
     * @return The number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return this.ballsVelocities.size();
    }

    /**
     * gets the initial velocity of each ball. The size of the list will be
     * equal to the number of balls
     * @return A list of velocities for the balls
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        return this.ballsVelocities;
    }

    /**
     * @return The paddles speed
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return The paddle width
     */
    @Override
    public int paddleWidth() {
        return this.paddleWid;
    }

    /**
     * @return the levels name
     */
    @Override
    public String levelName() {
        return this.name;
    }

    /**
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        return this.back;
    }

    /**
     * A method to get the blocks that should appear at the level, each block
     * contains its size, color and location.
     * @return a list of all the blocks in the level
     */
    @Override
    public List<Block> blocks() {
        return null;
    }

    /**
     * @return The number of blocks to be removed before the level is cleared
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

}
