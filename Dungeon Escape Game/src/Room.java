/**
 * Room class.
 * Represents one room in the dungeon.
 */
public class Room {

    private String name;
    private String description;
    private Enemy enemy;   // can be null
    private Item item;     // can be null
    private boolean visited;
    private boolean isExit;

    // Constructor
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        this.visited = false;
        this.isExit = false;
    }

    // Get info
    public String getName() { return name; }
    public String getDescription() { return description; }

    // Visited
    public boolean isVisited() { return visited; }
    public void markVisited() { visited = true; }

    // Exit
    public boolean isExit() { return isExit; }
    public void setExit(boolean b) { isExit = b; }

    // Enemy
    public Enemy getEnemy() { return enemy; }
    public void setEnemy(Enemy e) { enemy = e; }
    public boolean hasEnemy() { return enemy != null && enemy.isAlive(); }

    // Item
    public Item getItem() { return item; }
    public void setItem(Item i) { item = i; }
    public boolean hasItem() { return item != null; }
    public void clearItem() { item = null; }

    // Map symbol
    public String mapSymbol() {
        if (!visited) return "?";
        if (isExit) return "X";
        if (hasEnemy()) return "M";
        if (hasItem()) return "i";
        return " ";
    }
}