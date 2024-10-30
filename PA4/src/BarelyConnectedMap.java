import java.util.*;

/**
 * The {@code BarelyConnectedMap} class provides methods to find the barely connected map and
 * to generate related textual analysis.
 */
public class BarelyConnectedMap {
    /**
     * A statement to indicate whether the barely connected map meets the required conditions.
     */
    public static boolean statement = false;

    /**
     * Constructs a {@code BarelyConnectedMap} object and sorts the roads by length and ID.
     *
     * @param startPoint the starting point of the map
     * @param endPoint the ending point of the map
     * @param roads the list of roads to be considered
     */
    public BarelyConnectedMap(String startPoint, String endPoint, List<Road> roads) {
        Collections.sort(roads, Comparator.comparingInt(Road::getLength).thenComparing(road -> Math.abs(road.getID())));
    }

    /**
     * Finds the barely connected map that connects all points and includes the shortest route.
     *
     * @param startPoint the starting point of the map
     * @param endPoint the ending point of the map
     * @param roads the list of roads to be considered
     * @param points the list of points to be connected
     * @param barelyConnectedMap the current barely connected map
     * @param shortestRoute1 the shortest route found so far
     * @param allPoints the set of all points to be connected
     */
    public void findBarelyConnectedMap(String startPoint, String endPoint, List<Road> roads, List<String> points, List<Road> barelyConnectedMap, List<Road> shortestRoute1, Set<String> allPoints) {
        Collections.sort(roads, Comparator.comparingInt(Road::getLength).thenComparing(road -> Math.abs(road.getID())));

        FastestRoute fastestRouteFinder = new FastestRoute(startPoint, endPoint, barelyConnectedMap);
        List<Road> foundShortestRoute = fastestRouteFinder.findFastestRoute(startPoint, endPoint, barelyConnectedMap);

        if (!foundShortestRoute.isEmpty() && chechContainPoint(barelyConnectedMap, allPoints)) {
            statement = true;
            shortestRoute1.clear();
            shortestRoute1.addAll(foundShortestRoute);
            return;
        } else {
            for (Road road : roads) {
                for (String point : points) {
                    if (road.getStartPoint().equals(point) && !points.contains(road.getEndPoint())) {
                        if (!statement) {
                            barelyConnectedMap.add(road);
                            List<String> points2 = new ArrayList<>(points);
                            points2.add(road.getEndPoint());
                            findBarelyConnectedMap(startPoint, endPoint, roads, points2, barelyConnectedMap, shortestRoute1, allPoints);
                            if (statement) return;
                            barelyConnectedMap.remove(road);
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates a text file describing the barely connected map.
     *
     * @param barelyConnectedMap the list of roads in the barely connected map
     * @param lengthOfFirst the total length of the first route
     * @param outputFileName the name of the output file
     * @return the total length of the barely connected map
     */
    public int createTextFirst(List<Road> barelyConnectedMap, int lengthOfFirst, String outputFileName) {
        Collections.sort(barelyConnectedMap, Comparator.comparingInt(Road::getLength).thenComparing(road -> Math.abs(road.getID())));
        FileIO.writeToFile(outputFileName, "Roads of Barely Connected Map is:", true, true);

        int totalLengthOfSecond = 0;
        for (Road road : barelyConnectedMap) {
            totalLengthOfSecond += road.getLength();
            if (road.getID() < 0) {
                FileIO.writeToFile(outputFileName, road.getEndPoint() + "\t" + road.getStartPoint() + "\t" + road.getLength() + "\t" + -road.getID(), true, true);
            } else {
                FileIO.writeToFile(outputFileName, road.getStartPoint() + "\t" + road.getEndPoint() + "\t" + road.getLength() + "\t" + road.getID(), true, true);
            }
        }
        return totalLengthOfSecond;
    }

    /**
     * Creates a text file describing the fastest route on the barely connected map.
     *
     * @param shortestRoute1 the list of roads in the shortest route
     * @param lengthOfSecond the total length of the second route
     * @param startPoint the starting point of the route
     * @param endPoint the ending point of the route
     * @param outputFileName the name of the output file
     */
    public void createTextFirst2(List<Road> shortestRoute1, int lengthOfSecond, String startPoint, String endPoint, String outputFileName) {
        FileIO.writeToFile(outputFileName, "Fastest Route from " + startPoint + " to " + endPoint + " on Barely Connected Map" + " (" + lengthOfSecond + " KM):", true, true);

        for (Road road : shortestRoute1) {
            if (road.getID() < 0) {
                FileIO.writeToFile(outputFileName, road.getEndPoint() + "\t" + road.getStartPoint() + "\t" + road.getLength() + "\t" + -road.getID(), true, true);
            } else {
                FileIO.writeToFile(outputFileName, road.getStartPoint() + "\t" + road.getEndPoint() + "\t" + road.getLength() + "\t" + road.getID(), true, true);
            }
        }
    }

    /**
     * Creates a set of all points from the given list of roads.
     *
     * @param roads the list of roads to extract points from
     * @return a set of all points
     */
    public Set<String> createAllPoint(List<Road> roads) {
        Set<String> allPoints = new HashSet<>();
        for (Road road : roads) {
            allPoints.add(road.getStartPoint());
            allPoints.add(road.getEndPoint());
        }
        return allPoints;
    }

    /**
     * Checks if the given list of roads contains all points in the set.
     *
     * @param roads the list of roads to be checked
     * @param allPoints the set of all points to be contained
     * @return {@code true} if all points are contained, {@code false} otherwise
     */
    public boolean chechContainPoint(List<Road> roads, Set<String> allPoints) {
        Set<String> points = createAllPoint(roads);
        return points.containsAll(allPoints);
    }
}
