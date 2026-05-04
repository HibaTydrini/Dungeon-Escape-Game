public class Goblin extends Enemy {
    public Goblin() {
        super("Goblin", 15, 6);
    }

    @Override
    public String attackMessage() {
        return "The Goblin scratches you!";
    }
}