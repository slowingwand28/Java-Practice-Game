import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashMap;

// This is the main class of the application
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<Alien> alienTypes = new ArrayList<>();
    static HashMap<String, Integer> defeatedEnemies = new HashMap<>();


    public static void main(String[] args) {
        setupAliens();

        System.out.println("Enter your name, Spartan:");
        String playerName = scanner.nextLine();
        Player player = new Player(playerName, 100, 15);

        gameLoop(player);

        System.out.println("Game Over. Thanks for playing!");
    }

    // Method to set up different types of aliens
    public static void setupAliens() {
        alienTypes.add(new Alien("Grunt", 30, 8));
        alienTypes.add(new Alien("Jackal", 40, 10));
        alienTypes.add(new Alien("Elite", 50, 12));
    }

    // Main game loop
    public static void gameLoop(Player player) {
        int enemiesDefeated = 0;
        // Loop until the player is dead
        while (player.isAlive()) {
            System.out.println("\n1. Explore");
            System.out.println("2. View Stats");
            System.out.println("3. View Inventory");
            System.out.println("4. View Defeated Enemies");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (explore(player)) {
                        enemiesDefeated++;
                    }
                }
                case 2 -> player.printStatus();
                case 3 -> player.showInventory();
                case 4 -> showDefeatedEnemies();
                case 5 -> {

                    System.out.println("Quitting the game...");
                    return; // Exit the game loop
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
            if (enemiesDefeated >= 5) {
                System.out.println("Congratulations! You have secured the ring!");
                return; // Exit the game loop
            }
        }
    }

    // Method to handle exploring and encountering aliens
    public static boolean explore(Player player) {
        System.out.println("You explore the ring...");

        // 70% chance to encounter an alien
        int encounterChance = random.nextInt(100);
        if (encounterChance <= 20) {
            System.out.println("You found a Medkit!");
            Item healthPack = new Item("Medkit", 20);
            if (player.inventory.containsKey(healthPack.name)) {
                player.inventory.get(healthPack.name).add(healthPack);
            } else {
                ArrayList<Item> items = new ArrayList<>();
                items.add(healthPack);
                player.inventory.put(healthPack.name, items);
            }
            return false; // No combat, just found an item
        } else if (encounterChance <= 70) {
            Alien template = alienTypes.get(random.nextInt(alienTypes.size()));
            Alien alien = new Alien(template.name, template.health, template.attackPower);
            if (alien.name == "Elite") {
                System.out.println("You encountered an Elite!");
            } else {
                System.out.println("You encountered a " + alien.name + "!");
            }
            return combat(player, alien);
        } else {
            System.out.println("You didn't find anything.");
            return false;
        }
    }

    // Method to handle combat between player and alien
    public static boolean combat(Player player, Alien alien) {
        while (player.isAlive() && alien.isAlive()) {
            System.out.println("\n1. Attack");
            System.out.println("2. Use Item");
            System.out.println("3. Run");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Player attacks alien
                    alien.takeDamage(player.attackPower);
                    System.out.println("You attacked the " + alien.name + " for " + player.attackPower + " damage.");
                    if (!alien.isAlive()) {
                        System.out.println("You defeated the " + alien.name + "!");
                        if (defeatedEnemies.containsKey(alien.name)) {
                            defeatedEnemies.put(alien.name, defeatedEnemies.get(alien.name) + 1);
                        } else {
                            defeatedEnemies.put(alien.name, 1);
                        }
                        return true;
                    }

                    // Alien attacks player
                    player.takeDamage(alien.attackPower);
                    System.out.println("The " + alien.name + " attacked you for " + alien.attackPower + " damage.");
                }
                case 2 -> {
                    player.showInventory();

                    if (player.inventory.isEmpty()) {
                        continue;
                    }
                    System.out.println("Enter the name of the item you want to use:");
                    scanner.nextLine(); // Consume newline
                    String itemName = scanner.nextLine();
                    player.useItem(itemName);
                }
                case 3 -> {
                    System.out.println("You escaped!");
                    return false; // Player ran away
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        System.out.println("You were defeated by the " + alien.name);
        return false; // Player died
    }

    // Method to show defeated enemies
    public static void showDefeatedEnemies() {
        if (defeatedEnemies.isEmpty()) {
            System.out.println("You haven't defeated any enemies yet.");
        } else {
            System.out.println("Defeated Enemies:");
            for (String enemy : defeatedEnemies.keySet()) {
                System.out.println("- " + enemy + " x" + defeatedEnemies.get(enemy));
            }
        }
    }
}
