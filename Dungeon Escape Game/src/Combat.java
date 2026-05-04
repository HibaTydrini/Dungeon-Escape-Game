import java.util.Random;
import java.util.Scanner;

/**
 * Handles combat between player and enemy.
 * Player can attack, use potion, or flee.
 */
public class Combat {

    private static Random rand = new Random();

    public static boolean fight(Player player, Enemy enemy, Scanner scanner) {
        System.out.println("\n  A " + enemy.getName() + " appears!");
        System.out.println();

        // Fight loop
        while (player.isAlive() && enemy.isAlive()) {

            // Show HP
            System.out.println("  YOU : " + player.hpBar());
            System.out.println("  " + enemy.getName() + " : " + enemy.hpBar());
            System.out.println();
            System.out.println("  [1] Attack   [2] Potion   [3] Flee");
            System.out.print("  > ");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                // Player attack
                int dmg = player.rollAttack();
                enemy.takeDamage(dmg);
                System.out.println("  You deal " + dmg + " damage!");

            } else if (input.equals("2")) {
                // Use potion
                player.usePotion();

            } else if (input.equals("3")) {
                // Try to run
                if (rand.nextBoolean()) {
                    System.out.println("  You escaped!");
                    return true;
                } else {
                    System.out.println("  Escape failed!");
                }

            } else {
                System.out.println("  Invalid choice.");
            }

            // Enemy attack
            if (enemy.isAlive()) {
                int dmg = enemy.rollAttack();
                player.takeDamage(dmg);
                System.out.println("  " + enemy.attackMessage() + " You take " + dmg + " damage!");
            }

            System.out.println();
        }

        // Check result
        if (!player.isAlive()) {
            return false;
        }

        System.out.println("  Enemy defeated!\n");
        return true;
    }
}