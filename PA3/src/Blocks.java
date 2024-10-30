import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Represents a block object in the game environment.
 */
public class Blocks extends ImageView {
    protected String name; // Name of the block
    protected double xCoordinate; // x-coordinate of the block
    protected double yCoordinate; // y-coordinate of the block
    protected ImageView Image; // ImageView representing the block's image

    /**
     * Constructs a block object with specified parameters.
     *
     * @param name the name of the block
     * @param xCoordinate the x-coordinate of the block
     * @param yCoordinate the y-coordinate of the block
     * @param obstacleImage the ImageView representing the block's image
     */
    public Blocks(String name ,double xCoordinate, double yCoordinate, ImageView obstacleImage) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.Image = obstacleImage;
        this.name = name;
    }

    /**
     * Retrieves the ImageView representing the block's image.
     *
     * @return the ImageView representing the block's image
     */
    public ImageView getImagaView(){
        return Image;
    }

    /**
     * Retrieves the x-coordinate of the block.
     *
     * @return the x-coordinate of the block
     */
    public double getBlocksX(){
        return xCoordinate;
    }

    /**
     * Retrieves the y-coordinate of the block.
     *
     * @return the y-coordinate of the block
     */
    public double getBlocksY(){
        return yCoordinate;
    }

    /**
     * Retrieves the name of the block.
     *
     * @return the name of the block
     */
    public String getBlocksName(){
        return name;
    }

    /**
     * Retrieves the ImageView representing the block's image.
     *
     * @return the ImageView representing the block's image
     */
    public ImageView getBlocksImage(){
        return Image;
    }

    /**
     * Sets the name of the block.
     *
     * @param name the new name of the block
     */
    public void setBlocksName(String name){
        this.name = name;
    }

    /**
     * Sets the image of the block.
     *
     * @param image the new image of the block
     */
    public void setBlocksImage(Image image){
        this.Image.setImage(image);    
    }
}

/**
 * Represents an empty block in the game environment.
 */
class Empty extends Blocks {
    public Empty(String name ,double xCoordinate, double yCoordinate, ImageView Image) {
        super(name, xCoordinate, yCoordinate, Image);
    }
}

/**
 * Represents a top block in the game environment.
 */
class Top extends Blocks {
    public Top(String name ,double xCoordinate, double yCoordinate, ImageView Image) {
        super(name, xCoordinate, yCoordinate, Image);
    }
}

/**
 * Represents a soil block in the game environment.
 */
class Soil extends Blocks {
    public Soil(String name ,double xCoordinate, double yCoordinate, ImageView Image) {
        super(name, xCoordinate, yCoordinate, Image);
    }
}

/**
 * Represents a lava block in the game environment.
 */
class Lava extends Blocks {
    public Lava(String name ,double xCoordinate, double yCoordinate, ImageView Image) {
        super(name, xCoordinate, yCoordinate, Image);
    }
}

/**
 * Represents an obstacle block in the game environment.
 */
class Obstacle extends Blocks {
    public Obstacle(String name ,double xCoordinate, double yCoordinate, ImageView Image) {
        super(name, xCoordinate, yCoordinate, Image);
    }
}

/**
 * Represents a valuable element block in the game environment.
 */
class ValuableElements extends Blocks {
    private int worth; // Worth of the valuable element
    private int weight; // Weight of the valuable element

    /**
     * Constructs a valuable element block object with specified parameters.
     *
     * @param name the name of the valuable element
     * @param xCoordinate the x-coordinate of the valuable element block
     * @param yCoordinate the y-coordinate of the valuable element block
     * @param Image the ImageView representing the valuable element block's image
     * @param worth the worth of the valuable element
     * @param weight the weight of the valuable element
     */
    public ValuableElements(String name, double xCoordinate, double yCoordinate, ImageView Image, int worth, int weight){
        super(name, xCoordinate, yCoordinate, Image);
        this.worth = worth;
        this.weight = weight;
    }

    /**
     * Retrieves the worth of the valuable element.
     *
     * @return the worth of the valuable element
     */
    public int getWorth(){
        return worth;
    }

    /**
     * Retrieves the weight of the valuable element.
     *
     * @return the weight of the valuable element
     */
    public int getWeight(){
        return weight;
    }
}   
