package problem.railroad.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Graph {

	/**
	 * Map representing nodes, his neighbors and they weight
	 */
	private Map<String, HashMap<String, Integer>> nodes;

	public Graph() {
		this.nodes = new HashMap<String, HashMap<String, Integer>>();
	}

	public void addNode(String name) {
		this.nodes.put(name, new HashMap<>());
	}

	public Map<String, HashMap<String, Integer>> getNodes() {
		return this.nodes;
	}

	public Set<String> getNeighbors(String name) {
		return this.nodes.keySet();
	}

	public void addNeighbor(String nodeName, String neighborName, Integer weight) {

		if (!this.nodes.containsKey(nodeName))
			this.addNode(nodeName);

		this.nodes.get(nodeName).put(neighborName, weight);
	}

	public Integer getDistanceBetweenNeighbor(String node, String neighbor) {

		if (!hasNeighbor(node, neighbor))
			return null;

		return this.nodes.get(node).get(neighbor);
	}

	private boolean hasNeighbor(String node, String neighbor) {

		Map<String, Integer> nodeNeighbors = this.nodes.get(node);
		return nodeNeighbors.containsKey(neighbor);
	}
	
	/**
	 * Calculates the total distance of a given route
	 * @param route String array representing a route
	 * @return Total distance
	 */
	public Integer calculateRoute(String[] route) {

		Integer count = 0;

		String prevNodeName = null;

		for (String nextNodeName : route) {

			if (prevNodeName != null) {

				Integer distance = this.getDistanceBetweenNeighbor(prevNodeName, nextNodeName);

				if (distance == null)
					return null;

				count += distance;
			}

			prevNodeName = nextNodeName;
		}

		return count;
	}

	/**
	 * Calculates the number of possible trips for a given start node and end node
	 * @param startNode 
	 * @param endNode
	 * @param tripLimit Maximum trip limit
	 * @param exact Set if the calculation should get the exact number of trips set on trip limit
	 * @return Number of possible trips
	 */
	public Integer calculatePossibleTrips(String startNode, String endNode, Integer tripLimit, boolean exact) {

		List<String> visited = new ArrayList<>();

		int pathCount = 0;

		pathCount = calculateTripsUntil(startNode, endNode, visited, pathCount, tripLimit, exact, true);

		return pathCount;
	}

	public Integer calculatePossibleTrips(String startNode, String endNode, Integer tripLimit) {

		return calculatePossibleTrips(startNode, endNode, tripLimit, false);
	}

	/**
	 * calculatePossibleTrips helper function to do the recursive job
	 */
	private Integer calculateTripsUntil(String startNode, String endNode, List<String> visited, Integer tripCount,
			Integer tripLimit, boolean exact, boolean first) {

		if (!first) {
			visited.add(startNode);
		}

		if ((startNode.equals(endNode) && visited.contains(endNode))
				&& ((exact && visited.size() == tripLimit) || (!exact))) {
			tripCount++;

		}
		for (String node : this.nodes.get(startNode).keySet()) {

			if (visited.size() < tripLimit) {
				tripCount = calculateTripsUntil(node, endNode, visited, tripCount, tripLimit, exact, false);
			}
		}

		visited.remove(startNode);

		return tripCount;
	}

	/**
	 * Calculates the number of possible routes for a given start node and end node
	 * @param startNode
	 * @param endNode
	 * @param distanceLimit Limit of total distance
	 * @return Number of possible routes
	 */
	public Integer calculateDiferentRoutes(String startNode, String endNode, Integer distanceLimit) {

		int routeCount = 0;

		LinkedList<Integer> distances = new LinkedList<>();

		routeCount = calculateDiferentRoutesUntil(startNode, endNode, routeCount, distances, distanceLimit, true);

		return routeCount;
	}

	/**
	 * calculateDiferentRoutes helper function to do the recursive job
	 */
	private Integer calculateDiferentRoutesUntil(String startNode, String endNode, Integer routeCount,
			LinkedList<Integer> distances, Integer distanceLimit, boolean first) {

		if (startNode.equals(endNode) && !first)
			routeCount++;

		for (String node : this.nodes.get(startNode).keySet()) {

			int totalDistance = countTotalDistance(distances) + this.nodes.get(startNode).get(node);

			if (totalDistance < distanceLimit) {

				distances.add(this.nodes.get(startNode).get(node));
				routeCount = calculateDiferentRoutesUntil(node, endNode, routeCount, distances, distanceLimit, false);
			}
		}

		if (!distances.isEmpty())
			distances.removeLast();

		return routeCount;
	}

	private Integer countTotalDistance(List<Integer> distance) {

		return distance.stream().mapToInt(i -> i.intValue()).sum();
	}

	@Override
	public String toString() {

		String returnData = "";

		for (Entry<String, HashMap<String, Integer>> entry : nodes.entrySet()) {

			String neighbors = "";

			for (Entry<String, Integer> edges : entry.getValue().entrySet()) {
				neighbors += "(" + edges.getKey() + ", " + edges.getValue() + ")";
			}

			returnData += entry.getKey() + " => " + neighbors + "\n";
		}

		return returnData;
	}
}
