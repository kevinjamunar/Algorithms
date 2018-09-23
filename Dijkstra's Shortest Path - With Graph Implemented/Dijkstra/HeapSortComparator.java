package Dijkstra;

import Graph.Vertex;
import java.util.Comparator;

public class HeapSortComparator implements Comparator<Vertex> {

    /*
        overrides the superclass' implementation
        to compare vertices by their 'CurrentDistance'
     */
    public int compare(Vertex vertexA, Vertex vertexB){
        return vertexA.getCurrentDistance() - vertexB.getCurrentDistance();
    }
}
