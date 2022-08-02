package sk.stuba.fei.uim.oop;

public abstract class ActionCard {
    private String name;
    protected Game game;
    public ActionCard(String name, Game game){
        this.name = name;
        this.game = game;
    }

    public String getName() {
        return name;
    }



    public abstract void usage
            (int playerIndex, int chosenCardIndex);
}
