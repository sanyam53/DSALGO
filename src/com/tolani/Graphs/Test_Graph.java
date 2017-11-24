package com.tolani.Graphs;

public class Test_Graph {

    public static void main(String[] args)
    {
        // adjacency list

        Graph2 G = new Graph2(5);

        G.addEdge(0,1);     // as graph is undrctd this wl put an edge bw both 0-1 and 1-0
        G.addEdge(2,3);
        G.addEdge(0,4);
        G.addEdge(2,4);
        G.addEdge(3,1);
        G.addEdge(3,4);
        G.addEdge(0,3);

        G.displayGraph();

        System.out.printf("\n\n");

        // adjacency matrix
        System.out.println();
        Graph1 g = new Graph1(5,GraphType.UNDIRECTED,IsGraphWeighted.NON_WEIGHTED);

        g.addEdge(0,1);     // as graph is undrctd this wl put an edge bw both 0-1 and 1-0
        g.addEdge(2,3);
        g.addEdge(0,4);
        g.addEdge(2,4);
        g.addEdge(3,1);
        g.addEdge(3,4);
        g.addEdge(0,3);

        g.displayGraph();

    }
}
