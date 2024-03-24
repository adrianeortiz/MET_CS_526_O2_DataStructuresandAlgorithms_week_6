import heapq
import math

"""
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

 Running Time Analysis of get_cheapest_flight
 --------------------
 <Your analysis here>
 """


class Flight:
    def __init__(self, source, destination, price):
        self.source = source
        self.destination = destination
        self.price = price


class AlgoJet:
    def __init__(self):
        self.graph = {}  # Recommended graph is {city: {destination : best_price}}

    def initialize_flight_graph(self, flights: list[Flight]):
        pass

    def get_cheapest_flight(self, source, destination):
        # Initialize distance to each city as infinity
        distances = {city: math.inf for city in self.graph.keys()}
        # The distance to the source city is 0 since you are already there
        distances[source] = 0
        # Initialize a priority queue with key 0 (distance to city) and source (city to process)
        pq = [(0, source)]
        return None


class TestAlgoFlights:
    def run_unit_tests(self):
        self.test_example()
        self.test_unreachable_destination()
        self.test_same_source_and_destination()
        self.test_cycle()
        self.test_multiple_flights()


    def print_test_result(self, test_name, result):
        color = "\033[92m" if result else "\033[91m"
        reset = "\033[0m"
        print(f"{color}[{result}] {test_name}{reset}")

    def test_answer(self, test_name, result, expected):
        if result == expected:
            self.print_test_result(test_name, True)
        else:
            self.print_test_result(test_name, False)
            print(f"Expected: {expected} \nGot:      {result}")

    def test_example(self):
        algo_jet = AlgoJet()
        flights = [
            Flight("A", "B", 100),
            Flight("A", "C", 150),
            Flight("B", "C", 40),
            Flight("B", "D", 200),
            Flight("C", "D", 100),
            Flight("C", "E", 120),
            Flight("D", "E", 80),
        ]
        algo_jet.initialize_flight_graph(flights)
        result1 = algo_jet.get_cheapest_flight("A", "E")
        result2 = algo_jet.get_cheapest_flight("A", "D")
        result3 = algo_jet.get_cheapest_flight("A", "C")
        result4 = algo_jet.get_cheapest_flight("B", "E")

        self.test_answer("test_example_1", result1, 260)
        self.test_answer("test_example_2", result2, 240)
        self.test_answer("test_example_3", result3, 140)
        self.test_answer("test_example_4", result4, 160)

    def test_unreachable_destination(self):
        algo_jet = AlgoJet()
        flights = [
            Flight("A", "B", 100),
            Flight("B", "C", 150),
            Flight("D", "E", 100),
        ]

        algo_jet.initialize_flight_graph(flights)

        result = algo_jet.get_cheapest_flight("A", "E")
        self.test_answer("test_unreachable_destination", result, -1)

    def test_same_source_and_destination(self):
        algo_jet = AlgoJet()
        flights = [
            Flight("A", "B", 100),
            Flight("B", "C", 150),
            Flight("C", "D", 100),
        ]

        algo_jet.initialize_flight_graph(flights)

        result = algo_jet.get_cheapest_flight("A", "A")
        self.test_answer("test_same_source_and_destination", result, 0)

    def test_cycle(self):
        algo_jet = AlgoJet()
        flights = [
            Flight("A", "B", 200),
            Flight("B", "C", 150),
            Flight("C", "D", 120),
            Flight("C", "A", 180),
            Flight("C", "B", 100),
            Flight("B", "E", 300),

        ]

        algo_jet.initialize_flight_graph(flights)

        result = algo_jet.get_cheapest_flight("A", "E")
        self.test_answer("test_cycle", result, 500)

    def test_multiple_flights(self):
        algo_jet = AlgoJet()
        flights = [
            Flight("A", "B", 200),
            Flight("B", "C", 150),
            Flight("A", "C", 140),
            Flight("A", "C", 180),
            Flight("A", "B", 100),
            Flight("B", "E", 300),
            Flight("B", "E", 250),
            Flight("C", "E", 220),

        ]

        algo_jet.initialize_flight_graph(flights)

        result = algo_jet.get_cheapest_flight("A", "E")
        self.test_answer("test_multiple_flights", result, 350)



if __name__ == '__main__':
    test_runner = TestAlgoFlights()
    test_runner.run_unit_tests()