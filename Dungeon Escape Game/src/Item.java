/**
 * Item class.
 * Represents objects the player can find in rooms.
 */
public class Item {

    private String name;
    private String description;
    private int healAmount; // 0 means not a potion

    // Constructor
    public Item(String name, String description, int healAmount) {
        this.name = name;
        this.description = description;
        this.healAmount = healAmount;
    }

    // Get item info
    public String getName() { return name; }
    public String getDescription() { return description; }
    public int getHealAmount() { return healAmount; }

    // Check if item is a potion
    public boolean isPotion() { return healAmount > 0; }
}