/**
 * This class represents the main entry point of the BookingSystem program. It reads input from a file, processes commands,
 * and writes output to another file based on command line arguments.
 */
public class BookingSystem {
    /**
     * The main method of the BookingSystem program.
     *
     * @param args Command line arguments. The first argument should be the path to the input file, and the second
     *             argument should be the path to the output file.
     */
    public static void main(String[] args) {
        if (args.length == 2) {
            // Read input file
            String[] content = FileIO1.readFile(args[0], true, true);

            // Check if output file name is valid
            String outputArgs = args[1];
            if (!outputArgs.endsWith("output.txt")) {
                System.out.println("ERROR: This program cannot write to the \"" + args[1] + "\", please check the permissions to write that directory. Program is going to terminate!");
            } else {
                // Process commands
                CommandProcessor commandProcessor = new CommandProcessor(outputArgs, null);
                commandProcessor.processCommand(content);
            }
        } else {
            System.out.println("ERROR: This program works exactly with two command line arguments, the first one is the path to the input file whereas the second one is the path to the output file. Sample usage can be as follows: \"java8 BookingSystem input.txt output.txt\". Program is going to terminate!");
        }
    }
}
