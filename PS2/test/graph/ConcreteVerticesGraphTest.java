package graph;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Set;

/**
 * Unit tests for the ConcreteVerticesGraph class.
 * These tests validate the correct functionality of methods in ConcreteVerticesGraph.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

    @Override
    protected Graph emptyInstance() {
        return new ConcreteVerticesGraph();  // Return a fresh instance of ConcreteVerticesGraph
    }

    @Test
    public void shouldAddVertexToGraph() {
        graph.addVertex("A");
        assertTrue("Graph should contain vertex 'A'", graph.getVertices().contains("A"));
    }

    @Test
    public void shouldRemoveVertexFromGraph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeVertex("A");

        assertFalse("Graph should not contain vertex 'A' after removal", graph.getVertices().contains("A"));
        assertTrue("Graph should still contain vertex 'B'", graph.getVertices().contains("B"));
    }

    @Test
    public void shouldRemoveEdgeBetweenVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeEdge("A", "B");

        assertFalse("Graph should not have an edge between 'A' and 'B' after removal", graph.hasEdge("A", "B"));
    }

    @Test
    public void shouldAddEdgeBetweenVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        // Check if the edge exists (undirected graph)
        assertTrue("Graph should have an edge between 'A' and 'B'", graph.hasEdge("A", "B"));
        assertTrue("Graph should have an edge between 'B' and 'A'", graph.hasEdge("B", "A"));
    }

    @Test
    public void shouldGetAdjacentVerticesForVertex() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        Set<String> adjacentVertices = graph.getAdjacentVertices("A");
        assertTrue("Vertex 'A' should be adjacent to 'B'", adjacentVertices.contains("B"));
        assertTrue("Vertex 'A' should be adjacent to 'C'", adjacentVertices.contains("C"));
    }

    @Test
    public void shouldReturnCorrectGraphSize() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals("Graph should have 2 vertices", 2, graph.getVertices().size());
    }

    @Test
    public void shouldReturnCorrectEdgeCount() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertEquals("Graph should have 1 edge", 1, graph.edgeCount());
    }

    @Test
    public void shouldRemoveAllEdgesFromGraph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeAllEdges();
        assertEquals("Graph should have 0 edges after removing all edges", 0, graph.edgeCount());
    }

    @Test
    public void shouldRemoveAllVerticesFromGraph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeAllVertices();
        assertTrue("Graph should have no vertices after removing all vertices", graph.getVertices().isEmpty());
    }
}
