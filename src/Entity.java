// Abstract class representing a generic entity in the game. Inherided by both Player and Alien classes.
public abstract class Entity {
    String name;
    int health;
    int attackPower;

    public Entity(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health < 0) {
            this.health = 0; // Health cannot be negative
        }
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    // Abstract method. Subclasses must implement this to print their status.
    public abstract void printStatus();
}
