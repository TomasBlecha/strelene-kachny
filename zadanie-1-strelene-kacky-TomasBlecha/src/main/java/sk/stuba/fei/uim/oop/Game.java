package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.ActionCards.AimAndShoot.AimCard;
import sk.stuba.fei.uim.oop.ActionCards.AimAndShoot.ShootCard;
import sk.stuba.fei.uim.oop.ActionCards.AimAndShoot.WildBillCard;
import sk.stuba.fei.uim.oop.ActionCards.DuckMove.DuckDanceCard;
import sk.stuba.fei.uim.oop.ActionCards.DuckMove.DuckMoveCard;
import sk.stuba.fei.uim.oop.ActionCards.DuckMove.RosamboCard;
import sk.stuba.fei.uim.oop.ActionCards.DuckMove.TurboDuckCard;
import sk.stuba.fei.uim.oop.ActionCards.PhantomCard;
import sk.stuba.fei.uim.oop.lakeCards.DuckCard;
import sk.stuba.fei.uim.oop.lakeCards.WaterCard;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    public ArrayList<Player> players = new ArrayList<>();
    private ArrayList<LakeCard> duckCards = new ArrayList<>();
    private ArrayList<LakeCard> duckBoard = new ArrayList<>();
    public ArrayList<ActionCard> aimers = new ArrayList<>();
    private ArrayList<ActionCard> actionCardDeck = new ArrayList<>();
    private ArrayList<ActionCard> phantomCardHolder = new ArrayList<>();

    public int numberOfPlayers(){
        int numPlayers;
        ArrayList<String> namesOfPlayers = new ArrayList<>();
        String nameOfPlayer;
        System.out.println("Welcome to Duck Hunt.");

        numPlayers = ZKlavesnice.readInt("Select number of players from 2 to 6: ");
        if (numPlayers < 2 || numPlayers > 6){
            System.out.println("You selected number out of range. Try again!");

            return numberOfPlayers();
        }
        for (int i = 0; i < numPlayers; i++){
            nameOfPlayer = ZKlavesnice.readString("Set name of player " + i + ": \n");
            nameOfPlayer = nameOfPlayer +i;
                players.add(new Player(nameOfPlayer, i));
            }

            //System.out.println("sss");


        return numPlayers;
    }
    private void fillLakeCards(int numberOfPlayers){
        for(int i = 0; i < numberOfPlayers; i++){
            for (int j = 0; j < 5; j++){
                //duckCards.add(new DuckCard(players.get(i).getName()));
                duckCards.add(new DuckCard(players.get(i).getName(),i));

            }
        }
        for (int k = 0; k < 5; k++){
            duckCards.add(new WaterCard("water"));
        }
        Collections.shuffle(duckCards);

        //System.out.println(duckCards.size()); //number of duck cards
        /*
        for(int i = 0; i < numberOfPlayers; i++){  // names of players
            System.out.println(players.get(i).getName());

        }
        */
        /*
        for(int i = 0; i < duckCards.size(); i++){ // names of duckCards
            System.out.println(duckCards.get(i).getName());

        }

         */
        System.out.println("----------------------");
    }
    private void setDuckBoard(){
        for (int i = 0; i < 6; i++){
            duckBoard.add(duckCards.get(0));
            duckCards.remove(0);

        }
        for(int i = 0; i < 6; i++){
            aimers.add(new PhantomCard("NotAimed", this));
        }

        /*
        System.out.println("++++++++++++++++++++++");
        for(int i = 0; i < duckBoard.size(); i++){ // names of duckBoard
            System.out.println(duckBoard.get(i).getName());
        }
        System.out.println("***********************");
        for(int i = 0; i < duckCards.size(); i++){ // names of duckCards
            System.out.println(duckCards.get(i).getName());

        }

         */
    }
    private void setActionCardDeck(){
        for(int i = 0; i < 10; i++){
            actionCardDeck.add(new AimCard("Aimed", this));
        }
        for(int i = 0; i < 12; i++){
            actionCardDeck.add(new ShootCard("Shoot", this));
        }
        for(int i = 0; i < 2; i++){
            actionCardDeck.add(new WildBillCard("WildBill", this));
        }
        for(int i = 0; i < 6; i++){
            actionCardDeck.add(new DuckMoveCard("DuckMove", this));
        }
        for(int i = 0; i < 2; i++){
            actionCardDeck.add(new RosamboCard("Rosambo",this));
        }
        actionCardDeck.add(new TurboDuckCard("TurboDuck", this));

        actionCardDeck.add(new DuckDanceCard("DuckDance", this));

        Collections.shuffle(actionCardDeck);
    }
    private void givePlayersTheirCards(){
        for (int i = 0; i < players.size(); i++){
            for(int j = 0; j < 3; j++){
                players.get(i).setCardInPossesion(actionCardDeck.get(0));
                actionCardDeck.remove(0);
            }
        }
    }
    private int chosenNumber(){
        int zvolene = ZKlavesnice.readInt("Write number of your Card: ");
        if(zvolene < 1 || zvolene > 3){
            System.out.println("Out of range. Try again!");
            return chosenNumber();
        }
        return zvolene;
    }
    private void showerOfAimed(){
        for(int i = 0; i < 6; i++) {
            if (duckBoard.get(i) instanceof DuckCard) {
                System.out.println("- Duck of player " + duckBoard.get(i).getName() + " is " + aimers.get(i).getName() + ".");
            } else {
                System.out.println("- " + duckBoard.get(i).getName() + " is " + aimers.get(i).getName() + ".");
            }
        }
    }
    private int getNumberOfActivePlayers() {
        int count = 0;
        for(int i = 0; i < players.size(); i++){
            if(players.get(i).isActive()){
                count++;
            }
        }
        return count;
    }
    private void getVictor(){
        for (int i = 0; i < players.size(); i++){
            if(players.get(i).isActive()){
                System.out.println("Victor is player " + players.get(i).getName() + ".");
                return;
            }
        }
    }
    private int checkAim(){
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(aimers.get(i).getName().equals("Aimed")){
                count++;
            }
        }
        if(count == 6){
            return 1;
        }
        return 0;
    }

    private int checkNoAim(){
        int count = 0;
        for(int i = 0; i < 6; i++){
            if(aimers.get(i).getName().equals("NotAimed")){
                count++;
            }
        }
        if(count == 6){
            return 1;
        }
        return 0;
    }
    private int checkForAimCards(int player){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if(players.get(player).getCardsInPossesion().get(i).getName().equals("Aimed")){
                count++;
            }
        }
        if(count == 3){
            return 1;
        }
        return 0;
    }
    private int checkForShootCards(int player){
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if(players.get(player).getCardsInPossesion().get(i).getName().equals("Shoot")){
                count++;
            }
        }
        if(count == 3){
            return 1;
        }
        return 0;
    }

    public ArrayList<LakeCard> getDuckBoard() {
        return duckBoard;
    }

    public ArrayList<LakeCard> getDuckCards() {
        return duckCards;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<ActionCard> getActionCardDeck() {
        return actionCardDeck;
    }

    public ArrayList<ActionCard> getPhantomCardHolder() {
        return phantomCardHolder;
    }

    public ArrayList<ActionCard> getAimers() {
        return aimers;
    }

    public void theGame(){
        fillLakeCards(numberOfPlayers());
        setDuckBoard();
        setActionCardDeck();
        givePlayersTheirCards();
        int turn = 0;
        int chosenNumber;
        boolean allGood;
        System.out.println("8888888888888888888888888888");
        while(getNumberOfActivePlayers() > 1){
            allGood = true;

            turn = turn % players.size(); //
            if(players.get(turn).isActive() && players.get(turn).getDeadDucks() >= 5){  //kill players with 5 dead ducks
                players.get(turn).setActive(false);
                for (int i = 0; i < 3; i++) {
                    actionCardDeck.add(players.get(turn).getCardsInPossesion().get(0));
                    players.get(turn).getCardsInPossesion().remove(0);
                }
            }
            if(!(players.get(turn).isActive())){  //skip non active players
                turn++;
                continue;
            }
            System.out.println("Player on turn: "+ players.get(turn).getName() +".");
            System.out.println("Dead Ducks of player: " + players.get(turn).getDeadDucks() + ".");

            showerOfAimed();

            System.out.println("Choose your Card!");
            //System.out.println("Aku kartu chcete pouzit?");
            System.out.println("1. " + players.get(turn).getCardsInPossesion().get(0).getName());
            System.out.println("2. " + players.get(turn).getCardsInPossesion().get(1).getName());
            System.out.println("3. " + players.get(turn).getCardsInPossesion().get(2).getName());
            chosenNumber = chosenNumber();
            boolean noProblem = false;
            boolean problemHappend = false;
            while (!noProblem) {

                if(problemHappend) {
                    System.out.println("Choose your Card!");
                    //System.out.println("Aku kartu chcete pouzit?");
                    System.out.println("1. " + players.get(turn).getCardsInPossesion().get(0).getName());
                    System.out.println("2. " + players.get(turn).getCardsInPossesion().get(1).getName());
                    System.out.println("3. " + players.get(turn).getCardsInPossesion().get(2).getName());
                    chosenNumber = chosenNumber();
                }
                if (checkAim() == 1 && checkForAimCards(turn) == 1){
                    System.out.println("You cant play any of your cards. I will exchange one and you miss a turn");
                    actionCardDeck.add(players.get(turn).getCardsInPossesion().get(0));
                    players.get(turn).getCardsInPossesion().remove(0);

                    players.get(turn).getCardsInPossesion().add(actionCardDeck.get(0));
                    actionCardDeck.remove(0);
                    allGood = false;
                    break;
                }
                if (checkNoAim() == 1 && checkForShootCards(turn) == 1){
                    System.out.println("You cant play any of your cards. I will exchange one and you miss a turn");
                    actionCardDeck.add(players.get(turn).getCardsInPossesion().get(0));
                    players.get(turn).getCardsInPossesion().remove(0);

                    players.get(turn).getCardsInPossesion().add(actionCardDeck.get(0));
                    actionCardDeck.remove(0);
                    allGood = false;
                    break;
                }
                if (players.get(turn).getCardsInPossesion().get(chosenNumber - 1).getName().equals("Shoot") && checkNoAim() == 1) {
                    System.out.println("You cant use this card, there are no Aimers. Try another!");
                    problemHappend = true;
                    continue;
                }
                if (players.get(turn).getCardsInPossesion().get(chosenNumber - 1).getName().equals("Aimed") && checkAim() == 1) {
                    System.out.println("You cant use this card, all positions are Aimed at. Try another!");
                    problemHappend = true;
                    continue;
                }

                noProblem = true;
            }
            if(allGood == true) {
                players.get(turn).getCardsInPossesion().get(chosenNumber - 1).usage(turn, chosenNumber - 1);
            }
            /*
            duckBoard.remove(chosenNumber-1);
            duckBoard.add(duckCards.get(0));
            duckCards.remove(0);
            System.out.println("++++++++++++++++++++++");
            /*
            for(int i = 0; i < duckBoard.size(); i++){ // names of duckBoard
                System.out.println(duckBoard.get(i).getName());
            }
            */
            turn++;

        }
        getVictor();

    }


}
