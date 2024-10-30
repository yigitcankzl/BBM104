import java.util.Map;

/**
 * Entry point class for running BNF (Backus-Naur Form) parsing and string generation.
 */
public class BNF {
    /**
     * Main method to parse BNF input and generate strings based on rules.
     *
     * @param args Command-line arguments:
     *             args[0] - Path to the input file containing BNF rules.
     *             args[1] - Path to the output file where generated strings will be written.
     */
    public static void main(String[] args) {
        BNFParser parser = new BNFParser();
        Map<String, String> bnfRules = parser.parseInput(args[0]);

        BNFWring writer = new BNFWring(bnfRules);
        writer.writer(args[1]);
    }
}
