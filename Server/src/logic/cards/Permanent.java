package logic.cards;

/**
 * Created by Aedan Smith.
 */

public abstract class Permanent {
    private String id;
    public int health;

    public Permanent(String id, int health){
        this.id = id;
        this.health = health;
    }

    public String getId() {
        return id;
    }
}
