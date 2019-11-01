package com.adaptionsoft.games.trivia.runner;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

import com.adaptionsoft.games.uglytrivia.Game;

public class GameRunner {

    private static boolean notAWinner;

    private final long seed;

    private static int nextSeed(int index) {
        return 13 * 19 + index;
    }

    public static void main(String[] args) throws IOException {
        System.setOut(new PrintStream("./game_master.txt"));

        for (int i = 0; i < 1000; i++) {
            System.out.println("--" + i);
            new GameRunner(nextSeed(i)).run();
        }
    }

    GameRunner(long seed) {
        this.seed = seed;
    }

    void run() {
        Game aGame = new Game();
        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");

        Random rand = new Random(seed);

        do {
            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }
        } while (notAWinner);

    }
}
