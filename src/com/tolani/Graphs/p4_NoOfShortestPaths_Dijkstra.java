package com.tolani.Graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

import java.util.*;
import com.tolani.Graphs.Graph2_v2.*;
import static com.tolani.Graphs.Color.*;

public class p4_NoOfShortestPaths_Dijkstra {

    static int[] NS = new int[5];

    public static void main(String[] args) {
        Graph2_v2 g = new Graph2_v2(5, GraphType.DIRECTED);       // size 5 means vertices frm 0 to 4 : tc here

        // weighted graph example

        g.addWeightedEdge(0, 1, 3);
        g.addWeightedEdge(0, 3, 5);    // change to 5 from 6
        g.addWeightedEdge(1, 3, 2);
        g.addWeightedEdge(1, 2, 7);     // changed to 7 from 9
        g.addWeightedEdge(3, 4, 4);
        g.addWeightedEdge(4, 1, 2);
        g.addWeightedEdge(4, 2, 1);

        g.displayGraph();

        DijkstraSSSP(g, 0);

        // printing result of dijsktra : edges thr in the graph will be (pi(v) , v) , and priority(v) is weight of shortes path frm src to 'v'
        for (Graph2_v2.Vertex v : g.vertexList.values()) {
            System.out.print(v.piValue + " " + v.data + " " + v.getPriority());
            System.out.println();
        }

        // printing NS

        System.out.println();
        // NS[i]=0 means it is src vertx
        System.out.println("no of shortest paths from vertex src to other vertices :" + Arrays.toString(NS));

    }


    public static void DijkstraSSSP(Graph2_v2 g , int source)
    {
        Graph2_v2.Vertex src = g.vertexList.get(source);

//You should create your priority Queue a little bit different by specifying how its elements should be compared. That is done by passing an anonymous Comparator for the Vertex class:

        PriorityQueue<Graph2_v2.Vertex> minHeap = new PriorityQueue<Graph2_v2.Vertex>(g.noOfVertices, new Comparator<Graph2_v2.Vertex>() {
            @Override
            public int compare(Graph2_v2.Vertex v1, Graph2_v2.Vertex v2) {
                return Integer.compare(v1.getPriority(),v2.getPriority());
            }
        });

        src.setPriority(0);
        minHeap.add(src);
        visitDijkstra(g,minHeap,src);

    }

    public static void visitDijkstra(Graph2_v2 g,PriorityQueue minHeap,Graph2_v2.Vertex v)
    {

        while(!minHeap.isEmpty())
        {
            Graph2_v2.Vertex u =(Graph2_v2.Vertex) minHeap.remove();

            u.setColor(RED);

            for(Graph2_v2.AdjListNode adjNode : u.adjList)
            {
                Graph2_v2.Vertex vObj = g.vertexList.get(adjNode.vertexId);

                if(vObj.getColor() == GREEN)
                {
                    vObj.setPriority( u.getPriority() + adjNode.getWeight());        // P(v) = P(u) + W(u,v);
                    minHeap.add(vObj);
                    vObj.setColor(BLACK);
                    vObj.piValue = u.data;

                    // code for NS
                    // this is the first time u r adding thru the edge (u,v) : now chck if 'u' is source then NS is 1 , bcz we hvnt intialized NS
                    // if 'u' is nt a src then may be some othr vertex : nd thr is case tht #shoretest path to tht other vertex may be > 1
                    // so u cant assign 1 in tht case : in tht case assign NS[u] : tht u ve already computed
                    if(u.data == 0) {
                        NS[vObj.data] = 1;
                    }
                    else
                    {
                        NS[vObj.data] = NS[u.data];
                    }
                }

                else if(vObj.getColor() == BLACK)        // if node is already thr in the data structure
                {
                    if(vObj.getPriority() > ( u.getPriority() + adjNode.getWeight()))    // then u calculate the priority if > then this then update
                    {
                        vObj.setPriority(u.getPriority() + adjNode.getWeight());       // this is actually a decrease key function

                        // so now to change Priority into the DS we dnt hv in built function in java
                        // so we remove then change priority then reinsert it into the heap

                        minHeap.remove(vObj);     // removing
                        minHeap.add(vObj);        // adding again : we ve updated the priority in the 1st step see

                        vObj.piValue = u.data;

                        // code for NS
                        // so u discovered a shorter path than previous path : so u update
                        NS[vObj.data] = NS[u.data];
                    }

                    else if(vObj.getPriority() == ( u.getPriority() + adjNode.getWeight()))
                    {
                        NS[vObj.data] = NS[vObj.data] + NS[u.data];
                    }
                }
            }
        }

    }

}
