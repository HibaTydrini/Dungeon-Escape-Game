import java.util.Random;

/**
 * Enemy base class.
 * Used to create different types of enemies.
 */
public abstract class Enemy {

    protected String name;
    protected int hp;
    protected int maxHp;
    protected int attackPower;
    protected Random rand = new Random();

    // Constructor
    public Enemy(String name, int hp, int attackPower) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
    }

    // Get enemy info
    public String getName() { return name; }
    public int getHp() { return hp; }
    public boolean isAlive() { return hp > 0; }

    // Take damage
    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    // Random attack damage
    public int rollAttack() {
        return 1 + rand.nextInt(attackPower);
    }

    // Show HP bar
    public String hpBar() {
        int filled = (int) Math.round((double) hp / maxHp * 8);
        int empty = 8 - filled;
        return "[" + "█".repeat(filled) + "░".repeat(empty) + "] " + hp + "/" + maxHp;
    }

    // Message when enemy attacks (different for each enemy)
    public abstract String attackMessage();
}