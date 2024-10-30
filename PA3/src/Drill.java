import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a Drill object that can move in four directions (UP, DOWN, LEFT, RIGHT)
 * and can fall vertically.
 */
public class Drill {
    private ImageView imageView; // ImageView representing the drill
    private double x; // x-coordinate of the drill
    private double y; // y-coordinate of the drill
    
    /**
     * Constructs a Drill object with initial position and ImageView.
     *
     * @param initialX the initial x-coordinate of the drill
     * @param initialY the initial y-coordinate of the drill
     * @param imageView the ImageView representing the drill
     */
    public Drill(double initialX, double initialY, ImageView imageView) {
        this.x = initialX;
        this.y = initialY;
        this.imageView = imageView;
        updateImageView(); // Update ImageView position
    }

    /**
     * Retrieves the current x-coordinate of the drill.
     *
     * @return the x-coordinate of the drill
     */
    public double getX(){
        return x;
    }

    /**
     * Retrieves the current y-coordinate of the drill.
     *
     * @return the y-coordinate of the drill
     */
    public double getY(){
        return y;
    }

    /**
     * Moves the drill in the specified direction.
     *
     * @param newDirection the direction in which the drill should move
     * @param image the Image to set as the drill's image after moving
     */
    public void move(Direction newDirection, Image image) {
        switch (newDirection) {
            case UP:
                if(y > -350){
                    y -= 50;
                    imageView.setImage(image); 
                }
                break;
            case DOWN:
                if(y < 400){
                    y += 50;
                    imageView.setImage(image); 
                }
                break;
            case LEFT:
                if(x > -375){
                    x -= 50;
                    imageView.setImage(image); 
                }
                break;
            case RIGHT:
                if(x < 375){
                    x += 50;
                    imageView.setImage(image);
                }
                break;
        }
        updateImageView(); // Update ImageView position
    }
    
    /**
     * Moves the drill downward by 50 units.
     */
    public void fall() {
        y += 50;
        updateImageView(); // Update ImageView position
    }
    
    /**
     * Updates the position and size of the ImageView to match the current drill position.
     */
    private void updateImageView() {
        imageView.setTranslateX(x);
        imageView.setTranslateY(y);
        imageView.setFitWidth(70); // Set width of the ImageView
        imageView.setFitHeight(70); // Set height of the ImageView
    }

    /**
     * Retrieves the ImageView representing the drill.
     *
     * @return the ImageView representing the drill
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Enumerates the possible directions in which the drill can move.
     */
    public enum Direction {
        UP,    // Move the drill upwards
        DOWN,  // Move the drill downwards
        LEFT,  // Move the drill to the left
        RIGHT  // Move the drill to the right
    }
}
