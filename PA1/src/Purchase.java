import java.util.ArrayList;

/**
 * The Purchase class represents a purchase operation.
 * It allows users to buy products based on their purchase content.
 */
public class Purchase {

        /**
     * Constructs a Purchase object with the specified purchase content, list of products, and output file name.
     *
     * @param purchaseContent The array containing purchase content.
     * @param listOfProduct   The list of available products.
     * @param outputFileName  The name of the output file.
     */
    public Purchase(String[] purchaseContent, ArrayList<ArrayList<String>> listOfProduct, String outputFileName) {
        ArrayList<ArrayList<String>> listOfPurchase = createPurchaseTable(purchaseContent);
        
        buy(listOfPurchase, listOfProduct, outputFileName);
    }

    /**
     * Handles the purchasing process.
     *
     * @param purchaseList    The list of purchases to process.
     * @param listOfProduct   The list of available products.
     * @param outputFileName  The name of the output file.
     */
    private void buy(ArrayList<ArrayList<String>> purchaseList, ArrayList<ArrayList<String>> listOfProduct, String outputFileName) {
        // Iterating over each purchase 
        for (ArrayList<String> purchase:purchaseList){
            String acceptedMoney = purchase.get(4);

            if (purchase.get(1).equals("PROTEIN")){
                int type = 4;
                buyProCarbFatCal(purchase, listOfProduct, outputFileName, type, acceptedMoney);

            } else if (purchase.get(1).equals("CARB")){
                int type = 5;
                buyProCarbFatCal(purchase, listOfProduct, outputFileName, type, acceptedMoney);

            } else if (purchase.get(1).equals("FAT")) {
                int type =6;
                buyProCarbFatCal(purchase, listOfProduct, outputFileName, type, acceptedMoney);
    
            } else if (purchase.get(1).equals("NUMBER")){

                Float purchasePrice = Float.parseFloat(purchase.get(0));
                int index = -1;

                for (ArrayList<String> product:listOfProduct){
                    index += 1;
                    Float productPrice = 0.0f;

                    if (product.get(3).isEmpty()){
                        productPrice = 0.0f;

                    }else {
                        productPrice = Float.parseFloat(product.get(3));
                    }

                    int indexOfPurchase = Integer.parseInt(purchase.get(2));


                    if (indexOfPurchase > 23) {
                        FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3), true, true);
                        if (acceptedMoney.equals("T")){
                            FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
                        }
                        FileOutput.writeToFile(outputFileName, "INFO: Number cannot be accepted. Please try again with another number.", true, true);

                        Integer purchasePrice1 = (int) Math.round(purchasePrice);
                        FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + purchasePrice1+" TL" , true, true);

                        break;
                    }


                    if (indexOfPurchase == index) {
                        if (product.get(1).equals("0") || product.get(1).isEmpty()){
                            int remainingPrice = (int) Math.round(purchasePrice);            
                            emptySlot(purchase, remainingPrice, outputFileName, acceptedMoney);

                            break;
                        }
                        if (productPrice < purchasePrice) {

                            FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3), true, true);
                            if (acceptedMoney.equals("T")){
                                FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
                            }
                            FileOutput.writeToFile(outputFileName, "PURCHASE: You have bought one " + product.get(0), true, true);

                            int remainingPrice = (int) Math.round(purchasePrice-productPrice);
                            FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + remainingPrice+" TL", true, true);

                            int count = Integer.parseInt(product.get(1));
                            count -= 1;
                            product.set(1, String.valueOf(count));
                            
