import java.util.ArrayList;
import java.util.List;

/**
 * Generic Inventory class for managing a collection of items.
 * 
 * @param <T> the type of items in the inventory, extending the Item class.
 */
public class Inventory<T extends Item> {
    private List<T> items;

    /**
     * Constructs an empty inventory.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Adds an item to the inventory.
     * 
     * @param item the item to add.
     */
    public void addItem(T item) {
        items.add(item);
    }

    /**
     * Removes an item from the inventory by its barcode.
     * 
     * @param barcode the barcode of the item to remove.
     */
    public void removeItem(String barcode) {
        items.removeIf(item -> item.getBarcode().equals(barcode));
    }

    /**
     * Searches for an item in the inventory by its barcode.
     * 
     * @param barcode the barcode of the item to search for.
     * @return the item with the specified barcode, or null if not found.
     */
    public T searchByBarcode(String barcode) {
        for (T item : items) {
            if (item.getBarcode().equals(barcode)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Searches for an item in the inventory by its name.
     * 
     * @param name the name of the item to search for.
     * @return the item with the specified name, or null if not found.
     */
    public T searchByName(String name) {
        for (T item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Gets the list of all items in the inventory.
     * 
     * @return the list of items.
     */
    public List<T> getItems() {
        return items;
    }

    /**
     * Checks if an item with the specified barcode exists in the inventory.
     * 
     * @param barcode the barcode of the item to check for.
     * @return true if the item exists, false otherwise.
     */
    public boolean containsItem(String barcode) {
        for (T item : items) {
            if (item.getBarcode().equals(barcode)) {
                return true;
            }
        }
        return false;
    }
}
