package graph;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * A concrete implementation of the Graph using edges and their weights.
 */
public class ConcreteEdgesGraph extends Graph {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Constructor
    public ConcreteEdgesGraph() {
        // Initially, the graph is empty
    }

    // Add a vertex to the graph
    public boolean add(String vertex) {
        if (vertices.add(vertex)) {
            return true;
        }
        return false;
    }

    // Set an edge between source and target with a specific weight
    public int set(String source, String target, int weight) {
        vertices.add(source);
        vertices.add(target);

        for (Edge edge : edges) {
            if (edge.source.equals(source) && edge.target.equals(target)) {
                int oldWeight = edge.weight;
                edge.weight = weight;
                return oldWeight;
            }
        }

        // If no existing edge, create a new one
        edges.add(new Edge(source, target, weight));
        return 0;
    }

    // Remove a vertex from the graph and its associated edges
    public boolean remove(String vertex) {
        if (!vertices.contains(vertex)) {
            return false;
        }
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.source.equals(vertex) || edge.target.equals(vertex));
        return true;
    }

    // Return the set of vertices in the graph
    public Set<String> vertices() {
        return new HashSet<>(vertices);
    }

    // Return the sources for a given target
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> result = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.target.equals(target)) {
                result.put(edge.source, edge.weight);
            }
        }
        return result;
    }

    // Return the targets for a given source
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> result = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.source.equals(source)) {
                result.put(edge.target, edge.weight);
            }
        }
        return result;
    }

    // Inner class to represent an Edge
    private static class Edge {
        String source;
        String target;
        int weight;

        // Constructor for the Edge
        Edge(String source, String target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
        }
    }
}
