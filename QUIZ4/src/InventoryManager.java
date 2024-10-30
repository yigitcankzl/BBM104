/**
 * The InventoryManager class is responsible for managing the inventory of books, toys, and stationery items.
 * It processes commands from an input file and performs actions such as adding, removing, searching, and displaying items.
 * This class interacts with the FileIO class for reading commands from an input file and writing results to an output file.
 */
public class InventoryManager {
    private String inputFileName;
    private String outputFileName;
    private Inventory<Book> bookInventory;
    private Inventory<Toy> toyInventory;
    private Inventory<Stationery> stationeryInventory;

    /**
     * Constructs a new InventoryManager with the specified input and output file names.
     * Initializes separate inventories for books, toys, and stationery items.
     *
     * @param inputFileName  the name of the input file containing commands
     * @param outputFileName the name of the output file for writing results
     */
    public InventoryManager(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        bookInventory = new Inventory<>();
        toyInventory = new Inventory<>();
        stationeryInventory = new Inventory<>();
    }

    /**
     * Processes the commands from the input file and performs the appropriate actions on the inventory.
     * Supported commands are ADD, REMOVE, SEARCHBYBARCODE, SEARCHBYNAME, and DISPLAY.
     * Each command is processed by splitting the input line and performing actions based on the command type.
     */
    public void processCommands() {
        // Read the content of the input file
        String[] content = FileIO.readFile(inputFileName, true, true);
        
        // Iterate through each line of the file content
        for (String line : content) {
            // Split the line into parts based on tab delimiter
            String[] parts = line.split("\t");
            String command = parts[0];

            switch (command) {
                case "ADD":
                    // ADD command to add a new item to the inventory
                    String itemType = parts[1];
                    switch (itemType) {
                        case "Book":
                            Book book = new Book(parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
                            bookInventory.addItem(book);
                            break;

                        case "Toy":
                            Toy toy = new Toy(parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
                            toyInventory.addItem(toy);
                            break;

                        case "Stationery":
                            Stationery stationery = new Stationery(parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
                            stationeryInventory.addItem(stationery);
                            break;
                    }
                    break;

                case "REMOVE":
                    // REMOVE command to remove an item from the inventory based on its barcode
                    String barcode = parts[1];
                    if (bookInventory.containsItem(barcode) || toyInventory.containsItem(barcode) || stationeryInventory.containsItem(barcode)) {
                        bookInventory.removeItem(barcode);
                        toyInventory.removeItem(barcode);
                        stationeryInventory.removeItem(barcode);

                        // Write the result to the output file
                        FileIO.writeToFile(outputFileName,"REMOVE RESULTS:\nItem is removed.",  true, true);
                    } else {
                        // Write the result to the output file
                        FileIO.writeToFile(outputFileName, "REMOVE RESULTS:\nItem is not found.",  true, true);
                    }
                    FileIO.writeToFile(outputFileName,"------------------------------",  true, true);
                    break;

                case "SEARCHBYBARCODE":
                    // SEARCHBYBARCODE command to find an item by its barcode
                    barcode = parts[1];
                    Item item = bookInventory.searchByBarcode(barcode);
                    if (item == null) item = toyInventory.searchByBarcode(barcode);
                    if (item == null) item = stationeryInventory.searchByBarcode(barcode);

                    if (item != null) {
                        // Write the search result to the output file
                        FileIO.writeToFile(outputFileName, "SEARCH RESULTS:",  true, true);
                        item.getInfoMessage(outputFileName);
                    } else {
                        // Write the search result to the output file
                        FileIO.writeToFile(outputFileName,"SEARCH RESULTS:\nItem is not found.",  true, true);
                    }
                    FileIO.writeToFile(outputFileName,"------------------------------",  true, true);
                    break;

                case "SEARCHBYNAME":
                    // SEARCHBYNAME command to find an item by its name
                    String name = parts[1];
                    item = bookInventory.searchByName(name);
                    if (item == null) item = toyInventory.searchByName(name);
                    if (item == null) item = stationeryInventory.searchByName(name);

                    if (item != null) {
                        // Write the search result to the output file
                        FileIO.writeToFile(outputFileName,"SEARCH RESULTS:",  true, true);
                        item.getInfoMessage(outputFileName);
                    } else {
                        // Write the search result to the output file
                        FileIO.writeToFile(outputFileName,"SEARCH RESULTS:\nItem is not found.", true, true);
                    }
                    FileIO.writeToFile(outputFileName,"------------------------------",  true, true);
                    break;

                case "DISPLAY":
                    // DISPLAY command to show all items in the inventory
                    FileIO.writeToFile(outputFileName,"INVENTORY:", true, true);

                    // Display all books
                    for (Book b : bookInventory.getItems()) {
                        b.getInfoMessage(outputFileName);
                    }
                    // Display all toys
                    for (Toy t : toyInventory.getItems()) {
                        t.getInfoMessage(outputFileName);
                    }
                    // Display all stationery items
                    for (Stationery s : stationeryInventory.getItems()) {
                        s.getInfoMessage(outputFileName);
                    }
                    FileIO.writeToFile(outputFileName,"------------------------------",  true, true);
                    break;
            }   
        }
    }
}
