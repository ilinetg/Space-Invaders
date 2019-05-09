import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import biuoop.DrawSurface;

/**
 * AliensCollectionRow class holds all the aliens in the level.
 * @author gal
 */
public class AliensCollectionRow implements Sprite {
    private static final int BULLET = 5;
    private static final double SECONDSBETWEENSHOTS = 0.5;
    private Map<Integer, List<AlienBlock>> enemies;
    private boolean moveRight;
    private double speed;
    private SpriteCollection sprites;
    private GameLevel game;
    private GameEnvironment gameEn;
    private long lastFinishTime;
    private long lastStartTime;
    private BallsList ballsList;

    /**
     * constructor.
     * @param aliens
     *            a list of AlienBlock.
     * @param col
     *            the spriteCollection of the game.
     * @param enviroment
     *            the GameEnvironment.
     * @param list
     *            BallsList;
     * @param g
     *            the GameLevel object.
     */
    public AliensCollectionRow(List<AlienBlock> aliens, SpriteCollection col,
            GameEnvironment enviroment, BallsList list, GameLevel g) {
        this.enemies = new TreeMap<Integer, List<AlienBlock>>();
        this.game = g;
        Collections.sort(aliens);
        for (int i = 0; i < 10; i++) {
            List<AlienBlock> l1 = new ArrayList<AlienBlock>();
            for (int j = 0; j < 5; j++) {
                l1.add(aliens.get(j + (5 * i)));
            }
            this.enemies.put(i, l1);
        }
        this.moveRight = true;
        this.speed = aliens.get(0).getSpeed();
        this.sprites = col;
        this.gameEn = enviroment;
        this.lastStartTime = System.currentTimeMillis();
        this.lastFinishTime = this.lastStartTime;
        this.ballsList = list;
    }

    @Override
    public void drawOn(DrawSurface d) {
        int n = this.enemies.size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < this.enemies.get(i).size(); j++) {
                this.enemies.get(i).get(j).drawOn(d);
            }

        }
    }

    @Override
    public void timePassed(double dt) {
        this.moveOneStep(dt);
        this.lastStartTime = System.currentTimeMillis();

        if ((this.lastStartTime - this.lastFinishTime) >= (SECONDSBETWEENSHOTS
                * 1000)) {
            this.alienShoot(dt);
            this.lastFinishTime = System.currentTimeMillis();
        }
    }

    /**
     * add the collection to the game.
     * @param gameLevel
     *            the GameLevel object.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * moves all aliens.
     * @param dt
     *            the speed.
     */
    public void moveOneStep(double dt) {
        Point upLeft = null;
        for (int i = 0; i < this.enemies.size(); i++) {
            if (!this.enemies.get(i).isEmpty()) {
                upLeft = this.enemies.get(i).get(0).getCollisionRectangle()
                        .getUpperLeft();
                break;
            }
            continue;
        }

        // if we didnt cross the border of the screen
        // if ((upRight.getX() != 750) && (this.moveRight)) {
        if (this.moveRight) {
            if (upLeft.getX() + (this.speed * dt) < 750) {
                for (int i = 0; i < this.enemies.size(); i++) {
                    for (AlienBlock b : this.enemies.get(i)) {
                        b.moveRight(dt);
                    }
                }
            } else {
                this.moveRight = false;
                for (int i = 0; i < this.enemies.size(); i++) {
                    for (AlienBlock b : this.enemies.get(i)) {
                        b.moveDown(dt);
                    }
                }
            }
        } else {
            this.moveRight = false;
            for (int i = this.enemies.size() - 1; i > 0; i--) {
                if (!this.enemies.get(i).isEmpty()) {
                    upLeft = this.enemies.get(i).get(0).getCollisionRectangle()
                            .getUpperLeft();
                    break;
                }
                continue;
            }

            // go left
            // if we didnt cross the border of the screen
            if (upLeft.getX() - (this.speed * dt) > 0) {
                for (int i = 0; i < this.enemies.size(); i++) {
                    for (AlienBlock b : this.enemies.get(i)) {
                        b.moveLeft(dt);
                    }
                }

            } else {
                this.moveRight = true;
                for (int i = 0; i < this.enemies.size(); i++) {
                    for (AlienBlock b : this.enemies.get(i)) {
                        b.moveDown(dt);
                    }
                }
            }
        }

    }

    /**
     * choose which alien should shoot.
     * @param dt
     *            - the speed.
     */
    public void alienShoot(double dt) {
        int key;
        while (true) {
            Random rn = new Random();
            key = rn.nextInt(9) + 1;
            if (!this.enemies.get(key).isEmpty()) {
                break;
            }
        }
        double y = this.enemies.get(key).get(0).getCollisionRectangle()
                .getUpperLeft().getY();
        int index = 0;
        for (int i = 1; i < this.enemies.get(key).size(); i++) {
            double tmp = this.enemies.get(key).get(i).getCollisionRectangle()
                    .getUpperLeft().getY();
            if (tmp > y) {
                y = tmp;
                index = i;
            }
        }
        this.shoot(key, index);
    }

    /**
     * Incharge of the shooting behavior of the aliens (shoots a Red ball).
     * @param key
     *            - the key in the map.
     * @param index
     *            - of the alien in the value of the map.
     */
    public void shoot(int key, int index) {
        double width = this.enemies.get(key).get(index).getCollisionRectangle()
                .getWidth();
        Point lowerLeft = this.enemies.get(key).get(index)
                .getCollisionRectangle().getLowerLeft();
        int x = (int) (lowerLeft.getX() + (width / 2));
        int y = (int) lowerLeft.getY() + 2;
        Ball ball = new Ball(x, y, BULLET, java.awt.Color.RED, this.gameEn);
        ball.setVelocity(0.1, 400);
        this.sprites.addSprite(ball);
        this.ballsList.addBall(ball);
    }

    /**
     * @param other
     *            alien we want to remove.
     */
    public void removefromRow(Block other) {
        for (int i = 0; i < this.enemies.size(); i++) {
            if (this.enemies.get(i).contains(other)) {
                this.enemies.get(i).remove(other);
            }
        }
    }

    /**
     * @return the y cordinate of the lowes alien in the collection.
     */
    public double getLowestAlienY() {
        int indexKey = 0;
        int indexRealPlace = 0;
        int indexPlace = 0;
        double secondY = 0;
        for (int j = 0; j < this.enemies.size(); j++) {
            double y = 0;

            for (int i = 0; i < this.enemies.get(j).size(); i++) {
                indexPlace = 0;
                double tmp = this.enemies.get(j).get(i).getCollisionRectangle()
                        .getUpperLeft().getY();
                if (tmp > y) {
                    y = tmp;
                    indexPlace = i;
                }
            }
            if (y > secondY) {
                secondY = y;
                indexKey = j;
                indexRealPlace = indexPlace;
            }
        }
        double check = this.enemies.get(indexKey).get(indexRealPlace)
                .getCollisionRectangle().getUpperLeft().getY();
        // for debug use
        if (check > 400) {
            int c = 4;
        }
        return this.enemies.get(indexKey).get(indexRealPlace)
                .getCollisionRectangle().getUpperLeft().getY();
    }

    /**
     * return all the aliens to there creating position.
     */
    public void setBackToBegining() {
        for (int i = 0; i < this.enemies.size(); i++) {
            for (int j = 0; j < this.enemies.get(i).size(); j++) {
                this.enemies.get(i).get(j).moveBackToStart(this.speed);
            }
        }
    }

    /**
     * removes all the alien's shots from the game.
     */
    public void removeBalls() {
        this.ballsList.removeAllBalls(this.game);
    }
}
