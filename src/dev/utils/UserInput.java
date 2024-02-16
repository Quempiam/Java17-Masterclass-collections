package dev.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String[] getPlayerNames() throws IOException {
        int i = 1;
        String input = "";
        List<String> names = new ArrayList<>(3);
        System.out.println("""
                =================================================
                ||                    WELCOME                  ||
                ||                      IN                     ||
                ||                BLACKJACK BY Q               ||
                =================================================
                
                """);
        do {
            System.out.printf("Write name of Player %d/3 or write 'start' to start the game\n", i);

            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Name can't be empty");
                continue;
            }
            if (input.strip() == "") {
                System.out.println("Name can't be empty");
                continue;
            }
            names.add(input);
            i++;

        } while (i < 4 || input.equalsIgnoreCase("start"));

        return names.toArray(new String[0]);
    }

    public static char getPlayerChoice() {
        String input = "";

        System.out.println("Do you want to Draw or Stand?");
        while (true) {
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Input can't be empty. Write again.");
                continue;
            }
            if ("d draw s stand".contains(input.toLowerCase()) && !input.contains(" ")) {
                return input.toLowerCase().charAt(0);

            }
            else {
                System.out.println("Incorrect input. Write again.");
            }
        }
    }

    public static char getYesOrNo() {
        String input = "";

        System.out.println("Y/N?");
        while (true) {
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                System.out.println("Input can't be empty. Write again.");
                continue;
            }
            if ("y yes n no".contains(input.toLowerCase()) && !input.contains(" ")) {
                return input.toLowerCase().charAt(0);

            }
            else {
                System.out.println("Incorrect input. Write again.");
            }
        }
    }
}
