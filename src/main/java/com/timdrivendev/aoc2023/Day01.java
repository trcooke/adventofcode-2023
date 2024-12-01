package com.timdrivendev.aoc2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Day01 {

    int part1() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        int sum = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String digits = "";
            for (int i = 0; i < line.length(); i++) {
                if (Character.isDigit(line.charAt(i))) {
                    digits += line.charAt(i);
                    break;
                }
            }
            for (int i = line.length() - 1; i >= 0; i--) {
                if (Character.isDigit(line.charAt(i))) {
                    digits += line.charAt(i);
                    break;
                }
            }
            sum += Integer.parseInt(digits);
        }
        return sum;
    }

    int part2() throws IOException {
        BufferedReader reader = getInput("InputDay01");
        int sum = 0;
        for (String line; (line = reader.readLine()) != null;) {
            String digits = "";
            digits += firstDigit(line);
            digits += lastDigit(line);
            System.out.println(digits);
            sum += Integer.parseInt(digits);
        }
        return sum;
    }

    private String firstDigit(String line) {
        if (Character.isDigit(line.charAt(0))) {
            return String.valueOf(line.charAt(0));
        }
        if (line.startsWith("one")) {
            return "1";
        }
        if (line.startsWith("two")) {
            return "2";
        }
        if (line.startsWith("three")) {
            return "3";
        }
        if (line.startsWith("four")) {
            return "4";
        }
        if (line.startsWith("five")) {
            return "5";
        }
        if (line.startsWith("six")) {
            return "6";
        }
        if (line.startsWith("seven")) {
            return "7";
        }
        if (line.startsWith("eight")) {
            return "8";
        }
        if (line.startsWith("nine")) {
            return "9";
        }
        return firstDigit(line.substring(1));
    }

    private String lastDigit(String line) {
        if (Character.isDigit(line.charAt(line.length() - 1))) {
            return String.valueOf(line.charAt(line.length() - 1));
        }
        if (line.endsWith("one")) {
            return "1";
        }
        if (line.endsWith("two")) {
            return "2";
        }
        if (line.endsWith("three")) {
            return "3";
        }
        if (line.endsWith("four")) {
            return "4";
        }
        if (line.endsWith("five")) {
            return "5";
        }
        if (line.endsWith("six")) {
            return "6";
        }
        if (line.endsWith("seven")) {
            return "7";
        }
        if (line.endsWith("eight")) {
            return "8";
        }
        if (line.endsWith("nine")) {
            return "9";
        }
        return lastDigit(line.substring(0, line.length() - 1));
    }

    private BufferedReader getInput(String inputFile) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream is = classLoader.getResourceAsStream(inputFile);
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        return new BufferedReader(isr);
    }

    public static void main(String[] args) throws IOException {
        Day01 solution = new Day01();
        System.out.println(solution.part1());
        System.out.println(solution.part2());
    }
}
