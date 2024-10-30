import java.util.ArrayList;

/**
 * The Product class represents products available in a gym meal machine.
 * It manages the creation, addition, and writing of product information.
 */
public class Product {
    private ArrayList<ArrayList<String>> listOfProduct;
    private ArrayList<ArrayList<String>> data;


    /**
     * Constructs a Product object with the specified product content and output file path.
     *
     * @param productContent The array containing product content.
     * @param outputFilePath The path of the output file.
     */
    public Product(String[] productContent,String outputFilePath) {
        data = createEmptyTable(data);
        this.listOfProduct = createProductTable(productContent);
        listOfProduct = add(listOfProduct,outputFilePath);
        writeProductTable(listOfProduct, outputFilePath);

    }


    /**
     * Adds products to the list of available products.
     *
     * @param list           The list of products to add.
     * @param outputFileName The name of the output file.
     * @return The updated list of products.
     */
    private ArrayList<ArrayList<String>> add(ArrayList<ArrayList<String>> list, String outputFileName) {
        if (this.data == null) {
            this.data = new ArrayList<>();
        }
    
        for (ArrayList<String> listRow : list) {
            boolean found = false;
            int fullNumber = 0;
    
            for (ArrayList<String> dataRow : this.data) {
                if (dataRow.get(0).equals(listRow.get(0))) {
                    if (!dataRow.get(1).isEmpty() && Integer.parseInt(dataRow.get(1)) < 10) {
                        int currentValue = Integer.parseInt(dataRow.get(1));
                        int newValue = currentValue + 1;
                        dataRow.set(1, String.valueOf(newValue));
                        dataRow.set(3, listRow.get(1));


                        dataRow.set(4, listRow.get(2));
                        dataRow.set(5, listRow.get(3));
                        dataRow.set(6, listRow.get(4));





                        double calculatedCalorie = calculatingCalorie(
                            Double.parseDouble(listRow.get(2)),
                            Double.parseDouble(listRow.get(3)),
                            Double.parseDouble(listRow.get(4)));

                        int intCalculatedCalorie = (int) Math.round(calculatedCalorie);

                        dataRow.set(2, String.valueOf(intCalculatedCalorie));



                        found = true;
                        break;

                    } else if (Integer.parseInt(dataRow.get(1)) == 10) {
                        fullNumber += 1;
                        continue;
                    }
    
                } else if (dataRow.get(0).isEmpty()) {
                    dataRow.set(0, listRow.get(0));
                    dataRow.set(1, "1");

                    dataRow.set(4, listRow.get(2));
                    dataRow.set(5, listRow.get(3));
                    dataRow.set(6, listRow.get(4));
                    


                    double calculatedCalorie = calculatingCalorie(
                        Double.parseDouble(listRow.get(2)),
                        Double.parseDouble(listRow.get(3)),
                        Double.parseDouble(listRow.get(4)));

                    int intCalculatedCalorie = (int) Math.round(calculatedCalorie);

                    dataRow.set(2, String.valueOf(intCalculatedCalorie));
                    dataRow.set(3, listRow.get(1));

                    
                    found = true;
                    break;
                } else if (!dataRow.get(0).equals(listRow.get(0)) && Integer.parseInt(dataRow.get(1)) == 10) {
                    fullNumber += 1;
                    continue;
                }

            }
            if (!found) {
                if(fullNumber == 24){
                    FileOutput.writeToFile(outputFileName, "INFO: There is no available place to put " + listRow.get(0), true, true);

                    fill(outputFileName);
                    break;
                }else{
                    FileOutput.writeToFile(outputFileName, "INFO: There is no available place to put " + listRow.get(0), true, true);

                }
            }
            
        }
        return data;
    }
    
    /**
     * Creates a table of products from the given product content.
     *
     * @param productContent The array containing product content.
     * @return The list of products.
     */
    private ArrayList<ArrayList<String>> createProductTable(String[] productContent) {
        ArrayList<ArrayList<String>> listOfProduct = new ArrayList<>();
        
        for (String line : productContent) {
            String[] tokens = line.split("\\s+"); 
            ArrayList<String> array = new ArrayList<>();

            for (String token : tokens) {
                try {
                    int number = Integer.parseInt(token);
                    array.add(String.valueOf(number));
                } catch (NumberFormatException e) {
                    if (array.size() == 1) {
                        array.add(0, array.get(0) + " " + token); 
                        array.remove(1);
                    } else {
                        array.add(token);
                }
            }
        }
        listOfProduct.add(array);
        
    }
    return listOfProduct;    
}

    /** 
     * Creates an empty table for products.
     *
     * @param data The data structure to store product information.
     * @return The empty table for products.
     */
    private ArrayList<ArrayList<String>> createEmptyTable(ArrayList<ArrayList<String>> data) {
        data = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j < 7; j++) {
                row.add("");
            }
            data.add(row);
        }
    return data;
    }
    
    /**
     * Calculates the calorie content of a product.
     *
     * @param protein     The protein content of the product.
     * @param carbohydrate The carbohydrate content of the product.
     * @param fat         The fat content of the product.
     * @return The calculated calorie content.
     */
    private double calculatingCalorie(double protein, double carbohydrate, double fat) {
        double calories;
        calories = protein*4 + carbohydrate*4 + fat * 9;
        return calories;
    }

    /**
     * Handles the case when the machine is full.
     *
     * @param outputFileName The name of the output file.
     * @return -1 indicating the machine is full.
     */
    private int fill(String outputFileName) {
        FileOutput.writeToFile(outputFileName, "INFO: The machine is full!", true, true);

        return -1;
    }

    /**
     * Gets the list of products.
     *
     * @return The list of products.
     */
    public ArrayList<ArrayList<String>> getListOfProduct() {
        return this.listOfProduct;
    }

    /**
     * Writes the product table to the output file.
     *
     * @param listOfProduct  The list of products to write.
     * @param outputFileName The name of the output file.
     */
    public void writeProductTable(ArrayList<ArrayList<String>> listOfProduct , String outputFileName){
        int index = 0;
            FileOutput.writeToFile(outputFileName, "-----Gym Meal Machine-----", true, true);
        for (ArrayList<String> product:listOfProduct) {
            index++;
            if (product.get(1).equals("0") || product.get(1).isEmpty()){
                if (index == 4) {
                    String message = "___(0, 0)___";
                    FileOutput.writeToFile(outputFileName, message, true, true);
                    index = 0;

                }else if (index == 1) {
                    String message = "___(0, 0)___";
                    FileOutput.writeToFile(outputFileName, message, true, false);

                }else {
                    String message = "___(0, 0)___";
                    FileOutput.writeToFile(outputFileName, message, true, false);

                }

            }else {
                if (index == 4) {
                    String message = product.get(0) + "(" + product.get(2) + ", " + product.get(1) + ")___";
                    FileOutput.writeToFile(outputFileName, message, true, true);
                    index = 0;

                }else {
                    String message = product.get(0) + "(" + product.get(2) + ", " + product.get(1) + ")___";
                    FileOutput.writeToFile(outputFileName, message, true, false);
                }

            }
        }
        FileOutput.writeToFile(outputFileName, "----------", true, true);
    }
}
