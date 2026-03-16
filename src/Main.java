import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

// This is the main class of the application
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static ArrayList<Alien> alienTypes = new ArrayList<>();

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
        // Loop until the player is dead
        while (player.isAlive()) {
            System.out.println("\n1. Explore");
            System.out.println("2. View Stats");
            System.out.println("3. Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> explore(player);
                case 2 -> player.printStatus();
                case 3 -> {
                    System.out.println("Quitting the game...");
                    break;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle exploring and encountering aliens
    public static void explore(Player player) {
        System.out.println("You explore the ring...");

        // 70% chance to encounter an alien
        int encounterChance = random.nextInt(100);
        if (encounterChance < 70) {
            Alien template = alienTypes.get(random.nextInt(alienTypes.size()));
            Alien alien = new Alien(template.species, template.health, template.attackPower);
            if (alien.species == "Elite") {
                System.out.println("You encountered an Elite!");
            } else {
                System.out.println("You encountered a " + alien.species + "!");
            }
            combat(player, alien);
        } else {
            System.out.println("You didn't find anything.");
        }
    }

    // Method to handle combat between player and alien
    public static void combat(Player player, Alien alien) {
        while (player.isAlive() && alien.isAlive()) {
            System.out.println("\n1. Attack");
            System.out.println("2. Run");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    // Player attacks alien
                    alien.takeDamage(player.attackPower);
                    System.out.println("You attacked the " + alien.species + " for " + player.attackPower + " damage.");
                    if (!alien.isAlive()) {
                        System.out.println("You defeated the " + alien.species + "!");
                        return;
                    }

                    // Alien attacks player
                    player.takeDamage(alien.attackPower);
                    System.out.println("The " + alien.species + " attacked you for " + alien.attackPower + " damage.");
                }
                case 2 -> {
                    System.out.println("You escaped!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
