package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

/**
 * A mutable weighted directed graph with labeled vertices, represented using edges.
 * Vertices are represented as Strings and edges are represented as Edge objects.
 */
public class ConcreteEdgesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    // Abstraction Function:
    //   - The graph is represented as a set of vertices and a list of edges.
    //   - Each edge has a source vertex, a target vertex, and a weight.
    //   - The graph contains all vertices that are either source or target of an edge.
    //   
    // Representation Invariant:
    //   - vertices should contain all the unique vertices involved in any edge.
    //   - Each edge must have a source, target, and a positive weight.
    //   - No edge should have a negative or zero weight.
    //   
    // Safety from rep exposure:
    //   - The internal representation (`vertices` and `edges`) is not exposed directly.
    //   - Any external modification to the graph requires using the public methods.

    public ConcreteEdgesGraph() {
        // Constructor initializes an empty graph.
    }

    // Check representation invariant
    public void checkRep() {
        assert vertices != null : "Vertices set should not be null";
        assert edges != null : "Edges list should not be null";
        
        // Check that each edge is valid
        for (Edge edge : edges) {
            assert edge.getWeight() > 0 : "Edge weight must be positive";
            assert vertices.contains(edge.getSource()) : "Source vertex must be in the vertex set";
            assert vertices.contains(edge.getTarget()) : "Target vertex must be in the vertex set";
        }
    }

    @Override
    public boolean add(String vertex) {
        return vertices.add(vertex);  // Adds the vertex if not already present
    }

    @Override
    public int set(String source, String target, int weight) {
        if (weight <= 0) {
            // Remove edge if weight is zero or less
            Edge edgeToRemove = null;
            for (Edge edge : edges) {
                if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                    edgeToRemove = edge;
                    break;
                }
            }
            if (edgeToRemove != null) {
                edges.remove(edgeToRemove);
                return edgeToRemove.getWeight();  // Return the previous weight of the edge
            }
            return 0;  // No edge found, no removal
        }

        // Add or update edge with positive weight
        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                int previousWeight = edge.getWeight();
                edge.setWeight(weight);  // Update the weight of the existing edge
                return previousWeight;
            }
        }

        // Add a new edge if not found
        edges.add(new Edge(source, target, weight));
        vertices.add(source);  // Ensure the source vertex exists
        vertices.add(target);  // Ensure the target vertex exists
        return 0;  // No previous edge, return 0
    }

    @Override
    public boolean remove(String vertex) {
        // Remove the vertex from the set and all edges involving the vertex
        vertices.remove(vertex);
        edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        return true;
    }

    @Override
    public Set<String> vertices() {
        return new HashSet<>(vertices);  // Return a copy to avoid external mutation
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourcesMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sourcesMap.put(edge.getSource(), edge.getWeight());
            }
        }
        return sourcesMap;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targetsMap = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targetsMap.put(edge.getTarget(), edge.getWeight());
            }
        }
        return targetsMap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges: \n");
        for (Edge edge : edges) {
            sb.append(edge).append("\n");
        }
        return sb.toString();
    }
}

/**
 * A class representing an edge in the graph.
 * Each edge has a source vertex, a target vertex, and a weight.
 */
class Edge {

    private final String source;
    private final String target;
    private int weight;

    // Abstraction function:
    //   - Each edge connects a source vertex to a target vertex with a specific weight.
    //   
    // Representation Invariant:
    //   - source and target must be non-null strings.
    //   - weight must be positive.

    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();  // Ensure the invariant holds
    }

    // Check representation invariant
    private void checkRep() {
        assert source != null : "Source cannot be null";
        assert target != null : "Target cannot be null";
        assert weight > 0 : "Weight must be positive";
    }

    public String getSource() {
        return source;
    }

    public String getTarget() {
        return target;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        assert weight > 0 : "Weight must be positive";
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s [Weight: %d]", source, target, weight);
    }
}
