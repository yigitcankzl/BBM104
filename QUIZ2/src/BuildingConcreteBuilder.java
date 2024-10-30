import java.util.ArrayList;
import java.util.List;

/**
 * The BuildingConcreteBuilder class implements the BuildingBuilder interface to build the entire building by processing decoration orders.
 */
public class BuildingConcreteBuilder implements BuildingBuilder {
    private List<List<String>> decorateOrder; // List to store decoration orders
    private BuildingBuilder classroomBuilder; // Builder for classrooms
    private String args; // File path for writing outputs

    /**
     * Constructs a BuildingConcreteBuilder object with decoration orders, a builder for classrooms, and file path arguments.
     *
     * @param args The file path for writing outputs.
     * @param classroomBuilder The builder for classrooms.
     * @param decorationBuilder The builder for decorations.
     */
    public BuildingConcreteBuilder(String args, BuildingBuilder classroomBuilder, BuildingBuilder decorationBuilder){   
        this.decorateOrder = ((DecorationConcreteBuilder) decorationBuilder).getDecorate();
        this.classroomBuilder = classroomBuilder;
        this.args = args;
    }

    /**
     * Builds a classroom (not implemented in this method).
     *
     * @param args The arguments for building the classroom.
     */
    @Override
    public void buildClassroom(String args) {
        // Implementation not provided in this method
    }

    /**
     * Builds a decoration (not implemented in this method).
     *
     * @param args The arguments for building the decoration.
     */
    @Override
    public void buildDecoration(String args) {
        // Implementation not provided in this method
    }

    /**
     * Builds the entire building based on decoration orders.
     */
    public void build(){
        double allTotalPrice = 0.00;
        for(List<String> decorate: decorateOrder){
            double totalPrice = 0.00;
            for(String item : decorate){
                double price = 0.00;
                String[] parts = item.split("\\s+");
                Classroom classroom = ((ClassroomConcreteBuilder) classroomBuilder).getClassroomToEquals(parts[0]);
                Decoration wallDecoration = ((ClassroomConcreteBuilder) classroomBuilder).getDecorationToEquals(parts[1]);
                Decoration floorDecoration = ((ClassroomConcreteBuilder) classroomBuilder).getDecorationToEquals(parts[2]);

                if (classroom.getClassroomShape().equals("Circle")){
                    Circle circle = (Circle) classroom;
                    double wallArea = circle.getWallArea();
                    double floorArea = circle.getFloorArea();

                    if (wallDecoration.getDecorationType().equals("Tile")){ 
                        Tile tile = (Tile) wallDecoration;
                        price = tile.price(wallArea);
                        price = (int) Math.ceil(price);

                        FileIO.writeToFile(args, "Classroom " + circle.getClassroomName() + " used " + (int) Math.ceil(tile.calculateNumberOfTiles(wallArea)) + " Tiles for walls and used ", true, false);

                        totalPrice += price;
                    }else{
                        price = wallDecoration.price(wallArea);
                        price = (int) Math.ceil(price);

                        FileIO.writeToFile(args, "Classroom " + circle.getClassroomName() + " used " + (int) Math.ceil(circle.getWallArea()) + "m2 of "+ wallDecoration.getDecorationType() + " for walls and used ", true, false);
                        totalPrice += price;

                    }

                    Tile tile = (Tile) floorDecoration;
                    price = tile.price(floorArea);
                    price = (int) Math.ceil(price);

                    FileIO.writeToFile(args, (int) Math.ceil(tile.calculateNumberOfTiles(floorArea)) + " Tiles for flooring, these costed ", true, false);
                    totalPrice += price;

                    

                }else{
                    Rectangle rectangle = (Rectangle) classroom;
                    double wallArea = rectangle.getWallArea();
                    double floorArea = rectangle.getFloorArea();

                    if (wallDecoration.getDecorationType().equals("Tile")){
                        Tile tile = (Tile) wallDecoration;
                        price = tile.price(wallArea);
                        price = (int) Math.ceil(price);

                        FileIO.writeToFile(args, "Classroom " + rectangle.getClassroomName() + " used " + (int) Math.ceil(tile.calculateNumberOfTiles(wallArea)) + " Tiles for walls and used ", true, false);
                        totalPrice += price;

                    }else{
                        price = wallDecoration.price(wallArea);
                        price = (int) Math.ceil(price);

                        FileIO.writeToFile(args, "Classroom " + rectangle.getClassroomName() + " used " + (int) Math.ceil(rectangle.getWallArea()) + "m2 of "+ wallDecoration.getDecorationType() + " for walls and used ", true, false);
                        totalPrice += price;

                    }

                    Tile tile = (Tile) floorDecoration;
                    price = tile.price(floorArea);
                    price = (int) Math.ceil(price);

                    FileIO.writeToFile(args, (int) Math.ceil(tile.calculateNumberOfTiles(floorArea)) + " Tiles for flooring, these costed ", true, false);
                    totalPrice += price;

                }            
            }

            int integerTotalPrice = (int) totalPrice;
            FileIO.writeToFile(args, integerTotalPrice + "TL." , true, true);
            allTotalPrice += integerTotalPrice;
        }   
        
        int integerAllTotalPrice = (int) allTotalPrice;
        FileIO.writeToFile(args, "Total price is: " + integerAllTotalPrice + "TL." , true, false);
    }

    /**
     * Retrieves an empty list of classrooms.
     *
     * @return An empty list of classrooms.
     */
    @Override
    public List<Classroom> getClassrooms() {
        return new ArrayList<>();
    }

    /**
     * Retrieves an empty list of decorations.
     *
     * @return An empty list of decorations.
     */
    @Override
    public List<Decoration> getDecorations() {
        return new ArrayList<>();
    }

    /**
     * Retrieves an empty list of decoration orders.
     *
     * @return An empty list of decoration orders.
     */
    @Override
    public List<List<String>> getDecorate(){
        return new ArrayList<>();
    }
}

