import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;

/**
 * The SpaceInvaders main of the game.
 * @author gal
 */
public class Ass7Game {
    /**
     * @param args
     *            no input.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui, 20);
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                gui.getKeyboardSensor());
        menu.addSelection("h", "High scores",
                new ShowHiScoresTask(runner,
                        new HighScoresAnimation(HighScoresTable
                                .loadFromFile(new File("highScores.ser")), "h"),
                        gui.getKeyboardSensor()));
        List<LevelInformation> levelsToRun = new ArrayList<LevelInformation>();
        int[] nums = new int[3];
        nums[0] = 600;
        nums[1] = 60;
        nums[2] = 5;
        AlienBlock alien = new AlienBlock(new Point(0, 80), 40, 30,
                "resources/enemy.png", 100);
        SpaceInv spaceLevel = new SpaceInv("battle no 0 ",
                new Background(Color.black), nums, alien);
        levelsToRun.add(spaceLevel);
        menu.addSelection("s", "Start Game", new LevelRunTask(levelsToRun,
                runner, new GameFlow(runner, gui, 3), alien));
        menu.addSelection("q", "Quit", new Task<Void>() {
            @Override
            public Void run() {
                System.exit(0);
                return null;
            }
        });

        while (true) {
            runner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }
}
