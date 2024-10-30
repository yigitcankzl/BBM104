import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a BNF production, consisting of a symbol and a list of productions.
 */
class BNFProduction {
    private String symbol;
    private List<String> productions;

    /**
     * Constructs a BNFProduction object with the given symbol and productions.
     *
     * @param symbol      The symbol representing the production.
     * @param productions The list of production rules.
     */
    public BNFProduction(String symbol, List<String> productions) {
        this.symbol = symbol;
        this.productions = productions;
    }

    /**
     * Gets the symbol of this BNF production.
     *
     * @return The symbol.
     */
    public String getSymbol() {
        return symbol;
    }

    /**
     * Gets the list of productions for this BNF production.
     *
     * @return The list of productions.
     */
    public List<String> getProductions() {
        return productions;
    }
}

/**
 * Parses a file containing BNF (Backus-Naur Form) rules and stores them in a map.
 */
public class BNFParser {
    private Map<String, String> bnfRules;

    /**
     * Constructs a BNFParser object.
     */
    public BNFParser() {
        bnfRules = new HashMap<>();
    }

    /**
     * Parses the input file containing BNF rules and stores them in a map.
     *
     * @param inputFilePath The path to the input file.
     * @return A map containing the parsed BNF rules.
     */
    public Map<String, String> parseInput(String inputFilePath) {
        String[] content = FileIO1.readFile(inputFilePath, false, false);
        for (String line : content) {
            String[] parts = line.split("->");
            bnfRules.put(parts[0], parts[1]);
        }
        return bnfRules;
    }
}
