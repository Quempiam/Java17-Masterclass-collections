package dev.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static String[] getPlayerNames() {
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
        while (i < 4) {
            System.out.printf("Write name of Player %d/3 or write 'start' to start the game\n", i);

            try {
                input = scanner.nextLine();
                if (input.isBlank()) throw new Exception();
            } catch (Exception e) {
                System.out.println("Name can't be empty");
                continue;
            }
            if (input.equalsIgnoreCase("start")) break;
            names.add(input);
            i++;

        }

        return names.toArray(new String[0]);
    }

    /***
     * Returns true if player wants to draw or false if player wants to stand
     * @return true or false according to user input
     */
    public static boolean getDrawOrStand() {
        String input = "";

        System.out.println("Do you want to Draw or Stand?");
        while (true) {
            try {
                input = scanner.nextLine();
                if (input.isEmpty()) throw new Exception();
            } catch (Exception e) {
                System.out.println("Input can't be empty. Write again.");
                input = "";
                continue;
            }
            if ("d draw s stand".contains(input.toLowerCase()) && !input.contains(" ")) {
                return input.toLowerCase().charAt(0) == 'd';

            }
            else {
                System.out.println("Incorrect input. Write again.");
            }
        }
    }

    /***
     *
     * @return true or false according to user input
     */
    public static boolean getYesOrNo() {
        String input = "";

        System.out.println("Y/N?");
        while (true) {
            try {
                input = scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Input can't be empty. Write again.");
                continue;
            }
            if ("y yes n no".contains(input.toLowerCase()) && !input.contains(" ")) {
                return input.toLowerCase().charAt(0) == 'y';
            }
            else {
                System.out.println("Incorrect input. Write again.");
            }
        }
    }


}
