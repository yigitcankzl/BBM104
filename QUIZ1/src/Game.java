import java.util.ArrayList;

/**
 * The Game class represents a simple dice game where players throw dice and accumulate scores.
 * It processes input data, simulates the game, and writes the results to an output file.
 */
public class Game {
    
    private ArrayList<String> name = new ArrayList<>();
    private int userCount = 0;
    private ArrayList<String> input = new ArrayList<>();

    /**
     * Constructs a Game object with given input data and output filename.
     *
     * @param content       An array of strings containing input data from a file.
     * @param outputFilename The filename where the output of the game will be saved.
     */
    public Game(String[] content, String outputFilename) {
        initializeGame(content, outputFilename);
    }

    /**
     * Initializes the game by processing input data and simulating game rounds.
     *
     * @param content       An array of strings containing input data from a file.
     * @param outputFilename The filename where the output of the game will be saved.
     */
    private void initializeGame(String[] content, String outputFilename) {
        createList(content);
        ArrayList<Integer> score = createEmptyScoreTable(userCount);

        int index = 0; 
        for (String inputLine : input) {   
            inputLine = inputLine.trim();
            index += 1;
            int oneCount = 0;
            int lineScore = 0;

            // Calculate score for each dice throw
            String[] tokens = inputLine.split("-"); 
            for (String token : tokens) {
                if (token.equals("1")) {
                    oneCount += 1;
                } else { 
                    lineScore += Integer.parseInt(token);
                }
            }

            if (oneCount == 0) {
                // Updates score and prints message for regular shots
                score.set(index - 1, score.get(index - 1) + lineScore);
                if (lineScore == 0) {
                    String message4 = (name.get(index - 1) + " skipped the turn and " + name.get(index - 1) + "’s score is " + score.get(index - 1) + ".");
                    FileOutput1.writeToFile(outputFilename, message4, true, true);
                } else {
                    String message3 = (name.get(index - 1) + " threw " + inputLine + " and " + name.get(index - 1) + "’s score is " + score.get(index - 1) + ".");
                    FileOutput1.writeToFile(outputFilename, message3, true, true);
                }

                if (index == userCount) {
                    index = 0;
                } else {
                    continue;
                }

            } else if (oneCount == 1) {
                // Prints the message if one of the dice is one
                String message2 = (name.get(index - 1) + " threw " + inputLine + " and " + name.get(index - 1) + "’s score is " + score.get(index - 1) + ".");
                FileOutput1.writeToFile(outputFilename, message2, true, true);

                if (index == userCount) {
                    index = 0;
                } else {
                    continue;
                }

            } else if (oneCount == 2) {
                // Game over message if two '1's are thrown
                String message1 = (name.get(index - 1) + " threw 1-1. Game over " + name.get(index - 1) + "!");
                FileOutput1.writeToFile(outputFilename, message1, true, true);

                // Remove a player from the game if there are two players left and Two '1's are rolled
                score.remove(index - 1);
                name.remove(index - 1);
                userCount -= 1;
                index -= 1;

                if (index == userCount) {
                    index = 0;
                }

                // Check for winner if only one player remains
                if (userCount == 1) {
                    String message = (name.get(0) + " is the winner of the game with the score of " + score.get(0) + ". Congratulations " + name.get(0) + "!");
                    FileOutput1.writeToFile(outputFilename, message, true, false);
                    break;
                } else {
                    continue;
                }
            }
        }
    }

    /**
     * Processes the input content and populates the name and input lists.
     *
     * @param content An array of strings containing input data from a file.
     */
    private void createList(String[] content) {
        int index = -1;
        for (String line : content) {
            index++;
            if (index == 1) {
                String[] names = line.split(",");
                for (String addingNames : names) {
                    name.add(addingNames);
                }
            } else if (index == 0) {
                userCount = Integer.parseInt(line);
            } else {
                input.add(line);
            }
        }
    }

    /**
     * Creates an empty score table with specified number of players.
     *
     * @param userCount The number of players in the game.
     * @return An ArrayList of integers representing initial scores for each player.
     */
    private ArrayList<Integer> createEmptyScoreTable(int userCount) {
        ArrayList<Integer> scores = new ArrayList<>();
        for (int i = 0; i < userCount; i++) {
            scores.add(0);
        }
        return scores;
    }
}
