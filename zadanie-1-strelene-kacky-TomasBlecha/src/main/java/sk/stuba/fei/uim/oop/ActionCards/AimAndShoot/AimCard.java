package sk.stuba.fei.uim.oop.ActionCards.AimAndShoot;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class AimCard extends ActionCard {

    public AimCard(String name, Game game) {
        super(name, game);
    }

    public void showAimed(){   //Show board
        Game array = game;
        for(int i = 0; i < 6; i++){
            System.out.println("- Card " + array.getDuckBoard().get(i).getName() + " is " + array.aimers.get(i).getName() + ".");
        }
    }
    public int checkWhereAiming(){
        Game array = game;
        int boardIndex = ZKlavesnice.readInt("Select index where to Aim: ");
        if(boardIndex <1 || boardIndex >6){
            System.out.println("You selected index out of Board. Try again!");

            return checkWhereAiming();
        }
        if(array.getAimers().get(boardIndex-1) instanceof AimCard){
            System.out.println("This place is already Aimed at. Try another!");

            return checkWhereAiming();
        }
        return boardIndex-1;
    }
    public void useAim(int choice, int playerIndex, int chosenCardIndex){  //Set Aim on chosen place
        Game array = game;

        array.getPhantomCardHolder().add(array.getAimers().get(choice));
        array.getAimers().remove(choice);
        array.getAimers().add(choice, array.getPlayers().get(playerIndex).getCardsInPossesion().get(chosenCardIndex));
        array.getPlayers().get(playerIndex).getCardsInPossesion().remove(chosenCardIndex);

        array.getPlayers().get(playerIndex).getCardsInPossesion().add(array.getActionCardDeck().get(0));
        array.getActionCardDeck().remove(0);



    }
    @Override
    public void usage(int playerIndex , int chosenCardIndex){
        showAimed();
        useAim(checkWhereAiming(),playerIndex,chosenCardIndex);
    }
}
//asi hotovo