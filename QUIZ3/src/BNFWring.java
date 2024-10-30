import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Represents a class for generating strings based on given BNF (Backus-Naur Form) rules.
 */
public class BNFWring {
    private Map<String, String> bnfRules;

    /**
     * Constructs a BNFWring object with the given BNF rules.
     *
     * @param bnfRules The BNF rules to be used for string generation.
     */
    public BNFWring(Map<String, String> bnfRules) {
        this.bnfRules = bnfRules;
    }

    /**
     * Gets the BNF rules associated with this BNFWring object.
     *
     * @return The BNF rules.
     */
    public Map<String, String> getBNFRules() {
        return bnfRules;
    }

    /**
     * Generates a string based on BNF rules and writes it to a file.
     *
     * @param outputFilePath The path to the output file.
     */
    public void writer(String outputFilePath) {
        for (Map.Entry<String, String> entry : bnfRules.entrySet()) {
            if (entry.getKey().equals("S")) {
                String result = processSymbol(entry.getKey(), entry.getValue());
                FileIO1.writeToFile(outputFilePath, "(" + result + ")", false, false);
            }
        }
    }

    /**
     * Processes a BNF symbol and its rule to generate a string recursively.
     *
     * @param symbol The BNF symbol to be processed.
     * @param rule   The BNF rule associated with the symbol.
     * @return The generated string based on the BNF rule.
     */
    private String processSymbol(String symbol, String rule) {
        StringBuilder stringBuilder = new StringBuilder();

        if (containsSymbol(symbol)) {
            List<String> parts = getBNFRuleParts(rule);

            for (int i = 0; i < parts.size(); i++) {
                String part = parts.get(i);
                if (bnfRules.containsKey(part)) {
                    String replacement = bnfRules.get(part);

                    if (containsSymbol(replacement)) {
                        replacement = processSymbol(part, replacement);
                        parts.set(i, "(" + replacement + ")");
                    } else {
                        parts.set(i, "(" + replacement + ")");
                    }
                }
            }

            for (String str : parts) {
                stringBuilder.append(str);
            }

            return stringBuilder.toString();
        } else {
            return symbol;
        }
    }

    /**
     * Splits a BNF rule into individual parts.
     *
     * @param rule The BNF rule to be split.
     * @return A list containing individual parts of the BNF rule.
     */
    public List<String> getBNFRuleParts(String rule) {
        List<String> parts = new ArrayList<>();
        String[] splitParts = rule.split("");
        for (String part : splitParts) {
            parts.add(part.trim());
        }
        return parts;
    }

    /**
     * Checks if a given string contains a BNF symbol.
     *
     * @param part The string to be checked.
     * @return True if the string contains a BNF symbol, false otherwise.
     */
    private boolean containsSymbol(String part) {
        for (String symbol : bnfRules.keySet()) {
            if (part.contains(symbol)) {
                return true;
            }
        }
        return false;
    }
}
