// This class represents a player in the game
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends Entity {
    // Player's inventory to hold items
    HashMap<String, ArrayList<Item>> inventory; 

    // Constructor to initialize player
    public Player(String name, int health, int attackPower) {
        super(name, health, attackPower);
        this.inventory = new HashMap<>(); // Initialize inventory
    }

    // Method to display player status
    @Override
    public void printStatus() {
        System.out.println("=== Player Status ===");
        System.out.println("Name: " + this.name);
        System.out.println("Health: " + this.health);
        System.out.println("Attack Power: " + this.attackPower);
    }

    // Method to use an item from the inventory
    public void useItem(String itemName) {
        if (inventory.containsKey(itemName)) {
            ArrayList<Item> items = inventory.get(itemName);
            Item item = items.remove(0); // Get the first item of this type

            this.health += item.healAmount; // Heal the player
            System.out.println("You used " + item.name + " and healed for " + item.healAmount + " health.");
            if (items.isEmpty()) {
                inventory.remove(itemName); // Remove the item type from inventory if no more items of that type
            }
        } else {
            System.out.println("You don't have that item in your inventory.");
        }
    }

    // Method to show the player's inventory
    public void showInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("=== Inventory ===");
            for (String itemName : inventory.keySet()) {
                System.out.println("- " + itemName + " x" + inventory.get(itemName).size());
            }
        }
    }
}
