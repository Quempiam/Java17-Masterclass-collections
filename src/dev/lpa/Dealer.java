package dev.lpa;

public class Dealer extends Player{
    public Dealer() {
        super("Dealer");
    }

    public boolean wantDraw() {
        return getHandValue() <= 17;
    }
}
