/**
 * Dungeon class.
 * A simple 3x3 grid of rooms.
 */
public class Dungeon {

    public static final int SIZE = 3;
    private Room[][] grid;

    // Constructor
    public Dungeon() {
        grid = new Room[SIZE][SIZE];
        build();
    }

    // Create the dungeon layout
    private void build() {

        // Row 0
        grid[0][0] = new Room("Entrance", "The gate closes behind you.");
        grid[0][1] = new Room("Mossy Hall", "Something is nearby.");
        grid[0][2] = new Room("Storeroom", "Old shelves here.");

        // Row 1
        grid[1][0] = new Room("Empty Room", "Nothing here.");
        grid[1][1] = new Room("Chapel", "Broken altar.");
        grid[1][2] = new Room("Key Room", "Something shiny on the ground.");

        // Row 2
        grid[2][0] = new Room("Rat Room", "You hear noises.");
        grid[2][1] = new Room("Bone Hall", "Bones everywhere.");
        grid[2][2] = new Room("Boss Room", "It feels dangerous.");

        // Enemies
        grid[0][1].setEnemy(new Goblin());
        grid[1][1].setEnemy(new Skeleton());
        grid[2][1].setEnemy(new Goblin());
        grid[2][2].setEnemy(new Boss());

        // Items
        grid[0][2].setItem(new Item("Potion", "Heal 20 HP", 20));
        grid[1][2].setItem(new Item("Key", "Opens exit", 0));
        grid[2][0].setItem(new Item("Potion", "Heal 20 HP", 20));

        // Exit
        grid[2][2].setExit(true);

        // Start room already visited
        grid[0][0].markVisited();
    }

    // Get room
    public Room getRoom(int row, int col) {
        return grid[row][col];
    }

    // Check if position is valid
    public boolean inBounds(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

    // Print map
    public void printMap(int playerRow, int playerCol) {
        System.out.println("\n  MAP");

        for (int r = 0; r < SIZE; r++) {
            System.out.print("  ");
            for (int c = 0; c < SIZE; c++) {

                if (r == playerRow && c == playerCol) {
                    System.out.print("[Y]");
                } else {
                    System.out.print(grid[r][c].mapSymbol());
                }

                System.out.print(" ");
            }
            System.out.println();
        }
    }
}