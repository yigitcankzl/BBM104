import java.util.ArrayList;
import java.util.List;

/**
 * The ClassroomConcreteBuilder class implements the BuildingBuilder interface to build classrooms and decorations.
 */
public class ClassroomConcreteBuilder implements BuildingBuilder {
    private List<Classroom> classrooms; // List to store classrooms
    private List<Decoration> decorations; // List to store decorations

    /**
     * Constructs a ClassroomConcreteBuilder object with empty lists for classrooms and decorations.
     */
    public ClassroomConcreteBuilder() {
        this.classrooms = new ArrayList<Classroom>();
        this.decorations = new ArrayList<Decoration>();
    }

    /**
     * Builds a decoration.
     *
     * @param args The arguments for building the decoration.
     */
    @Override
    public void buildDecoration(String args) {
        // Implementation not provided in this method
    }

    /**
     * Builds a classroom.
     *
     * @param args The arguments for building the classroom.
     */
    @Override
    public void buildClassroom(String args) {
        // Read file and parse content
        String[] itemsContent = FileIO12.readFile(args, false, false);
        for(String line:itemsContent){
            String[] parts = line.split("\t");

            // Check if it's a classroom or a decoration
            if(parts[0].equals("CLASSROOM")){
                // Create a classroom object based on the type specified in the file
                if(parts[2].equals("Circle")){
                    Classroom circle = new Circle(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    classrooms.add(circle);
                } else if(parts[2].equals("Rectangle")){
                    Classroom rectangle = new Rectangle(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                    classrooms.add(rectangle);
                }
            } else if(parts[0].equals("DECORATION")){
                // If it's a decoration, create a decoration object based on the type specified in the file
                if(parts[2].equals("Paint")){
                    Decoration paint = new Paint(parts[1], parts[2], Integer.parseInt(parts[3]));
                    decorations.add(paint);
                } else if(parts[2].equals("Tile")){
                    Decoration tile = new Tile(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                    decorations.add(tile);
                } else if(parts[2].equals("Wallpaper")){
                    Decoration wallpaper = new Wallpaper(parts[1], parts[2], Integer.parseInt(parts[3]));
                    decorations.add(wallpaper);
                }
            }
        }
    }

    /**
     * Retrieves a list of classrooms.
     *
     * @return A list of classrooms.
     */
    @Override
    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    /**
     * Retrieves a list of decorations.
     *
     * @return A list of decorations.
     */
    @Override
    public List<Decoration> getDecorations() {
        return decorations;
    }

    /**
     * Retrieves a list of decorations applied to classrooms.
     *
     * @return A list of decorations applied to classrooms.
     */
    @Override
    public List<List<String>> getDecorate(){
        // Not implemented in this method, returning an empty list
        return new ArrayList<>();
    }

    /**
     * Retrieves a classroom object by comparing the name.
     *
     * @param firstItem The name of the classroom to search for.
     * @return The classroom object with the matching name, or null if not found.
     */
    public Classroom getClassroomToEquals(String firstItem) {
        for (Classroom classroom : classrooms) {
            if (classroom.getClassroomName().equals(firstItem)) {
                return classroom;
            }
        }
        return null; 
    }

    /**
     * Retrieves a decoration object by comparing the name.
     *
     * @param firstItem The name of the decoration to search for.
     * @return The decoration object with the matching name, or null if not found.
     */
    public Decoration getDecorationToEquals(String firstItem) {
        for (Decoration decoration : decorations) {
            if (decoration.getDecorationName().equals(firstItem)) {
                return decoration;
            }
        }
        return null; 
    }
}
