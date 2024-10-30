/**
 * The Stationery class represents a stationery item in the inventory.
 * It extends the Item class with an additional attribute for the kind of stationery.
 */
public class Stationery extends Item {
    private String kind;

    /**
     * Constructs a new Stationery item with the specified name, kind, barcode, and price.
     *
     * @param name    the name of the stationery item
     * @param kind    the kind of the stationery item
     * @param barcode the barcode of the stationery item
     * @param price   the price of the stationery item
     */
    public Stationery(String name, String kind, String barcode, double price) {
        super(name, barcode, price);
        this.kind = kind;
    }

    /**
     * Gets the kind of the stationery item.
     *
     * @return the kind of the stationery item
     */
    public String getKind() {
        return kind;
    }

    /**
     * Writes information about the stationery item to the specified output file.
     *
     * @param outputFileName the name of the output file
     */
    public void getInfoMessage(String outputFileName) {
        FileIO.writeToFile(outputFileName, "Kind of the " + getName() + " is " + kind + ". Its barcode is " + getBarcode() + " and its price is " + getPrice(), true, true);
    }
}
