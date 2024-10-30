/**
 * The Item class is an abstract representation of an item in the inventory.
 * It serves as a base class for specific item types such as Book, Toy, and Stationery.
 */
public abstract class Item {
    private String name;
    private String barcode;
    private double price;

    /**
     * Constructs a new Item with the specified name, barcode, and price.
     *
     * @param name    the name of the item
     * @param barcode the barcode of the item
     * @param price   the price of the item
     */
    public Item(String name, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
    }

    /**
     * Gets the name of the item.
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the barcode of the item.
     *
     * @return the barcode of the item
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Gets the price of the item.
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Writes information about the item to the specified output file.
     * This method should be overridden by subclasses to provide specific details about the item.
     *
     * @param outputFileName the name of the output file
     */
    public void getInfoMessage(String outputFileName) {
        // This method should be overridden by subclasses
    }
}
