package sk.stuba.fei.uim.oop.ActionCards.DuckMove;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;

import java.util.Collections;

public class RosamboCard extends ActionCard {

    public RosamboCard(String name, Game game) {
        super(name, game);
    }
    public void use(){
        Game array = game;

        Collections.shuffle(array.getDuckBoard());
    }
    public void takeCard(int playerIndex , int chosenCardIndex){
        Game array = game;

        array.getActionCardDeck().add(array.getPlayers().get(playerIndex).getCardsInPossesion().get(chosenCardIndex));
        array.getPlayers().get(playerIndex).getCardsInPossesion().remove(chosenCardIndex);

        array.getPlayers().get(playerIndex).getCardsInPossesion().add(array.getActionCardDeck().get(0));
        array.getActionCardDeck().remove(0);
    }
    @Override
    public void usage(int playerIndex, int chosenCardIndex) {
        use();
        takeCard(playerIndex, chosenCardIndex);
    }
    //asi hotovo
}
