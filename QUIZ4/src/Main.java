/**
 * Main class for managing inventory operations.
 * 
 * This class reads input from a file, processes inventory commands, and writes output to another file.
 */
public class Main {
    /**
     * Main method to start the inventory management process.
     * 
     * @param args Command-line arguments. The first argument should be the input file name, and the second argument should be the output file name.
     */
    public static void main(String[] args) {
        // Check if command-line arguments are provided
        if (args.length != 2) {
            System.out.println("Usage: java Main <input file> <output file>");
            return;
        }

        // Extract input and output file names from command-line arguments
        String inputFileName = args[0];
        String outputFileName = args[1];

        // Create an InventoryManager instance with input and output file names
        InventoryManager inventoryManager = new InventoryManager(inputFileName, outputFileName);

        // Process inventory commands
        inventoryManager.processCommands();
    }
}
    