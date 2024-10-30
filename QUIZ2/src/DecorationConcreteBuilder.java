import java.util.ArrayList;
import java.util.List;

/**
 * The DecorationConcreteBuilder class implements the BuildingBuilder interface to build decoration orders.
 */
public class DecorationConcreteBuilder implements BuildingBuilder {
    private List<List<String>> decorateOrder; // List to store decoration orders

    /**
     * Constructs a DecorationConcreteBuilder object with an empty list for decoration orders.
     */
    public DecorationConcreteBuilder() {
        this.decorateOrder = new ArrayList<>();
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
     * Builds a decoration order.
     *
     * @param args The arguments for building the decoration order.
     */
    @Override
    public void buildDecoration(String args) {
        // Read file and parse content
        String[] decorationContent = FileIO12.readFile(args, false, false);
        List<String> array;
        for(String line:decorationContent){
            String[] parts = line.split(" ");
            array = new ArrayList<>();
            for(String part:parts){
                array.add(part);
            } 
            decorateOrder.add(array);           
        }
    }

    /**
     * Retrieves the decoration orders.
     *
     * @return The list of decoration orders.
     */
    @Override
    public List<List<String>> getDecorate(){
        return decorateOrder;
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
}
