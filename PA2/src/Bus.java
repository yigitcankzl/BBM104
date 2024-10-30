
/**
 * The Bus class represents a bus entity with its properties and methods.
 */
public class Bus {
    private String busType;
    private int busID;
    private String busFrom;
    private String busTo;
    private int busRow;
    private double busPrice;

    private String[] busDisplay;
    private double revenue = 0.0f;

    /**
     * Constructor to initialize a Bus object with provided details.
     *
     * @param busType  The type of the bus.
     * @param busID    The ID of the bus.
     * @param busFrom  The starting point of the bus journey.
     * @param busTo    The destination of the bus journey.
     * @param busRow   The number of rows in the bus.
     * @param busPrice The price per seat of the bus.
     */
    public Bus(String busType, int busID, String busFrom, String busTo, int busRow, double busPrice) {
        this.busType = busType;
        this.busID = busID;
        this.busFrom = busFrom;
        this.busTo = busTo;
        this.busRow = busRow;
        this.busPrice = busPrice;
    }

    // Getters and setters for bus properties...

    /**
     * Getter for the type of the bus.
     *
     * @return The type of the bus.
     */
    public String getBusType() {
        return busType;
    }

    /**
     * Getter for the ID of the bus.
     *
     * @return The ID of the bus.
     */
    public int getBusID() {
        return busID;
    }

    /**
     * Getter for the starting point of the bus journey.
     *
     * @return The starting point of the bus journey.
     */
    public String getBusFrom() {
        return busFrom;
    }

    /**
     * Getter for the destination of the bus journey.
     *
     * @return The destination of the bus journey.
     */
    public String getBusTo() {
        return busTo;
    }

    /**
     * Getter for the number of rows in the bus.
     *
     * @return The number of rows in the bus.
     */
    public int getBusRow() {
        return busRow;
    }

    /**
     * Getter for the price per seat of the bus.
     *
     * @return The price per seat of the bus.
     */
    public double getBusPrice() {
        return busPrice;
    }

    /**
     * Getter for the total revenue generated from selling seats on the bus.
     *
     * @return The total revenue generated.
     */
    public double getRevenue() {
        return revenue;
    }

    /**
     * Method to add revenue generated from selling seats on the bus.
     *
     * @param addPrice The amount to be added to the revenue.
     */
    public void setAddRevenue(double addPrice) {
        revenue = revenue + addPrice;
    }

    /**
     * Method to subtract revenue, typically used for refunds.
     *
     * @param subPrice The amount to be subtracted from the revenue.
     */
    public void setSubRevenue(double subPrice) {
        revenue = revenue - subPrice;
    }


    /**
     * Calculates the size of each row based on the bus type.
     *
     * @return The size of each row.
     */
    private int getRowSize() {
        if (getBusType().equals("Minibus")) {
            return 2;
        } else if (getBusType().equals("Standard")) {
            return 4;
        } else {
            return 3;
        }
    }

    /**
     * Builds the initial display of the bus with all seats marked as available.
     *
     * @param busRow The number of rows in the bus.
     * @return The initial display of the bus.
     */
    public String[] firstBuildDisplay(int busRow) {
        String[] busDisplay = new String[busRow * getRowSize()];

        for (int i = 0; i < (busRow * getRowSize()); i++) {
            busDisplay[i] = "*";
        }
        return busDisplay;
    }

    /**
     * Marks a seat as sold in the bus display.
     *
     * @param busDisplay The current display of the bus.
     * @param index      The index of the seat to be marked as sold.
     * @param outputArgs The output arguments (not used in this method).
     * @return The updated bus display with the sold seat marked.
     */
    public String[] sellDisplay(String[] busDisplay, int index, String outputArgs) {
        if (busDisplay != null) {
            busDisplay[index - 1] = "X";
            return busDisplay;
        } else {
            return null;
        }
    }

    /**
     * Marks a seat as available (refunded) in the bus display.
     *
     * @param busDisplay The current display of the bus.
     * @param index      The index of the seat to be marked as available.
     * @param outputArgs The output arguments (not used in this method).
     * @return The updated bus display with the refunded seat marked.
     */
    public String[] refundDisplay(String[] busDisplay, int index, String outputArgs) {
        if (busDisplay != null) {
            busDisplay[index - 1] = "*";
            return busDisplay;
        } else {
            return null;
        }
    }

    /**
     * Getter for the current display of the bus.
     *
     * @return The current display of the bus.
     */
    public String[] getBusDisplay() {
        return busDisplay;
    }

    /**
     * Setter for the display of the bus.
     *
     * @param busDisplay The display of the bus to be set.
     */
    public void setBusDisplay(String[] busDisplay) {
        this.busDisplay = busDisplay;
    }



    /**
     * Calculates the total price for the seats sold.
     *
     * @param seatNumbers The numbers of the sold seats.
     * @return The total price for the sold seats.
     */
    public double calculateSoldPrice(String seatNumbers) {
        return 0.0f; // Placeholder implementation, actual implementation depends on business logic
    }

