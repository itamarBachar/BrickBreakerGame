//31871630

import game.levels.DirectHit;
import game.levels.FinalFour;
import game.levels.GameFlow;
import game.levels.Green3;
import game.levels.WideEasy;
import game.partipcans.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Itamar Bachar.
 * the class run the game.
 */
public class Ass6Game {
    /**
     * @param args the games the user want to play.
     */
    public static void main(String[] args) throws Exception {
        int counter = 0;
        GameFlow gameFlow = new GameFlow();
        List<LevelInformation> levels = new ArrayList<>();
        // check if we get argument from the user.
        if (args.length != 0) {
            List<Integer> list = new ArrayList<>();
            // run over the input, if the input valid enter him to the list.
            for (String s : args) {
                try {
                    int a = Integer.parseInt(s);
                    if (a > 0 && a <= 4) {
                        list.add(a);
                        counter++;
                    }
                } catch (Exception e) {
                    continue;
                }
            }
            // run the game
            if (counter > 0) {
                addLevel(list, levels);
                gameFlow.runLevels(levels);
            }
        }
        // run the standard game if we dont have arguments from the user.
        if (counter == 0) {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
            gameFlow.runLevels(levels);
        }

    }

    /**
     * the method add levels to the list.
     *
     * @param list   the list of the argument represent by integer.
     * @param levels the list of the level that we want to add to.
     */
    private static void addLevel(List<Integer> list, List<LevelInformation> levels) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == 1) {
                levels.add(new DirectHit());
            }
            if (list.get(i) == 2) {
                levels.add(new WideEasy());
            }
            if (list.get(i) == 3) {
                levels.add(new Green3());
            }
            if (list.get(i) == 4) {
                levels.add(new FinalFour());
            }
        }
    }
}
