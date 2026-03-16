// This class represents an alien in the game
public class Alien {
    String species;
    int health;
    int attackPower;

    // Constructor to initialize alien
    public Alien(String species, int health, int attackPower) {
        this.species = species;
        this.health = health;
        this.attackPower = attackPower;
    }

    // Method to handle alien taking damage
    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Health cannot be negative
        }
    }

    // Method to check if the alien is still alive
    public boolean isAlive() {
        return this.health > 0;
    }
}
