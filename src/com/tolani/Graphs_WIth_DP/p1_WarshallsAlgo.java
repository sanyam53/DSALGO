package com.tolani.Graphs_WIth_DP;

import com.tolani.Graphs.Graph1;
import com.tolani.Graphs.GraphType;
import com.tolani.Graphs.IsGraphWeighted;

import java.util.Arrays;

public class p1_WarshallsAlgo {

    public static void main(String[] args)
    {
        Graph1 g = new Graph1(4,GraphType.DIRECTED, IsGraphWeighted.NON_WEIGHTED);

        g.addEdge(0,1);
        g.addEdge(0,3);
        g.addEdge(1,0);
        g.addEdge(2,0);
        g.addEdge(2,1);
        g.addEdge(2,3);
        g.addEdge(3,1);

        //g.displayGraph();

        warshallsAlgo(g);
    }


    public static void warshallsAlgo(Graph1 g)
    {
        int n = g.getN();    // no of vertices getter method

        int[][] T = new int[n][n];

        // for k = 0 : means no intermediate nodes allwd
        for(int i=0 ; i < n ; i++)
        {
            for(int j =0 ; j < n ;j++)
            {
                T[i][j] = g.adjMatrix[i][j];         // T(i,j) = Aij
            }
        }

        //displayBooleanMatrix(T);

        for(int k = 1; k <= n ; k++)    // at most 'n' vertices are allwd as an intermediate vertices : so  k <=n
        {
            // below is we are just forming a T^k(i,j) for the values of 'k' : which will overwrite the previous T^k-1(i,j) formed
            for(int i=0 ; i < n ;i++)
            {
                for(int j=0 ; j < n ; j++)
                {
                    // now 'k' is implicit here

                    if( (T[i][j] == 1) || (T[i][k-1] == 1 && T[k-1][j] == 1))   // whn 'k = 4' means first 4 vertices(0 to 3) are allwd
                    {                                                                    // so we will chck (i to k) means it is (i to 3)
                        T[i][j] = 1;                                                // as we indexed our vertices , same for (j to k)
                    }
                    else
                    {
                        T[i][j] = 0;
                    }
                }
            }

        }

        displayMatrix(T);
    }


    public static void displayMatrix(int[][] matrix)
    {
        int k=0 ;

        for(int i[] : matrix)
        {
            System.out.print(k + " : ");
            k++;
            for(int j : i)
            {
                if(j == 99999)
                {
                    System.out.print("INF ");
                }
                else System.out.print(j + " ");
            }
            System.out.println();
        }
    }

}
