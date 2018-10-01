package problem.railroad.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Dijkstra {

	private Set<String> visitedNodes;
	private Set<String> unvisitedNodes;
	private Map<String, String> predecessors;
	private Map<String, Integer> distance;

	private String source;

	private Graph graph;

	public Dijkstra(Graph graph) {
		this.graph = graph;
	}

	/**
	 * Run the Dijkstra algorithm loop
	 * 
	 * @param source Source node
	 */
	public void run(String source) {
		visitedNodes = new HashSet<String>();
		unvisitedNodes = new HashSet<String>();
		distance = new HashMap<String, Integer>();
		predecessors = new HashMap<String, String>();

		this.source = source;

		unvisitedNodes.add(source);

		while (unvisitedNodes.size() > 0) {

			String node = getMin(unvisitedNodes);
			visitedNodes.add(node);
			unvisitedNodes.remove(node);
			findMinDistances(node);
		}
	}

	/**
	 * Gather the route from the source to a given target
	 * 
	 * @param target Target node
	 * @return List with the nodes that form the path from source to target
	 */
	public LinkedList<String> getPath(String target) {
		LinkedList<String> path = new LinkedList<String>();
		String step = target;

		if (predecessors.get(step) == null) {
			return null;
		}

		path.add(step);

		String currentPredecessor = predecessors.get(step);

		while (currentPredecessor != null && !(step.equals(source) && path.size() > 1)) {
			step = currentPredecessor;
			path.add(step);

			currentPredecessor = this.predecessors.get(step);
		}

		Collections.reverse(path);
		return path;
	}

	private void findMinDistances(String node) {
		List<String> adjacentNodes = getNeighbors(node);
		for (String target : adjacentNodes) {

			Integer distanceFromNode = getShortestDistance(node) + graph.getDistanceBetweenNeighbor(node, target);

			if (getShortestDistance(target) > distanceFromNode) {

				distance.put(target, getShortestDistance(node) + graph.getDistanceBetweenNeighbor(node, target));
				predecessors.put(target, node);
				unvisitedNodes.add(target);
			}
		}

	}

	private List<String> getNeighbors(String node) {
		List<String> neighbors = new ArrayList<String>();
		for (String neighbor : this.graph.getNodes().get(node).keySet()) {
			if (!isVisited(neighbor) || neighbor.equals(source)) {
				neighbors.add(neighbor);
			}
		}
		return neighbors;
	}

	private String getMin(Set<String> nodes) {
		String min = null;
		for (String node : nodes) {
			if (min == null) {
				min = node;
			} else {
				if (getShortestDistance(node) < getShortestDistance(min)) {
					min = node;
				}
			}
		}
		return min;
	}

	private int getShortestDistance(String destination) {
		Integer d = distance.get(destination);
		if (d == null) {
			return Integer.MAX_VALUE;
		} else {
			return d;
		}
	}

	private boolean isVisited(String node) {
		return visitedNodes.contains(node);
	}
}
