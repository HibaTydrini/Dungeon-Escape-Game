public class Skeleton extends Enemy {
    public Skeleton() {
        super("Skeleton", 22, 9);
    }

    @Override
    public String attackMessage() {
        return "The Skeleton swings its sword!";
    }
}