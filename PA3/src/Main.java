import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;


/**
 * Main class for the HU-Load game.
 * 
 * This class serves as the entry point for the HU-Load game application.
 * It extends the JavaFX Application class and provides the main method for
 * launching the game.
 */
public class Main extends Application {
    // Constants
    private static final int[] ELEMENT_WEIGHTS = {500000, 16000, 16000, 16000, 8000, 5000, 20000, 6660, 2500, 100, 205, 5, 1};
    private static double FUEL_CONSUMPTION_PER_MOVE = 100;

    // Arrays and Lists
    String[] listOfInsideBlocksName = {"soil", "lava", "obstacle", "ironium", "bronzium", "silverium", "goldium", "platinium", "einsteinium", "emerald", "ruby", "diamond", "amazonite"};
    ArrayList<Blocks> elements = new ArrayList<>();
    ArrayList<ImageView> inside = new ArrayList<>();

    // Images
    Image emptyImage = new Image("assets\\underground\\empty_15.png");

    // Game variables
    int haul = 0;
    int money = 0;
    double fuel = 10000;

    // UI Text elements
    private Text haulText;
    private Text moneyText;
    private Text fuelText;

    /**
     * Main entry point of the application.
     *
     * @param primaryStage the primary stage
     * @throws Exception if any error occurs during application start
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("HU-Load");
        
        //Load Images
        Image obstacleImage = new Image("assets\\underground\\obstacle_01.png"); 
        Image topImage = new Image("assets\\underground\\top_01.png");

        Image valuableAmazoniteImage = new Image("assets\\underground\\valuable_amazonite.png");
        Image valuableBronziumImage = new Image("assets\\underground\\valuable_bronzium.png");
        Image valuableDiamondImage =  new Image("assets\\underground\\valuable_diamond.png");
        Image valuableEinsteiniumImage = new Image("assets\\underground\\valuable_einsteinium.png");
        Image valuableEmeraldImage = new Image("assets\\underground\\valuable_emerald.png");
        Image valuableGoldiumImage = new Image("assets\\underground\\valuable_goldium.png");
        Image valuableIroniumImage = new Image("assets\\underground\\valuable_ironium.png");
        Image valuablePlatiniumImage = new Image("assets\\underground\\valuable_platinum.png");
        Image valuableRubyImage = new Image("assets\\underground\\valuable_ruby.png");
        Image valuableSilveriumImage = new Image("assets\\underground\\valuable_silverium.png");

        Image soilImage = new Image("assets\\underground\\soil_01.png");
        Image lavaImage = new Image("assets\\underground\\lava_01.png");

        Image drillLeftImage = new Image("assets\\drill\\drill_01.png");
        Image drillRightImage = new Image("assets\\drill\\drill_55.png");
        Image drillDownImage = new Image("assets\\drill\\drill_42.png");
        Image drillUpImage = new Image("assets\\drill\\drill_24.png");

        // Create a root pane to hold all graphical elements
        StackPane root  = new StackPane();

        // Lists to hold graphical elements
        ArrayList<ImageView> obstacles = new ArrayList<>();
        ArrayList<ImageView> topEdges = new ArrayList<>();
        ArrayList<ImageView> inside = new ArrayList<>();

        // Create obstacles
        for (int i = 0; i < 16; i++) {
            ImageView obstacleImageView = new ImageView(obstacleImage);

            obstacleImageView.setTranslateX(-375+(50*i));
            obstacleImageView.setTranslateY(400);

            Obstacle obstacle = new Obstacle("obstacle",-375+(50*i), 400, obstacleImageView);
            elements.add(obstacle);
            obstacles.add(obstacle.getImagaView());
        }

        // Create obstacles     
        for (int i = 0; i < 11; i++) {
            ImageView obstacleImageView = new ImageView(obstacleImage);

            obstacleImageView.setTranslateX(-375);
            obstacleImageView.setTranslateY(350-50*i);

            Obstacle obstacle = new Obstacle("obstacle",-375, 350-50*i,obstacleImageView);
            elements.add(obstacle);

            obstacles.add(obstacle.getImagaView());
        }

        // Create obstacles
        for (int i = 0; i < 11; i++) {
            ImageView obstacleImageView = new ImageView(obstacleImage);

            obstacleImageView.setTranslateX(375);
            obstacleImageView.setTranslateY(350-50*i);

            Obstacle obstacle = new Obstacle("obstacle",375,350-50*i, obstacleImageView);
            elements.add(obstacle);

            obstacles.add(obstacle.getImagaView());
        }
        
        // Create top edges
        for (int i = 0; i < 16; i++) {
            ImageView topImageView = new ImageView(topImage);

            topImageView.setTranslateX(-375+(50*i));
            topImageView.setTranslateY(-200);

            
            Top top = new Top("top",-375+50*i,-200,  topImageView);
            elements.add(top);

            topEdges.add(top.getImagaView());
       }
       
       
        // Set the background color and fill settings
        Background background = new Background(new BackgroundFill(Color.BLUE, null, null));

        // Create a brown rectangle representing the ground
        Rectangle Rectangle = new Rectangle(800, 650, Color.BROWN);
        Rectangle.setTranslateX(0);
        Rectangle.setTranslateY(100);

        // Create a black rectangle representing the upper section
        Rectangle upperRectangle = new Rectangle(800, 50, Color.BLACK);
        upperRectangle.setTranslateX(0);
        upperRectangle.setTranslateY(-400);

        // Create text to display in the upper section
        Text upperName = new Text("Yiğitcan Kızıl");
        upperName.setFont(Font.font(30));
        upperName.setFill(Color.RED);
        upperName.setTranslateX(0);
        upperName.setTranslateY(-400);

        // Add rectangles, text, obstacles, and top edges to the root pane
        root.getChildren().add(Rectangle);
        root.getChildren().add(upperRectangle);
        root.setBackground(background);
        root.getChildren().addAll(obstacles);
        root.getChildren().addAll(topEdges);

        /**
         * Generate random coordinates for elements on the game screen and initialize them.
         */
        Random random = new Random();
        int x = -1;
        int y = 0;
        for (int i = 0; i < 154; i++) {
            if (x == 13) {
                y++;
                x = -1;
            }
            x++;
            String selectedElement = selectWeightedRandomly(listOfInsideBlocksName, ELEMENT_WEIGHTS, random);
            switch (selectedElement) {
                case "amazonite":
                    ImageView valuableAmazoniteImageView = new ImageView(valuableAmazoniteImage);
                    valuableAmazoniteImageView.setTranslateX(-325 + x * 50);
                    valuableAmazoniteImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableAmazoniteImageView);
                    ValuableElements valuableElementAmazonite = new ValuableElements("amazonite", -325 + x * 50, -150 + y * 50, valuableAmazoniteImageView, 500, 500);
                    elements.add(valuableElementAmazonite);
                    break;

                case "bronzium":
                    ImageView valuableBronziumImageView = new ImageView(valuableBronziumImage);
                    valuableBronziumImageView.setTranslateX(-325 + x * 50);
                    valuableBronziumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableBronziumImageView);
                    ValuableElements valuableElementBronzium = new ValuableElements("bronzium", -325 + x * 50, -150 + y * 50, valuableBronziumImageView, 60, 10);
                    elements.add(valuableElementBronzium);
                    break;

