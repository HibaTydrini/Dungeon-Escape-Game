public class Boss extends Enemy {
    public Boss() {
        super("The Dark Lord", 40, 12);
    }

    @Override
    public String attackMessage() {
        return "The Dark Lord unleashes a devastating blow!";
    }
}