package com.tolani.Graphs_WIth_DP;

import com.tolani.Graphs.Graph1;
import com.tolani.Graphs.GraphType;
import com.tolani.Graphs.IsGraphWeighted;

import static com.tolani.Graphs_WIth_DP.p1_WarshallsAlgo.displayMatrix;

public class p2_NoOfPaths {


    public static void main(String[] args)
    {
        Graph1 g = new Graph1(4, GraphType.DIRECTED, IsGraphWeighted.NON_WEIGHTED);

        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(0,3);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(3,1);

        //g.displayGraph();

        NoOfPaths(g);
    }


    public static void NoOfPaths(Graph1 g)
    {
        int n = g.getN();    // no of vertices getter method

        int[][] N = new int[n][n];

        // for k = 0 : means no intermediate nodes allwd
        for(int i=0 ; i < n ; i++)
        {
            for(int j =0 ; j < n ;j++)
            {
                N[i][j] = g.adjMatrix[i][j];      // if thr is an edge then store 1 else store 0 paths
            }
        }

        displayMatrix(N);

        for(int k = 1; k <= n ; k++)    // at most 'n' vertices are allwd as an intermediate vertices
        {
            // below is we are just forming a T^k(i,j) for the values of 'k' : which will overwrite the previous T^k-1(i,j) formed
            for(int i=0 ; i < n ;i++)
            {
                for(int j=0 ; j < n ; j++)
                {
                    // this is the change frm abv algo

                    N[i][j] = N[i][j] + ( N[i][k-1] * N[k-1][j]);
                }
            }

        }

        System.out.println();
        displayMatrix(N);
    }
}
