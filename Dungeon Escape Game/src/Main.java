import java.util.Scanner;

/**
 * Dungeon Escape game.
 * Move, fight enemies, collect items, and find the exit.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // Title
        System.out.println("=== DUNGEON ESCAPE ===");
        System.out.print("Enter your name: ");

        String name = scanner.nextLine().trim();
        if (name.isEmpty()) name = "Hero";

        Player player = new Player(name);
        Dungeon dungeon = new Dungeon();

        System.out.println("\nFind the key, defeat the boss, and reach the exit.\n");

        boolean running = true;

        // Game loop
        while (running && player.isAlive()) {

            int row = player.getRow();
            int col = player.getCol();

            Room room = dungeon.getRoom(row, col);
            room.markVisited();

            // Show info
            dungeon.printMap(row, col);
            player.printStatus();
            System.out.println("\nRoom: " + room.getName());
            System.out.println(room.getDescription());

            // Enemy fight
            if (room.hasEnemy()) {
                boolean alive = Combat.fight(player, room.getEnemy(), scanner);
                if (!alive) break;
            }

            if (!player.isAlive()) break;

            // Item
            if (room.hasItem()) {
                Item item = room.getItem();
                System.out.println("\nYou found: " + item.getName());

                System.out.print("Pick up? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    player.pickUp(item);
                    room.clearItem();
                }
            }

            // Win condition
            if (room.isExit() && player.hasKey()) {
                running = false;
                break;
            } else if (room.isExit()) {
                System.out.println("\nYou need the key!");
            }

            // Move player
            move(player, dungeon, row, col);
        }

        // End screen
        if (!player.isAlive()) {
            System.out.println("\nGame Over");
        } else {
            System.out.println("\nYou escaped!");
        }

        scanner.close();
    }

    // Movement
    private static void move(Player player, Dungeon dungeon, int row, int col) {

        boolean moved = false;

        while (!moved) {
            System.out.print("\nMove (N/S/E/W) or P: ");

            String input = scanner.nextLine().toUpperCase();

            int newRow = row;
            int newCol = col;

            switch (input) {
                case "N" -> newRow--;
                case "S" -> newRow++;
                case "W" -> newCol--;
                case "E" -> newCol++;
                case "P" -> {
                    player.usePotion();
                    continue;
                }
                default -> {
                    System.out.println("Use N/S/E/W or P.");
                    continue;
                }
            }

            if (dungeon.inBounds(newRow, newCol)) {
                player.setPosition(newRow, newCol);
                moved = true;
            } else {
                System.out.println("Can't go there.");
            }
        }
    }
}