package com.timdrivendev.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day02 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        List<Game> games = parseGames(reader);
        Map<String, Integer> bag = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
        );
        int gameSum = 0;
        for (Game game : games) {
            List<Map<String, Integer>> gameSets = game.getGameSets();
            boolean possible = true;
            for (Map<String, Integer> gameSet : gameSets) {
                for (String cubeColour : gameSet.keySet()) {
                    if (!bag.containsKey(cubeColour) || (bag.containsKey(cubeColour) && gameSet.get(cubeColour) > bag.get(cubeColour))) {
                        possible = false;
                    }
                }
            }
            if (possible) {
                gameSum += game.getGameNumber();
            }
        }
        return gameSum;
    }

    private List<Game> parseGames(BufferedReader reader) throws IOException {
        List<Game> games = new ArrayList<>();
        for (String line; (line = reader.readLine()) != null;) {
            int gameNumber = Integer.parseInt(line.split(":")[0].trim().split(" ")[1]);
            String[] rawSets = line.split(":")[1].split(";");
            List<Map<String, Integer>> gameSets = new ArrayList<>();
            for (String set : rawSets) {
                String[] cubes = set.split(",");
                Map<String, Integer> gameSet = new HashMap<>();
                for (String cube : cubes) {
                    gameSet.put(cube.trim().split(" ")[1], Integer.valueOf(cube.trim().split(" ")[0]));
                }
                gameSets.add(gameSet);
            }
            games.add(new Game(
                    gameNumber,
                    gameSets
            ));
        }
        return games;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay02");
        List<Game> games = parseGames(reader);
        int sum = 0;
        for (Game game : games) {
            Map<String, Integer> min = new HashMap<>();
            for (Map<String, Integer> gameSet : game.getGameSets()) {
                for (String s : gameSet.keySet()) {
                    if (!min.containsKey(s) || (min.containsKey(s) && gameSet.get(s) > min.get(s))) {
                        min.put(s, gameSet.get(s));
                    }
                }
            }
            int power = 1;
            for (Map.Entry<String, Integer> entry : min.entrySet()) {
                power *= entry.getValue();
            }
            sum += power;
        }

        return sum;
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day02 solution = new Day02();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }

    private class Game {
        private final int gameNumber;
        private final List<Map<String, Integer>> gameSets;

        public Game(int gameNumber, List<Map<String, Integer>> gameSets) {
            this.gameNumber = gameNumber;
            this.gameSets = gameSets;
        }

        public List<Map<String, Integer>> getGameSets() {
            return gameSets;
        }

        public int getGameNumber() {
            return gameNumber;
        }
    }
}
