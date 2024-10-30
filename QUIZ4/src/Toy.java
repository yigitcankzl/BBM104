/**
 * The Toy class represents a toy item in the inventory.
 * It extends the Item class with an additional attribute for the color of the toy.
 */
public class Toy extends Item {
    private String color;

    /**
     * Constructs a new Toy item with the specified name, color, barcode, and price.
     *
     * @param name    the name of the toy
     * @param color   the color of the toy
     * @param barcode the barcode of the toy
     * @param price   the price of the toy
     */
    public Toy(String name, String color, String barcode, double price) {
        super(name, barcode, price);
        this.color = color;
    }

    /**
     * Gets the color of the toy.
     *
     * @return the color of the toy
     */
    public String getColor() {
        return color;
    }

    /**
     * Writes information about the toy item to the specified output file.
     *
     * @param outputFileName the name of the output file
     */
    public void getInfoMessage(String outputFileName) {
        FileIO.writeToFile(outputFileName, "Color of the " + getName() + " is " + getColor() + ". Its barcode is " + getBarcode() + " and its price is " + getPrice(), true, true);
    }
}
