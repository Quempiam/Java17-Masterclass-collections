package dev.lpa;

import dev.utils.UserInput;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean playAgain = false;
        boolean ret;

        do {

            Game game = new Game(UserInput.getPlayerNames());
            while (!game.isGameOver()) {
                ret = game.firstDeal();
                if (!ret) throw new Exception("Error occurred while dealing cards, game was closed");

                while (!game.isRoundOver()) {
                    ret = game.deal();
                    if (!ret) throw new Exception("Error occurred while dealing cards, game was closed");
                }

                game.getRoundWinner().addScore();
                game.resetRound();
            }

            System.out.println("Do you want to play new game?");
            playAgain = UserInput.getYesOrNo();
        } while (playAgain);


    }
}
