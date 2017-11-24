package com.tolani.Graphs_WIth_DP;

import com.tolani.Graphs.Graph1;
import com.tolani.Graphs.GraphType;
import com.tolani.Graphs.IsGraphWeighted;

import java.util.Arrays;

import static com.tolani.Graphs_WIth_DP.p1_WarshallsAlgo.displayMatrix;

public class p3_AllPairsShortestPath {

    final static int INF = 99999;      // TC : dnt define Integer.Max  as an infinite here bcz ,  whn u add two Int.Max vals it bcm smaller by
                                                        // wrap around : so ur algo wont work : so define it as large num such tht adding it
                                                    // two time wont wrap around to the smaller number

    public static void main(String[] args)
    {
        Graph1 g = new Graph1(5, GraphType.DIRECTED, IsGraphWeighted.WEIGHTED);

        g.addWeightedEdge(0,1,3);
        g.addWeightedEdge(0,3,6);
        g.addWeightedEdge(1,3,2);
        g.addWeightedEdge(1,2,9);
        g.addWeightedEdge(3,4,4);
        g.addWeightedEdge(4,1,2);
        g.addWeightedEdge(4,2,1);

        g.displayGraph();
        System.out.println();
        floydWarshalls_APSP(g);
    }


    public static void floydWarshalls_APSP(Graph1 g)
    {
        int n = g.getN();

        int[][] D = new int[n][n];

        // code for finding the shortest path frm i to j

        int[][] pi = new int[n][n];    // taking an n by n matrix called pi : for which we will calculate pi functin

        // intalizing it with -1
        for(int i=0 ; i < n ;i++)
        {
            for(int j=0 ; j < n ;j++)
            {
                pi[i][j] = -1;
            }
        }

        for(int i=0 ; i < n ; i++)
        {
            for(int j =0 ; j < n ; j++)
            {
                if(g.adjMatrix[i][j] == 0) D[i][j] = INF;        // we r storing infinity if thr is no edge bw i and j
                else D[i][j] = g.adjMatrix[i][j];
            }
        }

        for(int i=0 ; i < n ;i++) D[i][i] =0;      // u can put all diagonl entries as 0 if u dnt wnt to consider it or else
                                                    // u cn put them as INF if u wnt to find a cycle length (which is shortest)

        for(int k = 1 ; k <= n ; k++)
        {
            for(int i=0 ; i < n ;i++)
            {
                for(int j =0 ; j < n ; j++)
                {
                    // logic is simple : if (i to k + k to j )  is smaller thn previous path then update it

                    if(D[i][j] > ( D[i][k-1] + D[k-1][j]) )
                    {
                        D[i][j] = D[i][k-1] + D[k-1][j];

                        pi[i][j] = k-1;
                    }
                }
            }
        }

        displayMatrix(D);

        // lets print the shortest path using the pi function and pi matrix we ve calculated

        System.out.println();
        int src = 0; int dest = 2;
        System.out.print(dest + "<- ");
        printShoretestPath(src,dest,pi);
        System.out.print(src);
    }

    public static void printShoretestPath(int i , int j ,int[][] pi)
    {
        if(pi[i][j] != -1) {

            int k = pi[i][j];

            System.out.print(k + " <- ");

            printShoretestPath(i, k, pi);
            printShoretestPath(k, j, pi);
        }

    }


}
