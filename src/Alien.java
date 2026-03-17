// This class represents an alien in the game
public class Alien extends Entity {

    // Constructor to initialize alien
    public Alien(String name, int health, int attackPower) {
        super(name, health, attackPower);
    }

    @Override
    public void printStatus() {
        System.out.println("=== " + this.name + " Status ===");
        System.out.println("Species: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Attack Power: " + this.attackPower);
    }
}
