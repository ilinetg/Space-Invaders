import java.util.ArrayList;
import java.util.List;

/**
 * BallsList class. list of balls.
 * @author gal
 */
public class BallsList {
    private List<Ball> balls = new ArrayList<Ball>();

    /**
     * @param b
     *            ball to add.
     */
    public void addBall(Ball b) {
        this.balls.add(b);
    }

    /**
     * clear the list.
     * @param g
     *            the game level.
     */
    public void removeAllBalls(GameLevel g) {
        for (int i = 0; i < this.balls.size(); i++) {
            this.balls.get(i).removeFromGame(g);
        }
        this.balls.removeAll(this.balls);
    }

}
