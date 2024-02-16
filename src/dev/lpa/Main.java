package dev.lpa;

import dev.utils.UserInput;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Game game = new Game(UserInput.getPlayerNames());
    }
}
