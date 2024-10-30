import java.util.ArrayList;

/**
 * The Main class contains the main method to run the program.
 */
public class Main {

     /**
     * The main method to start the program.
     *
     * @param args Command line arguments: [0] - product content file path,
     *             [1] - purchase content file path, [2] - output file name.
     */
    public static void main(String[] args) {
        String[] productContent = FileInput.readFile(args[0], false, false);
        String[] purchaseContent = FileInput.readFile(args[1], false, false);
        String outputFileName = args[2];

        // Create product instance
        Product product = new Product(productContent, outputFileName);

        // Create product instance
        ArrayList<ArrayList<String>> listOfProduct = product.getListOfProduct();

        // Create purchase instance and perform purchase
        new Purchase(purchaseContent, listOfProduct, outputFileName);
        
        // Write the updated product table to the output file    
        product.writeProductTable(listOfProduct, outputFileName);

    }
}