    /**
     * Calculates the total price for the refunded seats.
     *
     * @param seatNumbers The numbers of the refunded seats.
     * @return The total price for the refunded seats.
     */
    public double calculateRefundPrice(String seatNumbers) {
        return 0.0f; // Placeholder implementation, actual implementation depends on business logic
    }

    /**
     * Calculates the total price for canceling seats.
     *
     * @param seatNumbers The numbers of the canceled seats.
     * @return The total price for canceling the seats.
     */
    public double calculateCancelPrice(String seatNumbers) {
        return 0.0f; // Placeholder implementation, actual implementation depends on business logic
    }

    /**
     * Calculates the total price for all seats on the bus, based on its current display.
     *
     * @param bus The bus object for which the total price is calculated.
     * @return The total price for all seats on the bus.
     */
    public double calculateAllPrice(Bus bus) {
        int number = 0;
        for (String seat : bus.getBusDisplay()) {
            if (seat.equals("X")) {
                number += 1;
            }
        }
        return number * bus.getBusPrice();
    }

}

/**
 * The Minibus class represents a specific type of bus, inheriting properties and methods from the Bus class.
 */
class Minibus extends Bus {

    /**
     * Constructor to initialize a Minibus object with provided details.
     *
     * @param busType    The type of the bus.
     * @param busID      The ID of the bus.
     * @param busFrom    The starting point of the bus journey.
     * @param busTo      The destination of the bus journey.
     * @param busRow     The number of rows in the bus.
     * @param busPrice   The price per seat of the bus.
     * @param busDisplay The initial display of the bus.
     */
    public Minibus(String busType, int busID, String busFrom, String busTo, int busRow, double busPrice, String[] busDisplay) {
        super(busType, busID, busFrom, busTo, busRow, busPrice);
    }

    /**
     * Writes the last bus display to a file.
     *
     * @param bus        The bus object whose display is to be written.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    public void getLastBusDisplay(Bus bus, String outputArgs) {

        String lastBusDisplay = "";
        if (bus.getBusDisplay() != null) {
            for (int i = 0; i < bus.getBusDisplay().length; i++) {
                if ((i + 1) % 2 == 0 && i != bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                    lastBusDisplay += ("\n");
                } else if (i == bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                } else {
                    lastBusDisplay += (bus.getBusDisplay()[i] + " ");
                }
            }
        }
        FileIO1.writeToFile(outputArgs, lastBusDisplay, true, true);
    }

    /**
     * Calculates the total price for the sold seats in the Minibus.
     *
     * @param seatNumbers The numbers of the sold seats.
     * @return The total price for the sold seats.
     */
    @Override
    public double calculateSoldPrice(String seatNumbers) {
        double totalPrice = 0;
        String[] seatNumbersList = seatNumbers.split("_");
        for (String seatNumber : seatNumbersList) {
            totalPrice += getBusPrice();
        }
        return totalPrice;
    }
}





/**
 * The Standard class represents a specific type of bus, inheriting properties and methods from the Bus class.
 */
class Standard extends Bus {
    private int refundCut;

    /**
     * Constructor to initialize a Standard object with provided details.
     *
     * @param busType    The type of the bus.
     * @param busID      The ID of the bus.
     * @param busFrom    The starting point of the bus journey.
     * @param busTo      The destination of the bus journey.
     * @param busRow     The number of rows in the bus.
     * @param busPrice   The price per seat of the bus.
     * @param refundCut  The refund cut percentage for canceled seats.
     * @param busDisplay The initial display of the bus.
     */
    public Standard(String busType, int busID, String busFrom, String busTo, int busRow, double busPrice, int refundCut, String[] busDisplay) {
        super(busType, busID, busFrom, busTo, busRow, busPrice);
        this.refundCut = refundCut;
    }

    /**
     * Getter for the refund cut percentage for canceled seats.
     *
     * @return The refund cut percentage.
     */
    public int getRefundCut() {
        return refundCut;
    }

    /**
     * Writes the last bus display to a file.
     *
     * @param bus        The bus object whose display is to be written.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    public void getLastBusDisplay(Bus bus, String outputArgs) {
        String lastBusDisplay = "";
        if (bus.getBusDisplay() != null) {
            for (int i = 0; i < bus.getBusDisplay().length; i++) {
                if ((i + 1) % 4 == 0 && i != bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                    lastBusDisplay += ("\n");
                } else if (((i + 1) % 2 == 0 && i != bus.getBusDisplay().length - 1)) {
                    lastBusDisplay += (bus.getBusDisplay()[i] + " ");
                    lastBusDisplay += ("| ");
                } else if (i == bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                } else {
                    lastBusDisplay += (bus.getBusDisplay()[i] + " ");
                }
            }
        }
        FileIO1.writeToFile(outputArgs, lastBusDisplay, true, true);
    }

    /**
     * Calculates the total price for the sold seats in the Standard bus.
     *
     * @param seatNumbers The numbers of the sold seats.
     * @return The total price for the sold seats.
     */
    @Override
    public double calculateSoldPrice(String seatNumbers) {
        double totalPrice = 0;
        String[] seatNumbersList = seatNumbers.split("_");
        for (String seatNumber : seatNumbersList) {
            totalPrice += getBusPrice();
        }
        return totalPrice;
    }

