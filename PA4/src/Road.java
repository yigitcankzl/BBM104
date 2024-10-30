/**
 * The {@code Road} class represents a road with a start point, end point, length, and an identifier.
 */
public class Road {
    private String startPoint;
    private String endPoint;
    private int length;
    private int id;

    /**
     * Constructs a new {@code Road} object with the specified start point, end point, length, and identifier.
     *
     * @param startPoint the starting point of the road
     * @param endPoint the ending point of the road
     * @param length the length of the road in units
     * @param id the unique identifier for the road
     */
    public Road(String startPoint, String endPoint, int length, int id) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.length = length;
        this.id = id;
    }

    /**
     * Returns the starting point of the road.
     *
     * @return the start point of the road
     */
    public String getStartPoint() {
        return startPoint;
    }

    /**
     * Returns the ending point of the road.
     *
     * @return the end point of the road
     */
    public String getEndPoint() {
        return endPoint;
    }

    /**
     * Returns the length of the road.
     *
     * @return the length of the road
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the unique identifier of the road.
     *
     * @return the identifier of the road
     */
    public int getID() {
        return id;
    }
}
