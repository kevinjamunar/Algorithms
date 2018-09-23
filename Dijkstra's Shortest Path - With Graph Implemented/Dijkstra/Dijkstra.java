package Dijkstra;
import Graph.Graph;
import Graph.Vertex;
import Graph.Edge;
import java.util.PriorityQueue;

/*
    Dijkstra's Shortest Path implementation using a PriorityQueue
    to hold Vertex nodes whose shortest paths have not been determined.
    Reduces the complexity to O(mlogn)
    m - vertices
    n - edges
 */

public class Dijkstra{

    private Graph graph;
    private PriorityQueue<Vertex> minHeap;

    public Dijkstra(Graph graph){
        this.graph = graph;
        this.minHeap = new PriorityQueue<>(200, new HeapSortComparator());
    }
    /*
        removes the min element from the heap.
     */
    private Vertex extractMin(){
        return this.minHeap.remove();
    }

    /*
        Adds all vertices to the heap.
     */
    private void buildHeap(Vertex sourceVertex){
        sourceVertex.setCurrentDistance(0); //initializes the starting vertex's distance (trivial)
        for(int i=1; i<=graph.getVertexCount(); i++){
            minHeap.add(this.graph.getValue(i));
        }
    }

    /*
        returns true if the heap is empty, false otherwise.
     */
    private boolean isHeapEmpty(){
        return minHeap.isEmpty();
    }

    /*
        Applies Dijkstra's Shortest Path algorithm to the graph
     */
    public void performDijkstra(Vertex sourceVertex){

        buildHeap(sourceVertex);

        while(!isHeapEmpty()) {

            //removes the min element from the heap and updates its isShortestPath value
            Vertex minVertex = extractMin();
            minVertex.setIsShortestPath(true);
            sourceVertex = minVertex;

            for (int i = 0; i < sourceVertex.getEdgeList().size(); i++) {

                //compute the distances for each edge.
                Edge currentEdge = sourceVertex.getEdgeList().get(i); //get the current edge
                int calculatedDistance = sourceVertex.getCurrentDistance() + currentEdge.getEdgeWeight(); //get its new distance
                Vertex currentVertex = currentEdge.getHeadVertex(); //get the vertex associated with the current edge

                //if the new distance is less than the current edge's distance, update the current edge's distance & previous vertex
                /*
                NB: Due to the PriorityQueue not ordering the vertex after changing its value,
                the vertex is removed from the PriorityQueue, updated, and then re-added.
                */
                if (calculatedDistance < currentVertex.getCurrentDistance()){
                    minHeap.remove(currentVertex);
                    currentVertex.setCurrentDistance(calculatedDistance);
                    currentVertex.setPrevVertex(sourceVertex);
                    minHeap.add(currentVertex);
                }
            }
        }
    }

    /*
        returns an array of shortest path distances to the specified vertices passed into the array.
     */
    public int[] getShortestDistances(int[] vertices){
        int arraySize = vertices.length;
        int[] shortestDistances = new int[arraySize];

        for(int i=0; i<arraySize; i++){
            shortestDistances[i] = graph.getValue(vertices[i]).getCurrentDistance();
        }
        return shortestDistances;
    }
}
