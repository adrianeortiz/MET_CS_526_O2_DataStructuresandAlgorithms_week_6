package student;    // DO NOT REMOVE FROM SUBMITTED FILE
import java.util.*;

/**
 You are working for a promising new “flight hacking” startup “AlgoJet”.
 The startup aims to find ultra-cheap flights by combining multiple flights from various airlines and by taking portions of multi leg flights.

 A database of flights is loaded into the AlgoFlight algorithm every morning with all offered flights.
 Flights will have:
 - Source
 - destination
 - price

 After initializing the system, customers can enter a source and destination city and get the cheapest flight (You will only need to return the price of the flight).

 If no flight path reaches the destination from the source, return -1

 You must solve this problem using a hand made graph data structure and Dijkstra’s algorithm.

 You are given the flight class which will be used to pass the list of flights.

 You will implement two methods:
 - initializeFlightGraph(List of flight objects)
 - getCheapestFlight(source, destination)

 You must provide a running time analysis of the getCheapestFlight function

 Code Author: <Your Name Here>

 Running Time Analysis of getCheapestFlight
 --------------------
 <Your analysis here>
 */
public class AlgoJet {
    public static class Flight {
        private final String source;
        private final String destination;
        private final int price;
        public Flight(String source, String destination, int price) {
            this.source = source;
            this.destination = destination;
            this.price = price;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public int getPrice() {
            return price;
        }
    }

    /**
     * This is my recommended (and optional) way to represent the graph which will me an adjacency List of:
     * Source to a map of all reachable destinations with the cheapest price to get to that destination from the source
     */
    private Map<String, Map<String, Integer>> graph;

    public AlgoJet() {
        this.graph = new HashMap<>();
    }

    public void initializeFlightGraph(List<Flight> flights) {
    }
    public int getCheapestFlight(String source, String destination) {
        // My recommended way to store the results for Dijkstra's algorithm
        Map<String, Integer> distances = new HashMap<>();
        // Initialize Dijkstra's results
        for (String city : graph.keySet()) {
            distances.put(city, Integer.MAX_VALUE);
        }
        distances.put(source, 0);

        // Initialize priority Queue
        PriorityQueue<Map.Entry<Integer, String>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));
        pq.add(new AbstractMap.SimpleEntry<>(0, source));

        return 0;
    }

    /*
    DO NOT EDIT BELOW THIS
    Below is the unit testing suite for this file.
    It provides all the tests that your code must pass to get full credit.
    */
    public static void runUnitTests() {
        testExample();
        testUnreachableDestination();
        testSameSourceAndDestination();
        testCycle();
        testMultipleFlights();
    }

    private static void printTestResult(String testName, boolean result) {
        String color = result ? "\033[92m" : "\033[91m";
        String reset = "\033[0m";
        System.out.println(color + "[" + result + "] " + testName + reset);
    }

    private static void testAnswer(String testName, int result, int expected) {
        if (result == expected) {
            printTestResult(testName, true);
        } else {
            printTestResult(testName, false);
            System.out.println("Expected: " + expected + "\nGot:      " + result);
        }
    }

    private static void testAnswer(String testName, int[] result, int[] expected) {
        if (Arrays.equals(result, expected)) {
            printTestResult(testName, true);
        } else {
            printTestResult(testName, false);
            System.out.println("Expected: " + Arrays.toString(expected) + "\nGot:      " + Arrays.toString(result));
        }
    }

    public static void testExample() {
        AlgoJet algoJet = new AlgoJet();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("A", "B", 100));
        flights.add(new Flight("A", "C", 150));
        flights.add(new Flight("B", "C", 40));
        flights.add(new Flight("B", "D", 200));
        flights.add(new Flight("C", "D", 100));
        flights.add(new Flight("C", "E", 120));
        flights.add(new Flight("D", "E", 80));
        algoJet.initializeFlightGraph(flights);
        int result1 = algoJet.getCheapestFlight("A", "E");
        int result2 = algoJet.getCheapestFlight("A", "D");
        int result3 = algoJet.getCheapestFlight("A", "C");
        int result4 = algoJet.getCheapestFlight("B", "E");

        testAnswer("testExample_1", result1, 260);
        testAnswer("testExample_2", result2, 240);
        testAnswer("testExample_3", result3, 140);
        testAnswer("testExample_4", result4, 160);
    }

    public static void testUnreachableDestination() {
        AlgoJet algoJet = new AlgoJet();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("A", "B", 100));
        flights.add(new Flight("B", "C", 150));
        flights.add(new Flight("D", "E", 100));
        algoJet.initializeFlightGraph(flights);
        int result = algoJet.getCheapestFlight("A", "E");
        testAnswer("testUnreachableDestination", result, -1);
    }

    public static void testSameSourceAndDestination() {
        AlgoJet algoJet = new AlgoJet();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("A", "B", 100));
        flights.add(new Flight("B", "C", 150));
        flights.add(new Flight("C", "D", 100));
        algoJet.initializeFlightGraph(flights);
        int result = algoJet.getCheapestFlight("A", "A");
        testAnswer("testSameSourceAndDestination", result, 0);
    }

    public static void testCycle() {
        AlgoJet algoJet = new AlgoJet();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("A", "B", 200));
        flights.add(new Flight("B", "C", 150));
        flights.add(new Flight("C", "D", 120));
        flights.add(new Flight("C", "A", 180));
        flights.add(new Flight("C", "B", 100));
        flights.add(new Flight("B", "E", 300));
        algoJet.initializeFlightGraph(flights);
        int result = algoJet.getCheapestFlight("A", "E");
        testAnswer("testCycle", result, 500);
    }

    public static void testMultipleFlights() {
        AlgoJet algoJet = new AlgoJet();
        List<Flight> flights = new ArrayList<>();
        flights.add(new Flight("A", "B", 200));
        flights.add(new Flight("B", "C", 150));
        flights.add(new Flight("A", "C", 140));
        flights.add(new Flight("A", "C", 180));
        flights.add(new Flight("A", "B", 100));
        flights.add(new Flight("B", "E", 300));
        flights.add(new Flight("B", "E", 250));
        flights.add(new Flight("C", "E", 220));
        algoJet.initializeFlightGraph(flights);
        int result = algoJet.getCheapestFlight("A", "E");
        testAnswer("testMultipleFlights", result, 350);
    }

    public static void main(String[] args) {
        runUnitTests();
    }
}
