import java.text.DecimalFormat;
import java.util.List;
import java.util.Comparator;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The CommandProcessor class processes commands related to bus operations.
 */
public class CommandProcessor {
    private List<Bus> buses;
    private String outputArgs;
    private DecimalFormat decimalFormat;

    /**
     * Constructor to initialize a CommandProcessor object with output arguments and a list of buses.
     *
     * @param outputArgs The output arguments specifying the file to write to.
     * @param buses      The list of buses.
     */
    public CommandProcessor(String outputArgs, List<Bus> buses) {
        this.outputArgs = outputArgs;
        this.buses = buses;
        this.decimalFormat = new DecimalFormat("0.00");
    }

    /**
     * Processes the given commands.
     *
     * @param content An array of strings representing the commands to be processed.
     */
    public void processCommand(String[] content) {
        List<Bus> buses = new ArrayList<>();
        for (String line : content) {
            if (line.equals(content[0])) {
                FileIO1.writeToFile(outputArgs, "COMMAND: " + line, true, true);
            } else {
                FileIO1.writeToFile(outputArgs, "\nCOMMAND: " + line, true, true);
            }

            String[] items = line.split("\t");
            switch (items[0]) {
                case "INIT_VOYAGE":
                    buses = initVoyage(items, buses, outputArgs);
                    break;

                case "SELL_TICKET":
                    sellTicket(items, buses, outputArgs);
                    break;

                case "REFUND_TICKET":
                    refundTicket(items, buses, outputArgs);
                    break;

                case "PRINT_VOYAGE":
                    printVoyage(items, buses, outputArgs);
                    break;

                case "CANCEL_VOYAGE":
                    cancelVoyage(items, buses, outputArgs);
                    break;

                case "Z_REPORT":
                    generateZReport(items, buses, outputArgs);
                    break;

                default:
                    FileIO1.writeToFile(outputArgs, "ERROR: There is no command namely " + items[0] + "!", true, false);
                    break;
            }
        }
        String lastCommand = content[content.length - 1];
        String[] items = lastCommand.split("\t");
        String[] zReport = {"Z_REPORT"};
        if (!(items[0].equals("Z_REPORT"))) {
            FileIO1.writeToFile(outputArgs, "\n", true, false);
            generateZReport(zReport, buses, outputArgs);
        }
    }





