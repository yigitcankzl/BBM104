import java.util.List;

/**
 * The BuildingBuilder interface defines methods for building classrooms, decorations, and retrieving information about them.
 */
public interface BuildingBuilder {
    /**
     * Builds a classroom with the specified arguments.
     *
     * @param args The arguments for building the classroom.
     */
    void buildClassroom(String args);
    
    /**
     * Builds a decoration with the specified arguments.
     *
     * @param args The arguments for building the decoration.
     */
    void buildDecoration(String args);
    
    /**
     * Retrieves a list of classrooms.
     *
     * @return A list of classrooms.
     */
    List<Classroom> getClassrooms();
    
    /**
     * Retrieves a list of decorations.
     *
     * @return A list of decorations.
     */
    List<Decoration> getDecorations();
    
    /**
     * Retrieves a list of decorations applied to classrooms.
     *
     * @return A list of decorations applied to classrooms.
     */
    List<List<String>> getDecorate();
}
