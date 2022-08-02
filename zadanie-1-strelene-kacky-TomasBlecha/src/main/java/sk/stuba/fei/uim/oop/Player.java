package sk.stuba.fei.uim.oop;

import java.util.ArrayList;

public class Player {
    private String name;
    private int deadDucks;
    private boolean active;
    private int id;
    private ArrayList<ActionCard> cardsInPossesion;


    public Player(String name, int id){
        this.name = name;
        this.deadDucks = 0;
        this.active = true;
        this.id = id;
        this.cardsInPossesion = new ArrayList<>();

    }

    public String getName() {
        return name;
    }

    public int getDeadDucks() {
        return deadDucks;
    }

    public void setDeadDucks(int deadDucks) {
        this.deadDucks = deadDucks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setCardInPossesion(ActionCard givenCard) {
        cardsInPossesion.add(givenCard);
    }

    public ArrayList<ActionCard> getCardsInPossesion() {
        return cardsInPossesion;
    }
}
