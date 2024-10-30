import java.util.*;

/**
 * The {@code FastestRoute} class provides methods to find the shortest route between two points
 * using a list of roads and to generate related textual analysis.
 */
public class FastestRoute {
    /**
     * The current length of the fastest route.
     */
    public static int currentLengthq = 0;

    /**
     * Constructs a {@code FastestRoute} object and sorts the roads by length and ID.
     *
     * @param startPoint the starting point of the route
     * @param endPoint the ending point of the route
     * @param roads the list of roads to be considered
     */
    public FastestRoute(String startPoint, String endPoint, List<Road> roads) {
        Collections.sort(roads, Comparator.comparingInt(Road::getLength).thenComparing(road -> Math.abs(road.getID())));
    }

    /**
     * Finds the fastest route from the start point to the end point.
     *
     * @param startPoint the starting point of the route
     * @param endPoint the ending point of the route
     * @param roads the list of roads to be considered
     * @return a list of roads representing the fastest route
     */
    public List<Road> findFastestRoute(String startPoint, String endPoint, List<Road> roads) {
        Collections.sort(roads, Comparator.comparingInt(Road::getLength).thenComparing(road -> Math.abs(road.getID())));
        List<Road> shortestRoute = new ArrayList<>();
        findRoute(startPoint, endPoint, roads, new ArrayList<>(), shortestRoute, new HashSet<>(), 0, new int[]{Integer.MAX_VALUE});
        return shortestRoute;
    }

    /**
     * Recursively finds the fastest route from the current point to the end point.
     *
     * @param currentPoint the current point in the route
     * @param endPoint the ending point of the route
     * @param roads the list of roads to be considered
     * @param currentRoute the current route being constructed
     * @param shortestRoute the shortest route found so far
     * @param visited the set of visited points to avoid cycles
     * @param currentLength the current length of the route
     * @param minLength the minimum length found so far
     */
    private static void findRoute(String currentPoint, String endPoint, List<Road> roads, List<Road> currentRoute, List<Road> shortestRoute, Set<String> visited, int currentLength, int[] minLength) {
        if (currentPoint.equals(endPoint)) {
            if (currentLength < minLength[0]) {
                currentLengthq = currentLength;
                minLength[0] = currentLength;
                shortestRoute.clear();
                shortestRoute.addAll(currentRoute);
            }
            return;
        }

        for (Road road : roads) {
            if (road.getStartPoint().equals(currentPoint) && !visited.contains(road.getEndPoint())) {
                visited.add(road.getEndPoint());
                currentRoute.add(road);
                findRoute(road.getEndPoint(), endPoint, roads, currentRoute, shortestRoute, visited, currentLength + road.getLength(), minLength);
                currentRoute.remove(currentRoute.size() - 1);
                visited.remove(road.getEndPoint());
            }
        }
    }

    /**
     * Returns the current length of the fastest route.
     *
     * @return the current length of the fastest route
     */
    public int getCurrentLength() {
        return currentLengthq;
    }

    /**
     * Creates a text file describing the fastest route.
     *
     * @param shortestRoute the list of roads in the fastest route
     * @param lengthOfFirst the total length of the fastest route
     * @param startPoint the starting point of the route
     * @param endPoint the ending point of the route
     * @param outputFileName the name of the output file
     */
    public void createTextFirst(List<Road> shortestRoute, int lengthOfFirst, String startPoint, String endPoint, String outputFileName) {
        FileIO.writeToFile(outputFileName, "Fastest Route from " + startPoint + " to " + endPoint + " (" + lengthOfFirst + " KM):", true, true);
        for (Road road : shortestRoute) {
            if (road.getID() < 0) {
                FileIO.writeToFile(outputFileName, road.getEndPoint() + "\t" + road.getStartPoint() + "\t" + road.getLength() + "\t" + -road.getID(), true, true);
            } else {
                FileIO.writeToFile(outputFileName, road.getStartPoint() + "\t" + road.getEndPoint() + "\t" + road.getLength() + "\t" + road.getID(), true, true);
            }
        }
    }

    /**
     * Creates a text file with the analysis of the ratio of construction material usage.
     *
     * @param totalLengthOfFirst the total length of the fastest route in the original map
     * @param totalLengthOfSecond the total length of the fastest route in the barely connected map
     * @param outputFileName the name of the output file
     */
    public void createTextRatio1(int totalLengthOfFirst, int totalLengthOfSecond, String outputFileName) {
        FileIO.writeToFile(outputFileName, "Analysis:", true, true);
        double ratio = (double) totalLengthOfSecond / totalLengthOfFirst;
        String content = String.format("Ratio of Construction Material Usage Between Barely Connected and Original Map: %.2f%n", ratio);
        FileIO.writeToFile(outputFileName, content, true, false);
    }

    /**
     * Creates a text file with the analysis of the ratio of the fastest route lengths.
     *
     * @param lengthOfFirst the total length of the fastest route in the original map
     * @param lengthOfSecond the total length of the fastest route in the barely connected map
     * @param outputFileName the name of the output file
     */
    public void createTextRatio2(int lengthOfFirst, int lengthOfSecond, String outputFileName) {
        double ratio1 = (double) lengthOfSecond / lengthOfFirst;
        String content = String.format("Ratio of Fastest Route Between Barely Connected and Original Map: %.2f", ratio1);
        FileIO.writeToFile(outputFileName, content, true, false);
    }
}