                            break;
                        }else {
                            int remainingPrice = (int) Math.round(purchasePrice);            

                            insufficientMoney(purchase, remainingPrice, outputFileName, acceptedMoney);    

                            break;
                        }

                    }else if (index == 23){
                        int remainingPrice = (int) Math.round(purchasePrice);            
                        notFoundProduct(purchase, remainingPrice, outputFileName, acceptedMoney);
                        break;
                    }

                }


            } else if (purchase.get(1).equals("CALORIE")) {
                int type = 2;
                buyProCarbFatCal(purchase, listOfProduct, outputFileName, type, acceptedMoney);
            
            }
        }
    }


    /**
     * Creates a table of purchases from the given purchase content.
     *
     * @param purchaseContent The array containing purchase content.
     * @return The list of purchases.
     */
    private ArrayList<ArrayList<String>> createPurchaseTable(String[] purchaseContent) {
        ArrayList<ArrayList<String>> listOfPurchase = new ArrayList<>();
        for (String line : purchaseContent) {
            String[] tokens = line.split("\\s+"); 
            ArrayList<String> array = new ArrayList<>();
            int index = -1;
            boolean acceptedMoney = false;
            for (String token : tokens) {
                if (token.equals("CASH")){
                    continue;
                }else if (array.isEmpty()){
                    array.add(token);
                    index += 1;

                }else{
                    
                    try {
                        int number = Integer.parseInt(token);

                        if (index == 0 || index == 1) {
                            int numberBefore = Integer.parseInt(array.get(index));
                            if (number == 1 || number == 5 || number == 10 || number == 20 || number == 50 || number == 100 || number == 200) {
                                array.set(index, String.valueOf(number + numberBefore));
                            }else {
                                acceptedMoney = true;
                            }   
                        }
                    } catch (NumberFormatException e) {
                        array.add(token);
                        index += 1;

                }
            }   
        }
        array.add(line);
        if(acceptedMoney) {
            array.add("T");

        }else {
            array.add("F");

        }
        listOfPurchase.add(array);

    }
    return listOfPurchase;    
}

    /**
     * Handles the purchasing process based on the type of product.
     *
     * @param purchase       The purchase information.
     * @param listOfProduct  The list of available products.
     * @param outputFileName The name of the output file.
     * @param type           The type of product (PROTEIN, CARB, FAT, CALORIE).
     * @param acceptedMoney   Indicates whether the money is accepted.
     */
    private void buyProCarbFatCal(ArrayList<String> purchase, ArrayList<ArrayList<String>> listOfProduct,String outputFileName,int type, String acceptedMoney) {
        Float purchasePrice = Float.parseFloat(purchase.get(0));
        int index = -1;
        for (ArrayList<String> product:listOfProduct){
            index++;
            if (product.get(1).equals("0") || product.get(1).isEmpty()) {
                if (index == 23) {
                    int remainingPrice = (int) Math.round(purchasePrice);            
                    notFoundProduct(purchase, remainingPrice, outputFileName,acceptedMoney);
                    break;
                }else {
                    continue;
                }
            }else {
                if ((Float.parseFloat(product.get(type)) - 5 <= Float.parseFloat(purchase.get(2))) && (Float.parseFloat(purchase.get(2)) <= Float.parseFloat(product.get(type)) + 5)) {
                    Float productPrice = Float.parseFloat(product.get(3));
                    if (productPrice <= purchasePrice) {
                        FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3), true, true);
                        if (acceptedMoney.equals("T")){
                            FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
                        }
                        FileOutput.writeToFile(outputFileName, "PURCHASE: You have bought one " + product.get(0), true, true);

                        int remainingPrice = (int) Math.round(purchasePrice-productPrice);
                        FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + remainingPrice +" TL", true, true);

                        int count = Integer.parseInt(product.get(1));
                        count -= 1;
                        product.set(1, String.valueOf(count));

                        break;
                    } else{                
                        int remainingPrice = (int) Math.round(purchasePrice);            
                        insufficientMoney(purchase, remainingPrice, outputFileName, acceptedMoney);    
                        break;
                    }
                    
                }else if (index == 23) {
                    int remainingPrice = (int) Math.round(purchasePrice);            
                    notFoundProduct(purchase, remainingPrice, outputFileName, acceptedMoney);
                    break;
                }else {
                    continue;
                }
            }
        }
    }

    /**
     * Handles the case when the product is not found.
     *
     * @param purchase       The purchase information.
     * @param remainingPrice The remaining price after the purchase attempt.
     * @param outputFileName The name of the output file.
     * @param acceptedMoney   Indicates whether the money is accepted.
     * @return -1 indicating product not found.
     */
    private int notFoundProduct(ArrayList<String> purchase,int remainingPrice,String outputFileName,String acceptedMoney) {
        FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3), true, true);
        if (acceptedMoney.equals("T")){
            FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
        }
        FileOutput.writeToFile(outputFileName, "INFO: Product not found, your money will be returned.", true, true);
        FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + remainingPrice+" TL", true, true);
        return -1;
    }


    /**
     * Handles the case when there is insufficient money to make the purchase.
     *
     * @param purchase       The purchase information.
     * @param remainingPrice The remaining price after the purchase attempt.
     * @param outputFileName The name of the output file.
     * @param acceptedMoney   Indicates whether the money is accepted.
     * @return -1 indicating insufficient money.
     */
    private int insufficientMoney(ArrayList<String> purchase,int remainingPrice,String outputFileName, String acceptedMoney) {
        FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3), true, true);
        if (acceptedMoney.equals("T")){
            FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
        }
        FileOutput.writeToFile(outputFileName, "INFO: Insufficient money, try again with more money.", true, true);
        FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + remainingPrice+" TL", true, true);
        return -1;
    }


    /**
     * Handles the case when the product slot is empty.
     *
     * @param purchase       The purchase information.
     * @param remainingPrice The remaining price after the purchase attempt.
     * @param outputFileName The name of the output file.
     * @param acceptedMoney   Indicates whether the money is accepted.
     * @return -1 indicating an empty slot.
     */
    private int emptySlot(ArrayList<String> purchase,int remainingPrice,String outputFileName,String acceptedMoney) {
        FileOutput.writeToFile(outputFileName, "INPUT: " + purchase.get(3) , true, true);
        if (acceptedMoney.equals("T")){
            FileOutput.writeToFile(outputFileName, "INFO: Invalid money. Your money will be return.", true, true);
        }
        FileOutput.writeToFile(outputFileName, "INFO: This slot is empty, your money will be returned.", true, true);
        FileOutput.writeToFile(outputFileName, "RETURN: Returning your change: " + remainingPrice+" TL", true, true);
        return -1;
    }
    



}