                case "diamond":
                    ImageView valuableDiamondImageView = new ImageView(valuableDiamondImage);
                    valuableDiamondImageView.setTranslateX(-325 + x * 50);
                    valuableDiamondImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableDiamondImageView);
                    ValuableElements valuableElementDiamond = new ValuableElements("diamond", -325 + x * 50, -150 + y * 50, valuableDiamondImageView, 100000, 100);
                    elements.add(valuableElementDiamond);
                    break;

                case "einsteinium":
                    ImageView valuableEinsteiniumImageView = new ImageView(valuableEinsteiniumImage);
                    valuableEinsteiniumImageView.setTranslateX(-325 + x * 50);
                    valuableEinsteiniumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableEinsteiniumImageView);
                    ValuableElements valuableElementEinsteinium = new ValuableElements("einsteinium", -325 + x * 50, -150 + y * 50, valuableEinsteiniumImageView, 2000, 40);
                    elements.add(valuableElementEinsteinium);
                    break;

                case "emerald":
                    ImageView valuableEmeraldImageView = new ImageView(valuableEmeraldImage);
                    valuableEmeraldImageView.setTranslateX(-325 + x * 50);
                    valuableEmeraldImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableEmeraldImageView);
                    ValuableElements valuableElementEmerald = new ValuableElements("emerald", -325 + x * 50, -150 + y * 50, valuableEmeraldImageView, 5000, 60);
                    elements.add(valuableElementEmerald);
                    break;

                case "goldium":
                    ImageView valuableGoldiumImageView = new ImageView(valuableGoldiumImage);
                    valuableGoldiumImageView.setTranslateX(-325 + x * 50);
                    valuableGoldiumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableGoldiumImageView);
                    ValuableElements valuableElementGoldium = new ValuableElements("goldium", -325 + x * 50, -150 + y * 50, valuableGoldiumImageView, 250, 20);
                    elements.add(valuableElementGoldium);
                    break;

                case "ironium":
                    ImageView valuableIroniumImageView = new ImageView(valuableIroniumImage);
                    valuableIroniumImageView.setTranslateX(-325 + x * 50);
                    valuableIroniumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableIroniumImageView);
                    ValuableElements valuableElementIronium = new ValuableElements("ironium", -325 + x * 50, -150 + y * 50, valuableIroniumImageView, 30, 10);
                    elements.add(valuableElementIronium);
                    break;

                case "platinium":
                    ImageView valuablePlatiniumImageView = new ImageView(valuablePlatiniumImage);
                    valuablePlatiniumImageView.setTranslateX(-325 + x * 50);
                    valuablePlatiniumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuablePlatiniumImageView);
                    ValuableElements valuableElementPlatinium = new ValuableElements("platinium", -325 + x * 50, -150 + y * 50, valuablePlatiniumImageView, 750, 30);
                    elements.add(valuableElementPlatinium);
                    break;

                case "ruby":
                    ImageView valuableRubyImageView = new ImageView(valuableRubyImage);
                    valuableRubyImageView.setTranslateX(-325 + x * 50);
                    valuableRubyImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableRubyImageView);
                    ValuableElements valuableElementRuby = new ValuableElements("ruby", -325 + x * 50, -150 + y * 50, valuableRubyImageView, 20000, 80);
                    elements.add(valuableElementRuby);
                    break;

                case "silverium":
                    ImageView valuableSilveriumImageView = new ImageView(valuableSilveriumImage);
                    valuableSilveriumImageView.setTranslateX(-325 + x * 50);
                    valuableSilveriumImageView.setTranslateY(-150 + y * 50);
                    inside.add(valuableSilveriumImageView);
                    ValuableElements valuableElementSilverium = new ValuableElements("silverium", -325 + x * 50, -150 + y * 50, valuableSilveriumImageView, 100, 10);
                    elements.add(valuableElementSilverium);
                    break;

                case "soil":
                    ImageView soilImageView = new ImageView(soilImage);
                    soilImageView.setTranslateX(-325 + x * 50);
                    soilImageView.setTranslateY(-150 + y * 50);
                    inside.add(soilImageView);
                    Soil soil = new Soil("soil", -325 + x * 50, -150 + y * 50, soilImageView);
                    elements.add(soil);
                    break;

                case "lava":
                    ImageView lavaImageView = new ImageView(lavaImage);
                    lavaImageView.setTranslateX(-325 + x * 50);
                    lavaImageView.setTranslateY(-150 + y * 50);
                    inside.add(lavaImageView);
                    Lava lava = new Lava("lava", -325 + x * 50, -150 + y * 50, lavaImageView);
                    elements.add(lava);
                    break;

                case "obstacle":
                    ImageView obstacleImageView = new ImageView(obstacleImage);
                    obstacleImageView.setTranslateX(-325 + x * 50);
                    obstacleImageView.setTranslateY(-150 + y * 50);
                    inside.add(obstacleImageView);
                    Obstacle obstacle = new Obstacle("obstacle", -325 + x * 50, -150 + y * 50, obstacleImageView);
                    elements.add(obstacle);
                    break;
            }
        }

        root.getChildren().addAll(inside);

        /**
         * Set up the scene and initialize the game components.
         */
        Scene scene = new Scene(root, 800, 850);

        Drill drill = new Drill(25, -250, new ImageView(drillRightImage));

        /**
         * Event handler for keyboard input to control the drill's movement.
         */
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    Blocks blocks = getBlockAtPosition(drill.getX(), drill.getY() - 50, elements);

                    if (blocks == null) {
                        drill.move(Drill.Direction.UP, drillUpImage);
                        fuel -= FUEL_CONSUMPTION_PER_MOVE;
                        updateFuel();
                    } else if (blocks.getBlocksName().equals("empty")) {
                        drill.move(Drill.Direction.UP, drillUpImage);
                        fuel -= FUEL_CONSUMPTION_PER_MOVE;
                        updateFuel();
                    }
                    break;

                case DOWN:
                    Blocks blocks1 = getBlockAtPosition(drill.getX(), drill.getY() + 50, elements);

                    if (checkBlockInteraction(drill.getX(), drill.getY() + 50)) {
                        drill.move(Drill.Direction.DOWN, drillDownImage);
                        fuel -= FUEL_CONSUMPTION_PER_MOVE;
                        updateFuel();
                    } else if (blocks1.getBlocksName().equals("lava")) {
                        Rectangle redOverlay = new Rectangle(800, 850, Color.RED);
                        Text gameOverText = new Text("Game Over");
                        gameOverText.setFont(Font.font(60));
                        gameOverText.setFill(Color.WHITE);
                        StackPane.setAlignment(gameOverText, Pos.CENTER);

                        StackPane gameOverPane = new StackPane();
                        gameOverPane.getChildren().addAll(redOverlay, gameOverText);

                        root.getChildren().add(gameOverPane);
                    }
                    break;

                case LEFT:
                    Blocks blocks2 = getBlockAtPosition(drill.getX() - 50, drill.getY(), elements);

                    if (checkBlockInteraction(drill.getX() - 50, drill.getY())) {
                        drill.move(Drill.Direction.LEFT, drillLeftImage);
                        fuel -= FUEL_CONSUMPTION_PER_MOVE;
                        updateFuel();
                    } else if (blocks2.getBlocksName().equals("lava")) {
                        Rectangle redOverlay = new Rectangle(800, 850, Color.RED);
                        Text gameOverText = new Text("Game Over");
                        gameOverText.setFont(Font.font(60));
                        gameOverText.setFill(Color.WHITE);
                        StackPane.setAlignment(gameOverText, Pos.CENTER);

                        StackPane gameOverPane = new StackPane();
                        gameOverPane.getChildren().addAll(redOverlay, gameOverText);

                        root.getChildren().add(gameOverPane);
                    }
                    break;

                case RIGHT:
                    Blocks blocks3 = getBlockAtPosition(drill.getX() + 50, drill.getY(), elements);

                    if (checkBlockInteraction(drill.getX() + 50, drill.getY())) {
                        drill.move(Drill.Direction.RIGHT, drillRightImage);
                        fuel -= FUEL_CONSUMPTION_PER_MOVE;
                        updateFuel();
                    } else if (blocks3.getBlocksName().equals("lava")) {
                        Rectangle redOverlay = new Rectangle(800, 850, Color.RED);
                        Text gameOverText = new Text("Game Over");
                        gameOverText.setFont(Font.font(60));
                        gameOverText.setFill(Color.WHITE);
                        StackPane.setAlignment(gameOverText, Pos.CENTER);

                        StackPane gameOverPane = new StackPane();
                        gameOverPane.getChildren().addAll(redOverlay, gameOverText);

                        root.getChildren().add(gameOverPane);
                    }
                    break;
                default:
                    break;
            }
        });

        root.getChildren().add(drill.getImageView());

        /**
         * Initialize and display text elements for haul, money, and fuel.
         */
        haulText = createText("haul: " + haul, -335, -300, 30);
        moneyText = createText("money: " + money, -320, -250, 30);
        fuelText = createText("fuel:" + fuel, -295, -350, 30);
        root.getChildren().addAll(haulText, moneyText, fuelText, upperName);

        primaryStage.setScene(scene);
        primaryStage.show();

        /**
         * Set up a timeline for gravity simulation.
         */
        Timeline gravityTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.8), event -> {
                    canFallDown(drill.getX(), drill.getY() + 50, elements, drill);
                })
        );

        gravityTimeline.setCycleCount(Timeline.INDEFINITE);
        gravityTimeline.play();

        /**
         * Set up a timeline for updating fuel level.
         */
        Timeline fuelTimeline = new Timeline(
                new KeyFrame(Duration.seconds(0.1), event -> {
                    fuel -= 0.5;
                    boolean statment = updateFuel();
                    if (!statment) {
                        Rectangle greenOverlay = new Rectangle(800, 850, Color.GREEN);
                        Text gameOverText = new Text("Game Over");
                        gameOverText.setFont(Font.font(60));
                        gameOverText.setFill(Color.WHITE);
                        StackPane.setAlignment(gameOverText, Pos.CENTER);

                        Text collectedMoneyText = new Text("Collected Money:" + money);
                        collectedMoneyText.setFont(Font.font(40));
                        collectedMoneyText.setFill(Color.WHITE);
                        collectedMoneyText.setTranslateX(0);
                        collectedMoneyText.setTranslateY(+100);

                        StackPane gameOverPane = new StackPane();
                        gameOverPane.getChildren().addAll(greenOverlay, gameOverText, collectedMoneyText);

                        root.getChildren().add(gameOverPane);
                    }
                })
        );

        fuelTimeline.setCycleCount(Timeline.INDEFINITE);
        fuelTimeline.play();
            }

    /**
     * Selects an element randomly based on weighted probabilities.
     *
     * This method selects an element randomly from the given array of elements
     * based on the provided weights. The weights determine the probability of
     * selecting each element. The higher the weight, the higher the probability
     * of selection.
     *
     * @param elements the array of elements to choose from
     * @param weights the array of weights corresponding to each element
     * @param random the Random object used for randomization
     * @return the randomly selected element
     */
    private String selectWeightedRandomly(String[] elements, int[] weights, Random random) {
        // Calculate the total weight
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }

        // Generate a random number within the total weight range
        int randomNumber = random.nextInt(totalWeight);

        // Select the element based on the random number and weights
        int currentIndex = 0;
        for (int i = 0; i < elements.length; i++) {
            if (randomNumber < weights[i]) {
                currentIndex = i;
                break;
            }
            randomNumber -= weights[i];
        }

        // Return the selected element
        return elements[currentIndex];
    }



    /**
     * Retrieves the block located at the specified position (x, y).
     *
     * This method searches through the list of blocks provided and returns the block
     * whose position matches the specified coordinates (x, y). If no block is found
     * at the given position, it returns null.
     *
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @param elements the list of blocks to search through
     * @return the block located at the specified position, or null if no block is found
     */
    private Blocks getBlockAtPosition(double x, double y, ArrayList<Blocks> elements) {
        for (Blocks block : elements) {
            if (block.getBlocksX() == x && block.getBlocksY() == y) {
                return block; // Found block at the specified position
            }
        }
        return null; // No block found at the specified position
    }


    /**
     * Checks for block interaction at the specified position.
     *
     * This method examines the block at the given position (x, y) and determines whether the drill
     * can interact with it. The interaction depends on the type of block encountered:
     *
     * - If no block is found at the specified position, the drill can interact (returns true).
     * - If the block is lava or an obstacle, the drill cannot interact (returns false).
     * - If the block is soil or top (empty space), the block is cleared, and the drill can interact (returns true).
     * - If the block is a valuable element, the block is collected, and the drill can interact (returns true).
     *   The haul, money, and fuel consumption are updated accordingly.
     *
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @return true if the drill can interact with the block at the specified position, false otherwise
     */
    private boolean checkBlockInteraction(double x, double y) {
        Blocks block = getBlockAtPosition(x, y, elements);
        if (block == null) {
            return true; // No block found, interaction allowed
        } else {
            switch (block.getBlocksName()) {
                case "lava":
                case "obstacle":
                    return false; // Cannot interact with lava or obstacle
                case "soil":
                case "top":
                    block.setBlocksName("empty"); // Clear soil or top block
                    block.setBlocksImage(emptyImage);
                    return true; // Interaction allowed after clearing
                default:
                    // Interaction with valuable element
                    if (block instanceof ValuableElements) {
                        ValuableElements valuableBlock = (ValuableElements) block;
                        if (!valuableBlock.getBlocksName().equals("empty")) {
                            // Update haul, money, and fuel consumption
                            updateHaul(haul + valuableBlock.getWeight() / 10);
                            updateMoney(money + valuableBlock.getWorth());
                            updateFuelConsumption(haul);
                        }
                    }
                    // Clear the block regardless of its type
                    block.setBlocksName("empty");
                    block.setBlocksImage(emptyImage);
                    return true; // Interaction allowed after clearing
            }
        }
    }


    /**
     * Checks if the drill can fall down at the specified position.
     *
     * This method determines whether the drill can fall down from the specified position (x, y)
     * by checking if there is an empty space below or if there is no block present and the
     * position is within the allowed falling range (y < 350). If the conditions are met, the
     * drill is instructed to fall down.
     *
     * @param x the x-coordinate of the position to check
     * @param y the y-coordinate of the position to check
     * @param elements the list of blocks present in the game
     * @param drill the instance of the Drill object representing the player's drilling machine
     */
    private void canFallDown(double x, double y, ArrayList<Blocks> elements, Drill drill) {
        // Check if there is a block below or if the position is within the allowed range
        Blocks underBlock = getBlockAtPosition(x, y, elements);
        if (underBlock == null && y < 350) {
            // No block present below and within allowed range, drill can fall down
            drill.fall();
        } else if (underBlock != null && underBlock.getBlocksName().equals("empty") && y < 350) {
            // Empty space below and within allowed range, drill can fall down
            drill.fall();
        }
    }


    /**
     * Creates a Text object with the specified content, position, and font size.
     *
     * This method creates a Text object with the given content and sets its position
     * on the screen using the provided coordinates (x, y). Additionally, it sets the
     * font size of the text to the specified value.
     *
     * @param content the content to be displayed by the text
     * @param x the x-coordinate of the text position
     * @param y the y-coordinate of the text position
     * @param size the font size of the text
     * @return the Text object created with the specified attributes
     */
    private Text createText(String content, double x, double y, double size) {
        Text text = new Text(content);
        text.setFill(Color.WHITE);
        text.setFont(Font.font(size));
        text.setTranslateX(x);
        text.setTranslateY(y);
        return text;
    }

    /**
     * Updates the current haul value and refreshes the haul text on the screen.
     *
     * This method updates the haul value to the specified newHaul value and updates
     * the haul text displayed on the screen to reflect the new value.
     *
     * @param newHaul the new value of the haul to be set
     */
    private void updateHaul(int newHaul) {
        haul = newHaul;
        haulText.setText("haul: " + haul);
    }

    /**
     * Updates the current money amount and refreshes the money text on the screen.
     *
     * This method updates the money amount to the specified newMoney value and updates
     * the money text displayed on the screen to reflect the new amount.
     *
     * @param newMoney the new amount of money to be set
     */
    private void updateMoney(int newMoney) {
        money = newMoney;
        moneyText.setText("money: " + money);
    }

    /**
     * Updates the fuel level and refreshes the fuel text on the screen.
     *
     * This method checks if the fuel level is depleted. If the fuel level is zero
     * or less, it returns false indicating game over. Otherwise, it updates the
     * fuel text displayed on the screen with the current fuel level.
     *
     * @return true if the fuel level is above zero, false otherwise
     */
    private boolean updateFuel() {
        if (fuel <= 0) {
            return false; // Game over if fuel is depleted
        } else {
            fuelText.setText(String.format("fuel: %.2f", fuel));
            return true;
        }
    }

    /**
     * Updates the fuel consumption rate based on the current haul value.
     *
     * This method updates the fuel consumption rate per move based on the current
     * haul value. The higher the haul, the higher the fuel consumption per move.
     *
     * @param newHaul the current haul value
     */
    private void updateFuelConsumption(int newHaul) {
        FUEL_CONSUMPTION_PER_MOVE = (FUEL_CONSUMPTION_PER_MOVE * (100 + haul)) / 100;
    }


/**
 * Main method to launch the JavaFX application.
 *
 * This method serves as the entry point for launching the JavaFX application.
 * It initializes the JavaFX runtime and starts the application by calling the
 * launch method with the provided command-line arguments.
 *
 * @param args the command-line arguments passed to the application
 */
public static void main(String[] args) {
    launch(args);
}


}