    /**
     * Calculates the total price for canceling seats in the Standard bus.
     *
     * @param seatNumbers The numbers of the canceled seats.
     * @return The total price for canceling the seats.
     */
    @Override
    public double calculateCancelPrice(String seatNumbers) {
        String[] seatNumbersList = seatNumbers.split("_");
        double totalPrice = 0;
        for (String seatNumber : seatNumbersList) {
            double busPrice1 = getBusPrice();
            double refundCut1 = getRefundCut();
            totalPrice += (busPrice1 * ((100 - refundCut1) / 100));
        }
        return totalPrice;
    }
}


/**
 * The Premium class represents a specific type of bus, inheriting properties and methods from the Bus class.
 */
class Premium extends Bus {
    private int refundCut;
    private double premiumFee;

    /**
     * Constructor to initialize a Premium object with provided details.
     *
     * @param busType      The type of the bus.
     * @param busID        The ID of the bus.
     * @param busFrom      The starting point of the bus journey.
     * @param busTo        The destination of the bus journey.
     * @param busRow       The number of rows in the bus.
     * @param busPrice     The price per seat of the bus.
     * @param refundCut    The refund cut percentage for canceled seats.
     * @param premiumFee   The premium fee percentage for premium seats.
     * @param busDisplay   The initial display of the bus.
     */
    public Premium(String busType, int busID, String busFrom, String busTo, int busRow, double busPrice, int refundCut, double premiumFee, String[] busDisplay) {
        super(busType, busID, busFrom, busTo, busRow, busPrice);
        this.refundCut = refundCut;
        this.premiumFee = premiumFee;
    }

    /**
     * Getter for the refund cut percentage for canceled seats.
     *
     * @return The refund cut percentage.
     */
    public int getRefundCut() {
        return refundCut;
    }

    /**
     * Getter for the premium fee percentage for premium seats.
     *
     * @return The premium fee percentage.
     */
    public double getPremiumFee() {
        return premiumFee;
    }

    /**
     * Writes the last bus display to a file.
     *
     * @param bus        The bus object whose display is to be written.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    public void getLastBusDisplay(Bus bus, String outputArgs) {
        String lastBusDisplay = "";
        if (bus.getBusDisplay() != null) {
            for (int i = 0; i < bus.getBusDisplay().length; i++) {
                if ((i + 1) % 3 == 0 && i != bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                    lastBusDisplay += ("\n");
                } else if ((i) % 3 == 0 && i != bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i] + " ");
                    lastBusDisplay += ("| ");
                } else if (i == bus.getBusDisplay().length - 1) {
                    lastBusDisplay += (bus.getBusDisplay()[i]);
                } else {
                    lastBusDisplay += (bus.getBusDisplay()[i] + " ");
                }
            }
        }
        FileIO1.writeToFile(outputArgs, lastBusDisplay, true, true);
    }

    /**
     * Calculates the total price for the sold seats in the Premium bus.
     *
     * @param seatNumbers The numbers of the sold seats.
     * @return The total price for the sold seats.
     */
    @Override
    public double calculateSoldPrice(String seatNumbers) {
        double totalPrice = 0;
        String[] seatNumbersList = seatNumbers.split("_");
        for (String seatNumber : seatNumbersList) {
            int seat = Integer.parseInt(seatNumber);
            if ((seat - 1) % 3 == 0) {
                totalPrice += ((getBusPrice() * (getPremiumFee() + 100)) / 100);
            } else {
                totalPrice += getBusPrice();
            }
        }
        return totalPrice;
    }

    /**
     * Calculates the total price for canceling seats in the Premium bus.
     *
     * @param seatNumbers The numbers of the canceled seats.
     * @return The total price for canceling the seats.
     */
    @Override
    public double calculateCancelPrice(String seatNumbers) {
        String[] seatNumbersList = seatNumbers.split("_");
        double totalPrice = 0;
        for (String seatNumber : seatNumbersList) {
            double busPrice1 = getBusPrice();
            double refundCut1 = getRefundCut();
            double premiumFee1 = getPremiumFee();
            int seat = Integer.parseInt(seatNumber);
            if ((seat - 1) % 3 == 0) {
                double price = (((busPrice1 * (premiumFee1 + 100)) / 100) * ((100 - refundCut1) / 100));
                totalPrice += price;
            } else {
                double price = (busPrice1 * ((100 - refundCut1) / 100));
                totalPrice += price;
            }
        }
        return totalPrice;
    }

    /**
     * Calculates the total price for all seats on the Premium bus.
     *
     * @param bus The bus object for which the total price is calculated.
     * @return The total price for all seats on the bus.
     */
    @Override
    public double calculateAllPrice(Bus bus) {
        double number = 0;
        int index = 1;
        for (String seat : bus.getBusDisplay()) {
            double premiumFee1 = getPremiumFee();
            if ((index - 1) % 3 == 0) {
                if (seat.equals("X")) {
                    number += ((100 + premiumFee1) / 100);
                }
            } else {
                if (seat.equals("X")) {
                    number += 1;
                }
            }
            index += 1;
        }
        return number * getBusPrice();
    }
}
