/**
 * The Book class represents a book item in the inventory.
 * It extends the Item class with an additional attribute for the author of the book.
 */
public class Book extends Item {
    private String author;

    /**
     * Constructs a new Book item with the specified name, author, barcode, and price.
     *
     * @param name    the name of the book
     * @param author  the author of the book
     * @param barcode the barcode of the book
     * @param price   the price of the book
     */
    public Book(String name, String author, String barcode, double price) {
        super(name, barcode, price);
        this.author = author;
    }

    /**
     * Gets the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Writes information about the book item to the specified output file.
     *
     * @param outputFileName the name of the output file
     */
    public void getInfoMessage(String outputFileName) {
        FileIO.writeToFile(outputFileName, "Author of the " + getName() + " is " + getAuthor() + ". Its barcode is " + getBarcode() + " and its price is " + getPrice(), true, true);
    }
}