    /**
     * A utility method to initialize a new voyage.
     * 
     * @param items      The array containing information about the voyage.
     * @param buses      The list of buses.
     * @param outputArgs The output arguments specifying the file to write to.
     * @return The updated list of buses after adding the new voyage.
     */
    private static List<Bus> initVoyage(String[] items, List<Bus> buses, String outputArgs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try {
            if ((items.length < 7 || items.length > 9)) {
                FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"INIT_VOYAGE\" command!", true, false);
            } else {
                String busType = items[1];
                int busID = Integer.parseInt(items[2]);
                String busFrom = items[3];
                String busTo = items[4];
                int busRow = Integer.parseInt(items[5]);
                double busPrice = Double.parseDouble(items[6]);

                if (busID <= 0) {
                    FileIO1.writeToFile(outputArgs, "ERROR: " + busID + " is not a positive integer, ID of a voyage must be a positive integer!", true, false);
                } else {
                    if (busRow <= 0) {
                        FileIO1.writeToFile(outputArgs, "ERROR: " + busRow + " is not a positive integer, number of seat rows of a voyage must be a positive integer!", true, false);
                    } else {
                        if (busPrice <= 0) {
                            FileIO1.writeToFile(outputArgs, "ERROR: " + items[6] + " is not a positive number, price must be a positive number!", true, false);
                        } else {
                            boolean equalsStatement = false;
                            for (Bus bus : buses) {
                                if (bus.getBusID() == busID) {
                                    FileIO1.writeToFile(outputArgs, "ERROR: There is already a voyage with ID of " + busID + "!", true, false);
                                    equalsStatement = true;
                                    break;
                                }
                            }
                            if (!equalsStatement) {
                                if (busType.equals("Minibus")) {
                                    Bus minibus = new Minibus(busType, busID, busFrom, busTo, busRow, busPrice, null);
                                    String[] buildDisplay = minibus.firstBuildDisplay(busRow);
                                    minibus.setBusDisplay(buildDisplay);
                                    buses.add(minibus);

                                    FileIO1.writeToFile(outputArgs, "Voyage " + minibus.getBusID() + " was initialized as a minibus (2) voyage from " + minibus.getBusFrom() + " to " + minibus.getBusTo() + " with " + decimalFormat.format(minibus.getBusPrice()) + " TL priced " + minibus.getBusRow() * 2 + " regular seats. Note that minibus tickets are not refundable.", true, false);

                                } else if (busType.equals("Standard")) {
                                    int refundCut = Integer.parseInt(items[7]);
                                    if (0 > refundCut || refundCut > 100) {
                                        FileIO1.writeToFile(outputArgs, "ERROR: " + refundCut + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!", true, false);
                                    } else {
                                        Bus standard = new Standard(busType, busID, busFrom, busTo, busRow, busPrice, refundCut, null);
                                        String[] buildDisplay = standard.firstBuildDisplay(busRow);
                                        standard.setBusDisplay(buildDisplay);
                                        buses.add(standard);

                                        FileIO1.writeToFile(outputArgs, "Voyage " + standard.getBusID() + " was initialized as a standard (2+2) voyage from " + standard.getBusFrom() + " to " + standard.getBusTo() + " with " + decimalFormat.format(standard.getBusPrice()) + " TL priced " + standard.getBusRow() * 4 + " regular seats. Note that refunds will be " + refundCut + "% less than the paid amount.", true, false);

                                    }

                                } else if (busType.equals("Premium")) {
                                    int refundCut = Integer.parseInt(items[7]);
                                    if (0 > refundCut || refundCut > 100) {
                                        FileIO1.writeToFile(outputArgs, "ERROR: " + refundCut + " is not an integer that is in range of [0, 100], refund cut must be an integer that is in range of [0, 100]!", true, false);
                                    } else {
                                        double premiumFee = Double.parseDouble(items[8]);
                                        if (premiumFee < 0) {
                                            FileIO1.writeToFile(outputArgs, "ERROR: " + items[8] + " is not a non-negative integer, premium fee must be a non-negative integer!", true, false);
                                        } else {
                                            Bus premium = new Premium(busType, busID, busFrom, busTo, busRow, busPrice, refundCut, premiumFee, null);
                                            String[] buildDisplay = premium.firstBuildDisplay(busRow);
                                            premium.setBusDisplay(buildDisplay);
                                            buses.add(premium);
                                            (decimalFormat.format((premium.getBusPrice() * (premiumFee + 100)) / 100)).substring(0, (decimalFormat.format((premium.getBusPrice() * (premiumFee + 100)) / 100)).length() - 1);

                                            FileIO1.writeToFile(outputArgs, "Voyage " + premium.getBusID() + " was initialized as a premium (1+2) voyage from " + premium.getBusFrom() + " to " + premium.getBusTo() + " with " + decimalFormat.format(premium.getBusPrice()) + " TL priced " + premium.getBusRow() * 2 + " regular seats and " + (decimalFormat.format((premium.getBusPrice() * (premiumFee + 100)) / 100)) + " TL priced " + premium.getBusRow() + " premium seats. Note that refunds will be " + refundCut + "% less than the paid amount.", true, false);

                                        }
                                    }
                                } else {
                                    FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"INIT_VOYAGE\" command!", true, false);
                                }
                            }
                        }
                    }
                }
            }


        } catch (NumberFormatException e) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"INIT_VOYAGE\" command!", true, false);
        }
        return buses;
    }


        /**
     * A method to sell tickets for a specific voyage.
     * 
     * @param items      The array containing information about the ticket sale.
     * @param buses      The list of buses.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    private static void sellTicket(String[] items, List<Bus> buses, String outputArgs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try {
            if (!(items.length == 3)) {
                FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"SELL_TICKET\" command!", true, false);
            } else {
                boolean findStatement = false;
                boolean sealStatement = false;
                int sellBusNumber = Integer.parseInt(items[1]);
                outerloop:
                for (Bus bus : buses) {
                    if (bus.getBusID() == sellBusNumber) {
                        findStatement = true;
                        String seatNumbers = items[2];
                        String[] seatNumbersList = seatNumbers.split("_");
                        for (String seatNumber : seatNumbersList) {
                            int seat = Integer.parseInt(seatNumber);
                            if (seat < 0) {
                                FileIO1.writeToFile(outputArgs, "ERROR: " + seat + " is not a positive integer, seat number must be a positive integer!", true, false);
                                break outerloop;
                            }

                            if (seat - 1 >= 0 && seat - 1 <= bus.getBusDisplay().length) {
                                if (bus.getBusDisplay()[seat - 1].equals("*")) {
                                    sealStatement = true;
                                } else {
                                    FileIO1.writeToFile(outputArgs, "ERROR: One or more seats already sold!", true, false);
                                    sealStatement = false;
                                    break outerloop;
                                }
                            } else {
                                FileIO1.writeToFile(outputArgs, "ERROR: There is no such a seat!", true, false);
                                break outerloop;
                            }
                        }
                        if (sealStatement) {
                            for (String seatNumber : seatNumbersList) {
                                int seat = Integer.parseInt(seatNumber);
                                String[] updatedBusDisplay = bus.sellDisplay(bus.getBusDisplay(), seat, outputArgs);
                                bus.setBusDisplay(updatedBusDisplay);
                            }

                            bus.setAddRevenue(bus.calculateSoldPrice(seatNumbers));

                            String updatedSeatNumbers = seatNumbers.replace('_', '-');
                            FileIO1.writeToFile(outputArgs, "Seat " + updatedSeatNumbers + " of the Voyage " + sellBusNumber + " from " + bus.getBusFrom() + " to " + bus.getBusTo() + " was successfully sold for " + decimalFormat.format(bus.calculateSoldPrice(seatNumbers)) + " TL.", true, false);
                            break outerloop;
                        }
                    }
                }

                if (!findStatement) {
                    FileIO1.writeToFile(outputArgs, "ERROR: There is no voyage with ID of " + items[1] + "!", true, false);
                }
            }
        } catch (NumberFormatException e) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"SELL_TICKET\" command!", true, false);
        }
    }

        /**
     * A method to refund tickets for a specific voyage.
     * 
     * @param items      The array containing information about the ticket refund.
     * @param buses      The list of buses.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    private static void refundTicket(String[] items, List<Bus> buses, String outputArgs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        try {
            if (!(items.length == 3)) {
                FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"REFUND_TICKET\" command!", true, false);
            } else {
                boolean findStatement = false;
                boolean refundStatement = false;
                int refundBusNumber = Integer.parseInt(items[1]);
                outerloop:
                for (Bus bus : buses) {
                    if (bus.getBusID() == refundBusNumber) {
                        if (bus.getBusType().equals("Minibus")) {
                            FileIO1.writeToFile(outputArgs, "ERROR: Minibus tickets are not refundable!", true, false);
                            findStatement = true;
                            break;
                        } else {
                            findStatement = true;
                            String seatNumbers = items[2];
                            String[] seatNumbersList = seatNumbers.split("_");
                            for (String seatNumber : seatNumbersList) {
                                int seat = Integer.parseInt(seatNumber);
                                if (seat < 0) {
                                    FileIO1.writeToFile(outputArgs, "ERROR: " + seat + " is not a positive integer, seat number must be a positive integer!", true, false);
                                    break outerloop;
                                }

                                if (seat - 1 >= 0 && seat - 1 <= bus.getBusDisplay().length) {
                                    if (bus.getBusDisplay()[seat - 1].equals("X")) {
                                        refundStatement = true;
                                    } else {
                                        FileIO1.writeToFile(outputArgs, "ERROR: One or more seats are already empty!", true, false);
                                        break outerloop;
                                    }
                                } else {
                                    FileIO1.writeToFile(outputArgs, "ERROR: There is no such a seat!", true, false);
                                    break outerloop;
                                }
                            }

                            if (refundStatement) {
                                for (String seatNumber : seatNumbersList) {
                                    int seat = Integer.parseInt(seatNumber);
                                    bus.refundDisplay(bus.getBusDisplay(), seat, outputArgs);
                                }
                                double cancelPrice = bus.calculateCancelPrice(seatNumbers);
                                bus.setSubRevenue(cancelPrice);

                                String updatedSeatNumbers = seatNumbers.replace('_', '-');

                                FileIO1.writeToFile(outputArgs, "Seat " + updatedSeatNumbers + " of the Voyage " + refundBusNumber + " from " + bus.getBusFrom() + " to " + bus.getBusTo() + " was successfully refunded for " + decimalFormat.format(cancelPrice) + " TL.", true, false);
                            }
                        }
                    }
                }

                if (!findStatement) {
                    FileIO1.writeToFile(outputArgs, "ERROR: There is no voyage with ID of " + items[1] + "!", true, false);
                }
            }
        } catch (NumberFormatException e) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"REFUND_TICKET\" command!", true, false);
        }
    }

        /**
     * A method to print information about a specific voyage.
     * 
     * @param items      The array containing information about the voyage to be printed.
     * @param buses      The list of buses.
     * @param outputArgs The output arguments specifying the file to write to.
     */
    private static void printVoyage(String[] items, List<Bus> buses, String outputArgs) {
        try {
            if (!(items.length == 2)) {
                FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"PRINT_VOYAGE\" command!", true, false);
            } else {
                int voyageNumber = Integer.parseInt(items[1]);

                if (voyageNumber < 0) {
                    FileIO1.writeToFile(outputArgs, "ERROR: " + voyageNumber + " is not a positive integer, ID of a voyage must be a positive integer!", true, false);
                } else {
                    boolean findStatement = false;

                    for (Bus bus : buses) {
                        if (bus.getBusID() == voyageNumber) {
                            findStatement = true;
                            voyageInformation(bus, voyageNumber, outputArgs);
                        }
                    }

                    if (!findStatement) {
                        FileIO1.writeToFile(outputArgs, "ERROR: There is no voyage with ID of " + voyageNumber + "!", true, false);
                    }
                }

            }
        } catch (NumberFormatException e) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"PRINT_VOYAGE\" command!", true, false);
        }
    }

    /**
     * Displays information about a specific voyage, including its route, seat layout, and revenue.
     * 
     * @param bus          The Bus object representing the voyage.
     * @param voyageNumber The ID of the voyage to display information about.
     * @param outputArgs   The output arguments specifying the file to write to.
     */
    private static void voyageInformation(Bus bus, int voyageNumber, String outputArgs) {
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        if (bus.getBusID() == voyageNumber) {
            FileIO1.writeToFile(outputArgs, "Voyage " + voyageNumber + "\n" + bus.getBusFrom() + "-" + bus.getBusTo(), true, true);

            if ((bus.getBusType()).equals("Minibus")) {
                ((Minibus) bus).getLastBusDisplay(bus, outputArgs);

            } else if ((bus.getBusType()).equals("Standard")) {
                ((Standard) bus).getLastBusDisplay(bus, outputArgs);

            } else if ((bus.getBusType()).equals("Premium")) {
                ((Premium) bus).getLastBusDisplay(bus, outputArgs);

            }
            FileIO1.writeToFile(outputArgs, ("Revenue: " + decimalFormat.format(bus.getRevenue())), true, false);
        }
    }

        /**
     * Cancels a voyage identified by its ID and removes it from the list of active voyages.
     * 
     * @param items       An array containing command parameters.
     * @param buses       The list of active voyages.
     * @param outputArgs  The output arguments specifying the file to write to.
     */
    private static void cancelVoyage(String[] items, List<Bus> buses, String outputArgs) {
        try {
            if (items.length != 2) {
                FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"CANCEL_VOYAGE\" command!", true, false);

            } else {
                int voyageNumber = Integer.parseInt(items[1]);
                Iterator<Bus> iterator = buses.iterator();
                boolean findStatement = false;

                while (iterator.hasNext()) {

                    Bus bus = iterator.next();
                    if (voyageNumber < 0) {
                        FileIO1.writeToFile(outputArgs, "ERROR: " + voyageNumber + " is not a positive integer, ID of a voyage must be a positive integer!", true, false);
                        findStatement = true;
                    } else {
                        if (bus.getBusID() == voyageNumber) {


                            bus.setSubRevenue(bus.calculateAllPrice(bus));
                            FileIO1.writeToFile(outputArgs, "Voyage " + voyageNumber + " was successfully cancelled!\nVoyage details can be found below:", true, true);
                            voyageInformation(bus, voyageNumber, outputArgs);
                            iterator.remove();
                            findStatement = true;
                            break;
                        }
                    }

                }

                if (!findStatement) {
                    FileIO1.writeToFile(outputArgs, "ERROR: There is no voyage with ID of " + voyageNumber + "!", true, false);

                }
            }
        } catch (NumberFormatException e) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"CANCEL_VOYAGE\" command!", true, false);

        }
    }



    /**
     * Generates a Z report containing information about all active voyages.
     * 
     * @param items       An array containing command parameters (should be empty for this command).
     * @param buses       The list of active voyages.
     * @param outputArgs  The output arguments specifying the file to write to.
     */
    private static void generateZReport(String[] items, List<Bus> buses, String outputArgs) {
        // Sort the list of buses by ID
        Collections.sort(buses, Comparator.comparingInt(Bus::getBusID));

        // Check for erroneous usage of the command
        if (items.length != 1) {
            FileIO1.writeToFile(outputArgs, "ERROR: Erroneous usage of \"Z_REPORT\" command!", true, false);
        } else {
            // Write the header of the Z report
            FileIO1.writeToFile(outputArgs, "Z Report:\n----------------", true, false);
            
            // Check if there are no voyages available
            if (buses.isEmpty()) {
                FileIO1.writeToFile(outputArgs, "\nNo Voyages Available!", true, true);
                FileIO1.writeToFile(outputArgs, "----------------", true, false);
            } else {
                // Iterate over each bus to include its information in the report
                for (Bus bus : buses) {
                    FileIO1.writeToFile(outputArgs, "\n", true, false);
                    voyageInformation(bus, bus.getBusID(), outputArgs);
                    FileIO1.writeToFile(outputArgs, "\n----------------", true, false);
                }
            }
        }
    }

}