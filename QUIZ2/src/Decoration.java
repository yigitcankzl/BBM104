/**
 * The Decoration class represents a decoration item with its name, type, and price.
 */
public class Decoration {
    private String decorationName; // The name of the decoration
    private String decorationType; // The type of the decoration
    protected int decorationPrice; // The price of the decoration
    
    /**
     * Constructs a Decoration object with the specified name, type, and price.
     *
     * @param decorationName The name of the decoration.
     * @param decorationType The type of the decoration.
     * @param decorationPrice The price of the decoration.
     */
    public Decoration(String decorationName, String decorationType, int decorationPrice) {
        this.decorationName = decorationName;
        this.decorationType = decorationType;
        this.decorationPrice = decorationPrice;
    }
    
    /**
     * Retrieves the name of the decoration.
     *
     * @return The name of the decoration.
     */
    public String getDecorationName(){
        return decorationName;
    }

    /**
     * Retrieves the type of the decoration.
     *
     * @return The type of the decoration.
     */
    public String getDecorationType(){
        return decorationType;
    }

    /**
     * Calculates the price based on the total area.
     *
     * @param totalArea The total area to be decorated.
     * @return The calculated price based on the total area.
     */
    public double price(double totalArea){
        return totalArea * decorationPrice;
   }
}

/**
 * The Paint class represents a type of decoration item which is paint.
 */
class Paint extends Decoration{
    /**
     * Constructs a Paint object with the specified name, type, and price.
     *
     * @param decorationName The name of the paint decoration.
     * @param decorationType The type of the paint decoration.
     * @param decorationPrice The price of the paint decoration.
     */
    public Paint(String decorationName, String decorationType, int decorationPrice) {
        super(decorationName, decorationType, decorationPrice);
    }
}

/**
 * The Tile class represents a type of decoration item which is tile.
 */
class Tile extends Decoration{
    private int tileArea; // The area covered by a single tile

    /**
     * Constructs a Tile object with the specified name, type, price, and tile area.
     *
     * @param decorationName The name of the tile decoration.
     * @param decorationType The type of the tile decoration.
     * @param decorationPrice The price of the tile decoration.
     * @param tileArea The area covered by a single tile.
     */
    public Tile(String decorationName, String decorationType, int decorationPrice, int tileArea) {
        super(decorationName, decorationType, decorationPrice);
        this.tileArea = tileArea;
    }

    /**
     * Calculates the number of tiles required based on the total area.
     *
     * @param totalArea The total area to be tiled.
     * @return The number of tiles required to cover the total area.
     */
    public double calculateNumberOfTiles(double totalArea) {
        return (int) Math.ceil(totalArea / tileArea);
    }
    
    @Override
    public double price(double totalArea){
        double numberOfTiles = calculateNumberOfTiles(totalArea);
        return numberOfTiles * decorationPrice;
    }
}

/**
 * The Wallpaper class represents a type of decoration item which is wallpaper.
 */
class Wallpaper extends Decoration{
    /**
     * Constructs a Wallpaper object with the specified name, type, and price.
     *
     * @param decorationName The name of the wallpaper decoration.
     * @param decorationType The type of the wallpaper decoration.
     * @param decorationPrice The price of the wallpaper decoration.
     */
    public Wallpaper(String decorationName, String decorationType, int decorationPrice) {
        super(decorationName, decorationType, decorationPrice);
    }
}
