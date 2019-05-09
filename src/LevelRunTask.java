import java.util.List;

/**
 * LevelRunTask class that implements Task<Void>.
 * @author gal
 */
public class LevelRunTask implements Task<Void> {
    // members
    private AnimationRunner runner;
    private GameFlow gameFlow;
    private List<LevelInformation> levels;
    private AlienBlock enemy;

    /**
     * constructor.
     * @param levelsToRun
     *            list of levels.
     * @param runner
     *            the animation to run
     * @param game
     *            the gameFlow object.
     * @param alien
     *            is an AlienBlock.
     */
    public LevelRunTask(List<LevelInformation> levelsToRun,
            AnimationRunner runner, GameFlow game, AlienBlock alien) {
        this.runner = runner;
        this.gameFlow = game;
        this.levels = levelsToRun;
        this.enemy = alien;
    }

    @Override
    public Void run() {
        List<LevelInformation> levelList = this.levels;
        this.gameFlow.runLevels(levelList);
        return null;
    }
}
