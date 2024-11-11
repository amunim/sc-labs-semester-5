package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

/**
 * A mutable weighted directed graph with labeled vertices, represented using vertices.
 * Each vertex maintains a map of target vertices and their edge weights.
 */
public class ConcreteVerticesGraph implements Graph<String> {

    private final List<Vertex> vertices = new ArrayList<>();

    // Abstraction function:
    //   - The graph is represented by a list of vertices.
    //   - Each vertex maintains a map of target vertices with the weight of the edge.
    //   
    // Representation invariant:
    //   - All vertices are distinct, and no two vertices have the same label.
    //   - Each edge (target, weight) is valid, where weight > 0.
    //   
    // Safety from rep exposure:
    //   - Internal representation (vertices) is not exposed directly.
    //   - Any modifications to the graph require using the public methods.

    public ConcreteVerticesGraph() {
        // Constructor initializes an empty graph.
    }

    // Check representation invariant
    public void checkRep() {
        assert vertices != null : "Vertices list should not be null";

        // Ensure all vertices are distinct and edge weights are positive
        Set<String> vertexLabels = new HashSet<>();
        for (Vertex vertex : vertices) {
            assert vertex != null : "Vertex cannot be null";
            assert vertex.getOutgoingEdges() != null : "Outgoing edges map cannot be null";

            // Ensure the vertex label is unique in the graph
            assert !vertexLabels.contains(vertex.getLabel()) : "Duplicate vertex found";
            vertexLabels.add(vertex.getLabel());

            // Ensure all edges have positive weights
            for (Map.Entry<String, Integer> entry : vertex.getOutgoingEdges().entrySet()) {
                assert entry.getValue() > 0 : "Edge weight must be positive";
            }
        }
    }

    @Override
    public boolean add(String vertex) {
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) {
                return false; // Vertex already exists
            }
        }
        vertices.add(new Vertex(vertex));
        return true;
    }

    @Override
    public int set(String source, String target, int weight) {
        if (weight <= 0) {
            // If the weight is zero or negative, remove the edge if it exists
            for (Vertex v : vertices) {
                if (v.getLabel().equals(source)) {
                    return v.removeEdge(target);
                }
            }
            return 0; // Edge not found
        }

        // Add or update the edge with a positive weight
        for (Vertex v : vertices) {
            if (v.getLabel().equals(source)) {
                return v.addEdge(target, weight);
            }
        }

        // If the source vertex does not exist, create it and add the edge
        Vertex newSource = new Vertex(source);
        vertices.add(newSource);
        return newSource.addEdge(target, weight);
    }

    @Override
    public boolean remove(String vertex) {
        // Remove the vertex and any edges to/from it
        Vertex vertexToRemove = null;
        for (Vertex v : vertices) {
            if (v.getLabel().equals(vertex)) {
                vertexToRemove = v;
                break;
            }
        }
        if (vertexToRemove != null) {
            vertices.remove(vertexToRemove);
            // Remove any edges pointing to this vertex
            for (Vertex v : vertices) {
                v.removeEdge(vertex);
            }
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        Set<String> vertexLabels = new HashSet<>();
        for (Vertex vertex : vertices) {
            vertexLabels.add(vertex.getLabel());
        }
        return vertexLabels;
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourcesMap = new HashMap<>();
        for (Vertex vertex : vertices) {
            int weight = vertex.getWeightToTarget(target);
            if (weight > 0) {
                sourcesMap.put(vertex.getLabel(), weight);
            }
        }
        return sourcesMap;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        for (Vertex vertex : vertices) {
            if (vertex.getLabel().equals(source)) {
                return vertex.getOutgoingEdges();
            }
        }
        return new HashMap<>();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices()).append("\n");
        sb.append("Edges: \n");
        for (Vertex vertex : vertices) {
            sb.append(vertex).append("\n");
        }
        return sb.toString();
    }
}

/**
 * Represents a vertex in the graph.
 * Each vertex maintains a map of target vertices and their corresponding edge weights.
 */
class Vertex {

    private final String label;
    private final Map<String, Integer> outgoingEdges = new HashMap<>();

    // Abstraction function:
    //   - A vertex is represented by its label and a map of outgoing edges (target vertices and their weights).
    //   
    // Representation invariant:
    //   - The label of the vertex is unique within the graph.
    //   - The outgoing edges map contains valid target vertices with positive edge weights.

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public Map<String, Integer> getOutgoingEdges() {
        return new HashMap<>(outgoingEdges); // Return a copy to prevent external modification
    }

    public int addEdge(String target, int weight) {
        outgoingEdges.put(target, weight);
        return weight;
    }

    public int removeEdge(String target) {
        return outgoingEdges.remove(target, outgoingEdges.get(target)) ? 0 : 0;
    }

    public int getWeightToTarget(String target) {
        return outgoingEdges.getOrDefault(target, 0);
    }

    @Override
    public String toString() {
        return String.format("%s -> %s", label, outgoingEdges);
    }
}
