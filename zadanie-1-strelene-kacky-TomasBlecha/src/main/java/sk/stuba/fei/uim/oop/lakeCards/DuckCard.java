package sk.stuba.fei.uim.oop.lakeCards;

import sk.stuba.fei.uim.oop.LakeCard;

public class DuckCard extends LakeCard {

    private int owner_id;

    public DuckCard(String name, int owner_id){
        super(name);
        this.owner_id = owner_id;


    }
    @Override
    public String getName() {
        return super.getName();
    }

    public int getOwner_id() {
        return owner_id;
    }
}

