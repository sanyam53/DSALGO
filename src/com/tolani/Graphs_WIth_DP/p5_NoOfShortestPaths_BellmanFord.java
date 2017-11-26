package com.tolani.Graphs_WIth_DP;

import com.tolani.Graphs.Graph3;
import com.tolani.Graphs.GraphType;
import com.tolani.Graphs.IsGraphWeighted;

import java.util.*;

public class p5_NoOfShortestPaths_BellmanFord {

        public static final int INF = 99999;

        public static void main(String[] args)
        {
            Graph3 g = new Graph3(5,GraphType.DIRECTED, IsGraphWeighted.WEIGHTED);

            g.addWeightedEdge(0,1,3);
            g.addWeightedEdge(0,3,5);   // changed to 5 frm 6
            g.addWeightedEdge(1,3,2);
            g.addWeightedEdge(1,2,7);    // changed to 7 frm 9
            g.addWeightedEdge(3,4,4);
            g.addWeightedEdge(4,1,2);
            g.addWeightedEdge(4,2,1);

            System.out.print("edge list : ");
            g.displayEdgeList();
            System.out.println();

            System.out.println();
            SSSP_BellmanFordAlgo(g,0);
        }

        public static void SSSP_BellmanFordAlgo(Graph3 g, int src)
        {
            int n = g.getN();

            // Taking a O(n) space array D : which will contain distances frm src to all othr vertices
            int[] D = new int[n];

            // pi function : to print the shortest path : pi fun actually sets the immediate predecessor value

            LinkedList<Integer>[] pi = new LinkedList[5];

            for(int i=0 ; i < n ;i++)
            {
                pi[i] = new LinkedList<>();
            }

            // counting total #of shortest paths
            int[] NSP = new int[n];


            // intialization as : if thr exist a dirct edge then it is w(src,v) else INFINITE
            for(int i=0 ; i < n ; i++)
            {
                Graph3.Edge e = g.getEdge(src,i);     // this will essentially search in the edgelist : by iterating thru edge list

                if(e != null)
                {
                    D[i] = e.getEdgeWeight();

                    // u update the pi value here
                    pi[i].add(src);  // bcz to reach 'i' u r goint thru source vertex

                    // code for counting NSP
                    NSP[i] = 1;
                }
                else       // BCZ we are returning null if thr is no such edge present in the edgelist
                {
                    D[i] = INF;

                    // intialize to be -1
                    pi[i].add(-1);

                    // code for counting NSP : if thr is no edge then
                    NSP[i] = 0;
                }
            }

            // now u ve to apply RELAXATION procedure for more V-2 times or (n-2) times bcz in total u apply it (n-1) times
            // abv intialization is already applied it one time : so n-1 -1  = n-2 times left

            for(int k = 2; k <= n ; k++)
            {
                // we culd hv taken every vertex 'v' and then look at all incmng edges to 'v' and apply relaxation : but during this process
                // we came to knw tht every edge is considered exactly once so instead we cn just traverst thru all the edges frm the edgelist
                // exactly once : thts it O(E) time
                // travers thru the edge list and pick up every edge and apply relaxation procedure

                for(Graph3.Edge e : g.allEdges)
                {
                    // consider an edge (u,v) lets say
                    int u = e.getU();
                    int v = e.getV();

                    if(D[u] + e.getEdgeWeight() < D[v])         // here e.getEdgeWeight is essentially a w(u,v)
                    {
                        D[v] = D[u] + e.getEdgeWeight();

                        // set the pi value : as u came inside means u r setting immediate predecsr value for 'v'
                        pi[v].clear();
                        pi[v].add(u);     // this is updating in the array list thts why we r using indices OR we cn clear and add simple

                        // code for NSP : if u discovered a new smaller path thru the vertex 'u' then u update #of shortest paths to be NSP[u}
                        NSP[v] = NSP[u];
                    }

                    // code for counting NSP : else this conditin is nt needed for original prblm
                    else if((D[u] + e.getEdgeWeight()) == D[v]  &&  !pi[v].contains(u) )    // means 'u' shuld nt be in the parent list of 'v'
                    {
                        // then we will add 'u' to the parent list first then work on NSP
                        pi[v].add(u);    // this is only adding not clearing bfr adding here so multiple parents get stored


                        // as u r discovring the shortest path of same length then u add the #of shortest paths to the already #of Shortst Path
                        NSP[v] = NSP[v] + NSP[u];

                    }
                }

            }

            // printing the shortest paths to all othr vertices frm the src node : means printing the array D
            System.out.println("dist array : " + Arrays.toString(D));

            //System.out.println("pi array" + Arrays.toString(pi));       //   : just to verify pi array

            System.out.println();

            //printShortestPath(pi,2);         // printing the shortest path from src vertex to vertex 2


            // code for NSP
            System.out.println();
            System.out.println("no of shortest paths are : " + Arrays.toString(NSP));

        }

        // recursive function to print the shortest path

        public static void printShortestPath(int[] pi, int v)
        {
            int k = pi[v];     // to reach 'v' , we go thru 'k'

            if(k != -1)        // termination condition for recursion
            {
                System.out.print(k + "<- ");
                printShortestPath(pi,k);
            }
        }
}
