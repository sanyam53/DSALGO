package com.tolani.Graphs;

//import com.tolani.Graphs.GraphType;

public class Graph1 {

    int n;                        // #of vertices
    boolean[][] adjMatrix;            // 2d array
    GraphType gtype;

    Graph1(int size,GraphType gt)
    {
        this.n = size;
        adjMatrix = new boolean[n][n];

        gtype = gt;
    }

    public void addEdge(int u , int v)
    {
        if (gtype == GraphType.DIRECTED) {
            adjMatrix[u][v] = true;
        }

        else      // if its undirected graph then
        {
            adjMatrix[u][v] = true;
            adjMatrix[v][u] = true;
        }
    }

    public void displayGraph()
    {

        for(int j=0 ; j < adjMatrix.length ; j++)
        {
            System.out.print(j + " : ");

            for(int i=0 ; i < adjMatrix.length ; i++)
            {
                if(adjMatrix[i][j])System.out.print("1 ");
                else System.out.print("0 ");
            }

            System.out.println();
        }
    }
}
