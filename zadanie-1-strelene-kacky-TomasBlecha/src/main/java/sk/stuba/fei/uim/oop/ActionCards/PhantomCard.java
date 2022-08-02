package sk.stuba.fei.uim.oop.ActionCards;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;

public class PhantomCard extends ActionCard {
    public PhantomCard(String name, Game game) {
        super(name, game);
    }

    @Override
    public void usage(int playerIndex, int chosenCardIndex) {

    }
}
