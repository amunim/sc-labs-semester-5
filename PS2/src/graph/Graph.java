package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * A mutable directed graph with labeled vertices and weighted edges.
 * The vertices are labeled using distinct immutable {@code String} labels,
 * and edges have a positive weight of type {@code int}.
 */
public class Graph {

    private Map<String, Map<String, Integer>> adjacencyList;

    // Constructor to create a new empty graph
    public Graph() {
        adjacencyList = new HashMap<>();
    }

    // Adds a vertex to the graph if it does not already exist
    public void addVertex(String vertex) {
        adjacencyList.putIfAbsent(vertex, new HashMap<>());
    }

    // Removes a vertex from the graph, along with any edges connected to it
    public void removeVertex(String vertex) {
        adjacencyList.remove(vertex);
        for (Map<String, Integer> edges : adjacencyList.values()) {
            edges.remove(vertex);
        }
    }

    // Adds or updates an edge between two vertices with a specified weight
    public int set(String source, String target, int weight) {
        addVertex(source);  // Ensure the source vertex exists
        addVertex(target);  // Ensure the target vertex exists

        // If the edge already exists, update its weight and return the old weight
        if (adjacencyList.get(source).containsKey(target)) {
            int oldWeight = adjacencyList.get(source).get(target);
            adjacencyList.get(source).put(target, weight);
            return oldWeight;
        }

        // If no edge exists, create a new edge with the specified weight
        adjacencyList.get(source).put(target, weight);
        return 0;  // No previous edge, so return 0
    }

    // Adds an unweighted edge between two vertices (with a default weight of 1)
    public void addEdge(String source, String target) {
        addVertex(source);  // Ensure the source vertex exists
        addVertex(target);  // Ensure the target vertex exists

        // Create the edge with a default weight of 1 (for unweighted graph)
        set(source, target, 1);
    }

    // Checks if there is an edge between the specified vertices
    public boolean hasEdge(String vertex1, String vertex2) {
        return adjacencyList.containsKey(vertex1) && adjacencyList.get(vertex1).containsKey(vertex2);
    }

    // Returns a set of all vertices in the graph
    public Set<String> getVertices() {
        return adjacencyList.keySet();
    }

    // Returns a set of all adjacent vertices for the specified vertex
    public Set<String> getAdjacentVertices(String vertex) {
        return adjacencyList.containsKey(vertex) ? adjacencyList.get(vertex).keySet() : new HashSet<>();
    }

    // Returns the weight of the edge between source and target, or null if no such edge exists
    public Integer getEdgeWeight(String source, String target) {
        return adjacencyList.containsKey(source) ? adjacencyList.get(source).get(target) : null;
    }

    // Removes an edge between two vertices (directed graph)
    public void removeEdge(String vertex1, String vertex2) {
        if (adjacencyList.containsKey(vertex1)) {
            adjacencyList.get(vertex1).remove(vertex2);
        }
        // Note: In case of an undirected graph, you may want to also remove the reverse edge
        if (adjacencyList.containsKey(vertex2)) {
            adjacencyList.get(vertex2).remove(vertex1);  // Remove edge from the reverse direction (optional for undirected graphs)
        }
    }

    // Removes all edges in the graph
    public void removeAllEdges() {
        for (Map<String, Integer> edges : adjacencyList.values()) {
            edges.clear();
        }
    }

    // Removes all vertices and edges from the graph
    public void removeAllVertices() {
        adjacencyList.clear();
    }

    // Returns the number of vertices currently in the graph
    public int size() {
        return adjacencyList.size();
    }

    // Returns the total number of edges in the graph (counting directed edges)
    public int edgeCount() {
        int count = 0;
        for (Map<String, Integer> edges : adjacencyList.values()) {
            count += edges.size();
        }
        return count;
    }

    // Static method to create an empty graph instance
    public static Graph emptyInstance() {
        return new Graph();
    }

    // Returns a map of all target vertices (with edge weights) for a given source vertex
    public Map<String, Integer> targets(String vertex) {
        return adjacencyList.getOrDefault(vertex, new HashMap<>());
    }

    // Returns a map of all source vertices (with edge weights) for a given target vertex
    public Map<String, Integer> sources(String vertex) {
        Map<String, Integer> sources = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : adjacencyList.entrySet()) {
            if (entry.getValue().containsKey(vertex)) {
                sources.put(entry.getKey(), entry.getValue().get(vertex));
            }
        }
        return sources;
    }
}
