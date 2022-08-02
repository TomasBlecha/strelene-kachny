package sk.stuba.fei.uim.oop.ActionCards.AimAndShoot;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;
import sk.stuba.fei.uim.oop.lakeCards.WaterCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class WildBillCard extends ActionCard {
    public WildBillCard(String name, Game game) {
        super(name, game);
    }
    public void showAimed(){   //Show board
        Game array = game;
        for(int i = 0; i < 6; i++){
            System.out.println("- Card " + array.getDuckBoard().get(i).getName() + "is " + array.aimers.get(i).getName() + ".");
        }
    }
    public int checkIfAimed(){
        Game array = game;

        int boardIndex = ZKlavesnice.readInt("Select index where to Shoot: ");
        if(boardIndex <1 || boardIndex >6){
            System.out.println("You selected index out of Board. Try again!");

            return checkIfAimed();
        }
        if(array.aimers.get(boardIndex-1).getName() == "Aimed"){
            array.getActionCardDeck().add(array.aimers.get(boardIndex-1));
            array.aimers.remove(boardIndex-1);
            array.aimers.add(boardIndex-1, array.getPhantomCardHolder().get(0));
            array.getPhantomCardHolder().remove(0);

        }
        return boardIndex-1;
    }
    public void shoot(int boardIndex){
        Game array = game;

        if(!(array.getDuckBoard().get(boardIndex) instanceof WaterCard)){
            for(int i = 0; i < array.getPlayers().size(); i++){
                if(array.getDuckBoard().get(boardIndex).getName() == array.getPlayers().get(i).getName()){
                    array.getPlayers().get(i).setDeadDucks(array.getPlayers().get(i).getDeadDucks() + 1);
                    array.getDuckBoard().remove(boardIndex);
                    array.getDuckBoard().add(array.getDuckCards().get(0));
                    array.getDuckCards().remove(0);
                    System.out.println("Player " + array.getPlayers().get(i).getName() + " lost a duck.");
                    return;
                }
            }

        }
        else{
            System.out.println("You shot the water.");
            return;
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
        showAimed();
        shoot(checkIfAimed());
        takeCard(playerIndex, chosenCardIndex);
    }
    //Asi hotovo


}
