package com.adaptionsoft.games.trivia.runner;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameRunnerTest {

    private final Path masterFilePath = Paths.get("game_master.txt");
    private final Path testFilePath = Paths.get("game_test.txt");

    @Before
    public void setUp() {
        try {
            Files.delete(testFilePath);
            System.out.println(String.format("Test file: '%s' deleted.", testFilePath));
        } catch (IOException e) {
            // Do nothing
        }
    }

    @After
    public void tearDown() {
    }

    @Test
    public void run() throws IOException {
        System.setOut(new PrintStream("game_test.txt"));
        for (int i = 0; i < 1000; i++) {
            new GameRunner(GameRunner.nextSeed(i)).run();
        }

        final List<String> masterOutputs = Files.readAllLines(masterFilePath, StandardCharsets.UTF_8);
        final List<String> testOutputs = Files.readAllLines(testFilePath, StandardCharsets.UTF_8);

        assertEquals(masterOutputs.size(), testOutputs.size());

        for (int i = 0; i < testOutputs.size(); i++) {
            assertEquals(masterOutputs.get(i), testOutputs.get(i));
        }
    }
}