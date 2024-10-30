/**
 * The DiceGame class represents a simple dice game.
 * It reads input from a file, initiates a game, and saves the output to another file.
 */
public class DiceGame {
    
    /**
     * The main method of the DiceGame class.
     * It reads input from a file, initiates a game, and saves the output to another file.
     *
     * @param args An array of command-line arguments. The first argument should be the input file path,
     *             and the second argument should be the output file path.
     */
    public static void main(String[] args) {
        // Read content from the input file
        String[] content = FileInput1.readFile(args[0], false, false);
        // Get the output file name from the command-line arguments
        String outputFilename = args[1];
        // Create a new game instance with the input content and output filename
        new Game(content, outputFilename);
    }
}
