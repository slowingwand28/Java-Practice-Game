// This class represents a player in the game
public class Player {
    // Player attributes
    String name;
    int health;
    int attackPower;
    int credits;

    // Constructor to initialize player
    public Player(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.credits = 0; // Starting credits
    }

    // Method to handle player taking damage
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Health cannot be negative
        }
    }

    // Method to check if the player is still alive
    public boolean isAlive() {
        return this.health > 0;
    }

    // Method to display player status
    public void printStatus() {
        System.out.println("=== Player Status ===");
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Attack Power: " + this.attackPower);
        System.out.println("Credits: " + this.credits);
    }
}
