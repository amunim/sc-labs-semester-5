package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An implementation of Graph using vertices and their associated edges.
 */
public class ConcreteVerticesGraph extends Graph {

    private final List<Vertex> vertices = new ArrayList<>();

    // Constructor
    public ConcreteVerticesGraph() {
        // Initially, the graph is empty
    }

    // Add a vertex to the graph
    public boolean add(String vertex) {
        if (!containsVertex(vertex)) {
            vertices.add(new Vertex(vertex));
            return true;
        }
        return false;
    }

    // Set an edge between source and target with the given weight
    public int set(String source, String target, int weight) {
        add(source);  // Ensure the source vertex exists
        add(target);  // Ensure the target vertex exists

        Vertex sourceVertex = findVertex(source);
        Vertex targetVertex = findVertex(target);

        // Check if the edge already exists
        for (Edge edge : sourceVertex.edges) {
            if (edge.target.equals(target)) {
                int oldWeight = edge.weight;
                edge.weight = weight;
                return oldWeight;
            }
        }

        // If no existing edge, create a new one
        sourceVertex.edges.add(new Edge(target, weight));
        return 0;
    }

    // Remove a vertex and its associated edges
    public boolean remove(String vertex) {
        Vertex vertexToRemove = findVertex(vertex);
        if (vertexToRemove == null) {
            return false;  // Vertex does not exist
        }

        vertices.remove(vertexToRemove);

        // Remove edges pointing to this vertex
        for (Vertex v : vertices) {
            v.edges.removeIf(edge -> edge.target.equals(vertex));
        }
        return true;
    }

    // Return the set of vertices in the graph
    public Set<String> vertices() {
        Set<String> result = new HashSet<>();
        for (Vertex vertex : vertices) {
            result.add(vertex.label);
        }
        return result;
    }

    // Helper method to find a vertex by its label
    private Vertex findVertex(String label) {
        for (Vertex vertex : vertices) {
            if (vertex.label.equals(label)) {
                return vertex;
            }
        }
        return null;  // Should not happen if vertices are correctly managed
    }

    // Helper method to check if a vertex exists in the graph
    private boolean containsVertex(String vertex) {
        return vertices.stream().anyMatch(v -> v.label.equals(vertex));
    }

    // Inner class to represent a Vertex
    private static class Vertex {
        String label;
        List<Edge> edges;

        // Constructor for the Vertex
        Vertex(String label) {
            this.label = label;
            this.edges = new ArrayList<>();
        }
    }

    // Inner class to represent an Edge
    private static class Edge {
        String target;
        int weight;

        // Constructor for the Edge
        Edge(String target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }
}
