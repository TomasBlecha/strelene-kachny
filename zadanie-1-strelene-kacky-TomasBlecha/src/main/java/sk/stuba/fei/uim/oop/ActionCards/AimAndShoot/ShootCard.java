package sk.stuba.fei.uim.oop.ActionCards.AimAndShoot;

import sk.stuba.fei.uim.oop.ActionCard;
import sk.stuba.fei.uim.oop.Game;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

public class ShootCard extends ActionCard {
    public ShootCard(String name, Game game) {
        super(name, game);
    }

    public void showAimed(){   //Show board
        Game array = game;
        for(int i = 0; i < 6; i++){
            System.out.println("- Card " + array.getDuckBoard().get(i).getName() + "is " + array.aimers.get(i).getName() + ".");
        }
    }
    public int useShoot() {   //Shoot chosen place
        Game array = game;

        int boardIndex = ZKlavesnice.readInt("Select index where to Aim: ");
        if(boardIndex <1 || boardIndex >6){
            System.out.println("You selected index out of Board. Try again!");

            return useShoot();
        }
        if (array.getAimers().get(boardIndex - 1).getName() == "NotAimed") {
            System.out.println("You cant shoot on NotAimed place. Try again!");
            return useShoot();
        }
        else {
            array.getActionCardDeck().add(array.getAimers().get(boardIndex-1));
            array.getAimers().remove(boardIndex - 1);
            array.getAimers().add(boardIndex - 1, array.getPhantomCardHolder().get(0));// Set to noAim
            array.getPhantomCardHolder().remove(0);

            String tmpDuck = array.getDuckBoard().get(boardIndex-1).getName(); //Get name of shoot Duck
            if (!(tmpDuck == "water")) {         //Check if shooting at water


                for (int i = 0; i < array.getPlayers().size(); i++) {          //Find player with same name
                    if (array.getPlayers().get(i).getName() == tmpDuck) {
                        int tpmPlayer = array.getPlayers().get(i).getDeadDucks();
                        array.getPlayers().get(i).setDeadDucks(tpmPlayer + 1);   //When found, raise his deadDucks
                    }
                }

                //int tpm = array.getPlayers().get(playerOnTurn).getDeadDucks();
                //array.getPlayers().get(playerOnTurn).setDeadDucks(tpm+1);

                array.getDuckBoard().remove(boardIndex-1);
                array.getDuckBoard().add(array.getDuckCards().get(0));
                array.getDuckCards().remove(0);
            }
            else{
                System.out.println("You shot at water.");
            }
            return 0;
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
        useShoot();
        takeCard(playerIndex, chosenCardIndex);


    }
    //asi hotovo
}
