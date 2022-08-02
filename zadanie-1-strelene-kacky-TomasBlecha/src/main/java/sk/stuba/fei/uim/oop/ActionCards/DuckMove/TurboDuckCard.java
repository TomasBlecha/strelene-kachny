package sk.stuba.fei.uim.oop.ActionCards.DuckMove;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;
import sk.stuba.fei.uim.oop.LakeCard;
import sk.stuba.fei.uim.oop.lakeCards.WaterCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class TurboDuckCard extends ActionCard {

    public TurboDuckCard(String name, Game game) {
        super(name, game);
    }



    public int checkIfDuck(){
        Game array = game;

        int boardIndex = ZKlavesnice.readInt("Select index of TurboDuck: "); //TODO fix input

        if(boardIndex <1 || boardIndex >6){
            System.out.println("You selected index out of Board. Try again!");

            return checkIfDuck();
        }
        if(array.getDuckBoard().get(boardIndex-1) instanceof WaterCard) {
            System.out.println("This is no Duck. Try Again!");

            return checkIfDuck();

        }
        else{
            return boardIndex-1;
        }
    }
    public void duckToFront(int boardIndex){
        Game array = game;

        LakeCard tmp;
        tmp = array.getDuckBoard().get(boardIndex);
        array.getDuckBoard().remove(boardIndex);
        array.getDuckBoard().add(0 , tmp);
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
        duckToFront(checkIfDuck());
        takeCard(playerIndex, chosenCardIndex);
    }
    //asi hotovo
}
