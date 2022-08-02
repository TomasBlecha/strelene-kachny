package sk.stuba.fei.uim.oop.ActionCards.DuckMove;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;

import java.util.Collections;

public class DuckDanceCard extends ActionCard {

    public DuckDanceCard(String name, Game game) {
        super(name, game);
    }
    public void putCardsToDeck(){
        Game array = game;
        for(int i = 0; i < 6; i++){
            array.getDuckCards().add(array.getDuckBoard().get(0));
            array.getDuckBoard().remove(0);
        }
        Collections.shuffle(array.getDuckCards());
    }
    public void backOnBoard(){
        Game array = game;
        for(int i = 0; i < 6; i++){
            array.getDuckBoard().add(array.getDuckCards().get(0));
            array.getDuckCards().remove(0);
        }
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
        putCardsToDeck();
        backOnBoard();
        takeCard(playerIndex, chosenCardIndex);
    }
    //asi hotovo
}
