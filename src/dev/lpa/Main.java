package dev.lpa;

import dev.utils.UserInput;

public class Main {

    public static void main(String[] args) throws Exception {
        boolean playAgain = false;
        boolean ret;
        Player winner;

        do {

            Game game = new Game(UserInput.getPlayerNames());
            while (!game.isGameOver()) {
                ret = game.firstDeal();
                if (!ret) throw new Exception("Error occurred while dealing cards, game was closed");

                System.out.printf("\nROUND %d\n\n", game.getRoundNumber());
                while (!game.isRoundOver()) {
                    ret = game.deal();
                    if (!ret) throw new Exception("Error occurred while dealing cards, game was closed");
                }

                try {
                    winner = game.getRoundWinner();
                    winner.addScore();
                    System.out.println("\n" + winner.getName() + " won this round!");
                    winner.showHand();
                    System.out.printf("Value: %d\n", winner.getHandValue());
                    game.printScore();
                }
                catch (Exception e) {
                    System.out.println("NOBODY WON!");
                }
                game.addRoundNumber();
                game.resetRound();
            }

            winner = game.getGameWinner();
            System.out.printf("""
                    ||||||||||||||||||||||
                    || %s WON THE GAME
                    || CONGRATULATIONS!
                    ||||||||||||||||||||||
                    
                    """.formatted(winner.getName()));
            System.out.println("Do you want to play new game?");
            playAgain = UserInput.getYesOrNo();
        } while (playAgain);


    }
}
