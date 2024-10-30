import java.util.*;

/**
 * The {@code MapAnalyzer} class reads road data from a file, processes the data to find the fastest route and the barely connected map,
 * and generates related textual analysis and output files.
 */
public class MapAnalyzer {
    /**
     * The main method serves as the entry point for the application. It reads the input file, processes the road data to find
     * the fastest route and the barely connected map, and writes the results to the specified output file.
     *
     * @param args the command line arguments where args[0] is the input file name and args[1] is the output file name
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        // Read the input file
        String[] content = FileIO.readFile(args[0], true, true);
        List<Road> roads = new ArrayList<>();
        String startPoint = "";
        String endPoint = "";
        int totalLengthOfFirst = 0;
        String outputFileName = args[1];

        // Parse the file content to extract start point, end point, and roads
        for (String line : content) {
            String[] parts = line.split("\t");

            // Extract start and end points
            if (parts.length == 2) {
                startPoint = parts[0];
                endPoint = parts[1];
            } else {
                // Extract road details
                String point1 = parts[0];
                String point2 = parts[1];
                int length = Integer.parseInt(parts[2]);
                totalLengthOfFirst += length;
                int id = Integer.parseInt(parts[3]);

                // Add roads in both directions
                roads.add(new Road(point1, point2, length, id));
                roads.add(new Road(point2, point1, length, -id));
            }
        }

        // Find the fastest route between the start and end points
        FastestRoute fastestRouteFinder = new FastestRoute(startPoint, endPoint, roads);
        List<Road> shortestRoute = fastestRouteFinder.findFastestRoute(startPoint, endPoint, roads);
        int lengthOfFirst = fastestRouteFinder.getCurrentLength();

        // Write the fastest route to the output file
        fastestRouteFinder.createTextFirst(shortestRoute, lengthOfFirst, startPoint, endPoint, outputFileName);

        // Initialize variables for finding the barely connected map
        List<String> points = new ArrayList<>();
        points.add(startPoint);
        List<Road> barelyConnectedMap = new ArrayList<>();
        List<Road> shortestRoute1 = new ArrayList<>();
        BarelyConnectedMap map = new BarelyConnectedMap(startPoint, endPoint, roads);
        Set<String> allPoints = map.createAllPoint(roads);

        // Find the barely connected map
        map.findBarelyConnectedMap(startPoint, endPoint, roads, points, barelyConnectedMap, shortestRoute1, allPoints);

        // Write the barely connected map to the output file
        int totalLengthOfSecond = map.createTextFirst(barelyConnectedMap, lengthOfFirst, outputFileName);

        // Get the length of the shortest route on the barely connected map
        int lengthOfSecond = fastestRouteFinder.getCurrentLength();

        // Write the fastest route on the barely connected map to the output file
        map.createTextFirst2(shortestRoute1, lengthOfSecond, startPoint, endPoint, outputFileName);

        // Write the analysis ratios to the output file
        fastestRouteFinder.createTextRatio1(totalLengthOfFirst, totalLengthOfSecond, outputFileName);
        fastestRouteFinder.createTextRatio2(lengthOfFirst, lengthOfSecond, outputFileName);
    }
}
