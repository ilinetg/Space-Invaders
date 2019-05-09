import java.util.ArrayList;
import java.util.List;

/**
 * SpaceInv class that implements LevelInformation.
 * @author gal
 */
public class SpaceInv implements LevelInformation {
    // members
    private String name;
    private List<Velocity> ballsVelocities;
    private Background back;
    private int paddleWid;
    private int paddleSpeed;
    private int numOfBlocks;
    private AlienBlock block;

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
     * @param block
     *            is all the kinds of blocks.
     */
    public SpaceInv(String name, Background background,
            int[] paddleAndBlockRemove, AlienBlock block) {
        this.name = name;
        this.back = background;
        this.paddleSpeed = paddleAndBlockRemove[0];
        this.paddleWid = paddleAndBlockRemove[1];
        this.ballsVelocities = null;
        this.numOfBlocks = paddleAndBlockRemove[2];
        this.block = block;
    }

    /**
     * @return The number of balls in the level
     */
    @Override
    public int numberOfBalls() {
        return 0;
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
        List<Block> blocksOfAlien = new ArrayList<Block>();
        double speed = this.block.getSpeed();
        int x = 0;
        int y = 50;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                AlienBlock b = new AlienBlock(new Point(x, y), 40, 30,
                        this.block.getString(), speed);
                x += 60;
                blocksOfAlien.add(b);
            }
            x = 0;
            y += 40;
        }
        return blocksOfAlien;
    }

    /**
     * @return The number of blocks to be removed before the level is cleared
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }

}
