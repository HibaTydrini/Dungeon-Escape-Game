import java.util.ArrayList;
import java.util.Random;

/**
 * Player class.
 * Stores health, attack, items, and position.
 */
public class Player {

    private String name;
    private int hp;
    private int maxHp;
    private int attackPower;
    private boolean hasKey;
    private ArrayList<Item> potions;
    private int row, col;
    private Random rand = new Random();

    // Constructor
    public Player(String name) {
        this.name = name;
        this.hp = 50;
        this.maxHp = 50;
        this.attackPower = 10;
        this.hasKey = false;
        this.potions = new ArrayList<>();
        this.row = 0;
        this.col = 0;
    }

    // Get info
    public String getName() { return name; }
    public int getHp() { return hp; }
    public int getMaxHp() { return maxHp; }
    public boolean hasKey() { return hasKey; }
    public boolean isAlive() { return hp > 0; }
    public int getRow() { return row; }
    public int getCol() { return col; }
    public int potionCount() { return potions.size(); }

    // Move player
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Attack damage
    public int rollAttack() {
        return 5 + rand.nextInt(attackPower);
    }

    // Take damage
    public void takeDamage(int dmg) {
        hp = Math.max(0, hp - dmg);
    }

    // Pick up item
    public void pickUp(Item item) {
        if (item.isPotion()) {
            potions.add(item);
            System.out.println("  You got: " + item.getName());
        } else {
            hasKey = true;
            System.out.println("  You got the key!");
        }
    }

    // Use potion
    public boolean usePotion() {
        if (potions.isEmpty()) {
            System.out.println("  No potions!");
            return false;
        }

        Item potion = potions.remove(0);
        int heal = potion.getHealAmount();
        hp = Math.min(maxHp, hp + heal);

        System.out.println("  +" + heal + " HP (" + hp + "/" + maxHp + ")");
        return true;
    }

    // HP bar
    public String hpBar() {
        int filled = (int) Math.round((double) hp / maxHp * 8);
        int empty = 8 - filled;
        return "[" + "█".repeat(filled) + "░".repeat(empty) + "] " + hp + "/" + maxHp;
    }

    // Show status
    public void printStatus() {
        System.out.println("  " + name + " HP: " + hpBar()
                + " | ATK: " + attackPower
                + " | Potions: " + potionCount()
                + " | Key: " + (hasKey ? "yes" : "no"));
    }
}