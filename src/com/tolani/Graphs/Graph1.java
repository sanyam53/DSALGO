package com.tolani.Graphs;

public class Graph1 {

    int n;                        // #of vertices

    boolean[][] adjMatrix;            // 2d array

    Graph1(int size)
    {
        this.n = size;

        adjMatrix = new boolean[n][n];

    }

    public void addEdge(int u , int v)
    {
        adjMatrix[u][v] = true;

       // if its undirected graph then

        adjMatrix[v][u] = true;
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
