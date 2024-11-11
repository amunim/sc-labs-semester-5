/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;


import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */

 import java.util.Set;

import org.junit.Before;
;

public abstract class GraphInstanceTest {

    protected Graph graph;

    // Abstract method to get a fresh empty instance of Graph for each test
    protected abstract Graph emptyInstance();

    @Before
    public void setUp() {
        graph = emptyInstance();  // Set up fresh graph before each test
    }

    @Test
    public void testInitialVerticesEmpty() {
        // Ensure the graph starts with no vertices
        assertTrue("Graph should have no vertices initially", graph.getVertices().isEmpty());
    }

    @Test
    public void testAddVertex() {
        // Test adding a vertex to the graph
        graph.addVertex("A");
        assertTrue("Graph should contain vertex 'A'", graph.getVertices().contains("A"));
    }

    @Test
    public void testRemoveVertex() {
        // Test removing a vertex
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeVertex("A");

        assertFalse("Graph should not contain vertex 'A' after removal", graph.getVertices().contains("A"));
        assertTrue("Graph should still contain vertex 'B'", graph.getVertices().contains("B"));
    }

    @Test
    public void testAddEdge() {
        // Test adding an edge between two vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        // Check if the edge exists (undirected graph)
        assertTrue("Graph should have an edge between 'A' and 'B'", graph.hasEdge("A", "B"));
        assertTrue("Graph should have an edge between 'B' and 'A'", graph.hasEdge("B", "A"));
    }

    @Test
    public void testRemoveEdge() {
        // Test removing an edge between two vertices
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeEdge("A", "B");

        assertFalse("Graph should not have an edge between 'A' and 'B' after removal", graph.hasEdge("A", "B"));
    }

    @Test
    public void testGetAdjacentVertices() {
        // Test retrieving adjacent vertices for a given vertex
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
    public void testGraphSize() {
        // Test the size of the graph
        graph.addVertex("A");
        graph.addVertex("B");
        assertEquals("Graph should have 2 vertices", 2, graph.getVertices().size());
    }

    @Test
    public void testEdgeCount() {
        // Test the number of edges in the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        assertEquals("Graph should have 1 edge", 1, graph.edgeCount());
    }

    @Test
    public void testRemoveAllEdges() {
        // Test removing all edges from the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");
        graph.removeAllEdges();
        assertEquals("Graph should have 0 edges after removing all edges", 0, graph.edgeCount());
    }

    @Test
    public void testRemoveAllVertices() {
        // Test removing all vertices from the graph
        graph.addVertex("A");
        graph.addVertex("B");
        graph.removeAllVertices();
        assertTrue("Graph should have no vertices after removing all vertices", graph.getVertices().isEmpty());
    }
}
