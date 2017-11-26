package com.tolani.Graphs;

public class Graph1 {

    int n;                        // #of vertices
    public int[][] adjMatrix;            // 2d array

    GraphType gtype;
    IsGraphWeighted gweight;

    // constructor
    public Graph1(int size,GraphType gt,IsGraphWeighted gw)
    {
        this.n = size;
        adjMatrix = new int[n][n];

        gtype = gt;
        gweight = gw;
    }

    // getter and setter methods
    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public GraphType getGtype() {
        return gtype;
    }

    public void setGtype(GraphType gtype) {
        this.gtype = gtype;
    }

    // utility methods
    public void addEdge(int u , int v)
    {
        if (gtype == GraphType.DIRECTED) {
            adjMatrix[u][v] = 1;
        }

        else      // if its undirected graph then
        {
            adjMatrix[u][v] = 1;
            adjMatrix[v][u] = 1;
        }
    }

    public void addWeightedEdge(int u , int v , int weight)
    {

        if (gtype == GraphType.DIRECTED) {
            adjMatrix[u][v] = weight;
        }

        else      // if its undirected graph then
        {
            adjMatrix[u][v] = weight;
            adjMatrix[v][u] = weight;
        }
    }

    public void displayGraph()
    {

        for(int i=0 ; i < adjMatrix.length ; i++)
        {
            System.out.print(i + " : ");

            for(int j=0 ; j < adjMatrix.length ; j++)
            {
                System.out.print(adjMatrix[i][j] + " ");
            }

            System.out.println();
        }
    }
}
