import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import biuoop.DrawSurface;

/**
 * AlienBlock is a special block that can shoot and is an alien image and is a
 * comparable object too.
 * @author gal
 */
public class AlienBlock extends Block implements Comparable<AlienBlock> {
    private static final int BULLET = 5;
    private String img;
    private double speed;
    private boolean moveLeft;
    private boolean moveRight;
    private Point originalUperLeft;

    /**
     * constructor.
     * @param leftUp
     *            the blocks up left edge point.
     * @param wid
     *            the block's width.
     * @param height
     *            the block's height.
     * @param imgString
     *            the block's image.
     * @param fast
     *            the block's speed.
     */
    public AlienBlock(Point leftUp, int wid, int height, String imgString,
            double fast) {
        super(leftUp, wid, height);
        this.img = imgString;
        this.speed = fast;
        this.moveRight = true;
        this.moveLeft = false;
        this.originalUperLeft = new Point(leftUp.getX(), leftUp.getY());
    }

    /**
     * @return the image path.
     */
    public String getString() {
        return this.img;
    }

    /**
     * @return block's speed.
     */
    public double getSpeed() {
        return this.speed;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int x = (int) this.getCollisionRectangle().getUpperLeft().getX();
        int y = (int) this.getCollisionRectangle().getUpperLeft().getY();

        Image alienImg = null;
        try {
            alienImg = ImageIO.read(new File(this.img));
        } catch (IOException e) {
            System.out.println("no image for block alien");
        }
        d.drawImage(x, y, alienImg);
    }

    /**
     * move block right.
     * @param dt
     *            the speed.
     */
    public void moveRight(double dt) {
        Point upLeft = this.getCollisionRectangle().getUpperLeft();
        Point p = new Point(upLeft.getX() + (this.speed * dt), upLeft.getY());
        if (p.getX() >= 750) {
            p = new Point(750, upLeft.getY());
        }
        this.getCollisionRectangle().setUpleft(p);
    }

    /**
     * move block left.
     * @param dt
     *            the speed.
     */
    public void moveLeft(double dt) {
        Point upLeft = this.getCollisionRectangle().getUpperLeft();
        Point p = new Point(upLeft.getX() - (this.speed * dt), upLeft.getY());
        if (p.getX() <= 0) {
            p = new Point(0, upLeft.getY());
        }
        this.getCollisionRectangle().setUpleft(p);
    }

    /**
     * move block down.
     * @param dt
     *            speed.
     */
    public void moveDown(double dt) {
        Point upLeft = this.getCollisionRectangle().getUpperLeft();
        Point p = new Point(upLeft.getX(), upLeft.getY() + (10));
        if (p.getY() >= 450) {
            p = new Point(upLeft.getX(), 450);
        }
        this.getCollisionRectangle().setUpleft(p);
        this.speed = this.speed * 1.1;
    }

    /**
     * move the block to his creating position.
     * @param fast
     *            the speed to move to start.
     */
    public void moveBackToStart(double fast) {
        this.getCollisionRectangle().setUpleft(this.originalUperLeft);
        this.speed = fast;
    }

    /**
     * sets block's speed.
     * @param x
     *            the new speed.
     */
    public void setSpeed(double x) {
        this.speed = x;
    }

    /**
     * @return if the block can move right.
     */
    public boolean getMoveRight() {
        return this.moveRight;
    }

    /**
     * @param x
     *            boolean.
     */
    public void setMoveRight(boolean x) {
        this.moveRight = x;
    }

    @Override
    public int compareTo(AlienBlock other) {
        int x = (int) other.getCollisionRectangle().getUpperLeft().getX();
        int x2 = (int) this.getCollisionRectangle().getUpperLeft().getX();

        return (x - x2);

    }

}
