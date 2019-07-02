package hsn;

import java.util.Scanner;

public class Graph {
    private int numV;
    private boolean directed;

    /**
     * @param numV number of vertices
     * @param isDirected whether the graph is directed or not
     */
    public Graph(int numV, boolean isDirected){
        this.numV = numV;
        this.directed = isDirected;
    }

    /**
     * @return number of vertices
     */
    int getNumV(){
        return numV;
    }

    /**
     * @return If the graph is directed or not
     */
    public boolean isDirected(){
        return directed;
    }
}
