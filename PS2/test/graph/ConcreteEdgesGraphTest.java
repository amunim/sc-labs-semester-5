package graph;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Map;

/**
 * Unit tests for the ConcreteEdgesGraph class.
 * These tests validate the correct functionality of the methods defined in the ConcreteEdgesGraph implementation.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

    @Override
    protected Graph emptyInstance() {
        // Ensure this method returns a fresh instance of ConcreteEdgesGraph
        return new ConcreteEdgesGraph();  
    }

    @Test
    public void shouldAddVertexToGraph() {
        graph.addVertex("A");
        assertTrue("The graph should contain vertex 'A'", graph.getVertices().contains("A"));
    }

    @Test
    public void shouldRemoveVertexFromGraph() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeVertex("A");

        assertFalse("Vertex 'A' should be removed", graph.getVertices().contains("A"));
        assertTrue("Vertex 'B' should remain in the graph", graph.getVertices().contains("B"));
    }

    @Test
    public void shouldAddEdgeBetweenVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 10);  // Create an edge with weight 10

        Map<String, Integer> adjacentVertices = graph.targets("A");
        assertTrue("An edge from 'A' to 'B' should exist", adjacentVertices.containsKey("B"));
        assertEquals("The weight of the edge from 'A' to 'B' should be 10", 10, (int) adjacentVertices.get("B"));
    }

    @Test
    public void shouldRemoveEdgeBetweenVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 10);  // Create an edge
        graph.removeEdge("A", "B");  // Remove the edge
    
        Map<String, Integer> adjacentVertices = graph.targets("A");
        assertFalse("There should be no edge from 'A' to 'B' after removal", adjacentVertices.containsKey("B"));
    }

    @Test
    public void shouldRetrieveAllAdjacentVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.set("A", "B", 5);
        graph.set("A", "C", 10);

        Map<String, Integer> adjacentVertices = graph.targets("A");
        assertTrue("Vertex 'A' should have an edge to 'B'", adjacentVertices.containsKey("B"));
        assertTrue("Vertex 'A' should have an edge to 'C'", adjacentVertices.containsKey("C"));
    }

    @Test
    public void shouldReturnCorrectGraphSize() {
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals("The graph should contain exactly 2 vertices", 2, graph.getVertices().size());
    }

    @Test
    public void shouldTrackEdgeCountCorrectly() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 10);
        assertEquals("The graph should have exactly 1 edge", 1, graph.edgeCount());
    
        graph.removeEdge("A", "B");
        assertEquals("The graph should have 0 edges after removal", 0, graph.edgeCount());
    }

    @Test
    public void shouldRemoveAllEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 10);
        graph.removeAllEdges();
        
        assertEquals("The graph should have no edges after removal", 0, graph.edgeCount());
    }

    @Test
    public void shouldRemoveAllVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeAllVertices();
        
        assertTrue("The graph should contain no vertices after removal", graph.getVertices().isEmpty());
    }

    @Test
    public void shouldIdentifySourceVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 5);

        Map<String, Integer> sources = graph.sources("B");
        assertTrue("Source vertex 'A' should be present for target 'B'", sources.containsKey("A"));
        assertEquals("The edge from 'A' to 'B' should have a weight of 5", 5, (int) sources.get("A"));
    }

    @Test
    public void shouldIdentifyTargetVertices() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.set("A", "B", 10);

        Map<String, Integer> targets = graph.targets("A");
        assertTrue("Target vertex 'B' should be present for source 'A'", targets.containsKey("B"));
        assertEquals("The edge from 'A' to 'B' should have a weight of 10", 10, (int) targets.get("B"));
    }
}
